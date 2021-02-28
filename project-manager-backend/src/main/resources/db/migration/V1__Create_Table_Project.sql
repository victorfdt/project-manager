-- MySQL
CREATE TABLE IF NOT EXISTS project
(
    id          BIGINT(19) NOT NULL AUTO_INCREMENT,
    created_at  TIMESTAMP,
    description VARCHAR(255),
    end_date    DATE,
    identifier  VARCHAR(5) UNIQUE,
    name        VARCHAR(255),
    start_date  DATE,
    updated_at  TIMESTAMP,
    PRIMARY KEY (id)
);

-- H2
-- CREATE TABLE IF NOT EXISTS `project`
-- (
--     `ID`          BIGINT(19) NOT NULL AUTO_INCREMENT,
--     `CREATED_AT`  TIMESTAMP,
--     `DESCRIPTION` VARCHAR(255),
--     `END_DATE`    DATE(10),
--     `IDENTIFIER`  VARCHAR(5) UNIQUE,
--     `NAME`        VARCHAR(255),
--     `START_DATE`  DATE(10),
--     `UPDATED_AT`  TIMESTAMP,
--     PRIMARY KEY (ID)
-- );


select * from project;