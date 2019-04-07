package com.nexio.exercice.posts.comments;

import com.nexio.exercice.posts.comments.PostComment;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class PostCommentRepresentationAdapter {

    public PostCommentRepresentation adapt(final PostComment postComment){
        PostCommentRepresentation postCommentRepresentation = new PostCommentRepresentation();

        postCommentRepresentation.setAuthor(postComment.getAuthor());
        postCommentRepresentation.setBody(postComment.getBody());
        postCommentRepresentation.setCreationDate(postComment.getCreationDate());
        postCommentRepresentation.setId(postComment.getId());
        postCommentRepresentation.setPostId(postComment.getPostId());
        postCommentRepresentation.setUpdateDate(postComment.getUpdateDate());

        return postCommentRepresentation;
    }

    public PostComment adapt(final PostCommentAddVO postCommentAddVO){
        PostComment postComment = new PostComment();

        postComment.setBody(postCommentAddVO.getBody());

        postComment.setAuthor(1L); //This value should be the connected user
        postComment.setCreationDate(LocalDateTime.now());
        postComment.setPostId(postCommentAddVO.getPostId());

        return postComment;
    }
}
