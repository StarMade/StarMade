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


	gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xyz, 1.0);
	
	//float time = gl_Vertex.w;
	
	//distance = length(gl_Vertex.xyz - gl_MultiTexCoord0.xyz);
	

	lightVec = vec4(1);
	normal = vec4(1);
	vColor = vec4(1);

	gl_TexCoord[0].st = vec2(mod(floor(gl_MultiTexCoord0.w * 4.0), 2.0), mod(floor(gl_MultiTexCoord0.w * 2.0), 2.0));
	
	gl_FrontColor = gl_Color;
	
	direction = (vertPos.xyz - source.xyz);
	
	vertex.xyzw = vec4(gl_Vertex.xyz, 1.0);
	
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