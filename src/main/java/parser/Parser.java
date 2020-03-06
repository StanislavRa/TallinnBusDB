package parser;

import java.sql.Date;
import java.time.LocalDate;

public class Parser {
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {

        return java.sql.Date.valueOf(dateToConvert);
    }
}
