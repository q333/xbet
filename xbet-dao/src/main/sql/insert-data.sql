INSERT INTO xbet.roles (role) VALUES ('admin');
INSERT INTO xbet.roles (role) VALUES ('user');

INSERT INTO xbet.users (login, password, first_name, last_name, email, balance, role_id)
VALUES ('admin', 'Password1', 'Admin', 'Admin', 'admin@mail.ru', 500, 1);
INSERT INTO xbet.users (login, password, first_name, last_name, email, balance, role_id)
VALUES ('user', 'Password2', 'User', 'User', 'user@mail.ru', 100, 2);
INSERT INTO xbet.users (login, password, first_name, last_name, email, balance, role_id)
VALUES ('ivan', 'Password3', 'Ivan', 'Ivan', 'ivan@mail.ru', 0, 2);

INSERT INTO xbet.teams (name) VALUES ('Uruguay');
INSERT INTO xbet.teams (name) VALUES ('Portugal');
INSERT INTO xbet.teams (name) VALUES ('Argentina');
INSERT INTO xbet.teams (name) VALUES ('France');
INSERT INTO xbet.teams (name) VALUES ('Brazil');
INSERT INTO xbet.teams (name) VALUES ('Mexico');
INSERT INTO xbet.teams (name) VALUES ('Belgium');
INSERT INTO xbet.teams (name) VALUES ('Japan');
INSERT INTO xbet.teams (name) VALUES ('Spain');
INSERT INTO xbet.teams (name) VALUES ('Russia');
INSERT INTO xbet.teams (name) VALUES ('Croatia');
INSERT INTO xbet.teams (name) VALUES ('Denmark');
INSERT INTO xbet.teams (name) VALUES ('Switzerland');
INSERT INTO xbet.teams (name) VALUES ('Sweden');
INSERT INTO xbet.teams (name) VALUES ('Colombia');
INSERT INTO xbet.teams (name) VALUES ('England');

INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-06-30', 1, 2, 4.7, 3.2, 1.97, 1.91, 1.39, 1.22);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-06-30', 4, 3, 3.12, 2.8, 1.55, 1.88, 1.23, 1.13);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-01', 9, 10, 2.6, 2.2, 1.9, 1.33, 1.15, 1.67);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-01', 11, 12, 3.44, 2.6, 1.87, 1.96, 1.25, 1.17);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-02', 5, 6, 1.88, 3.1, 3.9, 1.11, 1.27, 1.96);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-02', 7, 8, 1.98, 2.9, 3.7, 1.29, 1.43, 1.82);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-03', 14, 13, 2.65, 2.09, 3.3, 1.25, 1.36, 1.99);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-03', 15, 16, 3.24, 1.98, 2.4, 2.11, 1.66, 1.87);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-06', 1, 4, 4.7, 3.2, 1.97, 1.91, 1.39, 1.22);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-06', 5, 7, 3.3, 2.9, 1.12, 1.86, 1.32, 1.19);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-07', 10, 11, 4, 3.2, 2.15, 1.79, 1.39, 1.28);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-07', 14, 16, 3.1, 2.7, 1.87, 1.62, 1.28, 1.15);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-10', 4, 7, 3.5, 2.6, 1.97, 1.53, 1.18, 1.11);
INSERT INTO xbet.matches (date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`)
VALUES ( '2018-07-11', 11, 16, 1.84, 2.3, 3.57, 1.12, 1.28, 1.21);


INSERT INTO xbet.results (matches_id, result, winner_id, loser_id, winner_goals, loser_goals)
VALUES ( 1, '1', 1, 2, 2, 1);
INSERT INTO xbet.results (matches_id, result, winner_id, loser_id, winner_goals, loser_goals)
VALUES ( 2, '1', 4, 3, 4, 3);
INSERT INTO xbet.results (matches_id, result, winner_id, loser_id, winner_goals, loser_goals)
VALUES ( 3, '1', 5, 6, 2, 0);
INSERT INTO xbet.results (matches_id, result, winner_id, loser_id, winner_goals, loser_goals)
VALUES ( 5, '2', 10, 9, 4, 5);


INSERT INTO xbet.bets (user_id, match_id, bet_result, bet, money, status)
VALUES ( 2, 9, '1', 2.3, 100, 'active');
INSERT INTO xbet.bets (user_id, match_id, bet_result, bet, money, status)
VALUES ( 2, 10, '2', 1.31, 100, 'active');
INSERT INTO xbet.bets (user_id, match_id, bet_result, bet, money, status)
VALUES ( 2, 5, 'X', '1.87', 100, 'won');
INSERT INTO xbet.bets (user_id, match_id, bet_result, bet, money, status)
VALUES ( 2, 6, '12', 3.1, 100, 'lost');





