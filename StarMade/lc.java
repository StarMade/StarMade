/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import org.schema.game.network.objects.NetworkSegmentController;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*    */ 
/*    */ public final class lc
/*    */   implements Runnable
/*    */ {
/*    */   private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*    */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*    */   private float jdField_a_of_type_Float;
/*    */   private float b;
/*    */   private lb jdField_a_of_type_Lb;
/*    */   private EditableSendableSegmentController jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */ 
/*    */   public lc(EditableSendableSegmentController paramEditableSendableSegmentController, Transform paramTransform, float paramFloat1, float paramFloat2, lb paramlb)
/*    */   {
/* 33 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
/* 34 */     this.jdField_a_of_type_Float = paramFloat1;
/* 35 */     this.b = paramFloat2;
/* 36 */     this.jdField_a_of_type_Lb = paramlb;
/* 37 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController = paramEditableSendableSegmentController;
/*    */   }
/*    */ 
/*    */   public final void run()
/*    */   {
/* 42 */     new HashSet();
/*    */ 
/* 44 */     long l1 = System.currentTimeMillis();
/*    */     try
/*    */     {
/* 48 */       this.jdField_a_of_type_JavaUtilArrayList = this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getRadius(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_a_of_type_Float);
/*    */ 
/* 50 */       long l2 = System.currentTimeMillis() - l1;
/* 51 */       Vector3f localVector3f = new Vector3f(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/*    */ 
/* 55 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getWorldTransformInverse().transform(localVector3f);
/* 56 */       localVector3f.x += 8.0F;
/* 57 */       localVector3f.y += 8.0F;
/* 58 */       localVector3f.z += 8.0F;
/* 59 */       for (Iterator localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); ) ((Segment)localIterator.next())
/* 60 */           .a(this.b, localVector3f, this.jdField_a_of_type_Float);
/*    */ 
/* 63 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getSegmentBuffer().a(kd.a, true).a() <= 0) {
/* 64 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.setFlagCoreDestroyedByExplosion(this.jdField_a_of_type_Lb != null ? this.jdField_a_of_type_Lb : new Object());
/*    */       }
/* 66 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.setFlagCharacterExitCheckByExplosion(true);
/*    */ 
/* 69 */       long l3 = System.currentTimeMillis() - l1 - l2;
/*    */ 
/* 71 */       long l4 = System.currentTimeMillis() - l1 - l2 - l3;
/*    */ 
/* 73 */       System.err.println("[EXPLOSION] " + this + " SERVER EXPLOSION HANDLE: TOTAL: " + (System.currentTimeMillis() - l1) + " HITS:" + this.jdField_a_of_type_JavaUtilArrayList.size() + ": " + this.jdField_a_of_type_Lb + "->" + this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController + "; ExplosionRadius " + this.jdField_a_of_type_Float + " TIMES RADIUS: " + l2 + "; HANDLE: " + l3 + "; REMOTE: " + l4 + ": dam 0; set 0; rem 0");
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/* 80 */       localIOException.printStackTrace();
/*    */     }
/*    */     catch (InterruptedException localInterruptedException)
/*    */     {
/* 80 */       localInterruptedException.printStackTrace();
/*    */     }
/*    */ 
/* 81 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 85 */     for (Segment localSegment : this.jdField_a_of_type_JavaUtilArrayList)
/* 86 */       synchronized (this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject()) {
/* 87 */         ((mw)localSegment).a(System.currentTimeMillis());
/* 88 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().dirtySegmentBuffer.add(new RemoteVector3i(localSegment.a, this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject()));
/*    */       }
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 97 */     return this.jdField_a_of_type_Boolean;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lc
 * JD-Core Version:    0.6.2
 */