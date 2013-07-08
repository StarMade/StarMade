package org.schema.schine.network.objects.remote;

import class_29;
import com.bulletphysics.linearmath.Transform;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.NetworkTransformation;

public class RemotePhysicsTransform
  extends RemoteField
{
  static final int len = 36;
  
  public RemotePhysicsTransform(NetworkTransformation paramNetworkTransformation, NetworkObject paramNetworkObject)
  {
    super(paramNetworkTransformation, paramNetworkObject);
  }
  
  public RemotePhysicsTransform(NetworkTransformation paramNetworkTransformation, boolean paramBoolean)
  {
    super(paramNetworkTransformation, paramBoolean);
  }
  
  public int byteLength()
  {
    return 36;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = new Quat4f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    ((NetworkTransformation)get()).getTransformReceive().basis.set(paramInt);
    ((NetworkTransformation)get()).getTransformReceive().origin.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    if (paramDataInputStream.readBoolean())
    {
      ((NetworkTransformation)get()).receivedVil = true;
      ((NetworkTransformation)get()).getLinReceive().set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
      ((NetworkTransformation)get()).getAngReceive().set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    }
    if (this.onServer) {
      ((NetworkTransformation)get()).setTimeStampReceive(paramDataInputStream.readLong());
    }
    ((NetworkTransformation)get()).received = true;
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    Quat4f localQuat4f = new Quat4f();
    class_29.a5(((NetworkTransformation)get()).getTransform().basis, localQuat4f);
    paramDataOutputStream.writeFloat(localQuat4f.field_596);
    paramDataOutputStream.writeFloat(localQuat4f.field_597);
    paramDataOutputStream.writeFloat(localQuat4f.field_598);
    paramDataOutputStream.writeFloat(localQuat4f.field_599);
    paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.field_615);
    paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.field_616);
    paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.field_617);
    if (((NetworkTransformation)get()).sendVil)
    {
      paramDataOutputStream.writeBoolean(true);
      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().field_615);
      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().field_616);
      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().field_617);
      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().field_615);
      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().field_616);
      paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().field_617);
    }
    else
    {
      paramDataOutputStream.writeBoolean(false);
    }
    if (!this.onServer) {
      paramDataOutputStream.writeLong(((NetworkTransformation)get()).getTimeStamp());
    }
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemotePhysicsTransform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */