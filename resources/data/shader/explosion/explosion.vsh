#version 120
varying float time;
varying float distance;

void main()
{
	
	gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xyz, 1);
	
	time = gl_Vertex.w;
	
	distance = length(gl_Vertex.xyz - gl_MultiTexCoord0.xyz);
	
	gl_TexCoord[0].st = vec2(mod(floor(gl_MultiTexCoord0.w * 4.0), 2.0), mod(floor(gl_MultiTexCoord0.w * 2.0), 2.0));
	
	gl_FrontColor = gl_Color;
	
}