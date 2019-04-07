package com.nexio.exercice.constants;

public class ResourcePaths {


    private static final String BASE_URL = "/exercice/api/v1";

    public static final class POSTS {

        public static final String ENDPOINT_API_POSTS = BASE_URL + "/posts";

        public static final String ENDPOINT_API_POSTS_ONE_POST = ENDPOINT_API_POSTS + "/{post_id}";

        public static final class COMMENTS {
            public static final String ENDPOINT_API_POST_COMMENTS = ENDPOINT_API_POSTS_ONE_POST + "/comments";

            public static final String ENDPOINT_API_POST_COMMENTS_ONE_COMMENT = ENDPOINT_API_POST_COMMENTS + "/{comment_id}";
        }
    }
}
