package my.lib;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/ajax/books")
    public List<Book> getBooks() {
        return bookDao.findByOrderByCreatedAtDesc();
    }

    @GetMapping("/ajax/bookCovers/{isbn}")
    public byte[] getBookCover(@PathVariable String isbn) {
        return bookCoverDao.findById(isbn).get().getImage();
    }
}
