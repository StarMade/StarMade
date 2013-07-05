/*    */ package org.lwjgl.util.mapped;
/*    */ 
/*    */ public class MappedSet4
/*    */ {
/*    */   private final MappedObject a;
/*    */   private final MappedObject b;
/*    */   private final MappedObject c;
/*    */   private final MappedObject d;
/*    */   public int view;
/*    */ 
/*    */   MappedSet4(MappedObject a, MappedObject b, MappedObject c, MappedObject d)
/*    */   {
/* 40 */     this.a = a;
/* 41 */     this.b = b;
/* 42 */     this.c = c;
/* 43 */     this.d = d;
/*    */   }
/*    */ 
/*    */   void view(int view)
/*    */   {
/* 49 */     this.a.setViewAddress(this.a.getViewAddress(view));
/* 50 */     this.b.setViewAddress(this.b.getViewAddress(view));
/* 51 */     this.c.setViewAddress(this.c.getViewAddress(view));
/* 52 */     this.d.setViewAddress(this.d.getViewAddress(view));
/*    */   }
/*    */ 
/*    */   public void next() {
/* 56 */     this.a.next();
/* 57 */     this.b.next();
/* 58 */     this.c.next();
/* 59 */     this.d.next();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet4
 * JD-Core Version:    0.6.2
 */