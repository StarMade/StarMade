
uniform vec4 silhouetteColor;
uniform sampler2D silhouetteTexture;

void main(void)
{
	vec4 c = texture2D(silhouetteTexture, gl_TexCoord[0].st );
	if(c.a <= 0.2 && silhouetteColor.x == 0.0){
		discard;
	}
    gl_FragColor = vec4(silhouetteColor.x, silhouetteColor.y, silhouetteColor.z, c.a);
}