package usingProcessing;

import processing.core.PApplet;

public class Cube extends PApplet {
	float col = 400;
	float colnoise;
	float radius = 200;
	float step = 0.002f;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {Cube.class.getName()});
	}
	public void settings() {
		size(600, 600, P3D);
	}
	public void setup() {

//	  pixelDensity(2);
	  colorMode(HSB, 360, 100, 100, 100);
	  background(0);
	  blendMode(ADD);
	  noFill();

	  colnoise = random(10);
	}

	public void draw() {
	  if (mousePressed) {
	    background(0);
	  }
	  translate(width/2, height/2, 0);
	  rotateY(frameCount * 0.002f);
	  rotateZ(frameCount * 0.003f);
	  rotateX(frameCount * 0.003f);
	  rotateX(noise(frameCount * 0.003f));

	  float s = 0;
	  float t = 0;
	  float x = 0;
	  float y = 0;
	  float z = 0;
	  beginShape();
	  while (s <= 180) {
	    t += 3;
	    s += 1;
	    float radianS = radians(s);
	    float radianT = radians(t);

	    x = radius * cos(radianS) * sin(radianT);
	    y = radius * sin(radianS) * sin(radianT);
	    z = radius * cos(radianT);
	    stroke(col *  noise(colnoise), 80, 60, 10);
	    vertex(x, y, z);
	  }
	  endShape();
	  colnoise += step;
	}

	public void keyPressed() {
	  if (key == ' ')saveFrame("####.png");
	}
}
