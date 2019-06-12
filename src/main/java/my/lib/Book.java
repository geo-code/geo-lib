package my.lib;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private Date publishedAt;
    private Date createdAt;
    private Date readAt;
    private State state;

    public enum State {inbox, next, read}
}
