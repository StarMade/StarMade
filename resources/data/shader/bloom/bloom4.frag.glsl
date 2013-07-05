#version 120

// Weights and offsets for the Gaussian blur

	varying vec3 Position;
	varying vec3 Normal;
	varying vec2 TexCoord;
	uniform sampler2D RenderTex;
	uniform sampler2D BlurTex;
	uniform float Width;
	
	// Uniform variables for the Phong reflection model
	// should be added here…
	
	//layout( location = 0 ) out vec4 FragColor;
	
	
	
	uniform float Weight[10];
	
	float luma( vec3 color ) {
		return 0.2126 * color.r + 0.7152 * color.g +
		0.0722 * color.b;
	}
	
 
	
	// Second blur and add to original
	vec4 pass4()
	{
		float size = 2.0;
		float dx = 1.0 / Width;
		vec4 val = texture2D(RenderTex, TexCoord);
		vec4 sum = texture2D(BlurTex, TexCoord) * Weight[0];
		
		
		for( int i = 1; i < 10; i++ )
		{
			sum += texture2D( BlurTex, TexCoord + vec2(i,0.0) * dx ) * Weight[i];
				
			sum += texture2D( BlurTex, TexCoord - vec2(i,0.0) * dx ) * Weight[i];
		}
		
		return val + sum;
	}
	
	void main()
	{
		// This will call pass1(), pass2(), pass3(), or pass4()
		gl_FragColor = pass4();
	}