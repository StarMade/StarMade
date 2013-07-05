#version 120

varying vec3 outOrigPos;
varying vec3 outNormal;

uniform sampler3D noiseTex;
//uniform mat4 mvp;
uniform vec4 thrustColor0;
uniform vec4 thrustColor1;
uniform float ticks;

void main()
{
	
	float cosang;
	float edgeAlpha;
	vec4 tc;
	//vec3 Up = normalize(vec3(gl_ModelViewMatrix[0][0],gl_ModelViewMatrix[1][0],gl_ModelViewMatrix[2][0]));
	vec3 Up = normalize(( gl_ModelViewMatrix * vec4(0,0,0,1)).xyz);
	    
	float noiseval;
	
	float heatfactor = thrustColor0.a;

	float heat = 1-clamp((outOrigPos.z/2.0) ,0.0,1.0);
	
	vec2 len = vec2(outOrigPos.y , outOrigPos.x);
	
	heat *= clamp(length(len)/outOrigPos.z*2.0,0.0,1.0);
	
	
	float x= gl_TexCoord[0].x-ticks ;
	float y= gl_TexCoord[0].y*0.5+(ticks)*(5+20*heatfactor);
	float z= ticks;
	vec4 noise =  texture3D(noiseTex, vec3( x,y,z ) );
	//vec4 noise =  texture3D(noiseTex, vec3(gl_TexCoord[0].x,  gl_TexCoord[0].y, 0.1 * ticks) );
	noiseval = (noise.x) ;
	
	noiseval = pow(noiseval,1.5+1.5*heatfactor);
	 
	 vec3 a = ( 10*noiseval*pow(heat*heatfactor,3.5)*thrustColor0.rgb);
	 vec3 b = 2.5*noiseval*thrustColor1.rgb;
	tc.rgb = ( a + b ); 
	
	edgeAlpha = abs( dot(Up, outNormal));
	edgeAlpha = max(0.0, edgeAlpha-0.1)*2.0;
	
	//this is very specific to the size of the plum
	//this renders the short plum even if viewer is behind it
	if(length(len) < 0.45 && outOrigPos.z < 0.30){
		edgeAlpha += pow(length(len)/0.30,2.3);
	}
	
	tc.a = (edgeAlpha) * pow(heat, 0.85 + 25*(1.01-heatfactor));
	if(tc.a <= 0.05){
		discard;
	}
	
	gl_FragColor = tc;
	//gl_FragColor = vec4(outNormal,1);//tc;
}





   