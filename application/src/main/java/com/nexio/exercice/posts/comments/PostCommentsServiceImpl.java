package com.nexio.exercice.posts.comments;

import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;
import org.springframework.stereotype.Service;

@Service
public class PostCommentsServiceImpl implements PostCommentsService {

    private PostCommentRepresentationAdapter postCommentRepresentationAdapter;

    private PostCommentsProvider postCommentsProvider;

    PostCommentsServiceImpl(PostCommentRepresentationAdapter postCommentRepresentationAdapter, PostCommentsProvider postCommentsProvider) {
        this.postCommentRepresentationAdapter = postCommentRepresentationAdapter;
        this.postCommentsProvider = postCommentsProvider;
    }

    @Override
    public PostCommentRepresentation addNewPostComment(PostCommentAddVO postCommentAddVO) {

        PostComment postComment = postCommentRepresentationAdapter.adapt(postCommentAddVO);

        postComment = postCommentsProvider.addOrUpdatePostComment(postComment);

        return postCommentRepresentationAdapter.adapt(postComment);
    }
}
