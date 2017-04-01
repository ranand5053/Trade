/**
 * 
 */
package com.jpmorgan.exercise.tradereport.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmorgan.exercise.tradereport.dao.TradeDAO;
import com.jpmorgan.exercise.tradereport.entity.Currency;
import com.jpmorgan.exercise.tradereport.entity.Trade;
import com.jpmorgan.exercise.tradereport.serviceaccess.ReportServiceImpl;
import com.jpmorgan.exercise.tradereport.util.TradeType;

/**
 * 
 * Test class for ReportServiceImpl
 * 
 * @author Rajender Anand
 *
 */
public class ReportServiceTest {
	

	/** Reporting service instance */
	private ReportServiceImpl reportService = new ReportServiceImpl();

	@BeforeClass
	public static void setUP() {
		TradeDAO dao = new TradeDAO();
		List<Integer> nonWorkingDays = Arrays.asList(Calendar.FRIDAY,
				Calendar.SATURDAY);

		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		// Add the default settlement data as +1
		c.add(Calendar.DATE, 1);

		Date settlementDate = c.getTime();
		dao.add(new Trade("foo", TradeType.BUY, 0.5,
				new Currency("SGP", "Singapore Dollars"), 200, 100, now,
				settlementDate));
		dao.add(new Trade("Y", TradeType.SELL, 0.20,
				new Currency("AED", "UAE Dirham", nonWorkingDays) ,450, 150, now,
				now));
		dao.add(new Trade("foo", TradeType.BUY, 0.5,
				new Currency("USD", "US Dollars"), 200, 200, now,
				now));
		dao.add(new Trade("Z", TradeType.SELL, 0.5,
				new Currency("GBP", "British Pound"), 200, 250, now,
				now));
		dao.add(new Trade("bar", TradeType.BUY, 0.5,
				new Currency("AUD", "Australian dollar"), 200, 180,now,
				now));
		dao.add(new Trade("M", TradeType.SELL, 0.5,
				new Currency("EUR", "Euro"), 200, 100.25, settlementDate,
				settlementDate));
		dao.add(new Trade("Y", TradeType.BUY, 0.20,
				new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays) ,300, 150, now,
				now));
		dao.add(new Trade("foo", TradeType.SELL, 0.10,
				new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays), 10, 150, now, settlementDate));
		dao.add(new Trade("bar", TradeType.BUY, 0.60,
				new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays) , 10, 150, now,
				now));
		dao.add(new Trade("foo", TradeType.SELL, 0.20,
				new Currency("SAR", "Saudi Arab Riyal", nonWorkingDays), 300, 150, now, now));
		
		dao.display();

	}

	@Test
	public void testFilterBuy() {
		TradeDAO dao = new TradeDAO();
		List<Trade> tradeList = dao.getTradesBySettlementDate(new Date());
		List<Trade> filterTradeList = reportService.filterTradeListByType(
				tradeList, TradeType.BUY);
		Assert.assertEquals(4, filterTradeList.size());

	}

	@Test
	public void testFilterSell() {
		TradeDAO dao = new TradeDAO();
		List<Trade> tradeList = dao.getTradesBySettlementDate(new Date());
		List<Trade> filterTradeList = reportService.filterTradeListByType(
				tradeList, TradeType.SELL);
		Assert.assertEquals(3, filterTradeList.size());

	}

	@Test
	public void testEntityRankBuy() {
		TradeDAO dao = new TradeDAO();
		List<Trade> tradeList = dao.getTradesBySettlementDate(new Date());
		List<Trade> filterTradeList = reportService.filterTradeListByType(
				tradeList, TradeType.BUY);
		LinkedHashMap<String, Double> entityRankMap = reportService
				.getEntityMapByRank(filterTradeList);
		Assert.assertEquals(3, entityRankMap.size());
		Assert.assertEquals(3, entityRankMap.size());
		Assert.assertTrue(entityRankMap.containsKey("foo"));
		Assert.assertEquals(20000.0, entityRankMap.get("foo"), 0.0);
		Assert.assertTrue(entityRankMap.containsKey("bar"));
		Assert.assertEquals(18900.0, entityRankMap.get("bar"), 0.0);
		Assert.assertTrue(entityRankMap.containsKey("Y"));
		Assert.assertEquals(9000.0, entityRankMap.get("Y"), 0.0);

	}

	@Test
	public void testEntityRankSell() {
		TradeDAO dao = new TradeDAO();
		List<Trade> tradeList = dao.getTradesBySettlementDate(new Date());
		List<Trade> filterTradeList = reportService.filterTradeListByType(
				tradeList, TradeType.SELL);
		LinkedHashMap<String, Double> entityRankMap = reportService
				.getEntityMapByRank(filterTradeList);
		Assert.assertEquals(3, entityRankMap.size());
		Assert.assertTrue(entityRankMap.containsKey("Z"));
		Assert.assertEquals(25000.0, entityRankMap.get("Z"), 0.0);
		Assert.assertTrue(entityRankMap.containsKey("Y"));
		Assert.assertEquals(13500.0, entityRankMap.get("Y"), 0.0);
		Assert.assertTrue(entityRankMap.containsKey("foo"));
		Assert.assertEquals(9000.0, entityRankMap.get("foo"), 0.0);

	}


}
