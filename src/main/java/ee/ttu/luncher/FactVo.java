package ee.ttu.luncher;

import lombok.Data;

@Data
public class FactVo {
	private String       name;
	private Cuisine      cuisine;
	private Integer      cost;
	private Service      service;
	private ServiceClass serviceClass;
	private Location     location;
	private Boolean      dressCode;
	private Boolean      takeAway;
	private Boolean      preOrder;
	private Boolean      reservation;
	private Boolean      vegan;
	private Boolean      driveIn;
	private Boolean      freeParking;
	private Integer      preparationTime;
	private Boolean      cafe;
	private Boolean      liveMusic;
	//@Getter @Setter private Integer?      rating;
	
	protected enum Cuisine {
		FASTFOOD, ASIA, AFRICA, AUSTRALIA, EUROPE, RUSSIA, UNKNOWN
	}
	
	protected enum Service {
		SELF, WAITER
	}
	
	protected enum ServiceClass {
		LOW, MID, HI
	}

	protected enum Location {
		VANALINN, KESKLINN, POHJATALLINN
	}
}
