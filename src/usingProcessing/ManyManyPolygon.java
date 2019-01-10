package usingProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class ManyManyPolygon extends PApplet {

	public static void main(String[] args) {
		PApplet.main(new String[] {ManyManyPolygon.class.getName()});
	}
	int R = 15;
	ArrayList<float[]> orbits_x,orbits_y;
	ArrayList<Ball> balls = new ArrayList<>();

	public void settings() {
		size(800,600);
	}

	public void setup() {
		colorMode(HSB, 360, 256, 256);
		orbits_x = new ArrayList<>();
		orbits_y = new ArrayList<>();

		for(int i = 3; i*R*2 < height;i+=2) {
			float theta = 360/i;
			float[] orbit_x = new float[i];
			float[] orbit_y = new float[i];
			for(int j = 0; j < i; j++) {
				orbit_x[j] = i*R*cos(radians(theta*j));
				orbit_y[j] = i*R*sin(radians(theta*j));
			}
			balls.add(new Ball(orbit_x,orbit_y));
			orbits_x.add(orbit_x);
			orbits_y.add(orbit_y);
		}
	}

	public void draw() {
		background(360);
		noFill();
		strokeWeight(3);
		translate(width/2, height/2);
		rotate(radians(-90));
		point(0,0);

		for(int i = 0; i < orbits_x.size(); i++) {
			beginShape();
			for(int j = 0; j <  orbits_x.get(i).length; j++) {
				stroke(10*j,256,256);
				vertex(orbits_x.get(i)[j], orbits_y.get(i)[j]);
			}
			endShape(CLOSE);
		}
		for(Ball b:balls) {
			b.move();
			b.update();
		}
	}

	public class Ball{
		PVector position = new PVector();
		PVector verosity = new PVector();
		float[] orbit_x ,orbit_y;
		float size = 10f;
		int target;
		public Ball(float[] x, float[] y) {
			orbit_x = x;
			orbit_y = y;
			position.x = orbit_x[0];
			position.y = orbit_y[0];
			this.verosity = new PVector(orbit_x[1]-orbit_x[0],orbit_y[1]-orbit_y[0]);
//			verosity.normalize();
			verosity.div(60f);
			target = 1;
		}
		public void turn() {
			if(target == orbit_x.length) {
				this.verosity = new PVector(orbit_x[0]-orbit_x[target-1],
											 orbit_y[0]-orbit_y[target-1]);
				target = 0;
			}else if(target == 0){
				this.verosity = new PVector(orbit_x[target]-orbit_x[orbit_x.length-1],
											 orbit_y[target]-orbit_y[orbit_x.length-1]);
			}else {
				this.verosity = new PVector(orbit_x[target]-orbit_x[target-1],
											 orbit_y[target]-orbit_y[target-1]);
			}
//			verosity.normalize();
			verosity.div(60f);
		}

		public void update() {
			if(dist(position.x, position.y, orbit_x[target], orbit_y[target]) < 0.5f) {
				target++;
				turn();
			}
			position.add(verosity);
		}
		public void move() {
			fill(150);
			noStroke();
			ellipse(position.x, position.y, size, size);
		}
	}
}
