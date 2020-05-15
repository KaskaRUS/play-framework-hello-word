-- !Ups

CREATE TABLE models (
        id bigint(20) NOT NULL AUTO_INCREMENT,
        name varchar(255) NOT NULL,
        production_start_date DATE NOT NULL,
        production_finish_date DATE,
        PRIMARY KEY (id)
);

-- !Downs

DROP TABLE models;