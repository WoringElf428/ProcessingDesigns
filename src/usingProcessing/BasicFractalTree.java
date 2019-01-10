package usingProcessing;

import processing.core.PApplet;

public class BasicFractalTree extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {BasicFractalTree.class.getName()});
	}

	float len = 200;
	float angle = 45;
	public void settings() {
		size(800,600);
	}
	public void setup() {
	}

	public void draw() {
		background(51);
		stroke(255);
		translate(width/2,height);
		branch(len);
		angle += 0.5f;
		if(angle > 360) {
			angle = 0f;
		}
	}

	public void branch(float len) {
		line(0,0,0,-len);
		translate(0,-len);
		if(len > 4) {
			pushMatrix();
			rotate(radians(angle));
			branch(len*0.66f);
			popMatrix();
			pushMatrix();
			rotate(radians(-angle));
			branch(len*0.66f);
			popMatrix();
		}
	}
}
