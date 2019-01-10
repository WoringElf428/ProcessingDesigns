package usingProcessing;

import processing.core.PApplet;

public class EscapingMouse extends PApplet {

	public static void main(String args[]) {
		PApplet.main(new String[] {EscapingMouse.class.getName()});
	}
	int num = 255;
	float[] x = new float[num];
	float[] y = new float[num];

	public void settings() {
		size(800,600);
	}

	public void setup() {
		frameRate(60);
		blendMode(ADD);
		for(int i = 0; i < num; i++) {
			x[i] = width/2.0f;
			y[i] = height/2.0f;
		}
	}
	public void draw() {
		background(0);
		fill(0,0,255);
		int t = 0;
		for(int i = 0; i < num; i++) {
			ellipse(x[i],y[i],5,5);
			if(dist(x[i],y[i],mouseX,mouseY) == 0) {
				t++;
			}
			if(x[i] > mouseX) {
				x[i] = x[i] + random(-8,6);
			}else {
				x[i] = x[i] + random(6,8);
			}
			if(y[i] > mouseY) {
				y[i] = y[i] + random(-8,6);
			}else {
				y[i] = y[i] + random(6,8);
			}
		}
		fill(255,0,t);
		ellipse(mouseX,mouseY,20,20);
	}
}
