-- Tạo cơ sở dữ liệu mới
CREATE DATABASE ringolift;
USE ringolift;

-- 1. Tạo bảng 'user_gender' Enum
CREATE TABLE user_gender (
                             gender ENUM('MALE', 'FEMALE') PRIMARY KEY
);

-- 2. Tạo bảng 'book_genre' Enum
CREATE TABLE book_genre (
                            genre ENUM(
                                'HORROR',
                                'FANTASY',
                                'SCIENCE_FICTION',
                                'ROMANCE',
                                'MYSTERY',
                                'THRILLER',
                                'HISTORICAL',
                                'ADVENTURE',
                                'DYSTOPIAN',
                                'BIOGRAPHY',
                                'AUTOBIOGRAPHY',
                                'SELF_HELP',
                                'NON_FICTION',
                                'POETRY',
                                'CLASSICS',
                                'DRAMA',
                                'YOUNG_ADULT',
                                'CHILDRENS',
                                'COMICS',
                                'GRAPHIC_NOVEL',
                                'LITERARY_FICTION',
                                'CONTEMPORARY'
                                ) PRIMARY KEY
);

-- 3. Tạo bảng 'friend_status' Enum
CREATE TABLE friend_status (
                               status ENUM('PENDING', 'ACCEPTED') PRIMARY KEY
);

-- 4. Tạo bảng 'question_type' Enum
CREATE TABLE question_type (
                               type ENUM('MULTIPLE_CHOICE', 'FILL_IN_THE_BLANK') PRIMARY KEY
);

-- 5. Tạo bảng 'part_of_speech' Enum
CREATE TABLE part_of_speech (
                                pos ENUM(
                                    'NOUN',
                                    'PRONOUN',
                                    'VERB',
                                    'ADJECTIVE',
                                    'ADVERB',
                                    'PREPOSITION',
                                    'CONJUNCTION',
                                    'INTERJECTION',
                                    'DETERMINER',
                                    'ARTICLE'
                                    ) PRIMARY KEY
);

-- 6. Tạo bảng 'word_status' Enum
CREATE TABLE word_status (
                             status ENUM('FORGOT', 'LEARNED') PRIMARY KEY
);

-- 7. Tạo bảng 'notification_type' Enum
CREATE TABLE notification_type (
                                   type ENUM('ACTIVITY', 'ACHIEVEMENT') PRIMARY KEY
);

-- 8. Tạo bảng 'users'
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       date_of_birth DATETIME,
                       gender ENUM('MALE', 'FEMALE'),
                       picture VARCHAR(255),
                       goal_id INT,
                       password VARCHAR(255),
                       is_public BOOLEAN,
                       google_id INT,
                       access_token VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (gender) REFERENCES user_gender(gender)
);

-- 9. Tạo bảng 'goals'
CREATE TABLE goals (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       time_spent INT,
                       lesson_count INT,
                       word_count INT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Cập nhật khóa ngoại cho 'users.goal_id' sau khi tạo bảng 'goals'
ALTER TABLE users
    ADD CONSTRAINT fk_users_goals
        FOREIGN KEY (goal_id) REFERENCES goals(id);

-- 10. Tạo bảng 'books'
CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255),
                       genre ENUM(
                           'HORROR',
                           'FANTASY',
                           'SCIENCE_FICTION',
                           'ROMANCE',
                           'MYSTERY',
                           'THRILLER',
                           'HISTORICAL',
                           'ADVENTURE',
                           'DYSTOPIAN',
                           'BIOGRAPHY',
                           'AUTOBIOGRAPHY',
                           'SELF_HELP',
                           'NON_FICTION',
                           'POETRY',
                           'CLASSICS',
                           'DRAMA',
                           'YOUNG_ADULT',
                           'CHILDRENS',
                           'COMICS',
                           'GRAPHIC_NOVEL',
                           'LITERARY_FICTION',
                           'CONTEMPORARY'
                           ),
                       published_date DATE,
                       isbn VARCHAR(255),
                       num_of_pages INT,
                       publisher VARCHAR(255),
                       description TEXT,
                       cover_image VARCHAR(255),
                       content_url VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (genre) REFERENCES book_genre(genre)
);

