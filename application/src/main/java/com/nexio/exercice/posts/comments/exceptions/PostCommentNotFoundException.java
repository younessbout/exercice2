package com.nexio.exercice.posts.comments.exceptions;

import com.nexio.exercice.exceptions.FunctionalException;

public class PostCommentNotFoundException extends FunctionalException {

    public PostCommentNotFoundException() {
        super("post.comment.not.found", "Post Comment not Found");
    }

}
