package usingProcessing;

import processing.core.PApplet;

public class LSystemFractal extends PApplet {
	public static void main(String args[]) {
		PApplet.main(new String[] {LSystemFractal.class.getName()});
	}
	float len = 100;
	String axiom = "F";
	float angle;

	public void settings() {
		size(800,600);
	}
	public void setup() {
		angle = radians(25);
	}
	public void draw() {
		background(130);
		resetMatrix();
		translate(width/2, height);
		stroke(255);
		for(int i = 0; i < axiom.length();i++) {
			 char c = axiom.charAt(i);
			 switch(c) {
			 case 'F':
				 line(0,0,0,-len);
				 translate(0, -len);
				 break;
			 case '+':
				 rotate(angle);
				 break;
			 case '-':
				 rotate(-angle);
				 break;
			 case '[':
				 pushMatrix();
				 break;
			 case ']':
				 popMatrix();
				 break;
			 }
		}
	}

	public String rule(char a) {
		return a == 'F'?"FF+[+F-F-F]-[-F+F+F]":String.valueOf(a);
	}

	public String  generate(String s) {
		if(s.length() > 50000) {
			System.out.println("でかすぎ");
			return s;
		}
		String next = "";
		if(len > 4) {
			len *= 0.55f;
		}
		for(int i = 0; i < s.length();i++) {
			char c = s.charAt(i);
			next += rule(c);
		}
		return next;
	}
	public void mouseClicked() {
		 axiom = generate(axiom);
		 System.out.println(axiom);
	}
}
