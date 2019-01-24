package usingProcessing;

import controlP5.ControlP5;
import controlP5.Slider;
import processing.core.PApplet;

public class MathematicalRoses extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {MathematicalRoses.class.getName()});
	}

	float r = 150;
	ControlP5 con;
	Slider k,cn;

	public void settings() {
		size(800,600);
	}

	public void setup() {
		con = new ControlP5(this);
		k = con.addSlider("k")
				.setPosition(0, 0)
				.setRange(1, 10)
				.setValue(5);
	}
	public void draw() {
		pushMatrix();
		background(137);
		translate(width/2,height/2);
		stroke(255);
		noFill();
		beginShape();
		for(float angle = 0; angle < TWO_PI; angle += 0.02f) {
			float radius = r * cos(7*angle);
			float x = radius * cos(angle*k.getValue());
			float y = radius * sin(angle*k.getValue());
			vertex(x,y);
		}
		endShape();
		popMatrix();
	}
}
