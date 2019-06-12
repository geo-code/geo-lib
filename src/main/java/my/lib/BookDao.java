package my.lib;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, String> {
    List<Book> findByOrderByCreatedAtDesc();
}
