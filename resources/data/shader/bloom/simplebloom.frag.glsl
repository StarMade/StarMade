	
	
	uniform sampler2D tex;

	void main(){	
	
		vec4 blurSample = vec4(0.0);	
		
		vec4 tmpPix;	
		vec4 offPix;	
		
		vec2 uv = gl_TexCoord[0].xy;	
	
		for(int i=-4;i<5;i++)	{		
			tmpPix = texture2D(tex,uv + vec2( i*0.005,0 ));		
			offPix = -0.3+tmpPix;		offPix = offPix * 15;		
			if( (offPix.r+offPix.g+offPix.b)>0 )		{			               
				 blurSample = blurSample + offPix;			
			}	
		}	
		for(int i=-4;i<5;i++)	{		
			tmpPix = texture2D(tex,uv + vec2( 0,i*0.005 ));              	
			offPix = -0.3+tmpPix;		offPix = offPix * 15;		
			if( (offPix.r+offPix.g+offPix.b)>0 )		{			
				blurSample += offPix;		
			}	
		}	
		blurSample = blurSample / 64;	
		gl_FragColor =  blurSample*1.2;
	}