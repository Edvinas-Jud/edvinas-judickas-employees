package org.example.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate convertDate(String date) {
        if (date.equalsIgnoreCase("NULL")) {
            return LocalDate.now();
        }
        return LocalDate.parse(date, dateFormatter);
    }

    public static LocalDateTime convertDateTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date, dateTimeFormatter), LocalTime.of(0, 0, 0));
    }


}
