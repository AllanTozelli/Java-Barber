CREATE TABLE SCHEDULES (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           start_at DATETIME NOT NULL,
                           end_at DATETIME NOT NULL,
                           client_id BIGINT,
                           CONSTRAINT UK_EMAIL UNIQUE (start_at, end_at),
                           CONSTRAINT FK_CLIENT FOREIGN KEY (client_id) REFERENCES CLIENT(id)
);