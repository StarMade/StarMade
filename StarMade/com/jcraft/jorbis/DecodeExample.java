/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Packet;
/*   4:    */import com.jcraft.jogg.Page;
/*   5:    */import com.jcraft.jogg.StreamState;
/*   6:    */import com.jcraft.jogg.SyncState;
/*   7:    */import java.io.FileInputStream;
/*   8:    */import java.io.InputStream;
/*   9:    */import java.io.PrintStream;
/*  10:    */
/*  34:    */class DecodeExample
/*  35:    */{
/*  36: 36 */  static int convsize = 8192;
/*  37: 37 */  static byte[] convbuffer = new byte[convsize];
/*  38:    */  
/*  39:    */  public static void main(String[] arg) {
/*  40: 40 */    InputStream input = System.in;
/*  41: 41 */    if (arg.length > 0) {
/*  42:    */      try {
/*  43: 43 */        input = new FileInputStream(arg[0]);
/*  44:    */      }
/*  45:    */      catch (Exception e) {
/*  46: 46 */        System.err.println(e);
/*  47:    */      }
/*  48:    */    }
/*  49:    */    
/*  50: 50 */    SyncState oy = new SyncState();
/*  51: 51 */    StreamState os = new StreamState();
/*  52: 52 */    Page og = new Page();
/*  53: 53 */    Packet op = new Packet();
/*  54:    */    
/*  55: 55 */    Info vi = new Info();
/*  56: 56 */    Comment vc = new Comment();
/*  57: 57 */    DspState vd = new DspState();
/*  58: 58 */    Block vb = new Block(vd);
/*  59:    */    
/*  61: 61 */    int bytes = 0;
/*  62:    */    
/*  65: 65 */    oy.init();
/*  66:    */    for (;;)
/*  67:    */    {
/*  68: 68 */      int eos = 0;
/*  69:    */      
/*  76: 76 */      int index = oy.buffer(4096);
/*  77: 77 */      byte[] buffer = oy.data;
/*  78:    */      try {
/*  79: 79 */        bytes = input.read(buffer, index, 4096);
/*  80:    */      }
/*  81:    */      catch (Exception e) {
/*  82: 82 */        System.err.println(e);
/*  83: 83 */        System.exit(-1);
/*  84:    */      }
/*  85: 85 */      oy.wrote(bytes);
/*  86:    */      
/*  88: 88 */      if (oy.pageout(og) != 1)
/*  89:    */      {
/*  90: 90 */        if (bytes < 4096) {
/*  91:    */          break;
/*  92:    */        }
/*  93:    */        
/*  94: 94 */        System.err.println("Input does not appear to be an Ogg bitstream.");
/*  95: 95 */        System.exit(1);
/*  96:    */      }
/*  97:    */      
/* 100:100 */      os.init(og.serialno());
/* 101:    */      
/* 110:110 */      vi.init();
/* 111:111 */      vc.init();
/* 112:112 */      if (os.pagein(og) < 0)
/* 113:    */      {
/* 114:114 */        System.err.println("Error reading first page of Ogg bitstream data.");
/* 115:115 */        System.exit(1);
/* 116:    */      }
/* 117:    */      
/* 118:118 */      if (os.packetout(op) != 1)
/* 119:    */      {
/* 120:120 */        System.err.println("Error reading initial header packet.");
/* 121:121 */        System.exit(1);
/* 122:    */      }
/* 123:    */      
/* 124:124 */      if (vi.synthesis_headerin(vc, op) < 0)
/* 125:    */      {
/* 126:126 */        System.err.println("This Ogg bitstream does not contain Vorbis audio data.");
/* 127:    */        
/* 128:128 */        System.exit(1);
/* 129:    */      }
/* 130:    */      
/* 141:141 */      int i = 0;
/* 142:142 */      while (i < 2) {
/* 143:143 */        while (i < 2)
/* 144:    */        {
/* 145:145 */          int result = oy.pageout(og);
/* 146:146 */          if (result == 0) {
/* 147:    */            break;
/* 148:    */          }
/* 149:    */          
/* 151:151 */          if (result == 1) {
/* 152:152 */            os.pagein(og);
/* 153:    */            
/* 155:155 */            while (i < 2) {
/* 156:156 */              result = os.packetout(op);
/* 157:157 */              if (result == 0)
/* 158:    */                break;
/* 159:159 */              if (result == -1)
/* 160:    */              {
/* 162:162 */                System.err.println("Corrupt secondary header.  Exiting.");
/* 163:163 */                System.exit(1);
/* 164:    */              }
/* 165:165 */              vi.synthesis_headerin(vc, op);
/* 166:166 */              i++;
/* 167:    */            }
/* 168:    */          }
/* 169:    */        }
/* 170:    */        
/* 171:171 */        index = oy.buffer(4096);
/* 172:172 */        buffer = oy.data;
/* 173:    */        try {
/* 174:174 */          bytes = input.read(buffer, index, 4096);
/* 175:    */        }
/* 176:    */        catch (Exception e) {
/* 177:177 */          System.err.println(e);
/* 178:178 */          System.exit(1);
/* 179:    */        }
/* 180:180 */        if ((bytes == 0) && (i < 2)) {
/* 181:181 */          System.err.println("End of file before finding all Vorbis headers!");
/* 182:182 */          System.exit(1);
/* 183:    */        }
/* 184:184 */        oy.wrote(bytes);
/* 185:    */      }
/* 186:    */      
/* 190:190 */      byte[][] ptr = vc.user_comments;
/* 191:191 */      for (int j = 0; j < ptr.length; j++) {
/* 192:192 */        if (ptr[j] == null)
/* 193:    */          break;
/* 194:194 */        System.err.println(new String(ptr[j], 0, ptr[j].length - 1));
/* 195:    */      }
/* 196:196 */      System.err.println("\nBitstream is " + vi.channels + " channel, " + vi.rate + "Hz");
/* 197:    */      
/* 198:198 */      System.err.println("Encoded by: " + new String(vc.vendor, 0, vc.vendor.length - 1) + "\n");
/* 199:    */      
/* 202:202 */      convsize = 4096 / vi.channels;
/* 203:    */      
/* 206:206 */      vd.synthesis_init(vi);
/* 207:207 */      vb.init(vd);
/* 208:    */      
/* 213:213 */      float[][][] _pcm = new float[1][][];
/* 214:214 */      int[] _index = new int[vi.channels];
/* 215:    */      
/* 216:216 */      while (eos == 0) {
/* 217:217 */        while (eos == 0)
/* 218:    */        {
/* 219:219 */          int result = oy.pageout(og);
/* 220:220 */          if (result == 0)
/* 221:    */            break;
/* 222:222 */          if (result == -1) {
/* 223:223 */            System.err.println("Corrupt or missing data in bitstream; continuing...");
/* 224:    */          }
/* 225:    */          else
/* 226:    */          {
/* 227:227 */            os.pagein(og);
/* 228:    */            for (;;)
/* 229:    */            {
/* 230:230 */              result = os.packetout(op);
/* 231:    */              
/* 232:232 */              if (result == 0)
/* 233:    */                break;
/* 234:234 */              if (result != -1)
/* 235:    */              {
/* 240:240 */                if (vb.synthesis(op) == 0) {
/* 241:241 */                  vd.synthesis_blockin(vb);
/* 242:    */                }
/* 243:    */                
/* 246:    */                int samples;
/* 247:    */                
/* 249:249 */                while ((samples = vd.synthesis_pcmout(_pcm, _index)) > 0) {
/* 250:250 */                  float[][] pcm = _pcm[0];
/* 251:251 */                  int bout = samples < convsize ? samples : convsize;
/* 252:    */                  
/* 255:255 */                  for (i = 0; i < vi.channels; i++) {
/* 256:256 */                    int ptr = i * 2;
/* 257:    */                    
/* 258:258 */                    int mono = _index[i];
/* 259:259 */                    for (int j = 0; j < bout; j++) {
/* 260:260 */                      int val = (int)(pcm[i][(mono + j)] * 32767.0D);
/* 261:    */                      
/* 264:264 */                      if (val > 32767) {
/* 265:265 */                        val = 32767;
/* 266:    */                      }
/* 267:267 */                      if (val < -32768) {
/* 268:268 */                        val = -32768;
/* 269:    */                      }
/* 270:270 */                      if (val < 0)
/* 271:271 */                        val |= 32768;
/* 272:272 */                      convbuffer[ptr] = ((byte)val);
/* 273:273 */                      convbuffer[(ptr + 1)] = ((byte)(val >>> 8));
/* 274:274 */                      ptr += 2 * vi.channels;
/* 275:    */                    }
/* 276:    */                  }
/* 277:    */                  
/* 278:278 */                  System.out.write(convbuffer, 0, 2 * vi.channels * bout);
/* 279:    */                  
/* 283:283 */                  vd.synthesis_read(bout);
/* 284:    */                }
/* 285:    */              }
/* 286:    */            }
/* 287:287 */            if (og.eos() != 0)
/* 288:288 */              eos = 1;
/* 289:    */          }
/* 290:    */        }
/* 291:291 */        if (eos == 0) {
/* 292:292 */          index = oy.buffer(4096);
/* 293:293 */          buffer = oy.data;
/* 294:    */          try {
/* 295:295 */            bytes = input.read(buffer, index, 4096);
/* 296:    */          }
/* 297:    */          catch (Exception e) {
/* 298:298 */            System.err.println(e);
/* 299:299 */            System.exit(1);
/* 300:    */          }
/* 301:301 */          oy.wrote(bytes);
/* 302:302 */          if (bytes == 0) {
/* 303:303 */            eos = 1;
/* 304:    */          }
/* 305:    */        }
/* 306:    */      }
/* 307:    */      
/* 310:310 */      os.clear();
/* 311:    */      
/* 315:315 */      vb.clear();
/* 316:316 */      vd.clear();
/* 317:317 */      vi.clear();
/* 318:    */    }
/* 319:    */    
/* 321:321 */    oy.clear();
/* 322:322 */    System.err.println("Done.");
/* 323:    */  }
/* 324:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.DecodeExample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */