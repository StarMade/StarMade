package org.schema.game.network.objects.remote;

import class_48;
import class_796;
import class_886;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.server.ServerState;

public class RemoteSegmentPiece
  extends RemoteField
{
  private SegmentController segmentController;
  
  public RemoteSegmentPiece(class_796 paramclass_796, SegmentController paramSegmentController, NetworkObject paramNetworkObject)
  {
    super(paramclass_796, paramNetworkObject);
    this.segmentController = paramSegmentController;
  }
  
  public RemoteSegmentPiece(class_796 paramclass_796, SegmentController paramSegmentController, boolean paramBoolean)
  {
    super(paramclass_796, paramBoolean);
    this.segmentController = paramSegmentController;
  }
  
  public int byteLength()
  {
    return 22;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i;
    int j;
    if ((paramInt = paramDataInputStream.readByte()) == 0)
    {
      paramInt = paramDataInputStream.readByte();
      i = paramDataInputStream.readByte();
      j = paramDataInputStream.readByte();
    }
    else if (paramInt == 1)
    {
      paramInt = paramDataInputStream.readShort();
      i = paramDataInputStream.readShort();
      j = paramDataInputStream.readShort();
    }
    else
    {
      paramInt = paramDataInputStream.readInt();
      i = paramDataInputStream.readInt();
      j = paramDataInputStream.readInt();
    }
    byte[] arrayOfByte = new byte[3];
    paramDataInputStream.readFully(arrayOfByte);
    if ((paramDataInputStream = this.segmentController.getSegmentBuffer().a9(new class_48(paramInt, i, j), this.segmentController.getState() instanceof ServerState)) != null)
    {
      paramDataInputStream.a14(arrayOfByte);
      set(paramDataInputStream);
    }
  }
  
  public void writeDynamicPosition(int paramInt1, int paramInt2, int paramInt3, DataOutputStream paramDataOutputStream)
  {
    if ((paramInt1 >= -128) && (paramInt2 >= -128) && (paramInt3 >= -128) && (paramInt1 <= -128) && (paramInt2 <= -128) && (paramInt3 <= 127))
    {
      paramDataOutputStream.writeByte(0);
      paramDataOutputStream.writeByte(paramInt1);
      paramDataOutputStream.writeByte(paramInt2);
      paramDataOutputStream.writeByte(paramInt3);
      return;
    }
    if ((paramInt1 >= -32768) && (paramInt2 >= -32768) && (paramInt3 >= -32768) && (paramInt1 <= -32768) && (paramInt2 <= -32768) && (paramInt3 <= 32767))
    {
      paramDataOutputStream.writeByte(1);
      paramDataOutputStream.writeShort(paramInt1);
      paramDataOutputStream.writeShort(paramInt2);
      paramDataOutputStream.writeShort(paramInt3);
      return;
    }
    paramDataOutputStream.writeByte(2);
    paramDataOutputStream.writeInt(paramInt1);
    paramDataOutputStream.writeInt(paramInt2);
    paramDataOutputStream.writeInt(paramInt3);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    assert (get() != null);
    writeDynamicPosition(((class_796)get()).field_1056 + ((class_796)get()).a7().field_34.field_475, ((class_796)get()).field_1057 + ((class_796)get()).a7().field_34.field_476, ((class_796)get()).field_1058 + ((class_796)get()).a7().field_34.field_477, paramDataOutputStream);
    paramDataOutputStream.write(((class_796)get()).a4());
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentPiece
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */