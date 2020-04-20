package util;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

public class Parser {
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {

        return java.sql.Date.valueOf(dateToConvert);
    }

    public String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public Time getTimeFromString(String time) {

        String format = "HH:mm";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long ms = 0;
        try {
            ms = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time t = new Time(ms);
        return t;
    }
}
