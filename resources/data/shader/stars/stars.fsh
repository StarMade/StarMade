#version 120
#extension GL_EXT_gpu_shader4 : enable


varying vec2 TexCoord; // From the geometry shader

uniform sampler2D starTex;



	
void main()
{
	
	vec4 a = texture2D(starTex, TexCoord); 
	gl_FragColor = vec4(gl_Color.xyz,1) * a;
}