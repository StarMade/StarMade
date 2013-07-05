#version 120
//attribute float gl_Vertex;          // Sprite center world position
//attribute vec3  gl_Color;           // Color index, vertex id, Luminosity

//uniform sampler1D ColorMap;
uniform sampler2D Texture;
uniform vec4 Param;    // StarBrightCoef, StarBrightMin, StarParticleSize, StarParticleFadeOut

varying vec2 TexCoord;
varying vec2 ColBright;


void main()
{
    vec4 viewPos = gl_ModelViewMatrix * gl_Vertex;

    float Dist2 = dot(viewPos.xyz, viewPos.xyz);
    
    float mag = Param.x * exp(6.5 * gl_Color.z) / Dist2 ;

    ColBright.x = gl_Color.x;
    ColBright.y = clamp(Param.w*(mag - Param.y), 0.0, 1.0);

    if (mag > 1.0)  mag = 1.0 + log(mag);
    float spriteSize = Param.z * sqrt(Dist2 * mag);

    TexCoord.x = mod(floor(gl_Color.y * 4.0), 2.0);
    TexCoord.y = mod(floor(gl_Color.y * 2.0), 2.0);

    viewPos.xy += spriteSize * (TexCoord * 2.0 - 1.0);
    gl_Position = gl_ProjectionMatrix * viewPos;
}