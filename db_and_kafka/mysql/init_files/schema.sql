use ellistrator;

CREATE TABLE if not exists quote 
(
created DATETIME NOT NULL,
updated DATETIME NOT NULL,
id INTEGER AUTO_INCREMENT NOT NULL,
symbol TEXT NOT NULL,
price FLOAT NOT NULL,
initial_quantity INT NOT NULL,
remaining_quantity INT NOT NULL,
trade_action TEXT NOT NULL,
PRIMARY KEY (id)
) COMMENT='Quote table';
