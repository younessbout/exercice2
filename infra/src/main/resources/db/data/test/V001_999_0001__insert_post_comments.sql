insert into Post_Comment (id, post_id, author, body, creation_date) values (POST_COMMENT_SEQ.nextval, 1, 1, 'Test Comment 1', CURRENT_TIMESTAMP);
insert into Post_Comment (id, post_id, author, body, creation_date) values (POST_COMMENT_SEQ.nextval, 1, 1, 'Test Comment 2', CURRENT_TIMESTAMP);

insert into Post_Comment (id, post_id, author, body, creation_date) values (9999999, 1, 1, 'Comment to Update', CURRENT_TIMESTAMP);