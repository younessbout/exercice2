package com.nexio.exercice.posts.comments;

import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.adapters.CommentAddRequest;
import com.nexio.exercice.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static com.nexio.exercice.constants.ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT;

@RestController
public class PostCommentsController {

    @PostMapping(path = ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS)
    public ResponseEntity addNewCommentToPost(@PathVariable("post_id") String postId, @RequestBody CommentAddRequest comment) throws URISyntaxException {
        return ResponseEntity.created(new URI(Utils.constructUrlWithParams(ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId, ""))).build();
    }
}
