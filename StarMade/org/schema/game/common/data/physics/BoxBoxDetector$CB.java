/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/* 18:   */class BoxBoxDetector$CB
/* 19:   */{
/* 20:   */  private BoxBoxDetector$CB(BoxBoxDetector paramBoxBoxDetector) {}
/* 21:   */  
/* 37:37 */  float[] normalR = null;
/* 38:   */  
/* 39:   */  int code;
/* 40:   */  boolean invert_normal;
/* 41:41 */  int normalROffset = 0;
/* 42:42 */  float s = (1.0F / -1.0F);
/* 43:   */  
/* 44:   */  public void reset() {
/* 45:45 */    this.normalR = null;
/* 46:   */    
/* 47:47 */    this.code = 0;
/* 48:48 */    this.invert_normal = false;
/* 49:49 */    this.normalROffset = 0;
/* 50:50 */    this.s = (1.0F / -1.0F);
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.BoxBoxDetector.CB
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */