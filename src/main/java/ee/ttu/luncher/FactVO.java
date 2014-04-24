package ee.ttu.luncher;

import lombok.Getter;
import lombok.Setter;

public class FactVO {
	@Getter @Setter private String       name;
	@Getter @Setter private Couisine     cousine;
	@Getter @Setter private Integer      cost;
	@Getter @Setter private Service      service;
	@Getter @Setter private ServiceClass serviceClass;
	@Getter @Setter private Location     location;
	@Getter @Setter private Boolean      dressCode;
	@Getter @Setter private Boolean      takeAway;
	@Getter @Setter private Boolean      preOrder;
	@Getter @Setter private Boolean      reservation;
	@Getter @Setter private Boolean      vegan;
	@Getter @Setter private Boolean      driveIn;
	@Getter @Setter private Boolean      freeParking;
	@Getter @Setter private Integer      preparationTime;
	@Getter @Setter private Boolean      cafe;
	@Getter @Setter private Boolean      liveMusic;
	//@Getter @Setter private Integer?      rating;
	
	protected enum Couisine {
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
