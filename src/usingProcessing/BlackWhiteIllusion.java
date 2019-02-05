package usingProcessing;

import java.util.ArrayList;

import processing.core.PApplet;

public class BlackWhiteIllusion extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { BlackWhiteIllusion.class.getName() });
	}

	ArrayList<float[]>  triangles_x = new ArrayList<>();
	ArrayList<float[]>  triangles_y = new ArrayList<>();
	ArrayList<float[]>  cornors = new ArrayList<>();
	float[] radius,starts,ends,speeds;
	boolean[] triple;
	int[] near;
//	float[] triangle_x = new float[3];
//	float[] triangle_y = new float[3];
//	float[] vertexes = new float[4];
	int t;


	public void settings() {
		size(600, 600);
	}

	public void setup() {
		frameRate(30);

		t = width/100;
		starts = new float[t];
		ends = new float[t];
		radius = new float[t];
		speeds = new float[t];
		triple = new boolean[t];
		near = new int[t];

		for(int j = 0; j < t; j ++) {
			float r = (j+1) * 50;
			float[] triangle_x = new float[3];
			float[] triangle_y = new float[3];
			for (int i = 0; i < 3; i++) {
				float x = r * cos(-i * HALF_PI);
				float y = r * sin(-i * HALF_PI);
				triangle_x[i] = x;
				triangle_y[i] = y;
			}
			float start_point = triangle_x[0];
			float zero_point = triangle_x[2];

			float bottom = dist(triangle_x[0], triangle_y[0],
					triangle_x[2], triangle_y[2]);
			float mini = bottom / 4;

			float[] vertexes = new float[4];
			for (int i = 0; i < 4; i++) {
				vertexes[i] = triangle_x[2] + mini * (i + 1);
				if (vertexes[i] <= zero_point) {
					vertexes[i] = triangle_x[0];
					check(i, j);
					near[j] = (i == 3) ? 0 : i + 1;
				}
			}

			cornors.add(vertexes);
			triangles_x.add(triangle_x);
			triangles_y.add(triangle_y);

			float speed = 0.75f - 0.05f * j;
			speeds[j] = speed;
			starts[j] = start_point;
			ends[j] = zero_point;
			radius[j] = r;
			triple[j] = false;
		}
	}

	public void draw() {
		background(0);

		translate(width / 2, height / 2);
		for(int i = radius.length-1; i >= 0; i--) {
			createSqr(i);
		}
	}

	public void createSqr(int time) {
		for(int j = 0; j < 4; j++) {
			rotate(HALF_PI);
			createUnitTri(time);
		}
	}

	public void createUnitTri(int time) {
		pushMatrix();
		translate(0,radius[time]);
//		stroke(137);
		noStroke();
		fill(255);

		beginShape();
		for (int i = 0; i < 3; i++) {
			vertex(triangles_x.get(time)[i], triangles_y.get(time)[i]);
		}
		endShape(CLOSE);

		showBlack(time);

		for (int i = 0; i < 4; i++) {
			cornors.get(time)[i] -= speeds[time];
			if (cornors.get(time)[i] <= ends[time]) {
				cornors.get(time)[i] = starts[time];
				check(i,time);
				near[time] = (i == 3) ? 0 : i + 1;
			}
		}
		popMatrix();
	}

	public void showBlack(int time) {
		fill(0);
		//noStroke();

		if (triple[time]) {
			if (near[time] == 1) {
				beginShape();
				vertex(ends[time], triangles_y.get(time)[2]);
				vertex(cornors.get(time)[1], triangles_y.get(time)[2]);
				vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
				endShape(CLOSE);

				beginShape();
				vertex(cornors.get(time)[0], triangles_y.get(time)[2]);
				vertex(starts[time], triangles_y.get(time)[2]);
				vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
				endShape(CLOSE);

				beginShape();
				vertex(cornors.get(time)[2], triangles_y.get(time)[2]);
				vertex(cornors.get(time)[3], triangles_y.get(time)[2]);
				vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
				endShape(CLOSE);
			} else if (near[time] == 3) {
				beginShape();
				vertex(ends[time], triangles_y.get(time)[2]);
				vertex(cornors.get(time)[3], triangles_y.get(time)[2]);
				vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
				endShape(CLOSE);

				beginShape();
				vertex(cornors.get(time)[2], triangles_y.get(time)[2]);
				vertex(starts[time], triangles_y.get(time)[2]);
				vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
				endShape(CLOSE);

				beginShape();
				vertex(cornors.get(time)[0], triangles_y.get(time)[2]);
				vertex(cornors.get(time)[1], triangles_y.get(time)[2]);
				vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
				endShape(CLOSE);
			}
		} else {
			beginShape();
			vertex(cornors.get(time)[0], triangles_y.get(time)[2]);
			vertex(cornors.get(time)[1], triangles_y.get(time)[2]);
			vertex(triangles_x.get(time)[1], triangles_y.get(time)[1]);
			endShape(CLOSE);

			beginShape();
			vertex(cornors.get(time)[2], triangles_y.get(time)[2]);
			vertex(cornors.get(time)[3], triangles_y.get(time)[2]);
			vertex(triangles_x.get(time)[1],triangles_y.get(time)[1]);
			endShape(CLOSE);
		}
	}

	public void check(int index, int time) {
		if (index == 0 || index == 2) {
			triple[time] = true;
		} else if (index == 1 || index == 3) {
			triple[time] = false;
		}
	}

}
