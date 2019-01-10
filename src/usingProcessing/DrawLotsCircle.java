package usingProcessing;

import processing.core.PApplet;
import processing.core.PVector;

public class DrawLotsCircle extends PApplet {
	int num = 15000;
	Particle[] mys = new Particle[num];

	public static void main(String args[]) {
		PApplet.main(new String[] {DrawLotsCircle.class.getName()});
	}

	public void settings() {
		size(1000,800);		//描画モードP2D
		//fullScreen();
	}

	public void setup() {
		frameRate(60);			//フレームレート60
		blendMode(ADD);
		noStroke();

		//配置を初期化
		for(int i = 0; i < num; i++) {
			mys[i] = new Particle(random(2, 12));
		}
	}

	public void draw() {
		background(0);			//背景色を黒に設定

		//点を動かす
		for(int i = 0; i < num; i++) {
			mys[i].draw();
		}


		blendMode(BLEND);		//混色を戻す
		fill(0, 5);				//色を設定
		noStroke();
		rect(0,0,width,height);	//四角形で塗りつぶす
	}

public class Particle{
	int r,g,b;
	float dimater;
	PVector location, velocity;

	public Particle(float d) {
		dimater = d;
		location = new PVector(random(0, width), random(0,height));
		velocity = new PVector(random(-4, 4), random(-4, 4));
		r = (int) random(255);
		g = (int) random(255);
		b = (int) random(255);
	}

	public void draw() {
		fill(r, 0, 0);
		ellipse(location.x, location.y, dimater, dimater);
		location.add(velocity);

		//画面の端で反射する処理
		if(location.x < 0 || location.y > width) {
			velocity.x = velocity.x * -1;
		}
		if(location.y < 0 || location.y > height) {
			velocity.y = velocity.y * -1;
		}
	}
}
}
