#version 120

uniform sampler2D Texture;
uniform vec4      Param;

varying vec3 tCoord;
varying vec2 brightness;
varying float time;
varying float distance;


void main()
{
    vec2 texCoord;
    texCoord.y = tCoord.y;
    if (abs(2.0 * tCoord.x - 1.0) < tCoord.z)
        texCoord.x = 0.5;
    else
    {
        if (tCoord.x > 0.5)
            texCoord.x = (tCoord.x - tCoord.z) / (1.0 - tCoord.z);
        else
            texCoord.x = tCoord.x / (1.0 - tCoord.z);
    }

    vec4 color = texture2D(Texture, texCoord);
    if(length(color.rgb) < 1.1){
    	discard;
    }
    
    gl_FragColor.rgb = brightness.y  * color.rgb;
    gl_FragColor.a = brightness.y * color.a;
}
