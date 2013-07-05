/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public class yr extends yz
/*    */ {
/*    */   protected float a;
/*    */   protected float b;
/*    */ 
/*    */   public yr(ClientState paramClientState)
/*    */   {
/* 14 */     super(paramClientState);
/*    */   }
/*    */   public yr(ClientState paramClientState, float paramFloat1, float paramFloat2) {
/* 17 */     this(paramClientState);
/* 18 */     this.b = paramFloat1;
/* 19 */     this.a = paramFloat2;
/*    */   }
/*    */ 
/*    */   public void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected final void d()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void b()
/*    */   {
/* 34 */     GlUtil.d();
/* 35 */     r();
/* 36 */     for (Iterator localIterator = this.a.iterator(); localIterator.hasNext(); ) ((xM)localIterator.next())
/* 37 */         .b();
/*    */ 
/* 40 */     if (this.g) {
/* 41 */       l();
/*    */     }
/*    */ 
/* 46 */     GlUtil.c();
/*    */   }
/*    */ 
/*    */   public float a()
/*    */   {
/* 52 */     return this.a;
/*    */   }
/*    */ 
/*    */   public float b()
/*    */   {
/* 57 */     return this.b;
/*    */   }
/*    */ 
/*    */   public void c()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yr
 * JD-Core Version:    0.6.2
 */