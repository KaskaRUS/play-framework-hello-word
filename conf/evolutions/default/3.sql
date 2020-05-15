-- !Ups

CREATE TABLE items(
                      id              bigint(20) NOT NULL AUTO_INCREMENT,
                      mark_id         bigint(20),
                      model_id        bigint(20),
                      production_date DATE,
                      cost            real,
                      mileage         int,
                      PRIMARY KEY (id),
                      FOREIGN KEY (mark_id) REFERENCES marks (id) ON DELETE SET NULL,
                      FOREIGN KEY (model_id) REFERENCES models (id) ON DELETE SET NULL
);

-- !Downs

DROP TABLE items;