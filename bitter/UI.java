package bitter;

public class UI {
	private char option1, option2, option3, option4, option5, option6;
	private String description1, description2, description3, description4, descritpion5, description6;
	private char selection;
	
	UI(char opt1, char opt2, char opt3, char opt4, char opt5, char opt6) {
		option1 = opt1;
		option2 = opt2;
		option3 = opt3;
		option4 = opt4;
		option5 = opt5;
		option6 = opt6;
		selection = ' ';
	}
	public void setDescription (String descriptionNum, String description) {
		descriptionNum = description;
	}
	public char getSelection () {
		return selection;
	}
	public void setSelection(char option) {
		selection = option;
	}
	public char getOption (char whichOption) {
		return whichOption;
	}
	
}
