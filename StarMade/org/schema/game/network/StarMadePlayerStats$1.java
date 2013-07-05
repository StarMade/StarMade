/*    */ package org.schema.game.network;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FilenameFilter;
/*    */ 
/*    */ final class StarMadePlayerStats$1
/*    */   implements FilenameFilter
/*    */ {
/*    */   public final boolean accept(File paramFile, String paramString)
/*    */   {
/* 54 */     return paramString.startsWith("ENTITY_PLAYERSTATE");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.StarMadePlayerStats.1
 * JD-Core Version:    0.6.2
 */