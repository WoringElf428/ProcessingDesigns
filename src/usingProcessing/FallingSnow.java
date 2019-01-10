package usingProcessing;

import processing.core.PApplet;

public class FallingSnow extends PApplet{
	public static void main(String args[]) {
		PApplet.main(new String[] {FallingSnow.class.getName()});
	}

	int num = 1500;
	Snow[] mys = new Snow[num];

	public void settings() {
		size(800,600);
	}

	public void setup() {
		frameRate(60);			//フレームレート60
		noStroke();

		//配置を初期化
		for(int i = 0; i < num; i++) {
			mys[i] = new Snow();
		}
		filter(BLUR,3);
	}

	public void draw() {
		background(0);			//背景色を黒に設定
		fill(255);
		for(int i=0; i<num; i++) {
			mys[i].fall();
		}

	}

	public class Snow{
		float x,y,speed,size;
		int r;

		public Snow() {
			x = random(0,width);
			y = random(0,height);
			speed = random(0.3f,2.0f);
			size = random(0.5f,4.0f);
			r = (int)random(0,180);
		}

		public void fall(){
			ellipse(x,y,size,size);
			y = y + speed;
			x = x + 0.4f * sin(radians(r));
			r++;
			if(r == 360) {r = 0;}
			if(y > height) {y = 0;}
		}
	}
}
