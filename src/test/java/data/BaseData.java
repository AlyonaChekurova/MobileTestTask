package data;

/**
 * Интерфейс с общими значениями
 */
public interface BaseData {
    /**
     * Ожидание для прелоадера
     */
    int PROGRESS_BAR_TIMEOUT = 120000;
    String DRIVER_NOT_EXIST_ERROR = "Driver not exist";
    String DRIVER_NOT_CREATED_ERROR = "Driver not created";
    String ELEMENT_NOT_PRESENT_ERROR = "Элемент %s отсутствует на экране";
    String ELEMENT_PRESENT_ERROR = "Элемент %s присутствует на экране";
    String AFTER_WAITING_TIME_SUFFIX = " после ожидания %s с";
    String TEXTS_NOT_EQUAL_ERROR = "В элементе - '%s' некорректный текст.\n" + "Фактическое значение '%s'. Ожидаемое - '%s'";
    String INCORRECT_DATE_FORMAT = "Неправильный формат даты в элементе %s";
    String SOME_STRING = "abc!@#123";
    String ELEMENT_LIST_CONTAINS_TEXT_ERROR = "%s содержит элемент %s без ввденного текста";
    String ELEMENT_LIST_SIZE_ERROR = "Размер %s не совпадает с ожидаемым";
}
