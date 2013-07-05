
#version 110


uniform float time;
uniform float time2;



varying vec4 waterTex0;
varying vec4 waterTex1;

struct lightDataType {
    vec4  ambient; 
    vec4  diffuse; 
    vec4  specular; 
    vec4  position; // Position in eye coordinates
};

uniform lightDataType light;

attribute vec3 tangent;
attribute vec3 binormal;

varying vec3 tbnDirToLight; // Direction to light in tangent space
varying vec3 tbnHalfVector; // Half vector in tangent space
varying vec3 tbnDirToEye;

varying vec4 position;


const float wavespeed =1.7;
const float waveheight = 5.0;
const float wavefreq = 4.0 ;


//unit 0 = water_reflection
//unit 1 = water_refraction
//unit 2 = water_normalmap
//unit 3 = water_dudvmap
//unit 4 = water_depthmap

vec4 getWaved(vec4 v){
	v.y = ( sin( wavefreq*((wavespeed*time)+v.x) ) )*waveheight; //+ sin( wavefreq*((wavespeed*time)+v.z) ) 
	return v;
}

void main(void)
{
	

	//float val = 20.0;
	
	//for waves:
	//vec4 neighbour1 = vec4(vert.x + val, 	vert.y, vert.z, 	  vert.w);
	//vec4 neighbour2 = vec4(vert.x, 			vert.y, vert.z + val, vert.w);
	//vert = getWaved(vert);
	//neighbour1 = getWaved(neighbour1);
	//neighbour2 = getWaved(neighbour2);
	//tangent  = neighbour1 - vert;
	//binormal = neighbour2 - vert;
	//norm.xyz   = cross(tangent.xyz, binormal.xyz);
	//////////
	
	
	
	
	
	// Vertex location
    position = ftransform();
  
    gl_Position = position;

 

	vec4 t1 = vec4(0.0, time, 0.0,0.0);
	vec4 t2 = vec4(0.0, -time2, 0.0,0.0);
	waterTex0 =  (gl_MultiTexCoord0 ) + t1;
	waterTex1 =  (gl_MultiTexCoord0 ) + t2;


    // Texture coordinates
    // Multiplication with texture matrix can be omitted if default (identity matrix) is used. To
    // use the texture matrix comment the first and uncomment the second line.
    gl_TexCoord[0] = gl_MultiTexCoord0;
    // gl_TexCoord[0] = gl_TextureMatrix[0] * gl_MultiTexCoord0;

    // Tangent space vectors (TBN)
    // The binormal can either be passed as an attribute or calculated as cross(normal, tangent).
    vec3 mvTangent = gl_NormalMatrix * tangent;
    vec3 mvBinormal = gl_NormalMatrix * binormal;
    vec3 mvNormal = gl_NormalMatrix * gl_Normal;
    //vec3 mvBinormal = cross(mvNormal, mvTangent);

    // Vertex coords from eye position
    vec3 mvVertex = vec3(gl_ModelViewMatrix * gl_Vertex);

    // Eye direction from vertex, for half vector
    // If eye position is at (0, 0, 0), -mvVertex points to eye position from vertex. Otherwise
    // direction to eye is: eyePosition - mvVertex
    vec3 mvDirToEye = -mvVertex;
    vec3 tbnDirToEye;
    tbnDirToEye.x = dot(mvDirToEye, mvTangent);
    tbnDirToEye.y = dot(mvDirToEye, mvBinormal);
    tbnDirToEye.z = dot(mvDirToEye, mvNormal);
    
    // Light direction from vertex
    vec3 mvDirToLight = vec3(light.position*15.0) - mvVertex; // For positional lights lights
    tbnDirToLight.x = dot(mvDirToLight, mvTangent);
    tbnDirToLight.y = dot(mvDirToLight, mvBinormal);
    tbnDirToLight.z = dot(mvDirToLight, mvNormal);
        
    // Half-vector for specular highlights
    tbnHalfVector = normalize(tbnDirToEye + tbnDirToLight);
	
}

