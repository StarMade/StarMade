/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import org.schema.game.common.data.element.ControlElementMap;
/*    */ import org.schema.game.server.controller.GameServerController;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.SynchronizationContainerController;
/*    */ 
/*    */ public final class vy extends vD
/*    */ {
/*    */   private int b;
/*    */ 
/*    */   public vy(StateInterface paramStateInterface, vc paramvc, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, q paramq1, q paramq2, String paramString3)
/*    */   {
/* 26 */     super(paramStateInterface, paramvc, paramString1, paramString2, paramArrayOfFloat, paramq1, paramq2, paramString3);
/* 27 */     this.jdField_b_of_type_Int = paramInt;
/*    */   }
/*    */ 
/*    */   public final void a(int paramInt)
/*    */   {
/* 36 */     ve.a(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_a_of_type_JavaLangString);
/* 37 */     String str3 = this.c; int i2 = this.jdField_b_of_type_Q.c; int i1 = this.jdField_b_of_type_Q.jdField_b_of_type_Int; int n = this.jdField_b_of_type_Q.a; int m = this.jdField_a_of_type_Q.c; int k = this.jdField_a_of_type_Q.jdField_b_of_type_Int; int j = this.jdField_a_of_type_Q.a; float[] arrayOfFloat = this.jdField_a_of_type_ArrayOfFloat; String str2 = this.jdField_b_of_type_JavaLangString; int i = paramInt; String str1 = this.jdField_a_of_type_JavaLangString; paramInt = this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*    */     jy localjy;
/* 37 */     (localjy = new jy(paramInt)).setUniqueIdentifier(str1); localjy.getMinPos().b(new q(j, k, m)); localjy.getMaxPos().b(new q(n, i1, i2)); localjy.setId(paramInt.getNextFreeObjectId()); localjy.setSectorId(i); localjy.setRealName(str2); localjy.initialize(); localjy.getInitialTransform().setFromOpenGLMatrix(arrayOfFloat); localjy.setSpawner(str3); (
/* 38 */       paramInt = localjy)
/* 38 */       .setFactionId(this.jdField_b_of_type_Int);
/* 39 */     paramInt.getControlElementMap().set(this.jdField_a_of_type_Vc.a());
/* 40 */     ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a().addNewSynchronizedObjectQueued(paramInt);
/*    */   }
/*    */ 
/*    */   public final void a(q paramq, vg paramvg, int paramInt, ObjectArrayList paramObjectArrayList)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vy
 * JD-Core Version:    0.6.2
 */