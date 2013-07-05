#version 120	

varying vec3 Position;
	varying vec3 Normal;
	varying vec2 TexCoord;
	uniform sampler2D RenderTex;
	uniform float LumThresh; // Luminance threshold
	uniform sampler2D SilhouetteTex;
	
	
	
	// Uniform variables for the Phong reflection model
	// should be added here…
	
	//layout( location = 0 ) out vec4 FragColor;
	
	// Weights and offsets for the Gaussian blur
	
	uniform float Weight[10];
	
	float luma( vec3 color ) {
		return 0.2126 * color.r + 0.7152 * color.g +
		0.0722 * color.b;
	}
	
	
	
	// Pass to extract the bright parts
	vec4 pass2()
	{
		
		vec4 val = texture2D(RenderTex, gl_TexCoord[0].st);
		vec4 valSil = texture2D(SilhouetteTex, gl_TexCoord[0].st);
		float lum = 0;
		
		if(valSil.x > 0.01){
			return val*32.0;
		}else{
			lum = luma( val.xyz );
		}
		
		float clam = clamp( lum - LumThresh, 0.0, 1.0);
		
		float thresh = (1.0 / (1.0 - LumThresh));
		return val * clam * thresh;
	}

	
	
	
	void main()
	{
		// This will call pass1(), pass2(), pass3(), or pass4()
		gl_FragColor = pass2();
	}