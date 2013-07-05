
uniform mat4 worldToScreen;

varying vec3 worldP;

void main() {
    gl_Position = worldToScreen * vec4(gl_Vertex.xyz, 1.0);
    worldP = gl_Vertex.xyz;
}
