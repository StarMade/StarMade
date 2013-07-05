// Position of the view eye in world space
uniform vec3 eyePos;
// Fresnel parameters
uniform float fresnelBias, fresnelScale, fresnelPower;
// Ratio of indices of refraction
uniform float etaRatio;
// Reflected and refracted vectors
varying vec3 reflectedVector, refractedVector;
// Reflection factor based on fresnel equation
varying float refFactor;
void main()
{
	// Create incident and normal vectors
	vec3 I = normalize(gl_Vertex.xyz - eyePos.xyz);
	vec3 N = normalize(gl_Normal);
	// Calculate reflected and refracted vectors
	reflectedVector = reflect(I, N);
	refractedVector = refract(I, N, etaRatio);
	// Appoximation of the fresnel equation
	refFactor = fresnelBias+fresnelScale*pow(1+dot(I,N), fresnelPower);
	// Transform vertex
	gl_TexCoord[0] = gl_MultiTexCoord0;
	gl_Position = ftransform();
}