package usingProcessing;

import peasy.PeasyCam;
import processing.core.PApplet;

public class WaveBomb extends PApplet{
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] {WaveBomb.class.getName()});
	}

	PeasyCam cam;
	public void settings() {
		size(800,600);
	}
	public void setup() {
		smooth();
//		cam = new PeasyCam(this, 100);
	}
	public void draw(){
		background(255);
		noFill();
		translate(width/2,height/2);
		rect(0,0,10,80);
	}
}
