package my.lib;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class AjaxController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookCoverDao bookCoverDao;
    @Autowired
    private OpenApi openApi;

    @GetMapping("/ajax/books")
    public Page<Book> getBooks(String state, Pageable pageable) {
        return bookDao.findByStateOrderByCreatedAtDesc(Book.State.valueOf(state), pageable);
    }

    @GetMapping("/ajax/bookCovers/{isbn}")
    public byte[] getBookCover(@PathVariable String isbn) {
        return bookCoverDao.findById(isbn).get().getImage();
    }

    @GetMapping("/ajax/search")
    public List<OpenApi.BookInfo> search(String query) {
        return openApi.search(query);
    }

    @PostMapping("/ajax/addBook")
    @Transactional
    public void addBook(String isbn) {
        OpenApi.BookInfo bookInfo = openApi.get(isbn);
        bookDao.save(new Book(bookInfo.getIsbn(), bookInfo.getTitle(), bookInfo.getAuthor(), bookInfo.getPublishedAt()));
        bookCoverDao.save(new BookCover(bookInfo.getIsbn(), bookInfo.getCoverBytes()));
    }

    @PostMapping("/ajax/moveBooks")
    public void moveBooks(@RequestBody MoveBook moveBook) {
        bookDao.findByIsbnIn(moveBook.isbnList).forEach(book -> {
            book.setState(moveBook.getState());
            bookDao.save(book);
        });
    }

    @PostMapping("/ajax/deleteBooks")
    @Transactional
    public void deleteBooks(@RequestBody List<String> isbnList) {
        if (isbnList.size() > 10) throw new RuntimeException();
        isbnList.forEach(isbn -> {
            bookDao.deleteById(isbn);
            bookCoverDao.deleteById(isbn);
        });
    }

    @Data
    public static class MoveBook {
        private Book.State state;
        private List<String> isbnList;
    }
}