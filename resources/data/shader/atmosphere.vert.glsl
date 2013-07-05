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
uniform float fCloudHeight;
uniform vec3 fvLightPosition;
 
varying vec3 fvNormal;
varying vec3 fvViewDirection;
varying vec3 fvLightDirection;

 
void main(void)
{   
   float atmoSizeFact = 1.0 + fCloudHeight*10.0;
   mat4 atmoSizeMat = mat4(atmoSizeFact);
   atmoSizeMat[3].w = 1.0;

   //Matrice "rescale"
   //a 0 0 0
   //0 a 0 0
   //0 0 a 0
   //0 0 0 1
   
  	mat4 newWorldMatrix = gl_ModelViewMatrix*atmoSizeMat;
   gl_Position = gl_ProjectionMatrix * newWorldMatrix * gl_Vertex;
   
   //gl_Position = ftransform();

   
  
   vec3 vertexPosition = (gl_ModelViewMatrix * gl_Vertex).xyz;
  
   fvViewDirection  = -vertexPosition;
   
   //vec4 tempLightPos;
   //tempLightPos.xyz = fvLightPosition;
   //tempLightPos.w = 0.0;
   
   //fvLightDirection = fvLightPosition-vertexPosition;
   
   vec3 aux;
   
	vec4 ecPos;
    ecPos = gl_ModelViewMatrix * gl_Vertex;
	aux = vec3(gl_LightSource[0].position-ecPos);
	fvLightDirection = normalize(aux);
   
   //fvLightDirection = ( (gl_ModelViewMatrix * tempLightPos).xyz - vertexPosition);
    
   fvNormal         = gl_NormalMatrix * gl_Normal;
   gl_FrontColor = gl_Color;
}