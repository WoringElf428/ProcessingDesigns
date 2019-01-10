package usingProcessing;

import processing.core.PApplet;

public class StarField extends PApplet {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {StarField.class.getName()});
	}
	int num = 1000;
	Star[] stars = new Star[num];
	public void settings() {
		size(800,600);
	}

	public void setup() {
		frameRate(60);
		colorMode(HSB, 360, 256, 256);
		for(int i=0; i < num; i++) {
			stars[i] = new Star(10);
		}
	}

	public void draw() {
		background(0);
		translate(width/2, height/2);

		for(int i=0; i < num; i++) {
			stars[i].show();
			stars[i].update();
		}
	}

	class Star{
		float x,y,z,speed,size;
		int color = color(random(0,360), 255, 255, 50);
		public Star(float speed) {
			this.x = random(-width,width);
			this.y = random(-height,height);
			this.z = random(width);
			this.speed = speed;
			size = 5f;
		}
		public void show() {
			float sx = map(x/z,0,1f,0f,width);
			float sy = map(y/z,0,1f,0f,height);
			size = map(z, 0,width, 16,0);
			fill(color);
			ellipse(sx,sy,size+6, size+6);

			fill(255);
			ellipse(sx,sy,size, size);
		}

		public void update() {
			z -= speed;
			if(z < 1) {
				this.x = random(-width,width);
				this.y = random(-height,height);
				this.z = width;
			}
//			size = map(z,-width/2,0f,1f,16f);
		}
	}
}
