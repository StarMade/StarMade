#version 120



varying vec3 outOrigPos;
varying vec3 outNormal;
void main()
{
//	outNormal = normalize( gl_NormalMatrix * gl_MultiTexCoord0.xyz);
	
	outOrigPos = gl_Vertex.xyz;
	gl_TexCoord[0] = vec4(gl_Normal.xy,0,1);

	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
	outNormal = normalize( gl_NormalMatrix * normalize(vec3(gl_Vertex.xy, 0.0)));
}

