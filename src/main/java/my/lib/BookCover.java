package my.lib;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BookCover {
    @Id
    private String isbn;
    private byte[] image;
}
