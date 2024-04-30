package HW3.Try1;

enum DBField {
    STUDENT_ID("id"),
    STUDENT_NAME("first_name"),
    STUDENT_SURNAME("second_name"),
    GROUP_ID("group_id"),
    GROUP_NAME("name");
    private String name;
    DBField(String name) {this.name = name;}

    @Override
    public String toString() {return name;}
}
