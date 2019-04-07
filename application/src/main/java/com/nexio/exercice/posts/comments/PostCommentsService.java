package com.nexio.exercice.posts.comments;

import com.nexio.exercice.exceptions.FunctionalException;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;
import com.nexio.exercice.posts.comments.adapters.PostCommentUpdateVO;

import java.util.List;

public interface PostCommentsService {

    PostCommentRepresentation addNewPostComment(final PostCommentAddVO postCommentDO);

    List<PostCommentRepresentation> getPostCommentsForPost(final Long postId);

    PostCommentRepresentation updatePostComment(final PostCommentUpdateVO postCommentUpdateVO) throws FunctionalException;
}
