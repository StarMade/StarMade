uniform sampler2D baseMap;

void main( void )
{
vec4 a = texture2D( baseMap, gl_TexCoord[0].xy ); //adding .xy for fucking ati cards...
vec4 b = texture2D( baseMap, gl_TexCoord[1].xy ); //adding .xy for fucking ati cards...

vec4 fragcol = mix(a,b,0.5);

gl_FragColor = fragcol;
}