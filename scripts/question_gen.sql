use ringolift;
-- Insert 50 multiple-choice questions into the `questions` table
INSERT INTO `questions` (`id`, `content`, `audio_url`, `hint`, `type_id`, `created_at`, `updated_at`, `correct_answer`)
VALUES
(11, 'What is the capital of France?', NULL, 'Paris', 1, NOW(), NOW(), 'Paris'),
(12, 'Which planet is known as the Red Planet?', NULL, 'Mars', 1, NOW(), NOW(), 'Mars'),
(13, 'What is 5 + 3?', NULL, '8', 1, NOW(), NOW(), '8'),
(14, 'Which ocean is the largest?', NULL, 'Pacific', 1, NOW(), NOW(), 'Pacific'),
(15, 'What is the chemical symbol for water?', NULL, 'H2O', 1, NOW(), NOW(), 'H2O'),
(16, 'Choose the correct form: She _____ to the market yesterday.', NULL, 'went', 1, NOW(), NOW(), 'went'),
(17, 'Fill in the blank: The sun _____ in the east.', NULL, 'rises', 1, NOW(), NOW(), 'rises'),
(18, 'Select the synonym for "happy".', NULL, 'joyful', 1, NOW(), NOW(), 'joyful'),
(19, 'What is the opposite of "cold"?', NULL, 'hot', 1, NOW(), NOW(), 'hot'),
(20, 'Choose the correct word: He is taller _____ his brother.', NULL, 'than', 1, NOW(), NOW(), 'than'),
(21, 'What is the plural of "mouse"?', NULL, 'mice', 1, NOW(), NOW(), 'mice'),
(22, 'Which of the following is a fruit?', NULL, 'apple', 1, NOW(), NOW(), 'apple'),
(23, 'Select the correct spelling.', NULL, 'necessary', 1, NOW(), NOW(), 'necessary'),
(24, 'Which word is a noun?', NULL, 'book', 1, NOW(), NOW(), 'book'),
(25, 'What is 10 divided by 2?', NULL, '5', 1, NOW(), NOW(), '5'),
(26, 'Choose the correct verb: He _____ playing football.', NULL, 'enjoys', 1, NOW(), NOW(), 'enjoys'),
(27, 'Fill in the blank: A dog has four _____.', NULL, 'legs', 1, NOW(), NOW(), 'legs'),
(28, 'Which is an adjective?', NULL, 'beautiful', 1, NOW(), NOW(), 'beautiful'),
(29, 'What is the past tense of "run"?', NULL, 'ran', 1, NOW(), NOW(), 'ran'),
(30, 'Select the correct article: _____ apple is red.', NULL, 'The', 1, NOW(), NOW(), 'The'),
(31, 'What is the synonym of "fast"?', NULL, 'quick', 1, NOW(), NOW(), 'quick'),
(32, 'Fill in the blank: She _____ a new dress for the party.', NULL, 'bought', 1, NOW(), NOW(), 'bought'),
(33, 'Choose the correct option: A _____ of birds.', NULL, 'flock', 1, NOW(), NOW(), 'flock'),
(34, 'What is the opposite of "up"?', NULL, 'down', 1, NOW(), NOW(), 'down'),
(35, 'Fill in the blank: He _____ a doctor.', NULL, 'is', 1, NOW(), NOW(), 'is'),
(36, 'Choose the correct form: They _____ to the park every weekend.', NULL, 'go', 1, NOW(), NOW(), 'go'),
(37, 'What is the synonym of "big"?', NULL, 'large', 1, NOW(), NOW(), 'large'),
(38, 'Select the correct spelling.', NULL, 'receive', 1, NOW(), NOW(), 'receive'),
(39, 'What is the past tense of "eat"?', NULL, 'ate', 1, NOW(), NOW(), 'ate'),
(40, 'Choose the correct answer: The _____ are in the sky.', NULL, 'stars', 1, NOW(), NOW(), 'stars'),
(41, 'What is the opposite of "day"?', NULL, 'night', 1, NOW(), NOW(), 'night'),
(42, 'Fill in the blank: She _____ her homework already.', NULL, 'has done', 1, NOW(), NOW(), 'has done'),
(43, 'What is the plural of "child"?', NULL, 'children', 1, NOW(), NOW(), 'children'),
(44, 'Select the correct answer: The sun is very _____.', NULL, 'bright', 1, NOW(), NOW(), 'bright'),
(45, 'Choose the correct preposition: The book is _____ the table.', NULL, 'on', 1, NOW(), NOW(), 'on'),
(46, 'Fill in the blank: There are _____ apples in the basket.', NULL, 'five', 1, NOW(), NOW(), 'five'),
(47, 'What is the opposite of "small"?', NULL, 'big', 1, NOW(), NOW(), 'big'),
(48, 'Choose the correct option: He is _____ to school now.', NULL, 'going', 1, NOW(), NOW(), 'going'),
(49, 'Fill in the blank: She _____ very happy yesterday.', NULL, 'was', 1, NOW(), NOW(), 'was'),
(50, 'What is the synonym of "smart"?', NULL, 'intelligent', 1, NOW(), NOW(), 'intelligent'),
(51, 'Select the correct answer: The Earth revolves around the _____.', NULL, 'sun', 1, NOW(), NOW(), 'sun'),
(52, 'Fill in the blank: They _____ a new house last year.', NULL, 'built', 1, NOW(), NOW(), 'built'),
(53, 'What is the opposite of "short"?', NULL, 'tall', 1, NOW(), NOW(), 'tall'),
(54, 'Choose the correct spelling.', NULL, 'beginning', 1, NOW(), NOW(), 'beginning'),
(55, 'What is the plural of "fish"?', NULL, 'fish', 1, NOW(), NOW(), 'fish'),
(56, 'Fill in the blank: I _____ to the cinema yesterday.', NULL, 'went', 1, NOW(), NOW(), 'went'),
(57, 'Choose the correct word: He is as brave _____ a lion.', NULL, 'as', 1, NOW(), NOW(), 'as'),
(58, 'What is the synonym of "happy"?', NULL, 'content', 1, NOW(), NOW(), 'content'),
(59, 'Select the correct answer: The _____ is a domestic animal.', NULL, 'cat', 1, NOW(), NOW(), 'cat'),
(60, 'Fill in the blank: The baby is _____ in the cradle.', NULL, 'sleeping', 1, NOW(), NOW(), 'sleeping');

-- Distribute questions among 10 lessons via `lesson_questions`
INSERT INTO `lesson_questions` (`id`, `lesson_id`, `question_id`, `created_at`, `updated_at`)
VALUES
(11, 2, 11, NOW(), NOW()),
(12, 2, 12, NOW(), NOW()),
(13, 2, 13, NOW(), NOW()),
(14, 2, 14, NOW(), NOW()),
(15, 2, 15, NOW(), NOW()),
(16, 3, 16, NOW(), NOW()),
(17, 3, 17, NOW(), NOW()),
(18, 3, 18, NOW(), NOW()),
(19, 3, 19, NOW(), NOW()),
(20, 3, 20, NOW(), NOW()),
(21, 4, 21, NOW(), NOW()),
(22, 4, 22, NOW(), NOW()),
(23, 4, 23, NOW(), NOW()),
(24, 4, 24, NOW(), NOW()),
(25, 4, 25, NOW(), NOW()),
(26, 5, 26, NOW(), NOW()),
(27, 5, 27, NOW(), NOW()),
(28, 5, 28, NOW(), NOW()),
(29, 5, 29, NOW(), NOW()),
(30, 5, 30, NOW(), NOW()),
(31, 6, 31, NOW(), NOW()),
(32, 6, 32, NOW(), NOW()),
(33, 6, 33, NOW(), NOW()),
(34, 6, 34, NOW(), NOW()),
(35, 6, 35, NOW(), NOW()),
(36, 7, 36, NOW(), NOW()),
(37, 7, 37, NOW(), NOW()),
(38, 7, 38, NOW(), NOW()),
(39, 7, 39, NOW(), NOW()),
(40, 7, 40, NOW(), NOW()),
(41, 8, 41, NOW(), NOW()),
(42, 8, 42, NOW(), NOW()),
(43, 8, 43, NOW(), NOW()),
(44, 8, 44, NOW(), NOW()),
(45, 8, 45, NOW(), NOW()),
(46, 9, 46, NOW(), NOW()),
(47, 9, 47, NOW(), NOW()),
(48, 9, 48, NOW(), NOW()),
(49, 9, 49, NOW(), NOW()),
(50, 9, 50, NOW(), NOW()),
(51, 10, 51, NOW(), NOW()),
(52, 10, 52, NOW(), NOW()),
(53, 10, 53, NOW(), NOW()),
(54, 10, 54, NOW(), NOW()),
(55, 10, 55, NOW(), NOW()),
(56, 11, 56, NOW(), NOW()),
(57, 11, 57, NOW(), NOW()),
(58, 11, 58, NOW(), NOW()),
(59, 11, 59, NOW(), NOW()),
(60, 11, 60, NOW(), NOW());

