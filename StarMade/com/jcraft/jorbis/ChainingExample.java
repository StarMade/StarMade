package com.jcraft.jorbis;

import java.io.PrintStream;

class ChainingExample
{
  public static void main(String[] paramArrayOfString)
  {
    VorbisFile localVorbisFile = null;
    try
    {
      localVorbisFile = new VorbisFile(System.in, null, -1);
    }
    catch (Exception localException)
    {
      System.err.println(localException);
      return;
    }
    if (localVorbisFile.seekable())
    {
      System.out.println("Input bitstream contained " + localVorbisFile.streams() + " logical bitstream section(s).");
      System.out.println("Total bitstream playing time: " + localVorbisFile.time_total(-1) + " seconds\n");
    }
    else
    {
      System.out.println("Standard input was not seekable.");
      System.out.println("First logical bitstream information:\n");
    }
    for (int i = 0; i < localVorbisFile.streams(); i++)
    {
      Info localInfo = localVorbisFile.getInfo(i);
      System.out.println("\tlogical bitstream section " + (i + 1) + " information:");
      System.out.println("\t\t" + localInfo.rate + "Hz " + localInfo.channels + " channels bitrate " + localVorbisFile.bitrate(i) / 1000 + "kbps serial number=" + localVorbisFile.serialnumber(i));
      System.out.print("\t\tcompressed length: " + localVorbisFile.raw_total(i) + " bytes ");
      System.out.println(" play time: " + localVorbisFile.time_total(i) + "s");
      Comment localComment = localVorbisFile.getComment(i);
      System.out.println(localComment);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.ChainingExample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */