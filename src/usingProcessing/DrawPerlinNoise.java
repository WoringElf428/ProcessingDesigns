package usingProcessing;

import processing.core.PApplet;

public class DrawPerlinNoise extends PApplet{
	float strength = 1400.0f;		//ノイズの強さ
	float noiseScale = 0.002f;		//ノイズの大きさ（スケール）
	int step = 6;					//グリッドの細かさ

	public static void main( String args[]) {
		PApplet.main(new String[] {DrawPerlinNoise.class.getName()});
	}

	public void settings() {
		size(800,600);
		//fullScreen();
	}
	public void setup() {
		noLoop();
	}
	public void draw() {
		background(0);
		stroke(255);
		noFill();

		for(int j = 0; j < height*2; j+= step) {
			beginShape();
			for(int i = -width/2; i < width * 1.5; i += step) {
				float noise = noise(i * noiseScale, j * noiseScale) * strength;
				curveVertex(i, (float) (j + noise + (i * 0.5)));
			}
			endShape();
		}
	}
}
