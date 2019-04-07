package com.nexio.exercice.posts.comments;

import java.util.List;
import java.util.Optional;

public interface PostCommentsProvider {

    Optional<PostComment> getPostCommentById(final Long id);

    PostComment addOrUpdatePostComment(final PostComment postComment);

    List<PostComment> getPostCommentsForPost(final Long postId);

    void deletePostComment(final Long postCommentId);
}
