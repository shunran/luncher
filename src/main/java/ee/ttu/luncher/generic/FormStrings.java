package ee.ttu.luncher.generic;

import lombok.Getter;
import lombok.Setter;

public class FormStrings {
	public final static int ASIZE = 9;
	@Getter
	@Setter
	private String question;
	@Getter
	@Setter
	private String[][] answers;

	private final String[] qs = { "Kas söömiseks kuluv aeg on oluline?", // 0
			"Soovite süüa koha peal või kuskil mujal?", // 1
			"Kas teeninduse kvaliteet on oluline?", // 2
			"Kas 15 eurot pearoa eest on liiga kallis?", // 3
			"Kas liha söömine tekitab teis negatiivseid tundeid?", // 4
			"Kas sooviksite laua varem reserveerida?", // 5
			"Kas olulisem on elamus või kõhutäis?", // 6
			"Kas olete valmis ennast viisakalt riidesse panema?", // 7
			"Kas toidu kvaliteet on teile oluline?", // 8
			"Kas lähete autoga?" }; // 9

	private final String[][][] as = { { { "jah", "1" }, { "ei", "2" } }, // 0
			{ { "koha peal", "3" }, { "kaasa", "1" } }, // 1
			{ { "oluline", "1" }, { "ei ole oluline", "6" } }, // 2
			{ { "kallis", "1" }, { "ei ole kallis", "8" } }, // 3
			{ { "jah", "1" }, { "ei", "10" } }, // 4
			{ { "jah", "1" }, { "ei", "12" } }, // 5
			{ { "elamus", "1" }, { "kõhutäis", "14" } }, // 6
			{ { "jah", "1" }, { "ei", "16" } }, // 7
			{ { "jah", "1" }, { "ei", "18" } }, // 8
			{ { "jah", "1" }, { "ei", "20" } } // 9
	};

	public FormStrings(int i) {
		if (ASIZE >= i) {
			question = qs[i];
			answers = as[i];
		}
	}
}
