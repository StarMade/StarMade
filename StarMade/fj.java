/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ final class fj extends yD
/*    */ {
/*    */   private lE a;
/*    */ 
/*    */   public fj(fi paramfi, lE paramlE, ClientState paramClientState)
/*    */   {
/* 36 */     super(new fk(paramClientState), new fk(paramClientState), paramClientState);
/*    */ 
/* 38 */     this.a = paramlE;
/*    */ 
/* 40 */     a(null);
/* 41 */     this.a = paramlE;
/*    */ 
/* 43 */     fi.a(paramfi).put(paramlE, this);
/*    */   }
/*    */ 
/*    */   public final boolean equals(Object paramObject)
/*    */   {
/* 51 */     return this.a.equals(((fj)paramObject).a);
/*    */   }
/*    */ 
/*    */   public final int hashCode()
/*    */   {
/* 59 */     return this.a.getId();
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 64 */     ((fk)this.a).a(this.a.getName(), String.valueOf(this.a.f()), String.valueOf(this.a.e()), String.valueOf(this.a.g()), String.valueOf(this.a.h()));
/* 65 */     ((fk)this.b).a(this.a.getName(), String.valueOf(this.a.f()), String.valueOf(this.a.e()), String.valueOf(this.a.g()), String.valueOf(this.a.h()));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fj
 * JD-Core Version:    0.6.2
 */