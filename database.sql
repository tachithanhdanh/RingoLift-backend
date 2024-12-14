DROP DATABASE IF EXISTS ringolift;

-- Tạo cơ sở dữ liệu mới
CREATE DATABASE ringolift;
USE ringolift;

-- 1. Tạo bảng 'user_gender'
CREATE TABLE user_gender
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    gender_type VARCHAR(50) NOT NULL UNIQUE,
    created_at  DATETIME,
    updated_at  DATETIME
);

-- 1.1. Dữ liệu cho bảng 'user_gender'
INSERT INTO user_gender (gender_type, created_at, updated_at)
VALUES ('MALE', NOW(), NOW()),
       ('FEMALE', NOW(), NOW()),
       ('OTHER', NOW(), NOW());
-- 2. Tạo bảng 'book_genre'
CREATE TABLE book_genre
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    genre_type VARCHAR(50) NOT NULL UNIQUE,
    created_at DATETIME,
    updated_at DATETIME
);

-- 2.1. Dữ liệu cho bảng 'book_genre'
INSERT INTO book_genre (genre_type, created_at, updated_at)
VALUES ('HORROR', NOW(), NOW()),
       ('FANTASY', NOW(), NOW()),
       ('SCIENCE_FICTION', NOW(), NOW()),
       ('ROMANCE', NOW(), NOW()),
       ('MYSTERY', NOW(), NOW()),
       ('THRILLER', NOW(), NOW()),
       ('HISTORICAL', NOW(), NOW()),
       ('ADVENTURE', NOW(), NOW()),
       ('DYSTOPIAN', NOW(), NOW()),
       ('BIOGRAPHY', NOW(), NOW()),
       ('AUTOBIOGRAPHY', NOW(), NOW()),
       ('SELF_HELP', NOW(), NOW()),
       ('NON_FICTION', NOW(), NOW()),
       ('POETRY', NOW(), NOW()),
       ('CLASSICS', NOW(), NOW()),
       ('DRAMA', NOW(), NOW()),
       ('YOUNG_ADULT', NOW(), NOW()),
       ('COMICS', NOW(), NOW()),
       ('GRAPHIC_NOVEL', NOW(), NOW()),
       ('LITERARY_FICTION', NOW(), NOW()),
       ('CONTEMPORARY', NOW(), NOW());

-- 3. Tạo bảng 'friend_status'
CREATE TABLE friend_status
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    status_type VARCHAR(50) NOT NULL UNIQUE,
    created_at  DATETIME,
    updated_at  DATETIME
);

-- 3.1. Dữ liệu cho bảng 'friend_status'
INSERT INTO friend_status (status_type, created_at, updated_at)
VALUES ('PENDING', NOW(), NOW()),
       ('REJECTED', NOW(), NOW()),
       ('BLOCKED', NOW(), NOW()),
       ('ACCEPTED', NOW(), NOW());

-- 4. Tạo bảng 'question_type'
CREATE TABLE question_type
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    ques_type  VARCHAR(50) NOT NULL UNIQUE,
    created_at DATETIME,
    updated_at DATETIME
);


-- 4.1. Dữ liệu cho bảng 'question_type'
INSERT INTO question_type (ques_type, created_at, updated_at)
VALUES ('MULTIPLE_CHOICE', NOW(), NOW()),
       ('FILL_IN_THE_BLANK', NOW(), NOW());

-- 5. Tạo bảng 'part_of_speech'
CREATE TABLE part_of_speech
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    pos_type   VARCHAR(50) NOT NULL UNIQUE,
    created_at DATETIME,
    updated_at DATETIME
);

-- 5.1. Dữ liệu cho bảng 'part_of_speech'
INSERT INTO part_of_speech (pos_type, created_at, updated_at)
VALUES ('NOUN', NOW(), NOW()),
       ('PRONOUN', NOW(), NOW()),
       ('VERB', NOW(), NOW()),
       ('ADJECTIVE', NOW(), NOW()),
       ('ADVERB', NOW(), NOW()),
       ('PREPOSITION', NOW(), NOW()),
       ('CONJUNCTION', NOW(), NOW()),
       ('INTERJECTION', NOW(), NOW()),
       ('DETERMINER', NOW(), NOW()),
       ('ARTICLE', NOW(), NOW());

