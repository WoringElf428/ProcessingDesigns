package usingProcessing;

import processing.core.PApplet;

public class MouseInteraction extends PApplet {
	public static void main(String args[]) {
		PApplet.main(new String[] {MouseInteraction.class.getName()});
	}
	int c = color(0,255,255);
	public void settings() {
		size(800,600);
	}

	public void setup() {
		frameRate(60);
		noStroke();
	}

	public void draw() {
//		background(0);
		fill(0,33);
		rect(0,0,width,height);
		fill(c);
		ellipse(mouseX,mouseY,50,50);
	}

	public void mousePressed() {
		c = color(255,255,0);
	}
	public void mouseReleased() {
		c = color(255,0,255);
	}
}
