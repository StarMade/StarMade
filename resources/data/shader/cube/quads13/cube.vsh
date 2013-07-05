#version 120

uniform int animationTime;

const vec3 lightColor = vec3(1,1,1);
const float tiling = 0.0625;
const float tilingH = 0.03;
const float adi = 0.00485;

varying vec3 occlusion;
varying vec2 overlayTexCoords;
varying vec2 poweredOverlayTexCoords;
varying vec2 mainTexCoords;
varying float textureIndex;
varying vec3 vFragColor;

void calculateLight(vec3 normal, vec3 VertexPosition){
  // figure angle to light
	  	vec3 tnorm = normalize( gl_NormalMatrix * normal);
	  	
		vec4 eyeCoords = gl_ModelViewMatrix * vec4(VertexPosition,1);
		
		vec3 s = normalize(vec3(gl_LightSource[0].position.xyz - eyeCoords.xyz));
		
		vec3 v = normalize(-eyeCoords.xyz);
	
		vec3 r = reflect( -s, tnorm );
		vec3 ambient = vec3(1,1,1);// * Material.Ka;
		float sDotN = max( dot(s,tnorm), 0.0 );
		vec3 diffuse = ((lightColor * sDotN * 1.2) * occlusion) * occlusion;
		vec3 spec = vec3(0.005);
		if( sDotN > 0.0 ){
			spec = (vec3(2.9)  * pow( max( dot(r,v), 0.0 ), 3 )*occlusion)*occlusion;
		}
		vFragColor = ambient + diffuse + spec;
}

/*
void calculateLight(vec3 normal, vec3 v){
  // figure angle to light
	vec3 lDirNorm  = normalize((gl_ModelViewMatrix * vec4(v,1)).xyz);
  		
	vec3 eyeNormal = normalize( gl_NormalMatrix * normal);
	  
	float lightInten = max(0.0, dot(eyeNormal, lDirNorm));
	
	vFragColor = (lightColor) * lightInten + vec3(1.0,1.0,1.0);
}
*/

