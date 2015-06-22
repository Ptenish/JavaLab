package Konstantin.Aminev.inb.ch.makery.adress.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ADateUtilK {
	private static final String DATE_PATTERN = "dd.VK.yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_PATTERN);

	public static String aformatK(LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	public static LocalDate aparseK(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static boolean avalidDateMK(String dateString) {
		return ADateUtilK.aparseK(dateString) != null;
	}
}