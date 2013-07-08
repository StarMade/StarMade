/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import java.io.PrintStream;
/*   4:    */import javax.vecmath.Tuple3f;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.common.data.world.Universe;
/*   7:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   8:    */import org.schema.schine.network.NetworkStateContainer;
/*   9:    */import org.schema.schine.network.StateInterface;
/*  10:    */import org.schema.schine.network.objects.Sendable;
/*  11:    */
/*  16:    */public abstract class lp
/*  17:    */  extends ln
/*  18:    */{
/*  19: 19 */  private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  20:    */  protected mF a;
/*  21: 21 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  22: 22 */  private Vector3f c = new Vector3f();
/*  23:    */  
/*  24:    */  public lp(StateInterface paramStateInterface)
/*  25:    */  {
/*  26: 26 */    super(paramStateInterface);
/*  27: 27 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  28: 28 */    this.jdField_a_of_type_Float = 15.0F;
/*  29:    */  }
/*  30:    */  
/*  38:    */  public final void a(xq paramxq)
/*  39:    */  {
/*  40: 40 */    c(paramxq);
/*  41: 41 */    d(paramxq);
/*  42:    */    
/*  43: 43 */    super.a(paramxq);
/*  44:    */  }
/*  45:    */  
/*  47:    */  private void d(xq paramxq)
/*  48:    */  {
/*  49:    */    Object localObject;
/*  50:    */    
/*  51: 51 */    if (this.a != null) {
/*  52: 52 */      if (!this.b)
/*  53:    */      {
/*  54: 54 */        if (((localObject = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_a_of_type_Int)) != null) && ((localObject instanceof mv)))
/*  55:    */        {
/*  57: 57 */          localObject = (mv)localObject;
/*  58: 58 */          this.a.calcWorldTransformRelative(((mv)localObject).getId(), ((mv)localObject).a());
/*  59: 59 */          this.c.sub(this.a.getClientTransform().origin, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/*  60:    */        }
/*  61:    */        
/*  63:    */      }
/*  64: 64 */      else if ((localObject = ((vg)getState()).a().getSector(this.jdField_a_of_type_Int)) != null) {
/*  65: 65 */        this.a.calcWorldTransformRelative(((mx)localObject).a(), ((mx)localObject).a);
/*  66:    */        
/*  67: 67 */        this.c.sub(this.a.getClientTransform().origin, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/*  68:    */      } else {
/*  69: 69 */        this.jdField_a_of_type_Boolean = false;
/*  70:    */      }
/*  71:    */      
/*  72: 72 */      if (this.c.lengthSquared() != 0.0F) {
/*  73: 73 */        GlUtil.a(new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f), new Vector3f(this.c), this.jdField_b_of_type_JavaxVecmathVector3f);
/*  74:    */        
/*  76: 76 */        this.jdField_b_of_type_JavaxVecmathVector3f.normalize();
/*  77: 77 */        this.jdField_b_of_type_JavaxVecmathVector3f.scale(paramxq.a() * 2.07F);
/*  78: 78 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  79: 79 */        this.jdField_a_of_type_JavaxVecmathVector3f.normalize();
/*  80:    */      }
/*  81:    */    }
/*  82:    */    
/*  88: 88 */    (localObject = new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f)).scale(paramxq.a() * 25.0F);
/*  89:    */    
/*  90: 90 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  91: 91 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject);
/*  92:    */    
/*  93: 93 */    a(this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*  94:    */  }
/*  95:    */  
/*  99:    */  public final void b(xq paramxq)
/* 100:    */  {
/* 101:101 */    d(paramxq);
/* 102:    */  }
/* 103:    */  
/* 108:    */  public abstract void c(xq paramxq);
/* 109:    */  
/* 113:    */  public final void c(int paramInt)
/* 114:    */  {
/* 115:115 */    if (paramInt > 0) {
/* 116:    */      Sendable localSendable;
/* 117:117 */      if (((localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().get(paramInt)) != null) && ((localSendable instanceof mF))) {
/* 118:118 */        this.a = ((mF)localSendable);return;
/* 119:    */      }
/* 120:120 */      System.err.println("Exception: target is not known: ID: " + paramInt + "; " + getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects());
/* 121:    */      
/* 122:122 */      return; }
/* 123:123 */    this.a = null;
/* 124:    */  }
/* 125:    */  
/* 128:    */  public final void a(lu paramlu)
/* 129:    */  {
/* 130:130 */    super.a(paramlu);
/* 131:131 */    c(paramlu.jdField_a_of_type_Int);
/* 132:    */  }
/* 133:    */  
/* 139:    */  public final mF a()
/* 140:    */  {
/* 141:141 */    return this.a;
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */