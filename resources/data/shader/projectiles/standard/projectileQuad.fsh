
uniform sampler2D tex;
varying float time;
void main()
{
	vec4 color = texture2D(tex, gl_TexCoord[0].st);
	
	// use color.r*vColor.a to make black transparent
	float distance = length(gl_TexCoord[0].st - vec2(0.5)) * 1.0;
	
	color.rbg -= distance;
	
	if(color.a < 0.05){
		discard;
	}
	
	gl_FragColor = color;
}