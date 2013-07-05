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
// Function declarations
//

//VS_OUTPUT HoffmanShader( in VS_INPUT Input );

//
// Function definitions
//


varying vec4 Position;
varying vec2 TerrainCoord;
varying vec3 Normal;
varying vec3 Lin;
varying vec3 Fex;

void HoffmanShader( in VS_INPUT Input ) {
    vec4 worldPos;
    vec3 viewDir;
    float distance;
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
	vec4 ecPos;
    ecPos = gl_ModelViewMatrix * gl_Vertex;
//	aux = vec3(gl_LightSource[0].position-ecPos);
	aux = vec3(gl_LightSource[0].position-gl_Vertex);
	vec3 sunDirection = normalize(aux);
	
	mat4 worldView = gl_ModelViewMatrix ;
	mat4 worldViewProject = gl_ModelViewProjectionMatrix;

    worldPos = ( worldView * Input.Position );
    
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
    
    
    Position = ( worldViewProject * Input.Position  );
    TerrainCoord = Input.TexCoord.xy ;
    Normal = Input.Normal;
    Lin = inscatter;
    Fex = totalExtinction;
}


//
// Translator's entry point
//
void main() {
    VS_INPUT xlat_temp_Input;
    xlat_temp_Input.Position = vec4( gl_Vertex);
    xlat_temp_Input.Normal = vec3( gl_Normal);
    xlat_temp_Input.TexCoord = vec2( gl_MultiTexCoord0);

    HoffmanShader( xlat_temp_Input);

 	//gl_Position = ftransform();
 	gl_Position = vec4( Position);
 	gl_TexCoord[0] = vec4( TerrainCoord, 0.0, 0.0);

    
   
    //gl_TexCoord[1] = vec4( xlat_retVal.Normal, 0.0);
    //gl_FrontColor = vec4( xlat_retVal.Lin, 0.0);
    //gl_FrontSecondaryColor = vec4( xlat_retVal.Fex, 0.0);
}

