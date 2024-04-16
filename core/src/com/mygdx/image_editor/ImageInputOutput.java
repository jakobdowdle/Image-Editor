package com.mygdx.image_editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class ImageInputOutput {
	public static ImageInputOutput Instance;
	public ImageInputOutput() {
		Instance = this;
	}
	
	public Pixmap loadImage(String filePath) {
		byte[] fileBytes = Gdx.files.internal(filePath).readBytes();
		int[] fileIntData = Util.unsignBytes(fileBytes);
		if(fileBytes[0] != 'B' || fileBytes[1] != 'M') {
			 System.out.println(filePath + " is NOT a bitmap image");
			 return null;
		}
		byte[] fileSize = {fileBytes[2], fileBytes[3], fileBytes[4], fileBytes[5]};
		byte[] start = {fileBytes[10], fileBytes[11], fileBytes[12], fileBytes[13]};
		byte[] widthBytes = {fileBytes[18], fileBytes[19], fileBytes[20], fileBytes[21]};
		byte[] heightBytes = {fileBytes[22], fileBytes[23], fileBytes[24], fileBytes[25]};
		byte[] bitsPerPixel = {fileBytes[28], fileBytes[29]};
		int startPoint = Util.bytesToInt(start);
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
		if(bytesPerPixel != 3) {System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); return null;}
		
		Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
		int r,g,b;
		int x = 0;
		int y = height;
		for(int i = startPoint; i < fileBytes.length - 3; i += 3) {
			b = fileIntData[i];
			g = fileIntData[i + 1];
			r = fileIntData[i + 2];
			pixels.setColor(r/256f, g/256f, b/256f, 1);
			
			pixels.drawPixel(x, y);
			x++;
			if(x >= width) {
				x = 0;
				y--;
			}
		}
		return pixels;


	}

}

