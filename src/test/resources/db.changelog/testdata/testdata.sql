insert into author (name) values ('testA');
insert into genre (name) values ('testG');
insert into book (name, id_author, id_genre) VALUES ('testB', 1, 1);
insert into comment (text, id_book) values ('testC', 1);