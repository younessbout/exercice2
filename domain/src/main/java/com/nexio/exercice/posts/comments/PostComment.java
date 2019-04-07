package com.nexio.exercice.posts.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Post_Comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_COMMENT_SEQ")
    @SequenceGenerator(sequenceName = "POST_COMMENT_SEQ", name = "POST_COMMENT_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "author")
    private Long author;

    @Column(name = "body")
    private String body;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
