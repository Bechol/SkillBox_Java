use todolist;
insert into tbl_role values(1, 'ROLE_USER');
insert into tbl_role values(2, 'ROLE_ADMIN');
insert into tbl_user(id, email, first_name, last_name, username, password) values (1, 'oleg071984@gmail.com', 'Oleg', 'Bech', 'obech', '$2y$10$s421yB7r3t/QBu7LF5pZI.VW0.g.cHMpk2jVakWySF.k70A0ZQF4q');
insert into tbl_users_roles(user_id, role_id) values (1, 2);