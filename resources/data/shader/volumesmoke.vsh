#version 120

uniform vec4 lightPos;
uniform vec4 source;

varying vec4 normal;
varying vec4 lightVec;
varying vec4 vColor;
varying vec3 direction;
varying vec4 vertPos;
varying vec4 vertex;

void main()
{
	gl_Position = ftransform();
	gl_TexCoord[0] = gl_MultiTexCoord0;
	normal =  gl_TextureMatrix[0]*vec4(gl_Normal, 0.0);
	lightVec = lightPos-gl_TextureMatrix[0]*gl_Vertex;
	vColor=gl_Color;
	vertPos = ftransform();
	
	direction = (vertex.xyz - source.xyz);
	
	vertex = gl_TextureMatrix[0]*gl_Vertex;
	
	vec4 eyeCoord = gl_ModelViewMatrix * gl_Vertex;
	  gl_Position = gl_ProjectionMatrix * eyeCoord;
	  float distFromEye = distance(eyeCoord, vec4(0.0, 0.0, 0.0, 1.0));
	  float att = sqrt(1.0 / (gl_Point.distanceConstantAttenuation +
	                         (gl_Point.distanceLinearAttenuation +
	                          gl_Point.distanceQuadraticAttenuation * distFromEye) * distFromEye));
	 float size = clamp(gl_Point.size * att, gl_Point.sizeMin, gl_Point.sizeMax);
	 gl_PointSize = max(size, gl_Point.fadeThresholdSize);
	 float fade = min(size, gl_Point.fadeThresholdSize) / gl_Point.fadeThresholdSize;
	 fade = fade * fade * gl_Color.a;
	 gl_FrontColor = vec4(gl_Color.rgb, fade);
}