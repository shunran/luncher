package ee.ttu.luncher;

import lombok.Data;

@Data
public class FactVo {
	private String       name;
	private Cuisine      cuisine;
	private Integer      minCost;
	private Integer      maxCost;
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
	private Integer      minPreparationTime;
	private Integer      maxPreparationTime;
	private Boolean      cafe;
	private Boolean      liveMusic;
	//@Getter @Setter private Integer?      rating;

	protected enum Cuisine {
		FASTFOOD, ASIA, AFRICA, AUSTRALIA, ITALY, EUROPE, LATVIA, RUSSIA, UNKNOWN
	}

	protected enum Service {
		SELF, WAITER
	}

	protected enum ServiceClass {
		LOW, MID, HI
	}

	protected enum Location {
		EVERYWHERE, OLDCITY, CENTRAL, NORTHTALLINN
	}
}
