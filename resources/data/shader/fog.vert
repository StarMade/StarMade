varying float atten; 
varying float fogFactor; 
varying vec3 lightVec, viewVec; 
attribute vec3 tangent; 

void main(void)
{
	gl_Position = ftransform();
	gl_TexCoord[0] = gl_MultiTexCoord0.xy;
	
	vec3 n = normalize(gl_NormalMatrix * gl_Normal);
	vec3 t = normalize(gl_NormalMatrix * tangent);
	vec3 b = cross(n, t);
	
	vec3 vVertex = vec3(gl_ModelViewMatrix * gl_Vertex);
	vec3 vLVec = gl_LightSource[0].position.xyz - vVertex;
	
	atten = 1.0 / (1.0 + 0.00001 * dot(vLVec, vLVec));
	
	vec3 vVec = -vVertex;
	viewVec.x = dot(vVec, t);
	viewVec.y = dot(vVec, b);
	viewVec.z = dot(vVec, n);
	
	lightVec.x = dot(vLVec, t);
	lightVec.y = dot(vLVec, b);
	lightVec.z = dot(vLVec, n);
	
	const float LOG2 = 1.442695;
	gl_FogFragCoord = length(vVertex);
	fogFactor = exp2( -gl_Fog.density * 
					   gl_Fog.density * 
					   gl_FogFragCoord * 
					   gl_FogFragCoord * 
					   LOG2 );
	fogFactor = clamp(fogFactor, 0.0, 1.0);
}
