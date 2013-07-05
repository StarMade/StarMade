// ---------------------------------------------------------------------------
// REFLECTED SKY RADIANCE
// ---------------------------------------------------------------------------

// manual anisotropic filter
vec4 myTexture2DGrad(sampler2D tex, vec2 u, vec2 s, vec2 t)
{
    const float TEX_SIZE = 512.0; // 'tex' size in pixels
    const int N = 1; // use (2*N+1)^2 samples
    vec4 r = vec4(0.0);
    float l = max(0.0, log2(max(length(s), length(t)) * TEX_SIZE) - 0.0);
    for (int i = -N; i <= N; ++i) {
        for (int j = -N; j <= N; ++j) {
            r += texture2DLod(tex, u + (s * float(i) + t * float(j)) / float(N), l);
        }
    }
    return r / pow(2.0 * float(N) + 1.0, 2.0);
}

// V, N, Tx, Ty in world space
vec2 U(vec2 zeta, vec3 V, vec3 N, vec3 Tx, vec3 Ty) {
    vec3 f = normalize(vec3(-zeta, 1.0)); // tangent space
    vec3 F = f.x * Tx + f.y * N + f.z * Ty; // world space //adapted
    vec3 R = 2.0 * dot(F, V) * F - V;
    return (R.xz / (1.0 + R.y)) ;
}

float meanFresnel(float cosThetaV, float sigmaV) {
	return pow(1.0 - cosThetaV, 5.0 * exp(-2.69 * sigmaV)) / (1.0 + 22.7 * pow(sigmaV, 1.5));
}

// V, N in world space
float meanFresnel(vec3 V, vec3 N, vec2 sigmaSq) {
    vec2 v = V.xz; // view direction in wind space
    vec2 t = v * v / (1.0 - V.y * V.y); // cos^2 and sin^2 of view direction
    float sigmaV2 = dot(t, sigmaSq); // slope variance in view direction
    return meanFresnel(dot(V, N), sqrt(sigmaV2));
}

// V, N, Tx, Ty in world space;
vec3 meanSkyRadiance(vec3 V, vec3 N, vec3 Tx, vec3 Ty, vec2 sigmaSq) {
    vec4 result = vec4(0.0);

    const float eps = 0.001;
    vec2 u0 = U(vec2(0.0), V, N, Tx, Ty);
    vec2 dux = 2.0 * (U(vec2(eps, 0.0), V, N, Tx, Ty) - u0) / eps * sqrt(sigmaSq.x);
    vec2 duy = 2.0 * (U(vec2(0.0, eps), V, N, Tx, Ty) - u0) / eps * sqrt(sigmaSq.y);

//#ifdef HARDWARE_ANISTROPIC_FILTERING
    result = texture2DGrad(skySampler, u0 * (0.5 / 1.1) + 0.5, dux * (0.5 / 1.1), duy * (0.5 / 1.1));
//#else
//    result = myTexture2DGrad(skySampler, u0 * (0.5 / 1.1) + 0.5, dux * (0.5 / 1.1), duy * (0.5 / 1.1));
//#endif
    //if texture2DLod and texture2DGrad are not defined, you can use this (no filtering):
    //result = texture2D(skySampler, u0 * (0.5 / 1.1) + 0.5);

    return result.rgb;
}

// ----------------------------------------------------------------------------
vec3 tangentAt(vec3 normal) {
	vec3 c1 = cross(normal, vec3(0.0, 1.0, 0.0));
	vec3 c2 = cross(normal, vec3(0.0, 0.0, 1.0));
	
	if(length(c1)>length(c2))
	{
		return c1;
	}
	
	return c2;
}

varying vec3 vertexPos;
void main() { 
    vec3 V = normalize(viewDir);//normalize(worldCamera - P);
	vec4 gs = GRID_SIZES;
    
    vec2 ux = (vertexPos.xz + vec2(gridSize.x, 0.0));  
    vec2 uy = (vertexPos.xz + vec2(0.0, gridSize.y));  
    
    vec2 dux = abs(ux - u) * 2.0 ;
    vec2 duy = abs(uy - u) * 2.0 ;
    
    //vec2 slopes	 =  texture2DArrayGrad(fftWavesSampler, vec3(u / gs.x , 0.0), dux / gs.x , duy / gs.x ).xy;
    //slopes 		+=  texture2DArrayGrad(fftWavesSampler, vec3(u / gs.y , 0.0), dux / gs.y , duy / gs.y ).zw;
    //slopes 		+=  texture2DArrayGrad(fftWavesSampler, vec3(u / gs.z , 0.0), dux / gs.z , duy / gs.z ).xy;
    //slopes 		+=  texture2DArrayGrad(fftWavesSampler, vec3(u / gs.w , 0.0), dux / gs.w , duy / gs.w ).zw;
    
    vec2 slopes = 	texture2DArray(fftWavesSampler, vec3(u / gs.x, 1.0)).xy;
    slopes += 		texture2DArray(fftWavesSampler, vec3(u / gs.y, 1.0)).zw;
    slopes += 		texture2DArray(fftWavesSampler, vec3(u / gs.z, 2.0)).xy;
    slopes += 		texture2DArray(fftWavesSampler, vec3(u / gs.w, 2.0)).zw;

    vec3 N = normalize(vec3(-slopes.x, 1.0, -slopes.y ));
    if (dot(V, N) < 0.0) {
        N = reflect(N, V); // reflects backfacing normals
    }

    float Jxx = dFdx(u.x);
    float Jxy = dFdy(u.x);
    float Jyx = dFdx(u.y);
    float Jyy = dFdy(u.y);
    float A = Jxx * Jxx + Jyx * Jyx;
    float B = Jxx * Jxy + Jyx * Jyy;
    float C = Jxy * Jxy + Jyy * Jyy;
    float ua = pow(A  , 0.25);
    float ub = 0.5 + 0.5 * B / sqrt(A * C);
    float uc = pow(C , 0.25);
    vec2 sigmaSq = texture3D(slopeVarianceSampler, vec3(ua, ub, uc)).xw;

    sigmaSq = max(sigmaSq, 2e-5);

 	vec3 Ty = tangentAt( N );
   	vec3 Tx  = cross( N, Ty );

   // vec3 Ty = normalize(vec3(0.0, N.z, -N.y));
    //vec3 Tx = cross(Ty, N);

//#if defined(SEA_CONTRIB) || defined(SKY_CONTRIB)
    float fresnel = 0.02 + 0.98 * meanFresnel(V, N, sigmaSq);
     //fresnel = 0.3;
//#endif

    vec3 Lsun;
    vec3 Esky;
    vec3 extinction;
    //worldP, worldS
    vec3 wP = worldCamera;
    vec3 wV = viewDir;
	vec3 wS = worldSunDir;
    sunRadianceAndSkyIrradiance( wV, wP, wS, Lsun, Esky);

    gl_FragColor = vec4(0.0);

//#ifdef SUN_CONTRIB
    gl_FragColor.rgb += reflectedSunRadiance(worldSunDir, V, N, Tx, Ty, sigmaSq) * Lsun;
//#endif

//#ifdef SKY_CONTRIB
    gl_FragColor.rgb += fresnel * meanSkyRadiance(V, N, Tx, Ty, sigmaSq);
//#endif

//#ifdef SEA_CONTRIB
    vec3 Lsea = seaColor * Esky / M_PI;
    //gl_FragColor.rgb += Esky / M_PI;
    //gl_FragColor.rgb += Lsun;
    //gl_FragColor.rgb += vec3(fresnel);
    gl_FragColor.rgb += (1.0 - fresnel) * Lsea;
    //gl_FragColor.rgb += texture2D(skySampler, u/100).rgb; // scale is gooood
    //gl_FragColor.rgb += texture2D(skyIrradianceSampler, u).rgb; // scale is gooood
//#endif

//#if !defined(SEA_CONTRIB) && !defined(SKY_CONTRIB) && !defined(SUN_CONTRIB)
    //gl_FragColor.rgb += 0.0001 * seaColor * (Lsun * max(dot(N, worldSunDir), 0.0) + Esky) / M_PI;
//#endif

    //gl_FragColor.rg = slopes;
    //gl_FragColor.b = 0;
    gl_FragColor.rgb = hdr(gl_FragColor.rgb);
    
}