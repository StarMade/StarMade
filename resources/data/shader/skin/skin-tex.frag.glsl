#version 120


uniform sampler2D diffuseMap;



void main()
{
    // diffuse material color
    vec4 diffMaterial = texture2D(diffuseMap, gl_TexCoord[0].xy).rgba;

   

    // modulate specular ligh ting by fringe color, combine with regular lighting
    gl_FragColor = vec4(diffMaterial.xyz, diffMaterial.a);
}