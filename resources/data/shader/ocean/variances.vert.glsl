uniform float N_SLOPE_VARIANCE;

uniform sampler2D spectrum_1_2_Sampler;
uniform sampler2D spectrum_3_4_Sampler;
uniform int FFT_SIZE;

uniform vec4 GRID_SIZES;
uniform float slopeVarianceDelta;

uniform float c;

varying vec2 uv;

void main() {
    uv = gl_Vertex.zw;
    gl_Position = vec4(gl_Vertex.xy, 0.0, 1.0);
}