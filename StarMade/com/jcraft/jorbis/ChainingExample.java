/*    */ package com.jcraft.jorbis;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ class ChainingExample
/*    */ {
/*    */   public static void main(String[] arg)
/*    */   {
/* 31 */     VorbisFile ov = null;
/*    */     try
/*    */     {
/* 34 */       if (arg.length > 0) {
/* 35 */         ov = new VorbisFile(arg[0]);
/*    */       }
/*    */       else
/* 38 */         ov = new VorbisFile(System.in, null, -1);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 42 */       System.err.println(e);
/* 43 */       return;
/*    */     }
/*    */ 
/* 46 */     if (ov.seekable()) {
/* 47 */       System.out.println("Input bitstream contained " + ov.streams() + " logical bitstream section(s).");
/*    */ 
/* 49 */       System.out.println("Total bitstream playing time: " + ov.time_total(-1) + " seconds\n");
/*    */     }
/*    */     else
/*    */     {
/* 53 */       System.out.println("Standard input was not seekable.");
/* 54 */       System.out.println("First logical bitstream information:\n");
/*    */     }
/*    */ 
/* 57 */     for (int i = 0; i < ov.streams(); i++) {
/* 58 */       Info vi = ov.getInfo(i);
/* 59 */       System.out.println("\tlogical bitstream section " + (i + 1) + " information:");
/* 60 */       System.out.println("\t\t" + vi.rate + "Hz " + vi.channels + " channels bitrate " + ov.bitrate(i) / 1000 + "kbps serial number=" + ov.serialnumber(i));
/*    */ 
/* 62 */       System.out.print("\t\tcompressed length: " + ov.raw_total(i) + " bytes ");
/* 63 */       System.out.println(" play time: " + ov.time_total(i) + "s");
/* 64 */       Comment vc = ov.getComment(i);
/* 65 */       System.out.println(vc);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.ChainingExample
 * JD-Core Version:    0.6.2
 */