Select * from oauth_client_details;

INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('microcredential', '{bcrypt}$2a$10$LH6THUrWinpCmGj8XQQxdeAk9aFhc4.0qCT9MzmJXCRUTID5BHLRW', 'http:/*localhost:8080/code', 'READ,WRITE', '3600', '10000', 'order,checkout,user,product', 'authorization_code,password,refresh_token,implicit', '{}');

Select * from PERMISSION;
INSERT INTO PERMISSION (NAME) VALUES
 ('create_user'),       /* 1 */
 ('read_user'),         /* 2 */
 ('update_user'),       /* 3 */
 ('delert_user'),       /* 4 */
 ('create_product'),    /* 5 */
 ('read_product'),      /* 6 */
 ('update_product'),    /* 7 */
 ('delete_product'),    /* 8 */
 ('create_order'),      /* 9 */
 ('read_order'),        /* 10 */
 ('update_order'),      /* 11 */
 ('delete_order'),      /* 12 */
 ('create_checkout'),   /* 13 */
 ('delete_checkout');   /* 14 */

Select * from role;

 INSERT INTO role (NAME) VALUES
		('ROLE_admin'),('ROLE_user');

Select * from PERMISSION_ROLE;

 INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
     (1,1),
     (2,1),
     (3,1),
     (4,1),
     (5,1),
     (6,1),
     (7,1),
     (8,1),
     (9,1),
     (10,1),
     (11,1),
     (12,1),
     (13,1),
     (14,1),
     (2,2),
     (3,2),
     (6,2),
     (9,2),
     (10,2),
     (11,2),
     (12,2),
     (13,2),
     (14,2);

     Select * from user;
insert into user (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES
('1', 'admin','{bcrypt}$2a$10$McXSh.5orFNmDWH2wf0W3eWzA3utfIQVb.8qHMiDoNf3EYNUNGSgK', 'admin@microcredential.com', 1, 1, 1, 1);

insert into  user (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES
('2', 'sarath', '{bcrypt}$2a$10$K9vM5u0lTk8wsAhQ9stw2OOphQtLHy.i8mKQfC3BXfZ/5m2PMUv6.','sarath.r93@gmail.com', 1, 1, 1, 1);

Select * from ROLE_USER;
INSERT INTO ROLE_USER (ROLE_ID, USER_ID) VALUES
    (1, 1),
    (2, 2);