package com.nexio.exercice.integration;

import com.jayway.jsonpath.JsonPath;
import com.nexio.exercice.Application;
import com.nexio.exercice.constants.ResourcePaths;
import com.nexio.exercice.posts.comments.PostComment;
import com.nexio.exercice.posts.comments.PostCommentsProvider;
import com.nexio.exercice.posts.comments.adapters.PostCommentAddRequest;
import com.nexio.exercice.utils.TestUtils;
import com.nexio.exercice.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static com.nexio.exercice.constants.ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS_ONE_COMMENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
public class PostCommentsControllerIT {

    @Autowired
    private PostCommentsProvider postCommentsProvider;

    @Autowired
    MockMvc mvc;

    @Test
    public void test_add_new_comment_successful() throws Exception {

        final Long postId = 1L;

        PostCommentAddRequest request = new PostCommentAddRequest();
        request.setBody("Test Comment");

        ResultActions postActionMetaResult = mvc.perform(
                post(ResourcePaths.POSTS.COMMENTS.ENDPOINT_API_POST_COMMENTS, postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonBytes(request)));

        postActionMetaResult.andExpect(status().is(HttpStatus.CREATED.value()));

        String response = postActionMetaResult.andReturn().getResponse().getContentAsString();
        
        //Check the Comment is created in the database
        Long commentId = Long.valueOf(JsonPath.parse(response).read("$.id").toString());
        Optional<PostComment> postComment = postCommentsProvider.getPostCommentById(commentId);
        assertThat(postComment.isPresent());

        //Verify the Comment Values
        assertThat(postComment.get().getBody()).isEqualTo(request.getBody());
        assertThat(postComment.get().getCreationDate()).isNotNull();

        //Check the header Location value is correct
        postActionMetaResult.andExpect(header().string("Location",
                Utils.constructUrlWithParams(ENDPOINT_API_POST_COMMENTS_ONE_COMMENT, postId.toString(), postComment.get().getId().toString())));
    }

}
