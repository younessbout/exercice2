package com.nexio.exercice.integration;

import com.nexio.exercice.Application;
import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.PostCommentsController;
import com.nexio.exercice.posts.comments.adapters.CommentAddRequest;
import com.nexio.exercice.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {Application.class}, secure = false)
public class PostCommentsControllerIT {

    @Autowired
    private PostCommentsController postCommentsController;

    @Autowired
    MockMvc mvc;

    @Test
    public void test_add_new_comment_successful() throws Exception {

        CommentAddRequest request = new CommentAddRequest();

        ResultActions postActionMetaResult = mvc.perform(
                post(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonBytes(request)));

        postActionMetaResult.andExpect(status().is(HttpStatus.CREATED.value()));

        //TODO: Check the Comment is created in the database

        //TODO: Verify the Comment Values

        //TODO: Check the header Location value is correct

    }

}
