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

varying vec4 position;

void main(void) {
    // Vertex location
    position = ftransform();
	gl_Position = position;
    #ifdef __GLSL_CG_DATA_TYPES
  	gl_ClipVertex = position;
    #endif
    

    // Texture coordinates
    // Multiplication with texture matrix can be omitted if default (identity matrix) is used. To
    // use the texture matrix comment the first and uncomment the second line.
    gl_TexCoord[0] = gl_MultiTexCoord0;

    // Tangent space vectors (TBN)
    // The binormal can either be passed as an attribute or calculated as cross(normal, tangent).
    vec3 mvTangent = gl_NormalMatrix * tangent;
    vec3 mvBinormal = gl_NormalMatrix * binormal;
    vec3 mvNormal = gl_NormalMatrix * gl_Normal;
    // vec3 mvBinormal = cross(mvNormal, mvTangent);

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
    vec3 mvDirToLight = vec3(light.position) - mvVertex; // For positional lights lights
    tbnDirToLight.x = dot(mvDirToLight, mvTangent);
    tbnDirToLight.y = dot(mvDirToLight, mvBinormal);
    tbnDirToLight.z = dot(mvDirToLight, mvNormal);
        
    // Half-vector for specular highlights
    tbnHalfVector = normalize(tbnDirToEye + tbnDirToLight);
    }
    
    