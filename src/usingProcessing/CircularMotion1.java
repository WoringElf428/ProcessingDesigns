package usingProcessing;

import java.util.ArrayList;

import processing.core.PApplet;

public class CircularMotion1 extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {CircularMotion1.class.getName()});
	}
	int R = 45;
	ArrayList<Satellite> list = new ArrayList<>();

	public void settings() {
		size(800,600);
//		fullScreen();
	}

	public void setup() {

		for(int n = 1; R*n < height; n+=2) {
			float num = 360/(n*4);
			for(int i = 0; i < 360; i += num*2){
				list.add(new Satellite(n,i,	0.06f * n));
			}
		}
	}

	public void draw() {
		fill(0,33);
		rect(0,0,width,height);

		stroke(120, 50);

		for(Satellite s:list) {
			s.draw();
			s.update();
		}
	}

	public class Satellite{
		float n;
		float sheta,i,x,y,t;

		public Satellite(int n,float i,float theta) {
			this.n = n;
			this.i = i;
			this.sheta = theta;
			t = 0.01f;
		}

		public void update() {
		    i += sheta;
//		    n += 0.05 * cos(t);
		}
		public void draw() {
			x = R*n*cos(radians(i)) +width/2;
			y = R*n*sin(radians(i)) +height/2;

			float g = noise(x*t,y*t) * 255;
			fill(g,g,255);
			ellipse(x,y,5,5);

			line(width/2,height/2,x,y);

//			t += 0.1f;
		}
	}
}
