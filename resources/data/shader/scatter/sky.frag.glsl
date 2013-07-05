//
// Structure definitions
//


varying vec4 Position;
varying vec2 TerrainCoord;
varying vec3 Normal;
varying vec3 Lin;
varying vec3 Fex;

//
// Translator's entry point
//
void main() {
    gl_FragColor = vec4( Lin, 1.00000);
}


