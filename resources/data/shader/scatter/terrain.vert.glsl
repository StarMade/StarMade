//
// Structure definitions
//


struct VS_INPUT {
    vec4 Position;
    vec3 Normal;
    vec2 TexCoord;
};


//
// Global variable definitions
//

uniform vec3 betaDashM;
uniform vec3 betaDashR;
uniform vec3 betaRPlusBetaM;
uniform vec3 hGg;
uniform vec4 multipliers;
uniform vec3 oneOverBetaRPlusBetaM;
uniform vec4 sunColorAndIntensity;
//uniform vec3 lightPosition;
//uniform vec3 sunDirection;
//uniform mat4 worldView;
//uniform mat4 worldViewProject;

const vec4 constants = vec4( 0.15, 1.4426950, 0.5, 0.0 ); // { 0.25, 1.4426950, 0.5, 0.0 };



//
// terrain variables
//

uniform float terrainTilingFactor;


varying vec3 tbnDirToLight; // Direction to light in tangent space
varying vec3 tbnHalfVector; // Half vector in tangent space
varying float terrainHeight;
varying float distance;

vec3 tangentAt(vec3 normal) {
	vec3 c1 = cross(normal, vec3(0.0, 1.0, 0.0));
	vec3 c2 = cross(normal, vec3(0.0, 0.0, 1.0));
	
	if(length(c1)>length(c2))
	{
		return c1;
	}
	
	return c2;
}

//
// Function declarations
//

//VS_OUTPUT HoffmanShader( in VS_INPUT Input );

//
// Function definitions
//


varying vec3 Lin;
varying vec3 Fex;

void HoffmanShader( ) {
    vec4 worldPos;
    vec3 viewDir;
    
    vec3 sunDir;
    float theta;
    float phase1;
    float phase2;
    vec3 extinction;
    vec3 totalExtinction;
    vec3 betaRay;
    vec3 betaMie;
    vec3 inscatter;
    
    vec3 aux;
//	vec4 ecPos;
 //   ecPos = gl_ModelViewMatrix * gl_Vertex;
//	aux = vec3(gl_LightSource[0].position-ecPos);
	aux = vec3(gl_LightSource[0].position-gl_Vertex);
	vec3 sunDirection = normalize(aux);
	
	mat4 worldView = gl_ModelViewMatrix ;
	mat4 worldViewProject = gl_ModelViewProjectionMatrix;

    worldPos = ( worldView * gl_Vertex );
    
    sunDir = normalize(gl_LightSource[0].position - worldPos).xyz;
    
    viewDir = normalize( worldPos.xyz  );
    distance = length(worldPos.xyz);
    
    
    //sunDir = normalize( ( vec4( sunDirection, 0.000000) * worldView ).xyz  );
    
    
    theta = dot( sunDir, viewDir);
    
    //this tints all the non lit sections equal
    //if(theta < 0.0){
    //	theta = 0.0;
    //}
    phase1 = (1.00000 + (theta * theta));
    phase2 = (pow( inversesqrt( (hGg.y  - (hGg.z   * theta)) ), 3.00000) * hGg.x );
    
    
    extinction = exp( ((( -betaRPlusBetaM ) * distance) * constants.x ) );
    totalExtinction = (extinction * multipliers.yzw );
    betaRay = (betaDashR * phase1);
    betaMie = (betaDashM * phase2);
    
    inscatter = (((betaRay + betaMie) * oneOverBetaRPlusBetaM) * (1.00000 - extinction));
    inscatter *= multipliers.x ;
    inscatter *= (sunColorAndIntensity.xyz  * sunColorAndIntensity.w );
    totalExtinction *= (sunColorAndIntensity.xyz  * sunColorAndIntensity.w );
    
    
    
    Lin = inscatter;
    Fex = totalExtinction;
    
    
    ////////////
    //terrain stuff
    ////////////
    
   
    
    gl_ClipVertex = gl_ModelViewMatrix * gl_Vertex;
    terrainHeight = gl_Vertex.y;
    
	gl_TexCoord[0] = gl_MultiTexCoord0*terrainTilingFactor;
    gl_TexCoord[1] = gl_MultiTexCoord0*terrainTilingFactor;
    gl_TexCoord[2] = gl_MultiTexCoord0*terrainTilingFactor;
    gl_TexCoord[3] = gl_MultiTexCoord0*terrainTilingFactor;
    gl_TexCoord[4] = gl_MultiTexCoord0;
    gl_TexCoord[5] = gl_MultiTexCoord0*terrainTilingFactor*10.0;
    gl_TexCoord[6] = gl_MultiTexCoord0*terrainTilingFactor*10.0;

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


//
// Translator's entry point
//
void main() {
   // 
    //xlat_temp_Input.Position = vec4( gl_Vertex);
    //xlat_temp_Input.Normal = vec3( gl_Normal);
    //xlat_temp_Input.TexCoord = vec2( gl_MultiTexCoord0);

    HoffmanShader( );

 	gl_Position = ftransform();
 	//gl_Position = vec4( Position);
 	//gl_TexCoord[0] = vec4( TerrainCoord, 0.0, 0.0);

    
   
    //gl_TexCoord[1] = vec4( xlat_retVal.Normal, 0.0);
    //gl_FrontColor = vec4( xlat_retVal.Lin, 0.0);
    //gl_FrontSecondaryColor = vec4( xlat_retVal.Fex, 0.0);
}

