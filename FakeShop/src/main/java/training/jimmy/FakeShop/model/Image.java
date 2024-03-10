package training.jimmy.FakeShop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "image_table")
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Blob image;

    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
