package usingProcessing;

import java.util.ArrayList;

import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

public class MagicalSponge extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {MagicalSponge.class.getName()});
	}

	PeasyCam cam;
	ArrayList<Sponge> sponges = new ArrayList<>();
	public void settings() {
		size(800,600,P3D);
	}
	public void setup() {
		smooth();
		cam = new PeasyCam(this, 100);
		Sponge s = new Sponge(0,0,0,900);
		sponges.add(s);
	}
	public void draw() {
		background(0);
		for(Sponge s:sponges) {
			s.show();
		}
	}
	public void mouseClicked() {
		ArrayList<Sponge> next = new ArrayList<>();
		for(Sponge s:sponges) {
			ArrayList<Sponge> children = s.generate();
			next.addAll(children);
		}
		if(next.isEmpty()) {
			return;
		}
		sponges = next;
	}

	class Sponge{
		PVector pos;
		float r;

		public Sponge(float x, float y, float z, float r) {
			pos = new PVector(x,y,z);
			this.r = r;
		}

		public void show() {
			pushMatrix();
			translate(pos.x, pos.y, pos.z);
			fill(255);
			box(r);
			popMatrix();
		}

		public ArrayList<Sponge> generate() {
			ArrayList<Sponge> children = new ArrayList<>();
			float _r = r/3;
			if(_r < 30) {
				return children;
			}

			for(int x = -1; x < 2; x++) {
				for(int y = -1; y < 2; y++) {
					for(int z = -1; z < 2; z++) {
						if((x == 0 && y == 0)||
							(z == 0 && y == 0)||
							(x == 0 && z == 0)) {
							continue;
						}
						Sponge s = new Sponge(pos.x+_r*x,pos.y+_r*y,pos.z+_r*z,_r);
						children.add(s);
					}
				}
			}
			return children;
		}
	}
}
