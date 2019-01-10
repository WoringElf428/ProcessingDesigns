package usingProcessing;

import processing.core.PApplet;
import processing.core.PFont;

public class CharacticCirculs extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {CharacticCirculs.class.getName()});
	}

	PFont font;
	public void settings() {
		size(800,600);
	}
	public void setup() {
		font = loadFont("8514syse.fon");
		textFont(font);
		textSize(150);
		fill(255);
		noStroke();
		text("Happy New Year",10,100);
	}
	public void draw() {

	}
}
