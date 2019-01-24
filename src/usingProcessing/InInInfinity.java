package usingProcessing;

import processing.core.PApplet;

public class InInInfinity extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { InInInfinity.class.getName() });
	}

	float rr, angle,length;
	float R = 50;

	public void settings() {
		size(600, 600);
	}

	public void setup() {
		colorMode(HSB, 12, 100, 100);
		angle = 0;
		length = width/2 * 1/2;
	}

	public void draw() {
		background(0);
		translate(width / 2, height / 2);

		for(int i = 0; i < 12; i++) {
			pushMatrix();
			rotate(radians(30*i));
			translate(0, length);
			for (float a = 0; a < TWO_PI; a += 0.005) {
				if (a < angle && a > angle - PI * 2 / 3) {
					float s = map(a, angle - QUARTER_PI, angle, 20, 100);
					stroke(i+1, s, 100);
					float w = map(a, angle - QUARTER_PI, angle, 1, 5);
					strokeWeight(constrain(w, 0.4f, 5));
					noFill();

					rr = 2 * cos(2 * a);
					float r = R * sqrt(rr);

					pushMatrix();
					rotate(a);
					point(r, 0);
					point(-r, 0);
					popMatrix();
				}
			}
			popMatrix();
		}

		angle += 0.03;
		if (angle > TWO_PI + PI) {
			angle = 0;
		}
	}
}
