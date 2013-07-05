/*    */ package org.schema.game.common.data.world;
/*    */ 
/*    */ public class SectorNotFoundException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 7463903087999058722L;
/*    */ 
/*    */   public SectorNotFoundException(int paramInt)
/*    */   {
/* 11 */     super("SECTOR: " + String.valueOf(paramInt));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.SectorNotFoundException
 * JD-Core Version:    0.6.2
 */