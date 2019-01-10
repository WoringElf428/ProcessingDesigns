package usingProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class SnowCrystal extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { SnowCrystal.class.getName() });
	}

	SnowFlake s;
	ArrayList<SnowFlake> flakes = new ArrayList<>();

	public void settings() {
		size(800, 800);
//		fullScreen();
	}

	public void setup() {
		s = new SnowFlake(width/2, 0);
	}

	public void draw() {
		background(0);
		translate(width / 2, height / 2);

		while (!s.isFinished() && !s.isIntersecting(flakes)) {
			s.update();
		}
		flakes.add(s);
		s = new SnowFlake(width / 2, 0);

		for (int i = 0; i < 6; i++) {
			rotate(PI / 3);
			s.show();
			for (SnowFlake s : flakes) {
				s.show();
			}

			pushMatrix();
			scale(-1, 1);
			s.show();
			for (SnowFlake s : flakes) {
				s.show();
			}
			popMatrix();
		}
	}

	class SnowFlake {
		PVector pos = new PVector();
		float r;

		public SnowFlake(float x, float y) {
			pos.x = x;
			pos.y = y;
			r = 3;
		}

		public void show() {
			fill(255);
			stroke(255);
			ellipse(pos.x, pos.y, r*2, r*2);

			float angle = pos.heading();
			float m = pos.mag();
			angle = constrain(angle, 0, PI / 6);
			pos = PVector.fromAngle(angle);
			pos.setMag(m);
		}

		public void update() {
			pos.x -= 1;
			pos.y += random(-3, 3);
		}

		public boolean isFinished() {
			return pos.x < 1;
		}

		public boolean isIntersecting(ArrayList<SnowFlake> list) {
			for (SnowFlake s : list) {
				float d = dist(s.pos.x, s.pos.y, pos.x, pos.y);
				if (d < r * 2) {
					return true;
				}
			}
			return false;
		}
	}
}
