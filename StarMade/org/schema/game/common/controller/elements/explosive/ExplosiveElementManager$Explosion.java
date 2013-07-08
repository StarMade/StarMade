/*  1:   */package org.schema.game.common.controller.elements.explosive;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  5:   */import q;
/*  6:   */
/* 25:   */public class ExplosiveElementManager$Explosion
/* 26:   */{
/* 27:   */  public static final byte NORMAL = 0;
/* 28:   */  public static final byte INNER = 1;
/* 29:   */  private final q id;
/* 30:   */  private final Vector3f where;
/* 31:   */  private final EditableSendableSegmentController from;
/* 32:   */  private final EditableSendableSegmentController to;
/* 33:   */  private byte type;
/* 34:   */  
/* 35:   */  public ExplosiveElementManager$Explosion(ExplosiveElementManager paramExplosiveElementManager, q paramq, Vector3f paramVector3f, EditableSendableSegmentController paramEditableSendableSegmentController1, EditableSendableSegmentController paramEditableSendableSegmentController2, byte paramByte)
/* 36:   */  {
/* 37:37 */    this.id = paramq;
/* 38:38 */    this.where = paramVector3f;
/* 39:39 */    this.from = paramEditableSendableSegmentController1;
/* 40:40 */    this.to = paramEditableSendableSegmentController2;
/* 41:41 */    this.type = paramByte;
/* 42:   */  }
/* 43:   */  
/* 46:   */  public boolean equals(Object paramObject)
/* 47:   */  {
/* 48:48 */    return (((Explosion)paramObject).id.equals(this.id)) && (((Explosion)paramObject).to.equals(this.to));
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveElementManager.Explosion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */