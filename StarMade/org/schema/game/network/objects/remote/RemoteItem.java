package org.schema.game.network.objects.remote;

import class_637;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.vecmath.Vector3f;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteItem
  extends RemoteField
{
  private boolean add;
  
  public RemoteItem(class_637 paramclass_637, Boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    super(paramclass_637, paramNetworkObject);
    setAdd(paramBoolean.booleanValue());
  }
  
  public RemoteItem(class_637 paramclass_637, Boolean paramBoolean, boolean paramBoolean1)
  {
    super(paramclass_637, paramBoolean1);
    setAdd(paramBoolean.booleanValue());
  }
  
  public int byteLength()
  {
    return 22;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readInt();
    setAdd(paramDataInputStream.readBoolean());
    if (isAdd())
    {
      short s = paramDataInputStream.readShort();
      int i = paramDataInputStream.readInt();
      float f1 = paramDataInputStream.readFloat();
      float f2 = paramDataInputStream.readFloat();
      paramDataInputStream = paramDataInputStream.readFloat();
      ((class_637)get()).a32(paramInt, s, i, new Vector3f(f1, f2, paramDataInputStream));
      return;
    }
    ((class_637)get()).a32(paramInt, (short)-1, 0, null);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((class_637)get()).b5());
    paramDataOutputStream.writeBoolean(isAdd());
    int i = 5;
    if (isAdd())
    {
      paramDataOutputStream.writeShort(((class_637)get()).a34());
      paramDataOutputStream.writeInt(((class_637)get()).a3());
      paramDataOutputStream.writeFloat(((class_637)get()).a8().field_615);
      paramDataOutputStream.writeFloat(((class_637)get()).a8().field_616);
      paramDataOutputStream.writeFloat(((class_637)get()).a8().field_617);
      i += 18;
    }
    return i;
  }
  
  public boolean isAdd()
  {
    return this.add;
  }
  
  public void setAdd(boolean paramBoolean)
  {
    this.add = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteItem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */