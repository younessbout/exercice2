package com.nexio.exercice.posts.comments;

import com.nexio.exercice.exceptions.FunctionalException;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;
import com.nexio.exercice.posts.comments.adapters.PostCommentUpdateVO;
import com.nexio.exercice.posts.comments.exceptions.PostCommentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PostCommentsServiceImpl implements PostCommentsService {

    private PostCommentRepresentationAdapter postCommentRepresentationAdapter;

    private PostCommentsProvider postCommentsProvider;

    PostCommentsServiceImpl(PostCommentRepresentationAdapter postCommentRepresentationAdapter, PostCommentsProvider postCommentsProvider) {
        this.postCommentRepresentationAdapter = postCommentRepresentationAdapter;
        this.postCommentsProvider = postCommentsProvider;
    }

    @Override
    public PostCommentRepresentation addNewPostComment(PostCommentAddVO postCommentAddVO) {

        PostComment postComment = postCommentRepresentationAdapter.adaptPostCommentAddVO(postCommentAddVO);

        postComment = postCommentsProvider.addOrUpdatePostComment(postComment);

        return postCommentRepresentationAdapter.adapt(postComment);
    }

    @Override
    public List<PostCommentRepresentation> getPostCommentsForPost(Long postId) {
        List<PostComment> comments = postCommentsProvider.getPostCommentsForPost(postId);

        return postCommentRepresentationAdapter.adapt(comments);
    }

    @Override
    public PostCommentRepresentation updatePostComment(PostCommentUpdateVO postCommentUpdateVO) throws FunctionalException {

        PostComment postComment = postCommentsProvider.getPostCommentById(postCommentUpdateVO.getPostCommentId()).orElseThrow(() -> new PostCommentNotFoundException());

        postComment.setBody(postCommentUpdateVO.getBody());
        postComment.setUpdateDate(LocalDateTime.now());

        return postCommentRepresentationAdapter.adapt(postComment);
    }

    @Override
    public void deletePostComment(Long postCommentId) throws FunctionalException {
        PostComment postComment = postCommentsProvider.getPostCommentById(postCommentId).orElseThrow(() -> new PostCommentNotFoundException());

        postCommentsProvider.deletePostComment(postCommentId);
    }
}
