alter table users ADD COLUMN role varchar(100);
alter table users ADD COLUMN password varchar(300);
--username: username1, password: admin
update users set role='ROLE_ADMIN', password='$2a$10$VRJh6RWQ7EcQw2G9GMSKY.yhaw7bReoq5fJbBiomk1Xaosa2OgJni' where username = 'username1';
--username: username2, password: user
update users set role='ROLE_USER', password='$2a$10$mSmYDoB8jSrdrTvEK9vTIesiV53q5.B/Ln2IWweHmqRCC.7Rw.ZhG' where username = 'username2';
--username: username3, password: user
update users set role='ROLE_USER', password='$2a$10$mSmYDoB8jSrdrTvEK9vTIesiV53q5.B/Ln2IWweHmqRCC.7Rw.ZhG' where username = 'username3';