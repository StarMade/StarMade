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

uniform sampler2D baseMap;
uniform sampler2D specMap;
uniform sampler2D normalMap;


varying vec3 vNormal;

varying vec3 vLightDirectionInWorldSpace;
varying vec3 vViewDirectionInWorldSpace;

varying vec3 vLightDirectionInTangentSpace;
varying vec3 vViewDirectionInTangentSpace;

varying vec2 vTexCoords;


uniform float fSpecularPower;

uniform vec4 fvDiffuse; //Couleur diffuse du soleil
uniform vec4 fvSpecular; //Couleur speculaire du soleil

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
   float SpecularCloud = pow(max(0.3, dot(viewDirectionWorld, RCloud)), fSpecularPower) * 0.80;
   
   vec3 R = reflect(-lightDirection, normal);
   float NdotR = max(0.3, dot(viewDirection, R));
   
   float specularFactor = pow(NdotR, fSpecularPower*0.1);
   
   vec4 specularColor = texture2D(specMap, vTexCoords);
   
   vec4 specular = specularFactor * specularColor;
   
   vec4 diffuseFinal = diffuseColor * NdotL * fvDiffuse;
   
    float NdotV = dot(normal, viewDirection);
    float glow =  pow(cos(abs(NdotV) * 1.57079), 3.0);  
   
   
   
   vec4 beforeCloud = diffuseFinal + specular * fvSpecular + glow;
   
   
   
   //gl_FragColor.xyz = -viewDirection.xyz;
   
   //gl_FragColor = vec4(NdotLCloud);
   //gl_FragColor = diffuseColor;//beforeCloud;
   //gl_FragColor = mix(beforeCloud, finalCloudColor, 0.5);
   gl_FragColor = beforeCloud;
   //vec3 light = normalize(vLightDirectionInWorldSpace);
   //gl_FragColor = vec4(light.x, light.y, light.z, 1.0);
}