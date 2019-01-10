package usingProcessing;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PSurface;

public class Window{
	  Graphics2D g2;
	  public static void main(String[] args){
	    Window window=new Window();
	  }
	  Window(){
	    JFrame frame =new JFrame();
	    frame.setSize(400,400);
	    frame.setLayout(new BorderLayout());
	    PApplet second=new Applet(frame);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	  }

	public class Applet extends PApplet {
		JFrame frame;
		float strength = 1400.0f;		//ノイズの強さ
		float noiseScale = 0.002f;		//ノイズの大きさ（スケール）
		int step = 6;					//グリッドの細かさ

	  Applet(JFrame frame) {
	    super();
	    //this.frame=frame;
	    try {
	      java.lang.reflect.Method handleSettingsMethod =
	        this.getClass().getSuperclass().getDeclaredMethod("handleSettings", null);
	      handleSettingsMethod.setAccessible(true);
	      handleSettingsMethod.invoke(this, null);
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }

	      PSurface surface = super.initSurface();
	      Canvas canvas=(Canvas)surface.getNative();
	      surface.placeWindow(new int[]{0, 0}, new int[]{0, 0});
	      frame.add(canvas, BorderLayout.CENTER);
	      frame.add(new JButton(), BorderLayout.NORTH);
	      //this.showSurface();
	      this.startSurface();
	    }

	    public void settings(){
	      size(300, 300);
	    }
	    public void setup(){
	      background(0, 0, 0);
	      smooth();
	      strokeWeight(5);
	    }
	    public void draw(){
	      noStroke();
	      fill(0,10);
	      rect(0,0,width,height);
	      stroke(255);
	      if (mousePressed) {
	        line(mouseX,mouseY,pmouseX,pmouseY);
	      }
	    }

	  }
	}