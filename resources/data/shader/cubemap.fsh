#version 120
	varying vec3 ReflectDir; // The direction of the reflected ray
	uniform samplerCube CubeMapTex; // The cube map
	uniform vec4 MaterialColor; // Color of the object's "Tint"
	
	
	float luma( vec4 color ) {
		return 0.2126 * color.r + 0.7152 * color.g +
		0.0722 * color.b;
	}
	void main() {
		// Access the cube map texture
		vec4 cubeMapColor = textureCube(CubeMapTex, ReflectDir);
			//luma darkens down the map
			gl_FragColor = vec4( (luma(cubeMapColor) * cubeMapColor).xyz, 1.0);
	}