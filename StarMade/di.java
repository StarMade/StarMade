/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.opengl.GL13;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ 
/*    */ public final class di
/*    */   implements zn
/*    */ {
/*    */   private int a;
/*    */ 
/*    */   public di(int paramInt)
/*    */   {
/* 14 */     this.a = paramInt;
/*    */   }
/*    */ 
/*    */   public final void d()
/*    */   {
/* 19 */     GL11.glBindTexture(3553, 0);
/*    */   }
/*    */ 
/*    */   public final void a(zj paramzj)
/*    */   {
/* 30 */     GlUtil.a(paramzj, "selectionColor", 0.7F, 0.7F, 0.7F, 1.0F);
/*    */ 
/* 32 */     GL13.glActiveTexture(33984);
/* 33 */     GL11.glBindTexture(3553, this.a);
/* 34 */     GlUtil.a(paramzj, "mainTexA", 0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     di
 * JD-Core Version:    0.6.2
 */