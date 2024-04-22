package HW2;

import HW2.RandomDate.RandomDate;
import HW2.RandomDate.RandomDateByYMD;
import HW2.RandomDate.RandomDateByYMDGenerator;
import HW2.RandomDate.RandomDateGenerator;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        //class fields:
        DateTest dateTest = DateTest.class.getConstructor().newInstance();
//        System.out.println(dateTest.getClass().getDeclaredField("localDate"));
//        RandomDateGenerator.dateGenerator(dateTest);
        RandomDateByYMDGenerator.generate(dateTest);
        System.out.println(DateTest.date);
        System.out.println(DateTest.instantDate);
        System.out.println(DateTest.localDate);
        System.out.println(dateTest.ObjectDate);
        System.out.println(dateTest.ObjectInstantDate);
        System.out.println(dateTest.ObjectLocalDate);

    }

    private static class DateTest {
        @RandomDateByYMD
        private static Date date = new Date(LocalDate.now().toEpochDay());
        @RandomDateByYMD
        private static Instant instantDate = Instant.MAX;
        @RandomDateByYMD(min = "2000-10-25")
        private static LocalDate localDate = LocalDate.of(2024, 9, 25);
        @RandomDate
        private Date ObjectDate;
        @RandomDate
        private Instant ObjectInstantDate;
        @RandomDate
        private LocalDate ObjectLocalDate;


        public DateTest() {}

        public DateTest(Date objectDate, Instant objectInstantDate, LocalDate objectLocalDate) {
            ObjectDate = objectDate;
            ObjectInstantDate = objectInstantDate;
            ObjectLocalDate = objectLocalDate;
        }


    }
}
