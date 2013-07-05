

// Author: Tommy Hinks (thinks@hotmail.com) 2005 
 
uniform float time; 
uniform float MaxPartAge;   // Maximum particle age 
uniform float alpha;        // Alpha scaling 
uniform float pointSize;    // Point size 
uniform float smokeType; 
 
// Noise 
uniform float noiseAmp; 
uniform float noiseFreq; 
 
// Better to do color calculations in vertex shader since 
// there are less vertices than fragments. 
// Constants for blue fade 
const float blueness = 0.15;  
const float blue_fade = 1.0; 
 
const int TABSIZE = 32;     // Permutation stuff 
const float TABMASK = 31.0; 
//const float OCTAVES = 4.0;  // For fractal "noise" 
 
uniform int permTable[TABSIZE]; 
uniform float gradTable[TABSIZE*3]; 
 
varying vec4 v_color; 
 
int perm(int x) 
{ 
    return permTable[int(mod(float(x), TABMASK))]; 
} 
 
int index(int tx, int ty, int tz) 
{ 
    return perm(tx + perm(ty + perm(tz))); 
} 

vec3 glattice(int tx, int ty, int tz) 
{ 
    int i = index(tx, ty, tz); 
    return vec3(gradTable[i*3], gradTable[i*3 + 1], gradTable[i*3 + 2]); 
} 
 
vec3 hnoise(vec3 invec) 
{ 
   // Hack to avoid negative numbers 
   invec = invec + vec3(100.0, 100.0, 100.0);  
     
   // Integer part is first decimal as integer 
   // due to small scale scene. 
   vec3 ipart = floor(10.0*fract(invec)); 
   int ix = int(ipart[0]); 
   int iy = int(ipart[1]); 
   int iz = int(ipart[2]); 
    
   // Fraction vector 
   vec3 fracvec = 10.0*invec - ipart; 
 
   vec3 vertices[6]; 
   // x-comp 
   vertices[0] = glattice( (ix + 1), iy, iz ); 
   vertices[1] = glattice( (ix - 1), iy, iz ); 
    
   // y-comp 
   vertices[2] = glattice( ix, (iy + 1), iz ); 
   vertices[3] = glattice( ix, (iy - 1), iz ); 
    
   // z-comp 
   vertices[4] = glattice( ix, iy, (iz + 1) ); 
   vertices[5] = glattice( ix, iy, (iz - 1) ); 
 
   vec3 result; 
    
   // Simple 'interpolation', should be enough... 
   result = vertices[1] + smoothstep(0.0, 1.0, fracvec.x)*(vertices[0] - vertices[1]) + vertices[3] + smoothstep(0.0, 1.0, 
fracvec.y)*(vertices[2] - vertices[3]) + vertices[5] + smoothstep(0.0, 1.0, fracvec.z)*(vertices[4] - vertices[5]); 
    
   return result; 
} 
 
void main( void ) 
{ 
    // NOT USED!!  
    /* 
    vec3 vHnoise; 
    float i; 
    float freq = time*noiseFreq; 
     
    // Fractal "noise" 
    // Due to the fact that noiseAmp takes on small values 
    // the fractal "noise" may be indistinguishable(?) from just "noise" 
    for(i = 0.0; i < OCTAVES; i++) 
    { 
        // (Powers of two could be precalculated and stored in  
        // a texture or a uniform array to speed up program, and so could 
        // their inverses...) 
        float exp2 = exp(i);   
        vHnoise = vHnoise + (1.0/exp2)*hnoise(gl_Vertex.xyz - exp2*freq); 
    } 
    */ 
     
    vec3 vHnoise = hnoise(gl_Vertex.xyz - time*noiseFreq); 
          
    // Add "noise" to gl_Vertex     
    vec3 glVert = vec3(gl_Vertex.xyz) + noiseAmp*vHnoise; 
    
    gl_Position = gl_ModelViewProjectionMatrix * vec4(glVert, 1.0); 
    
     
    v_color = gl_Color;
    // Calculate alpha fade 
    v_color.a = alpha*(smoothstep(smokeType*MaxPartAge, (1.0 - smokeType)*MaxPartAge, v_color.a)); 
     
    // Use city block distance for blue edges... 
    v_color.b = v_color.b + blueness*smoothstep(0.0, blue_fade, (abs(glVert.x) + abs(glVert.z))); 
     
    // Set pointsize 
    // Smaller points at the edges enables smaller pointSize, 
    // which reduces total number of fragments and speeds up the program. 
    gl_PointSize = min(pointSize - 10.0*(abs(glVert.x) + abs(glVert.z)), gl_Point.sizeMax); 
    
    
}