package com.zengine;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class Shader {

	private int id;
	private Map<String, Integer> locationCache = new HashMap<>();
	
	public Shader(String vertex, String fragment) {
		this.id = Shader.load(vertex, fragment);
	}
	
	private int getUniformLocation(String name) {
		if(locationCache.containsKey(name)) {
			return locationCache.get(name);
		}
		int result = glGetUniformLocation(id, name);
		if(result == -1) {
			System.err.println("Could not find uniform variable " + name);
			System.exit(-1);
		} else {
			locationCache.put(name, result);
		}
		return result;
	}
	
	public void setUniform1i(String name, int value) {
		glUniform1i(getUniformLocation(name), value);
	}
	
	public void setUniform1f(String name, float value) {
		glUniform1f(getUniformLocation(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
		glUniform2f(getUniformLocation(name), x, y);
	}
	
	public void setUniform3f(String name, Vector3f v) {
		glUniform3f(getUniformLocation(name), v.x, v.y, v.z);
	}
	
	public void setUniformMat4(String name, Matrix4f matrix) {
		FloatBuffer buff = BufferUtils.createFloatBuffer(16);
		matrix.get(buff);
		glUniformMatrix4fv(getUniformLocation(name), false, buff);
	}
	
	public void bind() {
		glUseProgram(id);
	}
	
	public void unbind() {
		glUseProgram(0);
	}
	
	public static int load(String vertPath, String fragPath) {
		String vert = FileUtilities.loadAsString(vertPath);
		String frag = FileUtilities.loadAsString(fragPath);
		
		return create(vert, frag);
	}

	private static int create(String vert, String frag) {
		int program = glCreateProgram();
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vertID, vert);
		glShaderSource(fragID, frag);
		
		glCompileShader(vertID);
		if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(vertID));
			System.err.println("Failed to compile vertex shader.");
			return -1;
		}
		
		glCompileShader(fragID);
		if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(fragID));
			System.err.println("Failed to compile fragment shader.");
			return -1;
		}
		
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		glLinkProgram(program);
		glValidateProgram(program);
		
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		
		return program;
	}
}
