package usingProcessing;

import peasy.PeasyCam;
import processing.core.PApplet;

public class WaveBomb extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { WaveBomb.class.getName() });
	}

	PeasyCam cam;
	float angle;
	float w = 40;
	float max;
	float m = atan(1 / sqrt(2));

	public void settings() {
		size(800, 800, P3D);
	}

	public void setup() {
		cam = new PeasyCam(this, 100);
		lights();
		angle = 0f;
		max = dist(0, 0, width / 2, height / 2);
		rectMode(CENTER);
		//		colorMode();
	}

	public void draw() {
		background(255);
		//		ortho(-1300,300,-600,1200,-100,1200);
		rotateX(-QUARTER_PI);
		rotateY(m);
		for (int z = -height / 2; z < height / 2; z += w) {
			for (int x = -width / 2; x < width / 2; x += w) {
				float d = dist(x + width / 2, z + height / 2, width / 2, height / 2);
				float offset = map(d, 0, max, -PI, PI);
				float h = map(sin(radians(angle) + offset), -1, 1, 50, 300);
				int color = (int) map(h, 50, 300, 20, 256);
				fill(0, 256, color, 100);
				pushMatrix();
				translate(x, 0, z);
				box(w - 2, h, w - 2);
				popMatrix();
			}
		}

		angle += 5;
	}
}
