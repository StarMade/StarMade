uniform float time;

void main( void )
{
		gl_Position = ftransform();
		
		vec4 texco = gl_MultiTexCoord0;
		texco.s = texco.s + time*-0.01;
		texco.t = texco.t + time*-0.01;
		
		gl_TexCoord[0] = texco;
		
		texco = gl_MultiTexCoord0;
		texco.s = texco.s + time*0.02;
		texco.t = texco.t + time*0.02;
		gl_TexCoord[1] = texco;

}