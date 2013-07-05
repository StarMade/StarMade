#version 120

	
	varying vec3 ReflectDir; // The direction of the reflected ray
	uniform mat4 MVP; // Projection * ModelView
	
	void main()
	{
		ReflectDir = gl_Vertex.xyz;
		gl_Position = MVP * gl_Vertex;
	}