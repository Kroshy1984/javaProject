package ru.sfedu.FatCalculator.utils;

import ru.sfedu.FatCalculator.model.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ResultUtil {
    public static Stage stageType(String x) throws Exception {
        switch(x){
            case "UNDERWEIGHT": return Stage.UNDERWEIGHT;
            case "HEAlTHY": return Stage.HEAlTHY;
            case "OVERWEIGHT": return Stage.OVERWEIGHT;
            case "OBESE": return Stage.OBESE;
            case "EXTREMELY_OBESE": return Stage.EXTREMELY_OBESE;
            default: throw new Exception("disciplineTypeParser error, incorrect discipline");
        }
    }

    public static LocalDateTime changeStringLocalDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter).atStartOfDay();
    }

    public static String changeLocalDateString(LocalDateTime date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }

    public static Date changeStringDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        return format.parse(date);
    }
    
    public static String changeDateString(Date date) throws ParseException {
        DateFormat Date = new SimpleDateFormat("dd.MM.yyyy");
        return Date.format(date);
    }

}
