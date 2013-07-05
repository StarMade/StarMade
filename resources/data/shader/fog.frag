varying float atten; 
varying float fogFactor; 
varying vec3 lightVec, viewVec; 
uniform sampler2D normalMap;
uniform sampler2D colorMap;
uniform sampler2D colorMap2;

void main (void)
{
	vec3 lVec = normalize(lightVec);
	vec3 vVec = normalize(viewVec);
	
	vec4 base = texture2D(colorMap, gl_TexCoord[0]*2.0);
	vec3 bump = normalize(texture2D(normalMap, 
	                                gl_TexCoord[0]*2.0).xyz*2.0-1.0);
	vec4 base2 = texture2D(colorMap2, gl_TexCoord[0]*4.0);

	float diffuse = max( dot(lVec, bump), 0.0 );
	float specular = pow(clamp(dot(reflect(-vVec, bump), lVec), 0.0, 1.0), 
	                 gl_FrontMaterial.shininess );
	
	vec4 vAmbient = gl_FrontLightProduct[0].ambient * base + (base2*0.4);
	vec4 vDiffuse = gl_FrontLightProduct[0].diffuse * diffuse * base;
	vec4 vSpecular = gl_FrontLightProduct[0].specular * specular;
	
	vec4 finalColor = (vAmbient + vDiffuse + vSpecular) * atten;	
	
	gl_FragColor = mix(gl_Fog.color, finalColor, fogFactor );
}