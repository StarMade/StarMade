/*  1:   */package org.schema.game.network;
/*  2:   */
/*  3:   */import java.io.File;
/*  4:   */import java.io.FilenameFilter;
/*  5:   */
/* 49:   */final class StarMadePlayerStats$1
/* 50:   */  implements FilenameFilter
/* 51:   */{
/* 52:   */  public final boolean accept(File paramFile, String paramString)
/* 53:   */  {
/* 54:54 */    return paramString.startsWith("ENTITY_PLAYERSTATE");
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.StarMadePlayerStats.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */