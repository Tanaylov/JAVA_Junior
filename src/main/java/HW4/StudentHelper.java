package HW4;

import HW4.Entity.Student;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentHelper {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public static List<Student> getStudents() {
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> cq = cb.createQuery(Student.class);

            Root<Student> root = cq.from(Student.class);
            cq.select(root);

            Query query = session.createQuery(cq);

            List<Student> students = query.getResultList();

            session.close();

            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Student getStudentByID(Long id) {
        try (Session session = sessionFactory.openSession()){
            return session.find(Student.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void addStudent(String studentName) {
        Student newStudent1 = new Student();
        newStudent1.setName(studentName);

        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            session.persist(newStudent1);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void updateStudentName(String newName, String oldName, Long id) {
        try (Session s = sessionFactory.openSession()){
            boolean update = false;
            for (Student student : getStudents()) {
                if (student.getName().equals(oldName) && student.getId().equals(id)) {
                    student.setName(newName);
                    update = true;
                    s.getTransaction().begin();
                    s.merge(student);
                    s.getTransaction().commit();
                }
            }
            if (!update) System.out.println("There is no name " + oldName + " and id " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Student deleteStudent(String name, Long id) {
        try (Session s = sessionFactory.openSession()){
            for (Student student : getStudents()) {
                if (student.getName().equals(name) && student.getId().equals(id)) {
                    s.getTransaction().begin();
                    s.remove(student);
                    s.getTransaction().commit();
                    return student;
                }
            }
            System.out.println("There is no name " + name + " and id " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
