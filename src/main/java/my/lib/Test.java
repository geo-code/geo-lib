package my.lib;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
public class Test {
    private static final String SEQUENCE = "test_id_seq";
    @Id
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE)
    @GeneratedValue(generator = SEQUENCE)
    private Integer id;
    private String name;
}
