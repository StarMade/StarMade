#version 110

uniform sampler2D water_normalmap;

uniform sampler2D water_reflection;

uniform sampler2D water_refraction;

uniform sampler2D water_dudvmap;

uniform sampler2D water_depthmap;

uniform vec4 waterColor, waterDepth;

varying vec4 waterTex0; //lightpos
varying vec4 waterTex1; //moving texcoords
varying vec4 waterTex2; //moving texcoords
varying vec4 waterTex3; //for projection
varying vec4 waterTex4; //viewts


//unit 0 = water_reflection
//unit 1 = water_refraction
//unit 2 = water_normalmap
//unit 3 = water_dudvmap
//unit 4 = water_depthmap



void main(void)
{
const vec4 sca = vec4(0.005, 0.005, 0.005, 0.005); //distance scaler: default 0.005
const vec4 sca2 = vec4(0.02, 0.02, 0.02, 0.02); //size of reflection waves default 0.02

const vec4 tscale = vec4(0.25, 0.25, 0.25, 0.25); // intensenty of dudv map default 0.25

const vec4 two = vec4(2.0, 2.0, 2.0, 1.0); //default (2.0, 2.0, 2.0, 1.0)
const vec4 mone = vec4(-1.0, -1.0, -1.0, 1.0); //default (-1.0, -1.0, -1.0, 1.0)
const vec4 ofive = vec4(0.5,0.5,0.5,1.0); //default (0.5,0.5,0.5,1.0)

const float exponent = 64.0; //default 64


vec4 lightTS = normalize(waterTex0);
vec4 viewt = normalize(waterTex4);//+vec4(0.55,0.55,0.55,0.55); //mine: add vector for more light! //default waterTex4 (view)
vec4 disdis = texture2D(water_dudvmap, vec2(waterTex2 * tscale));
vec4 dist = texture2D(water_dudvmap, vec2(waterTex1 + disdis*sca2));
vec4 fdist = dist;
fdist = fdist * two + mone;
fdist = normalize(fdist);
fdist *= sca;

//load normalmap
vec4 nmap = texture2D(water_normalmap, vec2(waterTex1 + dist*sca2));


nmap = (nmap-ofive) * two;
nmap.xz *= 0.85;
nmap.y = 0.8 * abs(nmap.y) + 0.2; //is mine: makes nmap pint up more

vec4 vNorm = normalize(nmap);

//get projective texcoords
vec4 tmp = vec4(1.0 / waterTex3.w);
vec4 temp = tmp;

vec4 projCoord = waterTex3 * tmp;

//set projection of texture to the center of the waterplane
projCoord += vec4(1.0);
projCoord *= vec4(0.5); // default 0.5
tmp = projCoord + fdist;
tmp = clamp(tmp, 0.001, 0.999);


vec4 refTex = texture2D(water_reflection, vec2(tmp));
vec4 refl = refTex;
vec4 refr = texture2D(water_refraction, vec2(tmp));
vec4 wdepth = texture2D(water_depthmap, vec2(tmp));


wdepth = vec4(pow(wdepth.x, 16.0)); //the transparency of the water. default 4.0


vec4 invdepth = 1.0 - wdepth;

//calculate specular highlight
vec4 vRef = normalize(reflect(-viewt, vNorm)); //change - to + for opposite specular dir //default normalize(reflect(-lightTS, vNorm));
vec4 sunRef = normalize(reflect(-lightTS, vNorm));
vec4 sunRef2 = normalize(reflect(lightTS, vNorm));


//vRef*=1.6; //makes brighter and bigger specular

float stemp = max(0.0, dot(viewt, vRef) );

float sunTemp   = max(0.0, dot(viewt, sunRef) );
float sunTemp2  = max(0.0, dot(viewt, sunRef2) );

stemp = pow(stemp, exponent);
sunTemp = pow(sunTemp, exponent);
sunTemp2 = pow(sunTemp2, exponent);


vec4 specular = vec4(stemp);
//specular += vec4(sunTemp);
//specular += vec4(sunTemp2);

//calculate fresnel and inverted fresnel
//note: could be that fres and invfres are switched
vec4 invfres = vec4( dot(vNorm, viewt) );

//invfres = clamp(invfres, 0.001, 0.999);

vec4 fres = vec4(1.0) - invfres; //+ vec4(7.3,7.3,7.3,7.3);
fres += waterColor;
fres *= 0.05;
fres = clamp(fres, 0.031, 0.51);


//calculate reflection and refraction
refr *= invfres;
refr *= invdepth; 
temp = 0.65 *  wdepth * invfres ;


refl = (2.0*refl)*fres ; //mine: + invfres * waterColor
refr += refl + temp;
//add reflection and refraction
tmp = refr + refl;

gl_FragColor = tmp + specular ;

}