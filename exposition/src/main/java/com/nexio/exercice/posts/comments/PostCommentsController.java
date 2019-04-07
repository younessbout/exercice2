package com.nexio.exercice.posts.comments;

import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.adapters.*;
import com.nexio.exercice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.nexio.exercice.constants.ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT;

@RestController
public class PostCommentsController {

    private final Logger LOGGER = LoggerFactory.getLogger(PostCommentsController.class);

    private PostCommentsService postCommentsService;

    private PostCommentDtoAdapter postCommentDtoAdapter;

    public PostCommentsController(PostCommentsService postCommentsService, PostCommentDtoAdapter postCommentDtoAdapter) {
        this.postCommentsService = postCommentsService;
        this.postCommentDtoAdapter = postCommentDtoAdapter;
    }


    @PostMapping(path = ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS)
    public ResponseEntity<PostCommentRepresentation> addNewCommentToPost(@PathVariable("post_id") Long postId, @Validated @RequestBody PostCommentAddRequest comment) throws URISyntaxException {

        LOGGER.debug("REST request add new Post Comment for <Post ID> : <{}>. <Comment> : <{}> ", postId, comment);

        PostCommentAddVO postCommentAddVO = postCommentDtoAdapter.adaptPostCommentAddRequest(comment, postId);
        PostCommentRepresentation postCommentRepresentation = postCommentsService.addNewPostComment(postCommentAddVO);

        return ResponseEntity.created(new URI(Utils.constructUrlWithParams(ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId.toString(), postCommentRepresentation.getId().toString()))).
                body(postCommentRepresentation);
    }

    @GetMapping(path = ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS)
    public ResponseEntity<List<PostCommentRepresentation>> getListOfComments(@PathVariable("post_id") Long postId) {

        LOGGER.debug("REST request Get Post Comments for <Post ID> : <{}>.", postId);

        List<PostCommentRepresentation> comments = postCommentsService.getPostCommentsForPost(postId);

        return ResponseEntity.ok(comments);
    }

    @PutMapping(path = ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT)
    public ResponseEntity<PostCommentRepresentation> updateCommentToPost(@PathVariable("post_id") Long postId, @PathVariable("comment_id") Long postCommentId,
                                                                         @Validated @RequestBody PostCommentUpdateRequest comment) throws Exception {

        LOGGER.debug("REST request Update Post Comment for <Post ID, Comment Id> : <{}, {}>. <Comment> : <{}> ", postId, postCommentId, comment);

        PostCommentUpdateVO postCommentUpdateVO = postCommentDtoAdapter.adaptPostCommentUpdateRequest(comment, postCommentId);
        PostCommentRepresentation postCommentRepresentation = postCommentsService.updatePostComment(postCommentUpdateVO);

        return ResponseEntity.ok(postCommentRepresentation);
    }

    @DeleteMapping(path = ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT)
    public ResponseEntity deleteCommentToPost(@PathVariable("post_id") Long postId, @PathVariable("comment_id") Long postCommentId) throws Exception {

        LOGGER.debug("REST request Delete Post Comment for <Post ID, Comment Id> : <{}, {}>.", postId, postCommentId);

        postCommentsService.deletePostComment(postCommentId);

        return ResponseEntity.ok().build();
    }
}
