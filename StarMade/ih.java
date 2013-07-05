/*    */ import javax.vecmath.Vector3f;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class ih extends yz
/*    */ {
/*    */   private ct a;
/*    */ 
/*    */   public ih(ClientState paramClientState)
/*    */   {
/* 18 */     super(paramClientState);
/* 19 */     this.a = ((ct)paramClientState);
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/*    */     kd localkd;
/* 30 */     if ((
/* 30 */       localkd = this.a.a()) != null)
/*    */     {
/* 32 */       Vector3f localVector3f1 = new Vector3f(1.0F, 0.0F, 0.0F);
/* 33 */       Vector3f localVector3f2 = new Vector3f(0.0F, 1.0F, 0.0F);
/* 34 */       Vector3f localVector3f3 = new Vector3f(0.0F, -1.0F, 0.0F);
/*    */ 
/* 36 */       Vector3f localVector3f4 = GlUtil.e(new Vector3f(), localkd.getWorldTransform());
/* 37 */       Vector3f localVector3f5 = GlUtil.f(new Vector3f(), localkd.getWorldTransform());
/* 38 */       GlUtil.b(new Vector3f(), localkd.getWorldTransform());
/*    */ 
/* 40 */       float f = localVector3f2.dot(localVector3f5);
/* 41 */       localVector3f3.dot(localVector3f5);
/*    */ 
/* 45 */       f = (f + 1.0F) / 2.0F * 
/* 45 */         128.0F;
/*    */ 
/* 51 */       localVector3f1.dot(localVector3f4);
/*    */ 
/* 53 */       GL11.glBegin(1);
/* 54 */       GlUtil.a(1.0F, 1.0F, 1.0F, 0.4F);
/* 55 */       GL11.glVertex2f(0.0F, f);
/* 56 */       GL11.glVertex2f(128.0F, f);
/* 57 */       GL11.glEnd();
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 68 */     return 128.0F;
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 73 */     return 128.0F;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ih
 * JD-Core Version:    0.6.2
 */