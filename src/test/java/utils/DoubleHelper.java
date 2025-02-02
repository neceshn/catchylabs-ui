package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleHelper {
    public static Double priceToDouble(String price) {
        String normalizedNumber = price.replaceAll(",", "");
        double number = Double.parseDouble(normalizedNumber);
        return number;
    }
}
