package HW3.Try2;
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
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:homework");
            Statement statement = connection.createStatement()){
            statement.execute("""
                    CREATE TABLE student(
                        id BIGINT,
                        first_name VARCHAR(256),
                        second_name VARCHAR(256),
                        group_name VARCHAR(256)
                    );
                    """);
            statement.executeUpdate("""
                    INSERT INTO student(id, first_name, second_name, group_name) VALUES
                    (1, 'Ivan', 'Tanajlov', 'IT'),
                    (2, 'Sergey', 'Hutrov', 'Biology'),
                    (3, 'Svetlana', 'Ershova', 'Menegment'),
                    (4, 'Vladislav', 'Petrov', 'Engineering'),
                    (5, 'Elena', 'Dubova', 'IT')
                    """);
            ResultSet rs = statement.executeQuery("""
                    Select id, first_name, second_name, group_name FROM student;
                    """);
            while (rs.next())
                System.out.printf("Student ID: %d, name: %s, surname: %s, group name: %s",
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("group_name")
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
