/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import org.schema.schine.graphicsengine.camera.Camera;
/*    */ 
/*    */ public final class ds extends Camera
/*    */ {
/*    */   private Transform a;
/*    */ 
/*    */   public ds(yh paramyh)
/*    */   {
/* 23 */     super(new dC(paramyh));
/* 24 */     ((xb)a()).a(paramyh);
/* 25 */     this.a = new Transform();
/*    */ 
/* 27 */     this.a.setIdentity();
/*    */ 
/* 29 */     this.a = new wX(this);
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 89 */     ((wX)this.a).a.set(this.a);
/* 90 */     super.a(paramxq);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ds
 * JD-Core Version:    0.6.2
 */