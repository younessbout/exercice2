package com.nexio.exercice.posts.comments;

import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;

import java.util.List;

public interface PostCommentsService {

    PostCommentRepresentation addNewPostComment(final PostCommentAddVO postCommentDO);

    List<PostCommentRepresentation> getPostCommentsForPost(final Long postId);
}
