/*    */ package org.schema.game.common.controller.elements.explosive;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*    */ import q;
/*    */ 
/*    */ public class ExplosiveElementManager$Explosion
/*    */ {
/*    */   public static final byte NORMAL = 0;
/*    */   public static final byte INNER = 1;
/*    */   private final q id;
/*    */   private final Vector3f where;
/*    */   private final EditableSendableSegmentController from;
/*    */   private final EditableSendableSegmentController to;
/*    */   private byte type;
/*    */ 
/*    */   public ExplosiveElementManager$Explosion(ExplosiveElementManager paramExplosiveElementManager, q paramq, Vector3f paramVector3f, EditableSendableSegmentController paramEditableSendableSegmentController1, EditableSendableSegmentController paramEditableSendableSegmentController2, byte paramByte)
/*    */   {
/* 37 */     this.id = paramq;
/* 38 */     this.where = paramVector3f;
/* 39 */     this.from = paramEditableSendableSegmentController1;
/* 40 */     this.to = paramEditableSendableSegmentController2;
/* 41 */     this.type = paramByte;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 48 */     return (((Explosion)paramObject).id.equals(this.id)) && (((Explosion)paramObject).to.equals(this.to));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveElementManager.Explosion
 * JD-Core Version:    0.6.2
 */