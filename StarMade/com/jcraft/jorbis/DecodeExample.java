/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Packet;
/*     */ import com.jcraft.jogg.Page;
/*     */ import com.jcraft.jogg.StreamState;
/*     */ import com.jcraft.jogg.SyncState;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ class DecodeExample
/*     */ {
/*  36 */   static int convsize = 8192;
/*  37 */   static byte[] convbuffer = new byte[convsize];
/*     */ 
/*     */   public static void main(String[] arg) {
/*  40 */     InputStream input = System.in;
/*  41 */     if (arg.length > 0) {
/*     */       try {
/*  43 */         input = new FileInputStream(arg[0]);
/*     */       }
/*     */       catch (Exception e) {
/*  46 */         System.err.println(e);
/*     */       }
/*     */     }
/*     */ 
/*  50 */     SyncState oy = new SyncState();
/*  51 */     StreamState os = new StreamState();
/*  52 */     Page og = new Page();
/*  53 */     Packet op = new Packet();
/*     */ 
/*  55 */     Info vi = new Info();
/*  56 */     Comment vc = new Comment();
/*  57 */     DspState vd = new DspState();
/*  58 */     Block vb = new Block(vd);
/*     */ 
/*  61 */     int bytes = 0;
/*     */ 
/*  65 */     oy.init();
/*     */     while (true)
/*     */     {
/*  68 */       int eos = 0;
/*     */ 
/*  76 */       int index = oy.buffer(4096);
/*  77 */       byte[] buffer = oy.data;
/*     */       try {
/*  79 */         bytes = input.read(buffer, index, 4096);
/*     */       }
/*     */       catch (Exception e) {
/*  82 */         System.err.println(e);
/*  83 */         System.exit(-1);
/*     */       }
/*  85 */       oy.wrote(bytes);
/*     */ 
/*  88 */       if (oy.pageout(og) != 1)
/*     */       {
/*  90 */         if (bytes < 4096)
/*     */         {
/*     */           break;
/*     */         }
/*  94 */         System.err.println("Input does not appear to be an Ogg bitstream.");
/*  95 */         System.exit(1);
/*     */       }
/*     */ 
/* 100 */       os.init(og.serialno());
/*     */ 
/* 110 */       vi.init();
/* 111 */       vc.init();
/* 112 */       if (os.pagein(og) < 0)
/*     */       {
/* 114 */         System.err.println("Error reading first page of Ogg bitstream data.");
/* 115 */         System.exit(1);
/*     */       }
/*     */ 
/* 118 */       if (os.packetout(op) != 1)
/*     */       {
/* 120 */         System.err.println("Error reading initial header packet.");
/* 121 */         System.exit(1);
/*     */       }
/*     */ 
/* 124 */       if (vi.synthesis_headerin(vc, op) < 0)
/*     */       {
/* 126 */         System.err.println("This Ogg bitstream does not contain Vorbis audio data.");
/*     */ 
/* 128 */         System.exit(1);
/*     */       }
/*     */ 
/* 141 */       int i = 0;
/* 142 */       while (i < 2) {
/* 143 */         while (i < 2)
/*     */         {
/* 145 */           int result = oy.pageout(og);
/* 146 */           if (result == 0)
/*     */           {
/*     */             break;
/*     */           }
/*     */ 
/* 151 */           if (result == 1) {
/* 152 */             os.pagein(og);
/*     */ 
/* 155 */             while (i < 2) {
/* 156 */               result = os.packetout(op);
/* 157 */               if (result == 0)
/*     */                 break;
/* 159 */               if (result == -1)
/*     */               {
/* 162 */                 System.err.println("Corrupt secondary header.  Exiting.");
/* 163 */                 System.exit(1);
/*     */               }
/* 165 */               vi.synthesis_headerin(vc, op);
/* 166 */               i++;
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 171 */         index = oy.buffer(4096);
/* 172 */         buffer = oy.data;
/*     */         try {
/* 174 */           bytes = input.read(buffer, index, 4096);
/*     */         }
/*     */         catch (Exception e) {
/* 177 */           System.err.println(e);
/* 178 */           System.exit(1);
/*     */         }
/* 180 */         if ((bytes == 0) && (i < 2)) {
/* 181 */           System.err.println("End of file before finding all Vorbis headers!");
/* 182 */           System.exit(1);
/*     */         }
/* 184 */         oy.wrote(bytes);
/*     */       }
/*     */ 
/* 190 */       byte[][] ptr = vc.user_comments;
/* 191 */       for (int j = 0; (j < ptr.length) && 
/* 192 */         (ptr[j] != null); j++)
/*     */       {
/* 194 */         System.err.println(new String(ptr[j], 0, ptr[j].length - 1));
/*     */       }
/* 196 */       System.err.println("\nBitstream is " + vi.channels + " channel, " + vi.rate + "Hz");
/*     */ 
/* 198 */       System.err.println("Encoded by: " + new String(vc.vendor, 0, vc.vendor.length - 1) + "\n");
/*     */ 
/* 202 */       convsize = 4096 / vi.channels;
/*     */ 
/* 206 */       vd.synthesis_init(vi);
/* 207 */       vb.init(vd);
/*     */ 
/* 213 */       float[][][] _pcm = new float[1][][];
/* 214 */       int[] _index = new int[vi.channels];
/*     */ 
/* 216 */       while (eos == 0) {
/* 217 */         while (eos == 0)
/*     */         {
/* 219 */           int result = oy.pageout(og);
/* 220 */           if (result == 0)
/*     */             break;
/* 222 */           if (result == -1) {
/* 223 */             System.err.println("Corrupt or missing data in bitstream; continuing...");
/*     */           }
/*     */           else
/*     */           {
/* 227 */             os.pagein(og);
/*     */             while (true)
/*     */             {
/* 230 */               result = os.packetout(op);
/*     */ 
/* 232 */               if (result == 0)
/*     */                 break;
/* 234 */               if (result != -1)
/*     */               {
/* 240 */                 if (vb.synthesis(op) == 0)
/* 241 */                   vd.synthesis_blockin(vb);
/*     */                 int samples;
/* 249 */                 while ((samples = vd.synthesis_pcmout(_pcm, _index)) > 0) {
/* 250 */                   float[][] pcm = _pcm[0];
/* 251 */                   int bout = samples < convsize ? samples : convsize;
/*     */ 
/* 255 */                   for (i = 0; i < vi.channels; i++) {
/* 256 */                     int ptr = i * 2;
/*     */ 
/* 258 */                     int mono = _index[i];
/* 259 */                     for (int j = 0; j < bout; j++) {
/* 260 */                       int val = (int)(pcm[i][(mono + j)] * 32767.0D);
/*     */ 
/* 264 */                       if (val > 32767) {
/* 265 */                         val = 32767;
/*     */                       }
/* 267 */                       if (val < -32768) {
/* 268 */                         val = -32768;
/*     */                       }
/* 270 */                       if (val < 0)
/* 271 */                         val |= 32768;
/* 272 */                       convbuffer[ptr] = ((byte)val);
/* 273 */                       convbuffer[(ptr + 1)] = ((byte)(val >>> 8));
/* 274 */                       ptr += 2 * vi.channels;
/*     */                     }
/*     */                   }
/*     */ 
/* 278 */                   System.out.write(convbuffer, 0, 2 * vi.channels * bout);
/*     */ 
/* 283 */                   vd.synthesis_read(bout);
/*     */                 }
/*     */               }
/*     */             }
/* 287 */             if (og.eos() != 0)
/* 288 */               eos = 1;
/*     */           }
/*     */         }
/* 291 */         if (eos == 0) {
/* 292 */           index = oy.buffer(4096);
/* 293 */           buffer = oy.data;
/*     */           try {
/* 295 */             bytes = input.read(buffer, index, 4096);
/*     */           }
/*     */           catch (Exception e) {
/* 298 */             System.err.println(e);
/* 299 */             System.exit(1);
/*     */           }
/* 301 */           oy.wrote(bytes);
/* 302 */           if (bytes == 0) {
/* 303 */             eos = 1;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 310 */       os.clear();
/*     */ 
/* 315 */       vb.clear();
/* 316 */       vd.clear();
/* 317 */       vi.clear();
/*     */     }
/*     */ 
/* 321 */     oy.clear();
/* 322 */     System.err.println("Done.");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.DecodeExample
 * JD-Core Version:    0.6.2
 */