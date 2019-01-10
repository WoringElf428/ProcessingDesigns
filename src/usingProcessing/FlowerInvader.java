package usingProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class FlowerInvader extends PApplet{
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {FlowerInvader.class.getName()});
	}

	Ship ship = new Ship();
	int num = 5;
	Flower[] flowers = new Flower[num];

	public void settings() {
		size(800,600);
	}
	public void setup() {
		colorMode(HSB,360,100,100,100);
		for(int i = 0; i < num;i++) {
			flowers[i] = new Flower();
			flowers[i].reset();
		}
	}
	public void draw() {
		background(360,10,90);
		ship.show();
		ship.move();

		for(int i = 0; i < num;i++) {
			flowers[i].show();
			flowers[i].move();
			flowers[i].bounce();

			if(ship.isHit(flowers[i])) {
				textSize(50);
				textAlign(CENTER,CENTER);
				text("Game Over",width/2,height/2);
				noLoop();
			}
		}
	}
	public void keyPressed() {
		if(keyCode == RIGHT || keyCode == 'D') {
			ship.right(0.5f);
		}else if(keyCode == LEFT || keyCode == 'A') {
			ship.left(0.5f);
		}
	}
	public void mousePressed() {
		ship.addDrop();
	}

	class Ship{
		float x = width/2;
		float speed = 0f;
		ArrayList<Drop> drops = new ArrayList<>();
		ArrayList<Drop> gc = new ArrayList<>();
		public void show() {
			noFill();
		stroke(0,100,0);
			rectMode(CENTER);
			rect(x,height-20, 20,20);
			for(Drop d:drops) {
				d.show();
				d.move();
				for(int i = 0; i < num;i++) {
					if(d.isHit(flowers[i])) {
						flowers[i].hit();
					}
				}
				if(d.finish) {gc.add(d);}
			}
			drops.removeAll(gc);
		}
		public void move() {
			x += speed;
			adjustDist();
		}
		public void left(float l) {
			speed -= l;
		}

		public void right(float r) {
			speed += r;
		}
		private void  adjustDist() {
			if(this.x < 0) {
				this.x = 0;
			}else if(this.x > width) {
				this.x = width;
			}
		}

		public void addDrop() {
			if(drops.size() < 30) {
				drops.add(new Drop(x, height-30));
			}
		}
		public void removeDrop(Drop d) {
			drops.add(d);
		}
		public boolean isHit(Flower f) {
			float dist = dist(x, height-30, f.location.x, f.location.y);
			return dist < 20+f.size/3.5?true:false;
		}
	}

	class Drop{
		PVector velocity = new PVector(0,-3f);
		PVector location = new PVector();
		boolean finish = false;
		float size = 8f;

		public Drop(float _x, float _y) {
			location.x = _x;
			location.y = _y;
		}
		public void show() {
			fill(0);
			ellipseMode(CENTER);
			ellipse(location.x ,location.y,size,size);
		}

		public void move() {
			location.add(velocity);
			if(location.y < 0) {
				finish = true;
			}
		}

		public boolean isHit(Flower f) {
			float dist = dist(location.x, location.y, f.location.x, f.location.y);
			if (dist < size+f.size/3.5) {
				finish = true;
				return true;
			}else {
				return false;
			}
		}
	}

	class Flower {
		float size;
		PVector velocity;
		PVector location = new PVector();
		public void show() {
			float gray = map(location.y/height, 0,1,0,360);
			fill(gray,50,100,100);
			noStroke();
			ellipseMode(CENTER);
			ellipse(location.x,location.y, size,size);
		}
		public void hit() {
			size -= 10f;
			if(size <= 0) {
				reset();
			}
		}
		public void move() {
			location.add(velocity);
			if(location.y > height+size) {
				reset();
			}
		}
		public void reset() {
			location.x = random(0,width);
			location.y = -50;
			velocity = new PVector(random(-7f,7f),0.8f);
			size = 100f;
		}
		public void bounce() {
			if(location.x  >  width) {
				location.x  =  width;
				velocity.x *= -1;
			}
			if( location.x  < 0) {
				location.x  = 0;
				velocity.x *= -1;
			}
		}
	}
}
