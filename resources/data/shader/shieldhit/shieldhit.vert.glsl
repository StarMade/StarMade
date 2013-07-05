#version 120

uniform vec3 m_Collisions[8];
uniform float m_TexCoordMult;
uniform int m_CollisionNum;
//some crappy ATI cards do not support varying arrays
varying float dist0;
varying float dist1;
varying float dist2;
varying float dist3;
varying float dist4;
varying float dist5;
varying float dist6;
varying float dist7;


void main(){

	dist0 = length(gl_Vertex.xyz - m_Collisions[0]);
	dist1 = length(gl_Vertex.xyz - m_Collisions[1]);
	dist2 = length(gl_Vertex.xyz - m_Collisions[2]);
	dist3 = length(gl_Vertex.xyz - m_Collisions[3]);
	dist4 = length(gl_Vertex.xyz - m_Collisions[4]);
	dist5 = length(gl_Vertex.xyz - m_Collisions[5]);
	dist6 = length(gl_Vertex.xyz - m_Collisions[6]);
	dist7 = length(gl_Vertex.xyz - m_Collisions[7]);
	
	gl_TexCoord[0] = gl_MultiTexCoord0 * m_TexCoordMult;
    gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xyz, 1.0);
}