-- 6. Tạo bảng 'word_status'
CREATE TABLE word_status
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    status_type VARCHAR(50) NOT NULL UNIQUE,
    created_at  DATETIME,
    updated_at  DATETIME
);

-- 6.1. Dữ liệu cho bảng 'word_status'
INSERT INTO word_status (status_type, created_at, updated_at)
VALUES ('FORGOT', NOW(), NOW()),
       ('LEARNED', NOW(), NOW());

-- 7. Tạo bảng 'notification_type'
CREATE TABLE notification_type
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    noti_type  VARCHAR(50) NOT NULL UNIQUE,
    created_at DATETIME,
    updated_at DATETIME
);

-- 7.1. Dữ liệu cho bảng 'notification_type'
INSERT INTO notification_type (noti_type, created_at, updated_at)
VALUES ('ACTIVITY', NOW(), NOW()),
       ('ACHIEVEMENT', NOW(), NOW());

-- 8. Tạo bảng 'users'
CREATE TABLE users
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    date_of_birth DATETIME,
    gender_id     INT,
    picture       VARCHAR(255),
    goal_id       INT,
    password      VARCHAR(255),
    is_public     BOOLEAN,
    google_id     VARCHAR(255),
    access_token  VARCHAR(255),
    created_at    DATETIME,
    updated_at    DATETIME,
    FOREIGN KEY (gender_id) REFERENCES user_gender (id)
);

ALTER TABLE users
    ADD COLUMN role_id INT;

-- Create table roles
-- This table is used for storing user roles
CREATE TABLE roles
(
    id   INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);
ALTER TABLE users
    ADD FOREIGN KEY (role_id) REFERENCES roles (id);

INSERT INTO roles (id, name)
VALUES (2, 'ADMIN'),
       (1, 'USER');

-- Insert 4 admin users into 'users' table
INSERT INTO users (username, email, first_name, last_name, date_of_birth, gender_id, picture, goal_id, password, is_public, google_id, access_token, created_at, updated_at, role_id)
VALUES
    ('tachithanhdanh', 'tachithanhdanh@gmail.com', 'Chi Thanh Danh', 'Ta', '1990-05-01', 1, 'https://fakeurl.com/pic1.jpg', NULL, 'password1', TRUE, NULL, NULL, NOW(), NOW(), 2),
    ('vandatcqh', 'ngodat1214@gmail.com', 'Van Dat', 'Ngo', '1992-08-12', 1, 'https://fakeurl.com/pic2.jpg', NULL, 'password2', TRUE, NULL, NULL, NOW(), NOW(), 2),
    ('allforest01', 'allforest01@gmail.com', 'Tuan Kiet', 'Mai Van', '1985-10-25', 1, 'https://fakeurl.com/pic3.jpg', NULL, 'password3', TRUE, NULL, NULL, NOW(), NOW(), 2),
    ('NguyenHung1207', 'nguyenhung.9a5.nbk@gmail.com', 'Hung', 'Nguyen', '1988-01-15', 1, 'https://fakeurl.com/pic4.jpg', NULL, 'password4', TRUE, NULL, NULL, NOW(), NOW(), 2);


-- 9. Tạo bảng 'goals'
CREATE TABLE goals
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    time_spent   INT,
    lesson_count INT,
    word_count   INT,
    created_at   DATETIME,
    updated_at   DATETIME
);

-- 10. Tạo bảng 'books'
CREATE TABLE books
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    author         VARCHAR(255),
    genre_id       INT,
    published_date DATE,
    isbn           VARCHAR(255),
    num_of_pages   INT,
    publisher      VARCHAR(255),
    description    TEXT,
    cover_image    VARCHAR(255),
    content_url    VARCHAR(255),
    created_at     DATETIME,
    updated_at     DATETIME,
    FOREIGN KEY (genre_id) REFERENCES book_genre (id)
);

