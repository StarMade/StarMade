#version 130
#extension GL_EXT_gpu_shader4 : enable
#extension GL_EXT_geometry_shader4 : enable
#define M_PI 3.14159265

uniform mat4 screenToCamera; // screen space to camera space
uniform mat4 cameraToWorld; // camera space to world space
uniform mat4 worldToScreen; // world space to screen space
uniform vec3 worldCamera; // camera position in world space
uniform vec3 worldSunDir; // sun direction in world space

uniform vec2 gridSize;
uniform float normals;
uniform float choppy;
uniform float time;

uniform sampler2DArray fftWavesSampler;
uniform vec4 GRID_SIZES;

uniform sampler3D slopeVarianceSampler;

uniform vec3 seaColor; // sea bottom color

varying vec2 u; // coordinates in world space used to compute P(u)
varying vec3 P; // wave point P(u) in world space

/*vec2 oceanPos(vec4 vertex) {
    
    vec3 vertexPos = (vertex).xyz;
    vec3 cameraDir = (vec4(vertexPos - worldCamera,0.0)).xyz;
    vec3 vertexDir = normalize((vertexPos - worldCamera).xyz);
    //vec3 worldDir = (cameraToWorld * vec4(cameraDir, 0.0)).xyz;
    float t =  length(worldCamera - vertexPos);
    return worldCamera.xz + ((vertexDir.xz * (t)));
}*/

/*
vec2 oceanPos(vec4 vertex) {
    vec3 cameraDir = normalize((gl_ModelViewMatrix * vertex).xyz);
    vec3 worldDir = (gl_ModelViewMatrix * vec4(cameraDir, 0.0)).xyz;
    float t = worldCamera.y / worldDir.y;
    return worldCamera.xz +   t *worldDir.xz;
}
*/
vec2 oceanPos(vec4 vertex) {
    vec3 cameraDir = (worldCamera.xyz - vertex.xyz).xyz;
    vec3 worldDir = ( vec4(cameraDir, 0.0)).xyz;
    float t = worldCamera.y / worldDir.y;
    vec2 g = worldCamera.xz - vertex.xz;
    return g  ;
}

/*vec2 oceanPos(vec4 vertex) {
    vec3 cameraDir = normalize((cameraToWorld * vertex).xyz);
    vec3 worldDir = (vec4(cameraDir, 0.0)).xyz;
    float t = -worldCamera.y / worldDir.y;
    return worldCamera.xz + t * worldDir.xz;
}*/

varying vec3 vViewDirectionInWorldSpace;
varying vec3 vLightDirectionInWorldSpace;
varying vec3 vViewDirectionInTangentSpace;
varying vec3 vLightDirectionInTangentSpace;
varying vec3 vertexPos;

varying vec3 vViewerDir;
varying vec3 vLightDir;
varying vec3 vCameraPos;
varying vec3 vNormal;

vec3 tangentAt(vec3 normal) {
	vec3 c1 = cross(normal, vec3(1.0, 0.0, 0.0));
	vec3 c2 = cross(normal, vec3(0.0, 0.0, 1.0));
	
	if(length(c1)>length(c2))
	{
		return c1;
	}
	
	return c2;
}
void main() {
    gl_Position = gl_Vertex;
    
	
	vViewerDir = gl_ModelViewMatrixInverse[3].xyz - gl_Vertex.xyz;
    vCameraPos = vec3(gl_ModelViewMatrixInverse * vec4(worldCamera, 0.0));
    
    vec2 uV = (gl_Vertex).xz;
    
   
    //u = uV;
    
    //vec2 ux =  oceanPos(gl_Vertex + vec4(gridSize.x, 0.0, 0.0, 0.0));
    //vec2 uy =  oceanPos(gl_Vertex + vec4(0.0, 0.0, gridSize.y, 0.0));
    
    float size = 12.0;
    
    vec2 ux = (gl_Vertex.xz + vec2(gridSize.x, 0.0)) ;  
    vec2 uy = (gl_Vertex.xz + vec2(0.0, gridSize.y));  
    
    vertexPos = gl_Vertex.xyz;
    
    
    
    vec2 dux = abs(ux - uV) * 2.0 * 100.0 *size;
    vec2 duy = abs(uy - uV) * 2.0 * 100.0 *size;

	vec4 gs = GRID_SIZES;
	
	
	vec4 a = 	size*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.x + time/2, 0.0), dux / gs.x , duy / gs.x );
    vec4 b =   size/2*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.y + time/4, 0.0), dux / gs.y , duy / gs.y );
    vec4 c =   size/3*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.z + time/6, 0.0), dux / gs.z , duy / gs.z );
    vec4 d =   size/4*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.w + time/8, 0.0), dux / gs.w , duy / gs.w );
    
	//gs *=  time;
    vec3 dP = vec3(0.0);
    dP.y +=  a.x;
    dP.y +=  b.y;
    dP.y +=  c.z;
    dP.y +=  d.w;
	
	 vec3 normal 		= (vec3(0.0,1.0,0.0));
	 //normal 		= (gl_NormalMatrix * (vec3(0,10,0)+(vec3(a+b+c).xyz)));
	
	 
     vec3 binormal = tangentAt(normalize(normal));
     vec3 tangent  = cross(normalize(normal), binormal);
   
     vNormal              = gl_NormalMatrix * normalize(normal);
     vec3 vBinormal       = gl_NormalMatrix * binormal;
     vec3 vTangent        = gl_NormalMatrix * tangent;
	
	
    if (choppy > 0.0) {
        dP.xz += size*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.x, 3.0), dux / gs.x, duy / gs.x).xy;
        dP.xz += size*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.y, 3.0), dux / gs.y, duy / gs.y).zw;
        dP.xz += size*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.z, 4.0), dux / gs.z, duy / gs.z).xy;
        dP.xz += size*texture2DArrayGrad(fftWavesSampler, vec3(uV / gs.w, 4.0), dux / gs.w, duy / gs.w).zw;
    }

    P = vec3(uV.x + dP.x, dP.y, uV.y + dP.z);

 	u = uV;
 	
 	vec3 vertexPosition = ( gl_ModelViewMatrix * vec4((P),1.0) ).xyz;
   	vViewDirectionInWorldSpace =  ( -vertexPosition );
   
   vLightDir = vec3( gl_ModelViewMatrixInverse * (  gl_LightSource[0].position) ) ;
   
   
   	vec4 ecPos;
    ecPos = gl_ModelViewMatrix * gl_Vertex;
	vec3 aux = vec3(gl_LightSource[0].position.xyz - vertexPosition);
	vLightDirectionInWorldSpace = normalize(aux);
 	
 	
 	
   vViewDirectionInTangentSpace.x = dot(vViewDirectionInWorldSpace, vTangent);
   vViewDirectionInTangentSpace.y = dot(vViewDirectionInWorldSpace, vBinormal);
   vViewDirectionInTangentSpace.z = dot(vViewDirectionInWorldSpace, vNormal);
   
   vLightDirectionInTangentSpace.x = dot(vLightDirectionInWorldSpace, vTangent);
   vLightDirectionInTangentSpace.y = dot(vLightDirectionInWorldSpace, vBinormal);
   vLightDirectionInTangentSpace.z = dot(vLightDirectionInWorldSpace, vNormal);

    gl_Position = ( gl_ModelViewProjectionMatrix * vec4(P, 1.0));//worldToScreen * vec4(P, 1.0);
}