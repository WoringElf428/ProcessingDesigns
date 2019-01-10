package usingProcessing;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawPImageWithCircul extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {DrawPImageWithCircul.class.getName()});
	}
	PImage img;
	public void settings() {
		size(440,440);
	}
	public void setup() {
		img = loadImage("渋谷凛ジャケ.jpg");
		img.resize(width, height);
//		img.filter(POSTERIZE);
	}
	public void draw() {
		noStroke();
		for(int i = 0;i < 100;i++) {
			int x = (int) random(0,width);
			int y = (int) random(0,height);
			int col = img.get(x, y);

			fill(col,127);
			float b = red(col) + green(col) + blue(col);
//			float d = map(b,0,255*3,0,10);
			float d = 2f;
			ellipse(x, y, d, d);
		}
		/*
		 * background(0);
		noStroke();
		int step = (int) map(mouseX,0,width,1,100);
		for(int i = 0; i <width; i += step) {
			for(int j = 0; j< height; j += step) {
				int col = img.get(j, i);
				fill(col);
				rect(j,i,step,step);
			}
		}*/

		/*
		int col = img.get(mouseX, mouseY);
		fill(col);
		rect(0, 0, width, height);
		image(img,0,0);

		line(mouseX,0,mouseX,height);
		line(0,mouseY,width,mouseY);
		 */
	}
}
