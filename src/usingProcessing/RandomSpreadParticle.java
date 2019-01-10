package usingProcessing;

import processing.core.PApplet;
import processing.core.PVector;

public class RandomSpreadParticle extends PApplet {
	int num = 1000;
	TestPVector[] mys = new TestPVector[num];

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {RandomSpreadParticle.class.getName()});
	}

	public  void settings() {
		size(800,600);
	}
	public void setup() {
		frameRate(60);
		for(int i = 0; i < num; i++) {
			mys[i] = new TestPVector();
			mys[i].location.set(width/2.0f, height/2.0f);
			mys[i].acceleration.set(random(-10, 10), random(-10, 10));
			mys[i].gravity.set(0.0f,0.1f);
			mys[i].friction = 0.01f;
		}
	}
	public void draw() {
		fill(0, 75);
		rect(0, 0, width, height);
		noStroke();
		fill(255);
		for(int i = 0; i < num; i++) {
			mys[i].update_loc();
			mys[i].draw();
			mys[i].bounce();
		}
	}

	public void mouseReleased() {
		for(int i = 0; i < num; i++) {
			float angle = random(PI + 2.0f);
			float length = random(20.0f);
			PVector force = new PVector(cos(angle) * length, sin(angle) * length);
			mys[i].addForce(force);
		}
	}

	public class TestPVector  {
		PVector location;
		PVector acceleration;
		//PVector force;
		PVector velocity;
		PVector gravity;
		PVector min;
		PVector max;
		float mass;
		float friction;
		float radius;

		public TestPVector() {
			mass = 1.0f;
			friction = 0.01f;
			radius = 4.0f;
			//位置、加速度、速度、質量、初期の力を初期化
			location = new PVector(0f, 0f);
			velocity = new PVector(0f, 0f);
			gravity = new PVector(0f, 0f);
			//force = new PVector(12.0f, 8.0f);
			acceleration =  new PVector(0f, 0f);
			//運動範囲
			min = new PVector(0.0f,0.0f);
			max = new PVector(width,height);
		}

		public void draw() {
			ellipse(location.x, location.y,mass * radius * 2, mass * radius * 2);
		}

		public void update_loc() {
					//加速度に重力を追加
					acceleration.add(gravity);
					//速度に運動量を追加
					velocity.add(acceleration);
					//運動量に摩擦力をかける
					velocity.mult(1.0f-friction);
					//位置に運応用を追加
					location.add(velocity);
					//加速度0
					acceleration.set(0 ,0);
		}

		public void addForce(PVector force) {
			force.div(mass);
			acceleration.add(force);
		}

		public void bounce() {
			if(location.x > max.x) {
				location.x = max.x;
				velocity.x *= -1;
			}
			if(location.x < min.x) {
				location.x = min.x;
				velocity.x *= -1;
			}
			if(location.y > max.y) {
				location.y = max.y;
				velocity.y *= -1;
			}
			if(location.y < min.y) {
				location.y = min.y;
				velocity.y *= -1;
			}
		}
		public void throughWall() {
			if(location.x > max.x) {
				location.x = max.x;
			}
			if(location.x < min.x) {
				location.x = min.x;
			}
			if(location.y > max.y) {
				location.y = max.y;
			}
			if(location.y < min.y) {
				location.y = min.y;
			}
		}
	}
}
