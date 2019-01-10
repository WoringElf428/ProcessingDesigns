package usingProcessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

public class SolarSystem extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { SolarSystem.class.getName() });
	}

	PeasyCam cam;
	Planet sun;
	Planet[] planets;
	int number;
	final PVector FORCE = new PVector(1,0,1);
	public void settings() {
		size(800, 600, P3D);
	}

	public void setup() {
		cam = new PeasyCam(this, 200);
		sun = new Planet(0, 80, 0);
		number = 1;
		planets = new Planet[number];
		for (int i = 0; i < number; i++) {
			float dis = random(50, 200);
			float size = random(40, 70);
			float vel = random(-6, 6);
			planets[i] = new Planet(dis, size, vel);
//			planets[i].generateSatellite((int) (random(1, 4)));
		}
		sun.satellites = Arrays.asList(planets);

	}

	public void draw() {
		background(0);
		translate(width / 2, height / 2);
		lights();
		sun.show();
		sun.move();
	}

	class Planet {
		float d, s, v, a;
		PVector pos;
		PVector vel;
		List<Planet> satellites = new ArrayList<Planet>();

		public Planet(float distance, float size, float velocity) {
			d = distance;
			s = size;
			v = velocity;
			vel = PVector.random3D();
			vel.mult(d);
			a = 0;
			pos = new PVector(d * cos(radians(a)), d * sin(radians(a)), d * cos(radians(a)));
		}

		public void show() {
			pushMatrix();
			rotate(a, pos.x,pos.y,pos.z);
			translate(pos.x, pos.y, pos.z);

			fill(255);
			noStroke();
			sphere(s);

			for (Planet p : satellites) {
				p.show();
				p.move();
			}
			popMatrix();
		}

		public void move() {
			a += v;
			pos = vel.cross(FORCE);


		}

		public void generateSatellite(int i) {
			for (int k = 0; k < i; k++) {
				float _d = d * 0.5f;
				float _size = random(s * 0.4f, s * 0.6f);
				float _v = v * random(1.0f, 1.3f);
				satellites.add(new Planet(_d, _size, _v));
			}
		}
	}
}
