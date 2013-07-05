

uniform sampler2D tex;
uniform sampler2D nmap;
uniform float cSharp;
uniform float cCover;
uniform float cMove;
uniform vec4 lightPos;

varying vec4 vertPos;
varying vec3 normal;

void main()
{
	vec3 nnorm = normalize(normal);
	vec3 light =normalize(vec3(lightPos.x, lightPos.y*8.0, lightPos.z));

	float sunlight = max(0.0, dot(light,nnorm));
	
	float sun = pow(sunlight,2048.0);
	

	float depth = vertPos.z/vertPos.w;
	depth=pow(depth, 2048.0);

	//calculate cloud lighting
	vec3 cNorm = texture2D(nmap, gl_TexCoord[0].xz*6.0+vec2(cMove,cMove*2.0)).xyz;
	cNorm += texture2D(nmap, gl_TexCoord[0].xz*8.0+vec2(cMove*2.0,cMove*4.0)).xyz;
	cNorm += texture2D(nmap, gl_TexCoord[0].xz*2.0+vec2(cMove*0.5,cMove)).xyz;
	cNorm.x =-cNorm.x;
	cNorm = normalize(cNorm.xzy);
	float cDiff=max(0.0, dot(cNorm, light))*2.0+0.3;

	//calculate cloud density
	float sky = texture2D(tex, gl_TexCoord[0].xz*6.0+vec2(cMove,cMove*2.0)).x;
	sky += texture2D(tex, gl_TexCoord[0].xz*8.0+vec2(cMove*2.0,cMove*4.0)).x;
	sky += texture2D(tex, gl_TexCoord[0].xz*2.0-vec2(cMove*0.5,cMove)).x;
	sky*=0.333;
	float c = max(0.0,sky-cCover);
	float density = 1.0-pow(cSharp,c);
	
	
	//distance fog
	vec4 skyColor = mix(vec4(0.01, 0.7, 0.9,0.0),vec4(1.0), length(nnorm.xz));
	skyColor = mix(skyColor, vec4(1.0)*cDiff*cCover*cCover, density); //clouds
	vec4 grey=vec4(0.5,0.75,0.75,0.0);
	vec4 sct = mix(skyColor,grey, depth); //light scattering

	gl_FragColor = sct+vec4((1.0-density)*sun);
}