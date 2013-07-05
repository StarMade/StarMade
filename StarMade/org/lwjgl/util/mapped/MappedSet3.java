/*    */ package org.lwjgl.util.mapped;
/*    */ 
/*    */ public class MappedSet3
/*    */ {
/*    */   private final MappedObject a;
/*    */   private final MappedObject b;
/*    */   private final MappedObject c;
/*    */   public int view;
/*    */ 
/*    */   MappedSet3(MappedObject a, MappedObject b, MappedObject c)
/*    */   {
/* 40 */     this.a = a;
/* 41 */     this.b = b;
/* 42 */     this.c = c;
/*    */   }
/*    */ 
/*    */   void view(int view)
/*    */   {
/* 48 */     this.a.setViewAddress(this.a.getViewAddress(view));
/* 49 */     this.b.setViewAddress(this.b.getViewAddress(view));
/* 50 */     this.c.setViewAddress(this.c.getViewAddress(view));
/*    */   }
/*    */ 
/*    */   public void next() {
/* 54 */     this.a.next();
/* 55 */     this.b.next();
/* 56 */     this.c.next();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet3
 * JD-Core Version:    0.6.2
 */