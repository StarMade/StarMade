#version 120

varying float vertLen;
varying vec4 vertPos;
varying vec3 normal;


void main()
{
	vertLen = pow(length(gl_Vertex) * 0.0175,3.0);
	vertPos = ftransform();
	gl_Position = vertPos;
	//gl_TexCoord[0] = vec4(length(gl_Vertex*0.1));
	//gl_TexCoord[0].x = abs(gl_Vertex.x)*0.001;
	//gl_TexCoord[0].y = gl_Vertex.z*0.001;
	
	
	//gl_TexCoord[0] = gl_Vertex.xzyw*0.001;
	
		gl_TexCoord[0] = gl_MultiTexCoord0;
	
	if(gl_Vertex.y < 0){
		gl_TexCoord[0].xy = 1.0-gl_TexCoord[0].xy;
	}
	normal = gl_Normal;
}