package org.schema.game.common.data.physics;

class BoxBoxDetector$CB
{
  float[] normalR = null;
  int code;
  boolean invert_normal;
  int normalROffset = 0;
  float field_2075 = (1.0F / -1.0F);
  
  private BoxBoxDetector$CB(BoxBoxDetector paramBoxBoxDetector) {}
  
  public void reset()
  {
    this.normalR = null;
    this.code = 0;
    this.invert_normal = false;
    this.normalROffset = 0;
    this.field_2075 = (1.0F / -1.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.BoxBoxDetector.CB
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */