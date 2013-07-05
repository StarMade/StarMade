#version 400

layout( location = 0 ) out vec4 FragColor;

in vec2 TexCoord; // From the geometry shader

in vec3 OutColor;

in vec2 center;

uniform sampler1D antialiasTex;

const int NUM_SAMPLES = 20 ;
	
void main()
{
	
	vec4 a = texture(antialiasTex, max(0.05, abs(TexCoord.x))*0.99); 
	vec4 b = texture(antialiasTex, max(0.05, abs(TexCoord.y))*0.99); 
	vec4 col = vec4(OutColor,1) * vec4(a.x*b.x);
	FragColor = bloom(col);
}
	const float exposure  = 3.0;
	const float density = 1.4;
	const float decay  = 1.02;
	const float weight  = 1.4;
	
vec4 bloom(){
	vec4 ret = vec4(0);
	
	  vec2 deltaTextCoord = vec2( TexCoord.st  - center.xy); //
	  vec2 textCoo = TexCoord.st;
	  deltaTextCoord *= 1.0 / float(NUM_SAMPLES) * density;
	  float illuminationDecay = 1.0;


	   for(int i=0; i < NUM_SAMPLES ; i++)
	   {
	     textCoo -= deltaTextCoord;
	     vec4 sample = texture2D(firstPass, textCoo );
	          sample *= illuminationDecay * weight;
	          ret += sample;
	          illuminationDecay *= decay;
	   }
  		ret *= exposure;
  
  return ret;
}