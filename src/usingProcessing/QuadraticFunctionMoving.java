package usingProcessing;

import processing.core.PApplet;

public class QuadraticFunctionMoving extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {QuadraticFunctionMoving.class.getName()});
	}

	float p,x,y,cource,h;
	int a,h_limit;

	public void settings() {
		size(800,600);
	}

	public void setup() {
		frameRate(60);
		stroke(255, 255, 255);
		rectMode(CENTER);
		reset();
	}

	public void draw() {
		background(0);
		translate(width/2, height/2);
/*
		fill(0,31);
		rect(-width,-height,width,height);
*/

		noFill();
		rect(x, y+p, h *50, h * 50);
//		point(x, y+p);

		updateDist();
		if(x > width/2) {
			reset();
		}
	}

	public void updateDist() {
		x += 1.5f;
		y = a * cource * sq(x);
		h = noise(x,y);
	}

	public void reset() {
		 cource = random(0.01f,0.02f);
		 p = random(-300,300);
		 a = p > 0 ? -1:1;
		 y = p > 0 ? 0:height;
		 x = -300;
//		 x = 0;
	}

}
