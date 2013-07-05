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
uniform float m_CollisionAlphas[16];
varying float dists[16];

void main(void) {
	vec4 color = vec4(0.90, 0.90, 1.0, m_MinAlpha);
	for (int i=0; i < m_CollisionNum && i < 16;i++){

		  float x = dists[i]/(m_MaxDistance);//+();
		  float x2 = dists[i]/(m_MaxDistance)/1.7;//+();
		  
		  float y = (m_CollisionAlphas[i]);
		  float z = min(1,max(0,(1.5-m_CollisionAlphas[i])));
		 /*
		  if (x < 1){
		  	float alpha = (2-x)*cos(pi/2*(x-(1.0-m_CollisionAlphas[i]))+2.8*pi/2.0); 
		  	if (alpha > 0){
		  		color.a += alpha;
		  	}
		  }
		  */
		  
		  color.a += pow(e,(-1.0*((x-y)*(x-y))*10.0))*(1-y)*24.0;
		  color.a += pow(e,(-1.0*((x2-z)*(x2-z))*10.0))*(1-z)*2.0;
		  
		  color.a = min(1.0, color.a);
	}
	
	//color *= m_Color;


	vec4 noise = texture2D(m_Noise, (gl_TexCoord[0].st + m_Time)/30.0); 	//load du/dv normalmap //+noise.xy/2.0
	vec4 distort = texture2D(m_Distortion, noise.xy*color.a * gl_TexCoord[0].st/10.0); 	//load du/dv normalmap //+noise.xy/2.0
	


	color *= texture2D(m_ShieldTex, noise.xz*0.3+(distort.xy*0.1*color.a)+gl_TexCoord[0].st);
	color.gb *= min(1.0, m_ShieldPercentage*2.0);
	if(color.a < 0.02){
		discard;
	}
	color.rgb = min(vec3(1.0) , color.rgb +max(0,min(0.3,color.a*0.1)));
	gl_FragColor = color;   
}