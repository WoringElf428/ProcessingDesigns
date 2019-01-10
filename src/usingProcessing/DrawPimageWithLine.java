package usingProcessing;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawPimageWithLine extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {DrawPimageWithLine.class.getName()});
	}
	PImage img;
	public void settings() {
		size(440,440);
	}
	public void setup() {
		img = loadImage("渋谷凛ジャケ.jpg");
		img.resize(width, height);
	}
	public void draw() {
		noStroke();
		rectMode(CENTER);

		for(int i = 0;i < 150;i++) {
			int x = (int) random(0,width);
			int y = (int) random(0,height);
			int col = img.get(x, y);

			fill(col,127);
			float b = red(col) + green(col) + blue(col);
			float a = map(b,0,255*3,-PI/2f,PI/2);
			float l = map(b,0,255*3,0,10);
			pushMatrix();
			translate(x, y);
			rotate(a);
			rect(0,0,l,2);
			popMatrix();
		}
	}
}
