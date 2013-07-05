#version 110

uniform sampler2D water_normalmap;

uniform sampler2D water_reflection;

uniform sampler2D water_refraction;

uniform sampler2D water_dudvmap;

uniform sampler2D water_depthmap;

uniform vec4 waterColor, waterDepth;

uniform sampler2D noiseMap;

struct lightDataType {
    vec4  ambient; 
    vec4  diffuse; 
    vec4  specular; 
    vec4  position; // Position in eye coordinates
};

uniform lightDataType light;

uniform float shininess; // Shininess exponent for specular highlights

varying vec3 tbnDirToLight; // Direction to light in tangent space
varying vec3 tbnHalfVector; // Half vector in tangent space
varying vec3 tbnDirToEye;

varying vec4 position;

varying vec4 waterTex0; //moving texcoords
varying vec4 waterTex1; //moving texcoords
//varying vec4 waterTex2; //moving texcoords
//varying vec4 waterTex3; //for projection
//varying vec4 waterTex4; //viewts


//unit 0 = water_reflection
//unit 1 = water_refraction
//unit 2 = water_normalmap
//unit 3 = water_dudvmap
//unit 4 = water_depthmap

const vec4 two = vec4(2.0, 2.0, 2.0, 1.0); //default (2.0, 2.0, 2.0, 1.0)
const vec4 mone = vec4(-1.0, -1.0, -1.0, 1.0); //default (-1.0, -1.0, -1.0, 1.0)
const vec4 ofive = vec4(0.5,0.5,0.5,1.0); //default (0.5,0.5,0.5,1.0)
const vec4 scale = vec4(0.025, 0.025, 0.025, 0.075); //distance scaler: default 0.005
const vec4 scale2 = vec4(0.0001, 0.0001, 0.0001, 1.0); //size of reflection waves default 0.02

const vec4 tscale = vec4(0.325, 0.325, 0.325, 1.0); // turbulence of water


void main(void)
{


//normalize light vector
	vec3 tmp = normalize(tbnDirToLight);
	vec4 lightTS = vec4(tmp.x, tmp.y, tmp.z, 1.0);

//normalize vector to viewpos
	tmp = normalize(position.xyz);
	vec4 viewTS = vec4(tmp.x, tmp.y, tmp.z, 1.0);
	vec4 noise  =	texture2D(noiseMap, gl_TexCoord[0].xy); //

//distortion distortion
	vec4 disTemp = waterTex1 * tscale; 						//scale tex coords
	vec4 disdis = texture2D(water_dudvmap, disTemp.xy+noise.xy/2.0); 	//load du/dv normalmap //+noise.xy/2.0
	disdis *= scale2; 										//scale colors down

//add distortion to texcoord 0
	vec4 temp2 = waterTex0 * disdis;

	// Uncompress normal from normal map texture

vec4 normal = normalize(texture2D(water_normalmap, temp2.xy*5.0*noise.xy) * 2.0 - 1.0);
//normal = normalize(normal +( noise/2.0));

    // Depending on the normal map's format, the normal's Y direction may have to be inverted to
    // achieve the correct result. This depends - for example - on how the normal map has been
    // created or how it was loaded by the de.schema.schine.engine. If the shader output seems wrong, uncomment
    // this line:
     normal.y = -normal.y;
   

//load other du/dv normalmap


	vec4 dist = texture2D(water_dudvmap, vec2(temp2)*noise.xy*10.1);
	dist = dist * two + mone;							//scale and bias
	dist = -dist * scale;								//scale down
	

	//normal = normalize(normal + dist);

//distort texcoord by adding the scaled du/dv maps
//calculate projective texcoords
//divide by w component
	vec4 temp3 = vec4( 1.0 / position.w );
	vec4 projb = position * temp3;
	projb += vec4(0.97); //move mirrored projection up a little
	projb *= vec4(0.5);
	
//add distortion to projective tex coords

	vec4 ntc = projb + dist; 							//this is the disorted texcoord
	ntc = clamp(ntc, 0.001, 0.999);						//between 0 and 1
	
//load reflection and refraction with new texcoords
	vec4 reflection = texture2D(water_reflection, vec2(ntc));
	vec4 refraction = texture2D(water_refraction, vec2(ntc));
	
//load depth texture
	vec4 depth = texture2D(water_depthmap, vec2(ntc));
	depth = vec4(pow(depth.x, 32.0));
	
//invert depth
	vec4 invdepth = vec4(1.0 - depth);
	
	
//calculate frensel
	vec4 invfresnel = vec4( dot(normal, viewTS) );

	vec4 fresnel = vec4(1.0 - invfresnel);
	
//modulate the refractions with the inverted fresnel and the with the inverted depth.
//This gives all parts of the texture, where we see the refraction texture
	refraction *= invfresnel;
	refraction *= invdepth; //only if the depthmap is detailed


//add color. only the foggy (deep) parts should have watercolor. modulate the watercollor with exp(depth, fexp)
//the result is then modulated with the inverted fresnel, to get the non reflective parts, and added to the
//refraction texture
	vec4 temp4 = (waterColor*reflection) * depth * invfresnel;
	refraction += temp4;
	

//refraction texture finished!
//the reflection texture is modulated with the fresnel to get
//only the reflective parts

	reflection = reflection * fresnel;


//combine the textures
	vec4 colormaps = reflection + refraction;


    // Ambient
    vec4 ambient = light.ambient * colormaps;
    
    // Diffuse
    // Normalize interpolated direction to light
    vec3 tbnNormDirToLight = normalize(tbnDirToLight);
    
    // Full strength if normal points directly at light
    float diffuseIntensity = max(dot(tbnNormDirToLight, normal.xyz), 0.3);
    vec4 diffuse = (light.diffuse*1.1) * colormaps * diffuseIntensity;

    // add specular
    vec4 specular = vec4(0.0, 0.0, 0.0, 0.0);
    // Only calculate specular light if light reaches the fragment.
    if (diffuseIntensity > 0.0) {
        // Colour of specular reflection
        vec4 specularColour = reflection+0.5;// texture2D(specularMap, gl_TexCoord[5].xy); 
        // Specular strength, Blinn–Phong shading model
        //FIXME: the factor at the end shouldnt be there. somehow the vector is too short
        float specularModifier = max(dot(normal.xyz, normalize(tbnHalfVector)),0.01)*1.428; 
        float shininess2 = shininess*6.0;
        specular = light.specular * specularColour * pow(specularModifier, shininess2);
    }

  // Sum of all lights
    gl_FragColor = clamp(ambient + diffuse + specular, 0.0, 1.0);//
    
    // Use the diffuse texture's alpha value.
    //gl_FragColor.a = 1.0 ;

}

