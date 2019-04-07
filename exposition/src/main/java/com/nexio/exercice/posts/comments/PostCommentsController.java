package com.nexio.exercice.posts.comments;

import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddRequest;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddVO;
import com.nexio.exercice.posts.comments.adapters.PostCommentDtoAdapter;
import com.nexio.exercice.posts.comments.adapters.PostCommentRepresentation;
import com.nexio.exercice.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static com.nexio.exercice.constants.ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT;

@RestController
public class PostCommentsController {

    private PostCommentsService postCommentsService;

    private PostCommentDtoAdapter postCommentDtoAdapter;

    public PostCommentsController(PostCommentsService postCommentsService, PostCommentDtoAdapter postCommentDtoAdapter) {
        this.postCommentsService = postCommentsService;
        this.postCommentDtoAdapter = postCommentDtoAdapter;
    }


    @PostMapping(path = ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS)
    public ResponseEntity<PostCommentRepresentation> addNewCommentToPost(@PathVariable("post_id") Long postId, @Validated @RequestBody PostCommentAddRequest comment) throws URISyntaxException {

        PostCommentAddVO postCommentAddVO = postCommentDtoAdapter.adapt(comment, postId);
        PostCommentRepresentation postCommentRepresentation = postCommentsService.addNewPostComment(postCommentAddVO);

        return ResponseEntity.created(new URI(Utils.constructUrlWithParams(ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId.toString(), postCommentRepresentation.getId().toString()))).
                body(postCommentRepresentation);
    }
}
