package com.nexio.exercice.posts.comments.adapters;

import org.springframework.stereotype.Component;

@Component
public class PostCommentDtoAdapter {
    public PostCommentAddVO adapt(final PostCommentAddRequest postCommentAddRequest, final Long postId) {

        PostCommentAddVO postCommentAddVO = new PostCommentAddVO();

        postCommentAddVO.setPostId(postId);
        postCommentAddVO.setBody(postCommentAddRequest.getBody());

        return postCommentAddVO;
    }
}
