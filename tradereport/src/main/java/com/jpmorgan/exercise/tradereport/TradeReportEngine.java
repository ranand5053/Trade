package com.jpmorgan.exercise.tradereport;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jpmorgan.exercise.tradereport.dao.TradeDAO;
import com.jpmorgan.exercise.tradereport.entity.Currency;
import com.jpmorgan.exercise.tradereport.entity.Trade;
import com.jpmorgan.exercise.tradereport.serviceaccess.ReportService;
import com.jpmorgan.exercise.tradereport.serviceaccess.ReportServiceImpl;
import com.jpmorgan.exercise.tradereport.util.ReportType;
import com.jpmorgan.exercise.tradereport.util.TradeType;

/**
 * Trade Report Engine
 * 
 * @author Rajender Anand
 *
 */
public class TradeReportEngine {
	public static void main(String[] args) {
		loadTradeData();

		ReportService reportService = new ReportServiceImpl();
		System.out.println("Report for current Date");
		reportService.generateReport(ReportType.DAILY_INCOMING_SETTLEMENT_AMOUNT);
		reportService.generateReport(ReportType.DAILY_OUTGOING_SETLLEMENT_AMOUNT);
		reportService.generateReport(ReportType.STOCK_INCOMING_RANK);
		reportService.generateReport(ReportType.STOCK_OUTGOING_RANK);
		
	}

	private static void loadTradeData() {
		
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();

		// Increment the default settlement data as +2
		c.add(Calendar.DATE, 2);

		Date settlementDate = c.getTime();
		
		List<Integer> nonWorkingDays = Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY);

		
		TradeDAO tradeDao = new TradeDAO();
		
		tradeDao.add(new Trade("bar", TradeType.SELL, 0.12, 
				new Currency("AED", "UAE Dirham", nonWorkingDays), 50, 250.5, now, settlementDate));
		tradeDao.add(new Trade("foo", TradeType.BUY, 0.5, 
				new Currency("USD", "US Dollars"), 400, 140.25, now, now));
		tradeDao.add(new Trade("X", TradeType.SELL, 0.5, 
				new Currency("AUD", "Australian dollar"), 500, 98.30, now, settlementDate));
		tradeDao.add(new Trade("Y", TradeType.BUY, 0.5, 
				new Currency("SGP", "Singapore Dollars"), 310, 98.30, now, settlementDate));
		tradeDao.add(new Trade("foo", TradeType.SELL, 0.52, 
				new Currency("GBP", "British Pound"), 15, 980, now, now));
		tradeDao.add(new Trade("X", TradeType.SELL, 0.22, 
				new Currency("AED", "UAE Dirham", nonWorkingDays), 290, 250.5, now, now));
		tradeDao.add(new Trade("bar", TradeType.SELL, 0.45, 
				new Currency("EUR", "Euro"), 800, 35.25, now, settlementDate));
		tradeDao.add(new Trade("foo", TradeType.SELL, 0.5, 
				new Currency("GBP", "British Pound"), 150, 105, now, now));
		tradeDao.add(new Trade("bar", TradeType.SELL, 0.5, 
				new Currency("EUR", "Euro"), 50, 55.25, now, settlementDate));
		tradeDao.add(new Trade("Y", TradeType.BUY, 0.20, 
				new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays), 10, 69.45, now, settlementDate));
		tradeDao.add(new Trade("Y", TradeType.SELL, 0.10, 
				new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays), 500, 38.65, now, settlementDate));
		tradeDao.add(new Trade("X", TradeType.BUY, 0.51, 
						new Currency("USD", "US Dollars"), 600, 65.85, now, settlementDate));
		tradeDao.add(new Trade("bar", TradeType.BUY, 0.25, 
				new Currency("AUD", "Australian dollar"), 60, 25, now, now));
		tradeDao.add(new Trade("bar", TradeType.BUY, 0.60, 
						new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays), 250, 35, now, now));
		tradeDao.add(new Trade("Y", TradeType.SELL, 0.20, 
						new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays), 20, 88.65, now, settlementDate));

		tradeDao.display();
	}
}
