package HW2.RandomDate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RandomDateByYMD {
    String min() default "2024-01-01";
    String max() default "2025-12-31";
}
