#version 110

uniform mat4 ModelViewMatrix;

uniform mat4 ProjectionMatrix;

uniform vec3 CamPosition;

void main()
{
 	gl_FrontColor = gl_Color; 
	gl_Position = ModelViewMatrix * vec4(gl_Vertex.xyz + CamPosition,1.0);
	
}