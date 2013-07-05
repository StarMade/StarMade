#version 110

uniform sampler2D water_normalmap;

uniform sampler2D water_reflection;

uniform sampler2D water_refraction;

uniform sampler2D water_dudvmap;

uniform sampler2D water_depthmap;

uniform vec4 waterColor, waterDepth;

varying vec4 waterTex0; //lightpos
varying vec4 waterTex1; //moving texcoords
varying vec4 waterTex2; //moving texcoords
varying vec4 waterTex3; //for projection
varying vec4 waterTex4; //viewts


//unit 0 = water_reflection
//unit 1 = water_refraction
//unit 2 = water_normalmap
//unit 3 = water_dudvmap
//unit 4 = water_depthmap



void main(void)
{
const vec4 scale = vec4(0.005, 0.005, 0.005, 0.005); //distance scaler: default 0.005
const vec4 scale2 = vec4(0.02, 0.02, 0.02, 0.02); //size of reflection waves default 0.02

const vec4 tscale = vec4(0.25, 0.25, 0.25, 0.25); // intensenty of dudv map default 0.25

const vec4 two = vec4(2.0, 2.0, 2.0, 1.0); //default (2.0, 2.0, 2.0, 1.0)
const vec4 mone = vec4(-1.0, -1.0, -1.0, 1.0); //default (-1.0, -1.0, -1.0, 1.0)
const vec4 ofive = vec4(0.5,0.5,0.5,1.0); //default (0.5,0.5,0.5,1.0)

const float exponent = 32.0; //default 64
const float fexp = 4.0; //default 64

//normalize light vector
	vec4 lightTS = normalize(waterTex0);

//normalize vector to viewpos
	vec4 viewTS = normalize(waterTex4);

//distortion distortion
	vec4 disTemp = waterTex2 * tscale; 						//scale tex coords
	vec4 disdis = texture2D(water_dudvmap, vec2(disTemp)); 	//load du/dv normalmap 
	disdis *= scale2; 										//scale colors down

//add distortion to texcoord 1
	vec4 temp2 = waterTex1 * disdis;

//the distorted tex coord for the normalmaps are now stored in temp2.
//now ready to load both normal maps
//load normalmap with new texcoords
	vec4 normal = texture2D(water_normalmap, vec2(temp2));

	normal = normal * two + mone;						//scale and bias
	normal = normalize(normal);							//normalize


//load other du/dv normalmap
	vec4 dist = texture2D(water_dudvmap, vec2(temp2));
	dist = dist * two + mone;							//scale and bias
	dist = dist * scale;								//scale down

//with the normalmap we calculate the light relection vector
//2*dot(normal, lightTS)*normal - lightts.
//we need it later for specular highlights
	vec4 lref = normalize(reflect(lightTS, normal));


//distort texcoord by adding the scaled du/dv maps
//calculate projective texcoords
//divide by w component
	vec4 temp3 = vec4( 1.0 / waterTex3.w );
	vec4 projb = waterTex3 * temp3;
	projb += vec4(1.0);
	projb *= vec4(0.5);
	
//add distortion to projective tex coords
	vec4 ntc = projb + dist; 							//this is the disorted texcoord
	ntc = clamp(ntc, 0.001, 0.999);						//between 0 and 1
	
///////////////
//debug: undostorted textures:
//	//vec4 udreflection = texture2D(water_reflection, vec2(projb));
///////////////	
	
//load reflection and refraction with new texcoords
	vec4 reflection = texture2D(water_reflection, vec2(ntc));
	vec4 refraction = texture2D(water_refraction, vec2(ntc));
	
//load depth texture
	vec4 depth = texture2D(water_depthmap, vec2(ntc));
	depth = vec4(pow(depth.x, 4.0));
	
//invert depth
	vec4 invdepth = vec4(1.0 - depth);
	
	
//calculate specular hightlight
	float specFac = max(0.0, dot(lref, viewTS));
	vec4 specular = vec4( pow(specFac, exponent) );
	
//calculate frensel
	vec4 invfresnel = vec4( dot(normal, viewTS) );
	vec4 fresnel = vec4(1.0 - invfresnel);
	
	
//modulate the refractions with the inverted fresnel and the with the inverted depth.
//This gives all parts of the texture, where we see the refraction texture
	refraction *= invfresnel;
	refraction *= invdepth;
	

//add color. only the foggy (deep) parts should have watercolor. modulate the watercollor with exp(depth, fexp)
//the result is then modulated with the inverted fresnel, to get the non reflective parts, and added to the
//refraction texture
	vec4 temp4 = waterColor * depth;
	temp4 *= invfresnel;
	refraction += temp4;

//refraction texture finished!
//the reflection texture is modulated with the fresnel to get
//only the reflective parts

	reflection = reflection * fresnel;


//combine the textures
	vec4 colormaps = reflection + refraction;

//add specular
	vec4 complete = colormaps + specular;


	gl_FragColor = complete;

//this works:
//to test a texture use: texture2D(water_normalmap, gl_TexCoord[0].xy); or texture2D(water_depthmap, gl_TexCoord[0].xy);
//!!but gl_TexCoord[0] =  gl_MultiTexCoord0; has to be set in the vertex shader
}