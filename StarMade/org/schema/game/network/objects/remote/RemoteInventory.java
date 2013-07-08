package org.schema.game.network.objects.remote;

import class_48;
import class_635;
import class_639;
import class_641;
import class_655;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteInventory
  extends RemoteField
{
  private boolean add;
  private class_635 holder;
  
  public RemoteInventory(class_639 paramclass_639, class_635 paramclass_635, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramclass_639, paramBoolean2);
    setAdd(paramBoolean1);
    this.holder = paramclass_635;
  }
  
  public int byteLength()
  {
    return 22;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readInt();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    int k = paramDataInputStream.readInt();
    setAdd(paramDataInputStream.readBoolean());
    if (isAdd())
    {
      switch (paramInt)
      {
      case 1: 
        paramInt = new class_641(this.holder, new class_48(i, j, k));
        break;
      default: 
        paramInt = new class_655(this.holder, new class_48(i, j, k));
      }
      paramInt.a1(paramDataInputStream);
    }
    else
    {
      switch (paramInt)
      {
      case 1: 
        paramInt = new class_641(this.holder, new class_48(i, j, k));
        break;
      default: 
        paramInt = new class_655(this.holder, new class_48(i, j, k));
      }
    }
    set(paramInt);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = 17;
    paramDataOutputStream.writeInt(((class_639)get()).c2());
    paramDataOutputStream.writeInt(((class_639)get()).a44().field_475);
    paramDataOutputStream.writeInt(((class_639)get()).a44().field_476);
    paramDataOutputStream.writeInt(((class_639)get()).a44().field_477);
    paramDataOutputStream.writeBoolean(isAdd());
    if (isAdd()) {
      i = 17 + ((class_639)get()).a54(paramDataOutputStream);
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */