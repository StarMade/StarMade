// Cube map
uniform samplerCube env;
// Reflected and refracted vectors
varying vec3 reflectedVector, refractedVector;
// Reflection factor based on fresnel equation
varying float refFactor;
void main()
{
	// Read cube map
	vec4 reflectedColor = textureCube(env, reflectedVector);
	vec4 refractedColor = textureCube(env, refractedVector);
	// Mix reflected and refracted colors
	gl_FragColor = mix(refractedColor, reflectedColor, refFactor);
}