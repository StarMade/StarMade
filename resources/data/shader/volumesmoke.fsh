#version 120

uniform sampler2D tex;
uniform float cCover;

varying vec3 direction;
varying vec4 normal;
varying vec4 lightVec;
varying vec4 vColor;
varying vec4 vertPos;
varying vec4 vertex;

void main()
{
	
	float depth = 1.0 - 1.0/pow(length( direction), 0.01) ;
	vec3 nnormal = normalize(normal.xyz);
	
	
	vec3 nlight = normalize(lightVec.xyz);
	vec3 nlight2 = normalize(-vertex.xyz);
	float nlight2Atten=1.0-clamp((length(-vertex.xyz)*length(-vertex.xyz))/(1.0),0.0,1.0);
	nlight2Atten *= 1.5;
	float vign =  length(gl_TexCoord[0].xy-vec2(0.5));
	
	float time = pow(vColor.a, 4);
	
	vec4 fireCol = mix(vec4(1.0,1.0,0.1,0.0), vec4(1.0,0.15,0.1,0.0), pow(vign,0.2));
	vec4 fire = mix(vec4(1.0), fireCol*10.0, time);


	float diffuse = max(0.0,dot(nlight,nnormal))*cCover*cCover;
	float diffuseFire = max(0.0,dot(nlight2,nnormal));
	vec4 diff=vec4(diffuse)+vec4(diffuseFire*nlight2Atten)*fireCol;
	vec4 ambient = mix(vec4(0.15, 0.2,0.15,0.0), vec4(0.4, 0.4,0.4,0.0), nnormal.y*nnormal.y);

	vec4 color = texture2D(tex, gl_TexCoord[0].xy);
	
	vec4 grey=vec4(0.5,0.75,0.75,0.0);
	vec4 sct = mix((ambient+diff)*fire,grey, depth); 

	gl_FragColor = vec4(sct.xyz,color.r*vColor.a);
}