#version 120

const int NUM_BONES = 28;

attribute vec4 indices;
attribute vec4 weights;
uniform mat4 m_BoneMatrices[NUM_BONES];

uniform vec3 lightVector;
uniform vec3 eyeVector;
uniform float filmDepth;

varying vec3 diffColor;
varying vec3 specColor;
varying vec2 viewDepth;


void skin(inout vec4 position, inout vec4 normal){
    vec4 index  = indices;
    vec4 weight = weights;

    vec4 newPos    = vec4(0.0);
    vec4 newNormal = vec4(0.0);

    for (float i = 0.0; i < 4.0; i += 1.0){
    
        mat4 skinMat = m_BoneMatrices[int(index.x)];
        newPos    += weight.x * (skinMat * position);
        newNormal += weight.x * (skinMat * normal);
        
        //rotate coordinates
        index = index.yzwx;
        weight = weight.yzwx;
    }

    position = newPos;
    normal = newNormal;
}

void main()
{


	vec4 vertex = gl_Vertex;
	vec4 normal = vec4(gl_Normal.x, gl_Normal.y, gl_Normal.z, 1);
	
	skin(vertex, normal);
	
	
	gl_Position = gl_ModelViewProjectionMatrix * vertex;
	
	vec3 normalVec = gl_NormalMatrix * normal.xyz;
	 normalVec = normalize(normalVec);

    // compute the eye->vertex vector
    vec3 eyeVec = eyeVector;   //infinite viewer (Best looking)

    // compute the view depth for the thin film
    viewDepth = vec2((1.0 / dot(normalVec, eyeVec)) * filmDepth);
    
    // store normalized light vector
    vec3 lightVec = normalize(lightVector);

    // calculate half angle vector
    vec3 halfAngleVec = normalize(lightVec + eyeVec);

    // calculate diffuse component
    float diffuse = max(dot(normalVec, lightVec), 0.0);
    diffuse = max(0.2, diffuse);

    // calculate specular component
    float specular = max(dot(normalVec, halfAngleVec), 0.0);
    specular = pow(specular, 32.0);

    // output final lighting results
    diffColor = vec3(diffuse);
    specColor = vec3(specular);
    
    gl_TexCoord[0] = gl_MultiTexCoord0;
}