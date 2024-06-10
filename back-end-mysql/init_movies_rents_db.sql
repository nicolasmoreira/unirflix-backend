CREATE DATABASE IF NOT EXISTS movies_rents_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE movies_rents_db;

CREATE TABLE IF NOT EXISTS rents
(
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    movie_id      INT          NOT NULL,
    rented_on     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    returned_on   TIMESTAMP    NULL,
    customer_name VARCHAR(255) NOT NULL
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;