/*    */ import com.bulletphysics.collision.shapes.BoxShape;
/*    */ import com.bulletphysics.dynamics.RigidBody;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.controller.elements.lift.LiftUnit;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.graphicsengine.forms.Mesh;
/*    */ 
/*    */ public final class ex
/*    */   implements xg
/*    */ {
/*    */   public final ArrayList a;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */   private Mesh jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
/* 29 */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 30 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   private zx jdField_a_of_type_Zx;
/*    */ 
/*    */   public ex()
/*    */   {
/* 26 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 40 */     if (!this.jdField_a_of_type_Boolean) {
/* 41 */       c();
/*    */     }
/*    */ 
/* 44 */     for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++)
/* 45 */       if (!((LiftUnit)this.jdField_a_of_type_JavaUtilArrayList.get(i)).isActive()) {
/* 46 */         this.jdField_a_of_type_JavaUtilArrayList.remove(i);
/* 47 */         i--;
/*    */       }
/*    */       else
/*    */       {
/* 51 */         LiftUnit localLiftUnit = (LiftUnit)this.jdField_a_of_type_JavaUtilArrayList.get(i);
/* 52 */         d.G.c();
/* 53 */         this.jdField_a_of_type_Zx.a();
/* 54 */         if (localLiftUnit.getBody() != null) {
/* 55 */           localLiftUnit.getBody().getWorldTransform(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 56 */           GlUtil.d();
/* 57 */           GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*    */ 
/* 59 */           ((BoxShape)localLiftUnit.getBody().getCollisionShape())
/* 60 */             .getHalfExtentsWithoutMargin(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 61 */           GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f.x * 2.0F, this.jdField_a_of_type_JavaxVecmathVector3f.y * 2.0F, this.jdField_a_of_type_JavaxVecmathVector3f.z * 2.0F);
/*    */ 
/* 63 */           this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
/*    */ 
/* 67 */           GlUtil.c();
/*    */         }
/* 69 */         this.jdField_a_of_type_Zx.c();
/* 70 */         zj.e();
/*    */       }
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 81 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)xe.a().a("Box").a().get(0));
/*    */ 
/* 84 */     this.jdField_a_of_type_Zx = new zx();
/* 85 */     this.jdField_a_of_type_Zx.f();
/* 86 */     this.jdField_a_of_type_Zx.c(new float[] { 1.3F, 1.3F, 1.3F, 1.0F });
/* 87 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ex
 * JD-Core Version:    0.6.2
 */