-- Insert 4 answers for each question into the answers table
INSERT INTO answers (id, content, question_id, created_at, updated_at)
VALUES
(42, 'Paris', 11, NOW(), NOW()),
(43, 'London', 11, NOW(), NOW()),
(44, 'Rome', 11, NOW(), NOW()),
(45, 'Berlin', 11, NOW(), NOW()),
(46, 'Mars', 12, NOW(), NOW()),
(47, 'Jupiter', 12, NOW(), NOW()),
(48, 'Saturn', 12, NOW(), NOW()),
(49, 'Earth', 12, NOW(), NOW()),
(50, '8', 13, NOW(), NOW()),
(51, '6', 13, NOW(), NOW()),
(52, '7', 13, NOW(), NOW()),
(53, '9', 13, NOW(), NOW()),
(54, 'Pacific', 14, NOW(), NOW()),
(55, 'Atlantic', 14, NOW(), NOW()),
(56, 'Indian', 14, NOW(), NOW()),
(57, 'Arctic', 14, NOW(), NOW()),
(58, 'H2O', 15, NOW(), NOW()),
(59, 'CO2', 15, NOW(), NOW()),
(60, 'O2', 15, NOW(), NOW()),
(61, 'N2', 15, NOW(), NOW()),
(62, 'went', 16, NOW(), NOW()),
(63, 'goes', 16, NOW(), NOW()),
(64, 'will go', 16, NOW(), NOW()),
(65, 'going', 16, NOW(), NOW()),
(66, 'rises', 17, NOW(), NOW()),
(67, 'sets', 17, NOW(), NOW()),
(68, 'shines', 17, NOW(), NOW()),
(69, 'falls', 17, NOW(), NOW()),
(70, 'joyful', 18, NOW(), NOW()),
(71, 'sad', 18, NOW(), NOW()),
(72, 'angry', 18, NOW(), NOW()),
(73, 'bored', 18, NOW(), NOW()),
(74, 'hot', 19, NOW(), NOW()),
(75, 'cold', 19, NOW(), NOW()),
(76, 'warm', 19, NOW(), NOW()),
(77, 'freezing', 19, NOW(), NOW()),
(78, 'than', 20, NOW(), NOW()),
(79, 'as', 20, NOW(), NOW()),
(80, 'like', 20, NOW(), NOW()),
(81, 'in', 20, NOW(), NOW()),
(82, 'mice', 21, NOW(), NOW()),
(83, 'mouse', 21, NOW(), NOW()),
(84, 'rats', 21, NOW(), NOW()),
(85, 'cat', 21, NOW(), NOW()),
(86, 'apple', 22, NOW(), NOW()),
(87, 'carrot', 22, NOW(), NOW()),
(88, 'potato', 22, NOW(), NOW()),
(89, 'onion', 22, NOW(), NOW()),
(90, 'necessary', 23, NOW(), NOW()),
(91, 'neccessary', 23, NOW(), NOW()),
(92, 'nescessary', 23, NOW(), NOW()),
(93, 'nesessary', 23, NOW(), NOW()),
(94, 'book', 24, NOW(), NOW()),
(95, 'run', 24, NOW(), NOW()),
(96, 'beautiful', 24, NOW(), NOW()),
(97, 'quick', 24, NOW(), NOW()),
(98, '5', 25, NOW(), NOW()),
(99, '10', 25, NOW(), NOW()),
(100, '2', 25, NOW(), NOW()),
(101, '0', 25, NOW(), NOW()),
(102, 'enjoys', 26, NOW(), NOW()),
(103, 'play', 26, NOW(), NOW()),
(104, 'plays', 26, NOW(), NOW()),
(105, 'played', 26, NOW(), NOW()),
(106, 'legs', 27, NOW(), NOW()),
(107, 'hands', 27, NOW(), NOW()),
(108, 'ears', 27, NOW(), NOW()),
(109, 'eyes', 27, NOW(), NOW()),
(110, 'beautiful', 28, NOW(), NOW()),
(111, 'run', 28, NOW(), NOW()),
(112, 'quickly', 28, NOW(), NOW()),
(113, 'elegant', 28, NOW(), NOW()),
(114, 'ran', 29, NOW(), NOW()),
(115, 'run', 29, NOW(), NOW()),
(116, 'running', 29, NOW(), NOW()),
(117, 'runned', 29, NOW(), NOW()),
(118, 'The', 30, NOW(), NOW()),
(119, 'A', 30, NOW(), NOW()),
(120, 'An', 30, NOW(), NOW()),
(121, 'Some', 30, NOW(), NOW()),
(122, 'quick', 31, NOW(), NOW()),
(123, 'slow', 31, NOW(), NOW()),
(124, 'tiny', 31, NOW(), NOW()),
(125, 'lazy', 31, NOW(), NOW()),
(126, 'bought', 32, NOW(), NOW()),
(127, 'buys', 32, NOW(), NOW()),
(128, 'buy', 32, NOW(), NOW()),
(129, 'buying', 32, NOW(), NOW()),
(130, 'flock', 33, NOW(), NOW()),
(131, 'group', 33, NOW(), NOW()),
(132, 'crowd', 33, NOW(), NOW()),
(133, 'herd', 33, NOW(), NOW()),
(134, 'down', 34, NOW(), NOW()),
(135, 'up', 34, NOW(), NOW()),
(136, 'left', 34, NOW(), NOW()),
(137, 'right', 34, NOW(), NOW()),
(138, 'is', 35, NOW(), NOW()),
(139, 'are', 35, NOW(), NOW()),
(140, 'was', 35, NOW(), NOW()),
(141, 'were', 35, NOW(), NOW()),
(142, 'go', 36, NOW(), NOW()),
(143, 'goes', 36, NOW(), NOW()),
(144, 'went', 36, NOW(), NOW()),
(145, 'going', 36, NOW(), NOW()),
(146, 'large', 37, NOW(), NOW()),
(147, 'tiny', 37, NOW(), NOW()),
(148, 'quick', 37, NOW(), NOW()),
(149, 'slow', 37, NOW(), NOW()),
(150, 'receive', 38, NOW(), NOW()),
(151, 'recieve', 38, NOW(), NOW()),
(152, 'receve', 38, NOW(), NOW()),
(153, 'reseve', 38, NOW(), NOW()),
(154, 'ate', 39, NOW(), NOW()),
(155, 'eats', 39, NOW(), NOW()),
(156, 'eat', 39, NOW(), NOW()),
(157, 'eating', 39, NOW(), NOW()),
(158, 'stars', 40, NOW(), NOW()),
(159, 'moons', 40, NOW(), NOW()),
(160, 'clouds', 40, NOW(), NOW()),
(161, 'planets', 40, NOW(), NOW()),
(162, 'night', 41, NOW(), NOW()),
(163, 'day', 41, NOW(), NOW()),
(164, 'morning', 41, NOW(), NOW()),
(165, 'evening', 41, NOW(), NOW()),
(166, 'has done', 42, NOW(), NOW()),
(167, 'done', 42, NOW(), NOW()),
(168, 'doing', 42, NOW(), NOW()),
(169, 'has doing', 42, NOW(), NOW()),
(170, 'children', 43, NOW(), NOW()),
(171, 'childs', 43, NOW(), NOW()),
(172, 'child', 43, NOW(), NOW()),
(173, 'childrens', 43, NOW(), NOW()),
(174, 'bright', 44, NOW(), NOW()),
(175, 'dark', 44, NOW(), NOW()),
(176, 'dim', 44, NOW(), NOW()),
(177, 'dull', 44, NOW(), NOW()),
(178, 'on', 45, NOW(), NOW()),
(179, 'in', 45, NOW(), NOW()),
(180, 'under', 45, NOW(), NOW()),
(181, 'over', 45, NOW(), NOW()),
(182, 'five', 46, NOW(), NOW()),
(183, 'four', 46, NOW(), NOW()),
(184, 'six', 46, NOW(), NOW()),
(185, 'three', 46, NOW(), NOW()),
(186, 'big', 47, NOW(), NOW()),
(187, 'small', 47, NOW(), NOW()),
(188, 'tiny', 47, NOW(), NOW()),
(189, 'huge', 47, NOW(), NOW()),
(190, 'going', 48, NOW(), NOW()),
(191, 'goes', 48, NOW(), NOW()),
(192, 'went', 48, NOW(), NOW()),
(193, 'gone', 48, NOW(), NOW()),
(194, 'was', 49, NOW(), NOW()),
(195, 'were', 49, NOW(), NOW()),
(196, 'is', 49, NOW(), NOW()),
(197, 'are', 49, NOW(), NOW()),
(198, 'intelligent', 50, NOW(), NOW()),
(199, 'dumb', 50, NOW(), NOW()),
(200, 'slow', 50, NOW(), NOW()),
(201, 'smart', 50, NOW(), NOW()),
(202, 'sun', 51, NOW(), NOW()),
(203, 'moon', 51, NOW(), NOW()),
(204, 'earth', 51, NOW(), NOW()),
(205, 'stars', 51, NOW(), NOW()),
(206, 'built', 52, NOW(), NOW()),
(207, 'builds', 52, NOW(), NOW()),
(208, 'building', 52, NOW(), NOW()),
(209, 'build', 52, NOW(), NOW()),
(210, 'tall', 53, NOW(), NOW()),
(211, 'short', 53, NOW(), NOW()),
(212, 'tiny', 53, NOW(), NOW()),
(213, 'big', 53, NOW(), NOW()),
(214, 'beginning', 54, NOW(), NOW()),
(215, 'beggining', 54, NOW(), NOW()),
(216, 'begening', 54, NOW(), NOW()),
(217, 'beginnning', 54, NOW(), NOW()),
(218, 'fish', 55, NOW(), NOW()),
(219, 'fishes', 55, NOW(), NOW()),
(220, 'fishs', 55, NOW(), NOW()),
(221, 'fishing', 55, NOW(), NOW()),
(222, 'went', 56, NOW(), NOW()),
(223, 'goes', 56, NOW(), NOW()),
(224, 'gone', 56, NOW(), NOW()),
(225, 'going', 56, NOW(), NOW()),
(226, 'as', 57, NOW(), NOW()),
(227, 'like', 57, NOW(), NOW()),
(228, 'than', 57, NOW(), NOW()),
(229, 'from', 57, NOW(), NOW()),
(230, 'content', 58, NOW(), NOW()),
(231, 'happy', 58, NOW(), NOW()),
(232, 'sad', 58, NOW(), NOW()),
(233, 'angry', 58, NOW(), NOW()),
(234, 'cat', 59, NOW(), NOW()),
(235, 'dog', 59, NOW(), NOW()),
(236, 'mouse', 59, NOW(), NOW()),
(237, 'fish', 59, NOW(), NOW()),
(238, 'sleeping', 60, NOW(), NOW()),
(239, 'slept', 60, NOW(), NOW()),
(240, 'sleeps', 60, NOW(), NOW()),
(241, 'sleep', 60, NOW(), NOW());

