package usingProcessing;

import processing.core.PApplet;
import processing.core.PImage;

public class FloyedSteinbergDithering extends PApplet {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main(new String[] { FloyedSteinbergDithering.class.getName() });
	}

	/*
	 * フロイド-スタインバーグ・ディザリング
	 * 画像用ディザリングアルゴリズムであり、画像操作関係のソフトウェアで広く用いられており、
	 * 例えば最大256色までしか使えないGIF形式への変換の際に使われている。
	 * 各ピクセルの量子化誤差をそれに隣接するピクセル群に拡散させることでディザリングを実現するアルゴリズムである。
	 * 隣接ピクセルへの誤差の分配は次のようになる。
	 * 			pixel[x+1][y]   := pixel[x+1][y]  + 7/16 * quant_error
	 * 			pixel[x-1][y+1] := pixel[x-1][y+1]+ 3/16 * quant_error
	 * 			pixel[x][y+1]   := pixel[x][y+1]  + 5/16 * quant_error
	 * 			pixel[x+1][y+1] := pixel[x+1][y+1]+ 1/16 * quant_error
	 */
	PImage img;
	float errR, errG, errB;
	int scale = 4;
	public void settings() {
		img = loadImage("渋谷凛ジャケ.jpg");
		size(img.width * 2, img.height);
	}

	public void setup() {
		image(img, 0, 0);
//		img.filter(GRAY);
		img.loadPixels();
		for (int y = 0; y < img.height - 1; y++) {
			for (int x = 1; x < img.width - 1; x++) {
				int color = img.pixels[index(x, y)];
				float r = red(color);
				float g = green(color);
				float b = blue(color);

				//色数を減らして描画
				float newR = round(scale * r / 255) * (255 / scale);
				float newG = round(scale * g / 255) * (255 / scale);
				float newB = round(scale * b / 255) * (255 / scale);
				img.pixels[index(x, y)] = color(newR, newG, newB);

				errR = r - newR;
				errG = g - newG;
				errB = b - newB;

				int[] plusX = { 1, -1, 0, 1 };
				int[] plusY = { 0, 1, 1, 1 };
				int[] num = { 7, 3, 5, 1 };
				for (int i = 0; i < 4; i++) {
					dithering(num[i], index(x + plusX[i], y + plusY[i]));
				}
			}
		}
		img.updatePixels();
		image(img, img.width, 0);
	}

	public int index(int x, int y) {
		return x + y * img.width;
	}

	public void dithering(int num, int index) {
		int color = img.pixels[index];
		float r = red(color) + errR * num / 16;
		float g = green(color) + errG * num / 16;
		float b = blue(color) + errB * num / 16;
		img.pixels[index] = color(r, g, b);
	}
}
