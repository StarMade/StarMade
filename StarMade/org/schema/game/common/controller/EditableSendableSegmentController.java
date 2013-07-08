/*    1:     */package org.schema.game.common.controller;
/*    2:     */
/*    3:     */import I;
/*    4:     */import ag;
/*    5:     */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*    6:     */import dj;
/*    7:     */import eH;
/*    8:     */import es;
/*    9:     */import ij;
/*   10:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   11:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   12:     */import jH;
/*   13:     */import jI;
/*   14:     */import jL;
/*   15:     */import jO;
/*   16:     */import jR;
/*   17:     */import jY;
/*   18:     */import java.io.IOException;
/*   19:     */import java.util.Collection;
/*   20:     */import java.util.HashMap;
/*   21:     */import java.util.HashSet;
/*   22:     */import java.util.List;
/*   23:     */import java.util.Set;
/*   24:     */import java.util.concurrent.ThreadPoolExecutor;
/*   25:     */import jq;
/*   26:     */import ju;
/*   27:     */import jv;
/*   28:     */import jw;
/*   29:     */import jx;
/*   30:     */import kI;
/*   31:     */import ka;
/*   32:     */import kd;
/*   33:     */import ki;
/*   34:     */import lA;
/*   35:     */import lP;
/*   36:     */import lT;
/*   37:     */import lc;
/*   38:     */import ln;
/*   39:     */import lz;
/*   40:     */import mx;
/*   41:     */import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*   42:     */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*   43:     */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*   44:     */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*   45:     */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*   46:     */import org.schema.game.common.data.element.BeamHandler;
/*   47:     */import org.schema.game.common.data.element.BeamHandler.BeamState;
/*   48:     */import org.schema.game.common.data.element.Element;
/*   49:     */import org.schema.game.common.data.element.ElementClassNotFoundException;
/*   50:     */import org.schema.game.common.data.element.ElementDocking;
/*   51:     */import org.schema.game.common.data.element.ElementInformation;
/*   52:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   53:     */import org.schema.game.common.data.physics.PhysicsExt;
/*   54:     */import org.schema.game.common.data.world.Universe;
/*   55:     */import org.schema.game.network.objects.remote.RemoteSegmentPiece;
/*   56:     */import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*   57:     */import org.schema.schine.network.NetworkStateContainer;
/*   58:     */import org.schema.schine.network.objects.Sendable;
/*   59:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   60:     */import org.schema.schine.network.server.ServerMessage;
/*   61:     */import vf;
/*   62:     */import x;
/*   63:     */import xe;
/*   64:     */import xq;
/*   65:     */
/*   66:     */public abstract class EditableSendableSegmentController extends ka implements jH
/*   67:     */{
/*   68:     */  private static final long MIN_TIME_BETWEEN_EDITS = 50L;
/*   69:     */  private long lastEditBlocks;
/*   70:  70 */  private final q tmpPos = new q();
/*   71:     */  
/*   72:  72 */  private final q absPosCache = new q();
/*   73:     */  
/*   74:     */  private boolean flagCharacterExitCheckByExplosion;
/*   75:     */  private Object flagCoreDestroyedByExplosion;
/*   76:  76 */  private final java.util.ArrayList explosionOrdersRunning = new java.util.ArrayList();
/*   77:     */  
/*   78:  78 */  private final q tmpVisPos = new q();
/*   79:     */  
/*   80:  80 */  public EditableSendableSegmentController(org.schema.schine.network.StateInterface paramStateInterface) { super(paramStateInterface); }
/*   81:     */  
/*   82:     */  public boolean allowedType(short paramShort)
/*   83:     */  {
/*   84:  84 */    if (!ElementKeyMap.getInfo(paramShort).isPlacable()) {
/*   85:  85 */      if (!isOnServer()) {
/*   86:  86 */        if (1 == paramShort) {
/*   87:  87 */          ((ct)getState()).a().b("ERROR\nCore Elements cannot be placed\nthey are used to spawn new ships");
/*   89:     */        }
/*   90:     */        else
/*   91:     */        {
/*   92:  92 */          ((ct)getState()).a().b("ERROR\nThis Element cannot be placed.\nhow did you even get that...");
/*   93:     */        }
/*   94:     */      }
/*   95:     */      
/*   98:  98 */      return false;
/*   99:     */    }
/*  100: 100 */    return true;
/*  101:     */  }
/*  102:     */  
/*  103:     */  private void checkCharacterExit() {
/*  104: 104 */    System.err.println("[SegController] CHECKING CHARACTER EXIT");
/*  105: 105 */    java.util.Iterator localIterator; if ((this instanceof cw)) {
/*  106: 106 */      for (localIterator = ((cw)this).a().iterator(); localIterator.hasNext();) {
/*  107: 107 */        ((lE)localIterator.next()).a().d();
/*  108:     */      }
/*  109:     */    }
/*  110:     */  }
/*  111:     */  
/*  112:     */  public boolean isEmptyOnServer() {
/*  113: 113 */    int i = 1;
/*  114: 114 */    for (java.util.Iterator localIterator = ((vg)getState()).b().values().iterator(); localIterator.hasNext();) {
/*  115: 115 */      if (((lE)localIterator.next()).c() == getSectorId()) {
/*  116: 116 */        i = 0;
/*  117: 117 */        break;
/*  118:     */      }
/*  119:     */    }
/*  120: 120 */    if (i != 0) {
/*  121: 121 */      return false;
/*  122:     */    }
/*  123:     */    
/*  125: 125 */    q localq2 = new q(getMaxPos());
/*  126:     */    
/*  128: 128 */    q localq1 = new q(getMinPos());
/*  129:     */    
/*  130: 130 */    System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localq1 + " TO " + localq2 + ": " + this);
/*  131: 131 */    for (int j = localq1.c; j <= localq2.c; j++) {
/*  132: 132 */      for (int k = localq1.jdField_b_of_type_Int; k <= localq2.jdField_b_of_type_Int; k++) {
/*  133: 133 */        for (int m = localq1.jdField_a_of_type_Int; m <= localq2.jdField_a_of_type_Int; m++) {
/*  134:     */          Object localObject;
/*  135: 135 */          (localObject = new q()).jdField_a_of_type_Int = ((m << 4) + 8);
/*  136: 136 */          ((q)localObject).jdField_b_of_type_Int = ((k << 4) + 8);
/*  137: 137 */          ((q)localObject).c = ((j << 4) + 8);
/*  138:     */          
/*  139: 139 */          if (!(localObject = getSegmentBuffer().a((q)localObject, true)).a().g()) {
/*  140: 140 */            System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localq1 + " TO " + localq2 + ": " + this + " --- IS NOT EMPTY " + ((le)localObject).a());
/*  141: 141 */            return false;
/*  142:     */          }
/*  143:     */        }
/*  144:     */      }
/*  145:     */    }
/*  146: 146 */    System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localq1 + " TO " + localq2 + ": " + this + " --- IS EMPTY");
/*  147: 147 */    return true;
/*  148:     */  }
/*  149:     */  
/*  150:     */  public int damageElement(short paramShort, int paramInt1, org.schema.game.common.data.world.SegmentData paramSegmentData, int paramInt2) {
/*  151: 151 */    try { paramShort = ElementKeyMap.getInfo(paramShort);
/*  152: 152 */      paramShort = (int)Math.max(0.0D, paramInt2 - Math.ceil(paramInt2 * paramShort.getArmourPercent()));
/*  153: 153 */      paramSegmentData.setHitpoints(paramInt1, (short)Math.max(0, paramSegmentData.getHitpoints(paramInt1) - paramShort));
/*  154: 154 */      return paramShort;
/*  155: 155 */    } catch (ElementClassNotFoundException localElementClassNotFoundException) { localElementClassNotFoundException.printStackTrace();
/*  156:     */      
/*  157: 157 */      System.err.println("[WARNING] Exception catched! returning zero damage"); }
/*  158: 158 */    return 0;
/*  159:     */  }
/*  160:     */  
/*  161:     */  public void destroy() {
/*  162: 162 */    System.out.println("[SEGMENTCONTROLLER] ENTITY " + this + " HAS BEEN DESTROYED... ");
/*  163: 163 */    markedForPermanentDelete(true);
/*  164: 164 */    setMarkedForDeleteVolatile(true);
/*  165:     */  }
/*  166:     */  
/*  167:     */  public void doDimExtensionIfNecessary(org.schema.game.common.data.world.Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3) {
/*  168: 168 */    paramSegment.a(paramByte1, paramByte2, paramByte3, this.absPosCache);
/*  169:     */    
/*  171: 171 */    if (paramByte1 == 0) {
/*  172: 172 */      org.schema.game.common.data.world.Segment.a(this.absPosCache.jdField_a_of_type_Int - 1, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c, this.tmpPos);
/*  173: 173 */      extendDim(0, this.absPosCache, this.tmpPos, -1, 0, 0);
/*  174:     */    }
/*  175: 175 */    if (paramByte2 == 0) {
/*  176: 176 */      org.schema.game.common.data.world.Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int - 1, this.absPosCache.c, this.tmpPos);
/*  177: 177 */      extendDim(1, this.absPosCache, this.tmpPos, 0, -1, 0);
/*  178:     */    }
/*  179: 179 */    if (paramByte3 == 0) {
/*  180: 180 */      org.schema.game.common.data.world.Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c - 1, this.tmpPos);
/*  181: 181 */      extendDim(2, this.absPosCache, this.tmpPos, 0, 0, -1);
/*  182:     */    }
/*  183:     */    
/*  184: 184 */    if (paramByte1 == 15) {
/*  185: 185 */      org.schema.game.common.data.world.Segment.a(this.absPosCache.jdField_a_of_type_Int + 1, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c, this.tmpPos);
/*  186: 186 */      extendDim(0, this.absPosCache, this.tmpPos, 1, 0, 0);
/*  187:     */    }
/*  188: 188 */    if (paramByte2 == 15) {
/*  189: 189 */      org.schema.game.common.data.world.Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int + 1, this.absPosCache.c, this.tmpPos);
/*  190: 190 */      extendDim(1, this.absPosCache, this.tmpPos, 0, 1, 0);
/*  191:     */    }
/*  192: 192 */    if (paramByte3 == 15) {
/*  193: 193 */      org.schema.game.common.data.world.Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c + 1, this.tmpPos);
/*  194: 194 */      extendDim(2, this.absPosCache, this.tmpPos, 0, 0, 1);
/*  195:     */    }
/*  196:     */  }
/*  197:     */  
/*  203:     */  public void extendDim(int paramInt1, q paramq1, q paramq2, int paramInt2, int paramInt3, int paramInt4)
/*  204:     */  {
/*  205: 205 */    if (!isInboundCoord(paramInt1, paramq2))
/*  206:     */    {
/*  208: 208 */      getMaxPos().jdField_a_of_type_Int += (paramInt2 > 0 ? paramInt2 : 0);
/*  209: 209 */      getMaxPos().jdField_b_of_type_Int += (paramInt3 > 0 ? paramInt3 : 0);
/*  210: 210 */      getMaxPos().c += (paramInt4 > 0 ? paramInt4 : 0);
/*  211:     */      
/*  212: 212 */      getMinPos().jdField_a_of_type_Int += (paramInt2 < 0 ? paramInt2 : 0);
/*  213: 213 */      getMinPos().jdField_b_of_type_Int += (paramInt3 < 0 ? paramInt3 : 0);
/*  214: 214 */      getMinPos().c += (paramInt4 < 0 ? paramInt4 : 0);
/*  215:     */    }
/*  216:     */  }
/*  217:     */  
/*  219:     */  private void forceCharacterExit(le paramle)
/*  220:     */  {
/*  221: 221 */    synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  222: 222 */      for (java.util.Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/*  223: 223 */        if (((localSendable = (Sendable)localIterator.next()) instanceof lE))
/*  224:     */        {
/*  225: 225 */          ((lE)localSendable).a(paramle);
/*  226:     */        }
/*  227:     */      }
/*  228: 228 */      return;
/*  229:     */    } }
/*  230:     */  
/*  231: 231 */  protected short getCoreType() { return 1; }
/*  232:     */  
/*  241:     */  public void getNearestIntersectingElementPosition(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, q paramq, float paramFloat, ah paramah, az paramaz)
/*  242:     */  {
/*  243: 243 */    if (System.currentTimeMillis() - this.lastEditBlocks < 50L) {
/*  244: 244 */      return;
/*  245:     */    }
/*  246: 246 */    Object localObject1 = new q();
/*  247:     */    
/*  248: 248 */    if ((paramVector3f1 = getNearestPiece(paramVector3f1, paramVector3f2, paramFloat, (q)localObject1, paramq)) == null) {
/*  249: 249 */      System.err.println("[SEGCONTROLLER][ELEMENT][REMOVE] NO NEAREST PIECE FOUND");
/*  250: 250 */      return;
/*  251:     */    }
/*  252: 252 */    paramVector3f2 = paramq.a(1, 1, 1);
/*  253:     */    
/*  259: 259 */    paramq = Math.min((paramVector3f1 = paramVector3f1.a(new q())).jdField_a_of_type_Int, paramVector3f1.jdField_a_of_type_Int + ((q)localObject1).jdField_a_of_type_Int);
/*  260: 260 */    paramFloat = Math.min(paramVector3f1.jdField_b_of_type_Int, paramVector3f1.jdField_b_of_type_Int + ((q)localObject1).jdField_b_of_type_Int);
/*  261: 261 */    Object localObject2 = Math.min(paramVector3f1.c, paramVector3f1.c + ((q)localObject1).c);
/*  262:     */    
/*  263: 263 */    int j = Math.max(paramVector3f1.jdField_a_of_type_Int, paramVector3f1.jdField_a_of_type_Int + ((q)localObject1).jdField_a_of_type_Int);
/*  264: 264 */    int k = Math.max(paramVector3f1.jdField_b_of_type_Int, paramVector3f1.jdField_b_of_type_Int + ((q)localObject1).jdField_b_of_type_Int);
/*  265: 265 */    Object localObject3 = Math.max(paramVector3f1.c, paramVector3f1.c + ((q)localObject1).c);
/*  266:     */    
/*  267: 267 */    if (j == paramVector3f1.jdField_a_of_type_Int) {
/*  268: 268 */      paramq++;
/*  269: 269 */      j++;
/*  270:     */    }
/*  271: 271 */    if (k == paramVector3f1.jdField_b_of_type_Int) {
/*  272: 272 */      paramFloat++;
/*  273: 273 */      k++;
/*  274:     */    }
/*  275: 275 */    if (localObject3 == paramVector3f1.c) {
/*  276: 276 */      localObject2++;
/*  277: 277 */      localObject3++;
/*  278:     */    }
/*  279: 279 */    if (paramaz.jdField_a_of_type_Int > 0) {
/*  280: 280 */      paramaz.jdField_a_of_type_Int = 0;
/*  281: 281 */      return;
/*  282:     */    }
/*  283:     */    
/*  292: 292 */    System.err.println("[SEGCONTROLLER][ELEMENT][REMOVE] REMOVING BLOCKS: SIZE: " + localObject1);
/*  293:     */    
/*  294: 294 */    paramVector3f1 = new HashSet(8);
/*  295:     */    
/*  296: 296 */    long l = System.currentTimeMillis();
/*  297:     */    
/*  298: 298 */    for (localObject1 = localObject2; localObject1 < localObject3; localObject1++) {
/*  299: 299 */      for (int i = paramFloat; i < k; i++) {
/*  300: 300 */        for (int m = paramq; m < j; m++) {
/*  301: 301 */          remove(m, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  302:     */          
/*  304: 304 */          if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*  305:     */          {
/*  307: 307 */            Object localObject8 = (paramaz.jdField_a_of_type_Q.c - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  308:     */            
/*  309: 309 */            remove(m, i, localObject1 + localObject8, paramah, paramVector3f2, paramVector3f1);
/*  310:     */          }
/*  311: 311 */          else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*  312:     */          {
/*  314: 314 */            int i4 = (paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int - i << 1) + paramaz.jdField_b_of_type_Int;
/*  315:     */            
/*  316: 316 */            remove(m, i + i4, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  317:     */          }
/*  318: 318 */          else if ((!paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  319:     */          {
/*  322: 322 */            int i5 = (paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int - m << 1) + paramaz.jdField_b_of_type_Int;
/*  323:     */            
/*  324: 324 */            remove(m + i5, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  326:     */          }
/*  327: 327 */          else if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*  328:     */          {
/*  329: 329 */            Object localObject4 = paramaz.jdField_a_of_type_Q.c;
/*  330: 330 */            int i6 = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  331:     */            
/*  332: 332 */            Object localObject9 = (localObject4 - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  333: 333 */            int n = (i6 - i << 1) + paramaz.jdField_b_of_type_Int;
/*  334:     */            
/*  335: 335 */            remove(m, i, localObject1 + localObject9, paramah, paramVector3f2, paramVector3f1);
/*  336: 336 */            remove(m, i + n, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  337: 337 */            remove(m, i + n, localObject1 + localObject9, paramah, paramVector3f2, paramVector3f1);
/*  339:     */          }
/*  340: 340 */          else if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  341:     */          {
/*  342: 342 */            Object localObject5 = paramaz.jdField_a_of_type_Q.c;
/*  343: 343 */            int i7 = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*  344:     */            
/*  345: 345 */            Object localObject10 = (localObject5 - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  346: 346 */            int i1 = (i7 - m << 1) + paramaz.jdField_b_of_type_Int;
/*  347:     */            
/*  348: 348 */            remove(m, i, localObject1 + localObject10, paramah, paramVector3f2, paramVector3f1);
/*  349: 349 */            remove(m + i1, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  350: 350 */            remove(m + i1, i, localObject1 + localObject10, paramah, paramVector3f2, paramVector3f1);
/*  352:     */          }
/*  353: 353 */          else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  354:     */          {
/*  355: 355 */            int i2 = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  356: 356 */            int i8 = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*  357:     */            
/*  358: 358 */            int i11 = (i2 - i << 1) + paramaz.jdField_b_of_type_Int;
/*  359: 359 */            int i3 = (i8 - m << 1) + paramaz.jdField_b_of_type_Int;
/*  360:     */            
/*  361: 361 */            remove(m, i + i11, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  362: 362 */            remove(m + i3, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  363: 363 */            remove(m + i3, i + i11, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  365:     */          }
/*  366: 366 */          else if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  367:     */          {
/*  368: 368 */            Object localObject6 = paramaz.jdField_a_of_type_Q.c;
/*  369: 369 */            int i9 = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  370: 370 */            int i12 = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*  371:     */            
/*  372: 372 */            Object localObject7 = (localObject6 - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  373: 373 */            int i10 = (i9 - i << 1) + paramaz.jdField_b_of_type_Int;
/*  374: 374 */            int i13 = (i12 - m << 1) + paramaz.jdField_b_of_type_Int;
/*  375:     */            
/*  376: 376 */            remove(m + i13, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  377: 377 */            remove(m, i + i10, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  378: 378 */            remove(m, i, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*  379:     */            
/*  380: 380 */            remove(m + i13, i + i10, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  381: 381 */            remove(m + i13, i, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*  382: 382 */            remove(m, i + i10, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*  383:     */            
/*  384: 384 */            remove(m + i13, i + i10, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*  385:     */          }
/*  386:     */        }
/*  387:     */      }
/*  388:     */    }
/*  389:     */    
/*  392: 392 */    System.err.println("[SEGMENTCONTROLLER] REMOVAL TOOK " + (System.currentTimeMillis() - l));
/*  393:     */    
/*  394: 394 */    if (paramVector3f2 == 0) {
/*  395: 395 */      for (localObject1 = paramVector3f1.iterator(); ((java.util.Iterator)localObject1).hasNext();) { org.schema.game.common.data.world.Segment localSegment;
/*  396: 396 */        if (!(localSegment = (org.schema.game.common.data.world.Segment)((java.util.Iterator)localObject1).next()).g()) {
/*  397: 397 */          localSegment.a().restructBB(true);
/*  398:     */        }
/*  399:     */      }
/*  400:     */    }
/*  401:     */  }
/*  402:     */  
/*  403:     */  private void remove(int paramInt1, int paramInt2, int paramInt3, ah paramah, boolean paramBoolean, HashSet paramHashSet)
/*  404:     */  {
/*  405: 405 */    if (((paramInt1 = getSegmentBuffer().a(new q(paramInt1, paramInt2, paramInt3), false)) != null) && (paramInt1.a() != 0)) {
/*  406: 406 */      paramInt2 = paramInt1.a();
/*  407: 407 */      paramInt3 = 0;
/*  408: 408 */      if (((1 == paramInt2) || (((this instanceof ki)) && (getTotalElements() == 1))) && (
/*  409: 409 */        (paramInt1.a(this.tmpVisPos).equals(kd.jdField_a_of_type_Q)) || ((this instanceof ki)))) {
/*  410: 410 */        paramInt3 = 1;
/*  411: 411 */        if (!isOnServer()) {
/*  412: 412 */          if (getTotalElements() == 1)
/*  413:     */          {
/*  414: 414 */            String str = (this instanceof kd) ? "Are you sure you want to delete the core?\n\nThis will destroy the ship,\nand you will get the core.\n" : "Are you sure you want to delete the last block?\n\nThis will destroy the station!\n\nNo refunds!!";
/*  415:     */            
/*  418: 418 */            new jw(this, (ct)getState(), "Delete last block?", str, paramInt1, paramInt2, paramah, paramBoolean)
/*  419:     */            
/*  454: 454 */              .c();
/*  455:     */          } else {
/*  456: 456 */            ((ct)getState()).a().d("Cannot delete core!\nIt must be the last\nelement to delete");
/*  457:     */          }
/*  458:     */        }
/*  459:     */      }
/*  460:     */      
/*  461: 461 */      if (paramInt3 == 0) {
/*  462: 462 */        removeBlock(paramInt1, paramInt2, paramah, paramBoolean);
/*  463: 463 */        paramHashSet.add(paramInt1.a());
/*  464:     */      }
/*  465:     */    }
/*  466:     */  }
/*  467:     */  
/*  476:     */  private short removeBlock(le paramle, short paramShort, ah paramah, boolean paramBoolean)
/*  477:     */  {
/*  478: 478 */    if (paramle.a().a(paramle.a(this.tmpLocalPos), paramBoolean)) {
/*  479: 479 */      this.lastEditBlocks = System.currentTimeMillis();
/*  480: 480 */      ((mw)paramle.a()).a(System.currentTimeMillis());
/*  481:     */    }
/*  482: 482 */    paramle.a();
/*  483: 483 */    paramle.a(getState().getId());
/*  484: 484 */    getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(paramle, this, getNetworkObject()));
/*  485: 485 */    assert (getNetworkObject().modificationBuffer.hasChanged());
/*  486: 486 */    assert (getNetworkObject().isChanged());
/*  487:     */    
/*  489: 489 */    paramah.a(paramShort);
/*  490:     */    
/*  492: 492 */    return paramShort;
/*  493:     */  }
/*  494:     */  
/*  498:     */  public int getNearestIntersection(short paramShort, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, ag paramag, int paramInt1, boolean paramBoolean, ju paramju, q paramq, int paramInt2, float paramFloat, az paramaz)
/*  499:     */  {
/*  500: 500 */    if ((paramShort == 291) && (getElementClassCountMap().a((short)291) > 0)) {
/*  501: 501 */      if (!isOnServer()) {
/*  502: 502 */        ((ct)getState()).a().b("ERROR\nOnly one Faction block is permitted\nper structure");
/*  503:     */      }
/*  504:     */      
/*  507: 507 */      return 0;
/*  508:     */    }
/*  509: 509 */    if (!allowedType(paramShort)) {
/*  510: 510 */      System.err.println("Type is not allowed on " + this + "; " + paramShort);
/*  511: 511 */      return 0;
/*  512:     */    }
/*  513:     */    
/*  514: 514 */    if (System.currentTimeMillis() - this.lastEditBlocks < 50L) {
/*  515: 515 */      return 0;
/*  516:     */    }
/*  517:     */    
/*  518: 518 */    q localq1 = new q();
/*  519: 519 */    le localle = null;
/*  520: 520 */    q localq2 = new q();
/*  521:     */    
/*  522:     */    try
/*  523:     */    {
/*  524: 524 */      localle = getNextToNearestPiece(paramVector3f1, paramVector3f2, localq2, paramFloat, paramBoolean, paramq, localq1);
/*  525:     */      
/*  526: 526 */      if ((paramaz.jdField_a_of_type_Int > 0) && (localle != null)) {
/*  527: 527 */        paramVector3f1 = localle.a(new q());
/*  528: 528 */        switch (paramaz.jdField_a_of_type_Int) {
/*  529:     */        case 1: 
/*  530: 530 */          System.err.println("SYM XY PLANE SET");
/*  531: 531 */          paramaz.jdField_a_of_type_Q.c = paramVector3f1.c;
/*  532: 532 */          paramaz.jdField_a_of_type_Boolean = true;
/*  533: 533 */          break;
/*  534:     */        case 2: 
/*  535: 535 */          System.err.println("SYM XZ PLANE SET");
/*  536: 536 */          paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int = paramVector3f1.jdField_b_of_type_Int;
/*  537: 537 */          paramaz.jdField_b_of_type_Boolean = true;
/*  538: 538 */          break;
/*  539:     */        case 4: 
/*  540: 540 */          System.err.println("SYM YZ PLANE SET");
/*  541: 541 */          paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int = paramVector3f1.jdField_a_of_type_Int;
/*  542: 542 */          paramaz.jdField_c_of_type_Boolean = true;
/*  543:     */        }
/*  544:     */        
/*  545: 545 */        paramaz.jdField_a_of_type_Int = 0;
/*  546: 546 */        return 0;
/*  547:     */      }
/*  548:     */      
/*  549: 549 */      System.err.println("[CLIENT][EDIT] PLACING AT " + localle + "; size: " + paramq + " --> " + localq1 + ": PHY: " + (localle != null ? localle.a().a().getPhysicsDataContainer().getObject() : ""));
/*  550:     */    }
/*  551:     */    catch (CannotImmediateRequestOnClientException paramVector3f1) {
/*  552: 552 */      System.err.println("[CLIENT][WARNING] Cannot ADD! segment not yet in buffer " + paramVector3f1.a() + ". -> requested");
/*  553: 553 */      return 0;
/*  554:     */    }
/*  555:     */    
/*  556: 556 */    if (localle != null) {
/*  557: 557 */      if (paramju != null) { paramVector3f2 = localle.a(new q()); if (paramVector3f2.jdField_a_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[0]) System.err.println("X valid " + paramVector3f2.jdField_a_of_type_Int + "/" + paramVector3f1.jdField_a_of_type_ArrayOfInt[0] + " "); if (paramVector3f2.jdField_a_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[0]) { if (paramVector3f2.jdField_b_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[1]) System.err.println("Y valid " + paramVector3f2.jdField_b_of_type_Int + "/" + paramVector3f1.jdField_a_of_type_ArrayOfInt[1] + " "); if (paramVector3f2.jdField_b_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[1]) if (paramVector3f1.jdField_a_of_type_ArrayOfBoolean[2] != 0) if (paramVector3f2.c != paramVector3f1.jdField_a_of_type_ArrayOfInt[2]) System.err.println("Z valid " + paramVector3f2.c + "/" + paramVector3f1.jdField_a_of_type_ArrayOfInt[2] + " "); } if ((paramVector3f2.c != paramVector3f1.jdField_a_of_type_ArrayOfInt[2] ? 0 : paramVector3f1.jdField_a_of_type_ArrayOfBoolean[1] != 0 ? 0 : (paramVector3f1 = paramju).jdField_a_of_type_ArrayOfBoolean[0] != 0 ? 0 : 1) == 0)
/*  558: 558 */          return 0;
/*  559:     */      }
/*  560: 560 */      if (localle.a().g())
/*  561:     */      {
/*  562: 562 */        getSegmentProvider().a().assignData(localle.a());
/*  563:     */      }
/*  564:     */      
/*  565: 565 */      System.err.println("adding new element to " + getClass().getSimpleName() + " at " + localle + ", type " + paramShort);
/*  566: 566 */      paramVector3f1 = new int[2];
/*  567:     */      
/*  568: 568 */      paramVector3f2 = localle.a(new q());
/*  569: 569 */      paramVector3f1[1] = paramInt2;
/*  570:     */      
/*  571: 571 */      paramju = localq1.jdField_a_of_type_Int < 0 ? paramVector3f2.jdField_a_of_type_Int + localq1.jdField_a_of_type_Int + 1 : paramVector3f2.jdField_a_of_type_Int;
/*  572: 572 */      paramq = localq1.jdField_b_of_type_Int < 0 ? paramVector3f2.jdField_b_of_type_Int + localq1.jdField_b_of_type_Int + 1 : paramVector3f2.jdField_b_of_type_Int;
/*  573: 573 */      paramInt2 = localq1.c < 0 ? paramVector3f2.c + localq1.c + 1 : paramVector3f2.c;
/*  574:     */      
/*  575: 575 */      paramFloat = localq1.jdField_a_of_type_Int < 0 ? paramVector3f2.jdField_a_of_type_Int + 1 : paramVector3f2.jdField_a_of_type_Int + localq1.jdField_a_of_type_Int;
/*  576: 576 */      int j = localq1.jdField_b_of_type_Int < 0 ? paramVector3f2.jdField_b_of_type_Int + 1 : paramVector3f2.jdField_b_of_type_Int + localq1.jdField_b_of_type_Int;
/*  577: 577 */      paramVector3f2 = localq1.c < 0 ? paramVector3f2.c + 1 : paramVector3f2.c + localq1.c;
/*  578: 580 */      for (; 
/*  579:     */          
/*  580: 580 */          (paramInt2 < paramVector3f2) && (paramVector3f1[1] > 0); paramInt2++) {
/*  581: 581 */        for (int i = paramq; (i < j) && (paramVector3f1[1] > 0); i++) {
/*  582: 582 */          for (float f = paramju; (f < paramFloat) && (paramVector3f1[1] > 0); f++)
/*  583:     */          {
/*  584: 584 */            build(f, i, paramInt2, paramShort, paramInt1, paramBoolean, paramag, localq2, paramVector3f1);
/*  585:     */            
/*  599:     */            int m;
/*  600:     */            
/*  613: 613 */            if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*  614:     */            {
/*  616: 616 */              m = (paramaz.jdField_a_of_type_Q.c - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  617:     */              
/*  618: 618 */              build(f, i, paramInt2 + m, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  619:     */            }
/*  620: 620 */            else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*  621:     */            {
/*  623: 623 */              m = (paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int - i << 1) + paramaz.jdField_b_of_type_Int;
/*  624:     */              
/*  625: 625 */              build(f, i + m, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  626:     */            }
/*  627: 627 */            else if ((!paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  628:     */            {
/*  631: 631 */              m = (paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int - f << 1) + paramaz.jdField_b_of_type_Int;
/*  632:     */              
/*  633: 633 */              build(f + m, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  634:     */            } else { int k;
/*  635:     */              int n;
/*  636: 636 */              if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*  637:     */              {
/*  638: 638 */                k = paramaz.jdField_a_of_type_Q.c;
/*  639: 639 */                m = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  640:     */                
/*  641: 641 */                n = (k - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  642: 642 */                k = (m - i << 1) + paramaz.jdField_b_of_type_Int;
/*  643:     */                
/*  644: 644 */                build(f, i, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  645: 645 */                build(f, i + k, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  646: 646 */                build(f, i + k, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  648:     */              }
/*  649: 649 */              else if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  650:     */              {
/*  651: 651 */                k = paramaz.jdField_a_of_type_Q.c;
/*  652: 652 */                m = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*  653:     */                
/*  654: 654 */                n = (k - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  655: 655 */                k = (m - f << 1) + paramaz.jdField_b_of_type_Int;
/*  656:     */                
/*  657: 657 */                build(f, i, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  658: 658 */                build(f + k, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  659: 659 */                build(f + k, i, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  661:     */              }
/*  662: 662 */              else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  663:     */              {
/*  664: 664 */                k = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  665: 665 */                m = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*  666:     */                
/*  667: 667 */                n = (k - i << 1) + paramaz.jdField_b_of_type_Int;
/*  668: 668 */                k = (m - f << 1) + paramaz.jdField_b_of_type_Int;
/*  669:     */                
/*  670: 670 */                build(f, i + n, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  671: 671 */                build(f + k, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  672: 672 */                build(f + k, i + n, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  674:     */              }
/*  675: 675 */              else if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*  676:     */              {
/*  677: 677 */                k = paramaz.jdField_a_of_type_Q.c;
/*  678: 678 */                m = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  679: 679 */                n = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*  680:     */                
/*  681: 681 */                k = (k - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  682: 682 */                m = (m - i << 1) + paramaz.jdField_b_of_type_Int;
/*  683: 683 */                n = (n - f << 1) + paramaz.jdField_b_of_type_Int;
/*  684:     */                
/*  685: 685 */                build(f + n, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  686: 686 */                build(f, i + m, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  687: 687 */                build(f, i, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  688:     */                
/*  689: 689 */                build(f + n, i + m, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  690: 690 */                build(f + n, i, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  691: 691 */                build(f, i + m, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  692:     */                
/*  693: 693 */                build(f + n, i + m, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, true, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  694:     */              }
/*  695:     */            }
/*  696:     */          }
/*  697:     */        }
/*  698:     */      }
/*  699:     */      
/*  702: 702 */      return paramVector3f1[0];
/*  703:     */    }
/*  704: 704 */    System.err.println("no intersection found in world currentSegmentContext");
/*  705:     */    
/*  707: 707 */    return 0;
/*  708:     */  }
/*  709:     */  
/*  710:     */  private void build(int paramInt1, int paramInt2, int paramInt3, short paramShort, int paramInt4, boolean paramBoolean, ag paramag, q paramq, int[] paramArrayOfInt) {
/*  711: 711 */    if (paramArrayOfInt[1] > 0)
/*  712:     */    {
/*  713: 713 */      if ((paramInt1 = getSegmentBuffer().a(new q(paramInt1, paramInt2, paramInt3), false)) != null)
/*  714:     */      {
/*  716: 716 */        paramInt2 = 0; if (paramInt1.a().a(paramShort, paramInt1.a(this.tmpLocalPos), paramInt4, paramBoolean)) {
/*  717: 717 */          this.lastEditBlocks = System.currentTimeMillis();
/*  718: 718 */          ((mw)paramInt1.a()).a(System.currentTimeMillis());
/*  719:     */          
/*  720: 720 */          paramInt1.a();
/*  721: 721 */          paramInt2 = paramInt1.a(new q());
/*  722:     */          
/*  723: 723 */          paramag.a(paramInt2, paramq, paramShort);
/*  724:     */          
/*  725: 725 */          paramInt1.a(getState().getId());
/*  726:     */          
/*  727: 727 */          getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(paramInt1, this, getNetworkObject()));
/*  728:     */          
/*  730: 730 */          assert (getNetworkObject().modificationBuffer.hasChanged());
/*  731: 731 */          assert (getNetworkObject().isChanged());
/*  732: 732 */          paramArrayOfInt[0] += 1;
/*  733: 733 */          paramArrayOfInt[1] -= 1;
/*  734: 734 */          return; }
/*  735: 735 */        System.err.println("Block at " + paramInt1 + " already exists");
/*  736:     */      }
/*  737:     */    }
/*  738:     */  }
/*  739:     */  
/*  742:     */  public le getNearestPiece(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat, q paramq1, q paramq2)
/*  743:     */  {
/*  744: 744 */    javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f();
/*  745: 745 */    paramVector3f2.scale(paramFloat);
/*  746:     */    
/*  747: 747 */    localVector3f.add(paramVector3f1, paramVector3f2);
/*  748:     */    
/*  752: 752 */    (
/*  753:     */    
/*  754: 754 */      paramVector3f2 = new org.schema.game.common.data.physics.CubeRayCastResult(paramVector3f1, localVector3f, Boolean.valueOf(false), this)).setRespectShields(false);
/*  755:     */    
/*  756: 756 */    paramVector3f2.onlyCubeMeshes = true;
/*  757: 757 */    paramVector3f2.setIgnoereNotPhysical(true);
/*  758:     */    
/*  763: 763 */    if (((paramVector3f1 = getPhysics().testRayCollisionPoint(paramVector3f1, localVector3f, paramVector3f2, false)).hasHit()) && (paramVector3f1.collisionObject != null) && 
/*  764: 764 */      ((paramVector3f1 instanceof org.schema.game.common.data.physics.CubeRayCastResult)) && (((org.schema.game.common.data.physics.CubeRayCastResult)paramVector3f1).getSegment() != null))
/*  765:     */    {
/*  767: 767 */      (paramVector3f1 = (org.schema.game.common.data.physics.CubeRayCastResult)paramVector3f1).getSegment().a().getSegmentController();
/*  768: 768 */      paramVector3f2 = paramVector3f1.getSegment();
/*  769:     */      
/*  779: 779 */      (paramFloat = new q(paramVector3f1.getSegment().jdField_a_of_type_Q.jdField_a_of_type_Int, paramVector3f1.getSegment().jdField_a_of_type_Q.jdField_b_of_type_Int, paramVector3f1.getSegment().jdField_a_of_type_Q.c)).jdField_a_of_type_Int += paramVector3f1.cubePos.a - 8;
/*  780: 780 */      paramFloat.jdField_b_of_type_Int += paramVector3f1.cubePos.b - 8;
/*  781: 781 */      paramFloat.c += paramVector3f1.cubePos.c - 8;
/*  782:     */      
/*  791: 791 */      getWorldTransformInverse().transform(paramVector3f1.hitPointWorld);
/*  792:     */      
/*  796: 796 */      int i = Element.getSide(paramVector3f1.hitPointWorld, paramFloat);
/*  797:     */      
/*  798: 798 */      System.err.println("SIDE: " + Element.getSideString(i) + ": " + paramVector3f1.hitPointWorld + "; " + paramFloat);
/*  799:     */      
/*  801: 801 */      paramq2.jdField_a_of_type_Int = (-paramq2.jdField_a_of_type_Int);
/*  802: 802 */      paramq2.jdField_b_of_type_Int = (-paramq2.jdField_b_of_type_Int);
/*  803: 803 */      paramq2.c = (-paramq2.c);
/*  804:     */      
/*  805: 805 */      switch (i) {
/*  806:     */      case 0: 
/*  807: 807 */        paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  808: 808 */        break;
/*  809:     */      case 1: 
/*  810: 810 */        paramq1.b(-paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  811: 811 */        break;
/*  812:     */      case 2: 
/*  813: 813 */        paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  814: 814 */        break;
/*  815:     */      case 3: 
/*  816: 816 */        paramq1.b(paramq2.jdField_a_of_type_Int, -paramq2.jdField_b_of_type_Int, paramq2.c);
/*  817: 817 */        break;
/*  818:     */      case 4: 
/*  819: 819 */        paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  820: 820 */        break;
/*  821:     */      case 5: 
/*  822: 822 */        paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, -paramq2.c);
/*  823: 823 */        break;
/*  824: 824 */      default:  System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
/*  825:     */      }
/*  826:     */      
/*  827:     */      
/*  830: 830 */      return new le(paramVector3f2, paramVector3f1.cubePos);
/*  831:     */    }
/*  832:     */    
/*  835: 835 */    return null;
/*  836:     */  }
/*  837:     */  
/*  838:     */  public org.schema.game.network.objects.NetworkSegmentController getNetworkObject() {
/*  839: 839 */    return super.getNetworkObject();
/*  840:     */  }
/*  841:     */  
/*  843:     */  public le getNextToNearestPiece(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, q paramq1, float paramFloat, boolean paramBoolean, q paramq2, q paramq3)
/*  844:     */  {
/*  845: 845 */    paramBoolean = new javax.vecmath.Vector3f();
/*  846: 846 */    paramVector3f2.normalize();
/*  847: 847 */    paramVector3f2.scale(paramFloat);
/*  848:     */    
/*  849: 849 */    paramBoolean.add(paramVector3f1, paramVector3f2);
/*  850:     */    
/*  851: 851 */    (
/*  852:     */    
/*  853: 853 */      paramVector3f2 = new org.schema.game.common.data.physics.CubeRayCastResult(paramVector3f1, paramBoolean, Boolean.valueOf(false), this)).setRespectShields(false);
/*  854: 854 */    paramVector3f2.onlyCubeMeshes = true;
/*  855: 855 */    paramVector3f2.setIgnoereNotPhysical(true);
/*  856:     */    
/*  861: 861 */    if (((paramVector3f1 = getPhysics().testRayCollisionPoint(paramVector3f1, paramBoolean, paramVector3f2, false)) != null) && (paramVector3f1.hasHit()) && ((paramVector3f1 instanceof org.schema.game.common.data.physics.CubeRayCastResult)))
/*  862:     */    {
/*  877: 877 */      if ((paramVector3f2 = (org.schema.game.common.data.physics.CubeRayCastResult)paramVector3f1).getSegment() == null) {
/*  878: 878 */        System.err.println("CUBERESULT SEGMENT NULL");
/*  879: 879 */        return null;
/*  880:     */      }
/*  881:     */      
/*  882: 882 */      paramFloat = new q(paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_a_of_type_Int, paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_b_of_type_Int, paramVector3f2.getSegment().jdField_a_of_type_Q.c);
/*  883:     */      
/*  884: 884 */      paramq1.b(paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_a_of_type_Int + paramVector3f2.cubePos.a, paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_b_of_type_Int + paramVector3f2.cubePos.b, paramVector3f2.getSegment().jdField_a_of_type_Q.c + paramVector3f2.cubePos.c);
/*  885:     */      
/*  889: 889 */      paramFloat.jdField_a_of_type_Int += paramVector3f2.cubePos.a - 8;
/*  890: 890 */      paramFloat.jdField_b_of_type_Int += paramVector3f2.cubePos.b - 8;
/*  891: 891 */      paramFloat.c += paramVector3f2.cubePos.c - 8;
/*  892:     */      
/*  900: 900 */      if (((ct)getState()).a() == getSectorId()) {
/*  901: 901 */        getWorldTransformInverse().transform(paramVector3f1.hitPointWorld);
/*  902:     */      }
/*  903:     */      else {
/*  904: 904 */        (paramq1 = new com.bulletphysics.linearmath.Transform(getWorldTransformClient())).inverse();
/*  905: 905 */        paramq1.transform(paramVector3f1.hitPointWorld);
/*  906:     */      }
/*  907:     */      
/*  915: 915 */      paramq1 = Element.getSide(paramVector3f2.hitPointWorld, paramFloat);
/*  916:     */      
/*  917: 917 */      System.err.println("SIDE: " + Element.getSideString(paramq1) + ": " + paramVector3f2.hitPointWorld + "; " + paramFloat);
/*  918:     */      
/*  920: 920 */      switch (paramq1) {
/*  921: 921 */      case 0:  float tmp422_420 = paramFloat;tmp422_420.jdField_a_of_type_Int = ((int)(tmp422_420.jdField_a_of_type_Int + 1.0F));
/*  922: 922 */        paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  923: 923 */        break;
/*  924: 924 */      case 1:  float tmp458_456 = paramFloat;tmp458_456.jdField_a_of_type_Int = ((int)(tmp458_456.jdField_a_of_type_Int - 1.0F));
/*  925: 925 */        paramq3.b(-paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  926: 926 */        break;
/*  927: 927 */      case 2:  float tmp495_493 = paramFloat;tmp495_493.jdField_b_of_type_Int = ((int)(tmp495_493.jdField_b_of_type_Int + 1.0F));
/*  928: 928 */        paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  929: 929 */        break;
/*  930: 930 */      case 3:  float tmp531_529 = paramFloat;tmp531_529.jdField_b_of_type_Int = ((int)(tmp531_529.jdField_b_of_type_Int - 1.0F));
/*  931: 931 */        paramq3.b(paramq2.jdField_a_of_type_Int, -paramq2.jdField_b_of_type_Int, paramq2.c);
/*  932: 932 */        break;
/*  933: 933 */      case 4:  float tmp568_566 = paramFloat;tmp568_566.c = ((int)(tmp568_566.c + 1.0F));
/*  934: 934 */        paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  935: 935 */        break;
/*  936: 936 */      case 5:  float tmp604_602 = paramFloat;tmp604_602.c = ((int)(tmp604_602.c - 1.0F));
/*  937: 937 */        paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, -paramq2.c);
/*  938: 938 */        break;
/*  939: 939 */      default:  System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
/*  940:     */      }
/*  941:     */      
/*  942:     */      
/*  944: 944 */      paramFloat.a(8, 8, 8);
/*  945:     */      
/*  955: 955 */      paramVector3f1 = new le();
/*  956:     */      
/*  958: 958 */      if (((paramVector3f1 = getSegmentBuffer().a(paramFloat, true, paramVector3f1)) != null) && (paramVector3f1.a().g()))
/*  959:     */      {
/*  960: 960 */        getSegmentProvider().a().assignData(paramVector3f1.a());
/*  961:     */      }
/*  962:     */      
/*  965: 965 */      paramVector3f2 = 0;
/*  966: 966 */      paramq1 = new jO();
/*  967:     */      try
/*  968:     */      {
/*  969: 969 */        if ((paramVector3f1 != null) && (getCollisionChecker().a(paramVector3f1, paramq1)))
/*  970:     */        {
/*  971: 971 */          paramVector3f2 = 1; }
/*  972:     */      } catch (Exception localException) {
/*  973: 973 */        
/*  974:     */        
/*  975: 975 */          localException;
/*  976:     */      }
/*  977:     */      
/*  979: 977 */      if (!getDockingController().a().isEmpty())
/*  980:     */      {
/*  982: 980 */        if (((this instanceof ld)) && ((((ld)this).a() instanceof DockingBlockManagerInterface))) {
/*  983: 981 */          for (paramBoolean = getDockingController().a().iterator(); paramBoolean.hasNext();) { paramq2 = (ElementDocking)paramBoolean.next();
/*  984:     */            
/*  986: 984 */            for (paramq3 = ((DockingBlockManagerInterface)((ld)this).a()).getDockingBlock().iterator(); paramq3.hasNext();) {
/*  987: 985 */              for (localIterator = ((ManagerModuleCollection)paramq3.next()).getCollectionManagers().iterator(); localIterator.hasNext();) {
/*  988:     */                DockingBlockCollectionManager localDockingBlockCollectionManager;
/*  989: 987 */                if (((localDockingBlockCollectionManager = (DockingBlockCollectionManager)localIterator.next()).getControllerElement().equals(paramq2.to)) && (!localDockingBlockCollectionManager.isValidPositionToBuild(paramFloat))) {
/*  990: 988 */                  throw new BlockedByDockedElementException(paramq2.to);
/*  991:     */                }
/*  992:     */              }
/*  993:     */            }
/*  994:     */          }
/*  995:     */        }
/*  996:     */      }
/*  997:     */      java.util.Iterator localIterator;
/*  998: 996 */      if (paramVector3f2 != 0) {
/*  999: 997 */        throw new ElementPositionBlockedException(paramq1.a);
/* 1000:     */      }
/* 1001:     */      
/* 1004:1002 */      return paramVector3f1;
/* 1005:     */    }
/* 1006:     */    
/* 1007:1005 */    return null;
/* 1008:     */  }
/* 1009:     */  
/* 1013:     */  public void handleBeingSalvaged(BeamHandler.BeamState paramBeamState, jq paramjq, javax.vecmath.Vector3f paramVector3f, org.schema.game.common.data.physics.CubeRayCastResult paramCubeRayCastResult, int paramInt)
/* 1014:     */  {
/* 1015:1013 */    if ((this instanceof km)) {
/* 1016:1014 */      ((km)this).a(true);
/* 1017:     */    }
/* 1018:     */  }
/* 1019:     */  
/* 1025:     */  public void handleExplosion(com.bulletphysics.linearmath.Transform paramTransform, float paramFloat1, float paramFloat2, lb paramlb, byte paramByte)
/* 1026:     */  {
/* 1027:1025 */    System.err.println("[HIT][EXPLOSION] Handling explosion: " + this + " " + paramTransform + ", R: " + paramFloat1 + ", dam " + paramFloat2 + " from " + paramlb);
/* 1028:     */    
/* 1029:1027 */    if ((this instanceof km)) {
/* 1030:1028 */      ((km)this).a(true);
/* 1031:     */    }
/* 1032:     */    
/* 1033:1031 */    if ((paramByte != 1) && 
/* 1034:1032 */      ((this instanceof ld)) && ((((ld)this).a() instanceof ShieldContainerInterface)))
/* 1035:     */    {
/* 1037:1035 */      if ((paramByte = (ShieldContainerInterface)((ld)this).a()).getShieldManager().getShields() > 0.0D) {
/* 1038:1036 */        double d1 = paramFloat2 * Math.max(1.0F, paramFloat1 / 2.0F);
/* 1039:1037 */        double d2 = paramByte.getShieldManager().getShields();
/* 1040:     */        
/* 1041:1039 */        if ((paramFloat2 = (float)paramByte.handleShieldHit(paramTransform.origin, null, paramFloat2 * Math.max(1.0F, paramFloat1 / 2.0F))) <= 0.0F) {
/* 1042:1040 */          System.err.println("[EXPLOSION] " + this + " Shield completely absorbed damage: SHIELDS: " + d2 + " -> " + paramByte.getShieldManager().getShields() + " DAM: " + d1 + " -> " + paramFloat2);
/* 1043:1041 */          return;
/* 1044:     */        }
/* 1045:     */      }
/* 1046:     */    }
/* 1047:     */    
/* 1050:1048 */    paramByte = paramFloat2;
/* 1051:     */    
/* 1052:1050 */    if (isOnServer()) { Object localObject1;
/* 1053:1051 */      if (getFactionId() > 0)
/* 1054:     */      {
/* 1056:1054 */        if ((localObject1 = ((vf)getState()).a().a(getFactionId())) != null) {
/* 1057:1055 */          ((lP)localObject1).a(paramlb);
/* 1058:     */        } else {
/* 1059:1057 */          System.err.println("[SERVER][EDITABLESEGMENTCONTROLLER] ON HIT: faction not found: " + getFactionId());
/* 1060:     */        }
/* 1061:     */      }
/* 1062:     */      
/* 1063:1061 */      if (((localObject1 = ((vg)getState()).a().getSector(getSectorId())) != null) && (((mx)localObject1).b())) {
/* 1064:1062 */        if ((paramlb != null) && ((paramlb instanceof cw))) {
/* 1065:1063 */          localObject2 = ((cw)paramlb).a();
/* 1066:1064 */          for (int i = 0; i < ((java.util.ArrayList)localObject2).size(); i++) {
/* 1067:1065 */            lE locallE = (lE)((java.util.ArrayList)localObject2).get(i);
/* 1068:1066 */            if (System.currentTimeMillis() - locallE.a > 5000L) {
/* 1069:1067 */              locallE.a = System.currentTimeMillis();
/* 1070:1068 */              locallE.a(new ServerMessage("This Sector is Protected!", 2, locallE.getId()));
/* 1071:     */            }
/* 1072:     */          }
/* 1073:     */        }
/* 1074:1072 */        return;
/* 1075:     */      }
/* 1076:     */      
/* 1077:1075 */      if (!canAttack(paramlb)) {
/* 1078:1076 */        return;
/* 1079:     */      }
/* 1080:     */      
/* 1082:1080 */      Object localObject2 = new lc(this, paramTransform, paramFloat1, paramByte, paramlb);
/* 1083:1081 */      this.explosionOrdersRunning.add(localObject2);
/* 1084:1082 */      getState().getThreadPool().execute((Runnable)localObject2);
/* 1085:     */    }
/* 1086:     */  }
/* 1087:     */  
/* 1090:     */  public boolean canAttack(lb paramlb)
/* 1091:     */  {
/* 1092:1090 */    if ((isHomeBase()) || ((getDockingController().b()) && (getDockingController().a().to.a().a().isHomeBaseFor(getFactionId()))))
/* 1093:     */    {
/* 1094:1092 */      if ((paramlb != null) && ((paramlb instanceof cw))) {
/* 1095:1093 */        paramlb = ((cw)paramlb).a();
/* 1096:1094 */        for (int i = 0; i < paramlb.size(); i++) {
/* 1097:1095 */          lE locallE = (lE)paramlb.get(i);
/* 1098:1096 */          if (System.currentTimeMillis() - locallE.a > 5000L) {
/* 1099:1097 */            locallE.a = System.currentTimeMillis();
/* 1100:1098 */            locallE.a(new ServerMessage("Cannot attack a faction's\nhome base!", 2, locallE.getId()));
/* 1101:     */          }
/* 1102:     */        }
/* 1103:     */      }
/* 1104:1102 */      return false;
/* 1105:     */    }
/* 1106:1104 */    return true;
/* 1107:     */  }
/* 1108:     */  
/* 1109:     */  public void handleHit(com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat) {
/* 1110:1108 */    org.schema.game.common.data.physics.CubeRayCastResult localCubeRayCastResult = (org.schema.game.common.data.physics.CubeRayCastResult)paramClosestRayResultCallback;
/* 1111:1109 */    assert (localCubeRayCastResult != null);
/* 1112:1110 */    assert (localCubeRayCastResult.getSegment() != null) : localCubeRayCastResult.hasHit();
/* 1113:1111 */    assert (localCubeRayCastResult.getSegment().a() != null) : localCubeRayCastResult.getSegment().jdField_a_of_type_Q;
/* 1114:     */    
/* 1115:1113 */    if ((localCubeRayCastResult == null) || (localCubeRayCastResult.getSegment() == null) || (localCubeRayCastResult.getSegment().a() == null)) {
/* 1116:1114 */      return;
/* 1117:     */    }
/* 1118:     */    
/* 1119:1117 */    if ((this instanceof km)) {
/* 1120:1118 */      ((km)this).a(true);
/* 1121:     */    }
/* 1122:1120 */    org.schema.game.common.data.world.SegmentData localSegmentData = localCubeRayCastResult.getSegment().a();
/* 1123:     */    
/* 1125:1123 */    int i = org.schema.game.common.data.world.SegmentData.getInfoIndex(localCubeRayCastResult.cubePos);
/* 1126:1124 */    short s = localSegmentData.getType(i);
/* 1127:1125 */    localSegmentData.getHitpoints(i);
/* 1128:     */    
/* 1129:1127 */    le localle = new le(localCubeRayCastResult.getSegment(), localCubeRayCastResult.cubePos);
/* 1130:     */    
/* 1131:1129 */    if (isOnServer()) { Object localObject;
/* 1132:1130 */      if (getFactionId() > 0)
/* 1133:     */      {
/* 1135:1133 */        if ((localObject = ((vf)getState()).a().a(getFactionId())) != null) {
/* 1136:1134 */          ((lP)localObject).a(paramlb);
/* 1137:     */        } else {
/* 1138:1136 */          System.err.println("[SERVER][EDITABLESEGMENTCONTROLLER] ON HIT: faction not found: " + getFactionId());
/* 1139:     */        }
/* 1140:     */      }
/* 1141:     */      
/* 1143:1141 */      if (((localObject = ((vg)getState()).a().getSector(getSectorId())) != null) && (((mx)localObject).b())) {
/* 1144:1142 */        if ((paramlb != null) && ((paramlb instanceof cw))) {
/* 1145:1143 */          localObject = ((cw)paramlb).a();
/* 1146:1144 */          for (paramlb = 0; paramlb < ((java.util.ArrayList)localObject).size(); paramlb++) {
/* 1147:1145 */            paramFloat = (lE)((java.util.ArrayList)localObject).get(paramlb);
/* 1148:1146 */            if (System.currentTimeMillis() - paramFloat.a > 5000L) {
/* 1149:1147 */              paramFloat.a = System.currentTimeMillis();
/* 1150:1148 */              paramFloat.a(new ServerMessage("This Sector is Protected!", 2, paramFloat.getId()));
/* 1151:     */            }
/* 1152:     */          }
/* 1153:     */        }
/* 1154:1152 */        return;
/* 1155:     */      }
/* 1156:1154 */      if (!canAttack(paramlb)) {
/* 1157:     */        return;
/* 1158:     */      }
/* 1159:     */      
/* 1160:     */      int j;
/* 1161:1159 */      if ((j = damageElement(s, i, localSegmentData, (int)paramFloat)) > 0) {
/* 1162:1160 */        onDamageServer(j, paramlb);
/* 1163:     */      }
/* 1164:     */      
/* 1165:1163 */      if (localSegmentData.getHitpoints(i) <= 0)
/* 1166:     */      {
/* 1169:1167 */        if ((s == getCoreType()) && (localle.a(this.absPosCache).equals(kd.jdField_a_of_type_Q))) {
/* 1170:1168 */          localSegmentData.setHitpoints(i, (short)0);
/* 1171:1169 */          onCoreDestroyed(paramlb);
/* 1172:1170 */          onCoreHitAlreadyDestroyed(paramFloat);
/* 1173:     */        }
/* 1174:     */        else
/* 1175:     */        {
/* 1176:1174 */          localCubeRayCastResult.getSegment().a(localCubeRayCastResult.cubePos, true);
/* 1177:     */        }
/* 1178:     */        
/* 1179:1177 */        if (isEnterable(s)) {
/* 1180:1178 */          forceCharacterExit(localle);
/* 1181:     */        }
/* 1182:     */      }
/* 1183:     */      
/* 1184:1182 */      ((mw)localCubeRayCastResult.getSegment()).a(System.currentTimeMillis());
/* 1185:1183 */      localle.a();
/* 1186:1184 */      getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(localle, this, getNetworkObject()));
/* 1187:     */    }
/* 1188:1186 */    if (!isOnServer())
/* 1189:     */    {
/* 1190:1188 */      ElementInformation localElementInformation = ElementKeyMap.getInfo(s);
/* 1191:1189 */      int k = (int)Math.max(0.0D, paramFloat - Math.ceil(paramFloat * localElementInformation.getArmourPercent()));
/* 1192:     */      
/* 1193:1191 */      (
/* 1194:1192 */        paramlb = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 1195:1193 */      paramlb.origin.set(paramClosestRayResultCallback.hitPointWorld);
/* 1196:1194 */      ij.a.add(new eH(paramlb, String.valueOf(k), 1.0F, 0.0F, 0.0F));
/* 1197:     */      
/* 1198:1196 */      ((ct)getState())
/* 1199:1197 */        .a().a().a(paramClosestRayResultCallback);
/* 1200:1198 */      if (k < 300) {
/* 1201:1199 */        xe.a("0022_spaceship enemy - hit small explosion small enemy ship blow up", paramlb, 5.0F);return; }
/* 1202:1200 */      if (k < 600) {
/* 1203:1201 */        xe.a("0022_spaceship enemy - hit medium explosion medium enemy ship blow up", paramlb, 10.0F);return;
/* 1204:     */      }
/* 1205:1203 */      xe.a("0022_spaceship enemy - hit large explosion big enemy ship blow up", paramlb, 30.0F);
/* 1206:     */    }
/* 1207:     */  }
/* 1208:     */  
/* 1214:     */  public void onCoreHitAlreadyDestroyed(float paramFloat) {}
/* 1215:     */  
/* 1220:     */  public void handleHitMissile(ln paramln, com.bulletphysics.linearmath.Transform paramTransform)
/* 1221:     */  {
/* 1222:1220 */    handleExplosion(paramTransform, paramln.a(), paramln.a(), paramln.a(), (byte)0);
/* 1223:     */  }
/* 1224:     */  
/* 1227:     */  public void handleMovingInput(xq paramxq, lA paramlA) {}
/* 1228:     */  
/* 1230:     */  public int handleRepair(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, javax.vecmath.Vector3f paramVector3f, org.schema.game.common.data.physics.CubeRayCastResult paramCubeRayCastResult, xq paramxq)
/* 1231:     */  {
/* 1232:1230 */    paramq = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
/* 1233:     */    
/* 1235:1233 */    paramjq = (paramBeamState = paramjq.getHandler().beamHit(paramBeamState, paramq)) * 20;
/* 1236:     */    
/* 1237:1235 */    if ((paramBeamState > 0) && (isOnServer()))
/* 1238:     */    {
/* 1239:1237 */      paramq.a().a();
/* 1240:1238 */      paramVector3f = org.schema.game.common.data.world.SegmentData.getInfoIndex(paramq.a(this.tmpLocalPos));
/* 1241:     */      
/* 1242:1240 */      paramxq = paramq.a().a().getHitpoints(paramVector3f);
/* 1243:1241 */      int i = ElementKeyMap.getInfo(paramq.a()).getMaxHitPoints();
/* 1244:1242 */      paramq.a().a().setHitpoints(paramVector3f, (short)Math.min(i, paramxq + paramjq));
/* 1245:     */      
/* 1247:1245 */      ((mw)paramq.a()).a(System.currentTimeMillis());
/* 1248:1246 */      paramq.a();
/* 1249:1247 */      paramq.a(getState().getId());
/* 1250:1248 */      ((org.schema.game.network.objects.NetworkSegmentController)paramq.a().a().getNetworkObject()).modificationBuffer.add(new RemoteSegmentPiece(paramq, this, getNetworkObject()));
/* 1251:     */    }
/* 1252:     */    
/* 1255:1253 */    if ((paramBeamState > 0) && (!isOnServer()))
/* 1256:     */    {
/* 1257:1255 */      ElementKeyMap.getInfo(paramq.a());
/* 1258:1256 */      paramxq = Math.max(0, paramjq);
/* 1259:     */      
/* 1260:     */      com.bulletphysics.linearmath.Transform localTransform;
/* 1261:1259 */      (localTransform = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 1262:1260 */      localTransform.origin.set(paramCubeRayCastResult.hitPointWorld);
/* 1263:1261 */      ij.a.add(new eH(localTransform, String.valueOf(paramxq), 0.0F, 1.0F, 0.0F));
/* 1264:     */    }
/* 1265:     */    
/* 1266:1264 */    return paramBeamState;
/* 1267:     */  }
/* 1268:     */  
/* 1270:     */  private boolean isEnterable(short paramShort)
/* 1271:     */  {
/* 1272:1270 */    return (paramShort != 0) && (ElementKeyMap.getInfo(paramShort).isEnterable());
/* 1273:     */  }
/* 1274:     */  
/* 1275:     */  public boolean isRepariableFor(jI paramjI, String[] paramArrayOfString, q paramq) {
/* 1276:1274 */    return true;
/* 1277:     */  }
/* 1278:     */  
/* 1279:     */  public void newNetworkObject()
/* 1280:     */  {
/* 1281:1279 */    setNetworkObject(new org.schema.game.network.objects.NetworkSegmentController(getState(), this));
/* 1282:     */  }
/* 1283:     */  
/* 1285:     */  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, org.schema.game.common.data.world.Segment paramSegment, boolean paramBoolean)
/* 1286:     */  {
/* 1287:1285 */    super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
/* 1288:     */    
/* 1289:1287 */    if (isOnServer()) {
/* 1290:1288 */      doDimExtensionIfNecessary(paramSegment, paramByte1, paramByte2, paramByte3);
/* 1291:     */    }
/* 1292:     */  }
/* 1293:     */  
/* 1299:     */  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable) {}
/* 1300:     */  
/* 1305:     */  public boolean needsManifoldCollision()
/* 1306:     */  {
/* 1307:1305 */    return getElementClassCountMap().a((short)14) > 0;
/* 1308:     */  }
/* 1309:     */  
/* 1312:     */  protected abstract void onCoreDestroyed(lb paramlb);
/* 1313:     */  
/* 1316:     */  protected void onDamageServer(int paramInt, lb paramlb) {}
/* 1317:     */  
/* 1319:     */  public void startCreatorThread()
/* 1320:     */  {
/* 1321:1319 */    if (getCreatorThread() == null) {
/* 1322:1320 */      setCreatorThread(new kI(this));
/* 1323:     */    }
/* 1324:     */  }
/* 1325:     */  
/* 1328:     */  public String toString()
/* 1329:     */  {
/* 1330:1328 */    return getSegmentControllerTypeString() + "(" + getId() + ")";
/* 1331:     */  }
/* 1332:     */  
/* 1335:     */  protected abstract String getSegmentControllerTypeString();
/* 1336:     */  
/* 1339:     */  public void updateLocal(xq paramxq)
/* 1340:     */  {
/* 1341:1339 */    if (isMarkedForDeleteVolatile()) {
/* 1342:1340 */      System.err.println("[EditableSegmentControleler] " + this + " MARKED TO DELETE ON " + getState());
/* 1343:     */    }
/* 1344:     */    
/* 1346:1344 */    if (isOnServer()) {
/* 1347:1345 */      for (int i = 0; i < this.explosionOrdersRunning.size(); i++) {
/* 1348:     */        lc locallc;
/* 1349:1347 */        if ((locallc = (lc)this.explosionOrdersRunning.get(i)).a()) {
/* 1350:1348 */          locallc.a();
/* 1351:1349 */          this.explosionOrdersRunning.remove(i);
/* 1352:1350 */          i--;
/* 1353:     */        }
/* 1354:     */      }
/* 1355:     */    }
/* 1356:     */    
/* 1357:1355 */    if (getFlagCoreDestroyedByExplosion() != null) {
/* 1358:1356 */      System.err.println("[EditSegController] " + this + " CORE HAS BEEN DESTROYED");
/* 1359:1357 */      if ((getFlagCoreDestroyedByExplosion() instanceof Sendable)) {
/* 1360:1358 */        onCoreDestroyed((lb)getFlagCoreDestroyedByExplosion());
/* 1361:     */      } else {
/* 1362:1360 */        onCoreDestroyed(null);
/* 1363:     */      }
/* 1364:1362 */      setFlagCoreDestroyedByExplosion(null);
/* 1365:     */    }
/* 1366:1364 */    if (isFlagCharacterExitCheckByExplosion()) {
/* 1367:     */      try {
/* 1368:1366 */        checkCharacterExit();
/* 1369:1367 */      } catch (IOException localIOException) { 
/* 1370:     */        
/* 1373:1371 */          localIOException;
/* 1374:     */      } catch (InterruptedException localInterruptedException) {
/* 1375:1369 */        
/* 1376:     */        
/* 1377:1371 */          localInterruptedException;
/* 1378:     */      }
/* 1379:     */      
/* 1380:1372 */      setFlagCharacterExitCheckByExplosion(false);
/* 1381:     */    }
/* 1382:1374 */    super.updateLocal(paramxq);
/* 1383:     */  }
/* 1384:     */  
/* 1388:     */  public Object getFlagCoreDestroyedByExplosion()
/* 1389:     */  {
/* 1390:1382 */    return this.flagCoreDestroyedByExplosion;
/* 1391:     */  }
/* 1392:     */  
/* 1395:     */  public void setFlagCoreDestroyedByExplosion(Object paramObject)
/* 1396:     */  {
/* 1397:1389 */    this.flagCoreDestroyedByExplosion = paramObject;
/* 1398:     */  }
/* 1399:     */  
/* 1401:     */  public boolean isFlagCharacterExitCheckByExplosion()
/* 1402:     */  {
/* 1403:1395 */    return this.flagCharacterExitCheckByExplosion;
/* 1404:     */  }
/* 1405:     */  
/* 1408:     */  public void setFlagCharacterExitCheckByExplosion(boolean paramBoolean)
/* 1409:     */  {
/* 1410:1402 */    this.flagCharacterExitCheckByExplosion = paramBoolean;
/* 1411:     */  }
/* 1412:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.EditableSendableSegmentController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */