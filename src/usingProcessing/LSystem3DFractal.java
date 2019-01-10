package usingProcessing;

import java.util.ArrayList;

import peasy.PeasyCam;
import processing.core.PApplet;
import toxi.geom.Vec3D;

public class LSystem3DFractal extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {LSystem3DFractal.class.getName()});
	}
	PeasyCam cam;
	Stick s;
	ArrayList<Stick> allStick = new ArrayList<>();
	float angle1,angle2,angle3,generate;

	public void settings() {
		size(800,600,P3D);
	}

	public void setup() {
		smooth();
		cam = new PeasyCam(this, 100);
		angle1 = random(-90,90);
		angle2 = random(-90,90);
		angle3 = random(-90,90);
		generate = 1;
	}
	public void draw() {
		background(137);
		stroke(255);
		noFill();
		box(800);
		allStick.clear();

		s = new Stick(new Vec3D(0,0,0),new Vec3D(100,0,0),(int)generate,"A");
		allStick.add(s);

		for(Stick s:allStick) {
			s.run();
		}
		updateAngle();
	}
	public void updateAngle() {
		angle1 += 0.01f;
		angle2 += 0.01f;
		angle3 += 0.01f;
		generate += 0.005f;
		if(angle1 > 90) {
			angle1 = 0;
		}
		if(angle2 > 90) {
			angle2 = 0;
		}
		if(angle3 > 90) {
			angle3 = 0;
		}
		if(generate > 15) {
			generate = 1;
		}
	}

	class Stick{
		Vec3D loc,vel,oriLoc;
		int g;
		String axiom;
		public Stick(Vec3D _loc, Vec3D _vel,int _g, String type) {
			loc = _loc;
			vel = _vel;
			g = _g;
			axiom = type;
			oriLoc = loc.copy();

			updateLoc();
			updateDir();
			spawn();
		}
		public void run() {
			display();
		}

		public void spawn() {
			if(g > 0) {
				if(axiom == "A") {
					Stick s1 = new Stick(loc.copy(),vel.copy(),g-1,"A");
					allStick.add(s1);

					Stick s2 = new Stick(loc.copy(),vel.copy(),g-1,"B");
					allStick.add(s2);
				}else if(axiom == "B"){
					Stick s1 = new Stick(loc.copy(),vel.copy(),g-1,"C");
					allStick.add(s1);
				}else if(axiom == "C") {
					Stick s1 = new Stick(loc.copy(),vel.copy(),g-1,"A");
					allStick.add(s1);
				}
			}
		}
		public void display() {
			stroke(255,0,0);
			strokeWeight(4);
			point(loc.x,loc.y,loc.z);

			stroke(255);
			strokeWeight(1);
			line(oriLoc.x,oriLoc.y,oriLoc.z,loc.x,loc.y,loc.z);
		}
		public void updateLoc() {
			loc.addSelf(vel);
		}
		public void updateDir() {
			float angle = 0f;
			switch(axiom) {
			case "A":
				rotate(0,angle1,0);
				break;
			case "B":
				rotate(0,0,angle2);
				break;
			case "C":
				rotate(angle3,0,0);
				break;
			}
		}
		private void rotate(float x,float y,float z) {
			vel.rotateX(x);
			vel.rotateY(y);
			vel.rotateZ(z);
		}
	}
}
