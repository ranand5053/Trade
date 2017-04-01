/**
 * 
 */
package com.jpmorgan.exercise.tradereport.entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * Model class to keep currency details
 * 
 * @author Rajender Anand
 *
 */
	public class Currency {

		// currency code
		private String code;

		// currency description
		private String description;

		// Default Non Working Day for the settlement of any currency is Sunday and
		// Saturday
		private List<Integer> nonWorkingDays = Arrays.asList(Calendar.SUNDAY,
				Calendar.SATURDAY);

		/**
		 * Constructs the Currency object
		 * 
		 * @param code
		 *            The Code for the currency
		 * @param desc
		 *            The description of the Currency
		 */
		public Currency(String code, String description) {
			this.code = code;
			this.description = description;
		}
		
		/**
		 * Constructs the Currency object
		 * 
		 * @param code
		 *            The Code for the currency
		 * @param desc
		 *            The description of the Currency
		 * @param nonWorkingDays
		 *            The List of non working days for the currency
		 */
		public Currency(String code, String description, List<Integer> nonWorkingDays) {
			this(code, description);
			this.nonWorkingDays = nonWorkingDays;
		}

		
		/**
		 * Gets the Currency code
		 * 
		 * @return
		 */
		public String getCode() {
			return code;
		}

		/**
		 * Set the Currency code
		 * 
		 * @param code
		 */
		public void setCode(String code) {
			this.code = code;
		}

		/**
		 * Gets the description of the currency
		 * 
		 * @return
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Set the Description of the currency
		 * 
		 * @param description
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * Get Non Working day associated with the currency
		 * 
		 * @return list 
		 */
		public List<Integer> getNonWorkingDays() {
			return nonWorkingDays;
		}

		/**
		 * Sets the List of Non Working day associated with the currency.
		 * 
		 * See Calendar.DAY_OF_WEEK for the possible days and values.
		 * 
		 * @return
		 */
		public void setNonWorkingDays(List<Integer> nonWorkingDays) {
			this.nonWorkingDays = nonWorkingDays;

		}

		/**
		 *  Returns a string representation of Currency object.
		 */
		@Override
		public String toString() {
			return "Currency [code= " + code + ", description= " + description 
					+", nonWorkingDays= " + nonWorkingDays + "]";
		}

	}
