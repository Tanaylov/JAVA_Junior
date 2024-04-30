package HW3.Try1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Повторить все, что было на семниаре на таблице Student с полями
 * 1. id - bigint
 * 2. first_name - varchar(256)
 * 3. second_name - varchar(256)
 * 4. group - varchar(128)
 * <p>
 * Написать запросы:
 * 1. Создать таблицу
 * 2. Наполнить таблицу данными (insert)
 * 3. Поиск всех студентов
 * 4. Поиск всех студентов по имени группы
 * <p>
 * Доп. задания:
 * 1. ** Создать таблицу group(id, name); в таблице student сделать внешний ключ на group
 * 2. ** Все идентификаторы превратить в UUID
 */
public class DBInit {
    private static final String URL = "jdbc:h2:mem:myDB;INIT=RUNSCRIPT FROM 'src/main/resources/init.sql'";
    private static final String LOGIN = "Ivan";
    private static final String PASS = "";


    public static Connection getConnection() throws SQLException {return DriverManager.getConnection(URL, LOGIN, PASS);}

    private static void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
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
                                        
                    CREATE TABLE group_name(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(256)
                        -- FOREIGN KEY(id) REFERENCES student(group_id)
                    );                                                                                
                    """
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
