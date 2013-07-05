#version 120


uniform sampler2D diffuseMap;
uniform sampler2D fringeMap;

varying vec3 diffColor;
varying vec3 specColor;
varying vec2 viewDepth;

void main()
{
    // diffuse material color
    vec3 diffMaterial = texture2D(diffuseMap, gl_TexCoord[0].xy).rgb;

    // lookup fringe value based on view depth
    vec3 fringeColor = texture2D(fringeMap, viewDepth).rgb;

    // modulate specular ligh ting by fringe color, combine with regular lighting
    gl_FragColor = vec4(diffColor*diffMaterial + fringeColor*specColor, 1.0);
}