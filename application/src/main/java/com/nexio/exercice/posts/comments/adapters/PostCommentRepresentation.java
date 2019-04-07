package com.nexio.exercice.posts.comments.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentRepresentation {

    private Long id;

    private Long postId;

    private Long author;

    private String body;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
