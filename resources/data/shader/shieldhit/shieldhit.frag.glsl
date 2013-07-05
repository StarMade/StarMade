#version 120


uniform float m_MinAlpha;
uniform float m_MaxDistance;
const float pi = 3.141592;
const float e = 2.71828183;


//uniform vec4 m_Color;
uniform sampler2D m_ShieldTex;
uniform sampler2D m_Distortion;
uniform sampler2D m_Noise;
uniform float m_Time;
uniform float m_ShieldPercentage;
uniform int m_CollisionNum;
uniform float m_CollisionAlphas[8];

//some crappy ATI cards do not support varying arrays. sometimes even without giving compiling errors
varying float dist0;
varying float dist1;
varying float dist2;
varying float dist3;
varying float dist4;
varying float dist5;
varying float dist6;
varying float dist7;

float hit(float dist, float alphaIn, int i){

	float alphaOut = alphaIn;
	if(i < m_CollisionNum){
		  float x = dist / (m_MaxDistance);
		  float x2 = dist / (m_MaxDistance) / 1.7;
		  
		  float collAlpha = m_CollisionAlphas[i];
		  
		  float y = collAlpha;
		  float z = clamp(1.3 - collAlpha, 0.0, 1.0);
		  
		  
		 /*
		  if (x < 1){
		  	float alpha2 = (2-x)*cos(pi/2*(x-(1.0-m_CollisionAlphas[i]))+2.8*pi/2.0); 
		  	if (alpha2 > 0){
		  		color.a += alpha2;
		  	}
		  }
		  */
		  
		  alphaOut += pow(e,(-1.0*((x-y)*(x-y))*2.5))*(1.0-y)*1.0;
		  alphaOut += pow(e,(-1.0*((x2-z)*(x2-z))*20.0))*(1.0-z)*3.0;
		  
		  alphaOut = min(1.0, alphaOut);
	}
	return alphaOut;
	  
}

void main(void) {
	vec4 color = vec4(0.90, 0.90, 1.0, m_MinAlpha);
	
	
	
	color.a = hit(dist0, color.a, 0);
	color.a = hit(dist1, color.a, 1);
	color.a = hit(dist2, color.a, 2);
	color.a = hit(dist3, color.a, 3);
	color.a = hit(dist4, color.a, 4);
	color.a = hit(dist5, color.a, 5);
	color.a = hit(dist6, color.a, 6);
	color.a = hit(dist7, color.a, 7);


	vec4 noise = texture2D(m_Noise, (gl_TexCoord[0].st + m_Time)/30.0); 	//load du/dv normalmap //+noise.xy/2.0
	vec4 distort = texture2D(m_Distortion, noise.xy*color.a * gl_TexCoord[0].st/10.0); 	//load du/dv normalmap //+noise.xy/2.0
	


	color *= texture2D(m_ShieldTex, noise.xz*0.3+(distort.xy*0.1*color.a)+gl_TexCoord[0].st);
	color.gb *= min(1.0, m_ShieldPercentage*2.0);
	if(color.a < 0.02){
		discard;
	}
	float alf = clamp(color.a*0.1, 0.0, 0.3);
	color.r = min(1.0, color.r + alf);
	color.g = min(1.0, color.g + alf);
	color.b = min(1.0, color.b + alf);
	
	
	gl_FragColor = color;   
}