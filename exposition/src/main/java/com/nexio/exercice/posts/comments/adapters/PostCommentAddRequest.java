package com.nexio.exercice.posts.comments.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentAddRequest {

    @JsonProperty("body")
    @NotNull(message = "body of the comment canno't be null")
    private String body;
}
