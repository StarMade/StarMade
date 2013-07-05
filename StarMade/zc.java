/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class zc extends yW
/*    */ {
/* 14 */   private Vector3f a = new Vector3f();
/*    */ 
/* 16 */   private Vector3f b = new Vector3f();
/*    */ 
/*    */   public zc()
/*    */   {
/* 24 */     super(16);
/*    */   }
/*    */ 
/*    */   public boolean a(int paramInt, xq paramxq)
/*    */   {
/* 43 */     paramxq = this.a.a(paramInt);
/* 44 */     this.a.a(paramInt, this.a);
/* 45 */     this.a.d(paramInt, this.b);
/*    */ 
/* 47 */     this.a.a(paramInt, this.a.x + this.b.x, this.a.y + this.b.y, this.a.z + this.b.z);
/* 48 */     return paramxq < 1300.0F;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zc
 * JD-Core Version:    0.6.2
 */