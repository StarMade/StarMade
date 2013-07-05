/*    
 	This file is part of jME Planet Demo.

    jME Planet Demo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation.

    jME Planet Demo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with jME Planet Demo.  If not, see <http://www.gnu.org/licenses/>.
*/
const float LOG2 = 1.442695;
uniform sampler2D baseMap;
uniform sampler2D specMap;
uniform sampler2D normalMap;
uniform sampler2D cloudsMap;


varying vec3 vNormal;

varying vec3 vLightDirectionInWorldSpace;
varying vec3 vViewDirectionInWorldSpace;

varying vec3 vLightDirectionInTangentSpace;
varying vec3 vViewDirectionInTangentSpace;

varying vec2 vTexCoords;


uniform float density;
uniform float dist;
uniform float fSpecularPower;
uniform float fCloudHeight;
uniform float fCloudRotation; //Les nuages se deplace lentement

uniform vec4 fvDiffuse; //Couleur diffuse du soleil
uniform vec4 fvSpecular; //Couleur speculaire du soleil


float modff(float x, float y){
	return x - y * floor(x/y);
}

void main(void)
{
   vec3 normal           = normalize( ( texture2D( normalMap, vTexCoords ).xyz * 2.0 ) - 1.0 );
   
   vec3 simpleNormal     = normalize(vNormal);
   
   vec3 lightDirection   = normalize(vLightDirectionInTangentSpace);
   vec3 viewDirection    = normalize(vViewDirectionInTangentSpace);
   
   vec4 diffuseColor = texture2D(baseMap, vTexCoords); 
   
   float NdotL = dot(normal, lightDirection);
   
   vec3 lightDirectionWorld = normalize(vLightDirectionInWorldSpace);
   vec3 viewDirectionWorld = normalize(vViewDirectionInWorldSpace);
   
   float NdotLCloud = dot(simpleNormal, lightDirectionWorld);
   
   vec3 RCloud = reflect(-lightDirectionWorld, simpleNormal);
   float SpecularCloud = pow(max(0.0, dot(viewDirectionWorld, RCloud)), fSpecularPower) * 0.80;
   
   vec3 R = reflect(-lightDirection, normal);
   float NdotR = max(0.0, dot(viewDirection, R));
   
   float specularFactor = pow(NdotR, fSpecularPower);
   
   vec4 specularColor = texture2D(specMap, vTexCoords);
   
   vec4 specular = specularFactor * specularColor;
   
   vec4 diffuseFinal = diffuseColor * NdotL * fvDiffuse;
   
   vec2 cloudTexCoord = vTexCoords;
    cloudTexCoord.x = modff((cloudTexCoord.x+fCloudRotation),1.0);
   
   vec4 cloudColor = texture2D(cloudsMap, cloudTexCoord);
   
   float zFactor = min(0.01, abs(fCloudHeight/lightDirection.z));
   vec2 displacement = -zFactor * lightDirection.yx;
   
   float shadowModule = 1.0 - texture2D(cloudsMap, cloudTexCoord + displacement).w;
   
   vec4 beforeCloud = diffuseFinal + specular * fvSpecular;
   
   beforeCloud *= shadowModule;
   
   vec4 finalCloudColor;
   finalCloudColor.xyz = cloudColor.xyz * fvDiffuse.xyz * 
   												NdotLCloud + fvSpecular.xyz*SpecularCloud;
   finalCloudColor.w = cloudColor.w;
   
   //gl_FragColor.xyz = -viewDirection.xyz;
   
   //gl_FragColor = vec4(NdotLCloud);
   //gl_FragColor = diffuseColor;//beforeCloud;
   //gl_FragColor = mix(beforeCloud, finalCloudColor, 0.5);
   
   float z = gl_FragCoord.z / gl_FragCoord.w;
   float fogFactor = exp2( -density * 
					   density * 
					   z * 
					   z * 
					   LOG2 );
	fogFactor = 1.0 - clamp(fogFactor*2.2, 0.0, 1.0);
   
   
   gl_FragColor = mix(beforeCloud, finalCloudColor, finalCloudColor.w);
   if(dist >= 0.0){
    	gl_FragColor.a *= fogFactor;
    }
   
   //vec3 light = normalize(vLightDirectionInWorldSpace);
   //gl_FragColor = vec4(light.x, light.y, light.z, 1.0);
}

