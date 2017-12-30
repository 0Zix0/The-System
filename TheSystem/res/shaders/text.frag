#version 330 core

out vec4 color;

in vec2 pass_texCoords;
in vec3 pass_fragPos;
in vec3 pass_color;
in vec3 pass_foreground;
in vec3 pass_background;

uniform sampler2D diffuse;

void main()
{
	color = texture(diffuse, pass_texCoords);
	if(color.x == 1) {
		color.x = pass_foreground.x;
		color.y = pass_foreground.y;
		color.z = pass_foreground.z;
	} else {
		color.x = pass_background.x;
		color.y = pass_background.y;
		color.z = pass_background.z;
	}
}
