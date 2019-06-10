package my.lib;

import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<Test, Integer> {
}
