uniform vec3 sunDir;

varying vec2 U; // sky map texture coordinates (world space stereographic projection)

void main() {
    gl_Position = gl_Vertex;
    U = gl_Vertex.xy * 1.1;
}