-- 11. Tạo bảng 'friends'
CREATE TABLE friends (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         sender_id INT,
                         receiver_id INT,
                         status ENUM('PENDING', 'ACCEPTED'),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (sender_id) REFERENCES users(id),
                         FOREIGN KEY (receiver_id) REFERENCES users(id),
                         FOREIGN KEY (status) REFERENCES friend_status(status)
);

-- 12. Tạo bảng 'questions'
CREATE TABLE questions (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           content TEXT NOT NULL,
                           audio_url VARCHAR(255),
                           hint TEXT,
                           type ENUM('MULTIPLE_CHOICE', 'FILL_IN_THE_BLANK'),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (type) REFERENCES question_type(type)
);

-- 13. Tạo bảng 'answers'
CREATE TABLE answers (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         content VARCHAR(255) NOT NULL,
                         question_id INT,
                         is_correct BOOLEAN,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- 14. Tạo bảng 'words'
CREATE TABLE words (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       word VARCHAR(255) NOT NULL,
                       meaning VARCHAR(255) NOT NULL,
                       topic VARCHAR(255),
                       part_of_speech ENUM(
                           'NOUN',
                           'PRONOUN',
                           'VERB',
                           'ADJECTIVE',
                           'ADVERB',
                           'PREPOSITION',
                           'CONJUNCTION',
                           'INTERJECTION',
                           'DETERMINER',
                           'ARTICLE'
                           ),
                       pronunciation VARCHAR(255),
                       audio_url VARCHAR(255),
                       example_sentence TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (part_of_speech) REFERENCES part_of_speech(pos)
);

-- 15. Tạo bảng 'word_progress'
CREATE TABLE word_progress (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT,
                               word_id INT,
                               status ENUM('FORGOT', 'LEARNED'),
                               note TEXT,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id),
                               FOREIGN KEY (word_id) REFERENCES words(id),
                               FOREIGN KEY (status) REFERENCES word_status(status)
);

-- 16. Tạo bảng 'chapters'
CREATE TABLE chapters (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          cover_image VARCHAR(255),
                          description TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 17. Tạo bảng 'chapter_progress'
CREATE TABLE chapter_progress (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  user_id INT,
                                  chapter_id INT,
                                  unlocked BOOLEAN,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                  FOREIGN KEY (chapter_id) REFERENCES chapters(id)
);

-- 18. Tạo bảng 'lessons'
CREATE TABLE lessons (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         chapter_id INT,
                         description TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (chapter_id) REFERENCES chapters(id)
);

-- 19. Tạo bảng 'lesson_progress'
CREATE TABLE lesson_progress (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT,
                                 lesson_id INT,
                                 correct_count INT,
                                 incorrect_count INT,
                                 time_spent INT,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 FOREIGN KEY (user_id) REFERENCES users(id),
                                 FOREIGN KEY (lesson_id) REFERENCES lessons(id)
);

-- 20. Tạo bảng 'lesson_questions'
CREATE TABLE lesson_questions (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  lesson_id INT,
                                  question_id INT,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  FOREIGN KEY (lesson_id) REFERENCES lessons(id),
                                  FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- 21. Tạo bảng 'mistakes'
CREATE TABLE mistakes (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          lesson_id INT,
                          question_id INT,
                          active BOOLEAN,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (lesson_id) REFERENCES lessons(id),
                          FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- 22. Tạo bảng 'daily_progress'
CREATE TABLE daily_progress (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                user_id INT,
                                time_spent INT,
                                lesson_count INT,
                                word_count INT,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 23. Tạo bảng 'feedback'
CREATE TABLE feedback (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          lesson_id INT,
                          stars INT,
                          comment TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (lesson_id) REFERENCES lessons(id)
);

-- 24. Tạo bảng 'messages'
CREATE TABLE messages (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          sender_id INT,
                          receiver_id INT,
                          message_text TEXT,
                          is_read BOOLEAN,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (sender_id) REFERENCES users(id),
                          FOREIGN KEY (receiver_id) REFERENCES users(id)
);

-- 25. Tạo bảng 'notifications'
CREATE TABLE notifications (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT,
                               type ENUM('ACTIVITY', 'ACHIEVEMENT'),
                               content TEXT,
                               is_read BOOLEAN,
                               is_deleted BOOLEAN,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id),
                               FOREIGN KEY (type) REFERENCES notification_type(type)
);

-- 26. Tạo bảng 'admins'
CREATE TABLE admins (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
