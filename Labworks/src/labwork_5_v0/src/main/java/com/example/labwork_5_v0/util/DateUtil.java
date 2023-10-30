package com.example.labwork_5_v0.util;

//Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

  /**
   * Шаблон даты, используемый для преобразования
   */
  private static final String DATE_PATTERN = "dd.MM.yyyy";
  /**
   * Форматировщик даты.
   */
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
    DATE_PATTERN
  );

  /**
     * Возвращает полученную дату в виде отформатированной строки.
     * Используется определённый выше {@link
    DateUtil#DATE_PATTERN}.
     *
     * @param date - дата, которая будет возвращена в виде строки
     * @return отформатированную строку
     */
  public static String format(LocalDate date) {
    if (date == null) return null;
    return DATE_FORMATTER.format(date);
  }

  /**
     * Преобразует строку, которая отформатирована по правилам
     * шаблона {@link DateUtil#DATE_PATTERN} в объект {@link
    LocalDate}.
     * <p>
     * Возвращает null, если строка не может быть преобразована.
     *
     * @param dateString - дата в виде String
     * @return объект даты или null, если строка не может быть
    преобразована
     */
  public static LocalDate parse(String dateString) {
    try {
      return DATE_FORMATTER.parse(dateString, LocalDate::from);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  /**
   * Проверяет, является ли строка корректной датой
   *
   * @param dateString
   * @return true, если строка является корректной датой
   */
  public static boolean validDate(String dateString) {
    // Пытаемся разобрать строку
    return DateUtil.parse(dateString) != null;
  }
}
