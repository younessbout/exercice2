package com.nexio.exercice.posts.comments;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
}
