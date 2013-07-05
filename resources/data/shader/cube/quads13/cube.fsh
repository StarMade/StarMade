#version 120

uniform sampler2D mainTex0;
uniform sampler2D mainTex1;
uniform sampler2D mainTex2;
uniform sampler2D overlayTex;
uniform float density;
uniform float selectTime;


varying vec2 overlayTexCoords;
varying vec2 poweredOverlayTexCoords;
varying vec2 mainTexCoords;
varying vec3 occlusion;
varying float textureIndex;
varying vec3 vFragColor;
const float LOG2 = 1.442695;
vec3 gamma(vec3 color){
    return pow(color, vec3(1.0/2.0));
}

void main()
{
	vec4 tex;
	
	if(textureIndex == 0.0){
		tex = texture2D(mainTex0, mainTexCoords.st);
	}else if(textureIndex <= 1.5){
		tex = texture2D(mainTex1, mainTexCoords.st);
	}else{
		tex = texture2D(mainTex2, mainTexCoords.st);
	}
	
	//if(poweredOverlayTexCoords.x >= 0){
	//	oTex += texture2D(overlayTex, poweredOverlayTexCoords.st);
	//}
	
	tex.a += (vFragColor.x + vFragColor.y + vFragColor.z)/80.0;
	if(tex.a <= 0.01 || tex.a < 0.11){
		discard;
	}
	
	float z = gl_FragCoord.z / gl_FragCoord.w;
	float fogFactor = exp2( -density * 
					   density * 
					   z * 
					   z * 
					   LOG2 );
	fogFactor = clamp(fogFactor, 0.0, 1.0);
	
	
	vec4 light = vec4((vFragColor * occlusion), 1.0);
	vec4 oTex = texture2D(overlayTex, overlayTexCoords.st);
	vec4 mixTex = vec4(mix(tex , oTex, oTex.a)  * light);
	mixTex.a = tex.a;
	
	mixTex.rgb += selectTime;
	
	gl_FragColor = mix(vec4(0.0,0.0,0.0,1.0), mixTex, fogFactor );
	//gl_FragColor = mixTex;
	
}