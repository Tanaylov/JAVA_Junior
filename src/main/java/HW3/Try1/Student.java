package HW3.Try1;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final long ID;
    private final String FIRST_NAME;
    private final String SECOND_NAME;
    private int group_id;

    private static List<Student> studentList = new ArrayList<>();

    public Student(long ID, String FIRST_NAME, String SECOND_NAME, int group_id) {
        this.ID = ID;
        this.FIRST_NAME = FIRST_NAME;
        this.SECOND_NAME = SECOND_NAME;
        this.group_id = group_id;
    }

    //region getters
    public long getID() {
        return ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getSECOND_NAME() {
        return SECOND_NAME;
    }

    public int getGroup_id() {
        return group_id;
    }

    public static List<Student> getStudentList() {return studentList;}
    //endregion getters

    //region setters

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    //endregion setters

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Surname: %s, Group ID: %d", ID, FIRST_NAME, SECOND_NAME, group_id);
    }
}
