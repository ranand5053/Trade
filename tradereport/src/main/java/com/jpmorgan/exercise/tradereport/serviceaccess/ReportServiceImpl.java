/**
 * 
 */
package com.jpmorgan.exercise.tradereport.serviceaccess;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmorgan.exercise.tradereport.dao.TradeDAO;
import com.jpmorgan.exercise.tradereport.entity.Trade;
import com.jpmorgan.exercise.tradereport.util.ReportType;
import com.jpmorgan.exercise.tradereport.util.TradeType;

/**
 * 
 * Implementing ReportService interface.
 * 
 * @author Rajender Anand
 *
 */
public class ReportServiceImpl implements ReportService {


	/**
	 * Generates the trade report for given report type with
	 * today settlement date.
	 * 
	 * 
	 * @param reportType
	 *            enum representing the report type
	 * @exception InvalidReportType
	 */
	public void generateReport(ReportType reportType) {
		generateReport(reportType, new java.util.Date());
	}

	/**
	 * It generates the trade report based on the passed report type
	 *  and settlement date.
	 * 
	 * If the no report type is passed the default report will be generated for
	 * INCOMING_SETTLEMENT_AMOUNT.
	 * 
	 * @param reportType
	 *            The enumeration representing the report type
	 * @exception InvalidReportTypeException
	 * 
	 */

	public void generateReport(ReportType reportType, Date reportDate) {
		TradeDAO dao = new TradeDAO();
		List<Trade> tradeList = dao.getTradesBySettlementDate(reportDate);
		
		if (reportType == null) {
			throw new IllegalArgumentException("Report type cannot be null.");
		}
		switch (reportType) {
		case DAILY_INCOMING_SETTLEMENT_AMOUNT:
			displayIncomingTradeReport(reportDate, tradeList);
			break;
		case DAILY_OUTGOING_SETLLEMENT_AMOUNT:
			displayOutgoingTradeReport(reportDate, tradeList);
			break;
		case STOCK_INCOMING_RANK:
			displayIncomingTradeReportByEntity(reportDate, tradeList);
			break;
		case STOCK_OUTGOING_RANK:
			displayOutgoingTradeReportByEntity(reportDate, tradeList);
			break;
		}

	}

	private void displayOutgoingTradeReport(Date reportDate,
			List<Trade> tradeList) {
		List<Trade> buyTradeList = filterTradeListByType(tradeList, TradeType.BUY);
		System.out.println("Buy Settlement Trade report");
		System.out.println("Settlement Date :" + reportDate.toString());
		System.out.println("Total Outgoing Amount in USD :" 
				+ getTradesTotalInUSD(buyTradeList));
		System.out.println("=======================================================");

	}

	/**
	 * @param reportDate
	 * @param tradeList
	 */
	private void displayIncomingTradeReport(Date reportDate,
			 List<Trade> tradeList) {
		List<Trade> sellTradeList = filterTradeListByType(tradeList,
				TradeType.SELL);

		System.out.println("Sell Settlement Trade report");
		System.out.println("Settlement Date :" + reportDate.toString());
		System.out.println("Total Amount in USD :"
				+ getTradesTotalInUSD(sellTradeList));
		System.out.println("=======================================================");
	}
	
	private void displayOutgoingTradeReportByEntity(Date reportDate,
			List<Trade> tradeList) {
		List<Trade> buyTradeList = filterTradeListByType(tradeList,
				TradeType.BUY);
		Map<String, Double> entityRankMap = getEntityMapByRank(buyTradeList);

		System.out.println("Buy Settlement Trade report by Entity Rank");
		System.out.println("Settlement Date :" + reportDate.toString());
		entityRankMap.forEach((k,v) -> {
			   System.out.println("Entity : " + k + ", Total Amount in USD : "
			     + v);
		});
		System.out.println("=======================================================");
	}

	private void displayIncomingTradeReportByEntity(Date reportDate,
			List<Trade> tradeList) {
		List<Trade> filterTradeList = filterTradeListByType(tradeList,
				TradeType.SELL);

		Map<String, Double> entityRankMap = getEntityMapByRank(filterTradeList);

		System.out.println("Sell Settlement Trade report by Entity Rank");
		System.out.println("Trade Settlement Date : " + reportDate.toString());
		entityRankMap.forEach((k,v) -> {
			   System.out.println("Entity : " + k + ", Total Amount in USD : "
			     + v);
		});
		System.out.println("=======================================================");
	}

	

	/**
	 * This method will filter the trades based on the trade type passed.
	 * 
	 * @param tradeList
	 *            The List of trades which needs to be filtered
	 * @param tradeType
	 *            The tradeType used for filtering the trades
	 * @return <code>List<Trades><code> The list of trades matching the trade type.
	 */
	public List<Trade> filterTradeListByType(List<Trade> tradeList,
			TradeType tradeType) {
		return tradeList.stream().filter(t -> t.getTradeType() == tradeType)
				.collect(Collectors.<Trade> toList());

	}

	/**
	 * This method calculates the Total amount of the trades based on the
	 * FxPrice, Quantity and Units
	 * 
	 * @param tradeList
	 *            The list of trade objects from which the total USD amount
	 *            needs to be calculated
	 * @return <code> double <code> all trades total USD amount.
	 */
	public double getTradesTotalInUSD(List<Trade> tradeList) {
		double result = 0;
		if (tradeList != null) {
			return tradeList.stream().mapToDouble(t -> t.getAmountinUSD())
					.sum();
		}
		return result;
	}

	/**
	 * This method will return a LinkedHashMap containing the entity as the key
	 * and total USD traded amount for that entity in the decreasing order of
	 * the total USD amount.
	 * 
	 * @param tradeList
	 * @return
	 */
	public LinkedHashMap<String, Double> getEntityMapByRank(
			List<Trade> tradeList) {
		LinkedHashMap<String, Double> entityRankMap = new LinkedHashMap<String, Double>();
		Map<String, Double> tradeMap = tradeList.stream().collect(
				Collectors.groupingBy(Trade::getEntity,
						Collectors.summingDouble(Trade::getAmountinUSD)));

		tradeMap.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Double> comparingByValue()
						.reversed())
				.forEachOrdered(
						entry -> entityRankMap.put(entry.getKey(),
								entry.getValue()));
		return entityRankMap;
	}

}
