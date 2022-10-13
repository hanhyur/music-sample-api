DROP TABLE IF EXISTS `soundtrack`;
DROP TABLE IF EXISTS `album`;
DROP TABLE IF EXISTS `artist`;

CREATE TABLE `artist` (
                          `id`	    BIGINT	    NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `name`	    VARCHAR(50)	NULL,
                          `birth`	        DATE	    NULL,
                          `agency`	        VARCHAR(50)	NULL,
                          `nationality`	    VARCHAR(50)	NULL,
                          `introduction`	TEXT	    NULL,
                          `registered_date`	DATETIME	NULL
);

CREATE TABLE `album` (
                         `id`	        BIGINT	        NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `name`	    VARCHAR(50)     NULL,
                         `title`	    VARCHAR(100)	NULL,
                         `release_date`	    DATE	        NULL,
                         `genre`	        VARCHAR(100)	NULL,
                         `description`      TEXT            NULL,
                         `registered_date`	DATETIME	    NULL
);

CREATE TABLE `soundtrack` (
                         `id`	        BIGINT	        NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `order`        INT             NOT NULL,
                         `title`        VARCHAR(100)    NULL,
                         `play_time`        BIGINT          NULL,
                         `exposue`          boolean         NULL,
                         `album_id`     BIGINT          NULL
);