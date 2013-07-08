/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import javax.vecmath.Matrix3f;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */import javax.vecmath.Vector4f;
/*   9:    */
/*  41:    */class BoxCollision
/*  42:    */{
/*  43:    */  public static final float BOX_PLANE_EPSILON = 1.0E-006F;
/*  44:    */  
/*  45:    */  public static boolean BT_GREATER(float x, float y)
/*  46:    */  {
/*  47: 47 */    return Math.abs(x) > y;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public static float BT_MAX3(float a, float b, float c) {
/*  51: 51 */    return Math.max(a, Math.max(b, c));
/*  52:    */  }
/*  53:    */  
/*  54:    */  public static float BT_MIN3(float a, float b, float c) {
/*  55: 55 */    return Math.min(a, Math.min(b, c));
/*  56:    */  }
/*  57:    */  
/*  58:    */  public static boolean TEST_CROSS_EDGE_BOX_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend, int i_dir_0, int i_dir_1, int i_comp_0, int i_comp_1) {
/*  59: 59 */    float dir0 = -VectorUtil.getCoord(edge, i_dir_0);
/*  60: 60 */    float dir1 = VectorUtil.getCoord(edge, i_dir_1);
/*  61: 61 */    float pmin = VectorUtil.getCoord(pointa, i_comp_0) * dir0 + VectorUtil.getCoord(pointa, i_comp_1) * dir1;
/*  62: 62 */    float pmax = VectorUtil.getCoord(pointb, i_comp_0) * dir0 + VectorUtil.getCoord(pointb, i_comp_1) * dir1;
/*  63: 63 */    if (pmin > pmax)
/*  64:    */    {
/*  65: 65 */      pmin += pmax;
/*  66: 66 */      pmax = pmin - pmax;
/*  67: 67 */      pmin -= pmax;
/*  68:    */    }
/*  69: 69 */    float abs_dir0 = VectorUtil.getCoord(absolute_edge, i_dir_0);
/*  70: 70 */    float abs_dir1 = VectorUtil.getCoord(absolute_edge, i_dir_1);
/*  71: 71 */    float rad = VectorUtil.getCoord(_extend, i_comp_0) * abs_dir0 + VectorUtil.getCoord(_extend, i_comp_1) * abs_dir1;
/*  72: 72 */    if ((pmin > rad) || (-rad > pmax)) {
/*  73: 73 */      return false;
/*  74:    */    }
/*  75: 75 */    return true;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static boolean TEST_CROSS_EDGE_BOX_X_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend) {
/*  79: 79 */    return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 2, 1, 1, 2);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public static boolean TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend) {
/*  83: 83 */    return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 0, 2, 2, 0);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static boolean TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend) {
/*  87: 87 */    return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 1, 0, 0, 1);
/*  88:    */  }
/*  89:    */  
/*  92:    */  public static float bt_mat3_dot_col(Matrix3f mat, Vector3f vec3, int colindex)
/*  93:    */  {
/*  94: 94 */    return vec3.x * mat.getElement(0, colindex) + vec3.y * mat.getElement(1, colindex) + vec3.z * mat.getElement(2, colindex);
/*  95:    */  }
/*  96:    */  
/*  99:    */  public static boolean compareTransformsEqual(Transform t1, Transform t2)
/* 100:    */  {
/* 101:101 */    return t1.equals(t2);
/* 102:    */  }
/* 103:    */  
/* 105:    */  public static class BoxBoxTransformCache
/* 106:    */  {
/* 107:107 */    public final Vector3f T1to0 = new Vector3f();
/* 108:108 */    public final Matrix3f R1to0 = new Matrix3f();
/* 109:109 */    public final Matrix3f AR = new Matrix3f();
/* 110:    */    
/* 111:    */    public void set(BoxBoxTransformCache cache) {
/* 112:112 */      throw new UnsupportedOperationException();
/* 113:    */    }
/* 114:    */    
/* 119:    */    public void calc_absolute_matrix()
/* 120:    */    {
/* 121:121 */      for (int i = 0; i < 3; i++) {
/* 122:122 */        for (int j = 0; j < 3; j++) {
/* 123:123 */          this.AR.setElement(i, j, 1.0E-006F + Math.abs(this.R1to0.getElement(i, j)));
/* 124:    */        }
/* 125:    */      }
/* 126:    */    }
/* 127:    */    
/* 130:    */    public void calc_from_homogenic(Transform arg1, Transform arg2)
/* 131:    */    {
/* 132:132 */      .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform temp_trans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 133:133 */        temp_trans.inverse(trans0);
/* 134:134 */        temp_trans.mul(trans1);
/* 135:    */        
/* 136:136 */        this.T1to0.set(temp_trans.origin);
/* 137:137 */        this.R1to0.set(temp_trans.basis);
/* 138:    */        
/* 139:139 */        calc_absolute_matrix();
/* 140:140 */      } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 141:    */      }
/* 142:    */    }
/* 143:    */    
/* 144:    */    public void calc_from_full_invert(Transform arg1, Transform arg2)
/* 145:    */    {
/* 146:146 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.R1to0.invert(trans0.basis);
/* 147:147 */        this.T1to0.negate(trans0.origin);
/* 148:148 */        this.R1to0.transform(this.T1to0);
/* 149:    */        
/* 150:150 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 151:151 */        tmp.set(trans1.origin);
/* 152:152 */        this.R1to0.transform(tmp);
/* 153:153 */        this.T1to0.add(tmp);
/* 154:    */        
/* 155:155 */        this.R1to0.mul(trans1.basis);
/* 156:    */        
/* 157:157 */        calc_absolute_matrix();
/* 158:158 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/* 159:    */      } }
/* 160:    */    
/* 161:161 */    public Vector3f transform(Vector3f arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (point == out) {
/* 162:162 */          point = localStack.get$javax$vecmath$Vector3f(point);
/* 163:    */        }
/* 164:    */        
/* 165:165 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 166:166 */        this.R1to0.getRow(0, tmp);
/* 167:167 */        out.x = (tmp.dot(point) + this.T1to0.x);
/* 168:168 */        this.R1to0.getRow(1, tmp);
/* 169:169 */        out.y = (tmp.dot(point) + this.T1to0.y);
/* 170:170 */        this.R1to0.getRow(2, tmp);
/* 171:171 */        out.z = (tmp.dot(point) + this.T1to0.z);
/* 172:172 */        return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 173:    */      }
/* 174:    */    }
/* 175:    */  }
/* 176:    */  
/* 177:    */  public static class AABB
/* 178:    */  {
/* 179:179 */    public final Vector3f min = new Vector3f();
/* 180:180 */    public final Vector3f max = new Vector3f();
/* 181:    */    
/* 182:    */    public AABB() {}
/* 183:    */    
/* 184:    */    public AABB(Vector3f V1, Vector3f V2, Vector3f V3)
/* 185:    */    {
/* 186:186 */      calc_from_triangle(V1, V2, V3);
/* 187:    */    }
/* 188:    */    
/* 189:    */    public AABB(Vector3f V1, Vector3f V2, Vector3f V3, float margin) {
/* 190:190 */      calc_from_triangle_margin(V1, V2, V3, margin);
/* 191:    */    }
/* 192:    */    
/* 193:    */    public AABB(AABB other) {
/* 194:194 */      set(other);
/* 195:    */    }
/* 196:    */    
/* 197:    */    public AABB(AABB other, float margin) {
/* 198:198 */      this(other);
/* 199:199 */      this.min.x -= margin;
/* 200:200 */      this.min.y -= margin;
/* 201:201 */      this.min.z -= margin;
/* 202:202 */      this.max.x += margin;
/* 203:203 */      this.max.y += margin;
/* 204:204 */      this.max.z += margin;
/* 205:    */    }
/* 206:    */    
/* 207:    */    public void init(Vector3f V1, Vector3f V2, Vector3f V3, float margin) {
/* 208:208 */      calc_from_triangle_margin(V1, V2, V3, margin);
/* 209:    */    }
/* 210:    */    
/* 211:    */    public void set(AABB other) {
/* 212:212 */      this.min.set(other.min);
/* 213:213 */      this.max.set(other.max);
/* 214:    */    }
/* 215:    */    
/* 216:    */    public void invalidate() {
/* 217:217 */      this.min.set(3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F);
/* 218:218 */      this.max.set(-3.402824E+038F, -3.402824E+038F, -3.402824E+038F);
/* 219:    */    }
/* 220:    */    
/* 221:    */    public void increment_margin(float margin) {
/* 222:222 */      this.min.x -= margin;
/* 223:223 */      this.min.y -= margin;
/* 224:224 */      this.min.z -= margin;
/* 225:225 */      this.max.x += margin;
/* 226:226 */      this.max.y += margin;
/* 227:227 */      this.max.z += margin;
/* 228:    */    }
/* 229:    */    
/* 230:    */    public void copy_with_margin(AABB other, float margin) {
/* 231:231 */      other.min.x -= margin;
/* 232:232 */      other.min.y -= margin;
/* 233:233 */      other.min.z -= margin;
/* 234:    */      
/* 235:235 */      other.max.x += margin;
/* 236:236 */      other.max.y += margin;
/* 237:237 */      other.max.z += margin;
/* 238:    */    }
/* 239:    */    
/* 240:    */    public void calc_from_triangle(Vector3f V1, Vector3f V2, Vector3f V3) {
/* 241:241 */      this.min.x = BoxCollision.BT_MIN3(V1.x, V2.x, V3.x);
/* 242:242 */      this.min.y = BoxCollision.BT_MIN3(V1.y, V2.y, V3.y);
/* 243:243 */      this.min.z = BoxCollision.BT_MIN3(V1.z, V2.z, V3.z);
/* 244:    */      
/* 245:245 */      this.max.x = BoxCollision.BT_MAX3(V1.x, V2.x, V3.x);
/* 246:246 */      this.max.y = BoxCollision.BT_MAX3(V1.y, V2.y, V3.y);
/* 247:247 */      this.max.z = BoxCollision.BT_MAX3(V1.z, V2.z, V3.z);
/* 248:    */    }
/* 249:    */    
/* 250:    */    public void calc_from_triangle_margin(Vector3f V1, Vector3f V2, Vector3f V3, float margin) {
/* 251:251 */      calc_from_triangle(V1, V2, V3);
/* 252:252 */      this.min.x -= margin;
/* 253:253 */      this.min.y -= margin;
/* 254:254 */      this.min.z -= margin;
/* 255:255 */      this.max.x += margin;
/* 256:256 */      this.max.y += margin;
/* 257:257 */      this.max.z += margin;
/* 258:    */    }
/* 259:    */    
/* 262:    */    public void appy_transform(Transform arg1)
/* 263:    */    {
/* 264:264 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 265:    */        
/* 266:266 */        Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 267:267 */        center.add(this.max, this.min);
/* 268:268 */        center.scale(0.5F);
/* 269:    */        
/* 270:270 */        Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
/* 271:271 */        extends_.sub(this.max, center);
/* 272:    */        
/* 274:274 */        trans.transform(center);
/* 275:    */        
/* 276:276 */        Vector3f textends = localStack.get$javax$vecmath$Vector3f();
/* 277:    */        
/* 278:278 */        trans.basis.getRow(0, tmp);
/* 279:279 */        tmp.absolute();
/* 280:280 */        textends.x = extends_.dot(tmp);
/* 281:    */        
/* 282:282 */        trans.basis.getRow(1, tmp);
/* 283:283 */        tmp.absolute();
/* 284:284 */        textends.y = extends_.dot(tmp);
/* 285:    */        
/* 286:286 */        trans.basis.getRow(2, tmp);
/* 287:287 */        tmp.absolute();
/* 288:288 */        textends.z = extends_.dot(tmp);
/* 289:    */        
/* 290:290 */        this.min.sub(center, textends);
/* 291:291 */        this.max.add(center, textends);
/* 292:292 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/* 293:    */      }
/* 294:    */    }
/* 295:    */    
/* 296:    */    public void appy_transform_trans_cache(BoxCollision.BoxBoxTransformCache arg1)
/* 297:    */    {
/* 298:298 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 299:    */        
/* 300:300 */        Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 301:301 */        center.add(this.max, this.min);
/* 302:302 */        center.scale(0.5F);
/* 303:    */        
/* 304:304 */        Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
/* 305:305 */        extends_.sub(this.max, center);
/* 306:    */        
/* 308:308 */        trans.transform(center, center);
/* 309:    */        
/* 310:310 */        Vector3f textends = localStack.get$javax$vecmath$Vector3f();
/* 311:    */        
/* 312:312 */        trans.R1to0.getRow(0, tmp);
/* 313:313 */        tmp.absolute();
/* 314:314 */        textends.x = extends_.dot(tmp);
/* 315:    */        
/* 316:316 */        trans.R1to0.getRow(1, tmp);
/* 317:317 */        tmp.absolute();
/* 318:318 */        textends.y = extends_.dot(tmp);
/* 319:    */        
/* 320:320 */        trans.R1to0.getRow(2, tmp);
/* 321:321 */        tmp.absolute();
/* 322:322 */        textends.z = extends_.dot(tmp);
/* 323:    */        
/* 324:324 */        this.min.sub(center, textends);
/* 325:325 */        this.max.add(center, textends);
/* 326:326 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/* 327:    */      }
/* 328:    */    }
/* 329:    */    
/* 330:    */    public void merge(AABB box)
/* 331:    */    {
/* 332:332 */      this.min.x = Math.min(this.min.x, box.min.x);
/* 333:333 */      this.min.y = Math.min(this.min.y, box.min.y);
/* 334:334 */      this.min.z = Math.min(this.min.z, box.min.z);
/* 335:    */      
/* 336:336 */      this.max.x = Math.max(this.max.x, box.max.x);
/* 337:337 */      this.max.y = Math.max(this.max.y, box.max.y);
/* 338:338 */      this.max.z = Math.max(this.max.z, box.max.z);
/* 339:    */    }
/* 340:    */    
/* 343:    */    public void merge_point(Vector3f point)
/* 344:    */    {
/* 345:345 */      this.min.x = Math.min(this.min.x, point.x);
/* 346:346 */      this.min.y = Math.min(this.min.y, point.y);
/* 347:347 */      this.min.z = Math.min(this.min.z, point.z);
/* 348:    */      
/* 349:349 */      this.max.x = Math.max(this.max.x, point.x);
/* 350:350 */      this.max.y = Math.max(this.max.y, point.y);
/* 351:351 */      this.max.z = Math.max(this.max.z, point.z);
/* 352:    */    }
/* 353:    */    
/* 356:    */    public void get_center_extend(Vector3f center, Vector3f extend)
/* 357:    */    {
/* 358:358 */      center.add(this.max, this.min);
/* 359:359 */      center.scale(0.5F);
/* 360:    */      
/* 361:361 */      extend.sub(this.max, center);
/* 362:    */    }
/* 363:    */    
/* 366:    */    public void find_intersection(AABB other, AABB intersection)
/* 367:    */    {
/* 368:368 */      intersection.min.x = Math.max(other.min.x, this.min.x);
/* 369:369 */      intersection.min.y = Math.max(other.min.y, this.min.y);
/* 370:370 */      intersection.min.z = Math.max(other.min.z, this.min.z);
/* 371:    */      
/* 372:372 */      intersection.max.x = Math.min(other.max.x, this.max.x);
/* 373:373 */      intersection.max.y = Math.min(other.max.y, this.max.y);
/* 374:374 */      intersection.max.z = Math.min(other.max.z, this.max.z);
/* 375:    */    }
/* 376:    */    
/* 377:    */    public boolean has_collision(AABB other) {
/* 378:378 */      if ((this.min.x > other.max.x) || (this.max.x < other.min.x) || (this.min.y > other.max.y) || (this.max.y < other.min.y) || (this.min.z > other.max.z) || (this.max.z < other.min.z))
/* 379:    */      {
/* 384:384 */        return false;
/* 385:    */      }
/* 386:386 */      return true;
/* 387:    */    }
/* 388:    */    
/* 395:    */    public boolean collide_ray(Vector3f arg1, Vector3f arg2)
/* 396:    */    {
/* 397:397 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f extents = localStack.get$javax$vecmath$Vector3f();Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 398:398 */        get_center_extend(center, extents);
/* 399:    */        
/* 400:400 */        float Dx = vorigin.x - center.x;
/* 401:401 */        if ((BoxCollision.BT_GREATER(Dx, extents.x)) && (Dx * vdir.x >= 0.0F)) { return false;
/* 402:    */        }
/* 403:403 */        float Dy = vorigin.y - center.y;
/* 404:404 */        if ((BoxCollision.BT_GREATER(Dy, extents.y)) && (Dy * vdir.y >= 0.0F)) { return false;
/* 405:    */        }
/* 406:406 */        float Dz = vorigin.z - center.z;
/* 407:407 */        if ((BoxCollision.BT_GREATER(Dz, extents.z)) && (Dz * vdir.z >= 0.0F)) { return false;
/* 408:    */        }
/* 409:409 */        float f = vdir.y * Dz - vdir.z * Dy;
/* 410:410 */        if (Math.abs(f) > extents.y * Math.abs(vdir.z) + extents.z * Math.abs(vdir.y)) { return false;
/* 411:    */        }
/* 412:412 */        f = vdir.z * Dx - vdir.x * Dz;
/* 413:413 */        if (Math.abs(f) > extents.x * Math.abs(vdir.z) + extents.z * Math.abs(vdir.x)) { return false;
/* 414:    */        }
/* 415:415 */        f = vdir.x * Dy - vdir.y * Dx;
/* 416:416 */        if (Math.abs(f) > extents.x * Math.abs(vdir.y) + extents.y * Math.abs(vdir.x)) { return false;
/* 417:    */        }
/* 418:418 */        return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 419:    */      }
/* 420:    */    }
/* 421:    */    
/* 422:422 */    public void projection_interval(Vector3f arg1, float[] arg2, float[] arg3) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 423:    */        
/* 424:424 */        Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 425:425 */        Vector3f extend = localStack.get$javax$vecmath$Vector3f();
/* 426:426 */        get_center_extend(center, extend);
/* 427:    */        
/* 428:428 */        float _fOrigin = direction.dot(center);
/* 429:429 */        tmp.absolute(direction);
/* 430:430 */        float _fMaximumExtent = extend.dot(tmp);
/* 431:431 */        vmin[0] = (_fOrigin - _fMaximumExtent);
/* 432:432 */        vmax[0] = (_fOrigin + _fMaximumExtent);
/* 433:433 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/* 434:    */      } }
/* 435:    */    
/* 436:436 */    public PlaneIntersectionType plane_classify(Vector4f arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 437:    */        
/* 438:438 */        float[] _fmin = new float[1];float[] _fmax = new float[1];
/* 439:439 */        tmp.set(plane.x, plane.y, plane.z);
/* 440:440 */        projection_interval(tmp, _fmin, _fmax);
/* 441:    */        
/* 442:442 */        if (plane.w > _fmax[0] + 1.0E-006F) {
/* 443:443 */          return PlaneIntersectionType.BACK_PLANE;
/* 444:    */        }
/* 445:    */        
/* 446:446 */        if (plane.w + 1.0E-006F >= _fmin[0]) {
/* 447:447 */          return PlaneIntersectionType.COLLIDE_PLANE;
/* 448:    */        }
/* 449:    */        
/* 450:450 */        return PlaneIntersectionType.FRONT_PLANE; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 451:    */      }
/* 452:    */    }
/* 453:    */    
/* 454:454 */    public boolean overlapping_trans_conservative(AABB arg1, Transform arg2) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();AABB tbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
/* 455:455 */        tbox.appy_transform(trans1_to_0);
/* 456:456 */        return has_collision(tbox); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 457:    */      }
/* 458:    */    }
/* 459:    */    
/* 460:460 */    public boolean overlapping_trans_conservative2(AABB arg1, BoxCollision.BoxBoxTransformCache arg2) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();AABB tbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
/* 461:461 */        tbox.appy_transform_trans_cache(trans1_to_0);
/* 462:462 */        return has_collision(tbox); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 463:    */      }
/* 464:    */    }
/* 465:    */    
/* 467:    */    public boolean overlapping_trans_cache(AABB arg1, BoxCollision.BoxBoxTransformCache arg2, boolean arg3)
/* 468:    */    {
/* 469:469 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 470:    */        
/* 472:472 */        Vector3f ea = localStack.get$javax$vecmath$Vector3f();Vector3f eb = localStack.get$javax$vecmath$Vector3f();
/* 473:473 */        Vector3f ca = localStack.get$javax$vecmath$Vector3f();Vector3f cb = localStack.get$javax$vecmath$Vector3f();
/* 474:474 */        get_center_extend(ca, ea);
/* 475:475 */        box.get_center_extend(cb, eb);
/* 476:    */        
/* 477:477 */        Vector3f T = localStack.get$javax$vecmath$Vector3f();
/* 478:    */        
/* 481:481 */        for (int i = 0; i < 3; i++) {
/* 482:482 */          transcache.R1to0.getRow(i, tmp);
/* 483:483 */          VectorUtil.setCoord(T, i, tmp.dot(cb) + VectorUtil.getCoord(transcache.T1to0, i) - VectorUtil.getCoord(ca, i));
/* 484:    */          
/* 485:485 */          transcache.AR.getRow(i, tmp);
/* 486:486 */          float t = tmp.dot(eb) + VectorUtil.getCoord(ea, i);
/* 487:487 */          if (BoxCollision.BT_GREATER(VectorUtil.getCoord(T, i), t)) {
/* 488:488 */            return false;
/* 489:    */          }
/* 490:    */        }
/* 491:    */        
/* 492:492 */        for (int i = 0; i < 3; i++) {
/* 493:493 */          float t = BoxCollision.bt_mat3_dot_col(transcache.R1to0, T, i);
/* 494:494 */          float t2 = BoxCollision.bt_mat3_dot_col(transcache.AR, ea, i) + VectorUtil.getCoord(eb, i);
/* 495:495 */          if (BoxCollision.BT_GREATER(t, t2)) {
/* 496:496 */            return false;
/* 497:    */          }
/* 498:    */        }
/* 499:    */        
/* 500:500 */        if (fulltest)
/* 501:    */        {
/* 502:502 */          for (int i = 0; i < 3; i++) {
/* 503:503 */            int m = (i + 1) % 3;
/* 504:504 */            int n = (i + 2) % 3;
/* 505:505 */            int o = i == 0 ? 1 : 0;
/* 506:506 */            int p = i == 2 ? 1 : 2;
/* 507:507 */            for (int j = 0; j < 3; j++) {
/* 508:508 */              int q = j == 2 ? 1 : 2;
/* 509:509 */              int r = j == 0 ? 1 : 0;
/* 510:510 */              float t = VectorUtil.getCoord(T, n) * transcache.R1to0.getElement(m, j) - VectorUtil.getCoord(T, m) * transcache.R1to0.getElement(n, j);
/* 511:511 */              float t2 = VectorUtil.getCoord(ea, o) * transcache.AR.getElement(p, j) + VectorUtil.getCoord(ea, p) * transcache.AR.getElement(o, j) + VectorUtil.getCoord(eb, r) * transcache.AR.getElement(i, q) + VectorUtil.getCoord(eb, q) * transcache.AR.getElement(i, r);
/* 512:    */              
/* 513:513 */              if (BoxCollision.BT_GREATER(t, t2)) {
/* 514:514 */                return false;
/* 515:    */              }
/* 516:    */            }
/* 517:    */          }
/* 518:    */        }
/* 519:519 */        return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 520:    */      }
/* 521:    */    }
/* 522:    */    
/* 524:    */    public boolean collide_plane(Vector4f plane)
/* 525:    */    {
/* 526:526 */      PlaneIntersectionType classify = plane_classify(plane);
/* 527:527 */      return classify == PlaneIntersectionType.COLLIDE_PLANE;
/* 528:    */    }
/* 529:    */    
/* 532:    */    public boolean collide_triangle_exact(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector4f arg4)
/* 533:    */    {
/* 534:534 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (!collide_plane(triangle_plane)) {
/* 535:535 */          return false;
/* 536:    */        }
/* 537:537 */        Vector3f center = localStack.get$javax$vecmath$Vector3f();Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
/* 538:538 */        get_center_extend(center, extends_);
/* 539:    */        
/* 540:540 */        Vector3f v1 = localStack.get$javax$vecmath$Vector3f();
/* 541:541 */        v1.sub(p1, center);
/* 542:542 */        Vector3f v2 = localStack.get$javax$vecmath$Vector3f();
/* 543:543 */        v2.sub(p2, center);
/* 544:544 */        Vector3f v3 = localStack.get$javax$vecmath$Vector3f();
/* 545:545 */        v3.sub(p3, center);
/* 546:    */        
/* 548:548 */        Vector3f diff = localStack.get$javax$vecmath$Vector3f();
/* 549:549 */        diff.sub(v2, v1);
/* 550:550 */        Vector3f abs_diff = localStack.get$javax$vecmath$Vector3f();
/* 551:551 */        abs_diff.absolute(diff);
/* 552:    */        
/* 554:554 */        BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, v1, v3, extends_);
/* 555:    */        
/* 556:556 */        BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, v1, v3, extends_);
/* 557:    */        
/* 558:558 */        BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, v1, v3, extends_);
/* 559:    */        
/* 560:560 */        diff.sub(v3, v2);
/* 561:561 */        abs_diff.absolute(diff);
/* 562:    */        
/* 564:564 */        BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, v2, v1, extends_);
/* 565:    */        
/* 566:566 */        BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, v2, v1, extends_);
/* 567:    */        
/* 568:568 */        BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, v2, v1, extends_);
/* 569:    */        
/* 570:570 */        diff.sub(v1, v3);
/* 571:571 */        abs_diff.absolute(diff);
/* 572:    */        
/* 574:574 */        BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, v3, v2, extends_);
/* 575:    */        
/* 576:576 */        BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, v3, v2, extends_);
/* 577:    */        
/* 578:578 */        BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, v3, v2, extends_);
/* 579:    */        
/* 580:580 */        return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 581:    */      }
/* 582:    */    }
/* 583:    */  }
/* 584:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BoxCollision
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */