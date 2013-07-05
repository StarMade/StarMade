/*    */ package org.schema.game.common.data.element.meta;
/*    */ 
/*    */ import Ad;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public abstract class MetaObject
/*    */ {
/*    */   private int id;
/*    */ 
/*    */   public abstract void serialize(DataOutputStream paramDataOutputStream);
/*    */ 
/*    */   public abstract void deserialize(DataInputStream paramDataInputStream);
/*    */ 
/*    */   public abstract short getObjectBlockID();
/*    */ 
/*    */   public int getId()
/*    */   {
/* 21 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int paramInt)
/*    */   {
/* 27 */     this.id = paramInt;
/*    */   }
/*    */ 
/*    */   public abstract Ad getBytesTag();
/*    */ 
/*    */   public abstract void fromTag(Ad paramAd);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.MetaObject
 * JD-Core Version:    0.6.2
 */