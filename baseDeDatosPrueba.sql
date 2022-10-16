INSERT INTO `db`.`role` (`description`, `name`) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO `db`.`role`  (`description`, `name`) VALUES ('ROLE_USER', 'USER');
INSERT INTO `db`.`role`  (`description`, `name`) VALUES ('ROLE_SUPERVISOR', 'SUPERVISOR');

INSERT INTO `db`.`user` (`email`, `first_name`, `last_name`, `password`, `username`) VALUES ('admin@admin.com', 'admin', 'admin', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'admin');
INSERT INTO `db`.`user` (`email`, `first_name`, `last_name`, `password`, `username`) VALUES ('alfredo@admin.com', 'Alfredo', 'Guerrero', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'alfredoa29');
 
INSERT INTO `db`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `db`.`user_roles` (`user_id`, `role_id`) VALUES ('2', '2');