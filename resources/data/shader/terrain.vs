

uniform float terrainTilingFactor;


varying vec3 tbnDirToLight; // Direction to light in tangent space
varying vec3 tbnHalfVector; // Half vector in tangent space
varying float terrainHeight;




vec3 tangentAt(vec3 normal) {
	vec3 c1 = cross(normal, vec3(0.0, 1.0, 0.0));
	vec3 c2 = cross(normal, vec3(0.0, 0.0, 1.0));
	
	if(length(c1)>length(c2))
	{
		return c1;
	}
	
	return c2;
}

void main()
{

    

	 // Vertex location
    gl_Position = ftransform();
	float f = terrainTilingFactor*3.0;
	gl_TexCoord[0] = gl_MultiTexCoord0*f;
    gl_TexCoord[1] = gl_MultiTexCoord0*f;
    gl_TexCoord[2] = gl_MultiTexCoord0*f;
    gl_TexCoord[3] = gl_MultiTexCoord0*f;
    gl_TexCoord[4] = gl_MultiTexCoord0;
    gl_TexCoord[5] = gl_MultiTexCoord0;
    gl_TexCoord[6] = gl_MultiTexCoord0;
    
    gl_ClipVertex = gl_ModelViewMatrix * gl_Vertex;
    terrainHeight = gl_Vertex.y;

	vec3 binormal = tangentAt(normalize(gl_Normal));
   	vec3 tangent  = cross(normalize(gl_Normal), binormal);

    // Tangent space vectors (TBN)
    // The binormal can either be passed as an attribute or calculated as cross(normal, tangent).
    vec3 mvTangent = gl_NormalMatrix * tangent;
    vec3 mvBinormal = gl_NormalMatrix * binormal;
    vec3 mvNormal = gl_NormalMatrix * normalize(gl_Normal);
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
    
    vec3 aux;
   
	vec4 ecPos;
    ecPos = gl_ModelViewMatrix * gl_Vertex;
	aux = vec3(gl_LightSource[0].position-ecPos);
	vec3 mvDirToLight = normalize(aux);
	
    //vec3 mvDirToLight = vec3(light.position) - mvVertex; // For positional lights lights
    tbnDirToLight.x = dot(mvDirToLight, mvTangent);
    tbnDirToLight.y = dot(mvDirToLight, mvBinormal);
    tbnDirToLight.z = dot(mvDirToLight, mvNormal);
        
    // Half-vector for specular highlights
    tbnHalfVector = normalize(tbnDirToEye + tbnDirToLight);

}

