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
		System.out.println(Arrays.toString(line));
		FactVo factVo = new FactVo();
		factVo.setName(line[0]);
		factVo.setCuisine(FactVo.Cuisine.valueOf(u(line[1])));
		factVo.setMinCost(Integer.parseInt(line[2]));
		factVo.setMaxCost(Integer.parseInt(line[3]));
		factVo.setService(FactVo.Service.valueOf(u(line[4])));
		factVo.setServiceClass(FactVo.ServiceClass.valueOf(u(line[5])));
		factVo.setLocation(FactVo.Location.valueOf(u(line[6])));
		factVo.setDressCode(makeJahEiToBool(line[7]));
		factVo.setTakeAway(makeJahEiToBool(line[8]));
		factVo.setPreOrder(makeJahEiToBool(line[9]));
		factVo.setReservation(makeJahEiToBool(line[10]));
		factVo.setVegan(makeJahEiToBool(line[11]));
		factVo.setDriveIn(makeJahEiToBool(line[12]));
		factVo.setFreeParking(makeJahEiToBool(line[13]));
		factVo.setMinPreparationTime(Integer.parseInt(line[14]));
		factVo.setMaxPreparationTime(Integer.parseInt(line[15]));
		factVo.setCafe(makeJahEiToBool(line[16]));
		factVo.setLiveMusic(makeJahEiToBool(line[17]));
		System.out.println(factVo);
		facts.add(factVo);
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
