#version 120

uniform sampler2D mainTexA;
uniform vec4 selectionColor;
void main()
{
	
	vec4 color = texture2D(mainTexA, gl_TexCoord[0].st); 
	if(color.a < 0.7){
		discard;
	}
	gl_FragColor = color * selectionColor;
}