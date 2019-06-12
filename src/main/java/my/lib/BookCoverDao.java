package my.lib;

import org.springframework.data.repository.CrudRepository;

public interface BookCoverDao extends CrudRepository<BookCover, String> {
}
