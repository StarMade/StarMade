#version 120

uniform vec4      Param;
uniform mat4x4    oldModelViewMatrix;

varying vec3 tCoord;
varying vec2 brightness;
varying float time;
varying float distance;

const float luminosity = 3.20;

void main()
{

	time = abs(gl_Vertex.w*0.005-0.5);
	
	distance = length(gl_Vertex.xyz - gl_MultiTexCoord0.xyz);
	
    tCoord.x = mod(floor(gl_MultiTexCoord0.w * 4.0), 2.0);
    tCoord.y = mod(floor(gl_MultiTexCoord0.w * 2.0), 2.0);

	gl_TexCoord[0].st = tCoord.xy;

    vec4 newViewPosition = gl_ModelViewMatrix * vec4(gl_Vertex.xyz, 1.0);
    vec4 oldViewPosition = oldModelViewMatrix * vec4(gl_Vertex.xyz, 1.0);
    
    vec4 viewPos;
    if (tCoord.x == 0.0){
     	viewPos = newViewPosition;
    }
    else{
		viewPos = oldViewPosition;
	}
    float Dist2 = dot(viewPos.xyz, viewPos.xyz);
    float mag = (Param.x) * exp(luminosity) / Dist2;
    float mag2 =  exp(100000.0* luminosity) / Dist2*0.1;

    brightness.x = gl_Color.x;
    brightness.y = clamp(Param.w*(mag2 - Param.y), 0.0, 1.0);

    if (mag > 1.0)  mag = 1.0 + log(mag);
    float spriteSize = Param.z*0.06 * sqrt(Dist2 * mag);

    vec2 Offset = tCoord.xy * 2.0 - 1.0;
    vec3 Ray = newViewPosition.xyz - oldViewPosition.xyz;

    if (dot(Ray, Ray) <= 1e-2) {
        viewPos.xy += Offset * (spriteSize);
        tCoord.z = 0.0;
    }
    else {
        float reyLength = 0.5 * length(newViewPosition.xy - oldViewPosition.xy);

        spriteSize /= reyLength + 1.0;
        
        tCoord.z = reyLength / (reyLength + 1.0);
        
        vec3 Tangent = normalize(cross(Ray, newViewPosition.xyz));
        mat2x2 Mat = mat2x2(Tangent.y, -Tangent.x, Tangent.x, Tangent.y);
        viewPos.xy += (Mat * Offset) * spriteSize;
        
    }

    gl_Position = gl_ProjectionMatrix * viewPos;
}