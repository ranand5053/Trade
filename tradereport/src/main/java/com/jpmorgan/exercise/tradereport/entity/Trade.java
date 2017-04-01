/**
 * 
 */
package com.jpmorgan.exercise.tradereport.entity;

import java.util.Date;

import com.jpmorgan.exercise.tradereport.util.TradeType;

/**
 * Model class to keep Trade details.
 *  
 * @author Rajender Anand
 *
 */
public class Trade {

	private String entity;
	private TradeType tradeType;
	private double fxPrice;
	private Currency currency;
	private Date instructionDate;
	private Date settlementDate;
	private int quantity;
	private double price;

	/**
	 * Constructs the Trade Object
	 * 
	 * @param entity
	 *            The name of the Stock entity
	 * @param tradeType
	 *            The trade type of the trade
	 * @param fxPrice
	 *            The Fx Price
	 * @param currency
	 *            The Currency of the trade
	 * @param quantity
	 *            The quantity of the trade
	 * @param price
	 *            The Price of each quantity
	 * @param instructionDate
	 *            The instruction date of the trade
	 * @param settlementDate
	 *            The settlement date on w of the trade
	 */
	public Trade(String entity, TradeType tradeType, double fxPrice,
			Currency currency, int quantity, double price,
			Date instructionDate, Date settlementDate) {
		this.entity = entity;
		this.tradeType = tradeType;
		this.fxPrice = fxPrice;
		this.currency = currency;
		this.quantity = quantity;
		this.price = price;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
	}


	/**
	 * Get the entity name
	 * 
	 * @return
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * Set the name of the entity
	 * 
	 * @param entity
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * Gets the type of Trade
	 * 
	 * @return
	 */
	public TradeType getTradeType() {
		return tradeType;
	}

	/**
	 * Sets the Trade type 
	 * 
	 * @param tradeType
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * Gets the AgreedFx Price
	 * 
	 * @return
	 */
	public double getFxPrice() {
		return fxPrice;
	}

	/**
	 * Sets the AgreedFx
	 * 
	 * @param fxPrice
	 */
	public void setFxPrice(double fxPrice) {
		this.fxPrice = fxPrice;
	}

	/**
	 * Get the Currency of the trade.
	 * 
	 * @return
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Sets the Currency of the trade
	 * 
	 * @param currency
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * Gets the Instruction date of the trade
	 * 
	 * @return
	 */
	public Date getInstructionDate() {
		return instructionDate;
	}

	/**
	 * Sets the Instruction date of the trade
	 * 
	 * @param instructionDate
	 */
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	/**
	 * Gets the Settlement date of the trade
	 * 
	 * @return
	 */
	public Date getSettlementDate() {
		return settlementDate;
	}

	/**
	 * Sets the Settlement date of the trade
	 * 
	 * @param settlementDate
	 */
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	/**
	 * Gets the quantity of the trade
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the trade
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the price of the trade
	 * 
	 * @return 
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the trade
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns the trade amount of the trade.
	 * 
	 * @return
	 */
	public double getAmountinUSD() {
		return price * quantity * fxPrice;
	}

	/**
	 * Returns a string representation of trade Object.
	 */
	@Override
	public String toString() {
		return "Trade [entity=" + entity + ", tradeType=" + tradeType
				+ " USD Amount=" + getAmountinUSD() + ", fxPrice="
				+ fxPrice + ", currency=" + currency + ", instructionDate="
				+ instructionDate + ", settlementDate=" + settlementDate
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}

}
