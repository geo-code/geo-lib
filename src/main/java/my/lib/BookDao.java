package my.lib;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookDao extends PagingAndSortingRepository<Book, String> {
    Page<Book> findByStateOrderByCreatedAtDesc(Book.State state, Pageable pageable);

    Page<Book> findByStateOrderByReadAtDesc(Book.State state, Pageable pageable);

    List<Book> findByIsbnIn(List<String> isbnList);
}
