/**
 * 
 */
package com.jpmorgan.exercise.tradereport.util;


/**
 * Enumeration type representing all different trade reports.
 * 
 * @author Rajender Anand
 *
 */
public enum ReportType {

	// Daily Sell/Incoming report
	DAILY_INCOMING_SETTLEMENT_AMOUNT,
	// Daily  Buy/Outgoing report
	DAILY_OUTGOING_SETLLEMENT_AMOUNT,
	// Stock rank report for the Sell/Incoming trades
	STOCK_INCOMING_RANK,
	// Stock rank report for the Buy/Outgoing Trades
	STOCK_OUTGOING_RANK

}
