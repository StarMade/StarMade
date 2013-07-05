
varying float time;
varying float distance;
void main()
{
	gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xyz, 1);
	
	time = gl_Vertex.w;
	distance = length(gl_Vertex.xyz - gl_Normal);
	
	gl_TexCoord[0] = gl_MultiTexCoord0;
	
	gl_FrontColor = gl_Color;
	
	/*vec4 eyeCoord = gl_ModelViewMatrix * gl_Vertex;
	
	  gl_Position = gl_ProjectionMatrix * eyeCoord;
	  
	  float distFromEye = distance(eyeCoord, vec4(0.0, 0.0, 0.0, 1.0));
	  float att = sqrt(1.0 / (gl_Point.distanceConstantAttenuation +
	                         (gl_Point.distanceLinearAttenuation +
	                          gl_Point.distanceQuadraticAttenuation * distFromEye) * distFromEye));
	                          
	 float size = clamp(gl_Point.size * att, gl_Point.sizeMin, gl_Point.sizeMax);
	 
	 gl_PointSize = max(size, gl_Point.fadeThresholdSize);
	 
	 float fade = min(size, gl_Point.fadeThresholdSize) / gl_Point.fadeThresholdSize;
	 
	 fade = fade * fade * gl_Color.a;
	 
	 gl_FrontColor = vec4(gl_Color.rgb, fade);*/
}