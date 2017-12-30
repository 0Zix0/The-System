#version 330

layout (location = 0) in vec3 inVertexPosition;
layout (location = 1) in vec2 inTextureCoords;
layout (location = 2) in vec3 inForeground;
layout (location = 3) in vec3 inBackground;

out vec2 pass_texCoords;
out vec3 pass_fragPos;
out vec3 pass_foreground;
out vec3 pass_background;

uniform mat4 vw_matrix = mat4(1.0);
uniform mat4 md_matrix = mat4(1.0);
uniform mat4 pr_matrix = mat4(1.0);

void main()
{
	gl_Position = pr_matrix * vw_matrix * md_matrix * vec4(inVertexPosition.x, inVertexPosition.y, inVertexPosition.z, 1.0);

	pass_texCoords = inTextureCoords;
	pass_fragPos = vec3(md_matrix * vec4(inVertexPosition, 1.0));
	pass_foreground = inForeground;
	pass_background = inBackground;
}
