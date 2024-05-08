package HW4;

import HW4.Entity.Student;

import java.util.List;

public class Start {
    public static void main(String[] args) {
//        StudentHelper.addStudent("Lorry");
//        StudentHelper.updateStudentName("Sergey", "Kirill", 2L);
//        System.out.println(StudentHelper.getStudentByID(2L));
//        System.out.println(StudentHelper.deleteStudent("Fill", 5L));
        List<Student> students = StudentHelper.getStudents();
        System.out.println(students);


        HibernateUtil.close();
    }
}
