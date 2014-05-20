package ee.ttu.luncher.generic;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Choice {
	private List <Integer> choice = new ArrayList <Integer>(FormStrings.ASIZE + 1);
}
