/*   1:    */package org.schema.game.common.data.physics.octree;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import javax.vecmath.Matrix3f;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import o;
/*   8:    */import org.schema.game.common.data.physics.BoxShapeExt;
/*   9:    */import org.schema.game.common.data.world.Segment;
/*  10:    */
/*  13:    */public class OctreeNode
/*  14:    */  extends OctreeLeaf
/*  15:    */{
/*  16:    */  private OctreeLeaf[] children;
/*  17:    */  
/*  18:    */  public OctreeNode(int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*  19:    */  {
/*  20: 20 */    super(paramInt1, paramByte, paramInt2, paramBoolean);
/*  21:    */  }
/*  22:    */  
/*  23:    */  public OctreeNode(o paramo1, o paramo2, int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*  24:    */  {
/*  25: 25 */    super(paramo1, paramo2, paramInt1, paramByte, paramInt2, paramBoolean);
/*  26:    */  }
/*  27:    */  
/*  29:    */  public void delete(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
/*  30:    */  {
/*  31: 31 */    super.delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt);
/*  32:    */    
/*  33: 33 */    if ((paramByte2 >= getStartX()) && (paramByte2 < getEndY() - getHalfDimY()))
/*  34:    */    {
/*  35: 35 */      if ((paramByte1 >= getStartX()) && (paramByte1 < getEndX() - getHalfDimX())) {
/*  36: 36 */        if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  37: 37 */          paramTreeCache.lvlToIndex[paramInt] = 0;
/*  38:    */          
/*  39: 39 */          this.children[0].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  40:    */        }
/*  41: 41 */        paramTreeCache.lvlToIndex[paramInt] = 3;
/*  42:    */        
/*  43: 43 */        this.children[3].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  44:    */      }
/*  45:    */      
/*  46: 46 */      if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  47: 47 */        paramTreeCache.lvlToIndex[paramInt] = 1;
/*  48:    */        
/*  49: 49 */        this.children[1].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  50:    */      }
/*  51: 51 */      paramTreeCache.lvlToIndex[paramInt] = 2;
/*  52:    */      
/*  53: 53 */      this.children[2].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  54:    */    }
/*  55:    */    
/*  58: 58 */    if ((paramByte1 >= getStartX()) && (paramByte1 < getEndX() - getHalfDimX())) {
/*  59: 59 */      if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  60: 60 */        paramTreeCache.lvlToIndex[paramInt] = 4;
/*  61:    */        
/*  62: 62 */        this.children[4].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  63:    */      }
/*  64: 64 */      paramTreeCache.lvlToIndex[paramInt] = 7;
/*  65:    */      
/*  66: 66 */      this.children[7].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  67:    */    }
/*  68:    */    
/*  69: 69 */    if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  70: 70 */      paramTreeCache.lvlToIndex[paramInt] = 5;
/*  71:    */      
/*  72: 72 */      this.children[5].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/*  73:    */    }
/*  74: 74 */    paramTreeCache.lvlToIndex[paramInt] = 6;
/*  75:    */    
/*  76: 76 */    this.children[6].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
/*  77:    */  }
/*  78:    */  
/*  81:    */  public void deleteCached(TreeCache paramTreeCache, int paramInt)
/*  82:    */  {
/*  83: 83 */    super.deleteCached(paramTreeCache, paramInt + 1);
/*  84: 84 */    this.children[paramTreeCache.lvlToIndex[paramInt]].deleteCached(paramTreeCache, paramInt + 1);
/*  85:    */  }
/*  86:    */  
/*  89:    */  public void drawOctree(Vector3f paramVector3f, boolean paramBoolean)
/*  90:    */  {
/*  91: 91 */    if (isHasHit()) {
/*  92: 92 */      super.drawOctree(paramVector3f, paramBoolean);
/*  93: 93 */      for (paramBoolean = false; paramBoolean < this.children.length; paramBoolean++) {
/*  94: 94 */        this.children[paramBoolean].drawOctree(paramVector3f, false);
/*  95:    */      }
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/* 104:    */  public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
/* 105:    */  {
/* 106:106 */    paramIntersectionCallback = doIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
/* 107:    */    
/* 109:109 */    if (isHasHit()) {
/* 110:110 */      for (int i = 0; i < this.children.length; i++) {
/* 111:111 */        if (!this.children[i].isEmpty()) {
/* 112:112 */          paramIntersectionCallback = this.children[i].findIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
/* 113:    */        } else {
/* 114:114 */          this.children[i].setHasHit(false);
/* 115:    */        }
/* 116:    */      }
/* 117:    */    }
/* 118:118 */    return paramIntersectionCallback;
/* 119:    */  }
/* 120:    */  
/* 125:    */  public IntersectionCallback findIntersectingCast(IntersectionCallback paramIntersectionCallback, Transform paramTransform1, BoxShapeExt paramBoxShapeExt, ConvexShape paramConvexShape, float paramFloat1, Segment paramSegment, Transform paramTransform2, Transform paramTransform3, float paramFloat2)
/* 126:    */  {
/* 127:127 */    paramIntersectionCallback = super.findIntersectingCast(paramIntersectionCallback, paramTransform1, paramBoxShapeExt, paramConvexShape, paramFloat1, paramSegment, paramTransform2, paramTransform3, paramFloat2);
/* 128:    */    
/* 129:129 */    if (isHasHit())
/* 130:    */    {
/* 131:131 */      for (int i = 0; i < this.children.length; i++) {
/* 132:132 */        paramIntersectionCallback = this.children[i].findIntersectingCast(paramIntersectionCallback, paramTransform1, paramBoxShapeExt, paramConvexShape, paramFloat1, paramSegment, paramTransform2, paramTransform3, paramFloat2);
/* 133:    */      }
/* 134:    */    }
/* 135:135 */    return paramIntersectionCallback;
/* 136:    */  }
/* 137:    */  
/* 140:    */  public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 141:    */  {
/* 142:142 */    paramIntersectionCallback = super.findIntersectingRay(paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/* 143:    */    
/* 144:144 */    if (isHasHit())
/* 145:    */    {
/* 146:146 */      for (int i = 0; i < this.children.length; i++) {
/* 147:147 */        if (!this.children[i].isEmpty()) {
/* 148:148 */          paramIntersectionCallback = this.children[i].findIntersectingRay(paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/* 149:    */        } else {
/* 150:150 */          this.children[i].setHasHit(false);
/* 151:    */        }
/* 152:    */      }
/* 153:    */    }
/* 154:154 */    return paramIntersectionCallback;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public void insert(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt) {
/* 158:158 */    super.insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
/* 159:    */    
/* 161:161 */    byte b1 = getStartX();
/* 162:162 */    byte b2 = getStartY();
/* 163:163 */    byte b3 = getStartZ();
/* 164:    */    
/* 165:165 */    int i = getEndX();
/* 166:166 */    int j = getEndY();
/* 167:167 */    int k = getEndZ();
/* 168:    */    
/* 170:170 */    if ((paramByte2 >= b2) && (paramByte2 < j - getHalfDimY()))
/* 171:    */    {
/* 173:173 */      if ((paramByte1 >= b1) && (paramByte1 < i - getHalfDimX())) {
/* 174:174 */        if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 175:175 */          paramTreeCache.lvlToIndex[paramInt] = 0;
/* 176:    */          
/* 177:177 */          this.children[0].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 178:    */        }
/* 179:179 */        paramTreeCache.lvlToIndex[paramInt] = 3;
/* 180:    */        
/* 181:181 */        this.children[3].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 182:    */      }
/* 183:    */      
/* 184:184 */      if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 185:185 */        paramTreeCache.lvlToIndex[paramInt] = 1;
/* 186:    */        
/* 187:187 */        this.children[1].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 188:    */      }
/* 189:189 */      paramTreeCache.lvlToIndex[paramInt] = 2;
/* 190:    */      
/* 191:191 */      this.children[2].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 192:    */    }
/* 193:    */    
/* 197:197 */    if ((paramByte1 >= b1) && (paramByte1 < i - getHalfDimX())) {
/* 198:198 */      if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 199:199 */        paramTreeCache.lvlToIndex[paramInt] = 4;
/* 200:    */        
/* 201:201 */        this.children[4].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 202:    */      }
/* 203:203 */      paramTreeCache.lvlToIndex[paramInt] = 7;
/* 204:    */      
/* 205:205 */      this.children[7].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 206:    */    }
/* 207:    */    
/* 208:208 */    if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 209:209 */      paramTreeCache.lvlToIndex[paramInt] = 5;
/* 210:    */      
/* 211:211 */      this.children[5].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);return;
/* 212:    */    }
/* 213:213 */    paramTreeCache.lvlToIndex[paramInt] = 6;
/* 214:    */    
/* 215:215 */    this.children[6].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
/* 216:    */  }
/* 217:    */  
/* 220:    */  public void insertCached(TreeCache paramTreeCache, int paramInt)
/* 221:    */  {
/* 222:222 */    super.insertCached(paramTreeCache, paramInt + 1);
/* 223:223 */    this.children[paramTreeCache.lvlToIndex[paramInt]].insertCached(paramTreeCache, paramInt + 1);
/* 224:    */  }
/* 225:    */  
/* 226:    */  public void reset() {
/* 227:227 */    super.reset();
/* 228:228 */    for (int i = 0; i < this.children.length; i++) {
/* 229:229 */      this.children[i].reset();
/* 230:    */    }
/* 231:    */  }
/* 232:    */  
/* 233:    */  protected boolean isLeaf()
/* 234:    */  {
/* 235:235 */    return false;
/* 236:    */  }
/* 237:    */  
/* 240:    */  public int split(int paramInt1, int paramInt2)
/* 241:    */  {
/* 242:242 */    int i = 1;
/* 243:243 */    this.children = new OctreeLeaf[8];
/* 244:    */    int j;
/* 245:245 */    if (getSet().first) {
/* 246:246 */      o localo1 = getStart(new o());
/* 247:    */      o localo2;
/* 248:248 */      (localo2 = getHalfDim(new o())).a(localo1);
/* 249:    */      
/* 250:250 */      o localo3 = new o(localo1);
/* 251:251 */      o localo4 = new o(localo2);
/* 252:252 */      localo3.a(getHalfDimX(), (byte)0, (byte)0);
/* 253:253 */      localo4.a(getHalfDimX(), (byte)0, (byte)0);
/* 254:    */      
/* 255:255 */      o localo5 = new o(localo1);
/* 256:256 */      o localo6 = new o(localo2);
/* 257:257 */      localo5.a(getHalfDimX(), (byte)0, getHalfDimZ());
/* 258:258 */      localo6.a(getHalfDimX(), (byte)0, getHalfDimZ());
/* 259:    */      
/* 260:260 */      o localo7 = new o(localo1);
/* 261:261 */      o localo8 = new o(localo2);
/* 262:262 */      localo7.a((byte)0, (byte)0, getHalfDimZ());
/* 263:263 */      localo8.a((byte)0, (byte)0, getHalfDimZ());
/* 264:    */      
/* 265:265 */      o localo9 = new o(localo1);
/* 266:266 */      o localo10 = new o(localo2);
/* 267:267 */      localo9.a((byte)0, getHalfDimY(), (byte)0);
/* 268:268 */      localo10.a((byte)0, getHalfDimY(), (byte)0);
/* 269:    */      
/* 270:270 */      o localo11 = new o(localo1);
/* 271:271 */      o localo12 = new o(localo2);
/* 272:272 */      localo11.a(getHalfDimX(), getHalfDimY(), (byte)0);
/* 273:273 */      localo12.a(getHalfDimX(), getHalfDimY(), (byte)0);
/* 274:    */      
/* 275:275 */      o localo13 = new o(localo1);
/* 276:276 */      o localo14 = new o(localo2);
/* 277:277 */      localo13.a(getHalfDimX(), getHalfDimY(), getHalfDimZ());
/* 278:278 */      localo14.a(getHalfDimX(), getHalfDimY(), getHalfDimZ());
/* 279:    */      
/* 280:280 */      o localo15 = new o(localo1);
/* 281:281 */      o localo16 = new o(localo2);
/* 282:282 */      localo15.a((byte)0, getHalfDimY(), getHalfDimZ());
/* 283:283 */      localo16.a((byte)0, getHalfDimY(), getHalfDimZ());
/* 284:    */      
/* 287:287 */      if (paramInt2 < getMaxLevel()) {
/* 288:288 */        this.children[0] = new OctreeNode(localo1, localo2, paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 289:289 */        this.children[1] = new OctreeNode(localo3, localo4, (paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 290:290 */        this.children[2] = new OctreeNode(localo5, localo6, (paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 291:291 */        this.children[3] = new OctreeNode(localo7, localo8, (paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 292:292 */        this.children[4] = new OctreeNode(localo9, localo10, (paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 293:293 */        this.children[5] = new OctreeNode(localo11, localo12, (paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 294:294 */        this.children[6] = new OctreeNode(localo13, localo14, (paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 295:295 */        this.children[7] = new OctreeNode(localo15, localo16, (paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 296:296 */        for (j = 0; j < this.children.length; j++) {
/* 297:297 */          i += ((OctreeNode)this.children[j]).split((paramInt1 << 3) + j, paramInt2 + 1);
/* 298:    */        }
/* 299:    */      } else {
/* 300:300 */        this.children[0] = new OctreeLeaf(j, localo2, paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 301:301 */        this.children[1] = new OctreeLeaf(localo3, localo4, (paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 302:302 */        this.children[2] = new OctreeLeaf(localo5, localo6, (paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 303:303 */        this.children[3] = new OctreeLeaf(localo7, localo8, (paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 304:304 */        this.children[4] = new OctreeLeaf(localo9, localo10, (paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 305:305 */        this.children[5] = new OctreeLeaf(localo11, localo12, (paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 306:306 */        this.children[6] = new OctreeLeaf(localo13, localo14, (paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 307:307 */        this.children[7] = new OctreeLeaf(localo15, localo16, (paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 308:308 */        i += 8;
/* 309:    */      }
/* 310:    */    }
/* 311:311 */    else if (paramInt2 < getMaxLevel()) {
/* 312:312 */      this.children[0] = new OctreeNode(paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 313:313 */      this.children[1] = new OctreeNode((paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 314:314 */      this.children[2] = new OctreeNode((paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 315:315 */      this.children[3] = new OctreeNode((paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 316:316 */      this.children[4] = new OctreeNode((paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 317:317 */      this.children[5] = new OctreeNode((paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 318:318 */      this.children[6] = new OctreeNode((paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 319:319 */      this.children[7] = new OctreeNode((paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 320:320 */      for (j = 0; j < this.children.length; j++) {
/* 321:321 */        i += ((OctreeNode)this.children[j]).split((paramInt1 << 3) + j, paramInt2 + 1);
/* 322:    */      }
/* 323:    */    } else {
/* 324:324 */      this.children[0] = new OctreeLeaf(paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 325:325 */      this.children[1] = new OctreeLeaf((paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 326:326 */      this.children[2] = new OctreeLeaf((paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 327:327 */      this.children[3] = new OctreeLeaf((paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 328:328 */      this.children[4] = new OctreeLeaf((paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 329:329 */      this.children[5] = new OctreeLeaf((paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 330:330 */      this.children[6] = new OctreeLeaf((paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 331:331 */      this.children[7] = new OctreeLeaf((paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 332:332 */      i += 8;
/* 333:    */    }
/* 334:    */    
/* 335:335 */    return i;
/* 336:    */  }
/* 337:    */  
/* 341:    */  public OctreeLeaf[] getChildren()
/* 342:    */  {
/* 343:343 */    return this.children;
/* 344:    */  }
/* 345:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */