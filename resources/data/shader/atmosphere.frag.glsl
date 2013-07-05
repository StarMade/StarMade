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
uniform vec4 fvAtmoColor;
uniform vec4 fvDiffuse;

uniform float density;
uniform float fAtmoDensity;
uniform float fAbsPower;
uniform float fGlowPower;
uniform float dist;

varying vec3 fvNormal;
varying vec3 fvViewDirection;
varying vec3 fvLightDirection;

vec3 maxVal(vec3 rgb) {
   return rgb/max(rgb.x, max(rgb.y, rgb.z));
}

void main(void)
{
    vec3 n = normalize(fvNormal);
    vec3 v = normalize(fvViewDirection);
    vec3 l = normalize(fvLightDirection);
    
    float NdotL = dot(n, l);
    float NdotV = dot(n, v);
    
    float abs_power = max(0.0, pow( -dot(v, l), 1.0/fAbsPower));
    
    float glow =  1.0 - pow(cos(abs(NdotV) * 1.57079), fGlowPower);  
    
    vec4 color = fvDiffuse * fvAtmoColor;
    vec4 invColor = 1.0 - color;
    invColor.w = color.w;
    
    vec4 diffuseColor = mix(color, invColor, abs_power);
    
    float falloff;
    
    falloff = max(0.0,(1.0 - NdotV) *  min(1.0, NdotL+0.35) * glow );
    falloff = pow(falloff, 1.0/fAtmoDensity);

 	float z = gl_FragCoord.z / gl_FragCoord.w;
   float fogFactor = exp2( -density * 
					   density * 
					   z * 
					   z * 
					   LOG2 );
	fogFactor = 1.0 - clamp(fogFactor*2.8, 0.0, 1.0);

    gl_FragColor.xyz = maxVal(diffuseColor.xyz * falloff);
    gl_FragColor.w = diffuseColor.w * falloff;
    if(dist >= 0.0){
    	gl_FragColor.a *= fogFactor;
    }
    //gl_FragColor = vec4(glow);    
}