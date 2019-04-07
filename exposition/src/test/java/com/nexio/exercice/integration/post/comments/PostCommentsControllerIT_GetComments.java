package com.nexio.exercice.integration.post.comments;

import com.nexio.exercice.Application;
import com.nexio.exercice.constants.ResourcePaths;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("test")
@Transactional
public class PostCommentsControllerIT_GetComments {

    @Autowired
    private PostCommentsProvider postCommentsProvider;

    @Autowired
    MockMvc mvc;

    @Test
    public void test_comments_for_post_successful() throws Exception {

        final Long postId = 1L;
        final int numberOfCommentsForPostWithId_1 = 2;

        ResultActions getActionMetaResult = mvc.perform(
                get(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS, postId)
                        .contentType(MediaType.APPLICATION_JSON));

        getActionMetaResult.andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$", hasSize(numberOfCommentsForPostWithId_1)));

    }

}
