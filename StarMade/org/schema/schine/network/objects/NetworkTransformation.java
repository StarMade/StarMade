package org.schema.schine.network.objects;

import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class NetworkTransformation
{
  private Transform transform;
  private Transform transformReceive;
  private Vector3f lin = new Vector3f();
  private Vector3f ang = new Vector3f();
  private Vector3f linReceive = new Vector3f();
  private Vector3f angReceive = new Vector3f();
  private long timeStamp;
  private long timeStampReceive;
  public boolean received;
  public boolean sendVil;
  public boolean receivedVil;
  
  public NetworkTransformation()
  {
    this.transform = new Transform();
    this.transformReceive = new Transform();
  }
  
  public NetworkTransformation(Transform paramTransform, long paramLong)
  {
    this.transform = paramTransform;
    this.transformReceive = new Transform(paramTransform);
    this.timeStamp = paramLong;
    this.timeStampReceive = paramLong;
  }
  
  public long getTimeStamp()
  {
    return this.timeStamp;
  }
  
  public Transform getTransform()
  {
    return this.transform;
  }
  
  public void setTimeStamp(long paramLong)
  {
    this.timeStamp = paramLong;
  }
  
  public void setTransform(Transform paramTransform)
  {
    this.transform = paramTransform;
  }
  
  public Transform getTransformReceive()
  {
    return this.transformReceive;
  }
  
  public void setTransformReceive(Transform paramTransform)
  {
    this.transformReceive = paramTransform;
  }
  
  public long getTimeStampReceive()
  {
    return this.timeStampReceive;
  }
  
  public void setTimeStampReceive(long paramLong)
  {
    this.timeStampReceive = paramLong;
  }
  
  public Vector3f getLin()
  {
    return this.lin;
  }
  
  public void setLin(Vector3f paramVector3f)
  {
    this.lin = paramVector3f;
  }
  
  public Vector3f getAng()
  {
    return this.ang;
  }
  
  public void setAng(Vector3f paramVector3f)
  {
    this.ang = paramVector3f;
  }
  
  public Vector3f getLinReceive()
  {
    return this.linReceive;
  }
  
  public void setLinReceive(Vector3f paramVector3f)
  {
    this.linReceive = paramVector3f;
  }
  
  public Vector3f getAngReceive()
  {
    return this.angReceive;
  }
  
  public void setAngReceive(Vector3f paramVector3f)
  {
    this.angReceive = paramVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.NetworkTransformation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */