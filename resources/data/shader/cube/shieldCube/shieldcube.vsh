#version 120

uniform int animationTime;

const vec3 lightColor = vec3(1,1,1);
const float tiling = 0.0625;
const float tilingH = 0.03;
const float adi = 0.004;


uniform vec3 segPos;
uniform vec3 m_Collisions[8];
uniform float m_TexCoordMult;
uniform int m_CollisionNum;
//some crappy ATI cards do not support varying arrays
varying float dist0;
varying float dist1;
varying float dist2;
varying float dist3;
varying float dist4;
varying float dist5;
varying float dist6;
varying float dist7;

void main()
{

	float indexInfo = gl_Vertex.x;
	
	
	float blue =  floor(indexInfo / 1048576.0);
	indexInfo -= blue * 1048576.0;
	
	float green =  floor(indexInfo / 65536.0);
	indexInfo -= green * 65536.0;
	
	float red =  floor(indexInfo / 4096.0);
	indexInfo -= red * 4096.0;
	
	float vIndex = floor(indexInfo);
	indexInfo -= vIndex;
	
	//float test = indexInfo*100.0;


	float info = gl_Vertex.y;
	
	//float test =  floor(info / 16777216.0);
	//info -= test * 16777216.0;
	
	//vec2 man = extractMantissa(info);
	//info = man.x;
	
	//float test = man.y;
	
	
	float blockType =  floor(info / 2097152.0);
	info -= blockType * 2097152.0;
	
	float animatedE =  floor(info / 1048576.0);
	info -= animatedE * 1048576.0;
	
	float hitPointsE =  floor(info / 131072.0) ;
	info -= hitPointsE * 131072.0;
	
	float typeE =  floor(info / 512.0) ;
	info -= typeE * 512.0;
	
	float layerE =  floor(info / 32.0) ;
	info -= layerE * 32.0;
	
	float sideId =  (floor(info / 4.0));
	info -= sideId * 4.0;
	
	float texCodeE = (floor(info));
	
	

	vec2 texCoords;
	
	float mTex = texCodeE * 0.25;
	
	
	
	vec2 quad = vec2(mod(floor(mTex * 4.0), 2.0), mod(floor(mTex * 2.0), 2.0));
	//either 0,0; 0,1; 1,1; 1,0
	
	vec2 quadPos = vec2(-0.5 + quad.y, -0.5 + quad.x);
	
	
	 //8,8,8 = 8736
	float z = floor(vIndex / 256.0);
	float y = floor((vIndex - z * 256.0) / 16.0);
	float x = vIndex - (y * 16.0 + z * 256.0);
	
	
	vec3 vPos = vec3(x-8.0,y-8.0,z-8.0);
	
	
	if(sideId == 0.0){
		vPos.x += 0.5;
		vPos.y += quadPos.x;
		vPos.z += quadPos.y;
	}else if(sideId == 1.0){
		vPos.x -= 0.5;
		vPos.y += quadPos.x;
		vPos.z += quadPos.y;
	}else if(sideId == 2.0){
		vPos.y += 0.5;
		vPos.x += quadPos.y;
		vPos.z += quadPos.x;
	}else if(sideId == 3.0){
		vPos.y -= 0.5;
		vPos.x += quadPos.x;
		vPos.z += quadPos.y;
	}else if(sideId == 4.0){
		vPos.z += 0.5;
		vPos.x += quadPos.y;
		vPos.y += quadPos.x;
	}else{
		vPos.z -= 0.5;
		vPos.x += quadPos.y;
		vPos.y += quadPos.x;
	}
	vec3 absPos = segPos + vPos;
	dist0 = length(absPos.xyz - m_Collisions[0]);
	dist1 = length(absPos.xyz - m_Collisions[1]);
	dist2 = length(absPos.xyz - m_Collisions[2]);
	dist3 = length(absPos.xyz - m_Collisions[3]);
	dist4 = length(absPos.xyz - m_Collisions[4]);
	dist5 = length(absPos.xyz - m_Collisions[5]);
	dist6 = length(absPos.xyz - m_Collisions[6]);
	dist7 = length(absPos.xyz - m_Collisions[7]);
	
	gl_TexCoord[0].xy = quad;
	gl_Position =  gl_ModelViewProjectionMatrix*vec4(vPos.xyz,1.0); 
	

	
}