UPDATE `lessons` SET 
  `title` = 'Introduction to English Grammar', 
  `description` = 'Learn the basics of English grammar, including sentence structure and parts of speech.' 
WHERE `id` = 1;

UPDATE `lessons` SET 
  `title` = 'Common English Vocabulary for Beginners', 
  `description` = 'Expand your vocabulary with commonly used English words and their meanings.' 
WHERE `id` = 2;

UPDATE `lessons` SET 
  `title` = 'Understanding Tenses in English', 
  `description` = 'Master the usage of past, present, and future tenses in English.' 
WHERE `id` = 3;

UPDATE `lessons` SET 
  `title` = 'How to Form Questions in English', 
  `description` = 'Learn how to form different types of questions using correct grammar.' 
WHERE `id` = 4;

UPDATE `lessons` SET 
  `title` = 'English Pronunciation Basics', 
  `description` = 'Improve your English pronunciation with tips and techniques.' 
WHERE `id` = 5;

UPDATE `lessons` SET 
  `title` = 'Practicing Speaking Skills', 
  `description` = 'Develop confidence in speaking English through various practice exercises.' 
WHERE `id` = 6;

UPDATE `lessons` SET 
  `title` = 'Listening Comprehension for Beginners', 
  `description` = 'Enhance your ability to understand spoken English in everyday contexts.' 
WHERE `id` = 7;

UPDATE `lessons` SET 
  `title` = 'Building Sentences in English', 
  `description` = 'Learn how to construct sentences correctly and effectively.' 
WHERE `id` = 8;

UPDATE `lessons` SET 
  `title` = 'Common Idioms and Phrases in English', 
  `description` = 'Discover the meanings and uses of popular idioms and expressions.' 
WHERE `id` = 9;

UPDATE `lessons` SET 
  `title` = 'Reading Comprehension Skills', 
  `description` = 'Improve your ability to read and understand English texts.' 
WHERE `id` = 10;

UPDATE `lessons` SET 
  `title` = 'Writing Simple Paragraphs', 
  `description` = 'Learn how to write clear and concise paragraphs in English.' 
WHERE `id` = 11;

UPDATE `lessons` SET 
  `title` = 'Using Articles and Prepositions', 
  `description` = 'Understand how to use articles and prepositions correctly.' 
WHERE `id` = 12;

UPDATE `lessons` SET 
  `title` = 'Expanding Your Vocabulary', 
  `description` = 'Learn strategies to increase your English vocabulary effectively.' 
WHERE `id` = 13;

UPDATE `lessons` SET 
  `title` = 'Understanding Passive Voice', 
  `description` = 'Explore how to use the passive voice in English sentences.' 
WHERE `id` = 14;

UPDATE `lessons` SET 
  `title` = 'Introduction to Punctuation', 
  `description` = 'Learn the importance of punctuation and how to use it properly.' 
WHERE `id` = 15;

UPDATE `lessons` SET 
  `title` = 'Writing Formal Emails', 
  `description` = 'Discover the structure and tone required for formal email communication.' 
WHERE `id` = 16;

UPDATE `lessons` SET 
  `title` = 'Using Comparatives and Superlatives', 
  `description` = 'Understand how to compare things using the correct grammar.' 
WHERE `id` = 17;

UPDATE `lessons` SET 
  `title` = 'Speaking About Daily Activities', 
  `description` = 'Practice describing your daily routine in English.' 
WHERE `id` = 18;

UPDATE `lessons` SET 
  `title` = 'Describing People and Places', 
  `description` = 'Learn vocabulary and grammar to describe people and locations.' 
WHERE `id` = 19;

UPDATE `lessons` SET 
  `title` = 'English for Travel and Tourism', 
  `description` = 'Explore common phrases and vocabulary for traveling abroad.' 
WHERE `id` = 20;

UPDATE `lessons` SET 
  `title` = 'Understanding Conditionals', 
  `description` = 'Master the usage of conditional sentences in English.' 
WHERE `id` = 21;

UPDATE `lessons` SET 
  `title` = 'Talking About the Future', 
  `description` = 'Learn how to express plans and predictions for the future.' 
WHERE `id` = 22;

UPDATE `lessons` SET 
  `title` = 'Business English Basics', 
  `description` = 'Develop skills for professional communication in the workplace.' 
WHERE `id` = 23;

UPDATE `lessons` SET 
  `title` = 'Effective Presentation Skills', 
  `description` = 'Learn how to create and deliver engaging presentations in English.' 
WHERE `id` = 24;

UPDATE `lessons` SET 
  `title` = 'Negotiating in English', 
  `description` = 'Discover useful phrases and techniques for negotiations.' 
WHERE `id` = 25;

UPDATE `lessons` SET 
  `title` = 'Writing Personal Statements', 
  `description` = 'Learn how to write compelling personal statements for applications.' 
WHERE `id` = 26;

UPDATE `lessons` SET 
  `title` = 'Improving Pronunciation of Difficult Sounds', 
  `description` = 'Focus on the pronunciation of tricky sounds in English.' 
WHERE `id` = 27;

UPDATE `lessons` SET 
  `title` = 'Understanding Reported Speech', 
  `description` = 'Learn how to transform direct speech into reported speech.' 
WHERE `id` = 28;

UPDATE `lessons` SET 
  `title` = 'English for Social Media', 
  `description` = 'Explore the informal and creative use of English on social media.' 
WHERE `id` = 29;

UPDATE `lessons` SET 
  `title` = 'Preparing for English Exams', 
  `description` = 'Get tips and strategies for excelling in English language exams.' 
WHERE `id` = 30;

