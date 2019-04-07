package com.nexio.exercice.integration.post.comments;

import com.nexio.exercice.Application;
import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.PostComment;
import com.nexio.exercice.posts.comments.PostCommentsProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("test")
@Transactional
public class PostCommentsControllerIT_Delete {

    @Autowired
    private PostCommentsProvider postCommentsProvider;

    @Autowired
    MockMvc mvc;

    @Test
    public void test_delete_comment_successful() throws Exception {

        final Long postId = 1L;
        final Long postCommentToUpdate = 9999999L;

        ResultActions deleteActionMetaResult = mvc.perform(
                delete(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId, postCommentToUpdate)
                        .contentType(MediaType.APPLICATION_JSON));

        deleteActionMetaResult.andExpect(status().is(HttpStatus.OK.value()));

        String response = deleteActionMetaResult.andReturn().getResponse().getContentAsString();

        Optional<PostComment> postComment = postCommentsProvider.getPostCommentById(postCommentToUpdate);
        assertThat(!postComment.isPresent());
    }

    @Test
    public void test_delete_non_existing_comment() throws Exception {

        final Long postId = 1L;
        final Long postCommentToUpdate = 19999999L;

        ResultActions deleteActionMetaResult = mvc.perform(
                delete(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId, postCommentToUpdate)
                        .contentType(MediaType.APPLICATION_JSON));

        deleteActionMetaResult.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("code").value("post.comment.not.found"));
    }

}
