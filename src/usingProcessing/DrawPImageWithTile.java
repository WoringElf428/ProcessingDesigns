package usingProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class DrawPImageWithTile extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { DrawPImageWithTile.class.getName()});
	}
	PImage img;
	int sizeMax = 10;
	ArrayList<Bubble> bubbles = new ArrayList<>();
	public void settings() {
		size(450,450);
	}
	public void setup() {
		noStroke();
		img = loadImage("渋谷凛ジャケ.jpg");
		img.resize(width, height);
		for(int i = 0; i < 10;i++) {
			PVector loc = new PVector(random(width),random(height));
			bubbles.add(new Bubble(loc));
		}
	}
	public void draw() {
		background(0);
//		image(img, 0, 0);

		for(int i = 0; i < bubbles.size(); i++) {
			bubbles.get(i).show();
		}
		for(int i = 0; i < bubbles.size(); i++) {
			if(!bubbles.get(i).isDead) {
				boolean ex = bubbles.get(i).checkPix();
				if(!ex) {
					PVector loc;
					while(true) {
						loc = new PVector(random(width),random(height));
						int c = get((int)loc.x,(int)loc.y);
						if(red(c)+green(c)+blue(c) == 0) { break;}
					}
					bubbles.add(new Bubble(loc));
					bubbles.get(i).isDead = true;
				}
			}
		}

	}

	public void mouseClicked() {
		bubbles.clear();
		for(int i = 0; i < 10;i++) {
			PVector loc = new PVector(random(0,width),random(0,height));
			bubbles.add(new Bubble(loc));
		}
	}

	class Bubble{
		float size,expand;
		int col;
		boolean expanding,isDead;
		PVector loc;
		public Bubble(PVector l) {
			loc = l;
			size = 0;
			expand = 2f;
			expanding = true;
			isDead = false;
			col = img.get((int)l.x, (int)l.y);
		}

		public void show() {
			if(expanding) {
				size += expand;
			}
			fill(col);
//			ellipse(loc.x,loc.y,size,size);
			rect(loc.x,loc.y,size,size);
		}

		public boolean checkPix() {
			float nextSize = size + expand;
			for(float i = 0; i < TWO_PI; i+=0.1f) {
				int x = (int) (cos(i)*nextSize/2f + loc.x);
				int y = (int) (sin(i)*nextSize/2f + loc.y);
				int c = get(x,y);
				if(red(c)+green(c)+blue(c) > 0 || size > sizeMax) {
					expanding = false;
				}
			}
			return expanding;
		}
	}
}
