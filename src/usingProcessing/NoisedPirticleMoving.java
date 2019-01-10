package usingProcessing;

import processing.core.PApplet;
import processing.core.PVector;

public class NoisedPirticleMoving extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {NoisedPirticleMoving.class.getName()});
	}

	int num = 1000;
	Pirticle3[] pirticles = new Pirticle3[num];
	float scale = 0.02f;
	float strength = 0.1f;

	public void settings() {
		size(800,600,P3D);
	}
	public void setup() {
		frameRate(60);
		for(int i = 0; i < num; i++) {
			pirticles[i] = new Pirticle3();
			pirticles[i].location.set(random(width),random(height),random(height/2));
			pirticles[i].gravity.set(0, 0, 0);
		}
		background(255);
	}
	public void draw() {
		noStroke();

		fill(0,3);
		for(int i = 0; i < num; i++) {
			PVector force = new PVector();
			force.x = cos(4*PI*noise(pirticles[i].location.x * scale,
									pirticles[i].location.y*scale,
									pirticles[i].location.z*scale));
			force.y = sin(4*PI*noise(pirticles[i].location.x * scale,
									pirticles[i].location.y*scale,
									pirticles[i].location.z*scale));
			/* cos、sinが0に近づく
			 * →forceが小さくなる
			 * 　→位置の変化が弱まる
			 * 　　→noiseによる影響が小さくなる
			 * 　　　→forceがなくなると位置の変化がなくなり、止まる。
			 *
			 */
			force.mult(strength);
			pirticles[i].addForce(force);
			pirticles[i].update_loc();
			pirticles[i].draw();
			pirticles[i].throughWall();
		}
	}

	public void mousePressed() {
		noiseSeed(round(random(1000)));
		scale = 0.02f;
		for(int i = 0; i < num; i++) {
			pirticles[i].location.set(random(width),random(height),random(height/2));
		}
		background(255);
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
			radius = 1f;
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
