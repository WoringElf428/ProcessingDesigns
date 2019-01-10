package usingProcessing;

import processing.core.PApplet;
import processing.core.PVector;

public class UniversalGravitation extends PApplet {
	public static void main(String args[]) {
		PApplet.main(new String[] {UniversalGravitation.class.getName()});
	}

	int num = 2000;
	Pirticle3[] pirticles = new Pirticle3[num];

	public void settings() {
		size(800,600,P3D);
	}

	public void setup() {
		frameRate(60);
		noStroke();
		for(int i = 0; i < num; i++) {
			pirticles[i] = new Pirticle3();
			pirticles[i].location.set(random(width),random(height),random(height/2));
			pirticles[i].gravity.set(0, 0, 0);
		}
	}

	public void draw() {
		background(0);
		fill(0,20);
		rect(0,0,width,height);
		fill(0,255,100);
		for(int j = 0; j < num; j++) {
			for(int i = 0; i < j; i++) {
				pirticles[i].attract(pirticles[j].location, pirticles[j].mass, 2.0f, 800.0f);
			}
			pirticles[j].update_loc();
			pirticles[j].draw();
			pirticles[j].bounce();
		}

	}
	public void mouseDragged() {
		for(int i = 0; i < num; i++) {
			PVector force = new PVector(mouseX, mouseY);
			pirticles[i].attract(force, 200, 5, 20);
		}
	}
	public class Pirticle3{
		PVector location;
		PVector acceleration;
		PVector velocity;
		PVector gravity;
		PVector min;
		PVector max;
		float mass;
		float friction;
		float radius;
		float G;

		public Pirticle3() {
			mass = random(1,2);
			friction = 0.02f;
			radius = 0.5f;
			G = 1.0f;
			//位置、加速度、速度、質量、初期化
			location = new PVector(0f, 0f, 0f);
			velocity = new PVector(0f, 0f, 0f);
			gravity = new PVector(0f, 0f, 0f);
			acceleration =  new PVector(0f, 0f, 0f);
			//運動範囲
			min = new PVector(0.0f,0.0f, 0f);
			max = new PVector(width,height,height/2);
		}

		public void draw() {
			pushMatrix();
			translate(location.x,location.y,location.z);
			ellipse(0,0,mass * radius * 2, mass * radius * 2);
			popMatrix();
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
			acceleration.set(0 ,0, 0);
		}

		public void addForce(PVector force) {
			force.div(mass);
			acceleration.add(force);
		}

		public void attract(PVector center, float _mass, float min, float max) {
			float distance = PVector.dist(center, location);
			distance = constrain(distance,min,max);

			float strangth = G * (mass * _mass) / (distance * distance);

			PVector force = PVector.sub(center, location);
			force.normalize();
			force.mult(strangth);
			addForce(force);
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
			if(location.z > max.z) {
				location.z = max.z;
				velocity.z *= -1;
			}
			if(location.z < min.z) {
				location.z = min.z;
				velocity.z *= -1;
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
			if(location.z > max.z) {
				location.z = max.z;
			}
			if(location.z < min.z) {
				location.z = min.z;
			}
		}
	}
}
