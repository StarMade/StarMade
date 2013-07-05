/*    */ package org.apache.commons.lang3.builder;
/*    */ 
/*    */ final class IDKey
/*    */ {
/*    */   private final Object value;
/*    */   private final int id;
/*    */ 
/*    */   public IDKey(Object _value)
/*    */   {
/* 41 */     this.id = System.identityHashCode(_value);
/*    */ 
/* 45 */     this.value = _value;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 54 */     return this.id;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object other)
/*    */   {
/* 64 */     if (!(other instanceof IDKey)) {
/* 65 */       return false;
/*    */     }
/* 67 */     IDKey idKey = (IDKey)other;
/* 68 */     if (this.id != idKey.id) {
/* 69 */       return false;
/*    */     }
/*    */ 
/* 72 */     return this.value == idKey.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.IDKey
 * JD-Core Version:    0.6.2
 */