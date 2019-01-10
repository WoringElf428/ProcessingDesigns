package usingProcessing;

import processing.core.PApplet;

public class PerlinNoiseGraphics extends PApplet {
	public static void main(String args[]) {
		PApplet.main(new String[] {PerlinNoiseGraphics.class.getName()});
	}
	float noise_scale;
	int step;
	public void settings() {
		size(800,600);
	}

	public void setup() {
		frameRate(60);
		noStroke();
		step = 5;
		noise_scale = 0.02f;
	}

	public void draw() {
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				float gray = noise(i * noise_scale * random(0.1f,0.5f), j * noise_scale * random(0.1f,0.5f));
				fill(gray);
				rect(i,j, step,step);
			}
		}
	}
}
