#version 120

uniform sampler3D tex;
varying float time;
varying float distance;


void main()
{
	float alphaTime = min(0.95,(time/1000.0));
	vec3 teCo = vec3(gl_TexCoord[0].x, gl_TexCoord[0].y, alphaTime);
	
	vec4 color = texture3D(tex, teCo);
	
	teCo = teCo * 2.0 - 1.0;
	float d = abs(length(teCo.xy) * distance/100.0);
	//color.a = 1.0 - alphaTime;
	float black = (color.r+color.b+color.g) / 3.0;
//	if(black <= 0.1){
//		discard;
//	}
	
	color.a =  black * 1.0-min(1.0,pow((d/0.68), 16.0));
	color.rg += max(0.0, 1.0-pow((distance/0.87), 16.0));
	//if(color.a <= 0.1){
	//	discard;
	//}
	gl_FragColor = color;
	
}

