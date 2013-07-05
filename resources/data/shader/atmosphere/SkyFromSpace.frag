//
// Atmospheric scattering fragment shader
//
// Author: Sean O'Neil
//
// Copyright (c) 2004 Sean O'Neil
//

uniform vec3 v3LightPos;
const float g = -0.990;
const float g2 = 0.9801;
uniform vec4 tint;

varying vec3 v3Direction;


void main (void)
{
	if(length(gl_Color) == 0.0){
		discard;
	}
	float fCos = dot(v3LightPos, v3Direction) / length(v3Direction);
	float fMiePhase = 1.5 * ((1.0 - g2) / (2.0 + g2)) * (1.0 + fCos*fCos) / pow(1.0 + g2 - 2.0*g*fCos, 1.5);
    gl_FragColor = tint * (gl_Color + fMiePhase * gl_SecondaryColor);
	gl_FragColor.a = (gl_FragColor.b+gl_FragColor.r*0.53)*1.4;
}
