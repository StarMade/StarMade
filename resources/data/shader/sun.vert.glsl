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
//uniform vec3 fvLightPosition;
//uniform vec3 fvViewPosition;

varying vec3 vNormal;

varying vec3 vLightDirectionInWorldSpace;
varying vec3 vViewDirectionInWorldSpace;

varying vec3 vLightDirectionInTangentSpace;
varying vec3 vViewDirectionInTangentSpace;

varying vec2 vTexCoords;

vec3 tangentAt(vec3 normal) {
	vec3 c1 = cross(normal, vec3(0.0, 1.0, 0.0));
	vec3 c2 = cross(normal, vec3(0.0, 0.0, 1.0));
	
	if(length(c1)>length(c2))
	{
		return c1;
	}
	
	return c2;
}

void main(void)
{
   gl_Position = ftransform();

   vTexCoords = gl_MultiTexCoord0.xy;

   //Methode 1
   //vNormal 				= gl_NormalMatrix * gl_Normal;
   //vec3 vTangent     	= tangentAt(normalize(vNormal));
   //vec3 vBinormal     	= normalize(cross(normalize(vNormal), vTangent));

   //Methode 2
   
   vec3 binormal = tangentAt(normalize(gl_Normal));
   vec3 tangent  = cross(normalize(gl_Normal), binormal);
   
   vNormal              = gl_NormalMatrix * normalize(gl_Normal);
   vec3 vBinormal       = gl_NormalMatrix * binormal;
   vec3 vTangent        = gl_NormalMatrix * tangent;

	

	//-------------

   vec3 vertexPosition = ( gl_ModelViewMatrix * gl_Vertex ).xyz;
   
   
   //vec4 tempLightPos;
   //tempLightPos.xyz = fvLightPosition;
   //tempLightPos.w = 0.0;
   
   vViewDirectionInWorldSpace =  ( -vertexPosition );
   
   
   // this causes the light to be all around
  vLightDirectionInWorldSpace =(- vertexPosition)  ;
  
	//vec3 aux;
   
	//vec4 ecPos;
    //ecPos = gl_ModelViewMatrix * gl_Vertex;
	//aux = vec3(gl_LightSource[0].position-ecPos);
	//vLightDirectionInWorldSpace = normalize(aux);
	
   
   
   // this causes the planet to have a dark side
   //vLightDirectionInWorldSpace = ( (gl_ModelViewMatrix * tempLightPos).xyz - vertexPosition);
   
   //Transformation dans l'espace tangent
   vLightDirectionInTangentSpace.x = dot(vLightDirectionInWorldSpace, vTangent);
   vLightDirectionInTangentSpace.y = dot(vLightDirectionInWorldSpace, vBinormal);
   vLightDirectionInTangentSpace.z = dot(vLightDirectionInWorldSpace, vNormal);
   
   vViewDirectionInTangentSpace.x = dot(vViewDirectionInWorldSpace, vTangent);
   vViewDirectionInTangentSpace.y = dot(vViewDirectionInWorldSpace, vBinormal);
   vViewDirectionInTangentSpace.z = dot(vViewDirectionInWorldSpace, vNormal);
}