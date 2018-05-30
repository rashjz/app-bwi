package rashjz.info.app.bwi.appbwi.utils;

import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.util.Objects.isNull;

public class DateUtils {
    private DateUtils() {
    }

    public static LocalDate toLocalDate(String str) {
        if ((str == null) ||
                "null".equals(str) ||
                "00000000".equals(str))
            return null;
        val format = str.contains("-") ?
                DateTimeFormatter.ISO_DATE :
                DateTimeFormatter.BASIC_ISO_DATE;
        return LocalDate.parse(str, format);
    }

    public static Date toLocalDate(Object o) {
        if (isNull(o)) {
            return new Date();
        } else {
            return (Date) o;
        }
    }

}
