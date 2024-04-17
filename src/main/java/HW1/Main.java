package HW1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>(10);
        for (int i = 1; i != 10; i++) {
            departments.add(new Department("Department #" + i));
        }

        List<Person> people = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            people.add(new Person("Person #" + (i + 1),
                    ThreadLocalRandom.current().nextInt(20, 65),
                    ThreadLocalRandom.current().nextInt(20_000, 100_000),
                    departments.get(ThreadLocalRandom.current().nextInt(departments.size()))));
        }

//        people.stream()
//                .limit(1)
//                .forEach(System.out::println);

        //From seminar:
//        printPersons(people);
//
//        Person person = getHighestSalary5D(people);
//        System.out.println(person);

        //Homework:
//        System.out.println(countPersons(people, 50, 500000.0));
//        System.out.println(averageSalary(people, 55).get());
//        System.out.println(groupByDepartment(people));
//        System.out.println(maxSalaryByDepartment(people));
//        System.out.println(groupPersonNamesByDepartment(people));
        System.out.println(minSalaryPersons(people));
    }
        //Find number of persons, which age over 'x' and salary over 'd'
    static int countPersons(List<Person> persons, int x, double d) {
        try {
            return (int) persons.stream()
                    .filter(person -> person.getAge() > x)
                    .filter(person -> person.getSalary() > d)
                    .count();
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException();
        }
    }

        //Find average salary of persons, who work in Department 'x'
        /**
         * Вовремя написания, ещё не досмотрел семинар до конца, а у самого не получилось докопаться до соответствующих возможностей.
         * В итоге 2 последующих метода сделал, как сделал. Переписывать не стал.
         */
    static Optional<Double> averageSalary(List<Person> persons, int x) {
        // TODO: Реализовать метод
        List<Double> salary = persons.stream()
                .filter(person -> person.getDepartment().getName().contains(Integer.toString(x)))
                .map(Person::getSalary)
                .toList();
        if (salary.size() == 0) return Optional.of(0.0);
        return Optional.of(salary.stream().reduce(Double::sum).get() / salary.size());
    }

        //Group persons by departments
    static Map<Department, List<Person>> groupByDepartment(List<Person> persons) {
        try {
            Set<Department> departments = persons.stream().map(Person::getDepartment).collect(Collectors.toSet());
            Map<Department, List<Person>> result = new HashMap<>(departments.size());
            departments.forEach(department -> {
                result.put(department, persons.stream()
                        .filter(person -> person.getDepartment().equals(department))
                        .toList());
            });
            return result;
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException();
        }
    }

        //Find MAX salary by department
    static Map<Department, Double> maxSalaryByDepartment(List<Person> persons) {
        if (persons.isEmpty() || persons == null) return null;
        return persons.stream()
                .collect(Collectors.toMap(Person::getDepartment, Person::getSalary, Math::max));
    }

        //Group persons' name by department
    static Map<Department, List<String>> groupPersonNamesByDepartment(List<Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy
                        (Person::getDepartment, Collectors.mapping(Person::getName, Collectors.toList())));
    }

        //Find person with MIN salary in department
    static List<Person> minSalaryPersons(List<Person> persons) {
        Collection<Double> salaryMin = persons.stream()
                .collect(Collectors.toMap(Person::getDepartment, Person::getSalary, Math::min)).values();
        return persons.stream()
                .filter(person -> salaryMin.contains(person.getSalary()))
                .toList();
    }

    //region Seminar:
        //Print person's name, which age over than 40:
    static void printPersons(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparingInt(p -> p.age))
                .dropWhile(person -> person.getAge() <= 40)
                .map(person -> person.getName())
                .forEach(System.out::println);
    }

        //Print person with the highest salary in fifth Department:
    static Person getHighestSalary5D(List<Person> people) {
        return people.stream()
                .filter(person -> person.getDepartment().getName().contains("5"))
                .max(Comparator.comparingDouble(Person::getSalary))
                .get();
    }
    //endregion Seminar:

    private static class Person {
        //region parameters:
        private String name;
        private int age;
        private double salary;
        private Department department;
        //endregion parameters:

        public Person() {}
        public Person(String name, int age, double salary, Department department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }
        //region getters:
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        public Department getDepartment() {
            return department;
        }
        //endregion getters:
        //region setters:

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }
        //endregion setters:

        @Override
        public String toString() {
            return "name: " + name +
                    ", age: " + age +
                    ", salary: " + salary +
                    " " + department + ";";
        }
    }

    private static class Department {
        String name;

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return " -- \'" + name + '\'';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Department that = (Department) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
