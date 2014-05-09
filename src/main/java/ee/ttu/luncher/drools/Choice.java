package ee.ttu.luncher.drools;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ee.ttu.luncher.drools.Rules.FormStrings;

@Data
public class Choice {
	private List <Integer> choice = new ArrayList <Integer>(FormStrings.ASIZE + 1);
}
