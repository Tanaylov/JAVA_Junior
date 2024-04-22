package HW2.RandomDate;

import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateByYMDGenerator {

    public static void generate(Object o) {
        Arrays.stream(o.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(RandomDateByYMD.class) && !field.toString().contains("final"))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        int year = ThreadLocalRandom.current().nextInt(
                            Integer.parseInt(field.getAnnotation(RandomDateByYMD.class).min().split("-")[0]),
                            Integer.parseInt(field.getAnnotation(RandomDateByYMD.class).max().split("-")[0]));
                        int month = ThreadLocalRandom.current().nextInt(
                            Integer.parseInt(field.getAnnotation(RandomDateByYMD.class).min().split("-")[1]),
                            Integer.parseInt(field.getAnnotation(RandomDateByYMD.class).max().split("-")[1]));
                        int day = ThreadLocalRandom.current().nextInt(
                            Integer.parseInt(field.getAnnotation(RandomDateByYMD.class).min().split("-")[2]),
                            Integer.parseInt(field.getAnnotation(RandomDateByYMD.class).max().split("-")[2]));
                        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, 0, 0, 0);

                        switch (field.getType().getSimpleName()) {
                            case "LocalDate" -> field.set(o, LocalDate.of(year, month, day));
                            case "Instant" -> field.set(o,
                                    Instant.ofEpochMilli(
                                            ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
                                                    .toInstant()
                                                    .toEpochMilli()));
                            case "Date" -> field.set(o, Date.from(Instant.ofEpochMilli(
                                    ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
                                            .toInstant()
                                            .toEpochMilli())));
                        }
                    } catch (Exception e) {
                        System.err.println("Something wrong");
                    }
                });
    }
}
