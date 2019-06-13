package my.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AjaxController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookCoverDao bookCoverDao;
    @Autowired
    private OpenApi openApi;

    @GetMapping("/ajax/inboxBooks")
    public Page<Book> getInboxBooks(Pageable pageable) {
        return bookDao.findByState(Book.State.inbox, pageable);
    }

    @GetMapping("/ajax/nextBooks")
    public Page<Book> getNextBooks(Pageable pageable) {
        return bookDao.findByState(Book.State.next, pageable);
    }

    @GetMapping("/ajax/readBooks")
    public Page<Book> getReadBooks(Pageable pageable) {
        return bookDao.findByState(Book.State.read, pageable);
    }

    @GetMapping("/ajax/bookCovers/{isbn}")
    public byte[] getBookCover(@PathVariable String isbn) {
        return bookCoverDao.findById(isbn).get().getImage();
    }

    @GetMapping("/ajax/search")
    public List<OpenApi.BookInfo> search(String query) {
        return openApi.search(query);
    }
}