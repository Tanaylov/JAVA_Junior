package HW2.RandomDate;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenerator {
    public static void dateGenerator(Object o) {
        Arrays.stream(o.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(RandomDate.class))
                .filter(field -> !field.toString().contains("final"))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        long date = ThreadLocalRandom.current().nextLong(field.getAnnotation(RandomDate.class).min(),
                                field.getAnnotation(RandomDate.class).max());
                        switch (field.getType().getSimpleName()) {
                            case "Date" -> field.set(o,
                                    Date.from(Instant.ofEpochSecond(date)));
                            case "Instant" -> field.set(o,
                                    Instant.ofEpochSecond(date));
                            case "LocalDate" -> field.set(o,
                                    LocalDate.ofInstant(Instant.ofEpochSecond(date), ZoneId.systemDefault()));
                        }
                    } catch (Exception e) {
                        System.err.println("Illegal argument");
                    }
                });
    }
}
