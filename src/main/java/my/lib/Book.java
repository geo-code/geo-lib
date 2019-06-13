package my.lib;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private Date publishedAt;
    private Date createdAt = new Date();
    private Date readAt;
    private State state = State.next;

    public enum State {inbox, next, read}

    public Book(String isbn, String title, String author, Date publishedAt) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
    }
}
