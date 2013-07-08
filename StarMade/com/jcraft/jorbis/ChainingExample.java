/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */
/* 27:   */class ChainingExample
/* 28:   */{
/* 29:   */  public static void main(String[] arg)
/* 30:   */  {
/* 31:31 */    VorbisFile ov = null;
/* 32:   */    try
/* 33:   */    {
/* 34:34 */      if (arg.length > 0) {
/* 35:35 */        ov = new VorbisFile(arg[0]);
/* 36:   */      }
/* 37:   */      else {
/* 38:38 */        ov = new VorbisFile(System.in, null, -1);
/* 39:   */      }
/* 40:   */    }
/* 41:   */    catch (Exception e) {
/* 42:42 */      System.err.println(e);
/* 43:43 */      return;
/* 44:   */    }
/* 45:   */    
/* 46:46 */    if (ov.seekable()) {
/* 47:47 */      System.out.println("Input bitstream contained " + ov.streams() + " logical bitstream section(s).");
/* 48:   */      
/* 49:49 */      System.out.println("Total bitstream playing time: " + ov.time_total(-1) + " seconds\n");
/* 50:   */    }
/* 51:   */    else
/* 52:   */    {
/* 53:53 */      System.out.println("Standard input was not seekable.");
/* 54:54 */      System.out.println("First logical bitstream information:\n");
/* 55:   */    }
/* 56:   */    
/* 57:57 */    for (int i = 0; i < ov.streams(); i++) {
/* 58:58 */      Info vi = ov.getInfo(i);
/* 59:59 */      System.out.println("\tlogical bitstream section " + (i + 1) + " information:");
/* 60:60 */      System.out.println("\t\t" + vi.rate + "Hz " + vi.channels + " channels bitrate " + ov.bitrate(i) / 1000 + "kbps serial number=" + ov.serialnumber(i));
/* 61:   */      
/* 62:62 */      System.out.print("\t\tcompressed length: " + ov.raw_total(i) + " bytes ");
/* 63:63 */      System.out.println(" play time: " + ov.time_total(i) + "s");
/* 64:64 */      Comment vc = ov.getComment(i);
/* 65:65 */      System.out.println(vc);
/* 66:   */    }
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.ChainingExample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */