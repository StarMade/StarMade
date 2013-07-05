// Author: Tommy Hinks (thinks@hotmail.com) 2005 
 
uniform sampler2D testTexture; 
 
// Fragment color 
varying vec4 v_color; 
 
void main( void ) 
{ 
     // Multiply color and texture values 
     gl_FragColor = v_color*texture2D( testTexture, gl_TexCoord[0].st ); 
} 