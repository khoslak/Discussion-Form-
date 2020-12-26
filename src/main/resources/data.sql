INSERT INTO Role(rolename) VALUES('ROLE_ADMIN');
INSERT INTO Role(rolename) VALUES('ROLE_USER');


INSERT INTO User(id,username,encrypted_Password,firstname,lastname,emp_Id,enabled) VALUES(1,'khoslak','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy','Kunal','Khosla',101,1);
INSERT INTO User(id,username,encrypted_Password,firstname,lastname,emp_Id,enabled) VALUES(2,'Frankin','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy','Frank','Liston',102,1);
INSERT INTO Threadiscussion(id,content,create_date,subject,user_id) VALUES (1,'This is  a problem that everyone has faced at least once in their life and is one of the most common problems we come across. The causes of this problem can range from something as simple as caps lock being left on to something a little more serious such as the users account being suspended.','2020-04-01','I can’t log in.',1);
INSERT INTO Comment(id,create_date,post_comment,username) VALUES (1,'2020-04-01','Try updating it','Frankin');
INSERT INTO User_Roles(users_id, roles_id) VALUES(1, 2);
INSERT INTO User_Roles(users_id, roles_id) VALUES(2, 2);

INSERT INTO User_Threads(user_id,threads_id) VALUES (1,1);
INSERT INTO  THREADISCUSSION_COMMENTS(THREADISCUSSION_ID,COMMENTS_ID) VALUES(1,1);