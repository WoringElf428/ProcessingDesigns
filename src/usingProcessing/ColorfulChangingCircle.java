package usingProcessing;

import processing.core.PApplet;

public class ColorfulChangingCircle  extends PApplet {
	float diameter;
	float hue;

	public static void main(String args[]) {
		PApplet.main(new String[] {ColorfulChangingCircle.class.getName()});
	}
	public void settings() {
		size(1000,800);		//描画モードP2D
	}

	public void setup() {
		frameRate(60);			//フレームレート60
		colorMode(HSB,360,100,100,100);
		noStroke();

	}

	public void draw() {
		background(0);			//背景色を黒に設定


		diameter = 400 + sin(frameCount * 0.1f) * 200;	//円の直径を変化
		hue = sin(frameCount * 0.1f) * 200;
		fill(hue,100,100);
		ellipse(width /2, height/2, diameter, diameter+10);

		fill(255,255,255);
		rect(0, height/2, width, 20);
	}
}