-- 11. Tạo bảng 'friends'
CREATE TABLE friends
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    sender_id   INT,
    receiver_id INT,
    status_id   INT,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (sender_id) REFERENCES users (id),
    FOREIGN KEY (receiver_id) REFERENCES users (id),
    FOREIGN KEY (status_id) REFERENCES friend_status (id)
);

-- 12. Tạo bảng 'questions'
CREATE TABLE questions
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    content    TEXT NOT NULL,
    audio_url  VARCHAR(255),
    hint       TEXT,
    type_id    INT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (type_id) REFERENCES question_type (id)
);

-- 13. Tạo bảng 'answers'
CREATE TABLE answers
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    content     VARCHAR(255) NOT NULL,
    question_id INT,
    is_correct  BOOLEAN,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

-- 14. Tạo bảng 'words'
CREATE TABLE words
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    word              VARCHAR(255) NOT NULL,
    meaning           VARCHAR(255) NOT NULL,
    topic             VARCHAR(255),
    part_of_speech_id INT,
    pronunciation     VARCHAR(255),
    audio_url         VARCHAR(255),
    example_sentence  TEXT,
    created_at        DATETIME,
    updated_at        DATETIME,
    FOREIGN KEY (part_of_speech_id) REFERENCES part_of_speech (id)
);

-- 15. Tạo bảng 'word_progress'
CREATE TABLE word_progress
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT,
    word_id    INT,
    status_id  INT,
    note       TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (word_id) REFERENCES words (id),
    FOREIGN KEY (status_id) REFERENCES word_status (id)
);

-- 16. Tạo bảng 'chapters'
CREATE TABLE chapters
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    cover_image VARCHAR(255),
    description TEXT,
    created_at  DATETIME,
    updated_at  DATETIME
);

-- 17. Tạo bảng 'chapter_progress'
CREATE TABLE chapter_progress
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT,
    chapter_id INT,
    unlocked   BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (chapter_id) REFERENCES chapters (id)
);

-- 18. Tạo bảng 'lessons'
CREATE TABLE lessons
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    chapter_id  INT,
    description TEXT,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (chapter_id) REFERENCES chapters (id)
);

-- 19. Tạo bảng 'lesson_progress'
CREATE TABLE lesson_progress
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT,
    lesson_id       INT,
    correct_count   INT,
    incorrect_count INT,
    time_spent      INT,
    created_at      DATETIME,
    updated_at      DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (lesson_id) REFERENCES lessons (id)
);

-- 20. Tạo bảng 'lesson_questions'
CREATE TABLE lesson_questions
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    lesson_id   INT,
    question_id INT,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (lesson_id) REFERENCES lessons (id),
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

-- 21. Tạo bảng 'mistakes'
CREATE TABLE mistakes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    lesson_id   INT,
    question_id INT,
    active      BOOLEAN,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (lesson_id) REFERENCES lessons (id),
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

-- 22. Tạo bảng 'daily_progress'
CREATE TABLE daily_progress
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT,
    time_spent   INT,
    lesson_count INT,
    word_count   INT,
    created_at   DATETIME,
    updated_at   DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- 23. Tạo bảng 'feedback'
CREATE TABLE feedback
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT,
    lesson_id  INT,
    stars      INT,
    comment    TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (lesson_id) REFERENCES lessons (id)
);

-- 24. Tạo bảng 'messages'
CREATE TABLE messages
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    sender_id    INT,
    receiver_id  INT,
    message_text TEXT,
    is_read      BOOLEAN,
    created_at   DATETIME,
    updated_at   DATETIME,
    FOREIGN KEY (sender_id) REFERENCES users (id),
    FOREIGN KEY (receiver_id) REFERENCES users (id)
);

-- 25. Tạo bảng 'notifications'
CREATE TABLE notifications
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT,
    type_id    INT,
    content    TEXT,
    is_read    BOOLEAN,
    is_deleted BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (type_id) REFERENCES notification_type (id)
);

-- 26. Tạo bảng 'admins'
CREATE TABLE admins
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
);
