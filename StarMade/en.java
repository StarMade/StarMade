/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
/*    */ 
/*    */ public final class en
/*    */   implements Comparable
/*    */ {
/* 15 */   long jdField_a_of_type_Long = -1L;
/*    */   private ElementCollection jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection;
/* 17 */   float jdField_a_of_type_Float = 0.0F;
/*    */   private final eF jdField_a_of_type_EF;
/*    */ 
/*    */   public en(kd paramkd, ElementCollection paramElementCollection)
/*    */   {
/* 22 */     this.jdField_a_of_type_EF = new eF();
/* 23 */     ElementCollection localElementCollection = paramElementCollection; paramElementCollection = paramkd; paramkd = this; this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection = localElementCollection; if ((localElementCollection instanceof PointDistributionCustomOutputUnit)) paramkd.jdField_a_of_type_EF.a(paramElementCollection, ((PointDistributionCustomOutputUnit)localElementCollection).getOutput()); else paramkd.jdField_a_of_type_EF.a(paramElementCollection, localElementCollection.getSignificator()); paramkd.jdField_a_of_type_Float = 0.0F; paramkd.jdField_a_of_type_Long = -1L;
/*    */   }
/*    */ 
/*    */   public final boolean equals(Object paramObject)
/*    */   {
/* 35 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection.equals(((en)paramObject).jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection);
/*    */   }
/*    */ 
/*    */   public final int hashCode()
/*    */   {
/* 43 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection.hashCode();
/*    */   }
/*    */   public final boolean a() {
/* 46 */     if ((this.jdField_a_of_type_Long < 0L) || ((float)(System.currentTimeMillis() - this.jdField_a_of_type_Long) > 200.0F)) {
/* 47 */       return false;
/*    */     }
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */     long l;
/* 64 */     if (((float)(
/* 64 */       l = System.currentTimeMillis() - this.jdField_a_of_type_Long) > 
/* 64 */       160.0F) && ((float)l < 200.0F)) {
/* 65 */       this.jdField_a_of_type_Long = (System.currentTimeMillis() + 160L); return;
/* 66 */     }if ((float)l >= 200.0F)
/* 67 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq) {
/* 71 */     this.jdField_a_of_type_Float = ((float)(this.jdField_a_of_type_Float + paramxq.a() / 1000.0F * ((Math.random() + 9.999999747378752E-005D) / 0.1000000014901161D)));
/* 72 */     if (this.jdField_a_of_type_Float > 1.0F)
/* 73 */       this.jdField_a_of_type_Float = 0.0F;
/*    */   }
/*    */ 
/*    */   public final void a(Transform paramTransform, Vector3f paramVector3f)
/*    */   {
/* 82 */     this.jdField_a_of_type_EF.a(paramTransform, paramVector3f);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     en
 * JD-Core Version:    0.6.2
 */