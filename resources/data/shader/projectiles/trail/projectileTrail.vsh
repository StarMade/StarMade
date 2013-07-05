#version 120

void main()
{
	
	gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xyz, 1.0);
	gl_TexCoord[0].s = mod(floor(gl_MultiTexCoord0.w * 2.0), 2.0);
	gl_TexCoord[0].t = gl_MultiTexCoord0.z;
	gl_TexCoord[0].z = gl_MultiTexCoord0.y;
	
}