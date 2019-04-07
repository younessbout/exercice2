package com.nexio.exercice.posts.comments;

import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;

public interface PostCommentsService {

    PostCommentRepresentation addNewPostComment(final PostCommentAddVO postCommentDO);
}
