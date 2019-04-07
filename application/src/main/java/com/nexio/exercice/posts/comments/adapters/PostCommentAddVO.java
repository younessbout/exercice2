package com.nexio.exercice.posts.comments.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentAddVO {

    private Long postId;

    private String body;
}
