/*  1:   */package org.schema.game.common.data.physics.octree;
/*  2:   */
/*  3:   */class TreeCache
/*  4:   */{
/*  5:   */  byte[] lvlToIndex;
/*  6:   */  boolean initialized;
/*  7:   */  
/*  8:   */  public TreeCache()
/*  9:   */  {
/* 10:10 */    this.initialized = false;
/* 11:11 */    this.lvlToIndex = new byte[3];
/* 12:   */  }
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.TreeCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */