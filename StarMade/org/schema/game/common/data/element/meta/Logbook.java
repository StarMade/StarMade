/*    */ package org.schema.game.common.data.element.meta;
/*    */ 
/*    */ import Ad;
/*    */ import Af;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public class Logbook extends MetaObject
/*    */ {
/*    */   private String txt;
/*    */ 
/*    */   public void serialize(DataOutputStream paramDataOutputStream)
/*    */   {
/* 16 */     paramDataOutputStream.writeUTF(this.txt);
/*    */   }
/*    */ 
/*    */   public void deserialize(DataInputStream paramDataInputStream)
/*    */   {
/* 21 */     this.txt = paramDataInputStream.readUTF();
/*    */   }
/*    */ 
/*    */   public short getObjectBlockID()
/*    */   {
/* 27 */     return -11;
/*    */   }
/*    */ 
/*    */   public Ad getBytesTag()
/*    */   {
/* 32 */     return new Ad(Af.i, null, this.txt);
/*    */   }
/*    */ 
/*    */   public void fromTag(Ad paramAd)
/*    */   {
/* 37 */     this.txt = ((String)paramAd.a());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.Logbook
 * JD-Core Version:    0.6.2
 */