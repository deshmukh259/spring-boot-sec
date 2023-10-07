INSERT into users(username,password,email,enabled) values ('purushottam','purushottam','abcd@gmail.com',true);
INSERT into users(username,password,email,enabled) values ('user','secret','user@gmail.com',true);
INSERT into users(username,password,email,enabled) values ('admin','admin','admin@gmail.com',true);
INSERT into authorities values ('user','ROLE_USER');
INSERT into authorities values ('admin','ROLE_ADMIN');
INSERT into authorities values ('purushottam','ROLE_ADMIN');

