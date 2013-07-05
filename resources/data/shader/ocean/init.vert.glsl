
varying vec2 uv;
void main() {
    uv = gl_Vertex.zw;
    gl_Position = vec4(gl_Vertex.xy, 0.0, 1.0);
}