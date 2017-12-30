package com.zengine.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.zengine.Input;
import com.zengine.Input.Keyboard;
import com.zengine.Input.MouseButton;
import com.zengine.Input.MousePosition;

public class Display {

	private static int width;
	private static int height;
	private static String title;
	
	private static long window = NULL;

	private static GLFWKeyCallback keyCallback;
	private static GLFWMouseButtonCallback mouseButtonCallback;
	private static GLFWCursorPosCallback cursorPosCallback;
	private static GLFWFramebufferSizeCallback framebufferSizeCallback;
	
	private static float unitsWide;
	private static float unitsHigh;
	
	public static boolean init(int width, int height, String title) {
		if(window != NULL) return false;
		if(!glfwInit()) return false;

		Display.unitsWide = width / 100.0f;
		Display.unitsHigh = height / 100.0f;
		Display.width = width;
		Display.height = height;
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if(window == NULL) return false;
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);
	
		glfwSetKeyCallback(window, keyCallback = new Input.Keyboard());
		glfwSetMouseButtonCallback(window, mouseButtonCallback = new Input.MouseButton());
		glfwSetCursorPosCallback(window, cursorPosCallback = new Input.MousePosition());
		glfwSetFramebufferSizeCallback(window, framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
			public void invoke(long window, int width, int height) {
				Display.width = width;
				Display.height = height;
				unitsWide = width / 100.0f;
				unitsHigh = height / 100.0f;
				glViewport(0, 0, width, height);
			}
		});
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		
		GL.createCapabilities();
		
		OpenGL.clearColor(0.0f, 0.0f, 0.0f, 1.0f);
		OpenGL.enable(GL_BLEND);
		OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		OpenGL.activeTexture(GL_TEXTURE0);
		
		OpenGL.enable(GL_DEPTH_TEST);
		
		return true;
	}
	
	public static void setTitle(String title) {
		glfwSetWindowTitle(window, title);
		Display.title = title;
	}
	
	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void update() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	public static float getUnitsHigh() {
		return unitsHigh;
	}
	
	public static float getUnitsWide() {
		return unitsWide;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
}
