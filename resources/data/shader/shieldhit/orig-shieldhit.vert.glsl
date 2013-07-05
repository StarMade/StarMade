#version 120


uniform int m_CollisionNum;
uniform vec3 m_Collisions[16];
uniform float m_TexCoordMult;
varying float dists[16];


void main(){


	for (int i=0;i<m_CollisionNum && i<16;i++) {
		dists[i] = distance(VertexPosition, m_Collisions[i]);
	}
	gl_TexCoord[0] = gl_MultiTexCoord0 * m_TexCoordMult;
    gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xyz, 1.0);
}