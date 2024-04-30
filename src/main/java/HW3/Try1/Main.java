package HW3.Try1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(DBQuery.getStudent("SELECT id, first_name, second_name, group_id FROM student"));
        System.out.println(DBQuery.getStudent("SELECT id, first_name, second_name, group_id FROM student WHERE group_id = 1"));
    }
}
