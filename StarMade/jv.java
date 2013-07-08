/*    1:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*    2:     */import com.bulletphysics.collision.shapes.CompoundShape;
/*    3:     */import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*    4:     */import com.bulletphysics.dynamics.RigidBody;
/*    5:     */import com.bulletphysics.linearmath.Transform;
/*    6:     */import com.bulletphysics.util.ObjectArrayList;
/*    7:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    8:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    9:     */import java.io.IOException;
/*   10:     */import java.io.PrintStream;
/*   11:     */import java.util.ArrayList;
/*   12:     */import java.util.Collection;
/*   13:     */import java.util.ConcurrentModificationException;
/*   14:     */import java.util.HashSet;
/*   15:     */import java.util.Iterator;
/*   16:     */import java.util.List;
/*   17:     */import java.util.Set;
/*   18:     */import javax.vecmath.Matrix3f;
/*   19:     */import javax.vecmath.Quat4f;
/*   20:     */import javax.vecmath.Vector3f;
/*   21:     */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*   22:     */import org.schema.game.common.controller.CollectionNotLoadedException;
/*   23:     */import org.schema.game.common.controller.SegmentController;
/*   24:     */import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*   25:     */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*   26:     */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*   27:     */import org.schema.game.common.data.element.ElementDocking;
/*   28:     */import org.schema.game.common.data.element.ElementInformation;
/*   29:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   30:     */import org.schema.game.common.data.physics.CubesCompoundShape;
/*   31:     */import org.schema.game.common.data.physics.PhysicsExt;
/*   32:     */import org.schema.game.common.data.physics.RigidBodyExt;
/*   33:     */import org.schema.game.common.data.world.Segment;
/*   34:     */import org.schema.game.common.data.world.Universe;
/*   35:     */import org.schema.game.network.objects.NetworkSegmentController;
/*   36:     */import org.schema.game.server.controller.GameServerController;
/*   37:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   38:     */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   39:     */import org.schema.schine.network.NetworkStateContainer;
/*   40:     */import org.schema.schine.network.StateInterface;
/*   41:     */import org.schema.schine.network.objects.Sendable;
/*   42:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   43:     */import org.schema.schine.network.objects.remote.RemoteString;
/*   44:     */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*   45:     */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*   46:     */import org.schema.schine.network.server.ServerMessage;
/*   47:     */
/*   53:     */public class jv
/*   54:     */{
/*   55:  55 */  private ElementDocking jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking = null;
/*   56:  56 */  private final Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*   57:     */  
/*   58:  58 */  private String jdField_a_of_type_JavaLangString = null;
/*   59:     */  
/*   60:     */  private q jdField_a_of_type_Q;
/*   61:     */  
/*   62:     */  private boolean jdField_a_of_type_Boolean;
/*   63:     */  
/*   64:     */  private long jdField_a_of_type_Long;
/*   65:     */  private final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*   66:  66 */  private final Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*   67:  67 */  private final Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*   68:     */  
/*   70:  70 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*   71:  71 */  public jv(SegmentController paramSegmentController) { this.jdField_a_of_type_JavaxVecmathQuat4f = new Quat4f();
/*   72:     */    
/*   76:  76 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*   77:  77 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*   78:  78 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*   79:     */  }
/*   80:     */  
/*   81:     */  public Quat4f a;
/*   82:     */  public final void a() { Iterator localIterator1;
/*   83:  83 */    if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()))
/*   84:     */    {
/*   85:  85 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_b_of_type_Boolean) {
/*   86:  86 */        int i = 0;
/*   87:     */        SegmentController localSegmentController;
/*   88:  88 */        if ((((localSegmentController = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a()) instanceof ld)) && ((((ld)localSegmentController).a() instanceof DockingBlockManagerInterface))) {
/*   89:  89 */          Object localObject = (DockingBlockManagerInterface)((ld)localSegmentController).a();
/*   90:  90 */          q localq = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q());
/*   91:     */          
/*   92:  92 */          if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*   93:     */            try {
/*   94:  94 */              this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a().a().getSegmentBuffer().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a(new q()), true, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from);
/*   95:     */              
/*   96:  96 */              this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getSegmentBuffer().a(localq, true, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to);
/*   97:  97 */            } catch (IOException localIOException) { 
/*   98:     */              
/*  101: 101 */                localIOException;
/*  102:     */            } catch (InterruptedException localInterruptedException) {
/*  103:  99 */              
/*  104:     */              
/*  105: 101 */                localInterruptedException;
/*  106:     */            }
/*  107:     */          }
/*  108:     */          
/*  110: 104 */          Iterator localIterator2 = ((DockingBlockManagerInterface)localObject).getDockingBlock().iterator(); int j; do { if (!localIterator2.hasNext()) break; localObject = (ManagerModuleCollection)localIterator2.next();
/*  111: 105 */            j = 0;
/*  112:     */            try
/*  113:     */            {
/*  114: 108 */              for (localObject = ((ManagerModuleCollection)localObject).getCollectionManagers().iterator(); ((Iterator)localObject).hasNext();) { DockingBlockCollectionManager localDockingBlockCollectionManager;
/*  115: 109 */                if ((localDockingBlockCollectionManager = (DockingBlockCollectionManager)((Iterator)localObject).next()).getControllerPos().equals(localq)) {
/*  116: 110 */                  i = 1;
/*  117: 111 */                  if (!localDockingBlockCollectionManager.isObjectDockable(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController)) {
/*  118: 112 */                    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*  119: 113 */                      b();
/*  120: 114 */                    } else if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) {
/*  121: 115 */                      ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().b("Size of docked structure\ntoo big for docking area!\nUndocking " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getRealName());
/*  122:     */                    }
/*  123:     */                    
/*  124: 118 */                    j = 1;
/*  125: 119 */                    break;
/*  126:     */                  }
/*  127:     */                }
/*  128:     */              }
/*  129:     */            } catch (ConcurrentModificationException localConcurrentModificationException) {
/*  130: 124 */              
/*  131:     */              
/*  134: 128 */                localConcurrentModificationException.printStackTrace();System.err.println("Exception could be catched and handeled by deferring docking valid request");throw new CollectionNotLoadedException();
/*  135: 129 */            } } while (j == 0); }
/*  136: 130 */        if (i == 0)
/*  137:     */        {
/*  142: 136 */          if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*  143: 137 */            b();
/*  144: 138 */          } else if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) || (localSegmentController.isClientOwnObject())) {
/*  145: 139 */            ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().b("Docking module has been removed\n\nUndocking  " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getRealName());
/*  146:     */          }
/*  147:     */        }
/*  148:     */      }
/*  149:     */      
/*  153: 147 */      for (localIterator1 = this.jdField_a_of_type_JavaUtilSet.iterator(); localIterator1.hasNext();) {
/*  154: 148 */        ((ElementDocking)localIterator1.next()).from.a().a().flagUpdateDocking();
/*  155:     */      }
/*  156:     */    }
/*  157:     */  }
/*  158:     */  
/*  159:     */  private long jdField_b_of_type_Long;
/*  160:     */  public final boolean a()
/*  161:     */  {
/*  162: 156 */    return System.currentTimeMillis() - this.jdField_a_of_type_Long > 1000L;
/*  163:     */  }
/*  164:     */  
/*  165: 159 */  private static boolean a(le paramle) { return paramle.a() == 7; }
/*  166:     */  
/*  167: 161 */  private final Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  168:     */  private long jdField_c_of_type_Long;
/*  169:     */  private boolean jdField_b_of_type_Boolean;
/*  170:     */  
/*  171: 165 */  private boolean a(le paramle1, le paramle2) { SegmentController localSegmentController = paramle2.a().a();
/*  172:     */    
/*  173:     */    Object localObject1;
/*  174: 168 */    if (!paramle2.a().a().getDockingController().jdField_a_of_type_JavaUtilSet.isEmpty())
/*  175:     */    {
/*  176: 170 */      for (localObject1 = paramle2.a().a().getDockingController().jdField_a_of_type_JavaUtilSet.iterator(); ((Iterator)localObject1).hasNext();) {
/*  177: 171 */        if (((localObject2 = (ElementDocking)((Iterator)localObject1).next()).to.equals(paramle2)) && (!((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().isEmpty())) {
/*  178: 172 */          ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a(((lE)((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().get(0)).getName(), "Cannot dock!\nDocking block in use by\n" + ((ElementDocking)localObject2).from.a().a().toNiceString(), 3);
/*  179:     */          
/*  184: 178 */          System.err.println("[DOCKING] WARNING: two ships are trying to dock to the same block " + ((ElementDocking)localObject2).to + ": in use by " + ((ElementDocking)localObject2).from.a().a() + "; trying: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  185: 179 */          this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  186: 180 */          return false;
/*  187:     */        }
/*  188:     */      }
/*  189:     */    }
/*  190:     */    Object localObject2;
/*  191: 185 */    if (paramle2.a().a().getDockingController().jdField_b_of_type_Boolean) {
/*  192: 186 */      System.err.println("ERROR: cannot dock onto docked object: " + paramle1.a().a() + " -> " + paramle2.a().a());
/*  193:     */      
/*  195: 189 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && 
/*  196: 190 */        ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw)) && 
/*  197: 191 */        (((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().isEmpty())) {
/*  198: 192 */        ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a(((lE)((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().get(0)).getName(), "Chain docking is not\nyet implemented\n(will come soon)", 3);
/*  199:     */      }
/*  200:     */      
/*  206: 200 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  207: 201 */      return false;
/*  208:     */    }
/*  209: 203 */    if (!this.jdField_a_of_type_JavaUtilSet.isEmpty()) {
/*  210: 204 */      System.err.println("ERROR: cannot dock onto with already docked object: " + paramle1.a().a() + " -> " + paramle2.a().a());
/*  211:     */      
/*  213: 207 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && 
/*  214: 208 */        ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw)) && 
/*  215: 209 */        (!((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().isEmpty())) {
/*  216: 210 */        ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a(((lE)((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().get(0)).getName(), "Chain docking is not\nyet implemented\n(will come soon)", 3);
/*  217:     */      }
/*  218:     */      
/*  224: 218 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  225: 219 */      return false;
/*  226:     */    }
/*  227:     */    
/*  228: 222 */    if ((!this.jdField_b_of_type_Boolean) || (this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a() != localSegmentController)) {
/*  229: 223 */      a(new ElementDocking(paramle1, paramle2));
/*  230: 224 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  231: 225 */      localSegmentController.getDockingController().jdField_a_of_type_JavaUtilSet.add(new ElementDocking(paramle1, paramle2));
/*  232:     */      
/*  234: 228 */      d();
/*  235: 229 */      (
/*  236: 230 */        localObject1 = new Transform()).setIdentity();
/*  237:     */      
/*  238: 232 */      localObject2 = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q());
/*  239:     */      
/*  240: 234 */      ((Transform)localObject1).origin.set(((q)localObject2).a - 8, ((q)localObject2).b - 8, ((q)localObject2).c - 8);
/*  241:     */      
/*  242: 236 */      switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) {
/*  243: 237 */      case 0:  GlUtil.e(this.jdField_a_of_type_JavaxVecmathVector3f, (Transform)localObject1);break;
/*  244: 238 */      case 1:  GlUtil.d(this.jdField_a_of_type_JavaxVecmathVector3f, (Transform)localObject1);break;
/*  245: 239 */      case 2:  GlUtil.f(this.jdField_a_of_type_JavaxVecmathVector3f, (Transform)localObject1);break;
/*  246: 240 */      case 3:  GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f, (Transform)localObject1);break;
/*  247: 241 */      case 4:  GlUtil.c(this.jdField_a_of_type_JavaxVecmathVector3f, (Transform)localObject1);break;
/*  248: 242 */      case 5:  GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector3f, (Transform)localObject1);
/*  249:     */      }
/*  250: 244 */      paramle1 = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a().a().getSegmentBuffer().a();
/*  251:     */      
/*  253: 247 */      if (this.jdField_b_of_type_JavaxVecmathVector3f.length() <= 0.0F)
/*  254:     */      {
/*  256: 250 */        this.jdField_b_of_type_JavaxVecmathVector3f.set(paramle1.jdField_a_of_type_JavaxVecmathVector3f);
/*  257:     */      }
/*  258:     */      
/*  264: 258 */      if (a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to)) {
/*  265: 259 */        this.jdField_a_of_type_JavaxVecmathVector3f.scale(1.5F);
/*  266:     */      } else {
/*  267: 261 */        float f1 = 0.0F;
/*  268:     */        
/*  271: 265 */        switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) {
/*  272: 266 */        case 0:  f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;break;
/*  273: 267 */        case 1:  f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;break;
/*  274: 268 */        case 2:  f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;break;
/*  275: 269 */        case 3:  f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;break;
/*  276: 270 */        case 4:  f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;break;
/*  277: 271 */        case 5:  f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;
/*  278:     */        }
/*  279: 273 */        System.err.println("[DOCK] NOW DOCKING: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": BOUNDING BOX: " + paramle1 + "; DIST: " + f1);
/*  280: 274 */        f1 = Math.abs(f1);
/*  281: 275 */        this.jdField_a_of_type_JavaxVecmathVector3f.scale(f1 + 0.5F);
/*  282:     */      }
/*  283:     */      
/*  284: 278 */      ((Transform)localObject1).origin.add(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  285:     */      
/*  288: 282 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.onPhysicsRemove();
/*  289:     */      
/*  290: 284 */      localSegmentController.onPhysicsRemove();
/*  291:     */      
/*  292: 286 */      Object localObject3 = (CompoundShape)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape();
/*  293:     */      
/*  294: 288 */      paramle1 = (CompoundShape)localSegmentController.getPhysicsDataContainer().getShape();
/*  295:     */      
/*  297: 291 */      for (int i = 0; i < ((CompoundShape)localObject3).getNumChildShapes(); i++)
/*  298:     */      {
/*  299: 293 */        this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a();
/*  300:     */        
/*  301: 295 */        int j = 0;
/*  302: 294 */        switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) {
/*  303:     */        case 2: 
/*  304: 296 */          break;
/*  305: 297 */        case 3:  ((Transform)localObject1).basis.rotX(3.141593F);break;
/*  306: 298 */        case 4:  ((Transform)localObject1).basis.rotX(1.570796F);break;
/*  307:     */        case 5:  Matrix3f localMatrix3f;
/*  308: 300 */          (localMatrix3f = new Matrix3f()).rotY(-1.570796F);
/*  309: 301 */          ((Transform)localObject1).basis.rotZ(1.570796F);((Transform)localObject1).basis.mul(localMatrix3f);localMatrix3f.rotZ(1.570796F);((Transform)localObject1).basis.mul(localMatrix3f);break;
/*  310: 302 */        case 1:  ((Transform)localObject1).basis.rotZ(1.570796F);break;
/*  311: 303 */        case 0:  ((Transform)localObject1).basis.rotZ(-1.570796F);
/*  312:     */        }
/*  313:     */        
/*  314: 306 */        paramle1.addChildShape((Transform)localObject1, ((CompoundShape)localObject3).getChildShape(i));
/*  315:     */        
/*  316: 308 */        d.a(((Transform)localObject1).basis, this.jdField_a_of_type_JavaxVecmathQuat4f);
/*  317:     */        
/*  318: 310 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShapeChield((CompoundShapeChild)paramle1.getChildList().get(paramle1.getChildList().size() - 1));
/*  319:     */      }
/*  320:     */      
/*  323: 315 */      paramle1.recalculateLocalAabb();
/*  324: 316 */      float f2 = ((RigidBodyExt)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject()).getInvMass();
/*  325: 317 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().updateMass(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getMass(), true);
/*  326: 318 */      float f3 = ((RigidBodyExt)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject()).getInvMass();
/*  327: 319 */      System.err.println("[DOCKING] " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " MASS: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getMass() + ", invBidyMass: " + f2 + "/" + f3 + " --TO-- " + localSegmentController + " MASS: " + localSegmentController.getMass());
/*  328:     */      
/*  329: 321 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId() != localSegmentController.getSectorId()) {
/*  330: 322 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setSectorId(localSegmentController.getSectorId());
/*  331: 323 */        if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw))
/*  332:     */        {
/*  333: 325 */          localObject4 = null; for (localObject1 = ((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().iterator(); ((Iterator)localObject1).hasNext();) { localObject3 = (lE)((Iterator)localObject1).next();
/*  334: 326 */            System.err.println("[SERVER][DOCKING] sector docking on border! " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " has players attached. Doing Sector Change for " + localObject3);
/*  335:     */            
/*  336:     */            q localq;
/*  337: 329 */            if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*  338: 330 */              localq = ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().getSector(localSegmentController.getSectorId()).jdField_a_of_type_Q;
/*  339:     */            } else {
/*  340: 332 */              localq = ((mv)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(localSegmentController.getSectorId())).a();
/*  341:     */            }
/*  342:     */            
/*  343: 335 */            ((lE)localObject3).a(new q(localq));
/*  344: 336 */            ((lE)localObject3).c(localSegmentController.getSectorId());
/*  345:     */            
/*  348: 340 */            if ((localObject4 = ((lE)localObject3).a()) != null) {
/*  349: 341 */              System.err.println("[SERVER][DOCKING] sector docking on border! " + localq + " has CHARACTER. Doing Sector Change for " + localObject4 + ": ");
/*  350: 342 */              ((lD)localObject4).setSectorId(localSegmentController.getSectorId());
/*  351:     */            } else {
/*  352: 344 */              System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + localObject3);
/*  353:     */            }
/*  354:     */          }
/*  355:     */        }
/*  356:     */      }
/*  357:     */      
/*  361: 353 */      Object localObject4 = localSegmentController.getPhysics().getBodyFromShape(paramle1, localSegmentController.getMass() > 0.0F ? localSegmentController.getMass() + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getMass() : 0.0F, localSegmentController.getWorldTransform());
/*  362:     */      
/*  367: 359 */      System.err.println("[DOCKING] ADDED CHILD COMPOUND: " + paramle1.getNumChildShapes() + "; " + paramle1 + ": InvMass " + ((RigidBody)localObject4).getInvMass());
/*  368:     */      
/*  369: 361 */      ((RigidBody)localObject4).setUserPointer(Integer.valueOf(localSegmentController.getId()));
/*  370:     */      
/*  371: 363 */      if ((!jdField_c_of_type_Boolean) && (((RigidBody)localObject4).getCollisionShape() != paramle1)) { throw new AssertionError();
/*  372:     */      }
/*  373: 365 */      localSegmentController.getPhysicsDataContainer().setObject((CollisionObject)localObject4);
/*  374:     */      
/*  376: 368 */      for (localObject1 = localSegmentController.getDockingController().jdField_a_of_type_JavaUtilSet.iterator(); ((Iterator)localObject1).hasNext();) {
/*  377: 369 */        ((ElementDocking)((Iterator)localObject1).next()).from.a().a().getPhysicsDataContainer().setObject(null);
/*  378:     */      }
/*  379: 371 */      localSegmentController.getPhysicsDataContainer().updatePhysical();
/*  380: 372 */      localSegmentController.onPhysicsAdd();
/*  381:     */      
/*  382: 374 */      ((RigidBodyExt)localSegmentController.getPhysicsDataContainer().getObject()).activate(true);
/*  383:     */      
/*  386: 378 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof ka)) {
/*  387: 379 */        ((ka)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).handleNTDockChanged();
/*  388:     */      }
/*  389:     */      
/*  390: 382 */      if ((!jdField_c_of_type_Boolean) && (localSegmentController.getPhysicsDataContainer().getShape() != paramle1)) throw new AssertionError();
/*  391: 383 */      if ((!jdField_c_of_type_Boolean) && (localSegmentController.getPhysicsDataContainer().getShape() != ((RigidBody)localObject4).getCollisionShape())) throw new AssertionError();
/*  392: 384 */      System.err.println("[SEGCON] NOW DOCKED ON " + paramle2 + " " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " -> " + paramle2.a().a() + "  on " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
/*  393:     */      
/*  394: 386 */      if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a() == this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId()))
/*  395:     */      {
/*  396: 388 */        xe.a("0022_ambience sfx - ambient hangar sounds hydraulics", this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform(), 0.99F);
/*  397:     */      }
/*  398: 390 */      return true;
/*  399:     */    }
/*  400: 392 */    return false;
/*  401:     */  }
/*  402:     */  
/*  403:     */  private boolean b(le paramle1, le paramle2)
/*  404:     */  {
/*  405: 397 */    paramle1.a();
/*  406: 398 */    if (paramle1.a() == 0) {
/*  407: 399 */      System.err.println("[DOCKING] NOT DOCKING " + paramle2.a().a() + " ON NOTHING: " + paramle1 + " ON " + paramle1.a().a());
/*  408: 400 */      return false;
/*  409:     */    }
/*  410:     */    
/*  411: 403 */    if (paramle2.a().a().getFactionId() != paramle1.a().a().getFactionId()) {
/*  412: 404 */      paramle1 = null; if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a() == this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController)) {
/*  413: 405 */        ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().b("You cannot dock on a\nship of another\nfaction");
/*  414:     */      }
/*  415: 407 */      System.err.println("[DOCKING] NOT DOCKING: faction does not equal " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
/*  416: 408 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*  417:     */      {
/*  418: 410 */        if (System.currentTimeMillis() - this.jdField_b_of_type_Long > 4000L) {
/*  419: 411 */          if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw))
/*  420:     */          {
/*  421: 413 */            for (paramle1 = ((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().iterator(); paramle1.hasNext();) {
/*  422: 414 */              (paramle2 = (lE)paramle1.next()).a(new ServerMessage("You cannot dock on a\nship of another\nfaction", 3, paramle2.getId()));
/*  423:     */            }
/*  424:     */          }
/*  425: 417 */          this.jdField_b_of_type_Long = System.currentTimeMillis();
/*  426:     */        }
/*  427:     */      }
/*  428:     */    }
/*  429:     */    else
/*  430:     */    {
/*  431: 423 */      if (ElementKeyMap.getInfo(paramle1.a()).isDockable()) {
/*  432: 424 */        System.err.println("[DOCKING] " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " DOING THE DOCK TO " + paramle1.a().a());
/*  433: 425 */        return a(paramle2, paramle1);
/*  434:     */      }
/*  435: 427 */      if (!jdField_c_of_type_Boolean) { throw new AssertionError(paramle1.a());
/*  436:     */      }
/*  437:     */    }
/*  438: 430 */    return false;
/*  439:     */  }
/*  440:     */  
/*  443:     */  public final ElementDocking a()
/*  444:     */  {
/*  445: 437 */    return this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking;
/*  446:     */  }
/*  447:     */  
/*  451:     */  public final Set a()
/*  452:     */  {
/*  453: 445 */    return this.jdField_a_of_type_JavaUtilSet;
/*  454:     */  }
/*  455:     */  
/*  562:     */  public final void a(NetworkSegmentController paramNetworkSegmentController)
/*  563:     */  {
/*  564: 556 */    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*  565: 557 */      if (this.jdField_b_of_type_Boolean) {
/*  566: 558 */        synchronized (paramNetworkSegmentController) {
/*  567: 559 */          paramNetworkSegmentController.dockingSize.set(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  568: 560 */          paramNetworkSegmentController.dockedTo.set(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getUniqueIdentifier());
/*  569:     */          
/*  570: 562 */          paramNetworkSegmentController.dockedElement.set(new s(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q()), 0));
/*  571:     */        }
/*  572:     */      }
/*  573:     */      
/*  575: 567 */      paramNetworkSegmentController.dockingSize.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  576: 568 */      paramNetworkSegmentController.dockedTo.set("NONE");
/*  577:     */      
/*  578: 570 */      System.err.println("[DOCKING] SET NT DOCK TO " + (String)paramNetworkSegmentController.dockedTo.get());
/*  579:     */    }
/*  580:     */  }
/*  581:     */  
/*  583:     */  public final void a(String paramString, q paramq)
/*  584:     */  {
/*  585: 577 */    if (a())
/*  586:     */    {
/*  587: 579 */      this.jdField_a_of_type_JavaLangString = paramString;
/*  588: 580 */      this.jdField_a_of_type_Q = paramq;
/*  589:     */    }
/*  590:     */  }
/*  591:     */  
/*  592:     */  public final void b() {
/*  593: 585 */    if (a()) {
/*  594: 586 */      System.err.println("[DOCKING] REQUEST UNDOCK " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + "; " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
/*  595: 587 */      this.jdField_a_of_type_Boolean = true;
/*  596:     */    }
/*  597:     */  }
/*  598:     */  
/*  601:     */  private void a(ElementDocking paramElementDocking)
/*  602:     */  {
/*  603: 595 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking = paramElementDocking;
/*  604: 596 */    this.jdField_b_of_type_Boolean = (paramElementDocking != null);
/*  605:     */  }
/*  606:     */  
/*  609:     */  public final void a(Ah paramAh)
/*  610:     */  {
/*  611: 603 */    if ((!jdField_c_of_type_Boolean) && (!paramAh.a().equals("dock"))) { throw new AssertionError();
/*  612:     */    }
/*  613:     */    
/*  614: 606 */    String str = (String)(paramAh = (Ah[])paramAh.a())[0].a();
/*  615: 607 */    if ((paramAh.length > 4) && (paramAh[4].a() == Aj.b))
/*  616:     */    {
/*  617: 609 */      ((Byte)paramAh[4].a()).byteValue();
/*  618:     */    }
/*  619: 611 */    if (!str.equals("NONE")) {
/*  620: 612 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setHidden(true);
/*  621: 613 */      a(str, (q)paramAh[1].a());
/*  622:     */    }
/*  623:     */    
/*  629: 621 */    if ((paramAh.length > 3) && (paramAh[3].a() == Aj.j))
/*  630:     */    {
/*  634: 626 */      if ("s".equals(paramAh[3])) {
/*  635: 627 */        this.jdField_b_of_type_JavaxVecmathVector3f.set((Vector3f)paramAh[3].a());
/*  636:     */      }
/*  637:     */    }
/*  638:     */  }
/*  639:     */  
/*  644:     */  public final Ah a()
/*  645:     */  {
/*  646: 638 */    if (this.jdField_a_of_type_JavaLangString != null)
/*  647:     */      try {
/*  648: 640 */        throw new IllegalStateException("Exception DELAYED DOCK OF " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " TO " + this.jdField_a_of_type_JavaLangString + " HAS FAILED");
/*  649: 641 */      } catch (Exception localException) { localException.printStackTrace();
/*  650:     */        
/*  651: 643 */        System.err.println("Exception successfully catched: rewriting desired docking");
/*  652:     */        
/*  653: 645 */        this.jdField_a_of_type_VB = new vB(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_Q);
/*  654:     */      }
/*  655:     */    Ah localAh1;
/*  656: 648 */    if (this.jdField_a_of_type_VB == null) {
/*  657: 649 */      localAh1 = new Ah(Aj.i, "dockedTo", this.jdField_b_of_type_Boolean ? this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getUniqueIdentifier() : "NONE");
/*  658:     */    } else {
/*  659: 651 */      localAh1 = new Ah(Aj.i, "dockedTo", this.jdField_a_of_type_VB.jdField_a_of_type_JavaLangString);
/*  660:     */    }
/*  661: 653 */    if (!((String)localAh1.a()).equals("NONE")) {
/*  662: 654 */      System.err.println("WROTE DOCKED TO " + localAh1.a());
/*  663:     */    }
/*  664:     */    Ah localAh2;
/*  665: 657 */    if (this.jdField_a_of_type_VB == null) {
/*  666: 658 */      localAh2 = new Ah(Aj.k, "dockedToPos", this.jdField_b_of_type_Boolean ? this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q()) : new q());
/*  667:     */    } else {
/*  668: 660 */      localAh2 = new Ah(Aj.k, "dockedToPos", this.jdField_a_of_type_VB.jdField_a_of_type_Q);
/*  669:     */    }
/*  670:     */    
/*  671: 663 */    Ah localAh3 = new Ah(Aj.b, null, Byte.valueOf((byte)0));
/*  672: 664 */    Ah localAh4 = new Ah(Aj.b, null, Byte.valueOf((byte)0));
/*  673:     */    
/*  674: 666 */    Ah localAh5 = new Ah(Aj.j, "s", this.jdField_b_of_type_JavaxVecmathVector3f);
/*  675:     */    
/*  676: 668 */    return new Ah(Aj.n, "dock", new Ah[] { localAh1, localAh2, localAh3, localAh4, localAh5, new Ah(Aj.a, null, null) });
/*  677:     */  }
/*  678:     */  
/*  711:     */  private void c()
/*  712:     */  {
/*  713: 705 */    if (this.jdField_b_of_type_Boolean) {
/*  714:     */      ElementDocking localElementDocking;
/*  715: 707 */      if (((localElementDocking = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking).to.a() == 0) || (ElementKeyMap.getInfo(localElementDocking.to.a()).isDockable())) {
/*  716: 708 */        System.err.println("NOW UNDOCKING: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + "; " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "; DOCKED TO TYPE: " + localElementDocking.to.a());
/*  717:     */        
/*  718:     */        SegmentController localSegmentController;
/*  719:     */        
/*  720: 712 */        if (!(localSegmentController = localElementDocking.to.a().a()).getDockingController().jdField_a_of_type_JavaUtilSet.remove(localElementDocking)) {
/*  721: 713 */          System.err.println("Exception: WARNING! UNDOCK UNSUCCESSFULL " + localElementDocking + ": " + localSegmentController.getDockingController().jdField_a_of_type_JavaUtilSet);
/*  722:     */        }
/*  723: 715 */        a(null);
/*  724:     */        
/*  726: 718 */        a(localSegmentController, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  727:     */        
/*  730: 722 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setObject(null);
/*  731: 723 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShape(null);
/*  732: 724 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShapeChield(null);
/*  733: 725 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().initialTransform.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*  734: 726 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getRemoteTransformable().a().set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*  735: 727 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.initPhysics();
/*  736: 728 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.onPhysicsAdd();
/*  737:     */        
/*  738: 730 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setFlagPhysicsInit(false);
/*  739: 731 */        this.jdField_b_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*  740:     */        
/*  741: 733 */        if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof ka)) {
/*  742: 734 */          ((ka)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).handleNTDockChanged();
/*  744:     */        }
/*  745:     */        
/*  747:     */      }
/*  748: 740 */      else if (!jdField_c_of_type_Boolean) { throw new AssertionError(localElementDocking.to.a());
/*  749:     */      }
/*  750: 742 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  751:     */    }
/*  752:     */  }
/*  753:     */  
/*  754:     */  private static void a(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
/*  755:     */  {
/*  756: 748 */    paramSegmentController1.onPhysicsRemove();
/*  757:     */    
/*  760: 752 */    if (paramSegmentController1.getDockingController().jdField_a_of_type_JavaUtilSet.size() > 0)
/*  761:     */    {
/*  762:     */      CubesCompoundShape localCubesCompoundShape;
/*  763: 755 */      int i = (localCubesCompoundShape = (CubesCompoundShape)paramSegmentController1.getPhysicsDataContainer().getShape()).getNumChildShapes();
/*  764:     */      
/*  765: 757 */      localCubesCompoundShape.removeChildShape(paramSegmentController2.getPhysicsDataContainer().getShapeChild().childShape);
/*  766: 758 */      if (paramSegmentController1.getMass() > 0.0F) {
/*  767: 759 */        paramSegmentController1.getPhysicsDataContainer().updateMass(paramSegmentController1.getMass(), true);
/*  768:     */      }
/*  769:     */      
/*  770: 762 */      if (localCubesCompoundShape.getNumChildShapes() != i - 1) {
/*  771: 763 */        System.err.println("[DOCKING] UPDATING SHAPE, BUT NO SHAPE HAS BEEN REMOVED (DELETION OF DOCKED OBJECT)");
/*  772:     */      }
/*  773:     */      
/*  778: 770 */      (paramSegmentController2 = paramSegmentController1.getPhysics().getBodyFromShape(localCubesCompoundShape, paramSegmentController1.getMass(), paramSegmentController1.getWorldTransform())).setUserPointer(Integer.valueOf(paramSegmentController1.getId()));
/*  779: 771 */      if ((!jdField_c_of_type_Boolean) && (paramSegmentController2.getCollisionShape() != localCubesCompoundShape)) { throw new AssertionError();
/*  780:     */      }
/*  781:     */      
/*  783: 775 */      paramSegmentController1.getPhysicsDataContainer().setObject(paramSegmentController2);
/*  784:     */      
/*  786: 778 */      for (Iterator localIterator = paramSegmentController1.getDockingController().jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext();) {
/*  787: 779 */        ((ElementDocking)localIterator.next()).from.a().a().getPhysicsDataContainer().setObject(null);
/*  788:     */      }
/*  789:     */      
/*  790: 782 */      if ((!jdField_c_of_type_Boolean) && (paramSegmentController1.getPhysicsDataContainer().getShape() != localCubesCompoundShape)) throw new AssertionError();
/*  791: 783 */      if ((!jdField_c_of_type_Boolean) && (paramSegmentController1.getPhysicsDataContainer().getShape() != paramSegmentController2.getCollisionShape())) { throw new AssertionError();
/*  792:     */      }
/*  793: 785 */      paramSegmentController1.onPhysicsAdd();
/*  794: 786 */      if (paramSegmentController1.getMass() > 0.0F) {
/*  795: 787 */        paramSegmentController1.getPhysicsDataContainer().updateMass(paramSegmentController1.getMass(), true);
/*  796:     */      }
/*  797: 789 */      return; }
/*  798: 790 */    System.err.println("[DOCKING] doing complete physics reset for " + paramSegmentController1);
/*  799: 791 */    paramSegmentController1.getPhysicsDataContainer().setObject(null);
/*  800: 792 */    paramSegmentController1.getPhysicsDataContainer().setShape(null);
/*  801: 793 */    paramSegmentController1.getPhysicsDataContainer().setShapeChield(null);
/*  802: 794 */    paramSegmentController1.getPhysicsDataContainer().initialTransform.set(paramSegmentController1.getWorldTransform());
/*  803: 795 */    paramSegmentController1.getRemoteTransformable().a().set(paramSegmentController1.getWorldTransform());
/*  804: 796 */    paramSegmentController1.initPhysics();
/*  805:     */    
/*  806: 798 */    paramSegmentController1.onPhysicsAdd();
/*  807:     */  }
/*  808:     */  
/*  930: 922 */  private final Quat4f jdField_b_of_type_JavaxVecmathQuat4f = new Quat4f();
/*  931: 923 */  private final Quat4f jdField_c_of_type_JavaxVecmathQuat4f = new Quat4f();
/*  932:     */  
/*  945:     */  public vB a;
/*  946:     */  
/*  960:     */  private void d()
/*  961:     */  {
/*  962: 954 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  963:     */    
/*  964: 956 */    switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) {
/*  965: 957 */    case 0:  GlUtil.e(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);break;
/*  966: 958 */    case 1:  GlUtil.d(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);break;
/*  967: 959 */    case 2:  GlUtil.f(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);break;
/*  968: 960 */    case 3:  GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);break;
/*  969: 961 */    case 4:  GlUtil.c(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);break;
/*  970: 962 */    case 5:  GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  971:     */    }
/*  972: 964 */    xO localxO = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a().a().getSegmentBuffer().a();
/*  973:     */    Vector3f localVector3f;
/*  974: 966 */    (localVector3f = new Vector3f()).sub(localxO.jdField_b_of_type_JavaxVecmathVector3f, localxO.jdField_a_of_type_JavaxVecmathVector3f);
/*  975: 967 */    if (a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to)) {
/*  976: 968 */      this.jdField_a_of_type_JavaxVecmathVector3f.scale(4.5F);
/*  977:     */    } else {
/*  978: 970 */      this.jdField_a_of_type_JavaxVecmathVector3f.scale(localVector3f.y / 2.0F);
/*  979:     */    }
/*  980:     */    
/*  983: 975 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  984:     */    
/*  985: 977 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  986: 978 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.inverse();
/*  987:     */  }
/*  988:     */  
/*  990:     */  public final void b(NetworkSegmentController paramNetworkSegmentController)
/*  991:     */  {
/*  992: 984 */    if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*  993:     */    {
/*  994: 986 */      paramNetworkSegmentController.dockingSize.getVector(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  995:     */      
/*  996: 988 */      String str = (String)paramNetworkSegmentController.dockedTo.get();
/*  997: 989 */      int i = (!this.jdField_b_of_type_Boolean) && (!str.equals("NONE")) ? 1 : 0;
/*  998:     */      
/*  999: 991 */      int j = (this.jdField_b_of_type_Boolean) && (!str.equals("NONE")) && (!str.equals(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getUniqueIdentifier())) ? 1 : 0;
/* 1000:     */      
/* 1002: 994 */      int k = (this.jdField_b_of_type_Boolean) && (str.equals("NONE")) ? 1 : 0;
/* 1003:     */      
/* 1004: 996 */      if ((i != 0) || (j != 0))
/* 1005:     */      {
/* 1006: 998 */        paramNetworkSegmentController = paramNetworkSegmentController.dockedElement.getVector();
/* 1007: 999 */        a(str, new q(paramNetworkSegmentController.a, paramNetworkSegmentController.b, paramNetworkSegmentController.c));
/* 1008:     */      }
/* 1009:1001 */      if (k != 0)
/* 1010:     */      {
/* 1011:1003 */        b();
/* 1012:     */      }
/* 1013:     */    }
/* 1014:     */  }
/* 1015:     */  
/* 1018:     */  public final void a(xq paramxq)
/* 1019:     */  {
/* 1020:     */    try
/* 1021:     */    {
/* 1022:1014 */      xq localxq1 = paramxq;paramxq = this; if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) || (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a().a()) || (System.currentTimeMillis() - paramxq.jdField_c_of_type_Long < 1000L)) return; Object localObject1; Object localObject2; if (paramxq.jdField_a_of_type_JavaLangString != null) { localTransform = null; synchronized (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects()) { for (localObject1 = paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); ((Iterator)localObject1).hasNext(); ) if ((((localObject2 = (Sendable)((Iterator)localObject1).next()) instanceof SegmentController)) && (((SegmentController)localObject2).getUniqueIdentifier().equals(paramxq.jdField_a_of_type_JavaLangString))) { if ((!(localObject1 = (SegmentController)localObject2).getPhysicsDataContainer().isInitialized()) || (!((SegmentController)localObject1).getSegmentBuffer().a().a()) || (!((SegmentController)localObject1).getSegmentBuffer().a().c())) { System.err.println("[DOCKING] TARGET PHYSICS NOT YET INITIALIZED: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT"));paramxq.jdField_c_of_type_Long = System.currentTimeMillis();return; } if ((!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) || (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a().a()) || (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a().c())) { System.err.println("[DOCKING] SELF PHYSICS NOT YET INITIALIZED: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT"));paramxq.jdField_c_of_type_Long = System.currentTimeMillis();return; } if ((((SegmentController)localObject1).getTotalElements() <= 0) || (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getTotalElements() <= 0)) System.err.println("[DOCKING][LANDING] Object has zero elements: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT")); } } } if (paramxq.jdField_a_of_type_Boolean) { localTransform = null;System.err.println("[DOCKING] Delayed undock requested on " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "Server" : "Client"));paramxq.c();paramxq.jdField_a_of_type_Boolean = false; } Transform localTransform = null; if ((!paramxq.jdField_a_of_type_JavaUtilSet.isEmpty()) && (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized())) { if ((!jdField_c_of_type_Boolean) && (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape() != paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape())) throw new AssertionError(paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape() + "; " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape()); ??? = null; for (localObject1 = paramxq.jdField_a_of_type_JavaUtilSet.iterator(); ((Iterator)localObject1).hasNext(); ((ElementDocking)localObject2).from.a().a().getPhysicsDataContainer().updatePhysical(paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject())) { if (((localObject2 = (ElementDocking)((Iterator)localObject1).next()) == null) || (((ElementDocking)localObject2).from == null)) { if (!jdField_c_of_type_Boolean) throw new AssertionError(); throw new NullPointerException("Invalid docking: " + localObject2); } if (((ElementDocking)localObject2).from.a().a().getPhysicsDataContainer().isInitialized()) if (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((ElementDocking)localObject2).from.a().a().getId())) { System.err.println("[DOCKING] UPDATING " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " MASS BECAUSE DOCKED SHIP DOESNT EXIST ANYMORE: " + ((ElementDocking)localObject2).from.a().a());a(paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, ((ElementDocking)localObject2).from.a().a());??? = localObject2; } else { xq localxq2 = localxq1; jv localjv; if ((localjv = ((ElementDocking)localObject2).from.a().a().getDockingController()).jdField_b_of_type_Boolean) { localjv.d(); if (a(localjv.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to)) { d.a((localTransform = localjv.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShapeChild().transform).basis, localjv.jdField_b_of_type_JavaxVecmathQuat4f);localjv.jdField_b_of_type_JavaxVecmathQuat4f.normalize();localjv.jdField_a_of_type_JavaxVecmathQuat4f.normalize(); if (localjv.jdField_a_of_type_JavaxVecmathQuat4f.w != 0.0F) { if (localjv.jdField_b_of_type_JavaxVecmathQuat4f.w == 0.0F) { localjv.jdField_c_of_type_JavaxVecmathQuat4f.set(localjv.jdField_a_of_type_JavaxVecmathQuat4f); } else { d.a(localjv.jdField_b_of_type_JavaxVecmathQuat4f, localjv.jdField_a_of_type_JavaxVecmathQuat4f, Math.min(1.0F, localxq2.a() * 50.0F), localjv.jdField_c_of_type_JavaxVecmathQuat4f);localjv.jdField_c_of_type_JavaxVecmathQuat4f.normalize(); } localTransform.basis.set(localjv.jdField_c_of_type_JavaxVecmathQuat4f); } } } } if (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject() == null) throw new NullPointerException("Tried chain update"); } if (??? != null) paramxq.jdField_a_of_type_JavaUtilSet.remove(???); ((CompoundShape)paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape()).recalculateLocalAabb(); } if ((paramxq.jdField_b_of_type_Boolean) && (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramxq.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getId()))) { System.err.println("[DOCKING] undocking this " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " because mothership is deleted: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a());paramxq.c();
/* 1023:     */      }
/* 1024:     */      
/* 1029:1021 */      return;
/* 1030:     */    }
/* 1031:     */    catch (IOException localIOException)
/* 1032:     */    {
/* 1033:1016 */      (paramxq = 
/* 1034:     */      
/* 1038:1021 */        localIOException).printStackTrace();throw new ErrorDialogException(paramxq.getMessage());
/* 1039:     */    } catch (InterruptedException localInterruptedException) {
/* 1040:1019 */      (paramxq = localInterruptedException).printStackTrace();
/* 1041:1020 */      throw new ErrorDialogException(paramxq.getMessage());
/* 1042:     */    }
/* 1043:     */  }
/* 1044:     */  
/* 1049:     */  public final boolean b()
/* 1050:     */  {
/* 1051:1030 */    return this.jdField_b_of_type_Boolean;
/* 1052:     */  }
/* 1053:     */  
/* 1065:     */  public final Vector3f a()
/* 1066:     */  {
/* 1067:1046 */    return this.jdField_b_of_type_JavaxVecmathVector3f;
/* 1068:     */  }
/* 1069:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */