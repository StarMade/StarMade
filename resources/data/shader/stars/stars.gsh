#version 120
#extension GL_EXT_geometry_shader4 : enable

uniform float Size2; // Half the width of the quad

uniform mat4 ProjectionMatrix;

varying out vec2 TexCoord;

const float min = 0.0;
const float max = 1.0;

void main()
{
	mat4 m = ProjectionMatrix; 
	
	gl_Position = m * (vec4(-Size2,-Size2,0.0,0.0) +
	gl_PositionIn[0]);
	gl_FrontColor = gl_FrontColorIn[0];
	TexCoord = vec2(min,min);
	EmitVertex();
	
	gl_Position = m * (vec4(Size2,-Size2,0.0,0.0) +
	gl_PositionIn[0]);
	gl_FrontColor = gl_FrontColorIn[0];
	TexCoord = vec2(max,min);
	EmitVertex();
	
	gl_Position = m * (vec4(-Size2,Size2,0.0,0.0) +
	gl_PositionIn[0]);
	gl_FrontColor = gl_FrontColorIn[0];
	TexCoord = vec2(min,max);
	EmitVertex();
	
	gl_Position = m * (vec4(Size2,Size2,0.0,0.0) +
	gl_PositionIn[0]);
	gl_FrontColor = gl_FrontColorIn[0];
	TexCoord = vec2(max,max);
	EmitVertex();
	
	EndPrimitive();
}