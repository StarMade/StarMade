/*    */ package com.eaio.uuid;
/*    */ 
/*    */ import org.omg.CORBA.TypeCode;
/*    */ import org.omg.CORBA.portable.InputStream;
/*    */ import org.omg.CORBA.portable.OutputStream;
/*    */ import org.omg.CORBA.portable.Streamable;
/*    */ 
/*    */ public final class UUIDHolder
/*    */   implements Streamable
/*    */ {
/* 16 */   public UUID value = null;
/*    */ 
/*    */   public UUIDHolder()
/*    */   {
/*    */   }
/*    */ 
/*    */   public UUIDHolder(UUID initialValue)
/*    */   {
/* 24 */     this.value = initialValue;
/*    */   }
/*    */ 
/*    */   public void _read(InputStream i)
/*    */   {
/* 29 */     this.value = UUIDHelper.read(i);
/*    */   }
/*    */ 
/*    */   public void _write(OutputStream o)
/*    */   {
/* 34 */     UUIDHelper.write(o, this.value);
/*    */   }
/*    */ 
/*    */   public TypeCode _type()
/*    */   {
/* 39 */     return UUIDHelper.type();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUIDHolder
 * JD-Core Version:    0.6.2
 */