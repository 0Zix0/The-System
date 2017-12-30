package com.zengine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL41.*;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.opengl.GL44.*;
import static org.lwjgl.opengl.GL45.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL15;

public class OpenGL {

	public static void clearColor(float r, float g, float b, float a) {
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		checkForErrors("glClearColor");
	}
	
	public static void enable(int target) {
		glEnable(target);
		checkForErrors("glEnable");
	}
	
	public static void blendFunc(int sFactor, int dFactor) {
		glBlendFunc(sFactor, dFactor);
		checkForErrors("glBlendFunc");
	}
	
	public static void activeTexture(int texture) {
		glActiveTexture(texture);
		checkForErrors("glActiveTexture");
	}
	
	public static void clear(int mask) {
		glClear(mask );
		checkForErrors("glClear");
	}

	public static int genBuffers() {
		int buf = glGenBuffers();
		checkForErrors("glGenBuffers");
		return buf;
	}
	
	public static int genVertexArrays() {
		int arr = glGenVertexArrays();
		checkForErrors("glGenVertexArrays");
		return arr;
	}
	
	public static void bindVertexArray(int arr) {
		glBindVertexArray(arr);
		checkForErrors("glBindVertexArray");
	}
	
	public static void bindBuffer(int target, int buffer) {
		glBindBuffer(target, buffer);
		checkForErrors("glBindBuffer");
	}
	
	public static void bufferData(int target, FloatBuffer data, int usage) {
		glBufferData(target, data, usage);
		checkForErrors("glBufferData");
	}
	
	public static void bufferData(int target, IntBuffer data, int usage) {
		glBufferData(target, data, usage);
		checkForErrors("glBufferData");
	}
	
	public static void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointerOffset) {
		glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
		checkForErrors("glVertexAttribPointer");
	}
	
	public static void enableVertexAttribArray(int index) {
		glEnableVertexAttribArray(index);
		checkForErrors("glEnableVertexAttribArray");
	}
	
	public static void drawElements(int mode, int count, int type, long indicesOffset) {
		glDrawElements(mode, count, type, indicesOffset);
		checkForErrors("glDrawElements");
	}
	
	public static int genFramebuffers() {
		int fb = glGenFramebuffers();
		checkForErrors("glGenFramebuffers");
		return fb;
	}
	
	public static void bindFramebuffer(int target, int framebuffer) {
		glBindFramebuffer(target, framebuffer);
		checkForErrors("glBindFramebuffer");
	}
	
	public static void drawBuffer(int buf) {
		glDrawBuffer(buf);
		checkForErrors("glDrawBuffer");
	}
	
	public static int genTextures() {
		int tex = glGenTextures();
		checkForErrors("glGenTextures");
		return tex;
	}
	
	public static void bindTexture(int target, int texture) {
		glBindTexture(target, texture);
		checkForErrors("glBindTexture");
	}

	public static void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
		glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
		checkForErrors("glTexImage2D");
	}
	
	public static void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
		checkForErrors("glTexImage2D");
	}
	
	public static void texParameteri(int target, int pname, int param) {
		glTexParameteri(target, pname, param);
		checkForErrors("glTexParameteri");
	}
	
	public static void deleteBuffers(int buffer) {
		glDeleteBuffers(buffer);
		checkForErrors("glDeleteBuffers");
	}
	
	public static void deleteVertexArrays(int array) {
		glDeleteVertexArrays(array);
		checkForErrors("glDeleteVertexArrays");
	}
	
	public static void bufferData(int target, long size, int usage) {
		glBufferData(target, size, usage);
		checkForErrors("glBufferData");
	}
	
	public static ByteBuffer mapBuffer(int target, int access) {
		ByteBuffer res = glMapBuffer(target, access);
		checkForErrors("glMapBuffer");
		return res;
	}
	
	public static boolean unmapBuffer(int target) {
		boolean res = glUnmapBuffer(target);
		checkForErrors("glMapBuffer");
		return res;
	}

	public static void texImage2D(int glTexture2d, int level, int glRgba, int width, int height, int border,
			int glRgba2, int glUnsignedByte, IntBuffer createIntBuffer) {
		glTexImage2D(glTexture2d, level, glRgba, width, height, border, glRgba2, glUnsignedByte, createIntBuffer);
		checkForErrors("glTexImage2D");
	}
	
	public static void checkForErrors(String funname) {
		int err;
		while((err = glGetError()) != GL_NO_ERROR) {
			String msg = "";
			if(err == GL_INVALID_ENUM) {
				msg += "GL_INVALID_ENUM";
			} else if(err == GL_INVALID_VALUE) {
				msg += "GL_INVALID_VALUE";
			} else if(err == GL_INVALID_OPERATION) {
				msg += "GL_INVALID_OPERATION";
			} else if(err == GL_STACK_OVERFLOW) {
				msg += "GL_STACK_OVERFLOW";
			} else if(err == GL_STACK_UNDERFLOW) {
				msg += "GL_STACK_UNDERFLOW";
			} else if(err == GL_OUT_OF_MEMORY) {
				msg += "GL_OUT_OF_MEMORY";
			} else if(err == GL_INVALID_FRAMEBUFFER_OPERATION) {
				msg += "GL_INVALID_FRAMEBUFFER_OPERATION";
			} else if(err == GL_CONTEXT_LOST) {
				msg += "GL_CONTEXT_LOST";
			}
			msg += " in " + funname;
			System.err.println(msg);
			System.exit(-1);
		}
	}
}
