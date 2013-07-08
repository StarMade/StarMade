/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/* 100:    */class Geom
/* 101:    */{
/* 102:    */  static double EdgeEval(GLUvertex u, GLUvertex v, GLUvertex w)
/* 103:    */  {
/* 104:104 */    assert ((VertLeq(u, v)) && (VertLeq(v, w)));
/* 105:    */    
/* 106:106 */    double gapL = v.s - u.s;
/* 107:107 */    double gapR = w.s - v.s;
/* 108:    */    
/* 109:109 */    if (gapL + gapR > 0.0D) {
/* 110:110 */      if (gapL < gapR) {
/* 111:111 */        return v.t - u.t + (u.t - w.t) * (gapL / (gapL + gapR));
/* 112:    */      }
/* 113:113 */      return v.t - w.t + (w.t - u.t) * (gapR / (gapL + gapR));
/* 114:    */    }
/* 115:    */    
/* 117:117 */    return 0.0D;
/* 118:    */  }
/* 119:    */  
/* 121:    */  static double EdgeSign(GLUvertex u, GLUvertex v, GLUvertex w)
/* 122:    */  {
/* 123:123 */    assert ((VertLeq(u, v)) && (VertLeq(v, w)));
/* 124:    */    
/* 125:125 */    double gapL = v.s - u.s;
/* 126:126 */    double gapR = w.s - v.s;
/* 127:    */    
/* 128:128 */    if (gapL + gapR > 0.0D) {
/* 129:129 */      return (v.t - w.t) * gapL + (v.t - u.t) * gapR;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    return 0.0D;
/* 133:    */  }
/* 134:    */  
/* 151:    */  static double TransEval(GLUvertex u, GLUvertex v, GLUvertex w)
/* 152:    */  {
/* 153:153 */    assert ((TransLeq(u, v)) && (TransLeq(v, w)));
/* 154:    */    
/* 155:155 */    double gapL = v.t - u.t;
/* 156:156 */    double gapR = w.t - v.t;
/* 157:    */    
/* 158:158 */    if (gapL + gapR > 0.0D) {
/* 159:159 */      if (gapL < gapR) {
/* 160:160 */        return v.s - u.s + (u.s - w.s) * (gapL / (gapL + gapR));
/* 161:    */      }
/* 162:162 */      return v.s - w.s + (w.s - u.s) * (gapR / (gapL + gapR));
/* 163:    */    }
/* 164:    */    
/* 166:166 */    return 0.0D;
/* 167:    */  }
/* 168:    */  
/* 174:    */  static double TransSign(GLUvertex u, GLUvertex v, GLUvertex w)
/* 175:    */  {
/* 176:176 */    assert ((TransLeq(u, v)) && (TransLeq(v, w)));
/* 177:    */    
/* 178:178 */    double gapL = v.t - u.t;
/* 179:179 */    double gapR = w.t - v.t;
/* 180:    */    
/* 181:181 */    if (gapL + gapR > 0.0D) {
/* 182:182 */      return (v.s - w.s) * gapL + (v.s - u.s) * gapR;
/* 183:    */    }
/* 184:    */    
/* 185:185 */    return 0.0D;
/* 186:    */  }
/* 187:    */  
/* 194:    */  static boolean VertCCW(GLUvertex u, GLUvertex v, GLUvertex w)
/* 195:    */  {
/* 196:196 */    return u.s * (v.t - w.t) + v.s * (w.t - u.t) + w.s * (u.t - v.t) >= 0.0D;
/* 197:    */  }
/* 198:    */  
/* 206:    */  static double Interpolate(double a, double x, double b, double y)
/* 207:    */  {
/* 208:208 */    a = a < 0.0D ? 0.0D : a;
/* 209:209 */    b = b < 0.0D ? 0.0D : b;
/* 210:210 */    if (a <= b) {
/* 211:211 */      if (b == 0.0D) {
/* 212:212 */        return (x + y) / 2.0D;
/* 213:    */      }
/* 214:214 */      return x + (y - x) * (a / (a + b));
/* 215:    */    }
/* 216:    */    
/* 217:217 */    return y + (x - y) * (b / (a + b));
/* 218:    */  }
/* 219:    */  
/* 236:    */  static void EdgeIntersect(GLUvertex o1, GLUvertex d1, GLUvertex o2, GLUvertex d2, GLUvertex v)
/* 237:    */  {
/* 238:238 */    if (!VertLeq(o1, d1)) {
/* 239:239 */      GLUvertex temp = o1;
/* 240:240 */      o1 = d1;
/* 241:241 */      d1 = temp;
/* 242:    */    }
/* 243:243 */    if (!VertLeq(o2, d2)) {
/* 244:244 */      GLUvertex temp = o2;
/* 245:245 */      o2 = d2;
/* 246:246 */      d2 = temp;
/* 247:    */    }
/* 248:248 */    if (!VertLeq(o1, o2)) {
/* 249:249 */      GLUvertex temp = o1;
/* 250:250 */      o1 = o2;
/* 251:251 */      o2 = temp;
/* 252:252 */      temp = d1;
/* 253:253 */      d1 = d2;
/* 254:254 */      d2 = temp;
/* 255:    */    }
/* 256:    */    
/* 257:257 */    if (!VertLeq(o2, d1))
/* 258:    */    {
/* 259:259 */      v.s = ((o2.s + d1.s) / 2.0D);
/* 260:260 */    } else if (VertLeq(d1, d2))
/* 261:    */    {
/* 262:262 */      double z1 = EdgeEval(o1, o2, d1);
/* 263:263 */      double z2 = EdgeEval(o2, d1, d2);
/* 264:264 */      if (z1 + z2 < 0.0D) {
/* 265:265 */        z1 = -z1;
/* 266:266 */        z2 = -z2;
/* 267:    */      }
/* 268:268 */      v.s = Interpolate(z1, o2.s, z2, d1.s);
/* 269:    */    }
/* 270:    */    else {
/* 271:271 */      double z1 = EdgeSign(o1, o2, d1);
/* 272:272 */      double z2 = -EdgeSign(o1, d2, d1);
/* 273:273 */      if (z1 + z2 < 0.0D) {
/* 274:274 */        z1 = -z1;
/* 275:275 */        z2 = -z2;
/* 276:    */      }
/* 277:277 */      v.s = Interpolate(z1, o2.s, z2, d2.s);
/* 278:    */    }
/* 279:    */    
/* 282:282 */    if (!TransLeq(o1, d1)) {
/* 283:283 */      GLUvertex temp = o1;
/* 284:284 */      o1 = d1;
/* 285:285 */      d1 = temp;
/* 286:    */    }
/* 287:287 */    if (!TransLeq(o2, d2)) {
/* 288:288 */      GLUvertex temp = o2;
/* 289:289 */      o2 = d2;
/* 290:290 */      d2 = temp;
/* 291:    */    }
/* 292:292 */    if (!TransLeq(o1, o2)) {
/* 293:293 */      GLUvertex temp = o2;
/* 294:294 */      o2 = o1;
/* 295:295 */      o1 = temp;
/* 296:296 */      temp = d2;
/* 297:297 */      d2 = d1;
/* 298:298 */      d1 = temp;
/* 299:    */    }
/* 300:    */    
/* 301:301 */    if (!TransLeq(o2, d1))
/* 302:    */    {
/* 303:303 */      v.t = ((o2.t + d1.t) / 2.0D);
/* 304:304 */    } else if (TransLeq(d1, d2))
/* 305:    */    {
/* 306:306 */      double z1 = TransEval(o1, o2, d1);
/* 307:307 */      double z2 = TransEval(o2, d1, d2);
/* 308:308 */      if (z1 + z2 < 0.0D) {
/* 309:309 */        z1 = -z1;
/* 310:310 */        z2 = -z2;
/* 311:    */      }
/* 312:312 */      v.t = Interpolate(z1, o2.t, z2, d1.t);
/* 313:    */    }
/* 314:    */    else {
/* 315:315 */      double z1 = TransSign(o1, o2, d1);
/* 316:316 */      double z2 = -TransSign(o1, d2, d1);
/* 317:317 */      if (z1 + z2 < 0.0D) {
/* 318:318 */        z1 = -z1;
/* 319:319 */        z2 = -z2;
/* 320:    */      }
/* 321:321 */      v.t = Interpolate(z1, o2.t, z2, d2.t);
/* 322:    */    }
/* 323:    */  }
/* 324:    */  
/* 325:    */  static boolean VertEq(GLUvertex u, GLUvertex v) {
/* 326:326 */    return (u.s == v.s) && (u.t == v.t);
/* 327:    */  }
/* 328:    */  
/* 329:    */  static boolean VertLeq(GLUvertex u, GLUvertex v) {
/* 330:330 */    return (u.s < v.s) || ((u.s == v.s) && (u.t <= v.t));
/* 331:    */  }
/* 332:    */  
/* 334:    */  static boolean TransLeq(GLUvertex u, GLUvertex v)
/* 335:    */  {
/* 336:336 */    return (u.t < v.t) || ((u.t == v.t) && (u.s <= v.s));
/* 337:    */  }
/* 338:    */  
/* 339:    */  static boolean EdgeGoesLeft(GLUhalfEdge e) {
/* 340:340 */    return VertLeq(e.Sym.Org, e.Org);
/* 341:    */  }
/* 342:    */  
/* 343:    */  static boolean EdgeGoesRight(GLUhalfEdge e) {
/* 344:344 */    return VertLeq(e.Org, e.Sym.Org);
/* 345:    */  }
/* 346:    */  
/* 347:    */  static double VertL1dist(GLUvertex u, GLUvertex v) {
/* 348:348 */    return Math.abs(u.s - v.s) + Math.abs(u.t - v.t);
/* 349:    */  }
/* 350:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Geom
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */