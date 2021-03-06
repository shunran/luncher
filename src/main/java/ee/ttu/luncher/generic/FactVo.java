package ee.ttu.luncher.generic;

import lombok.Data;

@Data
public class FactVo implements Comparable <FactVo>, Cloneable{
	
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
	private Double		 perceptron = .0;

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

	@Override
	public int compareTo(FactVo o) {
		return perceptron<o.getPerceptron()?1:perceptron>o.getPerceptron()?-1:0;
	}
	
	public FactVo clone() {
		try {
			return (FactVo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
