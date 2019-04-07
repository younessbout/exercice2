CREATE TABLE POST_COMMENT (
  id            number         not null,
  post_id       number         not null,
  author        number         not null,
  body          varchar2(4096) not null,
  creation_date timestamp      not null,
  update_date   timestamp,
  CONSTRAINT pk_User PRIMARY KEY (id)
);

CREATE SEQUENCE POST_COMMENT_SEQ
  MINVALUE 1
  MAXVALUE 999999999999
  START WITH 1
  INCREMENT BY 1;