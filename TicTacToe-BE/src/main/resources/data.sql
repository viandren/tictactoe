DROP TABLE IF EXISTS board;
CREATE TABLE board(
  id INT AUTO_INCREMENT PRIMARY KEY,
  userUID VARCHAR(200),
  boardData VARCHAR(1000),
  nextPlayer VARCHAR(1)
);

DROP TABLE IF EXISTS sys_param;
CREATE TABLE sys_param(
  id INT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(200),
  value VARCHAR(200)
);

DROP TABLE IF EXISTS scores;
CREATE TABLE scores(
  id INT AUTO_INCREMENT PRIMARY KEY,
  playerName VARCHAR(200),
  opponentName VARCHAR(200),
  playerScore SMALLINT,
  opponentScore SMALLINT,
  draw SMALLINT
);


INSERT INTO sys_param VALUES(1, 'healthcheck', 'OK');