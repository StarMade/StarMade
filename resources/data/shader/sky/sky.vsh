
varying vec4 vertPos;
varying vec3 normal;
void main()
{
	gl_Position = ftransform();
	vertPos = ftransform();
	gl_TexCoord[0] = gl_Vertex*0.001;
	normal = gl_Normal;

}