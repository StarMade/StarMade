uniform mat4 screenToCamera;
uniform mat4 cameraToWorld;
uniform vec3 worldCamera;
uniform vec3 worldSunDir;

varying vec3 viewDir;


void main() {
	
    viewDir = (cameraToWorld * vec4((screenToCamera * gl_Vertex).xyz, 0.0)).xyz;
    //viewDir = (gl_ModelViewMatrix * vec4((gl_ProjectionMatrix * gl_Vertex).xyz, 0.0)).xyz;
    //vec3 vertexPosition = ( gl_ModelViewMatrix * gl_Vertex.xyz ).xyz;
   	//viewDir =  ( -vertexPosition );
    gl_Position = vec4(gl_Vertex.xy, 0.9999999, 1.0);
}