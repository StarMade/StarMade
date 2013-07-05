/*    */ package org.schema.game.common.data.element.meta;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public class MetaObjectManager
/*    */ {
/*    */   public static final short RECIPE = -10;
/*    */   public static final short LOG_BOOK = -11;
/* 14 */   private Int2ObjectOpenHashMap map = new Int2ObjectOpenHashMap();
/*    */ 
/*    */   public void deserialize(DataInputStream paramDataInputStream)
/*    */   {
/* 18 */     int i = paramDataInputStream.readInt();
/* 19 */     short s = paramDataInputStream.readShort();
/*    */     MetaObject localMetaObject;
/* 22 */     if ((
/* 22 */       localMetaObject = (MetaObject)this.map.get(i)) == null)
/*    */     {
/* 23 */       (
/* 24 */         localMetaObject = instantiate(s))
/* 24 */         .setId(i);
/* 25 */       this.map.put(i, localMetaObject);
/*    */     }
/* 27 */     localMetaObject.deserialize(paramDataInputStream);
/*    */   }
/*    */ 
/*    */   public static MetaObject instantiate(short paramShort)
/*    */   {
/* 32 */     switch (paramShort) { case -11:
/* 33 */       return new Logbook();
/*    */     case -10:
/* 34 */       return new Recipe();
/*    */     }
/* 36 */     throw new IllegalArgumentException("UNKNOWN OID: " + paramShort);
/*    */   }
/*    */ 
/*    */   public void serialize(DataOutputStream paramDataOutputStream, MetaObject paramMetaObject) {
/* 40 */     paramDataOutputStream.writeInt(paramMetaObject.getId());
/* 41 */     paramDataOutputStream.writeShort(paramMetaObject.getObjectBlockID());
/* 42 */     paramMetaObject.serialize(paramDataOutputStream);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.MetaObjectManager
 * JD-Core Version:    0.6.2
 */