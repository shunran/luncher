package ee.ttu.luncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
import au.com.bytecode.opencsv.CSVReader;

public class FactDao {
	private final String csvFileName = "/resources/facts.csv";
	
	@Getter @Setter
	private ArrayList <FactVo> facts = new ArrayList<FactVo>();

	public void load() throws IOException {
		InputStream in = (InputStream) getClass().getResourceAsStream(csvFileName);
		Reader bReader = new BufferedReader(new InputStreamReader(in));
		CSVReader reader = new CSVReader(bReader, '\t');
		String[] nextLine;
		Integer i  = 1;
		//First row is titles
		reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			System.out.println("loen rida no:" + i);
			i += 1;
			// nextLine[] is an array of values from the line
			tokenize(nextLine);
		}
		reader.close();
	}
	
	private void tokenize(String[] line) {
		System.out.println("rea pikkus on " + String.valueOf(line.length));
		/*if (line.length < 15) {
			System.out.println(Arrays.toString(line));
			return;
		}*/
		FactVo factVo = new FactVo();
		factVo.setName(line[0]);
		factVo.setCuisine(FactVo.Cuisine.valueOf(u(line[1])));
		factVo.setCost(Integer.parseInt(line[2]));
		factVo.setService(FactVo.Service.valueOf(u(line[3])));
		factVo.setServiceClass(FactVo.ServiceClass.valueOf(line[4]));
		factVo.setLocation(FactVo.Location.valueOf(line[5]));
		factVo.setDressCode(makeJahEiToBool(line[6]));
		factVo.setTakeAway(makeJahEiToBool(line[7]));
		factVo.setPreOrder(makeJahEiToBool(line[8]));
		factVo.setReservation(makeJahEiToBool(line[9]));
		factVo.setVegan(makeJahEiToBool(line[10]));
		factVo.setDriveIn(makeJahEiToBool(line[11]));
		factVo.setFreeParking(makeJahEiToBool(line[12]));
		factVo.setPreparationTime(Integer.parseInt(line[13]));
		factVo.setCafe(makeJahEiToBool(line[14]));
		factVo.setLiveMusic(makeJahEiToBool(line[15]));
		System.out.println(factVo);
		facts.add(factVo);
	}
	
	private char[] length(String[] line) {
		// TODO Auto-generated method stub
		return null;
	}

	private Boolean makeJahEiToBool(String string) {
		if (u(string) == "JAH")
			return true;
		else
			return false;
	}
	
	private String u(String l) {
		return l.trim().toUpperCase();
	}
}
