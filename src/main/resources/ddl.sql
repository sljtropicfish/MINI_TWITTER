CREATE TABLE User(
    id int(11) NOT NULL, AUTO_INCREMENT, PRIMARY KEY,
    first_name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
)

CREATE TABLE `Friendship` (
  `follower_id` int(11) NOT NULL,
  `followee_id` int(11) NOT NULL,
  PRIMARY KEY (follower_id, followee_id),
  CONSTRAINT `Friendship_ibfk_1` FOREIGN KEY (`follower_id`)
        REFERENCES `User` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Student_Course_ibfk_2` FOREIGN KEY (`followee_id`)
        REFERENCES `User` (`id`) ON DELETE CASCADE
);

CREATE TABLE `Tweet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT Tweet_fk_1 FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE CASCADE
)