vec2 extractMantissa(float inP){
	vec2 outP = vec2(inP, 0.0);
	/*

	if(outP.x >= 4294967296.0){
		outP.y += 128.0;
		outP.x -= 4294967296.0;
	}
	if(outP.x >= 2147483648.0){
		outP.y += 64.0;
		outP.x -= 2147483648.0;
	}
	if(outP.x >= 1073741824.0){
		outP.y += 32.0;
		outP.x -= 1073741824.0;
	}
	if(outP.x >= 536870912.0){
		outP.y += 16.0;
		outP.x -= 536870912.0;
	}
	if(outP.x >= 268435456.0){
		outP.y += 8.0;
		outP.x -= 268435456.0;
	}
	if(outP.x >= 134217728.0){
		outP.y += 4.0;
		outP.x -= 134217728.0;
	}
	if(outP.x >= 67108864.0){
		outP.y += 2.0;
		outP.x -= 67108864.0;
	}
	if(outP.x >= 33554432.0){
		outP.y += 1.0;
		outP.x -= 33554432.0;
	}*/
	
	float b = floor(outP.x / 33554432.0)*33554432.0;
	outP.y += log2(b) - 24.0;
	outP.x -= b;
	
	
	return outP;
	/*
	4294967296
	2147483648
	1073741824
	536870912
	268435456
	134217728
	67108864
	33554432*/
}

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
	
	float mirror =  floor(info / 4194304.0);
	info -= mirror * 4194304.0;
	
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


	poweredOverlayTexCoords = vec2(-1.0);
	
	float type = typeE;

	vec2 texCoords;
	
	float mTex = texCodeE * 0.25;
	
	
	occlusion =  vec3(max(0.05, red / 16.0), max(0.05, green / 16.0), max(0.05, blue / 16.0));
	
	
	
	textureIndex = layerE;
	
	type += animatedE * animationTime;
	
	float xIndex = mod(type, 16.0);
	float yIndex = floor(type / 16.0);
	
	
	vec2 quad = vec2(mod(floor(mTex * 4.0), 2.0), mod(floor(mTex * 2.0), 2.0));
	//either 0,0; 0,1; 1,1; 1,0
	
	vec2 quadPos = vec2(-0.5 + quad.y, -0.5 + quad.x);
	
	
	 //8,8,8 = 8736
	float z = floor(vIndex / 256.0);
	float y = floor((vIndex - z * 256.0) / 16.0);
	float x = vIndex - (y * 16.0 + z * 256.0);
	
	
	vec3 vPos = vec3(x-8.0,y-8.0,z-8.0);
	
	if(mirror == 1.0){
		quad.x = 1.0 - quad.x;
	}
	if(sideId == 0.0){
		//occlusion = vec3(1.0, 1.0, 0.0);
		vPos.x += 0.5;
		vPos.y += quadPos.x;
		vPos.z += quadPos.y;
		
		
		if(blockType == 0){
			texCoords = vec2( (1.0 - quad.x)*tiling, (1.0 - quad.y)*tiling );
		}else{
			texCoords = vec2( (quad.x)*tiling, (1.0 - quad.y)*tiling );
		}
		
		calculateLight(vec3(1,0,0), vPos);
	}else if(sideId == 1.0){
		//occlusion = vec3(0.0, 0.0, 1.0);
		vPos.x -= 0.5;
		vPos.y += quadPos.x;
		vPos.z += quadPos.y;
		
		
		if(blockType == 0){
			texCoords = vec2( (quad.x)*tiling, (1.0 - quad.y)*tiling );
		}else{
			texCoords = vec2( (1.0 - quad.x)*tiling, (1.0 - quad.y)*tiling );
		}
		calculateLight(vec3(-1,0,0), vPos);
		
	}else if(sideId == 2.0){
		vPos.y += 0.5;
		vPos.x += quadPos.y;
		vPos.z += quadPos.x;
		
		
		
		if(blockType == 0){
			texCoords = vec2( (1.0 - quad.x)*tiling, (1.0 - quad.y)*tiling );
		}else{
			texCoords = vec2( (quad.x)*tiling, (quad.y)*tiling );
		}
		calculateLight(vec3(0,1,0), vPos);
		
	}else if(sideId == 3.0){
		vPos.y -= 0.5;
		vPos.x += quadPos.x;
		vPos.z += quadPos.y;
		
		calculateLight(vec3(0,-1,0), vPos);
		if(blockType == 0){
			texCoords = vec2( (1.0 - quad.y)*tiling, (quad.x)*tiling );
		}else{
			texCoords = vec2( (1.0 - quad.y)*tiling, (1.0 - quad.x)*tiling );
		}
		
	}else if(sideId == 4.0){
		//occlusion = vec3(0.0, 1.0, 0.0);
		vPos.z += 0.5;
		vPos.x += quadPos.y;
		vPos.y += quadPos.x;
		
		
		
		if(blockType == 0){
			texCoords = vec2( (quad.x)*tiling, (1.0 - quad.y)*tiling );
		}else{
			texCoords = vec2( (1.0 - quad.x)*tiling, (1.0 - quad.y)*tiling );
		}
		
		calculateLight(vec3(0,0,1), vPos);
	}else{
		//occlusion = vec3(1.0, 0.0, 0.0);
		vPos.z -= 0.5;
		vPos.x += quadPos.y;
		vPos.y += quadPos.x;
		
		
		
		if(blockType == 0){
			texCoords = vec2( (1.0 - quad.x)*tiling, (1.0 - quad.y)*tiling );
		}else{
			texCoords = vec2( (quad.x)*tiling, (1.0 - quad.y)*tiling );
		}
		
		calculateLight(vec3(0,0,-1), vPos);
	}
	
	
	
	vec2 vcord = vec2(texCoords.x, texCoords.y);
	
	float hpTx = floor(hitPointsE);
	if(hpTx > 0.0){
	
		overlayTexCoords.x = float(vcord.x) + 0.125 * hpTx;
		overlayTexCoords.y = float(vcord.y);
	}else{
		overlayTexCoords = vec2(0.0,0.0);
	}
	
	
	
	
	mainTexCoords = vec2( texCoords.x + tiling * xIndex, texCoords.y + tiling * yIndex);
	
	
	float antibleeding = adi;
	
	
	
	
	gl_Position =  gl_ModelViewProjectionMatrix*vec4(vPos.xyz,1.0); 
	
	//float dist = length(gl_Position);
	
	//if( dist >= 40.0){
		//antibleeding = min(tilingH, antibleeding * (dist / 40.0));
	//}
	
	
	if(texCoords.x == 0){
		mainTexCoords.x += antibleeding;
	}else{
		mainTexCoords.x -= antibleeding;
	}
	if(texCoords.y == 0){
		mainTexCoords.y += antibleeding;
	}else{
		mainTexCoords.y -= antibleeding;
	}
	//mainTexCoords.y = min(1.0, max(0.0, mainTexCoords.y));
	//mainTexCoords.x = min(1.0, max(0.0, mainTexCoords.x));
	
}

