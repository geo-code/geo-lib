package my.lib;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookDao extends PagingAndSortingRepository<Book, String> {
    Page<Book> findByState(Book.State state, Pageable pageable);
}
