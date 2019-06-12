package my.lib;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GeoLibApplicationTests {
    @Autowired
    private BookDao bookDao;

    @Test
    public void contextLoads() {
        Book book = new Book();
        book.setIsbn("123456789");
        book.setTitle("대성당");
        book.setAuthor("레이먼드 카버");
        book.setPublishedAt(new Date());
        book.setCreatedAt(new Date());
        book.setReadAt(new Date());
        book.setState(Book.State.next);
        bookDao.save(book);
    }

}
