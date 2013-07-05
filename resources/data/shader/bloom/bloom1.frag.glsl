#version 120
	varying vec3 Position;
	varying vec3 Normal;
	varying vec2 TexCoord;
	uniform sampler2D RenderTex;
	uniform sampler2D BlurTex;
	//uniform float Width;
	//uniform float Height;
	uniform float LumThresh; // Luminance threshold
	
	// Uniform variables for the Phong reflection model
	// should be added here…
	
	
	
	// Weights and offsets for the Gaussian blur
	
	uniform float Weight[10];
	
	float luma( vec3 color ) {
		return 0.2126 * color.r + 0.7152 * color.g +
		0.0722 * color.b;
	}
	
	// See Chapter 2 for the ADS shading model code
	vec3 phongModel( vec3 pos, vec3 norm ) { 
	
		return pos;
	}
	// The render pass
	vec4 pass1()
	{
		return vec4(phongModel( Position, Normal ),1.0);
	}
	
	

	void main()
	{
		// This will call pass1(), pass2(), pass3(), or pass4()
		gl_FragColor = pass1();
	}