package com.zengine.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.zengine.BufferUtilities;

public class BasicTexture {

	private int textureID;
	private int width;
	private int height;
	
	public BasicTexture(String path) {
		load(path);
	}
	
	public BasicTexture(int width, int height, ByteBuffer buffer) {
		this.width = width;
		this.height = height;
		textureID = OpenGL.genTextures();
		OpenGL.bindTexture(GL11.GL_TEXTURE_2D, textureID);
		OpenGL.texImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		
		OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		OpenGL.bindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	private void load(String file) {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(file));
			width = image.getWidth();
			height = image.getHeight();
			int[] pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			
			int[] data = new int[width * height];
			for(int i = 0; i < width * height; i++) {
				int a = (pixels[i] & 0xff000000) >> 24;
				int r = (pixels[i] & 0xff0000) >> 16;
				int g = (pixels[i] & 0xff00) >> 8;
				int b = (pixels[i] & 0xff);
				
				data[i] = a << 24 | b << 16 | g << 8 | r;
			}
			textureID = OpenGL.genTextures();
			OpenGL.bindTexture(GL11.GL_TEXTURE_2D, textureID);
			OpenGL.texImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, BufferUtilities.createIntBuffer(data));
			
			OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
			OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

			OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			OpenGL.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

			OpenGL.bindTexture(GL11.GL_TEXTURE_2D, 0);
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Error while loading texure '" + file + "'!");
		}
	}
	
	public void bind() {
		OpenGL.bindTexture(GL11.GL_TEXTURE_2D, textureID);
	}
	
	public void unbind() {
		OpenGL.bindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
