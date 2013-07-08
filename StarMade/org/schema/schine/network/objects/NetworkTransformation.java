/*   1:    */package org.schema.schine.network.objects;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */
/*   7:    */public class NetworkTransformation
/*   8:    */{
/*   9:    */  private Transform transform;
/*  10:    */  private Transform transformReceive;
/*  11: 11 */  private Vector3f lin = new Vector3f();
/*  12: 12 */  private Vector3f ang = new Vector3f();
/*  13: 13 */  private Vector3f linReceive = new Vector3f();
/*  14: 14 */  private Vector3f angReceive = new Vector3f();
/*  15:    */  private long timeStamp;
/*  16:    */  private long timeStampReceive;
/*  17:    */  public boolean received;
/*  18:    */  public boolean sendVil;
/*  19:    */  public boolean receivedVil;
/*  20:    */  
/*  21:    */  public NetworkTransformation() {
/*  22: 22 */    this.transform = new Transform();
/*  23: 23 */    this.transformReceive = new Transform();
/*  24:    */  }
/*  25:    */  
/*  27:    */  public NetworkTransformation(Transform paramTransform, long paramLong)
/*  28:    */  {
/*  29: 29 */    this.transform = paramTransform;
/*  30: 30 */    this.transformReceive = new Transform(paramTransform);
/*  31: 31 */    this.timeStamp = paramLong;
/*  32: 32 */    this.timeStampReceive = paramLong;
/*  33:    */  }
/*  34:    */  
/*  37:    */  public long getTimeStamp()
/*  38:    */  {
/*  39: 39 */    return this.timeStamp;
/*  40:    */  }
/*  41:    */  
/*  43:    */  public Transform getTransform()
/*  44:    */  {
/*  45: 45 */    return this.transform;
/*  46:    */  }
/*  47:    */  
/*  49:    */  public void setTimeStamp(long paramLong)
/*  50:    */  {
/*  51: 51 */    this.timeStamp = paramLong;
/*  52:    */  }
/*  53:    */  
/*  55:    */  public void setTransform(Transform paramTransform)
/*  56:    */  {
/*  57: 57 */    this.transform = paramTransform;
/*  58:    */  }
/*  59:    */  
/*  62:    */  public Transform getTransformReceive()
/*  63:    */  {
/*  64: 64 */    return this.transformReceive;
/*  65:    */  }
/*  66:    */  
/*  69:    */  public void setTransformReceive(Transform paramTransform)
/*  70:    */  {
/*  71: 71 */    this.transformReceive = paramTransform;
/*  72:    */  }
/*  73:    */  
/*  76:    */  public long getTimeStampReceive()
/*  77:    */  {
/*  78: 78 */    return this.timeStampReceive;
/*  79:    */  }
/*  80:    */  
/*  83:    */  public void setTimeStampReceive(long paramLong)
/*  84:    */  {
/*  85: 85 */    this.timeStampReceive = paramLong;
/*  86:    */  }
/*  87:    */  
/*  90:    */  public Vector3f getLin()
/*  91:    */  {
/*  92: 92 */    return this.lin;
/*  93:    */  }
/*  94:    */  
/*  97:    */  public void setLin(Vector3f paramVector3f)
/*  98:    */  {
/*  99: 99 */    this.lin = paramVector3f;
/* 100:    */  }
/* 101:    */  
/* 104:    */  public Vector3f getAng()
/* 105:    */  {
/* 106:106 */    return this.ang;
/* 107:    */  }
/* 108:    */  
/* 111:    */  public void setAng(Vector3f paramVector3f)
/* 112:    */  {
/* 113:113 */    this.ang = paramVector3f;
/* 114:    */  }
/* 115:    */  
/* 118:    */  public Vector3f getLinReceive()
/* 119:    */  {
/* 120:120 */    return this.linReceive;
/* 121:    */  }
/* 122:    */  
/* 125:    */  public void setLinReceive(Vector3f paramVector3f)
/* 126:    */  {
/* 127:127 */    this.linReceive = paramVector3f;
/* 128:    */  }
/* 129:    */  
/* 132:    */  public Vector3f getAngReceive()
/* 133:    */  {
/* 134:134 */    return this.angReceive;
/* 135:    */  }
/* 136:    */  
/* 139:    */  public void setAngReceive(Vector3f paramVector3f)
/* 140:    */  {
/* 141:141 */    this.angReceive = paramVector3f;
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkTransformation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */