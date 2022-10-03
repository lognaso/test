package kr.co.cwit.common.util;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRate	{
	private String name;
	private String exchange_rate;
	private String cross_rate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExchange_rate() {
		return exchange_rate;
	}
	public void setExchange_rate(String exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
	public String getCross_rate() {
		return cross_rate;
	}
	public void setCross_rate(String cross_rate) {
		this.cross_rate = cross_rate;
	}
	
	@Override
	public String toString() {
		return "ExchangeRate [name=" + name + ", exchange_rate=" + exchange_rate + ", cross_rate=" + cross_rate + "]";
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<>();
		map.put("CROSS_RATE", this.getCross_rate() );
		map.put("BASIS_RATE", this.getExchange_rate() );
		map.put("MONETARY_UNIT", this.getName());
		return map;
	}
}