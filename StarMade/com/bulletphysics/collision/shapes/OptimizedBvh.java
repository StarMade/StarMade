/*    1:     */package com.bulletphysics.collision.shapes;
/*    2:     */
/*    3:     */import com.bulletphysics..Stack;
/*    4:     */import com.bulletphysics.linearmath.AabbUtil2;
/*    5:     */import com.bulletphysics.linearmath.MiscUtil;
/*    6:     */import com.bulletphysics.linearmath.VectorUtil;
/*    7:     */import com.bulletphysics.util.ObjectArrayList;
/*    8:     */import java.io.Serializable;
/*    9:     */import javax.vecmath.Vector3f;
/*   10:     */
/*   43:     */public class OptimizedBvh
/*   44:     */  implements Serializable
/*   45:     */{
/*   46:     */  private static final long serialVersionUID = 1L;
/*   47:     */  private static final boolean DEBUG_TREE_BUILDING = false;
/*   48:  48 */  private static int gStackDepth = 0;
/*   49:  49 */  private static int gMaxStackDepth = 0;
/*   50:     */  
/*   51:  51 */  private static int maxIterations = 0;
/*   52:     */  public static final int MAX_SUBTREE_SIZE_IN_BYTES = 2048;
/*   53:     */  public static final int MAX_NUM_PARTS_IN_BITS = 10;
/*   54:     */  private final ObjectArrayList<OptimizedBvhNode> leafNodes;
/*   55:     */  private final ObjectArrayList<OptimizedBvhNode> contiguousNodes;
/*   56:     */  private QuantizedBvhNodes quantizedLeafNodes;
/*   57:     */  private QuantizedBvhNodes quantizedContiguousNodes;
/*   58:     */  private int curNodeIndex;
/*   59:     */  
/*   60:     */  public OptimizedBvh()
/*   61:     */  {
/*   62:  62 */    this.leafNodes = new ObjectArrayList();
/*   63:  63 */    this.contiguousNodes = new ObjectArrayList();
/*   64:     */    
/*   65:  65 */    this.quantizedLeafNodes = new QuantizedBvhNodes();
/*   66:  66 */    this.quantizedContiguousNodes = new QuantizedBvhNodes();
/*   67:     */    
/*   72:  72 */    this.bvhAabbMin = new Vector3f();
/*   73:  73 */    this.bvhAabbMax = new Vector3f();
/*   74:  74 */    this.bvhQuantization = new Vector3f();
/*   75:     */    
/*   76:  76 */    this.traversalMode = TraversalMode.STACKLESS;
/*   77:  77 */    this.SubtreeHeaders = new ObjectArrayList();
/*   78:     */  }
/*   79:     */  
/*   82:     */  public void setInternalNodeAabbMin(int nodeIndex, Vector3f aabbMin)
/*   83:     */  {
/*   84:  84 */    if (this.useQuantization) {
/*   85:  85 */      this.quantizedContiguousNodes.setQuantizedAabbMin(nodeIndex, quantizeWithClamp(aabbMin));
/*   86:     */    }
/*   87:     */    else {
/*   88:  88 */      ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMinOrg.set(aabbMin);
/*   89:     */    }
/*   90:     */  }
/*   91:     */  
/*   92:     */  public void setInternalNodeAabbMax(int nodeIndex, Vector3f aabbMax) {
/*   93:  93 */    if (this.useQuantization) {
/*   94:  94 */      this.quantizedContiguousNodes.setQuantizedAabbMax(nodeIndex, quantizeWithClamp(aabbMax));
/*   95:     */    }
/*   96:     */    else {
/*   97:  97 */      ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMaxOrg.set(aabbMax);
/*   98:     */    }
/*   99:     */  }
/*  100:     */  
/*  101:     */  public Vector3f getAabbMin(int nodeIndex) {
/*  102: 102 */    if (this.useQuantization) {
/*  103: 103 */      Vector3f tmp = new Vector3f();
/*  104: 104 */      unQuantize(tmp, this.quantizedLeafNodes.getQuantizedAabbMin(nodeIndex));
/*  105: 105 */      return tmp;
/*  106:     */    }
/*  107:     */    
/*  109: 109 */    return ((OptimizedBvhNode)this.leafNodes.getQuick(nodeIndex)).aabbMinOrg;
/*  110:     */  }
/*  111:     */  
/*  112:     */  public Vector3f getAabbMax(int nodeIndex) {
/*  113: 113 */    if (this.useQuantization) {
/*  114: 114 */      Vector3f tmp = new Vector3f();
/*  115: 115 */      unQuantize(tmp, this.quantizedLeafNodes.getQuantizedAabbMax(nodeIndex));
/*  116: 116 */      return tmp;
/*  117:     */    }
/*  118:     */    
/*  120: 120 */    return ((OptimizedBvhNode)this.leafNodes.getQuick(nodeIndex)).aabbMaxOrg;
/*  121:     */  }
/*  122:     */  
/*  123:     */  public void setQuantizationValues(Vector3f aabbMin, Vector3f aabbMax) {
/*  124: 124 */    setQuantizationValues(aabbMin, aabbMax, 1.0F);
/*  125:     */  }
/*  126:     */  
/*  127:     */  public void setQuantizationValues(Vector3f arg1, Vector3f arg2, float arg3)
/*  128:     */  {
/*  129: 129 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f clampValue = localStack.get$javax$vecmath$Vector3f();
/*  130: 130 */      clampValue.set(quantizationMargin, quantizationMargin, quantizationMargin);
/*  131: 131 */      this.bvhAabbMin.sub(aabbMin, clampValue);
/*  132: 132 */      this.bvhAabbMax.add(aabbMax, clampValue);
/*  133: 133 */      Vector3f aabbSize = localStack.get$javax$vecmath$Vector3f();
/*  134: 134 */      aabbSize.sub(this.bvhAabbMax, this.bvhAabbMin);
/*  135: 135 */      this.bvhQuantization.set(65535.0F, 65535.0F, 65535.0F);
/*  136: 136 */      VectorUtil.div(this.bvhQuantization, this.bvhQuantization, aabbSize);
/*  137: 137 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  138:     */    } }
/*  139:     */  
/*  140: 140 */  public void setInternalNodeEscapeIndex(int nodeIndex, int escapeIndex) { if (this.useQuantization) {
/*  141: 141 */      this.quantizedContiguousNodes.setEscapeIndexOrTriangleIndex(nodeIndex, -escapeIndex);
/*  142:     */    }
/*  143:     */    else {
/*  144: 144 */      ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).escapeIndex = escapeIndex;
/*  145:     */    }
/*  146:     */  }
/*  147:     */  
/*  148:     */  public void mergeInternalNodeAabb(int nodeIndex, Vector3f newAabbMin, Vector3f newAabbMax) {
/*  149: 149 */    if (this.useQuantization)
/*  150:     */    {
/*  153: 153 */      long quantizedAabbMin = quantizeWithClamp(newAabbMin);
/*  154: 154 */      long quantizedAabbMax = quantizeWithClamp(newAabbMax);
/*  155: 155 */      for (int i = 0; i < 3; i++) {
/*  156: 156 */        if (this.quantizedContiguousNodes.getQuantizedAabbMin(nodeIndex, i) > QuantizedBvhNodes.getCoord(quantizedAabbMin, i)) {
/*  157: 157 */          this.quantizedContiguousNodes.setQuantizedAabbMin(nodeIndex, i, QuantizedBvhNodes.getCoord(quantizedAabbMin, i));
/*  158:     */        }
/*  159:     */        
/*  160: 160 */        if (this.quantizedContiguousNodes.getQuantizedAabbMax(nodeIndex, i) < QuantizedBvhNodes.getCoord(quantizedAabbMax, i)) {
/*  161: 161 */          this.quantizedContiguousNodes.setQuantizedAabbMax(nodeIndex, i, QuantizedBvhNodes.getCoord(quantizedAabbMax, i));
/*  162:     */        }
/*  163:     */      }
/*  164:     */    }
/*  165:     */    else
/*  166:     */    {
/*  167: 167 */      VectorUtil.setMin(((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMinOrg, newAabbMin);
/*  168: 168 */      VectorUtil.setMax(((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMaxOrg, newAabbMax);
/*  169:     */    }
/*  170:     */  }
/*  171:     */  
/*  172:     */  public void swapLeafNodes(int i, int splitIndex) {
/*  173: 173 */    if (this.useQuantization) {
/*  174: 174 */      this.quantizedLeafNodes.swap(i, splitIndex);
/*  175:     */    }
/*  176:     */    else
/*  177:     */    {
/*  178: 178 */      OptimizedBvhNode tmp = (OptimizedBvhNode)this.leafNodes.getQuick(i);
/*  179: 179 */      this.leafNodes.setQuick(i, this.leafNodes.getQuick(splitIndex));
/*  180: 180 */      this.leafNodes.setQuick(splitIndex, tmp);
/*  181:     */    }
/*  182:     */  }
/*  183:     */  
/*  184:     */  public void assignInternalNodeFromLeafNode(int internalNode, int leafNodeIndex) {
/*  185: 185 */    if (this.useQuantization) {
/*  186: 186 */      this.quantizedContiguousNodes.set(internalNode, this.quantizedLeafNodes, leafNodeIndex);
/*  187:     */    }
/*  188:     */    else {
/*  189: 189 */      ((OptimizedBvhNode)this.contiguousNodes.getQuick(internalNode)).set((OptimizedBvhNode)this.leafNodes.getQuick(leafNodeIndex));
/*  190:     */    }
/*  191:     */  }
/*  192:     */  
/*  193:     */  private static class NodeTriangleCallback extends InternalTriangleIndexCallback
/*  194:     */  {
/*  195:     */    public NodeTriangleCallback(ObjectArrayList<OptimizedBvhNode> triangleNodes)
/*  196:     */    {
/*  197: 197 */      this.triangleNodes = triangleNodes;
/*  198:     */    }
/*  199:     */    
/*  200: 200 */    private final Vector3f aabbMax = new Vector3f(); private final Vector3f aabbMin = new Vector3f();
/*  201:     */    public ObjectArrayList<OptimizedBvhNode> triangleNodes;
/*  202:     */    
/*  203: 203 */    public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex) { OptimizedBvhNode node = new OptimizedBvhNode();
/*  204: 204 */      this.aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  205: 205 */      this.aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  206: 206 */      VectorUtil.setMin(this.aabbMin, triangle[0]);
/*  207: 207 */      VectorUtil.setMax(this.aabbMax, triangle[0]);
/*  208: 208 */      VectorUtil.setMin(this.aabbMin, triangle[1]);
/*  209: 209 */      VectorUtil.setMax(this.aabbMax, triangle[1]);
/*  210: 210 */      VectorUtil.setMin(this.aabbMin, triangle[2]);
/*  211: 211 */      VectorUtil.setMax(this.aabbMax, triangle[2]);
/*  212:     */      
/*  214: 214 */      node.aabbMinOrg.set(this.aabbMin);
/*  215: 215 */      node.aabbMaxOrg.set(this.aabbMax);
/*  216:     */      
/*  217: 217 */      node.escapeIndex = -1;
/*  218:     */      
/*  220: 220 */      node.subPart = partId;
/*  221: 221 */      node.triangleIndex = triangleIndex;
/*  222: 222 */      this.triangleNodes.add(node);
/*  223:     */    }
/*  224:     */  }
/*  225:     */  
/*  226:     */  private static class QuantizedNodeTriangleCallback extends InternalTriangleIndexCallback
/*  227:     */  {
/*  228:     */    public QuantizedBvhNodes triangleNodes;
/*  229:     */    public OptimizedBvh optimizedTree;
/*  230:     */    
/*  231:     */    public QuantizedNodeTriangleCallback(QuantizedBvhNodes triangleNodes, OptimizedBvh tree)
/*  232:     */    {
/*  233: 233 */      this.triangleNodes = triangleNodes;
/*  234: 234 */      this.optimizedTree = tree;
/*  235:     */    }
/*  236:     */    
/*  237:     */    public void internalProcessTriangleIndex(Vector3f[] arg1, int arg2, int arg3)
/*  238:     */    {
/*  239: 239 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();assert (partId < 1024);
/*  240: 240 */        assert (triangleIndex < 2097152);
/*  241:     */        
/*  242: 242 */        assert (triangleIndex >= 0);
/*  243:     */        
/*  244: 244 */        int nodeId = this.triangleNodes.add();
/*  245: 245 */        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  246: 246 */        aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  247: 247 */        aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  248: 248 */        VectorUtil.setMin(aabbMin, triangle[0]);
/*  249: 249 */        VectorUtil.setMax(aabbMax, triangle[0]);
/*  250: 250 */        VectorUtil.setMin(aabbMin, triangle[1]);
/*  251: 251 */        VectorUtil.setMax(aabbMax, triangle[1]);
/*  252: 252 */        VectorUtil.setMin(aabbMin, triangle[2]);
/*  253: 253 */        VectorUtil.setMax(aabbMax, triangle[2]);
/*  254:     */        
/*  256: 256 */        float MIN_AABB_DIMENSION = 0.002F;
/*  257: 257 */        float MIN_AABB_HALF_DIMENSION = 0.001F;
/*  258: 258 */        if (aabbMax.x - aabbMin.x < 0.002F) {
/*  259: 259 */          aabbMax.x += 0.001F;
/*  260: 260 */          aabbMin.x -= 0.001F;
/*  261:     */        }
/*  262: 262 */        if (aabbMax.y - aabbMin.y < 0.002F) {
/*  263: 263 */          aabbMax.y += 0.001F;
/*  264: 264 */          aabbMin.y -= 0.001F;
/*  265:     */        }
/*  266: 266 */        if (aabbMax.z - aabbMin.z < 0.002F) {
/*  267: 267 */          aabbMax.z += 0.001F;
/*  268: 268 */          aabbMin.z -= 0.001F;
/*  269:     */        }
/*  270:     */        
/*  271: 271 */        this.triangleNodes.setQuantizedAabbMin(nodeId, this.optimizedTree.quantizeWithClamp(aabbMin));
/*  272: 272 */        this.triangleNodes.setQuantizedAabbMax(nodeId, this.optimizedTree.quantizeWithClamp(aabbMax));
/*  273:     */        
/*  274: 274 */        this.triangleNodes.setEscapeIndexOrTriangleIndex(nodeId, partId << 21 | triangleIndex);
/*  275: 275 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/*  276:     */      }
/*  277:     */    } }
/*  278:     */  
/*  279: 279 */  public void build(StridingMeshInterface arg1, boolean arg2, Vector3f arg3, Vector3f arg4) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.useQuantization = useQuantizedAabbCompression;
/*  280:     */      
/*  283: 283 */      int numLeafNodes = 0;
/*  284:     */      
/*  285: 285 */      if (this.useQuantization)
/*  286:     */      {
/*  287: 287 */        setQuantizationValues(_aabbMin, _aabbMax);
/*  288:     */        
/*  289: 289 */        QuantizedNodeTriangleCallback callback = new QuantizedNodeTriangleCallback(this.quantizedLeafNodes, this);
/*  290:     */        
/*  291: 291 */        triangles.internalProcessAllTriangles(callback, this.bvhAabbMin, this.bvhAabbMax);
/*  292:     */        
/*  294: 294 */        numLeafNodes = this.quantizedLeafNodes.size();
/*  295:     */        
/*  296: 296 */        this.quantizedContiguousNodes.resize(2 * numLeafNodes);
/*  297:     */      }
/*  298:     */      else {
/*  299: 299 */        NodeTriangleCallback callback = new NodeTriangleCallback(this.leafNodes);
/*  300:     */        
/*  301: 301 */        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
/*  302: 302 */        aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  303: 303 */        Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  304: 304 */        aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  305:     */        
/*  306: 306 */        triangles.internalProcessAllTriangles(callback, aabbMin, aabbMax);
/*  307:     */        
/*  309: 309 */        numLeafNodes = this.leafNodes.size();
/*  310:     */        
/*  313: 313 */        MiscUtil.resize(this.contiguousNodes, 2 * numLeafNodes, OptimizedBvhNode.class);
/*  314:     */      }
/*  315:     */      
/*  316: 316 */      this.curNodeIndex = 0;
/*  317:     */      
/*  318: 318 */      buildTree(0, numLeafNodes);
/*  319:     */      
/*  321: 321 */      if ((this.useQuantization) && (this.SubtreeHeaders.size() == 0)) {
/*  322: 322 */        BvhSubtreeInfo subtree = new BvhSubtreeInfo();
/*  323: 323 */        this.SubtreeHeaders.add(subtree);
/*  324:     */        
/*  325: 325 */        subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, 0);
/*  326: 326 */        subtree.rootNodeIndex = 0;
/*  327: 327 */        subtree.subtreeSize = (this.quantizedContiguousNodes.isLeafNode(0) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(0));
/*  328:     */      }
/*  329:     */      
/*  331: 331 */      this.subtreeHeaderCount = this.SubtreeHeaders.size();
/*  332:     */      
/*  334: 334 */      this.quantizedLeafNodes.clear();
/*  335: 335 */      this.leafNodes.clear();
/*  336: 336 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  337:     */    } }
/*  338:     */  
/*  339: 339 */  public void refit(StridingMeshInterface arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (this.useQuantization)
/*  340:     */      {
/*  341: 341 */        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  342: 342 */        meshInterface.calculateAabbBruteForce(aabbMin, aabbMax);
/*  343:     */        
/*  344: 344 */        setQuantizationValues(aabbMin, aabbMax);
/*  345:     */        
/*  346: 346 */        updateBvhNodes(meshInterface, 0, this.curNodeIndex, 0);
/*  347:     */        
/*  351: 351 */        for (int i = 0; i < this.SubtreeHeaders.size(); i++) {
/*  352: 352 */          BvhSubtreeInfo subtree = (BvhSubtreeInfo)this.SubtreeHeaders.getQuick(i);
/*  353: 353 */          subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, subtree.rootNodeIndex);
/*  354:     */        }
/*  355:     */        
/*  356:     */      }
/*  357:     */      else
/*  358:     */      {
/*  359: 359 */        build(meshInterface, false, null, null);
/*  360:     */      }
/*  361: 361 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  362:     */    } }
/*  363:     */  
/*  364: 364 */  public void refitPartial(StridingMeshInterface meshInterface, Vector3f aabbMin, Vector3f aabbMax) { throw new UnsupportedOperationException(); }
/*  365:     */  
/*  370:     */  private boolean useQuantization;
/*  371:     */  
/*  375:     */  private final Vector3f bvhAabbMin;
/*  376:     */  
/*  380:     */  private final Vector3f bvhAabbMax;
/*  381:     */  
/*  384:     */  private final Vector3f bvhQuantization;
/*  385:     */  
/*  388:     */  protected TraversalMode traversalMode;
/*  389:     */  
/*  392:     */  protected final ObjectArrayList<BvhSubtreeInfo> SubtreeHeaders;
/*  393:     */  
/*  396:     */  protected int subtreeHeaderCount;
/*  397:     */  
/*  400:     */  public void updateBvhNodes(StridingMeshInterface arg1, int arg2, int arg3, int arg4)
/*  401:     */  {
/*  402: 402 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();assert (this.useQuantization);
/*  403:     */      
/*  404: 404 */      int curNodeSubPart = -1;
/*  405:     */      
/*  406: 406 */      Vector3f[] triangleVerts = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/*  407: 407 */      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  408: 408 */      Vector3f meshScaling = meshInterface.getScaling(localStack.get$javax$vecmath$Vector3f());
/*  409:     */      
/*  410: 410 */      VertexData data = null;
/*  411:     */      
/*  412: 412 */      for (int i = endNode - 1; i >= firstNode; i--) {
/*  413: 413 */        QuantizedBvhNodes curNodes = this.quantizedContiguousNodes;
/*  414: 414 */        int curNodeId = i;
/*  415:     */        
/*  416: 416 */        if (curNodes.isLeafNode(curNodeId))
/*  417:     */        {
/*  418: 418 */          int nodeSubPart = curNodes.getPartId(curNodeId);
/*  419: 419 */          int nodeTriangleIndex = curNodes.getTriangleIndex(curNodeId);
/*  420: 420 */          if (nodeSubPart != curNodeSubPart) {
/*  421: 421 */            if (curNodeSubPart >= 0) {
/*  422: 422 */              meshInterface.unLockReadOnlyVertexBase(curNodeSubPart);
/*  423:     */            }
/*  424: 424 */            data = meshInterface.getLockedReadOnlyVertexIndexBase(nodeSubPart);
/*  425:     */          }
/*  426:     */          
/*  428: 428 */          data.getTriangle(nodeTriangleIndex * 3, meshScaling, triangleVerts);
/*  429:     */          
/*  430: 430 */          aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  431: 431 */          aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  432: 432 */          VectorUtil.setMin(aabbMin, triangleVerts[0]);
/*  433: 433 */          VectorUtil.setMax(aabbMax, triangleVerts[0]);
/*  434: 434 */          VectorUtil.setMin(aabbMin, triangleVerts[1]);
/*  435: 435 */          VectorUtil.setMax(aabbMax, triangleVerts[1]);
/*  436: 436 */          VectorUtil.setMin(aabbMin, triangleVerts[2]);
/*  437: 437 */          VectorUtil.setMax(aabbMax, triangleVerts[2]);
/*  438:     */          
/*  439: 439 */          curNodes.setQuantizedAabbMin(curNodeId, quantizeWithClamp(aabbMin));
/*  440: 440 */          curNodes.setQuantizedAabbMax(curNodeId, quantizeWithClamp(aabbMax));
/*  442:     */        }
/*  443:     */        else
/*  444:     */        {
/*  446: 446 */          int leftChildNodeId = i + 1;
/*  447:     */          
/*  448: 448 */          int rightChildNodeId = this.quantizedContiguousNodes.isLeafNode(leftChildNodeId) ? i + 2 : i + 1 + this.quantizedContiguousNodes.getEscapeIndex(leftChildNodeId);
/*  449:     */          
/*  450: 450 */          for (int i2 = 0; i2 < 3; i2++) {
/*  451: 451 */            curNodes.setQuantizedAabbMin(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMin(leftChildNodeId, i2));
/*  452: 452 */            if (curNodes.getQuantizedAabbMin(curNodeId, i2) > this.quantizedContiguousNodes.getQuantizedAabbMin(rightChildNodeId, i2)) {
/*  453: 453 */              curNodes.setQuantizedAabbMin(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMin(rightChildNodeId, i2));
/*  454:     */            }
/*  455:     */            
/*  456: 456 */            curNodes.setQuantizedAabbMax(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMax(leftChildNodeId, i2));
/*  457: 457 */            if (curNodes.getQuantizedAabbMax(curNodeId, i2) < this.quantizedContiguousNodes.getQuantizedAabbMax(rightChildNodeId, i2)) {
/*  458: 458 */              curNodes.setQuantizedAabbMax(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMax(rightChildNodeId, i2));
/*  459:     */            }
/*  460:     */          }
/*  461:     */        }
/*  462:     */      }
/*  463:     */      
/*  464: 464 */      if (curNodeSubPart >= 0)
/*  465: 465 */        meshInterface.unLockReadOnlyVertexBase(curNodeSubPart);
/*  466:     */    } finally {
/*  467: 467 */      localStack.pop$javax$vecmath$Vector3f();
/*  468:     */    }
/*  469:     */  }
/*  470:     */  
/*  478:     */  protected void buildTree(int arg1, int arg2)
/*  479:     */  {
/*  480: 480 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();int numIndices = endIndex - startIndex;
/*  481: 481 */      int curIndex = this.curNodeIndex;
/*  482:     */      
/*  483: 483 */      assert (numIndices > 0);
/*  484:     */      
/*  485: 485 */      if (numIndices == 1)
/*  486:     */      {
/*  492: 492 */        assignInternalNodeFromLeafNode(this.curNodeIndex, startIndex);
/*  493:     */        
/*  494: 494 */        this.curNodeIndex += 1;
/*  495: 495 */        return;
/*  496:     */      }
/*  497:     */      
/*  499: 499 */      int splitAxis = calcSplittingAxis(startIndex, endIndex);
/*  500:     */      
/*  501: 501 */      int splitIndex = sortAndCalcSplittingIndex(startIndex, endIndex, splitAxis);
/*  502:     */      
/*  503: 503 */      int internalNodeIndex = this.curNodeIndex;
/*  504:     */      
/*  505: 505 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  506: 506 */      tmp1.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  507: 507 */      setInternalNodeAabbMax(this.curNodeIndex, tmp1);
/*  508: 508 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  509: 509 */      tmp2.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  510: 510 */      setInternalNodeAabbMin(this.curNodeIndex, tmp2);
/*  511:     */      
/*  512: 512 */      for (int i = startIndex; i < endIndex; i++) {
/*  513: 513 */        mergeInternalNodeAabb(this.curNodeIndex, getAabbMin(i), getAabbMax(i));
/*  514:     */      }
/*  515:     */      
/*  516: 516 */      this.curNodeIndex += 1;
/*  517:     */      
/*  520: 520 */      int leftChildNodexIndex = this.curNodeIndex;
/*  521:     */      
/*  523: 523 */      buildTree(startIndex, splitIndex);
/*  524:     */      
/*  525: 525 */      int rightChildNodexIndex = this.curNodeIndex;
/*  526:     */      
/*  527: 527 */      buildTree(splitIndex, endIndex);
/*  528:     */      
/*  535: 535 */      int escapeIndex = this.curNodeIndex - curIndex;
/*  536:     */      
/*  537: 537 */      if (this.useQuantization)
/*  538:     */      {
/*  539: 539 */        int sizeQuantizedNode = QuantizedBvhNodes.getNodeSize();
/*  540: 540 */        int treeSizeInBytes = escapeIndex * sizeQuantizedNode;
/*  541: 541 */        if (treeSizeInBytes > 2048) {
/*  542: 542 */          updateSubtreeHeaders(leftChildNodexIndex, rightChildNodexIndex);
/*  543:     */        }
/*  544:     */      }
/*  545:     */      
/*  546: 546 */      setInternalNodeEscapeIndex(internalNodeIndex, escapeIndex);
/*  547: 547 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  548:     */    } }
/*  549:     */  
/*  550: 550 */  protected boolean testQuantizedAabbAgainstQuantizedAabb(long aabbMin1, long aabbMax1, long aabbMin2, long aabbMax2) { int aabbMin1_0 = QuantizedBvhNodes.getCoord(aabbMin1, 0);
/*  551: 551 */    int aabbMin1_1 = QuantizedBvhNodes.getCoord(aabbMin1, 1);
/*  552: 552 */    int aabbMin1_2 = QuantizedBvhNodes.getCoord(aabbMin1, 2);
/*  553:     */    
/*  554: 554 */    int aabbMax1_0 = QuantizedBvhNodes.getCoord(aabbMax1, 0);
/*  555: 555 */    int aabbMax1_1 = QuantizedBvhNodes.getCoord(aabbMax1, 1);
/*  556: 556 */    int aabbMax1_2 = QuantizedBvhNodes.getCoord(aabbMax1, 2);
/*  557:     */    
/*  558: 558 */    int aabbMin2_0 = QuantizedBvhNodes.getCoord(aabbMin2, 0);
/*  559: 559 */    int aabbMin2_1 = QuantizedBvhNodes.getCoord(aabbMin2, 1);
/*  560: 560 */    int aabbMin2_2 = QuantizedBvhNodes.getCoord(aabbMin2, 2);
/*  561:     */    
/*  562: 562 */    int aabbMax2_0 = QuantizedBvhNodes.getCoord(aabbMax2, 0);
/*  563: 563 */    int aabbMax2_1 = QuantizedBvhNodes.getCoord(aabbMax2, 1);
/*  564: 564 */    int aabbMax2_2 = QuantizedBvhNodes.getCoord(aabbMax2, 2);
/*  565:     */    
/*  566: 566 */    boolean overlap = true;
/*  567: 567 */    overlap = (aabbMin1_0 > aabbMax2_0) || (aabbMax1_0 < aabbMin2_0) ? false : overlap;
/*  568: 568 */    overlap = (aabbMin1_2 > aabbMax2_2) || (aabbMax1_2 < aabbMin2_2) ? false : overlap;
/*  569: 569 */    overlap = (aabbMin1_1 > aabbMax2_1) || (aabbMax1_1 < aabbMin2_1) ? false : overlap;
/*  570: 570 */    return overlap;
/*  571:     */  }
/*  572:     */  
/*  573:     */  protected void updateSubtreeHeaders(int leftChildNodexIndex, int rightChildNodexIndex) {
/*  574: 574 */    assert (this.useQuantization);
/*  575:     */    
/*  577: 577 */    int leftSubTreeSize = this.quantizedContiguousNodes.isLeafNode(leftChildNodexIndex) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(leftChildNodexIndex);
/*  578: 578 */    int leftSubTreeSizeInBytes = leftSubTreeSize * QuantizedBvhNodes.getNodeSize();
/*  579:     */    
/*  581: 581 */    int rightSubTreeSize = this.quantizedContiguousNodes.isLeafNode(rightChildNodexIndex) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(rightChildNodexIndex);
/*  582: 582 */    int rightSubTreeSizeInBytes = rightSubTreeSize * QuantizedBvhNodes.getNodeSize();
/*  583:     */    
/*  584: 584 */    if (leftSubTreeSizeInBytes <= 2048) {
/*  585: 585 */      BvhSubtreeInfo subtree = new BvhSubtreeInfo();
/*  586: 586 */      this.SubtreeHeaders.add(subtree);
/*  587:     */      
/*  588: 588 */      subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, leftChildNodexIndex);
/*  589: 589 */      subtree.rootNodeIndex = leftChildNodexIndex;
/*  590: 590 */      subtree.subtreeSize = leftSubTreeSize;
/*  591:     */    }
/*  592:     */    
/*  593: 593 */    if (rightSubTreeSizeInBytes <= 2048) {
/*  594: 594 */      BvhSubtreeInfo subtree = new BvhSubtreeInfo();
/*  595: 595 */      this.SubtreeHeaders.add(subtree);
/*  596:     */      
/*  597: 597 */      subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, rightChildNodexIndex);
/*  598: 598 */      subtree.rootNodeIndex = rightChildNodexIndex;
/*  599: 599 */      subtree.subtreeSize = rightSubTreeSize;
/*  600:     */    }
/*  601:     */    
/*  603: 603 */    this.subtreeHeaderCount = this.SubtreeHeaders.size();
/*  604:     */  }
/*  605:     */  
/*  606:     */  protected int sortAndCalcSplittingIndex(int arg1, int arg2, int arg3)
/*  607:     */  {
/*  608: 608 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();int splitIndex = startIndex;
/*  609: 609 */      int numIndices = endIndex - startIndex;
/*  610:     */      
/*  612: 612 */      Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  613: 613 */      means.set(0.0F, 0.0F, 0.0F);
/*  614: 614 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  615: 615 */      for (int i = startIndex; i < endIndex; i++) {
/*  616: 616 */        center.add(getAabbMax(i), getAabbMin(i));
/*  617: 617 */        center.scale(0.5F);
/*  618: 618 */        means.add(center);
/*  619:     */      }
/*  620: 620 */      means.scale(1.0F / numIndices);
/*  621:     */      
/*  622: 622 */      float splitValue = VectorUtil.getCoord(means, splitAxis);
/*  623:     */      
/*  625: 625 */      for (i = startIndex; i < endIndex; i++)
/*  626:     */      {
/*  627: 627 */        center.add(getAabbMax(i), getAabbMin(i));
/*  628: 628 */        center.scale(0.5F);
/*  629:     */        
/*  630: 630 */        if (VectorUtil.getCoord(center, splitAxis) > splitValue)
/*  631:     */        {
/*  632: 632 */          swapLeafNodes(i, splitIndex);
/*  633: 633 */          splitIndex++;
/*  634:     */        }
/*  635:     */      }
/*  636:     */      
/*  646: 646 */      int rangeBalancedIndices = numIndices / 3;
/*  647: 647 */      boolean unbalanced = (splitIndex <= startIndex + rangeBalancedIndices) || (splitIndex >= endIndex - 1 - rangeBalancedIndices);
/*  648:     */      
/*  649: 649 */      if (unbalanced) {
/*  650: 650 */        splitIndex = startIndex + (numIndices >> 1);
/*  651:     */      }
/*  652:     */      
/*  653: 653 */      boolean unbal = (splitIndex == startIndex) || (splitIndex == endIndex);
/*  654: 654 */      assert (!unbal);
/*  655:     */      
/*  656: 656 */      return splitIndex; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  657:     */    }
/*  658:     */  }
/*  659:     */  
/*  660:     */  protected int calcSplittingAxis(int arg1, int arg2)
/*  661:     */  {
/*  662: 662 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  663: 663 */      means.set(0.0F, 0.0F, 0.0F);
/*  664: 664 */      Vector3f variance = localStack.get$javax$vecmath$Vector3f();
/*  665: 665 */      variance.set(0.0F, 0.0F, 0.0F);
/*  666: 666 */      int numIndices = endIndex - startIndex;
/*  667:     */      
/*  668: 668 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  669: 669 */      for (int i = startIndex; i < endIndex; i++) {
/*  670: 670 */        center.add(getAabbMax(i), getAabbMin(i));
/*  671: 671 */        center.scale(0.5F);
/*  672: 672 */        means.add(center);
/*  673:     */      }
/*  674: 674 */      means.scale(1.0F / numIndices);
/*  675:     */      
/*  676: 676 */      Vector3f diff2 = localStack.get$javax$vecmath$Vector3f();
/*  677: 677 */      for (i = startIndex; i < endIndex; i++) {
/*  678: 678 */        center.add(getAabbMax(i), getAabbMin(i));
/*  679: 679 */        center.scale(0.5F);
/*  680: 680 */        diff2.sub(center, means);
/*  681:     */        
/*  682: 682 */        VectorUtil.mul(diff2, diff2, diff2);
/*  683: 683 */        variance.add(diff2);
/*  684:     */      }
/*  685: 685 */      variance.scale(1.0F / (numIndices - 1.0F));
/*  686:     */      
/*  687: 687 */      return VectorUtil.maxAxis(variance); } finally { localStack.pop$javax$vecmath$Vector3f();
/*  688:     */    }
/*  689:     */  }
/*  690:     */  
/*  691:     */  public void reportAabbOverlappingNodex(NodeOverlapCallback nodeCallback, Vector3f aabbMin, Vector3f aabbMax)
/*  692:     */  {
/*  693: 693 */    if (this.useQuantization)
/*  694:     */    {
/*  697: 697 */      long quantizedQueryAabbMin = quantizeWithClamp(aabbMin);
/*  698: 698 */      long quantizedQueryAabbMax = quantizeWithClamp(aabbMax);
/*  699:     */      
/*  701: 701 */      switch (1.$SwitchMap$com$bulletphysics$collision$shapes$TraversalMode[this.traversalMode.ordinal()]) {
/*  702:     */      case 1: 
/*  703: 703 */        walkStacklessQuantizedTree(nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax, 0, this.curNodeIndex);
/*  704: 704 */        break;
/*  705:     */      
/*  710:     */      case 2: 
/*  711: 711 */        walkRecursiveQuantizedTreeAgainstQueryAabb(this.quantizedContiguousNodes, 0, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
/*  712: 712 */        break;
/*  713:     */      
/*  714:     */      default: 
/*  715: 715 */        if (!$assertionsDisabled) throw new AssertionError();
/*  716:     */        break;
/*  717:     */      }
/*  718:     */    } else {
/*  719: 719 */      walkStacklessTree(nodeCallback, aabbMin, aabbMax);
/*  720:     */    }
/*  721:     */  }
/*  722:     */  
/*  723:     */  protected void walkStacklessTree(NodeOverlapCallback nodeCallback, Vector3f aabbMin, Vector3f aabbMax) {
/*  724: 724 */    assert (!this.useQuantization);
/*  725:     */    
/*  727: 727 */    OptimizedBvhNode rootNode = null;
/*  728: 728 */    int rootNode_index = 0;
/*  729:     */    
/*  730: 730 */    int curIndex = 0;
/*  731: 731 */    int walkIterations = 0;
/*  732:     */    
/*  737: 737 */    while (curIndex < this.curNodeIndex)
/*  738:     */    {
/*  739: 739 */      assert (walkIterations < this.curNodeIndex);
/*  740:     */      
/*  741: 741 */      walkIterations++;
/*  742:     */      
/*  743: 743 */      rootNode = (OptimizedBvhNode)this.contiguousNodes.getQuick(rootNode_index);
/*  744:     */      
/*  745: 745 */      boolean aabbOverlap = AabbUtil2.testAabbAgainstAabb2(aabbMin, aabbMax, rootNode.aabbMinOrg, rootNode.aabbMaxOrg);
/*  746: 746 */      boolean isLeafNode = rootNode.escapeIndex == -1;
/*  747:     */      
/*  749: 749 */      if ((isLeafNode) && (aabbOverlap)) {
/*  750: 750 */        nodeCallback.processNode(rootNode.subPart, rootNode.triangleIndex);
/*  751:     */      }
/*  752:     */      
/*  753: 753 */      rootNode = null;
/*  754:     */      
/*  756: 756 */      if ((aabbOverlap) || (isLeafNode)) {
/*  757: 757 */        rootNode_index++;
/*  758: 758 */        curIndex++;
/*  759:     */      }
/*  760:     */      else {
/*  761: 761 */        int escapeIndex = ((OptimizedBvhNode)this.contiguousNodes.getQuick(rootNode_index)).escapeIndex;
/*  762: 762 */        rootNode_index += escapeIndex;
/*  763: 763 */        curIndex += escapeIndex;
/*  764:     */      }
/*  765:     */    }
/*  766: 766 */    if (maxIterations < walkIterations) {
/*  767: 767 */      maxIterations = walkIterations;
/*  768:     */    }
/*  769:     */  }
/*  770:     */  
/*  771:     */  protected void walkRecursiveQuantizedTreeAgainstQueryAabb(QuantizedBvhNodes currentNodes, int currentNodeId, NodeOverlapCallback nodeCallback, long quantizedQueryAabbMin, long quantizedQueryAabbMax) {
/*  772: 772 */    assert (this.useQuantization);
/*  773:     */    
/*  777: 777 */    boolean aabbOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, currentNodes.getQuantizedAabbMin(currentNodeId), currentNodes.getQuantizedAabbMax(currentNodeId));
/*  778: 778 */    boolean isLeafNode = currentNodes.isLeafNode(currentNodeId);
/*  779:     */    
/*  780: 780 */    if (aabbOverlap) {
/*  781: 781 */      if (isLeafNode) {
/*  782: 782 */        nodeCallback.processNode(currentNodes.getPartId(currentNodeId), currentNodes.getTriangleIndex(currentNodeId));
/*  783:     */      }
/*  784:     */      else
/*  785:     */      {
/*  786: 786 */        int leftChildNodeId = currentNodeId + 1;
/*  787: 787 */        walkRecursiveQuantizedTreeAgainstQueryAabb(currentNodes, leftChildNodeId, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
/*  788:     */        
/*  789: 789 */        int rightChildNodeId = currentNodes.isLeafNode(leftChildNodeId) ? leftChildNodeId + 1 : leftChildNodeId + currentNodes.getEscapeIndex(leftChildNodeId);
/*  790: 790 */        walkRecursiveQuantizedTreeAgainstQueryAabb(currentNodes, rightChildNodeId, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
/*  791:     */      }
/*  792:     */    }
/*  793:     */  }
/*  794:     */  
/*  795:     */  protected void walkStacklessQuantizedTreeAgainstRay(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, int arg6, int arg7) {
/*  796: 796 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();assert (this.useQuantization);
/*  797:     */      
/*  798: 798 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  799:     */      
/*  800: 800 */      int curIndex = startNodeIndex;
/*  801: 801 */      int walkIterations = 0;
/*  802: 802 */      int subTreeSize = endNodeIndex - startNodeIndex;
/*  803:     */      
/*  804: 804 */      QuantizedBvhNodes rootNode = this.quantizedContiguousNodes;
/*  805: 805 */      int rootNode_idx = startNodeIndex;
/*  806:     */      
/*  809: 809 */      boolean boxBoxOverlap = false;
/*  810: 810 */      boolean rayBoxOverlap = false;
/*  811:     */      
/*  812: 812 */      float lambda_max = 1.0F;
/*  813:     */      
/*  815: 815 */      Vector3f rayFrom = localStack.get$javax$vecmath$Vector3f(raySource);
/*  816: 816 */      Vector3f rayDirection = localStack.get$javax$vecmath$Vector3f();
/*  817: 817 */      tmp.sub(rayTarget, raySource);
/*  818: 818 */      rayDirection.normalize(tmp);
/*  819: 819 */      lambda_max = rayDirection.dot(tmp);
/*  820: 820 */      rayDirection.x = (1.0F / rayDirection.x);
/*  821: 821 */      rayDirection.y = (1.0F / rayDirection.y);
/*  822: 822 */      rayDirection.z = (1.0F / rayDirection.z);
/*  823:     */      
/*  829: 829 */      Vector3f rayAabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
/*  830: 830 */      Vector3f rayAabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
/*  831: 831 */      VectorUtil.setMin(rayAabbMin, rayTarget);
/*  832: 832 */      VectorUtil.setMax(rayAabbMax, rayTarget);
/*  833:     */      
/*  835: 835 */      rayAabbMin.add(aabbMin);
/*  836: 836 */      rayAabbMax.add(aabbMax);
/*  837:     */      
/*  840: 840 */      long quantizedQueryAabbMin = quantizeWithClamp(rayAabbMin);
/*  841: 841 */      long quantizedQueryAabbMax = quantizeWithClamp(rayAabbMax);
/*  842:     */      
/*  843: 843 */      Vector3f bounds_0 = localStack.get$javax$vecmath$Vector3f();
/*  844: 844 */      Vector3f bounds_1 = localStack.get$javax$vecmath$Vector3f();
/*  845: 845 */      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  846: 846 */      float[] param = new float[1];
/*  847:     */      
/*  848: 848 */      while (curIndex < endNodeIndex)
/*  849:     */      {
/*  867: 867 */        assert (walkIterations < subTreeSize);
/*  868:     */        
/*  869: 869 */        walkIterations++;
/*  870:     */        
/*  871: 871 */        param[0] = 1.0F;
/*  872: 872 */        rayBoxOverlap = false;
/*  873: 873 */        boxBoxOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, rootNode.getQuantizedAabbMin(rootNode_idx), rootNode.getQuantizedAabbMax(rootNode_idx));
/*  874: 874 */        boolean isLeafNode = rootNode.isLeafNode(rootNode_idx);
/*  875: 875 */        if (boxBoxOverlap) {
/*  876: 876 */          unQuantize(bounds_0, rootNode.getQuantizedAabbMin(rootNode_idx));
/*  877: 877 */          unQuantize(bounds_1, rootNode.getQuantizedAabbMax(rootNode_idx));
/*  878:     */          
/*  879: 879 */          bounds_0.add(aabbMin);
/*  880: 880 */          bounds_1.add(aabbMax);
/*  881:     */          
/*  892: 892 */          rayBoxOverlap = AabbUtil2.rayAabb(raySource, rayTarget, bounds_0, bounds_1, param, normal);
/*  893:     */        }
/*  894:     */        
/*  896: 896 */        if ((isLeafNode) && (rayBoxOverlap)) {
/*  897: 897 */          nodeCallback.processNode(rootNode.getPartId(rootNode_idx), rootNode.getTriangleIndex(rootNode_idx));
/*  898:     */        }
/*  899:     */        
/*  900: 900 */        if ((rayBoxOverlap) || (isLeafNode)) {
/*  901: 901 */          rootNode_idx++;
/*  902: 902 */          curIndex++;
/*  903:     */        }
/*  904:     */        else {
/*  905: 905 */          int escapeIndex = rootNode.getEscapeIndex(rootNode_idx);
/*  906: 906 */          rootNode_idx += escapeIndex;
/*  907: 907 */          curIndex += escapeIndex;
/*  908:     */        }
/*  909:     */      }
/*  910:     */      
/*  911: 911 */      if (maxIterations < walkIterations)
/*  912: 912 */        maxIterations = walkIterations;
/*  913:     */    } finally {
/*  914: 914 */      localStack.pop$javax$vecmath$Vector3f();
/*  915:     */    } }
/*  916:     */  
/*  917: 917 */  protected void walkStacklessQuantizedTree(NodeOverlapCallback nodeCallback, long quantizedQueryAabbMin, long quantizedQueryAabbMax, int startNodeIndex, int endNodeIndex) { assert (this.useQuantization);
/*  918:     */    
/*  919: 919 */    int curIndex = startNodeIndex;
/*  920: 920 */    int walkIterations = 0;
/*  921: 921 */    int subTreeSize = endNodeIndex - startNodeIndex;
/*  922:     */    
/*  923: 923 */    QuantizedBvhNodes rootNode = this.quantizedContiguousNodes;
/*  924: 924 */    int rootNode_idx = startNodeIndex;
/*  925:     */    
/*  930: 930 */    while (curIndex < endNodeIndex)
/*  931:     */    {
/*  948: 948 */      assert (walkIterations < subTreeSize);
/*  949:     */      
/*  950: 950 */      walkIterations++;
/*  951: 951 */      boolean aabbOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, rootNode.getQuantizedAabbMin(rootNode_idx), rootNode.getQuantizedAabbMax(rootNode_idx));
/*  952: 952 */      boolean isLeafNode = rootNode.isLeafNode(rootNode_idx);
/*  953:     */      
/*  954: 954 */      if ((isLeafNode) && (aabbOverlap)) {
/*  955: 955 */        nodeCallback.processNode(rootNode.getPartId(rootNode_idx), rootNode.getTriangleIndex(rootNode_idx));
/*  956:     */      }
/*  957:     */      
/*  958: 958 */      if ((aabbOverlap) || (isLeafNode)) {
/*  959: 959 */        rootNode_idx++;
/*  960: 960 */        curIndex++;
/*  961:     */      }
/*  962:     */      else {
/*  963: 963 */        int escapeIndex = rootNode.getEscapeIndex(rootNode_idx);
/*  964: 964 */        rootNode_idx += escapeIndex;
/*  965: 965 */        curIndex += escapeIndex;
/*  966:     */      }
/*  967:     */    }
/*  968:     */    
/*  969: 969 */    if (maxIterations < walkIterations) {
/*  970: 970 */      maxIterations = walkIterations;
/*  971:     */    }
/*  972:     */  }
/*  973:     */  
/*  974:     */  public void reportRayOverlappingNodex(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3) {
/*  975: 975 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();boolean fast_path = (this.useQuantization) && (this.traversalMode == TraversalMode.STACKLESS);
/*  976: 976 */      if (fast_path) {
/*  977: 977 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  978: 978 */        tmp.set(0.0F, 0.0F, 0.0F);
/*  979: 979 */        walkStacklessQuantizedTreeAgainstRay(nodeCallback, raySource, rayTarget, tmp, tmp, 0, this.curNodeIndex);
/*  980:     */      }
/*  981:     */      else
/*  982:     */      {
/*  983: 983 */        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
/*  984: 984 */        Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
/*  985: 985 */        VectorUtil.setMin(aabbMin, rayTarget);
/*  986: 986 */        VectorUtil.setMax(aabbMax, rayTarget);
/*  987: 987 */        reportAabbOverlappingNodex(nodeCallback, aabbMin, aabbMax);
/*  988:     */      }
/*  989: 989 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  990:     */    } }
/*  991:     */  
/*  992: 992 */  public void reportBoxCastOverlappingNodex(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();boolean fast_path = (this.useQuantization) && (this.traversalMode == TraversalMode.STACKLESS);
/*  993: 993 */      if (fast_path) {
/*  994: 994 */        walkStacklessQuantizedTreeAgainstRay(nodeCallback, raySource, rayTarget, aabbMin, aabbMax, 0, this.curNodeIndex);
/*  996:     */      }
/*  997:     */      else
/*  998:     */      {
/*  999: 999 */        Vector3f qaabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
/* 1000:1000 */        Vector3f qaabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
/* 1001:1001 */        VectorUtil.setMin(qaabbMin, rayTarget);
/* 1002:1002 */        VectorUtil.setMax(qaabbMax, rayTarget);
/* 1003:1003 */        qaabbMin.add(aabbMin);
/* 1004:1004 */        qaabbMax.add(aabbMax);
/* 1005:1005 */        reportAabbOverlappingNodex(nodeCallback, qaabbMin, qaabbMax);
/* 1006:     */      }
/* 1007:1007 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 1008:     */    } }
/* 1009:     */  
/* 1010:1010 */  public long quantizeWithClamp(Vector3f arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();assert (this.useQuantization);
/* 1011:     */      
/* 1012:1012 */      Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
/* 1013:1013 */      VectorUtil.setMax(clampedPoint, this.bvhAabbMin);
/* 1014:1014 */      VectorUtil.setMin(clampedPoint, this.bvhAabbMax);
/* 1015:     */      
/* 1016:1016 */      Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 1017:1017 */      v.sub(clampedPoint, this.bvhAabbMin);
/* 1018:1018 */      VectorUtil.mul(v, v, this.bvhQuantization);
/* 1019:     */      
/* 1020:1020 */      int out0 = (int)(v.x + 0.5F) & 0xFFFF;
/* 1021:1021 */      int out1 = (int)(v.y + 0.5F) & 0xFFFF;
/* 1022:1022 */      int out2 = (int)(v.z + 0.5F) & 0xFFFF;
/* 1023:     */      
/* 1024:1024 */      return out0 | out1 << 16 | out2 << 32; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 1025:     */    }
/* 1026:     */  }
/* 1027:     */  
/* 1028:1028 */  public void unQuantize(Vector3f vecOut, long vecIn) { int vecIn0 = (int)(vecIn & 0xFFFF);
/* 1029:1029 */    int vecIn1 = (int)((vecIn & 0xFFFF0000) >>> 16);
/* 1030:1030 */    int vecIn2 = (int)((vecIn & 0x0) >>> 32);
/* 1031:     */    
/* 1032:1032 */    vecOut.x = (vecIn0 / this.bvhQuantization.x);
/* 1033:1033 */    vecOut.y = (vecIn1 / this.bvhQuantization.y);
/* 1034:1034 */    vecOut.z = (vecIn2 / this.bvhQuantization.z);
/* 1035:     */    
/* 1036:1036 */    vecOut.add(this.bvhAabbMin);
/* 1037:     */  }
/* 1038:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.OptimizedBvh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */