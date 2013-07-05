/*    */ import java.util.LinkedList;
/*    */ 
/*    */ final class je
/*    */ {
/*  9 */   LinkedList a = new LinkedList();
/*    */ 
/*    */   final boolean a(Object paramObject)
/*    */   {
/* 19 */     synchronized (this.a) {
/* 20 */       return this.a.contains(paramObject);
/*    */     }
/*    */   }
/*    */ 
/*    */   final Object a() {
/* 25 */     synchronized (this.a) {
/* 26 */       while (this.a.isEmpty()) {
/* 27 */         this.a.wait();
/*    */       }
/* 29 */       return this.a.removeFirst();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     je
 * JD-Core Version:    0.6.2
 */