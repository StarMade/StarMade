package com.jcraft.jorbis;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

class DecodeExample
{
  static int convsize = 8192;
  static byte[] convbuffer = new byte[convsize];
  
  public static void main(String[] paramArrayOfString)
  {
    Object localObject1 = System.in;
    if (paramArrayOfString.length > 0) {
      try
      {
        localObject1 = new FileInputStream(paramArrayOfString[0]);
      }
      catch (Exception localException1)
      {
        System.err.println(localException1);
      }
    }
    SyncState localSyncState = new SyncState();
    StreamState localStreamState = new StreamState();
    Page localPage = new Page();
    Packet localPacket = new Packet();
    Info localInfo = new Info();
    Comment localComment = new Comment();
    DspState localDspState = new DspState();
    Block localBlock = new Block(localDspState);
    int i = 0;
    localSyncState.init();
    for (;;)
    {
      int j = 0;
      int k = localSyncState.buffer(4096);
      byte[] arrayOfByte = localSyncState.data;
      try
      {
        i = ((InputStream)localObject1).read(arrayOfByte, k, 4096);
      }
      catch (Exception localException2)
      {
        System.err.println(localException2);
        System.exit(-1);
      }
      localSyncState.wrote(i);
      if (localSyncState.pageout(localPage) != 1)
      {
        if (i < 4096) {
          break;
        }
        System.err.println("Input does not appear to be an Ogg bitstream.");
        System.exit(1);
      }
      localStreamState.init(localPage.serialno());
      localInfo.init();
      localComment.init();
      if (localStreamState.pagein(localPage) < 0)
      {
        System.err.println("Error reading first page of Ogg bitstream data.");
        System.exit(1);
      }
      if (localStreamState.packetout(localPacket) != 1)
      {
        System.err.println("Error reading initial header packet.");
        System.exit(1);
      }
      if (localInfo.synthesis_headerin(localComment, localPacket) < 0)
      {
        System.err.println("This Ogg bitstream does not contain Vorbis audio data.");
        System.exit(1);
      }
      int m = 0;
      while (m < 2)
      {
        while (m < 2)
        {
          int n = localSyncState.pageout(localPage);
          if (n == 0) {
            break;
          }
          if (n == 1)
          {
            localStreamState.pagein(localPage);
            while (m < 2)
            {
              n = localStreamState.packetout(localPacket);
              if (n == 0) {
                break;
              }
              if (n == -1)
              {
                System.err.println("Corrupt secondary header.  Exiting.");
                System.exit(1);
              }
              localInfo.synthesis_headerin(localComment, localPacket);
              m++;
            }
          }
        }
        k = localSyncState.buffer(4096);
        arrayOfByte = localSyncState.data;
        try
        {
          i = ((InputStream)localObject1).read(arrayOfByte, k, 4096);
        }
        catch (Exception localException3)
        {
          System.err.println(localException3);
          System.exit(1);
        }
        if ((i == 0) && (m < 2))
        {
          System.err.println("End of file before finding all Vorbis headers!");
          System.exit(1);
        }
        localSyncState.wrote(i);
      }
      Object localObject2 = localComment.user_comments;
      for (int i1 = 0; i1 < localObject2.length; i1++)
      {
        if (localObject2[i1] == null) {
          break;
        }
        System.err.println(new String(localObject2[i1], 0, localObject2[i1].length - 1));
      }
      System.err.println("\nBitstream is " + localInfo.channels + " channel, " + localInfo.rate + "Hz");
      System.err.println("Encoded by: " + new String(localComment.vendor, 0, localComment.vendor.length - 1) + "\n");
      convsize = 4096 / localInfo.channels;
      localDspState.synthesis_init(localInfo);
      localBlock.init(localDspState);
      localObject2 = new float[1][][];
      int[] arrayOfInt = new int[localInfo.channels];
      while (j == 0)
      {
        while (j == 0)
        {
          int i2 = localSyncState.pageout(localPage);
          if (i2 == 0) {
            break;
          }
          if (i2 == -1)
          {
            System.err.println("Corrupt or missing data in bitstream; continuing...");
          }
          else
          {
            localStreamState.pagein(localPage);
            for (;;)
            {
              i2 = localStreamState.packetout(localPacket);
              if (i2 == 0) {
                break;
              }
              if (i2 != -1)
              {
                if (localBlock.synthesis(localPacket) == 0) {
                  localDspState.synthesis_blockin(localBlock);
                }
                int i3;
                while ((i3 = localDspState.synthesis_pcmout((float[][][])localObject2, arrayOfInt)) > 0)
                {
                  Object localObject3 = localObject2[0];
                  int i4 = 0;
                  int i5 = i3 < convsize ? i3 : convsize;
                  for (m = 0; m < localInfo.channels; m++)
                  {
                    int i6 = m * 2;
                    int i7 = arrayOfInt[m];
                    for (int i8 = 0; i8 < i5; i8++)
                    {
                      int i9 = (int)(localObject3[m][(i7 + i8)] * 32767.0D);
                      if (i9 > 32767)
                      {
                        i9 = 32767;
                        i4 = 1;
                      }
                      if (i9 < -32768)
                      {
                        i9 = -32768;
                        i4 = 1;
                      }
                      if (i9 < 0) {
                        i9 |= 32768;
                      }
                      convbuffer[i6] = ((byte)i9);
                      convbuffer[(i6 + 1)] = ((byte)(i9 >>> 8));
                      i6 += 2 * localInfo.channels;
                    }
                  }
                  System.out.write(convbuffer, 0, 2 * localInfo.channels * i5);
                  localDspState.synthesis_read(i5);
                }
              }
            }
            if (localPage.eos() != 0) {
              j = 1;
            }
          }
        }
        if (j == 0)
        {
          k = localSyncState.buffer(4096);
          arrayOfByte = localSyncState.data;
          try
          {
            i = ((InputStream)localObject1).read(arrayOfByte, k, 4096);
          }
          catch (Exception localException4)
          {
            System.err.println(localException4);
            System.exit(1);
          }
          localSyncState.wrote(i);
          if (i == 0) {
            j = 1;
          }
        }
      }
      localStreamState.clear();
      localBlock.clear();
      localDspState.clear();
      localInfo.clear();
    }
    localSyncState.clear();
    System.err.println("Done.");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.DecodeExample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */