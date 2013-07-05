/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ public class BlockLevel
/*    */ {
/*    */   private final short id;
/*    */   private final int level;
/*    */ 
/*    */   public BlockLevel(short paramShort, int paramInt)
/*    */   {
/*  9 */     this.id = paramShort;
/* 10 */     this.level = paramInt;
/*    */   }
/*    */ 
/*    */   public short getIdBase()
/*    */   {
/* 16 */     return this.id;
/*    */   }
/*    */ 
/*    */   public int getLevel()
/*    */   {
/* 22 */     return this.level;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 28 */     return "Level: " + this.level + "; Base: " + ElementKeyMap.getInfo(this.id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BlockLevel
 * JD-Core Version:    0.6.2
 */