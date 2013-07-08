/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.lwjgl.opengl.GL11;
/*   5:    */
/*  81:    */public class PartialDisk
/*  82:    */  extends Quadric
/*  83:    */{
/*  84:    */  private static final int CACHE_SIZE = 240;
/*  85:    */  
/*  86:    */  public void draw(float innerRadius, float outerRadius, int slices, int loops, float startAngle, float sweepAngle)
/*  87:    */  {
/*  88: 88 */    float[] sinCache = new float['ð'];
/*  89: 89 */    float[] cosCache = new float['ð'];
/*  90:    */    
/*  94: 94 */    float texLow = 0.0F;float texHigh = 0.0F;
/*  95:    */    
/*  99: 99 */    if (slices >= 240)
/* 100:100 */      slices = 239;
/* 101:101 */    if ((slices < 2) || (loops < 1) || (outerRadius <= 0.0F) || (innerRadius < 0.0F) || (innerRadius > outerRadius))
/* 102:    */    {
/* 107:107 */      System.err.println("PartialDisk: GLU_INVALID_VALUE");
/* 108:108 */      return;
/* 109:    */    }
/* 110:    */    
/* 111:111 */    if (sweepAngle < -360.0F)
/* 112:112 */      sweepAngle = 360.0F;
/* 113:113 */    if (sweepAngle > 360.0F)
/* 114:114 */      sweepAngle = 360.0F;
/* 115:115 */    if (sweepAngle < 0.0F) {
/* 116:116 */      startAngle += sweepAngle;
/* 117:117 */      sweepAngle = -sweepAngle; }
/* 118:    */    int slices2;
/* 119:    */    int slices2;
/* 120:120 */    if (sweepAngle == 360.0F) {
/* 121:121 */      slices2 = slices;
/* 122:    */    } else {
/* 123:123 */      slices2 = slices + 1;
/* 124:    */    }
/* 125:    */    
/* 127:127 */    float deltaRadius = outerRadius - innerRadius;
/* 128:    */    
/* 131:131 */    float angleOffset = startAngle / 180.0F * 3.141593F;
/* 132:132 */    for (int i = 0; i <= slices; i++) {
/* 133:133 */      float angle = angleOffset + 3.141593F * sweepAngle / 180.0F * i / slices;
/* 134:134 */      sinCache[i] = sin(angle);
/* 135:135 */      cosCache[i] = cos(angle);
/* 136:    */    }
/* 137:    */    
/* 138:138 */    if (sweepAngle == 360.0F) {
/* 139:139 */      sinCache[slices] = sinCache[0];
/* 140:140 */      cosCache[slices] = cosCache[0];
/* 141:    */    }
/* 142:    */    
/* 143:143 */    switch (this.normals) {
/* 144:    */    case 100000: 
/* 145:    */    case 100001: 
/* 146:146 */      if (this.orientation == 100020) {
/* 147:147 */        GL11.glNormal3f(0.0F, 0.0F, 1.0F);
/* 148:    */      } else {
/* 149:149 */        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 150:    */      }
/* 151:151 */      break;
/* 152:    */    }
/* 153:    */    
/* 154:    */    
/* 155:    */    int j;
/* 156:    */    
/* 157:157 */    switch (this.drawStyle) {
/* 158:    */    case 100012:  int finish;
/* 159:159 */      if (innerRadius == 0.0F) {
/* 160:160 */        int finish = loops - 1;
/* 161:    */        
/* 162:162 */        GL11.glBegin(6);
/* 163:163 */        if (this.textureFlag) {
/* 164:164 */          GL11.glTexCoord2f(0.5F, 0.5F);
/* 165:    */        }
/* 166:166 */        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/* 167:167 */        float radiusLow = outerRadius - deltaRadius * ((loops - 1) / loops);
/* 168:168 */        if (this.textureFlag) {
/* 169:169 */          texLow = radiusLow / outerRadius / 2.0F;
/* 170:    */        }
/* 171:    */        
/* 172:172 */        if (this.orientation == 100020) {
/* 173:173 */          for (i = slices; i >= 0; i--) {
/* 174:174 */            if (this.textureFlag) {
/* 175:175 */              GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 176:    */            }
/* 177:    */            
/* 179:179 */            GL11.glVertex3f(radiusLow * sinCache[i], radiusLow * cosCache[i], 0.0F);
/* 180:    */          }
/* 181:    */        }
/* 182:182 */        for (i = 0; i <= slices; i++) {
/* 183:183 */          if (this.textureFlag) {
/* 184:184 */            GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 185:    */          }
/* 186:    */          
/* 188:188 */          GL11.glVertex3f(radiusLow * sinCache[i], radiusLow * cosCache[i], 0.0F);
/* 189:    */        }
/* 190:    */        
/* 191:191 */        GL11.glEnd();
/* 192:    */      } else {
/* 193:193 */        finish = loops;
/* 194:    */      }
/* 195:195 */      for (j = 0; j < finish;) {
/* 196:196 */        float radiusLow = outerRadius - deltaRadius * (j / loops);
/* 197:197 */        float radiusHigh = outerRadius - deltaRadius * ((j + 1) / loops);
/* 198:198 */        if (this.textureFlag) {
/* 199:199 */          texLow = radiusLow / outerRadius / 2.0F;
/* 200:200 */          texHigh = radiusHigh / outerRadius / 2.0F;
/* 201:    */        }
/* 202:    */        
/* 203:203 */        GL11.glBegin(8);
/* 204:204 */        for (i = 0; i <= slices; i++) {
/* 205:205 */          if (this.orientation == 100020) {
/* 206:206 */            if (this.textureFlag) {
/* 207:207 */              GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 208:    */            }
/* 209:    */            
/* 211:211 */            GL11.glVertex3f(radiusLow * sinCache[i], radiusLow * cosCache[i], 0.0F);
/* 212:    */            
/* 213:213 */            if (this.textureFlag) {
/* 214:214 */              GL11.glTexCoord2f(texHigh * sinCache[i] + 0.5F, texHigh * cosCache[i] + 0.5F);
/* 215:    */            }
/* 216:    */            
/* 218:218 */            GL11.glVertex3f(radiusHigh * sinCache[i], radiusHigh * cosCache[i], 0.0F);
/* 220:    */          }
/* 221:    */          else
/* 222:    */          {
/* 223:223 */            if (this.textureFlag) {
/* 224:224 */              GL11.glTexCoord2f(texHigh * sinCache[i] + 0.5F, texHigh * cosCache[i] + 0.5F);
/* 225:    */            }
/* 226:    */            
/* 228:228 */            GL11.glVertex3f(radiusHigh * sinCache[i], radiusHigh * cosCache[i], 0.0F);
/* 229:    */            
/* 233:233 */            if (this.textureFlag) {
/* 234:234 */              GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 235:    */            }
/* 236:    */            
/* 238:238 */            GL11.glVertex3f(radiusLow * sinCache[i], radiusLow * cosCache[i], 0.0F);
/* 239:    */          }
/* 240:    */        }
/* 241:241 */        GL11.glEnd();j++;continue;
/* 242:    */        
/* 245:245 */        GL11.glBegin(0);
/* 246:246 */        for (i = 0; i < slices2; i++) {
/* 247:247 */          float sintemp = sinCache[i];
/* 248:248 */          float costemp = cosCache[i];
/* 249:249 */          for (j = 0; j <= loops; j++) {
/* 250:250 */            radiusLow = outerRadius - deltaRadius * (j / loops);
/* 251:    */            
/* 252:252 */            if (this.textureFlag) {
/* 253:253 */              texLow = radiusLow / outerRadius / 2.0F;
/* 254:    */              
/* 255:255 */              GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 256:    */            }
/* 257:    */            
/* 259:259 */            GL11.glVertex3f(radiusLow * sintemp, radiusLow * costemp, 0.0F);
/* 260:    */          }
/* 261:    */        }
/* 262:262 */        GL11.glEnd();
/* 263:263 */        break;
/* 264:    */        
/* 265:265 */        if (innerRadius == outerRadius) {
/* 266:266 */          GL11.glBegin(3);
/* 267:    */          
/* 268:268 */          for (i = 0; i <= slices; i++) {
/* 269:269 */            if (this.textureFlag) {
/* 270:270 */              GL11.glTexCoord2f(sinCache[i] / 2.0F + 0.5F, cosCache[i] / 2.0F + 0.5F);
/* 271:    */            }
/* 272:272 */            GL11.glVertex3f(innerRadius * sinCache[i], innerRadius * cosCache[i], 0.0F);
/* 273:    */          }
/* 274:274 */          GL11.glEnd();
/* 275:    */        }
/* 276:    */        else {
/* 277:277 */          for (j = 0; j <= loops; j++) {
/* 278:278 */            float radiusLow = outerRadius - deltaRadius * (j / loops);
/* 279:279 */            if (this.textureFlag) {
/* 280:280 */              texLow = radiusLow / outerRadius / 2.0F;
/* 281:    */            }
/* 282:    */            
/* 283:283 */            GL11.glBegin(3);
/* 284:284 */            for (i = 0; i <= slices; i++) {
/* 285:285 */              if (this.textureFlag) {
/* 286:286 */                GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 287:    */              }
/* 288:    */              
/* 290:290 */              GL11.glVertex3f(radiusLow * sinCache[i], radiusLow * cosCache[i], 0.0F);
/* 291:    */            }
/* 292:292 */            GL11.glEnd();
/* 293:    */          }
/* 294:294 */          for (i = 0; i < slices2;) {
/* 295:295 */            float sintemp = sinCache[i];
/* 296:296 */            float costemp = cosCache[i];
/* 297:297 */            GL11.glBegin(3);
/* 298:298 */            for (j = 0; j <= loops; j++) {
/* 299:299 */              float radiusLow = outerRadius - deltaRadius * (j / loops);
/* 300:300 */              if (this.textureFlag) {
/* 301:301 */                texLow = radiusLow / outerRadius / 2.0F;
/* 302:    */              }
/* 303:    */              
/* 304:304 */              if (this.textureFlag) {
/* 305:305 */                GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 306:    */              }
/* 307:    */              
/* 309:309 */              GL11.glVertex3f(radiusLow * sintemp, radiusLow * costemp, 0.0F);
/* 310:    */            }
/* 311:311 */            GL11.glEnd();i++;continue;
/* 312:    */            
/* 332:315 */            if (sweepAngle < 360.0F) {
/* 333:316 */              for (i = 0; i <= slices; i += slices) {
/* 334:317 */                sintemp = sinCache[i];
/* 335:318 */                costemp = cosCache[i];
/* 336:319 */                GL11.glBegin(3);
/* 337:320 */                for (j = 0; j <= loops; j++) {
/* 338:321 */                  float radiusLow = outerRadius - deltaRadius * (j / loops);
/* 339:    */                  
/* 340:323 */                  if (this.textureFlag) {
/* 341:324 */                    texLow = radiusLow / outerRadius / 2.0F;
/* 342:325 */                    GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 343:    */                  }
/* 344:    */                  
/* 346:329 */                  GL11.glVertex3f(radiusLow * sintemp, radiusLow * costemp, 0.0F);
/* 347:    */                }
/* 348:331 */                GL11.glEnd();
/* 349:    */              }
/* 350:    */            }
/* 351:334 */            for (int j = 0; j <= loops; j += loops) {
/* 352:335 */              float radiusLow = outerRadius - deltaRadius * (j / loops);
/* 353:336 */              if (this.textureFlag) {
/* 354:337 */                texLow = radiusLow / outerRadius / 2.0F;
/* 355:    */              }
/* 356:    */              
/* 357:340 */              GL11.glBegin(3);
/* 358:341 */              for (i = 0; i <= slices; i++) {
/* 359:342 */                if (this.textureFlag) {
/* 360:343 */                  GL11.glTexCoord2f(texLow * sinCache[i] + 0.5F, texLow * cosCache[i] + 0.5F);
/* 361:    */                }
/* 362:    */                
/* 364:347 */                GL11.glVertex3f(radiusLow * sinCache[i], radiusLow * cosCache[i], 0.0F);
/* 365:    */              }
/* 366:349 */              GL11.glEnd();
/* 367:350 */              if (innerRadius == outerRadius) {
/* 368:    */                break;
/* 369:    */              }
/* 370:    */            }
/* 371:    */          }
/* 372:    */        }
/* 373:    */      }
/* 374:    */    }
/* 375:    */    int j;
/* 376:    */  }
/* 377:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.PartialDisk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */