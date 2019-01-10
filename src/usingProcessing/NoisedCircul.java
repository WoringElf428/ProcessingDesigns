package usingProcessing;

import processing.core.PApplet;

public class NoisedCircul extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {NoisedCircul.class.getName()});
	}
	public void settings() {
		size(800,600);
	}
	float radNoise,rad,t;
	float[] r;
	public void setup() {
		frameRate(60);
		smooth();
		colorMode(HSB, 10, 100, 100);
		r = new float[360];
		radNoise = random(10);
		t = 0;

		for(float ang = 0; radians(ang) < TWO_PI; ang += 1) {
			radNoise += 0.70;
			r[(int)ang] = noise(radNoise)*100;
		}
	}
	public void draw() {
		background(0,0,0);
		translate(width/2, height/2);
		t += 10;
		for(float ang = 0; radians(ang) < TWO_PI; ang += 1) {
			float a = cos(radians(ang+t));
			float rad = r[(int)ang]*a+150;
			float x = rad*cos(radians(ang));
			float y = rad*sin(radians(ang));
//			System.out.println(a);
			line(0,0,x,y);
		}
		if(t > 360) {
			t = 0;
		}
	}
}
