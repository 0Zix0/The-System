package com.zengine.graphics;

import java.nio.ByteBuffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.zengine.BufferUtilities;
import com.zengine.Spritesheet;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class BatchRenderer extends Renderer {
	
	public static final int RENDERER_MAX_SPRITES = 60000;
	public static final int RENDERER_VERTEX_SIZE = 20;
	public static final int RENDERER_SPRITE_SIZE = RENDERER_VERTEX_SIZE * 4;
	public static final int RENDERER_BUFFER_SIZE = RENDERER_SPRITE_SIZE * RENDERER_MAX_SPRITES;
	public static final int RENDERER_INDICES_SIZE = RENDERER_MAX_SPRITES * 6;
	
	private int vao, vbo, ibo;
	private int count;
	
	private ByteBuffer data = null;
	
	private Spritesheet spritesheet;
	
	public BatchRenderer(Spritesheet spritesheet) {
		this.spritesheet = spritesheet;
		
		vao = OpenGL.genVertexArrays();
		vbo = OpenGL.genBuffers();
		
		OpenGL.bindVertexArray(vao);
		OpenGL.bindBuffer(GL_ARRAY_BUFFER, vbo);
		OpenGL.bufferData(GL_ARRAY_BUFFER, RENDERER_BUFFER_SIZE, GL_DYNAMIC_DRAW);
		OpenGL.enableVertexAttribArray(0);
		OpenGL.enableVertexAttribArray(1);
		OpenGL.vertexAttribPointer(0, 3, GL_FLOAT, false, RENDERER_VERTEX_SIZE, 0);
		OpenGL.vertexAttribPointer(1, 2, GL_FLOAT, false, RENDERER_VERTEX_SIZE, 3 * 4);
		OpenGL.bindBuffer(GL_ARRAY_BUFFER, 0);
		
		int[] indices = new int[RENDERER_INDICES_SIZE];
		int offset = 0;
		for(int i = 0; i < indices.length; i += 6) {
			indices[  i  ] = offset + 0;
			indices[i + 1] = offset + 1;
			indices[i + 2] = offset + 2;
			
			indices[i + 3] = offset + 2;
			indices[i + 4] = offset + 3;
			indices[i + 5] = offset + 0;
			
			offset += 4;
		}
		
		ibo = OpenGL.genBuffers();
		OpenGL.bindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		OpenGL.bufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtilities.createIntBuffer(indices), GL_STATIC_DRAW);
				
		OpenGL.bindVertexArray(0);
	}

	public void submit(Vector3f pos, Vector2f size, Vector4f texCoords) {
		data.putFloat(pos.x);
		data.putFloat(pos.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.x);
		data.putFloat(texCoords.w);

		data.putFloat(pos.x);
		data.putFloat(pos.y + size.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.x);
		data.putFloat(texCoords.y);

		data.putFloat(pos.x + size.x);
		data.putFloat(pos.y + size.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.z);
		data.putFloat(texCoords.y);

		data.putFloat(pos.x + size.x);
		data.putFloat(pos.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.z);
		data.putFloat(texCoords.w);
		
		count += 6;
	}
	
	/*
	public void submit(Vector3f pos, Vector2f size, Vector4f texCoords) {
		data.putFloat(pos.x);
		data.putFloat(pos.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.z);
		data.putFloat(texCoords.y);

		data.putFloat(pos.x);
		data.putFloat(pos.y + size.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.z);
		data.putFloat(texCoords.w);

		data.putFloat(pos.x + size.x);
		data.putFloat(pos.y + size.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.x);
		data.putFloat(texCoords.w);

		data.putFloat(pos.x + size.x);
		data.putFloat(pos.y);
		data.putFloat(pos.z);
		data.putFloat(texCoords.x);
		data.putFloat(texCoords.y);
		
		count += 6;
	}
	*/
	
	public void start() {
		OpenGL.bindBuffer(GL_ARRAY_BUFFER, vbo);
		data = OpenGL.mapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY);
	}
	
	public void render() {
		OpenGL.unmapBuffer(GL_ARRAY_BUFFER);
		OpenGL.bindBuffer(GL_ARRAY_BUFFER, 0);
		
		spritesheet.bind();
		OpenGL.bindVertexArray(vao);
		OpenGL.bindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		OpenGL.drawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
		OpenGL.bindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		OpenGL.bindVertexArray(0);
		spritesheet.unbind();
		count = 0;
	}
}
