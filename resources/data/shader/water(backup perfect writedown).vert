
#version 110

uniform vec4 viewpos;
uniform vec4 lightpos;
uniform float time;
uniform float time2;
uniform float time3;
uniform float time4;
uniform float time5;
uniform float time6;

varying vec4 waterTex0;
varying vec4 waterTex1;
varying vec4 waterTex2;
varying vec4 waterTex3;
varying vec4 waterTex4;

varying vec4 waterTex5;




//unit 0 = water_reflection
//unit 1 = water_refraction
//unit 2 = water_normalmap
//unit 3 = water_dudvmap
//unit 4 = water_depthmap

void main(void)
{
	const float wavespeed =2.0;
	const float waveheight = 1.0;
	const float wavefreq = 4.0 ;
	vec4 mpos, temp;
	vec4 tangent = vec4(1.0, 0.0, 0.0, 0.0);
	vec4 norm = vec4(0.0, 1.0, 0.0, 0.0);
	vec4 binormal = vec4(0.0, 0.0, 1.0, 0.0);
	
	mat4 mvp = gl_ModelViewProjectionMatrix;
	mat4 mtx = gl_TextureMatrix[0];
	vec4 vert = gl_Vertex;

	vert.y = ( sin( wavefreq*((wavespeed*time)+vert.x) ) + sin( wavefreq*((wavespeed*time)+vert.z) ) )*waveheight;
	
	//norm = normalize(vec4(norm.x+vert.y/20.0, norm.y*10.0, norm.z-vert.y/10.0, 0.0));
	//binormal = normalize(vec4(norm.x+vert.y/20.0, norm.z-vert.y/10.0, norm.y*10.0, 0.0));
	temp = viewpos - vert;
	waterTex4.x = dot(temp, tangent);
	waterTex4.y = dot(temp, binormal);
	waterTex4.z = dot(temp, norm);
	waterTex4.w = 1.0;
	
	temp = (lightpos) - vert;
	waterTex0.x = dot(temp, tangent);
	waterTex0.y = dot(temp, binormal);
	waterTex0.z = dot(temp, norm);
	waterTex0.w = 1.0;
	
	mpos = mvp * vert;
	
	vec4 t1 = vec4(0.0, -time, 0.0,0.0);
	vec4 t2 = vec4(0.0, -time2, 0.0,0.0);
	waterTex1 =  (gl_MultiTexCoord0 + t1);
	waterTex2 =  (gl_MultiTexCoord0 + t2);
	gl_TexCoord[0] =  gl_MultiTexCoord0; //for texting a texture
	waterTex3 = mpos;
	
	gl_Position = mpos;
}