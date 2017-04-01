package com.jpmorgan.exercise.tradereport.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmorgan.exercise.tradereport.entity.Currency;
import com.jpmorgan.exercise.tradereport.entity.Trade;
import com.jpmorgan.exercise.tradereport.util.DateUtil;

/**
 * This class will hold all the reported trades 
 *  in map based on the actual settlement Date.
 * 
 * @author Rajender Anand
 *
 */

public class TradeDAO {

	private static Map<String, List<Trade>> inMemCache = new HashMap<String, List<Trade>>();

	
	/**
	 * Add the trade to the repository map based on the actual settlement date
	 * 
	 * @param trade
	 */
	public void add(Trade trade) {
		Date settlementDate = getSettlementDate(trade);
		String settlementDateStr = DateUtil.format(settlementDate);
		if (inMemCache.get(settlementDateStr) != null) {
			List<Trade> tradeList = inMemCache.get(settlementDateStr);
			tradeList.add(trade);
		} else {
			List<Trade> tradeList = new ArrayList<Trade>();
			tradeList.add(trade);
			inMemCache.put(settlementDateStr, tradeList);
		}
	}
	
	
	/**
	 * This method will calculate the Settlement Date based on the traded
	 * currency working days.
	 * 
	 * @param trade
	 *            The trade Object for which the settlement date needs to be
	 *            calculated.
	 * @return <code> Date <Code> The valid settlement date of the trade based on it's currency of trade
	 */

	private Date getSettlementDate(Trade trade) {
		Currency currency = trade.getCurrency();
		Date settlementDate = trade.getSettlementDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(settlementDate);
		int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);
		while (currency.getNonWorkingDays().contains(dayofWeek)) {
			calendar.add(Calendar.DATE, 1);
			dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);
		}
		Date newSettlementDate = calendar.getTime();
		return newSettlementDate;
	}

	/**
	 * This method will returns all the trades associated with the passed
	 * settlement date.
	 * 
	 * @param settlementDate
	 *            The Settlement Date.
	 * @return List<Trade> The list of trades associated with the settlement
	 *         date if any, otherwise empty List of trades
	 */
	public List<Trade> getTradesBySettlementDate(Date settlementDate) {
		List<Trade> tradeList = new ArrayList<Trade>();
		if (settlementDate != null) {
			String settlementDateStr = DateUtil.format(settlementDate);
			if (inMemCache.containsKey(settlementDateStr)) {
				if (inMemCache.get(settlementDateStr) != null) {
					tradeList = inMemCache.get(settlementDateStr);
				}
			}
		}
		return tradeList;
	}
	
	/**
	 * Display the Trade repository by Actual Settlement Date
	 */
	public void display() {
			inMemCache.forEach((k,v) -> {
			   System.out.println(" Settlement Date :-> "+k.toString());
			   v.forEach(trade-> System.out.println(trade));
			  });
	}
}


