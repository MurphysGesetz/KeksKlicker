package de.skullmc.cookie.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public final class MessageFormatter {

    public static final DecimalFormat messageFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);

    public static String format(final long number) {
        return messageFormat.format(number);
    }
}
