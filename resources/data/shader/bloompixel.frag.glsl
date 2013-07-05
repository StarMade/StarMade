#version 400
	in vec3 Position;
	in vec3 Normal;
	in vec2 TexCoord;
	uniform sampler2D RenderTex;
	uniform sampler2D BlurTex;
	uniform float Width;
	uniform float Height;
	uniform float LumThresh; // Luminance threshold
	
	subroutine vec4 RenderPassType();
	
	subroutine uniform RenderPassType RenderPass;
	
	 
	
	
	// Uniform variables for the Phong reflection model
	// should be added here…
	
	layout( location = 0 ) out vec4 FragColor;
	
	// Weights and offsets for the Gaussian blur
	uniform float PixOffset[10] =
	float[](0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0);
	
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
	subroutine (RenderPassType) vec4 pass1()
	{
		return vec4(phongModel( Position, Normal ),1.0);
	}
	
	// Pass to extract the bright parts
	subroutine( RenderPassType )
	vec4 pass2()
	{
		vec4 val = texture(RenderTex, TexCoord);
		
		float lum = luma( val.xyz );
		
		float clam = clamp( lum - LumThresh, 0.0, 1.0);
		
		float thresh = (1.0 / (1.0 - LumThresh));
		return val * clam * thresh;
	}
	// First blur pass
	subroutine( RenderPassType ) vec4 pass3()
	{
		float dy = 1.0 / Height;
		vec4 sum = texture(BlurTex, TexCoord) * Weight[0];
		for( int i = 1; i < 10; i++ )
		{
			sum += texture( BlurTex, TexCoord +
			vec2(0.0,PixOffset[i]) * dy ) * Weight[i];
			sum += texture( BlurTex, TexCoord - vec2( 0.0, PixOffset[i]) *dy ) * Weight[i];
			
		}
		return sum;
	}
	
	
	// Second blur and add to original
	vec4 pass4()
	{
		float dx = 1.0 / Width;
		vec4 val = texture(RenderTex, TexCoord);
		vec4 sum = texture(BlurTex, TexCoord) * Weight[0];
		for( int i = 1; i < 10; i++ )
		{
			sum += texture( BlurTex, TexCoord + 
				vec2(PixOffset[i],0.0) * dx ) * Weight[i];
				
			sum += texture( BlurTex, TexCoord - 
				vec2(PixOffset[i],0.0) * dx ) * Weight[i];
		}
		return val + sum;
	}
	
	void main()
	{
		// This will call pass1(), pass2(), pass3(), or pass4()
		FragColor = RenderPass();
	}