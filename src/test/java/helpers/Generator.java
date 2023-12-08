package helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Класс с методами генерации случайных данных.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Generator {

    /**
     * Метод генерации случайной строки, состоящей из букв, специальных символов, цифр
     *
     * @return случайную строку
     */
    public static RandomStringGenerator getRandomStringGenerator() {
        return new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(c -> Character.isLetterOrDigit(c) || "!@#$%^&*()_+".indexOf(c) >= 0)
                .build();
    }
}
