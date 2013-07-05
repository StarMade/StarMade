#version 120

uniform sampler2D tex;
varying float time;
varying float distance;


void main()
{
	vec2 teCo = vec2(gl_TexCoord[0].x, gl_TexCoord[0].y);
	
	vec4 color = texture2D(tex, teCo);
	
	teCo = teCo * 2.0 - 1.0;
	float d = abs(length(teCo.xy) * distance);
	
	
	color.a *= 1.0-min(1,pow(d/0.08, 1.0)) - time;
	
	color.rbg += max(0.0, 1.0-distance/17.17);
	gl_FragColor = color;
	
}

