#version 120
	varying vec3 Position;
	varying vec3 Normal;
	varying vec2 TexCoord;
	uniform sampler2D BlurTex;
	uniform float Height;
	
	// Uniform variables for the Phong reflection model
	// should be added here…
	
	//layout( location = 0 ) out vec4 FragColor;
	
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
	// First blur pass
	vec4 pass3()
	{
		float size = 2.0;
		float dy = 1.0 / Height;
		
		vec4 tex = texture2D(BlurTex, TexCoord);
		vec4 sum = tex * Weight[0];
		
		for( int i = 1; i < 10; i++ )
		{
			sum += texture2D( BlurTex, TexCoord + vec2( 0.0, i ) * dy ) * Weight[i];
			sum += texture2D( BlurTex, TexCoord - vec2( 0.0, i ) * dy ) * Weight[i];
			
		}
		return sum;
	}
	
	
	
	void main()
	{
		// This will call pass1(), pass2(), pass3(), or pass4()
		gl_FragColor = pass3();
	}