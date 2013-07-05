#version 120

const int NUM_BONES = 29;

attribute vec4 indices;
attribute vec4 weights;
uniform mat4 m_BoneMatrices[NUM_BONES];


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

    
    gl_TexCoord[0] = gl_MultiTexCoord0;
}