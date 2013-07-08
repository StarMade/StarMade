/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  21:    */class Normal
/*  22:    */{
/*  23:    */  static boolean SLANTED_SWEEP;
/*  24:    */  
/*  42:    */  static double S_UNIT_X;
/*  43:    */  
/*  60:    */  static double S_UNIT_Y;
/*  61:    */  
/*  78:    */  private static final boolean TRUE_PROJECT = false;
/*  79:    */  
/*  97:    */  static
/*  98:    */  {
/*  99: 99 */    if (SLANTED_SWEEP)
/* 100:    */    {
/* 109:109 */      S_UNIT_X = 0.5094153956495539D;
/* 110:110 */      S_UNIT_Y = 0.8605207462201063D;
/* 111:    */    } else {
/* 112:112 */      S_UNIT_X = 1.0D;
/* 113:113 */      S_UNIT_Y = 0.0D;
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  private static double Dot(double[] u, double[] v) {
/* 118:118 */    return u[0] * v[0] + u[1] * v[1] + u[2] * v[2];
/* 119:    */  }
/* 120:    */  
/* 121:    */  static void Normalize(double[] v) {
/* 122:122 */    double len = v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
/* 123:    */    
/* 124:124 */    assert (len > 0.0D);
/* 125:125 */    len = Math.sqrt(len);
/* 126:126 */    v[0] /= len;
/* 127:127 */    v[1] /= len;
/* 128:128 */    v[2] /= len;
/* 129:    */  }
/* 130:    */  
/* 131:    */  static int LongAxis(double[] v) {
/* 132:132 */    int i = 0;
/* 133:    */    
/* 134:134 */    if (Math.abs(v[1]) > Math.abs(v[0])) {
/* 135:135 */      i = 1;
/* 136:    */    }
/* 137:137 */    if (Math.abs(v[2]) > Math.abs(v[i])) {
/* 138:138 */      i = 2;
/* 139:    */    }
/* 140:140 */    return i;
/* 141:    */  }
/* 142:    */  
/* 146:    */  static void ComputeNormal(GLUtessellatorImpl tess, double[] norm)
/* 147:    */  {
/* 148:148 */    GLUvertex vHead = tess.mesh.vHead;
/* 149:    */    
/* 151:151 */    double[] maxVal = new double[3];
/* 152:152 */    double[] minVal = new double[3];
/* 153:153 */    GLUvertex[] minVert = new GLUvertex[3];
/* 154:154 */    GLUvertex[] maxVert = new GLUvertex[3];
/* 155:155 */    double[] d1 = new double[3];
/* 156:156 */    double[] d2 = new double[3];
/* 157:157 */    double[] tNorm = new double[3]; double 
/* 158:    */    
/* 159:159 */      tmp60_59 = (maxVal[2] = -2.0E+150D);maxVal[1] = tmp60_59;maxVal[0] = tmp60_59; double 
/* 160:160 */      tmp77_76 = (minVal[2] = 2.0E+150D);minVal[1] = tmp77_76;minVal[0] = tmp77_76;
/* 161:    */    
/* 162:162 */    for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 163:163 */      for (int i = 0; i < 3; i++) {
/* 164:164 */        double c = v.coords[i];
/* 165:165 */        if (c < minVal[i]) {
/* 166:166 */          minVal[i] = c;
/* 167:167 */          minVert[i] = v;
/* 168:    */        }
/* 169:169 */        if (c > maxVal[i]) {
/* 170:170 */          maxVal[i] = c;
/* 171:171 */          maxVert[i] = v;
/* 172:    */        }
/* 173:    */      }
/* 174:    */    }
/* 175:    */    
/* 179:179 */    int i = 0;
/* 180:180 */    if (maxVal[1] - minVal[1] > maxVal[0] - minVal[0]) {
/* 181:181 */      i = 1;
/* 182:    */    }
/* 183:183 */    if (maxVal[2] - minVal[2] > maxVal[i] - minVal[i]) {
/* 184:184 */      i = 2;
/* 185:    */    }
/* 186:186 */    if (minVal[i] >= maxVal[i])
/* 187:    */    {
/* 188:188 */      norm[0] = 0.0D;
/* 189:189 */      norm[1] = 0.0D;
/* 190:190 */      norm[2] = 1.0D;
/* 191:191 */      return;
/* 192:    */    }
/* 193:    */    
/* 197:197 */    double maxLen2 = 0.0D;
/* 198:198 */    GLUvertex v1 = minVert[i];
/* 199:199 */    GLUvertex v2 = maxVert[i];
/* 200:200 */    d1[0] = (v1.coords[0] - v2.coords[0]);
/* 201:201 */    d1[1] = (v1.coords[1] - v2.coords[1]);
/* 202:202 */    d1[2] = (v1.coords[2] - v2.coords[2]);
/* 203:203 */    for (v = vHead.next; v != vHead; v = v.next) {
/* 204:204 */      d2[0] = (v.coords[0] - v2.coords[0]);
/* 205:205 */      d2[1] = (v.coords[1] - v2.coords[1]);
/* 206:206 */      d2[2] = (v.coords[2] - v2.coords[2]);
/* 207:207 */      tNorm[0] = (d1[1] * d2[2] - d1[2] * d2[1]);
/* 208:208 */      tNorm[1] = (d1[2] * d2[0] - d1[0] * d2[2]);
/* 209:209 */      tNorm[2] = (d1[0] * d2[1] - d1[1] * d2[0]);
/* 210:210 */      double tLen2 = tNorm[0] * tNorm[0] + tNorm[1] * tNorm[1] + tNorm[2] * tNorm[2];
/* 211:211 */      if (tLen2 > maxLen2) {
/* 212:212 */        maxLen2 = tLen2;
/* 213:213 */        norm[0] = tNorm[0];
/* 214:214 */        norm[1] = tNorm[1];
/* 215:215 */        norm[2] = tNorm[2];
/* 216:    */      }
/* 217:    */    }
/* 218:    */    
/* 219:219 */    if (maxLen2 <= 0.0D)
/* 220:    */    {
/* 221:221 */      double tmp547_546 = (norm[2] = 0.0D);norm[1] = tmp547_546;norm[0] = tmp547_546;
/* 222:222 */      norm[LongAxis(d1)] = 1.0D;
/* 223:    */    }
/* 224:    */  }
/* 225:    */  
/* 226:    */  static void CheckOrientation(GLUtessellatorImpl tess)
/* 227:    */  {
/* 228:228 */    GLUface fHead = tess.mesh.fHead;
/* 229:229 */    GLUvertex vHead = tess.mesh.vHead;
/* 230:    */    
/* 235:235 */    double area = 0.0D;
/* 236:236 */    for (GLUface f = fHead.next; f != fHead; f = f.next) {
/* 237:237 */      GLUhalfEdge e = f.anEdge;
/* 238:238 */      if (e.winding > 0)
/* 239:    */        do {
/* 240:240 */          area += (e.Org.s - e.Sym.Org.s) * (e.Org.t + e.Sym.Org.t);
/* 241:241 */          e = e.Lnext;
/* 242:242 */        } while (e != f.anEdge);
/* 243:    */    }
/* 244:244 */    if (area < 0.0D)
/* 245:    */    {
/* 246:246 */      for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 247:247 */        v.t = (-v.t);
/* 248:    */      }
/* 249:249 */      tess.tUnit[0] = (-tess.tUnit[0]);
/* 250:250 */      tess.tUnit[1] = (-tess.tUnit[1]);
/* 251:251 */      tess.tUnit[2] = (-tess.tUnit[2]);
/* 252:    */    }
/* 253:    */  }
/* 254:    */  
/* 257:    */  public static void __gl_projectPolygon(GLUtessellatorImpl tess)
/* 258:    */  {
/* 259:259 */    GLUvertex vHead = tess.mesh.vHead;
/* 260:    */    
/* 261:261 */    double[] norm = new double[3];
/* 262:    */    
/* 264:264 */    boolean computedNormal = false;
/* 265:    */    
/* 266:266 */    norm[0] = tess.normal[0];
/* 267:267 */    norm[1] = tess.normal[1];
/* 268:268 */    norm[2] = tess.normal[2];
/* 269:269 */    if ((norm[0] == 0.0D) && (norm[1] == 0.0D) && (norm[2] == 0.0D)) {
/* 270:270 */      ComputeNormal(tess, norm);
/* 271:271 */      computedNormal = true;
/* 272:    */    }
/* 273:273 */    double[] sUnit = tess.sUnit;
/* 274:274 */    double[] tUnit = tess.tUnit;
/* 275:275 */    int i = LongAxis(norm);
/* 276:    */    
/* 301:301 */    sUnit[i] = 0.0D;
/* 302:302 */    sUnit[((i + 1) % 3)] = S_UNIT_X;
/* 303:303 */    sUnit[((i + 2) % 3)] = S_UNIT_Y;
/* 304:    */    
/* 305:305 */    tUnit[i] = 0.0D;
/* 306:306 */    tUnit[((i + 1) % 3)] = (norm[i] > 0.0D ? -S_UNIT_Y : S_UNIT_Y);
/* 307:307 */    tUnit[((i + 2) % 3)] = (norm[i] > 0.0D ? S_UNIT_X : -S_UNIT_X);
/* 308:    */    
/* 311:311 */    for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 312:312 */      v.s = Dot(v.coords, sUnit);
/* 313:313 */      v.t = Dot(v.coords, tUnit);
/* 314:    */    }
/* 315:315 */    if (computedNormal) {
/* 316:316 */      CheckOrientation(tess);
/* 317:    */    }
/* 318:    */  }
/* 319:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Normal
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */