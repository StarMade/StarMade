/*   1:    */package org.schema.schine.network.objects.remote;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import d;
/*   5:    */import java.io.DataInputStream;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */import javax.vecmath.Matrix3f;
/*   8:    */import javax.vecmath.Quat4f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import org.schema.schine.network.objects.NetworkObject;
/*  11:    */import org.schema.schine.network.objects.NetworkTransformation;
/*  12:    */
/*  19:    */public class RemotePhysicsTransform
/*  20:    */  extends RemoteField
/*  21:    */{
/*  22:    */  static final int len = 36;
/*  23:    */  
/*  24:    */  public RemotePhysicsTransform(NetworkTransformation paramNetworkTransformation, NetworkObject paramNetworkObject)
/*  25:    */  {
/*  26: 26 */    super(paramNetworkTransformation, paramNetworkObject);
/*  27:    */  }
/*  28:    */  
/*  29: 29 */  public RemotePhysicsTransform(NetworkTransformation paramNetworkTransformation, boolean paramBoolean) { super(paramNetworkTransformation, paramBoolean); }
/*  30:    */  
/*  31:    */  public int byteLength()
/*  32:    */  {
/*  33: 33 */    return 36;
/*  34:    */  }
/*  35:    */  
/*  36:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*  37:    */  {
/*  38: 38 */    paramInt = new Quat4f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*  39:    */    
/*  40: 40 */    ((NetworkTransformation)get()).getTransformReceive().basis.set(paramInt);
/*  41:    */    
/*  53: 53 */    ((NetworkTransformation)get()).getTransformReceive().origin.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*  54:    */    
/*  55: 55 */    if (paramDataInputStream.readBoolean()) {
/*  56: 56 */      ((NetworkTransformation)get()).receivedVil = true;
/*  57: 57 */      ((NetworkTransformation)get()).getLinReceive().set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*  58: 58 */      ((NetworkTransformation)get()).getAngReceive().set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*  59:    */    }
/*  60: 60 */    if (this.onServer) {
/*  61: 61 */      ((NetworkTransformation)get()).setTimeStampReceive(paramDataInputStream.readLong());
/*  62:    */    }
/*  63: 63 */    ((NetworkTransformation)get()).received = true;
/*  64:    */  }
/*  65:    */  
/*  81:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/*  82:    */  {
/*  83: 83 */    Quat4f localQuat4f = new Quat4f();
/*  84: 84 */    d.a(((NetworkTransformation)get()).getTransform().basis, localQuat4f);
/*  85:    */    
/*  86: 86 */    paramDataOutputStream.writeFloat(localQuat4f.x);
/*  87: 87 */    paramDataOutputStream.writeFloat(localQuat4f.y);
/*  88: 88 */    paramDataOutputStream.writeFloat(localQuat4f.z);
/*  89: 89 */    paramDataOutputStream.writeFloat(localQuat4f.w);
/*  90:    */    
/* 101:101 */    paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.x);
/* 102:102 */    paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.y);
/* 103:103 */    paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.z);
/* 104:    */    
/* 105:105 */    if (((NetworkTransformation)get()).sendVil) {
/* 106:106 */      paramDataOutputStream.writeBoolean(true);
/* 107:107 */      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().x);
/* 108:108 */      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().y);
/* 109:109 */      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().z);
/* 110:    */      
/* 111:111 */      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().x);
/* 112:112 */      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().y);
/* 113:113 */      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().z);
/* 114:    */    } else {
/* 115:115 */      paramDataOutputStream.writeBoolean(false);
/* 116:    */    }
/* 117:    */    
/* 118:118 */    if (!this.onServer) {
/* 119:119 */      paramDataOutputStream.writeLong(((NetworkTransformation)get()).getTimeStamp());
/* 120:    */    }
/* 121:    */    
/* 122:122 */    return byteLength();
/* 123:    */  }
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemotePhysicsTransform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */