package org.schema.game.common.data.element;

import class_48;
import class_748;
import class_796;
import class_941;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.data.world.Segment;

public class BeamHandler$BeamState
{
  public final class_48 elementPos;
  public final Vector3f from;
  public final Vector3f field_1883;
  public Vector3f hitPoint;
  public float timeRunning = 0.0F;
  public class_748 playerState;
  public long lastCheck = -1L;
  public float timeSpent;
  public class_796 segmentHit;
  public float hitOneSegment;
  public Segment cachedLastSegment;
  private float power;
  public Vector4f color = new Vector4f();
  public float camDistStart;
  public float camDistEnd;
  
  public BeamHandler$BeamState(BeamHandler paramBeamHandler, class_48 paramclass_48, Vector3f paramVector3f1, Vector3f paramVector3f2, class_748 paramclass_748, float paramFloat)
  {
    this.elementPos = paramclass_48;
    this.from = paramVector3f1;
    this.field_1883 = paramVector3f2;
    this.playerState = paramclass_748;
    setPower(paramFloat);
  }
  
  public boolean checkNecessary(class_941 paramclass_941, BeamState paramBeamState)
  {
    if ((this.lastCheck < 0L) || ((float)(System.currentTimeMillis() - this.lastCheck) > Math.min(300.0F, this.this$0.getBeamToHitInSecs(paramBeamState) / 3.0F * 1000.0F)))
    {
      this.lastCheck = System.currentTimeMillis();
      return true;
    }
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((BeamState)paramObject).elementPos.equals(this.elementPos);
  }
  
  public float getSalvageSpeed()
  {
    return this.power;
  }
  
  public int hashCode()
  {
    return this.elementPos.hashCode();
  }
  
  public void setPower(float paramFloat)
  {
    this.power = paramFloat;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.BeamHandler.BeamState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */