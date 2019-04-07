package com.nexio.exercice.integration.post.comments;

import com.nexio.exercice.Application;
import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.PostComment;
import com.nexio.exercice.posts.comments.PostCommentsProvider;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddRequest;
import com.nexio.exercice.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("test")
@Transactional
@Rollback
public class PostCommentsControllerIT_Update {

    @Autowired
    private PostCommentsProvider postCommentsProvider;

    @Autowired
    MockMvc mvc;

    @Test
    public void test_update_comment_successful() throws Exception {

        final Long postId = 1L;
        final Long postCommentToUpdate = 9999999L;

        PostCommentAddRequest request = new PostCommentAddRequest();
        request.setBody("Test Comment Updated");

        ResultActions putActionMetaResult = mvc.perform(
                put(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId, postCommentToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonBytes(request)));

        putActionMetaResult.andExpect(status().is(HttpStatus.OK.value()));

        String response = putActionMetaResult.andReturn().getResponse().getContentAsString();

        Optional<PostComment> postComment = postCommentsProvider.getPostCommentById(postCommentToUpdate);
        assertThat(postComment.isPresent());

        assertThat(postComment.get().getBody()).isEqualTo(request.getBody());
        assertThat(postComment.get().getCreationDate()).isNotNull();
    }

    @Test
    public void test_update_non_existing_comment() throws Exception {

        final Long postId = 1L;
        final Long postCommentToUpdate = 19999999L;

        PostCommentAddRequest request = new PostCommentAddRequest();
        request.setBody("Test Comment Updated");

        ResultActions putActionMetaResult = mvc.perform(
                put(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId, postCommentToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonBytes(request)));

        putActionMetaResult.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("code").value("post.comment.not.found"));
    }

}
