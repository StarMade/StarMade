#version 120

	varying vec3 Position;
	
	varying vec3 Normal;
	
	varying vec2 TexCoord;
	
	uniform mat4 ModelViewMatrix;
	
	uniform mat3 NormalMatrix;
	
	uniform mat4 ProjectionMatrix;
	
	uniform mat4 MVP;
	
	void main()
	{
		gl_TexCoord[0] = gl_MultiTexCoord0;
		TexCoord = vec2(gl_TexCoord[0].st);
		Normal = normalize( NormalMatrix * gl_Normal);
		Position = vec3( ModelViewMatrix * gl_Vertex );
		gl_Position = MVP * gl_Vertex;
	}