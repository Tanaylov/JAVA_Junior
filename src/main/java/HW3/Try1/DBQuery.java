package HW3.Try1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQuery {
    public static List<Student> getStudent(String query) {
        List<Student> students = new ArrayList<>(100);
        try (Connection connection = DBInit.getConnection();
            Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(DBField.STUDENT_ID.toString());
                String name = resultSet.getString(DBField.STUDENT_NAME.toString());
                String surname = resultSet.getString(DBField.STUDENT_SURNAME.toString());
                int idGroup = resultSet.getInt(DBField.GROUP_ID.toString());
                students.add(new Student(id, name, surname, idGroup));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
