package org.schema.game.common.controller.elements.explosive;

import class_48;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.EditableSendableSegmentController;

public class ExplosiveElementManager$Explosion
{
  public static final byte NORMAL = 0;
  public static final byte INNER = 1;
  private final class_48 field_2099;
  private final Vector3f where;
  private final EditableSendableSegmentController from;
  private final EditableSendableSegmentController field_2100;
  private byte type;
  
  public ExplosiveElementManager$Explosion(ExplosiveElementManager paramExplosiveElementManager, class_48 paramclass_48, Vector3f paramVector3f, EditableSendableSegmentController paramEditableSendableSegmentController1, EditableSendableSegmentController paramEditableSendableSegmentController2, byte paramByte)
  {
    this.field_2099 = paramclass_48;
    this.where = paramVector3f;
    this.from = paramEditableSendableSegmentController1;
    this.field_2100 = paramEditableSendableSegmentController2;
    this.type = paramByte;
  }
  
  public boolean equals(Object paramObject)
  {
    return (((Explosion)paramObject).field_2099.equals(this.field_2099)) && (((Explosion)paramObject).field_2100.equals(this.field_2100));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveElementManager.Explosion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */