/**
 * 
 */
package com.jpmorgan.exercise.tradereport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * An utility class to handle date formats.
 * 
 * @author Rajender Anand
 *
 */
public class DateUtil {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
	
	/**
	 * Parse the given string to create Date.
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date parse(String date) {
		try {
			return dateFormat.parse(date);
		} catch(ParseException pe) {
			throw new IllegalArgumentException("Exception in formatDate Invalid Date String. Should be in dd-MMM-YYYY format only");
		}
	}
	
	/**
	 * Formats the given date and return in String format "dd-MMM-YYYY"
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return dateFormat.format(date);
	}
	
	
}
