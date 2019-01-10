package usingProcessing;

import processing.core.PApplet;

public class CIrculicCircul extends PApplet {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {CIrculicCircul.class.getName()});
	}

	int   spotCntMax;
	float stepCnt;
	float stepCntMax;

	public void settings() {
		size(800,600);
	}

	public void setup() {
		colorMode(HSB, 360, 100, 100, 100);
	  blendMode(SCREEN);
	  frameRate(20);
	  smooth();
	  noStroke();

	  spotCntMax = 30;
	  stepCntMax = 25.0f;
	  stepCnt    = 1.0f;

	}

	public void draw() {

	  translate(width / 2, height / 2);
	  background(0);

	  for (int spotCnt = 1; spotCnt <= spotCntMax; ++spotCnt) {

	    float start  = map(spotCnt, 1, spotCntMax, 0.0f, 1000.0f);
	    float speed  = map(spotCnt, 1, spotCntMax, 2.0f, 0.1f);
	    float radius = map(spotCnt, 1, spotCntMax, 50, 180);
	    float radian = PI * (start + speed * stepCnt);

	    for (float radianDiv = 0; radianDiv < 1.0; radianDiv += 0.1) {

	      float radianApply = radian + PI * (radianDiv / 10.0f + speed / 4.0f);
	      float radiusApply = radius + (cos(radianApply) * sin(PI * stepCnt * 0.1f)) * 40f;

	      float x = radiusApply * cos(radianApply + spotCnt);
	      float y = radiusApply * sin(radianApply + spotCnt);

	      fill(3f * x, 50, 60 * sin(PI * 0.5f* radianDiv), 100);
	      drawEllipse(x, y, 10);
	    }
	  }

	  stepCnt += 0.02;
	  if (stepCnt > stepCntMax) {
	    exit();
	  }

	}

	void drawEllipse(float x, float y, float size) {

	  ellipse( x,  y, size, size);
	  ellipse(-x, -y, size, size);

	}
}
