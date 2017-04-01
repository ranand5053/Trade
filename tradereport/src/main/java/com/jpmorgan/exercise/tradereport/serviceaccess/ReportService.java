/**
 * 
 */
package com.jpmorgan.exercise.tradereport.serviceaccess;

import java.util.Date;

import com.jpmorgan.exercise.tradereport.util.ReportType;

/**
 * 
 * Report service interface to generate different type of reports.
 * 
 * @author Rajender Aannd
 *
 */
public interface ReportService {

	/**
	 * Generates the trade report for the given Report type with
	 * settlement date as today date.
	 * 
	 * @param reportType
	 *            enum representing report type
	 */
	void generateReport(ReportType reportType);

	/**
	 * Generates the trade report for the given Report type and
	 * settlement date.
	 * 
	 * @param reportType
	 *            enum representing report type
	 */
	void generateReport(ReportType reportType, Date reportDate);

}
