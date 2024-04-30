CREATE TABLE group_name(
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256)
);

CREATE TABLE student(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256),
    second_name VARCHAR(256),
    group_id INT
);

INSERT INTO student(first_name, second_name, group_id) VALUES('Ivan', 'Tanajlov', 1);
INSERT INTO student(first_name, second_name, group_id) VALUES('Sergey', 'Hutrov', 2);
INSERT INTO student(first_name, second_name, group_id) VALUES('Svetlana', 'Ershova', 3);
INSERT INTO student(first_name, second_name, group_id) VALUES('Vladislav', 'Petrov', 2);
INSERT INTO student(first_name, second_name, group_id) VALUES('Elena', 'Dubova', 1);

INSERT INTO group_name(name) VALUES('IT');
INSERT INTO group_name(name) VALUES('Management');
INSERT INTO group_name(name) VALUES('Biology');

