#version 120

uniform sampler2D       Scene;
uniform sampler2D       Texture;
uniform vec4            Param1; // (SunPosX, SunPosY, 1.0/fbo.width, 0.5/FlareTexture.width)
uniform vec4            Param2; // (Radius, Stride, Bright, Scale)


void main()
{
    vec2 flareTC = (gl_TexCoord[0].xy - vec2(0.5)) * Param1.z;
    vec3 conv = vec3(0.0);

     for (float x=-Param2.x; x<=Param2.x; x+=Param2.y)
     {
         for (float y=-Param2.x; y<=Param2.x; y+=Param2.y)
         {
            vec3  core = texture2D(Scene, Param1.xy - vec2(x,y) * Param2.w).rgb;
            float bright = dot(core, vec3(0.333));
            if (bright <= 1.0) continue;
            vec3 flare = texture2D(Texture,  flareTC + vec2(x,y) * Param1.w).rgb;
            conv += flare*core;
        }
    }

    gl_FragColor.rgb = conv * Param2.z;
    gl_FragColor.a = 0.0;

    //gl_FragColor.rgb += texture2DRect(Scene, Param1.xy + (gl_TexCoord[0].xy * Param1.z - vec2(0.5)) * Param2.w * 1024).rgb;
}
