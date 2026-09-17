package util;

import java.time.format.DateTimeFormatter;

public class DateUtils {
    public void dateFormat(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
}