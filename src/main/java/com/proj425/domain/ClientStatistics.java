package com.proj425.domain;

public class ClientStatistics {
	
	private Client client;
	private String avgDaysPerTrip;
	public ClientStatistics() {
		super();
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getAvgDaysPerTrip() {
		return avgDaysPerTrip;
	}
	public void setAvgDaysPerTrip(String avgDaysPerTrip) {
		this.avgDaysPerTrip = avgDaysPerTrip;
	}
	
	
	
}
