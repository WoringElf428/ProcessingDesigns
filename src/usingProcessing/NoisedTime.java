package usingProcessing;

import processing.core.PApplet;

public class NoisedTime extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {NoisedTime.class.getName()});
	}

	float time,noiseT,ang,rad,t;
	public void settings() {
		size(800,600);
	}
	public void setup() {
		smooth();
		ang = 0f;
		noiseT = random(20);
		time = noiseT;
		rad = 100;
	}

	public void draw() {
//		background(255);
		translate(width/2, height/2);
//		point(0,0);

		time += 0.1f;
		t = noise(time) * 20 - 8f;

		for(int i = 0; i < abs(t); i++) {
			update();
		}

		rad = 50+10 * cos(radians(time*10));
		float x = rad * cos(radians(ang));
		float y = rad * sin(radians(ang));
		ellipse(x,y,5,5);
	}

	public void update() {
		if(t < 0) {
			ang -= 0.1f;
			if(ang < 0) {
				ang = 360;
			}
		}else {
			ang += 0.1f;
			if(ang > 360) {
				ang = 0;
			}
		}


	}
}
