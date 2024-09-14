CREATE TABLE file_upload(
                            id BIGINT auto_increment,
                            name VARCHAR(100) NOT NULL,
                            md5 VARCHAR(32),
                            path VARCHAR(100) NOT NULL,
                            upload_time datetime(3) NOT NULL,
                            PRIMARY key (id)
);