package usingProcessing;

import processing.core.PApplet;

public class NoisedRect extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {NoisedRect.class.getName()});
	}

	float startX,startY,noiseX,noiseY;
	public void settings() {
		size(300,300);
	}

	public void setup() {
		smooth();
		frameRate(5);

		startX = random(20);
		startY = random(20);
		noiseX = random(10);
		noiseY = random(10);
	}
	public void draw() {
		background(255);
		startX += 0.01f;
		startY += 0.01f;
		noiseX += noise(startX)*0.5-0.25;
		noiseY += noise(startY)*0.5-0.25;


		float n_y = noiseY;
		for(int y= 0; y <= height; y+=5) {
			n_y += 0.1f;
			float n_x = noiseX;
			for(int x = 0; x <= width; x+=5) {
				n_x += 0.1f;
				drawPoint(x, y, noise(n_x,n_y));
			}
		}
	}

	public void drawPoint(float x,float y, float noise) {
		float l = 5*noise;
		rect(x,y,l,l);
	}
}
