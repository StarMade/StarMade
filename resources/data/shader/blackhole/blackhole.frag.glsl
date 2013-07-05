#version 120

uniform sampler2D tex;
uniform sampler2D nmap;
uniform float cSharp;
uniform float cCover;
uniform float cMove;
uniform vec4 lightPos;

varying vec4 vertPos;
varying vec3 normal;
varying float vertLen;

void main()
{
	vec3 nnorm = normalize(normal);
	vec3 light =normalize(vec3(lightPos.x, lightPos.y, lightPos.z));

	//float sunlight = max(0.0, dot(light,nnorm));
	
	//float sun = pow(sunlight,2048.0);
	

	float depth = vertPos.z/vertPos.w;
	depth=pow(depth, 16.0);

	float mx = 1.0+(gl_TexCoord[0].y*0.051);

	//calculate cloud lighting
	vec3 cNorm = texture2D(nmap, gl_TexCoord[0].xy*6.0+vec2(cMove*mx,cMove*2.0)).xyz;
	cNorm += texture2D(nmap, gl_TexCoord[0].xy*8.0+vec2(cMove*2.0*mx,cMove*4.0)).xyz;
	cNorm += texture2D(nmap, gl_TexCoord[0].xy*2.0+vec2(cMove*0.5*mx,cMove)).xyz;
	cNorm.x =-cNorm.x;
	cNorm = normalize(cNorm.xzy);
	float cDiff=max(0.0, dot(cNorm, light))*2.0+0.1;

	//calculate cloud density
	float sky = texture2D(tex, gl_TexCoord[0].xy*6.0+vec2(cMove*mx,cMove*2.0)).x;
	sky += texture2D(tex, gl_TexCoord[0].xy*8.0+vec2(cMove*2.0*mx,cMove*4.0)).x;
	sky += texture2D(tex, gl_TexCoord[0].xy*2.0-vec2(cMove*0.5*mx,cMove)).x;
	sky*=0.343;
	float c = max(0.0,sky-cCover);
	float density = 1.0-pow(cSharp,c);
	
	
	//distance fog
	vec4 skyColor = mix(vec4(0.1, 0.1, 0.1,0.0),vec4(1.0), length(nnorm.xz));
	skyColor = mix(skyColor, vec4(1.0)*cDiff*cCover*cCover, density); //clouds
	vec4 grey=vec4(0.5,0.75,0.75, 0.0);
	vec4 sct = mix(skyColor,grey, depth); //light scattering
	vec4 color = sct+vec4((1.0-density));
	color *= min(1.0, 0.2+vertLen*0.8);
	color *= min(1.0, 2.2-vertLen*0.4);
	color.xyz *= (texture2D(nmap, gl_TexCoord[0].xy*(2.0)+vec2(cMove*0.5*mx,cMove)).yzx);
	gl_FragColor = color;
}