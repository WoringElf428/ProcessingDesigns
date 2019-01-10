package usingProcessing;

import java.util.ArrayList;
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
	final PVector v2 = new PVector(1, 0, 1); //公転時に軌道をx-z平面に近づけるためのベクトル

	public void settings() {
		size(800, 600, P3D);
	}

	public void setup() {
		cam = new PeasyCam(this, 100);
		colorMode(HSB, 360, 100, 100);
		sun = new Planet(0, 300, 0);
		sun.generateSatellite(4, 1);
	}

	public void draw() {
		background(0);
		grid();

		lights();
		sun.show();
	}

	public void grid() {
		int l = 1200;
		stroke(0, 0, 100);
		for (int x = -l; x < l; x += 50) {
			line(x, 0, -l, x, 0, l);
			line(x, -l, 0, x, l, 0);
		}
		for (int y = -l; y < l; y += 50) {
			line(0, y, -l, 0, y, l);
			line(-l, y, 0, l, y, 0);
		}
		for (int z = -l; z < l; z += 50) {
			line(0, -l, z, 0, l, z);
			line(-l, 0, z, l, 0, z);
		}
	}

	class Planet {
		int color;
		float d, s, v, a;
		PVector pos;
		PVector vel;
		List<Planet> satellites = new ArrayList<Planet>();

		public Planet(float distance, float size, float velocity) {
			d = distance;
			s = size;
			v = velocity;

			pos = PVector.random3D();
			pos.mult(d);
			a = 0;
		}

		public void show() {
			pushMatrix();

			PVector r = pos.cross(v2);
			rotate(a, r.x, r.y, r.z);

			stroke(0, 0, 100);
			line(0, 0, 0, pos.x, pos.y, pos.z);

			translate(pos.x, pos.y, pos.z);
			fill(color, 100, 100);
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
		}

		public void generateSatellite(int i, int level) {
			for (int k = 0; k < i; k++) {
				float _size = s / (level * 2);
				float _d = random(s + _size, (s + _size) * 2.5f);
				float _v = random(-0.1f, 0.1f);
				satellites.add(new Planet(_d, _size, _v));
				int c = (int) map(_d, s + _size, (s + _size) * 2.5f, 0, 340);
				satellites.get(k).color = c;
				if (level < 2) {
					int num = (int) random(0, 6);
					satellites.get(k).generateSatellite(num, level + 1);
				}
			}

		}
	}
}
