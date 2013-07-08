/*  1:   */import com.bulletphysics.collision.shapes.BoxShape;
/*  2:   */import com.bulletphysics.dynamics.RigidBody;
/*  3:   */import com.bulletphysics.linearmath.Transform;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.List;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */import org.schema.game.common.controller.elements.lift.LiftUnit;
/*  8:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  9:   */import org.schema.schine.graphicsengine.forms.Mesh;
/* 10:   */
/* 17:   */public final class ex
/* 18:   */  implements xg
/* 19:   */{
/* 20:   */  public final ArrayList a;
/* 21:   */  private boolean jdField_a_of_type_Boolean;
/* 22:   */  private Mesh jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
/* 23:   */  
/* 24:   */  public ex()
/* 25:   */  {
/* 26:26 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 27:   */  }
/* 28:   */  
/* 29:29 */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 30:30 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 31:   */  
/* 32:   */  private zB jdField_a_of_type_ZB;
/* 33:   */  
/* 35:   */  public final void a() {}
/* 36:   */  
/* 38:   */  public final void b()
/* 39:   */  {
/* 40:40 */    if (!this.jdField_a_of_type_Boolean) {
/* 41:41 */      c();
/* 42:   */    }
/* 43:   */    
/* 44:44 */    for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/* 45:45 */      if (!((LiftUnit)this.jdField_a_of_type_JavaUtilArrayList.get(i)).isActive()) {
/* 46:46 */        this.jdField_a_of_type_JavaUtilArrayList.remove(i);
/* 47:47 */        i--;
/* 48:   */      }
/* 49:   */      else
/* 50:   */      {
/* 51:51 */        LiftUnit localLiftUnit = (LiftUnit)this.jdField_a_of_type_JavaUtilArrayList.get(i);
/* 52:52 */        zk.z.c();
/* 53:53 */        this.jdField_a_of_type_ZB.a();
/* 54:54 */        if (localLiftUnit.getBody() != null) {
/* 55:55 */          localLiftUnit.getBody().getWorldTransform(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 56:56 */          GlUtil.d();
/* 57:57 */          GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 58:   */          
/* 59:59 */          ((BoxShape)localLiftUnit.getBody().getCollisionShape())
/* 60:60 */            .getHalfExtentsWithoutMargin(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 61:61 */          GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f.x * 2.0F, this.jdField_a_of_type_JavaxVecmathVector3f.y * 2.0F, this.jdField_a_of_type_JavaxVecmathVector3f.z * 2.0F);
/* 62:   */          
/* 63:63 */          this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
/* 64:   */          
/* 67:67 */          GlUtil.c();
/* 68:   */        }
/* 69:69 */        this.jdField_a_of_type_ZB.c();
/* 70:70 */        zj.e();
/* 71:   */      }
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 79:   */  public final void c()
/* 80:   */  {
/* 81:81 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)xe.a().a("Box").a().get(0));
/* 82:   */    
/* 84:84 */    this.jdField_a_of_type_ZB = new zB();
/* 85:85 */    this.jdField_a_of_type_ZB.f();
/* 86:86 */    this.jdField_a_of_type_ZB.c(new float[] { 1.3F, 1.3F, 1.3F, 1.0F });
/* 87:87 */    this.jdField_a_of_type_Boolean = true;
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */