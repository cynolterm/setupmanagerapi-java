insert into cars(category, brand, model) values ('ROAD', 'Brand 1', 'Model 1');
insert into cars(category, brand, model) values ('GT4', 'Brand 1', 'Model 2');
insert into cars(category, brand, model) values ('GT3', 'Brand 2', 'Model 1');

insert into users(username, email, description, nationality, name) values ('username1', 'username1@gmail.com', 'hello world', 'HU', 'User1');
insert into users(username, email, description, nationality, name) values ('username2', 'username2@gmail.com', 'hello world', 'HU', 'User2');
insert into users(username, email, description, nationality, name) values ('username3', 'username3@gmail.com', 'hello world', 'HU', 'User3');

insert into tracks(length, name) values (4.381, 'Hungaroring');
insert into tracks(length, name) values (2.233, 'Track 2');
insert into tracks(length, name) values (3.423, 'Track 3');

insert into teams (owner_id, name, description) values(1, 'Test Racing', 'Valid description');

insert into setups(car_id, track_id, team_id) values (1, 1, 1);
insert into setups(car_id, track_id, team_id) values (2, 2, 1);

insert into setup_variants(car_setup, version, setup_id) values ('{"hello":"world"}', 1, 1);
insert into setup_variants(car_setup, version, setup_id) values ('{"hello":"worldsss"}', 2, 1);
insert into setup_variants(car_setup, version, setup_id) values ('{"hello":"world"}', 1, 2);