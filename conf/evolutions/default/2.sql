-- !Ups

CREATE TABLE items (
        id bigint(20) NOT NULL AUTO_INCREMENT,
        mark_id bigint(20),
        PRIMARY KEY (id)
);

-- !Downs

DROP TABLE items;