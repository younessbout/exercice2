package com.nexio.exercice.posts.comments;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostCommentsProviderImpl implements PostCommentsProvider {

    private PostCommentsRepository postCommentsRepository;

    public PostCommentsProviderImpl(PostCommentsRepository postCommentsRepository) {
        this.postCommentsRepository = postCommentsRepository;
    }


    @Override
    public Optional<PostComment> getPostCommentById(Long id) {
        return postCommentsRepository.findById(id);
    }

    @Override
    public PostComment addOrUpdatePostComment(PostComment postComment) {
        return postCommentsRepository.save(postComment);
    }

    @Override
    public List<PostComment> getPostCommentsForPost(Long postId) {
        return postCommentsRepository.findByPostId(postId);
    }

    @Override
    public void deletePostComment(Long postCommentId) {
        postCommentsRepository.deleteById(postCommentId);
    }


}