INSERT INTO `chapters` (`id`, `name`, `cover_image`, `description`, `created_at`, `updated_at`)
VALUES
(1, 'Introduction to English', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'This chapter introduces the basics of the English language.', NOW(), NOW()),
(2, 'Basic Grammar Rules', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Learn the foundational grammar rules essential for English communication.', NOW(), NOW()),
(3, 'Building Vocabulary', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Expand your vocabulary with common words and phrases.', NOW(), NOW()),
(4, 'Improving Pronunciation', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Focus on developing clear and accurate English pronunciation.', NOW(), NOW()),
(5, 'Listening and Speaking Skills', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Enhance your listening comprehension and speaking abilities.', NOW(), NOW()),
(6, 'Reading Comprehension', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Learn techniques for understanding and analyzing written English.', NOW(), NOW()),
(7, 'Writing Skills', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Improve your ability to write clear and coherent texts.', NOW(), NOW()),
(8, 'Advanced Grammar Topics', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Explore advanced grammar topics for fluent communication.', NOW(), NOW()),
(9, 'English for Everyday Use', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Learn practical English for daily conversations and interactions.', NOW(), NOW()),
(10, 'Exam Preparation', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Prepare effectively for English language exams.', NOW(), NOW());


UPDATE `chapters` SET 
  `name` = 'English Basics', 
  `description` = 'An introduction to essential English language concepts and skills.' 
WHERE `id` = 1;

UPDATE `chapters` SET 
  `name` = 'Grammar Essentials', 
  `description` = 'A comprehensive overview of basic grammar rules and their applications.' 
WHERE `id` = 2;

UPDATE `chapters` SET 
  `name` = 'Pronunciation Practice', 
  `description` = 'Learn techniques to improve your English pronunciation and fluency.' 
WHERE `id` = 4;

UPDATE `chapters` SET 
  `name` = 'Listening and Speaking', 
  `description` = 'Develop skills to listen and speak effectively in English conversations.' 
WHERE `id` = 5;

UPDATE `chapters` SET 
  `name` = 'Reading and Understanding', 
  `description` = 'Techniques to enhance reading comprehension and text analysis.' 
WHERE `id` = 6;

UPDATE `chapters` SET 
  `name` = 'Effective Writing', 
  `description` = 'Master the art of writing clear, concise, and well-structured texts in English.' 
WHERE `id` = 7;

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('The book ___ on the table.', NULL, 'Use the correct form of "be"', 1, 'is', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('She ___ to the store every day.', NULL, 'Think of a verb for regular action.', 1, 'goes', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I ___ seen that movie already.', NULL, 'Past perfect form.', 1, 'have', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ playing football now.', NULL, 'Present continuous.', 1, 'are', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('We ___ to Paris last year.', NULL, 'Think of a past action.', 1, 'went', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('If I were you, I ___ do that.', NULL, 'Conditional structure.', 1, 'would', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ finished their homework before dinner.', NULL, 'Past perfect form.', 1, 'had', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('He ___ not like coffee.', NULL, 'Negative form in present tense.', 1, 'does', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('There ___ three apples on the table.', NULL, 'Plural subject agreement.', 1, 'are', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('By next year, she ___ graduated from college.', NULL, 'Future perfect tense.', 1, 'will have', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('The children ___ playing in the park.', NULL, 'Past continuous form.', 1, 'were', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('He ___ to finish his homework before the deadline.', NULL, 'Modal verb for obligation.', 1, 'has', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('We ___ live in London when we were young.', NULL, 'Past habit structure.', 1, 'used to', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I think she ___ the most talented person here.', NULL, 'Superlative comparison.', 1, 'is', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I ___ to the beach tomorrow.', NULL, 'Future plan.', 1, 'am going', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('We ___ not agree with his opinion.', NULL, 'Negative form in present tense.', 1, 'do', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('She ___ working here since 2015.', NULL, 'Present perfect continuous.', 1, 'has been', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ meet us at the station at 6 PM.', NULL, 'Future arrangement.', 1, 'will', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('The train ___ leave the station at 9 AM.', NULL, 'Simple future tense.', 1, 'will', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I ___ not understand the question.', NULL, 'Negative form in present tense.', 1, 'do', NOW(), NOW());

-- Đáp án cho câu hỏi 1
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 1, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('are', 1, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('was', 1, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('be', 1, NOW(), NOW());

-- Đáp án cho câu hỏi 2
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('goes', 2, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('go', 2, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('gone', 2, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('going', 2, NOW(), NOW());

-- Đáp án cho câu hỏi 3
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('have', 3, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('has', 3, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('had', 3, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 3, NOW(), NOW());

-- Đáp án cho câu hỏi 4
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('are', 4, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 4, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('was', 4, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('be', 4, NOW(), NOW());

-- Đáp án cho câu hỏi 5
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('went', 5, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('go', 5, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('gone', 5, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('going', 5, NOW(), NOW());

-- Đáp án cho câu hỏi 6
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('would', 6, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('will', 6, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('can', 6, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('shall', 6, NOW(), NOW());

-- Đáp án cho câu hỏi 7
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('had', 7, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('have', 7, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('has', 7, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 7, NOW(), NOW());

-- Đáp án cho câu hỏi 8
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('does', 8, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('do', 8, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('did', 8, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 8, NOW(), NOW());

-- Đáp án cho câu hỏi 9
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('are', 9, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 9, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('was', 9, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('were', 9, NOW(), NOW());

-- Đáp án cho câu hỏi 10
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('will have', 10, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('has', 10, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('had', 10, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('have', 10, NOW(), NOW());

-- Đáp án cho câu hỏi 11
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('were', 11, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('was', 11, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('are', 11, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 11, NOW(), NOW());

-- Đáp án cho câu hỏi 12
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('has', 12, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('have', 12, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('had', 12, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 12, NOW(), NOW());

-- Đáp án cho câu hỏi 13
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('used to', 13, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('use to', 13, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('using to', 13, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('used', 13, NOW(), NOW());

-- Đáp án cho câu hỏi 14
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 14, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('are', 14, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('was', 14, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('were', 14, NOW(), NOW());

-- Đáp án cho câu hỏi 15
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('am going', 15, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is going', 15, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('are going', 15, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('will go', 15, NOW(), NOW());

-- Đáp án cho câu hỏi 16
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('do', 16, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('does', 16, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('did', 16, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 16, NOW(), NOW());

-- Đáp án cho câu hỏi 17
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('has been', 17, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('have been', 17, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('was', 17, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 17, NOW(), NOW());

-- Đáp án cho câu hỏi 18
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('will', 18, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('shall', 18, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('can', 18, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('should', 18, NOW(), NOW());

-- Đáp án cho câu hỏi 19
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('will', 19, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('shall', 19, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('can', 19, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('should', 19, NOW(), NOW());

-- Đáp án cho câu hỏi 20
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('do', 20, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('does', 20, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('did', 20, NOW(), NOW());
INSERT INTO answers (content, question_id, created_at, updated_at) VALUES ('is', 20, NOW(), NOW());

UPDATE `lessons` SET 
  `title` = 'Introduction to Sentence Structure', 
  `description` = 'Learn the fundamental rules of building sentences in English.' 
WHERE `id` = 51;

UPDATE `lessons` SET 
  `title` = 'Common Verbs and Their Usage', 
  `description` = 'Explore frequently used verbs and how to apply them in sentences.' 
WHERE `id` = 52;

UPDATE `lessons` SET 
  `title` = 'Mastering Adjectives and Adverbs', 
  `description` = 'Understand the role of adjectives and adverbs in enhancing descriptions.' 
WHERE `id` = 53;

UPDATE `lessons` SET 
  `title` = 'Question Formation in English', 
  `description` = 'Learn the structure and usage of questions in English grammar.' 
WHERE `id` = 54;

UPDATE `lessons` SET 
  `title` = 'Using Conjunctions Effectively', 
  `description` = 'Master the use of conjunctions to connect ideas and sentences.' 
WHERE `id` = 55;

UPDATE `lessons` SET 
  `title` = 'Active vs Passive Voice', 
  `description` = 'Understand the differences between active and passive voice in English.' 
WHERE `id` = 56;

UPDATE `lessons` SET 
  `title` = 'Improving Listening Skills', 
  `description` = 'Develop the ability to comprehend spoken English more effectively.' 
WHERE `id` = 57;

UPDATE `lessons` SET 
  `title` = 'Speaking About Hobbies and Interests', 
  `description` = 'Learn how to talk about your hobbies and interests in English.' 
WHERE `id` = 58;

UPDATE `lessons` SET 
  `title` = 'Writing Informal Emails', 
  `description` = 'Discover how to write casual and friendly emails in English.' 
WHERE `id` = 59;

UPDATE `lessons` SET 
  `title` = 'Describing Events in English', 
  `description` = 'Learn how to narrate events and tell stories in English.' 
WHERE `id` = 60;

-- Repeat similar updates for lessons 61 to 100
-- Here are some example titles and descriptions for lessons beyond 60:

UPDATE `lessons` SET 
  `title` = 'Talking About Past Experiences', 
  `description` = 'Practice sharing your past experiences using correct tenses.' 
WHERE `id` = 61;

UPDATE `lessons` SET 
  `title` = 'Describing Future Plans', 
  `description` = 'Learn how to articulate your future intentions and goals.' 
WHERE `id` = 62;

UPDATE `lessons` SET 
  `title` = 'Using Prepositions in Detail', 
  `description` = 'Understand the nuanced use of prepositions in various contexts.' 
WHERE `id` = 63;

UPDATE `lessons` SET 
  `title` = 'Discussing Opinions and Ideas', 
  `description` = 'Learn how to share and discuss your opinions effectively in English.' 
WHERE `id` = 64;

UPDATE `lessons` SET 
  `title` = 'Idiomatic Expressions in English', 
  `description` = 'Explore commonly used idioms and their meanings.' 
WHERE `id` = 65;

UPDATE `lessons` SET 
  `title` = 'Understanding Complex Sentences', 
  `description` = 'Learn how to form and interpret complex sentences in English.' 
WHERE `id` = 66;

UPDATE `lessons` SET 
  `title` = 'Effective Note-Taking Skills', 
  `description` = 'Develop skills to take clear and concise notes in English.' 
WHERE `id` = 67;

UPDATE `lessons` SET 
  `title` = 'Talking About Current Events', 
  `description` = 'Learn how to discuss news and current affairs in English.' 
WHERE `id` = 68;

UPDATE `lessons` SET 
  `title` = 'Understanding Figurative Language', 
  `description` = 'Explore metaphors, similes, and other figurative expressions in English.' 
WHERE `id` = 69;

UPDATE `lessons` SET 
  `title` = 'Preparing for Public Speaking', 
  `description` = 'Build confidence and learn strategies for delivering speeches in English.' 
WHERE `id` = 70;

-- Continue in a similar manner for lessons 71 to 100
INSERT INTO `lessons` (`id`, `title`, `chapter_id`, `description`, `created_at`, `updated_at`)
VALUES
(31, 'Exploring Synonyms and Antonyms', 3, 'Learn how to use synonyms and antonyms to enhance your vocabulary.', NOW(), NOW()),
(32, 'Using Context Clues for Vocabulary', 3, 'Develop skills to infer word meanings from context.', NOW(), NOW()),
(33, 'Writing Descriptive Paragraphs', 4, 'Practice writing vivid and detailed descriptive paragraphs.', NOW(), NOW()),
(34, 'Editing and Proofreading Skills', 4, 'Learn how to revise and edit your writing effectively.', NOW(), NOW()),
(35, 'Phrasal Verbs in Everyday English', 5, 'Discover the meanings and uses of common phrasal verbs.', NOW(), NOW()),
(36, 'Using Modal Verbs Correctly', 5, 'Master the usage of modal verbs for expressing ability, possibility, and more.', NOW(), NOW()),
(37, 'Understanding Sentence Fragments', 6, 'Identify and correct incomplete sentences in English.', NOW(), NOW()),
(38, 'Combining Sentences with Conjunctions', 6, 'Learn how to use conjunctions to combine ideas effectively.', NOW(), NOW()),
(39, 'Talking About Your Hometown', 7, 'Practice describing your hometown and its features.', NOW(), NOW()),
(40, 'Discussing Weather and Seasons', 7, 'Learn vocabulary and phrases to talk about weather and seasons.', NOW(), NOW()),
(41, 'Understanding Prefixes and Suffixes', 8, 'Explore how prefixes and suffixes change word meanings.', NOW(), NOW()),
(42, 'Building Academic Vocabulary', 8, 'Develop a strong foundation of vocabulary for academic purposes.', NOW(), NOW()),
(43, 'Describing Feelings and Emotions', 9, 'Learn how to express your emotions clearly in English.', NOW(), NOW()),
(44, 'Using Time Expressions', 9, 'Master time expressions to describe schedules and events.', NOW(), NOW()),
(45, 'Writing Opinion Essays', 10, 'Learn how to structure and present your opinions in essay format.', NOW(), NOW()),
(46, 'Analyzing Short Stories', 10, 'Enhance your reading comprehension by analyzing short stories.', NOW(), NOW()),
(47, 'Talking About Your Favorite Activities', 10, 'Practice discussing your favorite activities and hobbies.', NOW(), NOW()),
(48, 'Role-playing Real-life Situations', 10, 'Improve speaking skills through role-playing common scenarios.', NOW(), NOW()),
(49, 'Using Visual Aids in Presentations', 10, 'Learn how to enhance your presentations with effective visual aids.', NOW(), NOW()),
(50, 'Debating and Persuasion Techniques', 10, 'Master techniques for debating and persuading effectively in English.', NOW(), NOW());

UPDATE `lessons` SET 
  `title` = 'Understanding Compound Sentences', 
  `description` = 'Learn how to construct compound sentences using conjunctions and punctuation.' 
WHERE `id` = 71;

UPDATE `lessons` SET 
  `title` = 'Using Conditional Sentences', 
  `description` = 'Master the formation and usage of conditional sentences in English.' 
WHERE `id` = 72;

UPDATE `lessons` SET 
  `title` = 'Developing Listening for Details', 
  `description` = 'Enhance your ability to pick up specific details in spoken English.' 
WHERE `id` = 73;

UPDATE `lessons` SET 
  `title` = 'Describing Daily Routines', 
  `description` = 'Practice talking about your daily activities in English.' 
WHERE `id` = 74;

UPDATE `lessons` SET 
  `title` = 'Analyzing Paragraph Structures', 
  `description` = 'Learn how to analyze and understand the structure of English paragraphs.' 
WHERE `id` = 75;

UPDATE `lessons` SET 
  `title` = 'Using Linking Words in Writing', 
  `description` = 'Understand how to use linking words to create cohesive texts.' 
WHERE `id` = 76;

UPDATE `lessons` SET 
  `title` = 'Expressing Preferences in English', 
  `description` = 'Learn how to talk about your preferences and choices in English.' 
WHERE `id` = 77;

UPDATE `lessons` SET 
  `title` = 'Understanding Figurative Language', 
  `description` = 'Explore metaphors, similes, and other figures of speech in English.' 
WHERE `id` = 78;

UPDATE `lessons` SET 
  `title` = 'Improving Pronunciation through Reading', 
  `description` = 'Practice pronunciation by reading aloud and focusing on difficult sounds.' 
WHERE `id` = 79;

UPDATE `lessons` SET 
  `title` = 'Discussing Current Events', 
  `description` = 'Learn vocabulary and phrases for talking about news and current events.' 
WHERE `id` = 80;

UPDATE `lessons` SET 
  `title` = 'Introduction to Academic Writing', 
  `description` = 'Develop skills for writing essays and research papers in English.' 
WHERE `id` = 81;

UPDATE `lessons` SET 
  `title` = 'Mastering Relative Clauses', 
  `description` = 'Learn how to use relative clauses to provide additional information.' 
WHERE `id` = 82;

UPDATE `lessons` SET 
  `title` = 'Practicing Interview Skills', 
  `description` = 'Develop confidence and skills for participating in English interviews.' 
WHERE `id` = 83;

UPDATE `lessons` SET 
  `title` = 'Describing Future Goals', 
  `description` = 'Learn how to express your future aspirations and plans in English.' 
WHERE `id` = 84;

UPDATE `lessons` SET 
  `title` = 'Reading and Analyzing Poetry', 
  `description` = 'Explore English poetry to enhance your comprehension and interpretation skills.' 
WHERE `id` = 85;

UPDATE `lessons` SET 
  `title` = 'Building Complex Sentences', 
  `description` = 'Learn how to construct and use complex sentences effectively.' 
WHERE `id` = 86;

UPDATE `lessons` SET 
  `title` = 'Discussing Cultural Differences', 
  `description` = 'Explore vocabulary and phrases for discussing cultural diversity.' 
WHERE `id` = 87;

UPDATE `lessons` SET 
  `title` = 'Writing Professional Emails', 
  `description` = 'Learn how to write clear and professional emails in English.' 
WHERE `id` = 88;

UPDATE `lessons` SET 
  `title` = 'Exploring Nonverbal Communication', 
  `description` = 'Understand the importance of nonverbal cues in effective communication.' 
WHERE `id` = 89;

UPDATE `lessons` SET 
  `title` = 'Mastering Report Writing', 
  `description` = 'Learn how to write structured and informative reports in English.' 
WHERE `id` = 90;

UPDATE `lessons` SET 
  `title` = 'Expressing Opinions Politely', 
  `description` = 'Practice sharing your opinions respectfully in discussions.' 
WHERE `id` = 91;

UPDATE `lessons` SET 
  `title` = 'Using Transition Words in Essays', 
  `description` = 'Master the use of transition words for smooth flow in essays.' 
WHERE `id` = 92;

UPDATE `lessons` SET 
  `title` = 'Understanding News Articles', 
  `description` = 'Learn how to read and analyze English news articles effectively.' 
WHERE `id` = 93;

UPDATE `lessons` SET 
  `title` = 'Exploring Leadership Vocabulary', 
  `description` = 'Build your vocabulary for discussing leadership and management topics.' 
WHERE `id` = 94;

UPDATE `lessons` SET 
  `title` = 'Talking About Historical Events', 
  `description` = 'Practice discussing important historical events in English.' 
WHERE `id` = 95;

UPDATE `lessons` SET 
  `title` = 'Writing Summaries and Reviews', 
  `description` = 'Learn how to summarize and review texts effectively in English.' 
WHERE `id` = 96;

UPDATE `lessons` SET 
  `title` = 'Debating Ethical Issues', 
  `description` = 'Develop skills for debating ethical and moral issues in English.' 
WHERE `id` = 97;

UPDATE `lessons` SET 
  `title` = 'Understanding Formal vs Informal English', 
  `description` = 'Learn when to use formal and informal language in different contexts.' 
WHERE `id` = 98;

UPDATE `lessons` SET 
  `title` = 'Preparing for Group Discussions', 
  `description` = 'Practice participating in and contributing to group discussions in English.' 
WHERE `id` = 99;

UPDATE `lessons` SET 
  `title` = 'Exploring Creative Writing', 
  `description` = 'Develop your creative writing skills through various exercises and prompts.' 
WHERE `id` = 100;


CREATE TABLE `lesson_questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lesson_id` int DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lesson_id` (`lesson_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `lesson_questions_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`id`),
  CONSTRAINT `lesson_questions_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
----------------
giờ tôi có 80 question id từ 1 đến 80, có 100 lesson id từ 1 đến 100, tạo giúp tôi lesson question với lesson id từ 3 đến 100, mỗi cái sẽ có 4 câu hỏi, question lấy từ id 1 đến 80

UPDATE `answers` SET `question_id` = 61 WHERE `id` BETWEEN 242 AND 245;
UPDATE `answers` SET `question_id` = 62 WHERE `id` BETWEEN 246 AND 249;
UPDATE `answers` SET `question_id` = 63 WHERE `id` BETWEEN 250 AND 253;
UPDATE `answers` SET `question_id` = 64 WHERE `id` BETWEEN 254 AND 257;
UPDATE `answers` SET `question_id` = 65 WHERE `id` BETWEEN 258 AND 261;
UPDATE `answers` SET `question_id` = 66 WHERE `id` BETWEEN 262 AND 265;
UPDATE `answers` SET `question_id` = 67 WHERE `id` BETWEEN 266 AND 269;
UPDATE `answers` SET `question_id` = 68 WHERE `id` BETWEEN 270 AND 273;
UPDATE `answers` SET `question_id` = 69 WHERE `id` BETWEEN 274 AND 277;
UPDATE `answers` SET `question_id` = 70 WHERE `id` BETWEEN 278 AND 281;
UPDATE `answers` SET `question_id` = 71 WHERE `id` BETWEEN 282 AND 285;
UPDATE `answers` SET `question_id` = 72 WHERE `id` BETWEEN 286 AND 289;
UPDATE `answers` SET `question_id` = 73 WHERE `id` BETWEEN 290 AND 293;
UPDATE `answers` SET `question_id` = 74 WHERE `id` BETWEEN 294 AND 297;
UPDATE `answers` SET `question_id` = 75 WHERE `id` BETWEEN 298 AND 301;
UPDATE `answers` SET `question_id` = 76 WHERE `id` BETWEEN 302 AND 305;
UPDATE `answers` SET `question_id` = 77 WHERE `id` BETWEEN 306 AND 309;
UPDATE `answers` SET `question_id` = 78 WHERE `id` BETWEEN 310 AND 313;
UPDATE `answers` SET `question_id` = 79 WHERE `id` BETWEEN 314 AND 317;
UPDATE `answers` SET `question_id` = 80 WHERE `id` BETWEEN 318 AND 321;

-- Câu hỏi
INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('She ___ studying for the exam when I called.', NULL, 'Past continuous form.', 1, 'was', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I ___ never been to Japan before.', NULL, 'Present perfect tense.', 1, 'have', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ running when it started to rain.', NULL, 'Past continuous action.', 1, 'were', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('We ___ have a meeting tomorrow.', NULL, 'Future arrangement.', 1, 'will', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('He ___ his homework already.', NULL, 'Present perfect tense.', 1, 'has finished', NOW(), NOW());

-- Đáp án
INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('was', 81, NOW(), NOW()), ('is', 81, NOW(), NOW()), ('are', 81, NOW(), NOW()), ('be', 81, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('have', 82, NOW(), NOW()), ('has', 82, NOW(), NOW()), ('had', 82, NOW(), NOW()), ('was', 82, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('were', 83, NOW(), NOW()), ('was', 83, NOW(), NOW()), ('is', 83, NOW(), NOW()), ('be', 83, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('will', 84, NOW(), NOW()), ('would', 84, NOW(), NOW()), ('shall', 84, NOW(), NOW()), ('may', 84, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('has finished', 85, NOW(), NOW()), ('have finished', 85, NOW(), NOW()), ('finished', 85, NOW(), NOW()), ('was finished', 85, NOW(), NOW());

-- Câu hỏi
INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('The cat ___ sleeping on the chair.', NULL, 'Present continuous tense.', 1, 'is', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I ___ like chocolate when I was a child.', NULL, 'Negative past tense.', 1, 'did not', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('We ___ going to visit the museum tomorrow.', NULL, 'Future plan.', 1, 'are', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('He ___ working hard on his project.', NULL, 'Present continuous tense.', 1, 'is', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ to the park every weekend.', NULL, 'Habitual present action.', 1, 'go', NOW(), NOW());

-- Đáp án
INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('is', 86, NOW(), NOW()), ('are', 86, NOW(), NOW()), ('was', 86, NOW(), NOW()), ('be', 86, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('did not', 87, NOW(), NOW()), ('do not', 87, NOW(), NOW()), ('does not', 87, NOW(), NOW()), ('was not', 87, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('are', 88, NOW(), NOW()), ('is', 88, NOW(), NOW()), ('will', 88, NOW(), NOW()), ('was', 88, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('is', 89, NOW(), NOW()), ('are', 89, NOW(), NOW()), ('be', 89, NOW(), NOW()), ('was', 89, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('go', 90, NOW(), NOW()), ('goes', 90, NOW(), NOW()), ('going', 90, NOW(), NOW()), ('went', 90, NOW(), NOW());

-- Câu hỏi
INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('I ___ playing football when it started raining.', NULL, 'Past continuous action.', 1, 'was', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('She ___ never been to the USA before.', NULL, 'Present perfect tense.', 1, 'has', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('We ___ a wonderful time at the party last night.', NULL, 'Past simple action.', 1, 'had', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ not understand the instructions.', NULL, 'Negative past tense.', 1, 'did', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('By 2025, we ___ completed the project.', NULL, 'Future perfect tense.', 1, 'will have', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('He ___ to the store every Saturday.', NULL, 'Present habitual action.', 1, 'goes', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('They ___ studying for the final exam tomorrow.', NULL, 'Future continuous action.', 1, 'will be', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('She ___ arrived before the meeting started.', NULL, 'Past perfect action.', 1, 'had', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('The train ___ on time every day.', NULL, 'Simple present tense.', 1, 'arrives', NOW(), NOW());

INSERT INTO questions (content, audio_url, hint, type_id, correct_answer, created_at, updated_at)
VALUES ('He ___ not work on weekends.', NULL, 'Negative present tense.', 1, 'does', NOW(), NOW());

-- Đáp án
INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('was', 91, NOW(), NOW()), ('is', 91, NOW(), NOW()), ('are', 91, NOW(), NOW()), ('be', 91, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('has', 92, NOW(), NOW()), ('have', 92, NOW(), NOW()), ('had', 92, NOW(), NOW()), ('was', 92, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('had', 93, NOW(), NOW()), ('has', 93, NOW(), NOW()), ('have', 93, NOW(), NOW()), ('did', 93, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('did', 94, NOW(), NOW()), ('do', 94, NOW(), NOW()), ('does', 94, NOW(), NOW()), ('was', 94, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('will have', 95, NOW(), NOW()), ('has', 95, NOW(), NOW()), ('had', 95, NOW(), NOW()), ('will', 95, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('goes', 96, NOW(), NOW()), ('go', 96, NOW(), NOW()), ('going', 96, NOW(), NOW()), ('went', 96, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('will be', 97, NOW(), NOW()), ('is', 97, NOW(), NOW()), ('are', 97, NOW(), NOW()), ('was', 97, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('had', 98, NOW(), NOW()), ('has', 98, NOW(), NOW()), ('have', 98, NOW(), NOW()), ('was', 98, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('arrives', 99, NOW(), NOW()), ('arrive', 99, NOW(), NOW()), ('arriving', 99, NOW(), NOW()), ('arrived', 99, NOW(), NOW());

INSERT INTO answers (content, question_id, created_at, updated_at)
VALUES ('does', 100, NOW(), NOW()), ('do', 100, NOW(), NOW()), ('did', 100, NOW(), NOW()), ('was', 100, NOW(), NOW());

-- Insert lesson_questions
INSERT INTO `lesson_questions` (`lesson_id`, `question_id`, `created_at`, `updated_at`)
VALUES
(3, 1, NOW(), NOW()), (3, 2, NOW(), NOW()), (3, 3, NOW(), NOW()), (3, 4, NOW(), NOW()),
(4, 5, NOW(), NOW()), (4, 6, NOW(), NOW()), (4, 7, NOW(), NOW()), (4, 8, NOW(), NOW()),
(5, 9, NOW(), NOW()), (5, 10, NOW(), NOW()), (5, 11, NOW(), NOW()), (5, 12, NOW(), NOW()),
(6, 13, NOW(), NOW()), (6, 14, NOW(), NOW()), (6, 15, NOW(), NOW()), (6, 16, NOW(), NOW()),
(7, 17, NOW(), NOW()), (7, 18, NOW(), NOW()), (7, 19, NOW(), NOW()), (7, 20, NOW(), NOW()),
(8, 21, NOW(), NOW()), (8, 22, NOW(), NOW()), (8, 23, NOW(), NOW()), (8, 24, NOW(), NOW()),
(9, 25, NOW(), NOW()), (9, 26, NOW(), NOW()), (9, 27, NOW(), NOW()), (9, 28, NOW(), NOW()),
(10, 29, NOW(), NOW()), (10, 30, NOW(), NOW()), (10, 31, NOW(), NOW()), (10, 32, NOW(), NOW()),
(11, 33, NOW(), NOW()), (11, 34, NOW(), NOW()), (11, 35, NOW(), NOW()), (11, 36, NOW(), NOW()),
(12, 37, NOW(), NOW()), (12, 38, NOW(), NOW()), (12, 39, NOW(), NOW()), (12, 40, NOW(), NOW()),
(13, 41, NOW(), NOW()), (13, 42, NOW(), NOW()), (13, 43, NOW(), NOW()), (13, 44, NOW(), NOW()),
(14, 45, NOW(), NOW()), (14, 46, NOW(), NOW()), (14, 47, NOW(), NOW()), (14, 48, NOW(), NOW()),
(15, 49, NOW(), NOW()), (15, 50, NOW(), NOW()), (15, 51, NOW(), NOW()), (15, 52, NOW(), NOW()),
(16, 53, NOW(), NOW()), (16, 54, NOW(), NOW()), (16, 55, NOW(), NOW()), (16, 56, NOW(), NOW()),
(17, 57, NOW(), NOW()), (17, 58, NOW(), NOW()), (17, 59, NOW(), NOW()), (17, 60, NOW(), NOW()),
(18, 61, NOW(), NOW()), (18, 62, NOW(), NOW()), (18, 63, NOW(), NOW()), (18, 64, NOW(), NOW()),
(19, 65, NOW(), NOW()), (19, 66, NOW(), NOW()), (19, 67, NOW(), NOW()), (19, 68, NOW(), NOW()),
(20, 69, NOW(), NOW()), (20, 70, NOW(), NOW()), (20, 71, NOW(), NOW()), (20, 72, NOW(), NOW()),
(21, 73, NOW(), NOW()), (21, 74, NOW(), NOW()), (21, 75, NOW(), NOW()), (21, 76, NOW(), NOW()),
(22, 77, NOW(), NOW()), (22, 78, NOW(), NOW()), (22, 79, NOW(), NOW()), (22, 80, NOW(), NOW()),
(23, 81, NOW(), NOW()), (23, 82, NOW(), NOW()), (23, 83, NOW(), NOW()), (23, 84, NOW(), NOW()),
(24, 85, NOW(), NOW()), (24, 86, NOW(), NOW()), (24, 87, NOW(), NOW()), (24, 88, NOW(), NOW()),
(25, 89, NOW(), NOW()), (25, 90, NOW(), NOW()), (25, 91, NOW(), NOW()), (25, 92, NOW(), NOW()),
(26, 93, NOW(), NOW()), (26, 94, NOW(), NOW()), (26, 95, NOW(), NOW()), (26, 96, NOW(), NOW()),
(27, 97, NOW(), NOW()), (27, 98, NOW(), NOW()), (27, 99, NOW(), NOW()), (27, 100, NOW(), NOW());

-- Insert lesson_questions from lesson_id 28 onward
INSERT INTO `lesson_questions` (`lesson_id`, `question_id`, `created_at`, `updated_at`)
VALUES
(28, 1, NOW(), NOW()), (28, 2, NOW(), NOW()), (28, 3, NOW(), NOW()), (28, 4, NOW(), NOW()),
(29, 5, NOW(), NOW()), (29, 6, NOW(), NOW()), (29, 7, NOW(), NOW()), (29, 8, NOW(), NOW()),
(30, 9, NOW(), NOW()), (30, 10, NOW(), NOW()), (30, 11, NOW(), NOW()), (30, 12, NOW(), NOW()),
(31, 13, NOW(), NOW()), (31, 14, NOW(), NOW()), (31, 15, NOW(), NOW()), (31, 16, NOW(), NOW()),
(32, 17, NOW(), NOW()), (32, 18, NOW(), NOW()), (32, 19, NOW(), NOW()), (32, 20, NOW(), NOW()),
(33, 21, NOW(), NOW()), (33, 22, NOW(), NOW()), (33, 23, NOW(), NOW()), (33, 24, NOW(), NOW()),
(34, 25, NOW(), NOW()), (34, 26, NOW(), NOW()), (34, 27, NOW(), NOW()), (34, 28, NOW(), NOW()),
(35, 29, NOW(), NOW()), (35, 30, NOW(), NOW()), (35, 31, NOW(), NOW()), (35, 32, NOW(), NOW()),
(36, 33, NOW(), NOW()), (36, 34, NOW(), NOW()), (36, 35, NOW(), NOW()), (36, 36, NOW(), NOW()),
(37, 37, NOW(), NOW()), (37, 38, NOW(), NOW()), (37, 39, NOW(), NOW()), (37, 40, NOW(), NOW()),
(38, 41, NOW(), NOW()), (38, 42, NOW(), NOW()), (38, 43, NOW(), NOW()), (38, 44, NOW(), NOW()),
(39, 45, NOW(), NOW()), (39, 46, NOW(), NOW()), (39, 47, NOW(), NOW()), (39, 48, NOW(), NOW()),
(40, 49, NOW(), NOW()), (40, 50, NOW(), NOW()), (40, 51, NOW(), NOW()), (40, 52, NOW(), NOW()),
(41, 53, NOW(), NOW()), (41, 54, NOW(), NOW()), (41, 55, NOW(), NOW()), (41, 56, NOW(), NOW()),
(42, 57, NOW(), NOW()), (42, 58, NOW(), NOW()), (42, 59, NOW(), NOW()), (42, 60, NOW(), NOW()),
(43, 61, NOW(), NOW()), (43, 62, NOW(), NOW()), (43, 63, NOW(), NOW()), (43, 64, NOW(), NOW()),
(44, 65, NOW(), NOW()), (44, 66, NOW(), NOW()), (44, 67, NOW(), NOW()), (44, 68, NOW(), NOW()),
(45, 69, NOW(), NOW()), (45, 70, NOW(), NOW()), (45, 71, NOW(), NOW()), (45, 72, NOW(), NOW()),
(46, 73, NOW(), NOW()), (46, 74, NOW(), NOW()), (46, 75, NOW(), NOW()), (46, 76, NOW(), NOW()),
(47, 77, NOW(), NOW()), (47, 78, NOW(), NOW()), (47, 79, NOW(), NOW()), (47, 80, NOW(), NOW()),
(48, 81, NOW(), NOW()), (48, 82, NOW(), NOW()), (48, 83, NOW(), NOW()), (48, 84, NOW(), NOW()),
(49, 85, NOW(), NOW()), (49, 86, NOW(), NOW()), (49, 87, NOW(), NOW()), (49, 88, NOW(), NOW()),
(50, 89, NOW(), NOW()), (50, 90, NOW(), NOW()), (50, 91, NOW(), NOW()), (50, 92, NOW(), NOW());

-- Insert lesson_questions from lesson_id 51 onward
INSERT INTO `lesson_questions` (`lesson_id`, `question_id`, `created_at`, `updated_at`)
VALUES
(51, 1, NOW(), NOW()), (51, 2, NOW(), NOW()), (51, 3, NOW(), NOW()), (51, 4, NOW(), NOW()),
(52, 5, NOW(), NOW()), (52, 6, NOW(), NOW()), (52, 7, NOW(), NOW()), (52, 8, NOW(), NOW()),
(53, 9, NOW(), NOW()), (53, 10, NOW(), NOW()), (53, 11, NOW(), NOW()), (53, 12, NOW(), NOW()),
(54, 13, NOW(), NOW()), (54, 14, NOW(), NOW()), (54, 15, NOW(), NOW()), (54, 16, NOW(), NOW()),
(55, 17, NOW(), NOW()), (55, 18, NOW(), NOW()), (55, 19, NOW(), NOW()), (55, 20, NOW(), NOW()),
(56, 21, NOW(), NOW()), (56, 22, NOW(), NOW()), (56, 23, NOW(), NOW()), (56, 24, NOW(), NOW()),
(57, 25, NOW(), NOW()), (57, 26, NOW(), NOW()), (57, 27, NOW(), NOW()), (57, 28, NOW(), NOW()),
(58, 29, NOW(), NOW()), (58, 30, NOW(), NOW()), (58, 31, NOW(), NOW()), (58, 32, NOW(), NOW()),
(59, 33, NOW(), NOW()), (59, 34, NOW(), NOW()), (59, 35, NOW(), NOW()), (59, 36, NOW(), NOW()),
(60, 37, NOW(), NOW()), (60, 38, NOW(), NOW()), (60, 39, NOW(), NOW()), (60, 40, NOW(), NOW()),
(61, 41, NOW(), NOW()), (61, 42, NOW(), NOW()), (61, 43, NOW(), NOW()), (61, 44, NOW(), NOW()),
(62, 45, NOW(), NOW()), (62, 46, NOW(), NOW()), (62, 47, NOW(), NOW()), (62, 48, NOW(), NOW()),
(63, 49, NOW(), NOW()), (63, 50, NOW(), NOW()), (63, 51, NOW(), NOW()), (63, 52, NOW(), NOW()),
(64, 53, NOW(), NOW()), (64, 54, NOW(), NOW()), (64, 55, NOW(), NOW()), (64, 56, NOW(), NOW()),
(65, 57, NOW(), NOW()), (65, 58, NOW(), NOW()), (65, 59, NOW(), NOW()), (65, 60, NOW(), NOW()),
(66, 61, NOW(), NOW()), (66, 62, NOW(), NOW()), (66, 63, NOW(), NOW()), (66, 64, NOW(), NOW()),
(67, 65, NOW(), NOW()), (67, 66, NOW(), NOW()), (67, 67, NOW(), NOW()), (67, 68, NOW(), NOW()),
(68, 69, NOW(), NOW()), (68, 70, NOW(), NOW()), (68, 71, NOW(), NOW()), (68, 72, NOW(), NOW()),
(69, 73, NOW(), NOW()), (69, 74, NOW(), NOW()), (69, 75, NOW(), NOW()), (69, 76, NOW(), NOW()),
(70, 77, NOW(), NOW()), (70, 78, NOW(), NOW()), (70, 79, NOW(), NOW()), (70, 80, NOW(), NOW()),
(71, 81, NOW(), NOW()), (71, 82, NOW(), NOW()), (71, 83, NOW(), NOW()), (71, 84, NOW(), NOW()),
(72, 85, NOW(), NOW()), (72, 86, NOW(), NOW()), (72, 87, NOW(), NOW()), (72, 88, NOW(), NOW()),
(73, 89, NOW(), NOW()), (73, 90, NOW(), NOW()), (73, 91, NOW(), NOW()), (73, 92, NOW(), NOW()),
(74, 93, NOW(), NOW()), (74, 94, NOW(), NOW()), (74, 95, NOW(), NOW()), (74, 96, NOW(), NOW()),
(75, 97, NOW(), NOW()), (75, 98, NOW(), NOW()), (75, 99, NOW(), NOW()), (75, 100, NOW(), NOW());

INSERT INTO `chapters` (`id`, `name`, `cover_image`, `description`, `created_at`, `updated_at`)
VALUES
(11, 'English Idioms and Expressions', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Discover commonly used idioms and expressions to enhance your fluency.', NOW(), NOW()),
(12, 'Business English', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Develop communication skills for professional environments.', NOW(), NOW()),
(13, 'Cultural Topics in English', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Explore cultural topics to understand global perspectives in English.', NOW(), NOW()),
(14, 'English for Academic Purposes', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Learn English skills for research, essays, and academic presentations.', NOW(), NOW()),
(15, 'Creative Writing in English', 'https://www.vec.ca/wp-content/uploads/2019/03/English-Language-Level-System.jpg', 'Develop your creativity through storytelling, poetry, and essays.', NOW(), NOW());

INSERT INTO `lessons` (`id`, `title`, `chapter_id`, `description`, `created_at`, `updated_at`)
VALUES
(51, 'Understanding English Idioms', 11, 'Learn the meaning and usage of popular idioms in English.', NOW(), NOW()),
(52, 'Mastering Common Expressions', 11, 'Explore common expressions and their application in daily conversations.', NOW(), NOW()),
(53, 'Developing Business Vocabulary', 12, 'Build a strong foundation of vocabulary for business contexts.', NOW(), NOW()),
(54, 'Writing Professional Emails', 12, 'Learn how to write clear and effective professional emails.', NOW(), NOW()),
(55, 'Cultural Topics: Food and Traditions', 13, 'Explore how food and traditions shape cultural identity in English.', NOW(), NOW()),
(56, 'Cultural Topics: Festivals Around the World', 13, 'Learn about global festivals and their significance in English.', NOW(), NOW()),
(57, 'Academic Writing: Structuring Essays', 14, 'Develop skills for organizing and structuring academic essays.', NOW(), NOW()),
(58, 'Research Skills for Academic Writing', 14, 'Learn how to gather and analyze information for academic purposes.', NOW(), NOW()),
(59, 'Introduction to Creative Writing', 15, 'Unleash your creativity through writing stories and poetry.', NOW(), NOW()),
(60, 'Writing Compelling Characters', 15, 'Learn how to create engaging and memorable characters in English.', NOW(), NOW()),
(61, 'Using Idioms in Context', 11, 'Practice using idioms in various contexts to improve fluency.', NOW(), NOW()),
(62, 'Expressing Opinions with Idioms', 11, 'Learn idiomatic expressions to express opinions effectively.', NOW(), NOW()),
(63, 'Business Negotiation Vocabulary', 12, 'Master vocabulary related to negotiations and deals in business.', NOW(), NOW()),
(64, 'Giving Presentations in Business', 12, 'Learn how to deliver impactful business presentations.', NOW(), NOW()),
(65, 'Cultural Topics: Art and Music', 13, 'Explore the role of art and music in different cultures.', NOW(), NOW()),
(66, 'Cultural Topics: Celebrating Diversity', 13, 'Learn to appreciate and discuss cultural diversity in English.', NOW(), NOW()),
(67, 'Academic Writing: Citation and Referencing', 14, 'Understand the importance of proper citations and referencing.', NOW(), NOW()),
(68, 'Improving Academic Vocabulary', 14, 'Expand your vocabulary for essays, reports, and research papers.', NOW(), NOW()),
(69, 'Creative Writing: Crafting Plots', 15, 'Learn techniques to structure compelling plots for stories.', NOW(), NOW()),
(70, 'Exploring Different Writing Styles', 15, 'Experiment with various writing styles to develop your voice.', NOW(), NOW());

INSERT INTO `lessons` (`id`, `title`, `chapter_id`, `description`, `created_at`, `updated_at`)
VALUES
(70, 'Exploring Different Writing Styles', 15, 'Experiment with various writing styles to develop your voice.', NOW(), NOW()),
(71, 'Understanding Idioms in Everyday Conversations', 11, 'Learn how to use idioms in common day-to-day conversations.', NOW(), NOW()),
(72, 'Mastering Figurative Language', 11, 'Understand and use figurative language to enrich your English skills.', NOW(), NOW()),
(73, 'Business Communication Basics', 12, 'Learn the essentials of communication in professional settings.', NOW(), NOW()),
(74, 'Writing Business Reports', 12, 'Develop skills for writing clear and concise business reports.', NOW(), NOW()),
(75, 'Cultural Topics: Exploring History', 13, 'Discover how history shapes cultures and learn related vocabulary.', NOW(), NOW()),
(76, 'Cultural Topics: Language and Identity', 13, 'Learn about the relationship between language and cultural identity.', NOW(), NOW()),
(77, 'Academic Writing: Writing Abstracts', 14, 'Master the art of writing concise and clear academic abstracts.', NOW(), NOW()),
(78, 'Developing Research Questions', 14, 'Learn how to frame effective research questions for academic work.', NOW(), NOW()),
(79, 'Creative Writing: Descriptive Techniques', 15, 'Enhance your creative writing skills with vivid descriptions.', NOW(), NOW());
