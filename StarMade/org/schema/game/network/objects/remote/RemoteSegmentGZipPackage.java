package org.schema.game.network.objects.remote;

import class_672;
import class_886;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteSegmentGZipPackage
  extends RemoteField
{
  byte[] bufferBytes = new byte[5];
  byte[] compressedBuffer = new byte[16384];
  
  public RemoteSegmentGZipPackage(class_672 paramclass_672, NetworkObject paramNetworkObject)
  {
    super(paramclass_672, paramNetworkObject);
  }
  
  public int byteLength()
  {
    throw new IllegalArgumentException("Size unknown for zipped Stream");
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int arg2)
  {
    ??? = ByteUtil.a10(paramDataInputStream);
    assert (get() != null);
    assert (((class_672)get()).a15() != null);
    assert (((class_672)get()).a16() != null);
    if (??? <= 0)
    {
      System.err.println("Sent empty Segment " + ((class_672)get()).field_34);
      return;
    }
    assert (??? <= this.compressedBuffer.length) : ???;
    assert (((class_672)get()).a16() != null);
    for (int i = 0; i < ???; i++) {
      this.compressedBuffer[i] = ((byte)paramDataInputStream.read());
    }
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.compressedBuffer, 0, ???);
    ((class_672)get()).a16().reset();
    try
    {
      long l = ByteUtil.a11(paramDataInputStream = new GZIPInputStream(localByteArrayInputStream));
      synchronized (((class_672)get()).a16())
      {
        while (paramDataInputStream.read(this.bufferBytes) > 0)
        {
          byte b1 = this.bufferBytes[0];
          byte b2 = this.bufferBytes[1];
          byte b3 = this.bufferBytes[2];
          short s = ByteUtil.a12(this.bufferBytes, 3);
          ((class_672)get()).a16().setInfoElementUnsynched(b1, b2, b3, s, false);
        }
      }
      ((class_672)get()).a15().getSegmentBuffer().b6((Segment)get());
      ((class_672)get()).a46(l);
      paramDataInputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    assert (get() != null);
    Object localObject1 = new PipedOutputStream();
    Object localObject3 = new PipedInputStream((PipedOutputStream)localObject1);
    localObject3 = new BufferedInputStream((InputStream)localObject3);
    synchronized ((class_672)get())
    {
      try
      {
        localObject1 = new GZIPOutputStream((OutputStream)localObject1);
        SegmentData localSegmentData = ((class_672)get()).a16();
        assert (localSegmentData != null);
        ((GZIPOutputStream)localObject1).write(ByteUtil.a7(((class_672)get()).a44()));
        for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
          for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
            for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
            {
              int j = SegmentData.getInfoIndex(b3, b2, b1);
              if (localSegmentData.contains(j))
              {
                this.bufferBytes[0] = b3;
                this.bufferBytes[1] = b2;
                this.bufferBytes[2] = b1;
                ByteUtil.a13(localSegmentData.getType(j), this.bufferBytes, 3);
                ((GZIPOutputStream)localObject1).write(this.bufferBytes);
              }
            }
          }
        }
        ((GZIPOutputStream)localObject1).finish();
        ((GZIPOutputStream)localObject1).close();
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
    }
    ??? = 0;
    Object localObject2 = 0;
    ByteUtil.a14(((BufferedInputStream)localObject3).available(), paramDataOutputStream);
    int i;
    while ((??? = ((BufferedInputStream)localObject3).read(this.bufferBytes)) > 0)
    {
      paramDataOutputStream.write(this.bufferBytes, 0, ???);
      localObject2 += ???;
    }
    return i + 4;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentGZipPackage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */