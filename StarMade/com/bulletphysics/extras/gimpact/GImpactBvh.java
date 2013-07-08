/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import com.bulletphysics.util.IntArrayList;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  41:    */class GImpactBvh
/*  42:    */{
/*  43: 43 */  protected BvhTree box_tree = new BvhTree();
/*  44:    */  
/*  45:    */  protected PrimitiveManagerBase primitive_manager;
/*  46:    */  
/*  48:    */  public GImpactBvh()
/*  49:    */  {
/*  50: 50 */    this.primitive_manager = null;
/*  51:    */  }
/*  52:    */  
/*  55:    */  public GImpactBvh(PrimitiveManagerBase primitive_manager)
/*  56:    */  {
/*  57: 57 */    this.primitive_manager = primitive_manager;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public BoxCollision.AABB getGlobalBox(BoxCollision.AABB out) {
/*  61: 61 */    getNodeBound(0, out);
/*  62: 62 */    return out;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void setPrimitiveManager(PrimitiveManagerBase primitive_manager) {
/*  66: 66 */    this.primitive_manager = primitive_manager;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public PrimitiveManagerBase getPrimitiveManager() {
/*  70: 70 */    return this.primitive_manager;
/*  71:    */  }
/*  72:    */  
/*  73:    */  protected void refit()
/*  74:    */  {
/*  75: 75 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB leafbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  76: 76 */      BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  77: 77 */      BoxCollision.AABB temp_box = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  78:    */      
/*  79: 79 */      int nodecount = getNodeCount();
/*  80: 80 */      while (nodecount-- != 0)
/*  81: 81 */        if (isLeafNode(nodecount)) {
/*  82: 82 */          this.primitive_manager.get_primitive_box(getNodeData(nodecount), leafbox);
/*  83: 83 */          setNodeBound(nodecount, leafbox);
/*  85:    */        }
/*  86:    */        else
/*  87:    */        {
/*  88: 88 */          bound.invalidate();
/*  89:    */          
/*  90: 90 */          int child_node = getLeftNode(nodecount);
/*  91: 91 */          if (child_node != 0) {
/*  92: 92 */            getNodeBound(child_node, temp_box);
/*  93: 93 */            bound.merge(temp_box);
/*  94:    */          }
/*  95:    */          
/*  96: 96 */          child_node = getRightNode(nodecount);
/*  97: 97 */          if (child_node != 0) {
/*  98: 98 */            getNodeBound(child_node, temp_box);
/*  99: 99 */            bound.merge(temp_box);
/* 100:    */          }
/* 101:    */          
/* 102:102 */          setNodeBound(nodecount, bound);
/* 103:    */        }
/* 104:    */    } finally {
/* 105:105 */      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 106:    */    }
/* 107:    */  }
/* 108:    */  
/* 110:    */  public void update()
/* 111:    */  {
/* 112:112 */    refit();
/* 113:    */  }
/* 114:    */  
/* 118:    */  public void buildSet()
/* 119:    */  {
/* 120:120 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BvhDataArray primitive_boxes = new BvhDataArray();
/* 121:121 */      primitive_boxes.resize(this.primitive_manager.get_primitive_count());
/* 122:    */      
/* 123:123 */      BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 124:    */      
/* 125:125 */      for (int i = 0; i < primitive_boxes.size(); i++)
/* 126:    */      {
/* 127:127 */        this.primitive_manager.get_primitive_box(i, tmpAABB);
/* 128:128 */        primitive_boxes.setBound(i, tmpAABB);
/* 129:    */        
/* 130:130 */        primitive_boxes.setData(i, i);
/* 131:    */      }
/* 132:    */      
/* 133:133 */      this.box_tree.build_tree(primitive_boxes);
/* 134:134 */    } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:    */  public boolean boxQuery(BoxCollision.AABB arg1, IntArrayList arg2)
/* 139:    */  {
/* 140:140 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();int curIndex = 0;
/* 141:141 */      int numNodes = getNodeCount();
/* 142:    */      
/* 143:143 */      BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 144:    */      
/* 145:145 */      while (curIndex < numNodes) {
/* 146:146 */        getNodeBound(curIndex, bound);
/* 147:    */        
/* 150:150 */        boolean aabbOverlap = bound.has_collision(box);
/* 151:151 */        boolean isleafnode = isLeafNode(curIndex);
/* 152:    */        
/* 153:153 */        if ((isleafnode) && (aabbOverlap)) {
/* 154:154 */          collided_results.add(getNodeData(curIndex));
/* 155:    */        }
/* 156:    */        
/* 157:157 */        if ((aabbOverlap) || (isleafnode))
/* 158:    */        {
/* 159:159 */          curIndex++;
/* 160:    */        }
/* 161:    */        else
/* 162:    */        {
/* 163:163 */          curIndex += getEscapeNodeIndex(curIndex);
/* 164:    */        }
/* 165:    */      }
/* 166:166 */      if (collided_results.size() > 0) {
/* 167:167 */        return true;
/* 168:    */      }
/* 169:169 */      return false; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 174:    */  public boolean boxQueryTrans(BoxCollision.AABB arg1, Transform arg2, IntArrayList arg3)
/* 175:    */  {
/* 176:176 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB transbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
/* 177:177 */      transbox.appy_transform(transform);
/* 178:178 */      return boxQuery(transbox, collided_results); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 179:    */    }
/* 180:    */  }
/* 181:    */  
/* 183:    */  public boolean rayQuery(Vector3f arg1, Vector3f arg2, IntArrayList arg3)
/* 184:    */  {
/* 185:185 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();int curIndex = 0;
/* 186:186 */      int numNodes = getNodeCount();
/* 187:    */      
/* 188:188 */      BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 189:    */      
/* 190:190 */      while (curIndex < numNodes) {
/* 191:191 */        getNodeBound(curIndex, bound);
/* 192:    */        
/* 195:195 */        boolean aabbOverlap = bound.collide_ray(ray_origin, ray_dir);
/* 196:196 */        boolean isleafnode = isLeafNode(curIndex);
/* 197:    */        
/* 198:198 */        if ((isleafnode) && (aabbOverlap)) {
/* 199:199 */          collided_results.add(getNodeData(curIndex));
/* 200:    */        }
/* 201:    */        
/* 202:202 */        if ((aabbOverlap) || (isleafnode))
/* 203:    */        {
/* 204:204 */          curIndex++;
/* 205:    */        }
/* 206:    */        else
/* 207:    */        {
/* 208:208 */          curIndex += getEscapeNodeIndex(curIndex);
/* 209:    */        }
/* 210:    */      }
/* 211:211 */      if (collided_results.size() > 0) {
/* 212:212 */        return true;
/* 213:    */      }
/* 214:214 */      return false; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 219:    */  public boolean hasHierarchy()
/* 220:    */  {
/* 221:221 */    return true;
/* 222:    */  }
/* 223:    */  
/* 226:    */  public boolean isTrimesh()
/* 227:    */  {
/* 228:228 */    return this.primitive_manager.is_trimesh();
/* 229:    */  }
/* 230:    */  
/* 231:    */  public int getNodeCount() {
/* 232:232 */    return this.box_tree.getNodeCount();
/* 233:    */  }
/* 234:    */  
/* 237:    */  public boolean isLeafNode(int nodeindex)
/* 238:    */  {
/* 239:239 */    return this.box_tree.isLeafNode(nodeindex);
/* 240:    */  }
/* 241:    */  
/* 242:    */  public int getNodeData(int nodeindex) {
/* 243:243 */    return this.box_tree.getNodeData(nodeindex);
/* 244:    */  }
/* 245:    */  
/* 246:    */  public void getNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 247:247 */    this.box_tree.getNodeBound(nodeindex, bound);
/* 248:    */  }
/* 249:    */  
/* 250:    */  public void setNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 251:251 */    this.box_tree.setNodeBound(nodeindex, bound);
/* 252:    */  }
/* 253:    */  
/* 254:    */  public int getLeftNode(int nodeindex) {
/* 255:255 */    return this.box_tree.getLeftNode(nodeindex);
/* 256:    */  }
/* 257:    */  
/* 258:    */  public int getRightNode(int nodeindex) {
/* 259:259 */    return this.box_tree.getRightNode(nodeindex);
/* 260:    */  }
/* 261:    */  
/* 262:    */  public int getEscapeNodeIndex(int nodeindex) {
/* 263:263 */    return this.box_tree.getEscapeNodeIndex(nodeindex);
/* 264:    */  }
/* 265:    */  
/* 266:    */  public void getNodeTriangle(int nodeindex, PrimitiveTriangle triangle) {
/* 267:267 */    this.primitive_manager.get_primitive_triangle(getNodeData(nodeindex), triangle);
/* 268:    */  }
/* 269:    */  
/* 270:    */  public BvhTreeNodeArray get_node_pointer() {
/* 271:271 */    return this.box_tree.get_node_pointer();
/* 272:    */  }
/* 273:    */  
/* 274:    */  private static boolean _node_collision(GImpactBvh arg0, GImpactBvh arg1, BoxCollision.BoxBoxTransformCache arg2, int arg3, int arg4, boolean arg5) {
/* 275:275 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB box0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 276:276 */      boxset0.getNodeBound(node0, box0);
/* 277:277 */      BoxCollision.AABB box1 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 278:278 */      boxset1.getNodeBound(node1, box1);
/* 279:    */      
/* 280:280 */      return box0.overlapping_trans_cache(box1, trans_cache_1to0, complete_primitive_tests); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 281:    */    }
/* 282:    */  }
/* 283:    */  
/* 287:    */  private static void _find_collision_pairs_recursive(GImpactBvh boxset0, GImpactBvh boxset1, PairSet collision_pairs, BoxCollision.BoxBoxTransformCache trans_cache_1to0, int node0, int node1, boolean complete_primitive_tests)
/* 288:    */  {
/* 289:289 */    if (!_node_collision(boxset0, boxset1, trans_cache_1to0, node0, node1, complete_primitive_tests))
/* 290:    */    {
/* 292:292 */      return;
/* 293:    */    }
/* 294:294 */    if (boxset0.isLeafNode(node0)) {
/* 295:295 */      if (boxset1.isLeafNode(node1))
/* 296:    */      {
/* 297:297 */        collision_pairs.push_pair(boxset0.getNodeData(node0), boxset1.getNodeData(node1));
/* 298:298 */        return;
/* 299:    */      }
/* 300:    */      
/* 302:302 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, node0, boxset1.getLeftNode(node1), false);
/* 303:    */      
/* 308:308 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, node0, boxset1.getRightNode(node1), false);
/* 314:    */    }
/* 315:315 */    else if (boxset1.isLeafNode(node1))
/* 316:    */    {
/* 317:317 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), node1, false);
/* 318:    */      
/* 324:324 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), node1, false);
/* 327:    */    }
/* 328:    */    else
/* 329:    */    {
/* 331:331 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), boxset1.getLeftNode(node1), false);
/* 332:    */      
/* 337:337 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), boxset1.getRightNode(node1), false);
/* 338:    */      
/* 343:343 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), boxset1.getLeftNode(node1), false);
/* 344:    */      
/* 349:349 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), boxset1.getRightNode(node1), false);
/* 350:    */    }
/* 351:    */  }
/* 352:    */  
/* 359:    */  public static void find_collision(GImpactBvh arg0, Transform arg1, GImpactBvh arg2, Transform arg3, PairSet arg4)
/* 360:    */  {
/* 361:361 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache(); if ((boxset0.getNodeCount() == 0) || (boxset1.getNodeCount() == 0)) {
/* 362:362 */        return;
/* 363:    */      }
/* 364:364 */      BoxCollision.BoxBoxTransformCache trans_cache_1to0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
/* 365:    */      
/* 366:366 */      trans_cache_1to0.calc_from_homogenic(trans0, trans1);
/* 367:    */      
/* 372:372 */      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, 0, 0, true);
/* 375:    */    }
/* 376:    */    finally
/* 377:    */    {
/* 379:379 */      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
/* 380:    */    }
/* 381:    */  }
/* 382:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactBvh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */