/**
 * 
 */
package com.jpmorgan.exercise.tradereport.entity;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.exercise.tradereport.util.TradeType;


/**
 * 
 * Test class for Trade.
 * 
 * @author Rajender Anand
 *
 */
public class TradeTest {

	@Test
	public void testTradeUSDAmount() {
		Date now = Calendar.getInstance().getTime();
		Trade trade = new Trade("foo", TradeType.BUY, 1.5, new Currency("SGP",
				"Singapore Dollars"), 50, 100.00, now,now );
		Assert.assertEquals(7500.0, trade.getAmountinUSD(), 0.0);
	}

	@Test
	public void testTradeUSDAmountNeg() {
		Date now = Calendar.getInstance().getTime();
		Trade trade = new Trade("foo", TradeType.BUY, 1, new Currency("SGP",
				"Singapore Dollars"), 200, 100.00, now, now);
		Assert.assertFalse(4000.0 == trade.getAmountinUSD());
	}

}
