/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ class BoxBoxDetector$CB
/*    */ {
/* 37 */   float[] normalR = null;
/*    */   int code;
/*    */   boolean invert_normal;
/* 41 */   int normalROffset = 0;
/* 42 */   float s = (1.0F / -1.0F);
/*    */ 
/*    */   private BoxBoxDetector$CB(BoxBoxDetector paramBoxBoxDetector) {  } 
/* 45 */   public void reset() { this.normalR = null;
/*    */ 
/* 47 */     this.code = 0;
/* 48 */     this.invert_normal = false;
/* 49 */     this.normalROffset = 0;
/* 50 */     this.s = (1.0F / -1.0F);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.BoxBoxDetector.CB
 * JD-Core Version:    0.6.2
 */