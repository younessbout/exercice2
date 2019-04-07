package com.nexio.exercice.posts.comments.adapters;

import org.springframework.stereotype.Component;

@Component
public class PostCommentDtoAdapter {
    public PostCommentAddVO adaptPostCommentAddRequest(final PostCommentAddRequest postCommentAddRequest, final Long postId) {

        PostCommentAddVO postCommentAddVO = new PostCommentAddVO();

        postCommentAddVO.setPostId(postId);
        postCommentAddVO.setBody(postCommentAddRequest.getBody());

        return postCommentAddVO;
    }

    public PostCommentUpdateVO adaptPostCommentUpdateRequest(final PostCommentUpdateRequest postCommentUpdateRequest, final Long postCommentId) {

        PostCommentUpdateVO postCommentUpdateVO = new PostCommentUpdateVO();

        postCommentUpdateVO.setPostCommentId(postCommentId);
        postCommentUpdateVO.setBody(postCommentUpdateRequest.getBody());

        return postCommentUpdateVO;
    }
}
