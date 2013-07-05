#version 120
//uniform sampler1D ColorMap;
uniform sampler2D Texture;
uniform vec4 Param;    // StarBrightCoef, StarBrightMin, StarParticleSize, StarParticleFadeOut

varying vec2 TexCoord;
varying vec2 ColBright;
void main()
{
    vec4 Color = vec4(ColBright.x);//texture1D(ColorMap, ColBright.x);
    vec4 tex = texture2D(Texture, TexCoord);
    gl_FragColor.rgb = 2.0 * ColBright.y  * Color.rgb  * tex.rgb;
    gl_FragColor.a = 1.2 * ColBright.y*tex.a;
}