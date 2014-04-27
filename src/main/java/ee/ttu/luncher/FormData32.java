package ee.ttu.luncher;

import lombok.Getter;
import lombok.Setter;

public class FormData32 {
	final int len = 1;
	@Getter @Setter private String question;
	@Getter @Setter private String[] answers;
	
	private final String[] qs = {
			"Kui vana oli surnud siil?",
			"Milleks teile külmkapp, kui te ei suitseta?",
			"Mis värvi on armastus?"};

	private final String[][] as = { {
			"jeha", "nope" },
			{
			"ikka", "natukene"},
			{
			"ei ole teind", "lepatriinu"}		
			};
	
	public FormData32(int i) {
		if (len >= i) {
			question = qs[i];
			answers = as[i];				
		} else {
			question = qs[0];
			answers = as[0];				
		}

	}
}