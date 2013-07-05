 uniform float exposure;
 uniform float decay;
 uniform float density;
 uniform float weight;
 uniform vec2 lightPositionOnScreen;
 uniform sampler2D firstPass;
 const int NUM_SAMPLES = 20 ;

 void main()
 {
	exposure *= 3.0;
	//density*=1.4;
	//decay *= 1.02;
	//weight *= 1.4;
  vec2 deltaTextCoord = vec2( gl_TexCoord[0].st  - lightPositionOnScreen.xy); //
  vec2 textCoo = gl_TexCoord[0].st;
  deltaTextCoord *= 1.0 / float(NUM_SAMPLES) * density;
  float illuminationDecay = 1.0;


  for(int i=0; i < NUM_SAMPLES ; i++)
   {
     textCoo -= deltaTextCoord;
     vec4 sample = texture2D(firstPass, textCoo );
          sample *= illuminationDecay * weight;
          gl_FragColor += sample;
          illuminationDecay *= decay;
  }
  gl_FragColor *= exposure;
}





   