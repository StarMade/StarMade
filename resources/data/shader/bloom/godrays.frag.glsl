#version 120

uniform sampler2D       Texture;
uniform sampler2D       Scene;
uniform vec4            Param1; // (SunPosX, SunPosY, 1.0/fbo.width, 0.5/FlareTexture.width)
uniform vec4            Param2; // (Radius, Stride, Bright, Scale)
uniform float			screenRatio; // GLFrame.getHeight() / GLFrame.getWidth()

const float exposure = 1.0;
const float decay = 0.5;
const float density = 3.0;
const float weight = 0.25;
const float weight2 = 0.125;
const float weight3 = 0.1333;
const float weight4 = 0.0733;


const float Sexposure = 0.67;
const float Sdecay = 0.996;
const float Sdensity = 0.8;
const float Sweight = 0.02;
/*
float Density = 3;
float Exposure = -.1;
float Weight = .25;
float Weight2 = .125;
float Decay = .5;
*/

//uniform vec2 lightPositionOnScreen;
uniform sampler2D firstPass;
const int NUM_SAMPLES = 30 ;
	
	vec4 DoLenseFlare(vec2 ScreenLightPosition,vec2 texCoord,bool fwd, float weight, float xTC, float dist)
{


    // Calculate vector from pixel to light source in screen space.
    vec2 deltaTexCoord = (texCoord - ScreenLightPosition.xy);// Divide by number of samples and scale by control factor.
    
     
    deltaTexCoord *= (dist) /  density;
    
    //deltaTexCoord *= screenRatio;
 
 		deltaTexCoord.x /= screenRatio;
 
    // Store initial sample.
    vec3 color = vec3(0.0);
 
    // Set up illumination decay factor.
    float illuminationDecay = 1.0f;
 
    for (int i = 0; i < 3 ; i++)
    {
    
    	vec3  core = texture2D(firstPass, ScreenLightPosition.xy  - vec2(float(i)/3.0,float(i)/3.0) * Param2.w).rgb;
	            
        float bright = dot(core, vec3(0.333));
    
	        // Step sample location along ray.
	        if(fwd)
	            texCoord -= deltaTexCoord;
	        else
	            texCoord += deltaTexCoord;
	 
		 	if(texCoord.y > 1.0 || texCoord.y < 0.0 || texCoord.x > 1.0 || texCoord.x < 0.0){
		 		continue;
		 	}
		 	
	 		vec2 flareTC = texCoord;
	 		//flareTC.x /= screenRatio;
	 		flareTC.x *= 0.25;
	 		flareTC.x += xTC;
	 		
	 		
	 		
	        // Retrieve sample at new location.
	        vec4 sample = texture2D(Texture, flareTC);
	 
	        // Apply sample attenuation scale/decay factors.
	        sample *= illuminationDecay * weight;
	 
	        // Accumulate combined color.
	        color += sample.xyz * bright;
	 
	        // Update exponential decay factor.
	        illuminationDecay *= decay;
    }
 
    return vec4(color,1);
}
	
	
	
	vec4 addGodRay()
	{
	    vec2 flareTC = (gl_TexCoord[0].st - vec2(0.5));
	    
	    
	   // vec2 flareTC = (gl_TexCoord[0].st - vec2(0.5)) * Param1.z;
	    
	    
	    vec3 conv = vec3(0.0);
	
	     for (float x=-Param2.x; x<=Param2.x; x+=Param2.y)
	     {
	         for (float y=-Param2.x; y<=Param2.x; y+=Param2.y)
	         {
	            vec3  core = texture2D(firstPass, Param1.xy  - vec2(x,y) * Param2.w).rgb;
	            
	            float bright = dot(core, vec3(0.333));
	            if (bright > 0.99){
		            vec3 flare = texture2D(Texture,  flareTC + vec2(x,y) * Param1.w).rgb;
		            conv += flare*core;
	            }
	        }
	    }
		vec4 frag;
		frag.rbg = conv * Param2.z;
		frag.a = 0.0;
	   

    		//frag.rgb += texture2D(Scene, ((gl_TexCoord[0].xy ) * Param2.w * 800.0) -Param1.xy).rgb;
    		
    	return frag;
	}
	
	void main() {
	
	
		vec2 deltaTextCoord = vec2( gl_TexCoord[0].st - Param1.xy );
		vec2 textCoo = gl_TexCoord[0].st;
		
		deltaTextCoord *= 1.0 / float(NUM_SAMPLES) * Sdensity;
		
		float illuminationDecay = 1.0;
				
		vec4 fragCol = vec4(0.0,0.0,0.0,0.0);
		for(int i=0; i < NUM_SAMPLES ; i++) {
		
			textCoo -= deltaTextCoord;
			vec4 sample = texture2D(firstPass, textCoo );
			
			sample *= illuminationDecay * Sweight;
			fragCol += sample;
			illuminationDecay *= Sdecay;
			
		}
		
		vec2 coord = gl_TexCoord[0].st;//0.5 - (gl_TexCoord[0].st - Param1.xy)  * 0.5;
		
		;//texture2D(firstPass, textCoo );
		
		vec4 lf = DoLenseFlare(	Param1.xy,	coord,		true, 	weight, 	0.0,  3.0);
		lf += DoLenseFlare(		Param1.xy,	coord,		false, 	weight2, 	0.75, 4.0);
		lf += DoLenseFlare(		Param1.xy,	coord,		true, 	weight3, 	0.50, 11.0);
		lf += DoLenseFlare(		Param1.xy,	coord,		false, 	weight4, 	0.25, 12.0);
		
		vec4 col = ( lf * exposure) * 5.0;
		
		fragCol += col;
		
		
		gl_FragColor = fragCol;
		//gl_FragColor *= exposure;
	}
