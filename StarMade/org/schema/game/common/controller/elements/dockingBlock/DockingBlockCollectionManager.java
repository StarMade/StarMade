/*   1:    */package org.schema.game.common.controller.elements.dockingBlock;
/*   2:    */
/*   3:    */import Aj;
/*   4:    */import X;
/*   5:    */import am;
/*   6:    */import ar;
/*   7:    */import au;
/*   8:    */import ax;
/*   9:    */import bi;
/*  10:    */import bj;
/*  11:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*  12:    */import jD;
/*  13:    */import jR;
/*  14:    */import le;
/*  15:    */import org.schema.game.common.controller.elements.BlockMetaDataDummy;
/*  16:    */import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*  17:    */import org.schema.game.common.data.element.ControlElementMap;
/*  18:    */
/*  19:    */public abstract class DockingBlockCollectionManager extends ControlBlockElementCollectionManager
/*  20:    */{
/*  21:    */  public static final String TAG_ID = "A";
/*  22:    */  public static final int defaultDockingHalfSize = 3;
/*  23:    */  protected int enhancers;
/*  24: 24 */  public byte orientation = 0;
/*  25:    */  
/*  26:    */  private boolean collision;
/*  27: 27 */  private q minArea = new q(0, 0, 0);
/*  28: 28 */  private q maxArea = new q(0, 0, 0);
/*  29: 29 */  private static String dockingOnlineMsg = "Docking Module online!";
/*  30:    */  
/*  31: 31 */  private static String dockingOfflineMsg = "Docking Module offline!\nArea is blocked!";
/*  32:    */  
/*  34:    */  public DockingBlockCollectionManager(le paramle, org.schema.game.common.controller.SegmentController paramSegmentController, short paramShort)
/*  35:    */  {
/*  36: 36 */    super(paramle, paramShort, paramSegmentController);
/*  37:    */  }
/*  38:    */  
/*  42:    */  public void getDockingArea(q paramq1, q paramq2)
/*  43:    */  {
/*  44: 44 */    paramq2.a(this.maxArea, this.minArea);
/*  45: 45 */    paramq2.c();
/*  46: 46 */    paramq2.a(3, 3, 3);
/*  47: 47 */    paramq1.b(paramq2);
/*  48: 48 */    paramq1.b();
/*  49:    */    
/*  52: 52 */    paramq1.a(2);
/*  53: 53 */    paramq2.a(2);
/*  54:    */  }
/*  55:    */  
/*  58:    */  public void getDockingAreaAbsolute(q paramq1, q paramq2)
/*  59:    */  {
/*  60: 60 */    getDockingArea(paramq1, paramq2);
/*  61:    */    
/*  62: 62 */    javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(Math.abs(paramq1.a - paramq2.a) / 2.0F, Math.abs(paramq1.b - paramq2.b) / 2.0F, Math.abs(paramq1.c - paramq2.c) / 2.0F);
/*  63:    */    
/*  64: 64 */    q localq = new q(getControllerPos());
/*  65:    */    
/*  67: 67 */    switch (org.schema.game.common.data.element.Element.orientationBackMapping[getControllerElement().b()]) {
/*  68:    */    case 0: 
/*  69: 69 */      localq.b((int)(localq.a + localVector3f.x / 2.0F + 1.0F), localq.b, localq.c);break;
/*  70: 70 */    case 1:  localq.b((int)(localq.a - localVector3f.x / 2.0F - 1.0F), localq.b, localq.c);break;
/*  71:    */    case 2: 
/*  72: 72 */      localq.b(localq.a, (int)(localq.b + localVector3f.y / 2.0F + 1.0F), localq.c);break;
/*  73: 73 */    case 3:  localq.b(localq.a, (int)(localq.b - localVector3f.y / 2.0F - 1.0F), localq.c);break;
/*  74:    */    case 4: 
/*  75: 75 */      localq.b(localq.a, localq.b, (int)(localq.c + localVector3f.z / 2.0F + 1.0F));break;
/*  76: 76 */    case 5:  localq.b(localq.a, localq.b, (int)(localq.c - localVector3f.z / 2.0F - 1.0F));
/*  77:    */    }
/*  78: 78 */    paramq1.a();
/*  79: 79 */    paramq2.a();
/*  80: 80 */    paramq1.a(localq);
/*  81: 81 */    paramq2.a(localq);
/*  82:    */  }
/*  83:    */  
/*  84: 84 */  private final q min = new q();
/*  85: 85 */  private final q max = new q();
/*  86:    */  
/*  87:    */  public boolean isObjectDockable(org.schema.game.common.controller.SegmentController paramSegmentController)
/*  88:    */  {
/*  89: 89 */    if (this.enhancers != getSegmentController().getControlElementMap().getControlledElements(getEnhancerClazz(), getControllerPos()).a.size()) {
/*  90: 90 */      throw new org.schema.game.common.controller.CollectionNotLoadedException();
/*  91:    */    }
/*  92: 92 */    if (!paramSegmentController.getBoundingBox().a()) {
/*  93: 93 */      System.err.println("Exception Catched (OK): SegmentController tested to dock " + paramSegmentController + " to " + getSegmentController() + ": BB is not yet loaded");
/*  94: 94 */      throw new org.schema.game.common.controller.CollectionNotLoadedException();
/*  95:    */    }
/*  96:    */    
/*  98: 98 */    getDockingMoved(this.min, this.max);
/*  99:    */    
/* 100:100 */    q localq = new q(paramSegmentController.getSegmentBuffer().a().b.x, paramSegmentController.getSegmentBuffer().a().b.y, paramSegmentController.getSegmentBuffer().a().b.z);
/* 101:    */    
/* 105:105 */    paramSegmentController = new q(paramSegmentController.getSegmentBuffer().a().a.x, paramSegmentController.getSegmentBuffer().a().a.y, paramSegmentController.getSegmentBuffer().a().a.z);
/* 106:    */    
/* 118:118 */    paramSegmentController.b -= this.min.b;
/* 119:119 */    localq.b -= this.max.b;
/* 120:    */    
/* 121:121 */    if ((paramSegmentController.a < this.min.a) || (paramSegmentController.b < this.min.b) || (paramSegmentController.c < this.min.c) || (localq.a > this.max.a) || (localq.b > this.max.b) || (localq.c > this.max.c))
/* 122:    */    {
/* 123:123 */      System.err.println("[DOCKINGBLOCK] !NOT! DOCKABLE: Docking[" + this.min + "; " + this.max + "] / Segment[" + paramSegmentController + "; " + localq + "]");
/* 124:    */      
/* 125:125 */      return false;
/* 126:    */    }
/* 127:    */    
/* 130:130 */    return true;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public abstract void getDockingMoved(q paramq1, q paramq2);
/* 134:    */  
/* 135:135 */  public int getMargin() { return 0; }
/* 136:    */  
/* 141:141 */  protected Class getType() { return DockingBlockUnit.class; }
/* 142:    */  
/* 143:    */  public boolean hasAreaCollision() {
/* 144:144 */    q localq1 = new q();
/* 145:145 */    q localq2 = new q();
/* 146:146 */    getDockingAreaAbsolute(localq1, localq2);
/* 147:    */    
/* 148:148 */    javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f();
/* 149:149 */    javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f();
/* 150:150 */    localVector3f1.set(localq1.a - 8, localq1.b - 8, localq1.c - 8);
/* 151:151 */    localVector3f2.set(localq2.a - 8, localq2.b - 8, localq2.c - 8);
/* 152:    */    
/* 153:153 */    return getSegmentController().getCollisionChecker().a(localq1, localq2);
/* 154:    */  }
/* 155:    */  
/* 156:    */  public boolean hasCollision() {
/* 157:157 */    return this.collision;
/* 158:    */  }
/* 159:    */  
/* 161:    */  public boolean isValidPositionToBuild(q paramq)
/* 162:    */  {
/* 163:163 */    q localq1 = new q();
/* 164:164 */    q localq2 = new q();
/* 165:165 */    getDockingAreaAbsolute(localq1, localq2);
/* 166:    */    
/* 167:167 */    System.err.println("[DOCKING] CHECKING TO BUILD POSITION " + paramq + " ----> " + localq1 + "; " + localq2);
/* 168:168 */    return (paramq.a > localq2.a) || (paramq.b > localq2.b) || (paramq.c > localq2.c) || (paramq.a < localq1.a) || (paramq.b < localq1.b) || (paramq.c < localq1.c);
/* 169:    */  }
/* 170:    */  
/* 175:    */  protected void onChangedCollection()
/* 176:    */  {
/* 177:177 */    int i = -1;
/* 178:178 */    this.enhancers = 0;
/* 179:179 */    if (getCollection().isEmpty()) {
/* 180:180 */      this.minArea.b(0, 0, 0);
/* 181:181 */      this.maxArea.b(0, 0, 0);return;
/* 182:    */    }
/* 183:183 */    for (java.util.Iterator localIterator = getCollection().iterator(); localIterator.hasNext();)
/* 184:    */    {
/* 185:    */      DockingBlockUnit localDockingBlockUnit;
/* 186:    */      
/* 187:    */      int j;
/* 188:188 */      if ((j = Math.abs((localDockingBlockUnit = (DockingBlockUnit)localIterator.next()).getMax().a - localDockingBlockUnit.getMin().a) * Math.abs(localDockingBlockUnit.getMax().b - localDockingBlockUnit.getMin().b) * Math.abs(localDockingBlockUnit.getMax().c - localDockingBlockUnit.getMin().c)) > i) {
/* 189:189 */        this.minArea.b(localDockingBlockUnit.getMin());
/* 190:190 */        this.maxArea.b(localDockingBlockUnit.getMax());
/* 191:191 */        this.maxArea.a(1, 1, 1);
/* 192:192 */        i = j;
/* 193:    */      }
/* 194:    */      
/* 195:195 */      this.enhancers += localDockingBlockUnit.size();
/* 196:    */    }
/* 197:    */  }
/* 198:    */  
/* 203:    */  public void refreshActive()
/* 204:    */  {
/* 205:205 */    boolean bool = this.collision;
/* 206:206 */    this.collision = hasAreaCollision();
/* 207:    */    
/* 208:208 */    if ((!getSegmentController().isOnServer()) && (bool != this.collision))
/* 209:    */    {
/* 210:    */      ct localct;
/* 211:    */      aq localaq;
/* 212:212 */      if (((localaq = (localct = (ct)getSegmentController().getState()).a().a().a()).a().a().c()) || (localaq.a().a().a().a().c()))
/* 213:    */      {
/* 214:214 */        if (this.collision) {
/* 215:215 */          localct.a().a(dockingOnlineMsg);
/* 216:216 */          localct.a().b(dockingOfflineMsg);return;
/* 217:    */        }
/* 218:218 */        localct.a().a(dockingOfflineMsg);
/* 219:219 */        localct.a().d(dockingOnlineMsg);
/* 220:    */      }
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 229:    */  protected boolean hasMetaData()
/* 230:    */  {
/* 231:231 */    return true;
/* 232:    */  }
/* 233:    */  
/* 237:    */  protected void applyMetaData(BlockMetaDataDummy paramBlockMetaDataDummy)
/* 238:    */  {
/* 239:239 */    this.orientation = ((DockingMetaDataDummy)paramBlockMetaDataDummy).orientation;
/* 240:    */  }
/* 241:    */  
/* 242:    */  protected Ah toTagStructurePriv() {
/* 243:243 */    return new Ah(Aj.b, null, Byte.valueOf(this.orientation));
/* 244:    */  }
/* 245:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */