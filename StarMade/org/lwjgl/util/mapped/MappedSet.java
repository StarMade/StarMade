/*    */ package org.lwjgl.util.mapped;
/*    */ 
/*    */ public class MappedSet
/*    */ {
/*    */   public static MappedSet2 create(MappedObject a, MappedObject b)
/*    */   {
/* 48 */     return new MappedSet2(a, b);
/*    */   }
/*    */ 
/*    */   public static MappedSet3 create(MappedObject a, MappedObject b, MappedObject c)
/*    */   {
/* 57 */     return new MappedSet3(a, b, c);
/*    */   }
/*    */ 
/*    */   public static MappedSet4 create(MappedObject a, MappedObject b, MappedObject c, MappedObject d)
/*    */   {
/* 66 */     return new MappedSet4(a, b, c, d);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet
 * JD-Core Version:    0.6.2
 */