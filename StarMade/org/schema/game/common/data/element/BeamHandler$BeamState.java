/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import javax.vecmath.Vector4f;
/*  5:   */import lE;
/*  6:   */import le;
/*  7:   */import org.schema.game.common.data.world.Segment;
/*  8:   */import q;
/*  9:   */import xq;
/* 10:   */
/* 35:   */public class BeamHandler$BeamState
/* 36:   */{
/* 37:   */  public final q elementPos;
/* 38:   */  public final Vector3f from;
/* 39:   */  public final Vector3f to;
/* 40:   */  public Vector3f hitPoint;
/* 41:41 */  public float timeRunning = 0.0F;
/* 42:   */  public lE playerState;
/* 43:43 */  public long lastCheck = -1L;
/* 44:   */  public float timeSpent;
/* 45:   */  public le segmentHit;
/* 46:   */  public float hitOneSegment;
/* 47:   */  public Segment cachedLastSegment;
/* 48:   */  private float power;
/* 49:49 */  public Vector4f color = new Vector4f();
/* 50:   */  
/* 51:   */  public float camDistStart;
/* 52:   */  public float camDistEnd;
/* 53:   */  
/* 54:   */  public BeamHandler$BeamState(BeamHandler paramBeamHandler, q paramq, Vector3f paramVector3f1, Vector3f paramVector3f2, lE paramlE, float paramFloat)
/* 55:   */  {
/* 56:56 */    this.elementPos = paramq;
/* 57:57 */    this.from = paramVector3f1;
/* 58:58 */    this.to = paramVector3f2;
/* 59:59 */    this.playerState = paramlE;
/* 60:60 */    setPower(paramFloat);
/* 61:   */  }
/* 62:   */  
/* 64:   */  public boolean checkNecessary(xq paramxq, BeamState paramBeamState)
/* 65:   */  {
/* 66:66 */    if ((this.lastCheck < 0L) || ((float)(System.currentTimeMillis() - this.lastCheck) > Math.min(300.0F, this.this$0.getBeamToHitInSecs(paramBeamState) / 3.0F * 1000.0F))) {
/* 67:67 */      this.lastCheck = System.currentTimeMillis();
/* 68:   */      
/* 69:69 */      return true;
/* 70:   */    }
/* 71:71 */    return false;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public boolean equals(Object paramObject) {
/* 75:75 */    return ((BeamState)paramObject).elementPos.equals(this.elementPos);
/* 76:   */  }
/* 77:   */  
/* 80:   */  public float getSalvageSpeed()
/* 81:   */  {
/* 82:82 */    return this.power;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public int hashCode()
/* 86:   */  {
/* 87:87 */    return this.elementPos.hashCode();
/* 88:   */  }
/* 89:   */  
/* 92:   */  public void setPower(float paramFloat)
/* 93:   */  {
/* 94:94 */    this.power = paramFloat;
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BeamHandler.BeamState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */