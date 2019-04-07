package com.nexio.exercice.posts.comments;

import java.util.Optional;

public interface PostCommentsProvider {

    Optional<PostComment> getPostCommentById(final Long id);

    PostComment addOrUpdatePostComment(final PostComment postComment);
}
