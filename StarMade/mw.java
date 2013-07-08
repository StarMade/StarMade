/*   1:    */import java.io.DataInputStream;
/*   2:    */import java.io.DataOutputStream;
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.io.PushbackInputStream;
/*   6:    */import java.util.zip.DataFormatException;
/*   7:    */import java.util.zip.Deflater;
/*   8:    */import java.util.zip.GZIPInputStream;
/*   9:    */import java.util.zip.Inflater;
/*  10:    */import org.schema.game.common.controller.SegmentController;
/*  11:    */import org.schema.game.common.data.world.DeserializationException;
/*  12:    */import org.schema.game.common.data.world.Segment;
/*  13:    */import org.schema.game.common.data.world.SegmentData;
/*  14:    */
/*  17:    */public class mw
/*  18:    */  extends Segment
/*  19:    */{
/*  20:    */  private long jdField_a_of_type_Long;
/*  21:    */  private boolean jdField_a_of_type_Boolean;
/*  22:    */  public Object b;
/*  23: 23 */  private long jdField_b_of_type_Long = -1L;
/*  24:    */  
/*  25:    */  private boolean jdField_b_of_type_Boolean;
/*  26:    */  private boolean d;
/*  27:    */  
/*  28:    */  public mw(SegmentController paramSegmentController)
/*  29:    */  {
/*  30: 30 */    super(paramSegmentController);this.jdField_b_of_type_JavaLangObject = new Object();
/*  31:    */  }
/*  32:    */  
/*  34:    */  public void a(boolean paramBoolean)
/*  35:    */  {
/*  36: 36 */    this.jdField_a_of_type_Boolean = paramBoolean;System.currentTimeMillis();
/*  37:    */  }
/*  38:    */  
/*  40:    */  public final void a(DataInputStream arg1, int paramInt, boolean paramBoolean)
/*  41:    */  {
/*  42: 42 */    ??? = new PushbackInputStream(???, 2);
/*  43: 43 */    byte[] arrayOfByte = new byte[2];
/*  44: 44 */    ???.read(arrayOfByte);
/*  45: 45 */    ???.unread(arrayOfByte);
/*  46:    */    
/*  49: 49 */    ??? = new DataInputStream(???);
/*  50: 50 */    if ((arrayOfByte[0] == 31) && (arrayOfByte[1] == -117))
/*  51:    */    {
/*  54: 54 */      boolean bool = paramBoolean;paramBoolean = paramInt;paramInt = ???;??? = this; if (paramBoolean) paramInt = new DataInputStream(new GZIPInputStream(paramInt)); else paramInt = new DataInputStream(new GZIPInputStream(paramInt, paramBoolean)); long l2 = paramInt.readLong();localDataInputStream = paramInt.readInt();m = paramInt.readInt();int n = paramInt.readInt(); if (bool) ???.a(localDataInputStream, m, n); if ((!e) && (!bool) && ((???.jdField_a_of_type_Q.a != localDataInputStream) || (???.jdField_a_of_type_Q.b != m) || (???.jdField_a_of_type_Q.c != n))) throw new AssertionError(" deserialized " + localDataInputStream + ", " + m + ", " + n + "; toSerialize " + ???.jdField_a_of_type_Q); int i; if ((i = paramInt.readByte()) == 1) { if (???.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null) ???.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a().assignData(???); ???.d = true; synchronized (???.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData) { ???.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.deSerialize(paramInt); } } if ((!e) && (??? != 2)) throw new AssertionError(); ???.jdField_a_of_type_Long = l2;???.d = false; try { int j; if ((j = paramInt.read()) != -1) throw new DeserializationException("EoF not reached: " + j + " - size given: " + paramBoolean); return; } catch (IOException localIOException2) { IOException localIOException1; (localIOException1 = localIOException2).printStackTrace();throw new DeserializationException("[WARNING][DESERIALIZE] " + ???.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + ": " + ???.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": " + ???.jdField_a_of_type_Q + ": " + localIOException1.getMessage()); } }
/*  55: 55 */    long l1 = ???.readLong();
/*  56:    */    
/*  62: 62 */    int k = ???.readInt();
/*  63: 63 */    DataInputStream localDataInputStream = ???.readInt();
/*  64: 64 */    int m = ???.readInt();
/*  65:    */    
/*  66: 66 */    if (paramBoolean) {
/*  67: 67 */      a(k, localDataInputStream, m);
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    if ((!e) && (!paramBoolean) && ((this.jdField_a_of_type_Q.a != k) || (this.jdField_a_of_type_Q.b != localDataInputStream) || (this.jdField_a_of_type_Q.c != m))) { throw new AssertionError(" deserialized " + k + ", " + localDataInputStream + ", " + m + "; toSerialize " + this.jdField_a_of_type_Q + " on " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  71:    */    }
/*  72:    */    
/*  75: 75 */    if ((paramBoolean = ???.readByte()) == 1)
/*  76:    */    {
/*  78: 78 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController == null) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())) {
/*  79: 79 */        paramBoolean = vg.jdField_a_of_type_ArrayOfByte;
/*  80: 80 */        paramInt = vg.jdField_a_of_type_JavaUtilZipInflater;
/*  81:    */      } else {
/*  82: 82 */        paramBoolean = ct.jdField_a_of_type_ArrayOfByte;
/*  83: 83 */        paramInt = ct.jdField_a_of_type_JavaUtilZipInflater;
/*  84:    */      }
/*  85: 85 */      k = 0;
/*  86: 86 */      if (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null)
/*  87:    */      {
/*  88: 88 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a().assignData(this);
/*  89:    */      }
/*  90:    */      else
/*  91:    */      {
/*  92: 92 */        k = 1;
/*  93:    */      }
/*  94:    */      
/*  96: 96 */      this.d = true;
/*  97:    */      
/*  98: 98 */      localDataInputStream = ???.readInt();
/*  99:    */      
/* 103:103 */      if ((!e) && (localDataInputStream > paramBoolean.length)) throw new AssertionError(localDataInputStream + "/" + paramBoolean.length);
/* 104:104 */      synchronized (paramBoolean) {
/* 105:105 */        ??? = ???.read(paramBoolean, 0, localDataInputStream);
/* 106:    */        
/* 107:107 */        if ((!e) && (??? != localDataInputStream)) { throw new AssertionError();
/* 108:    */        }
/* 109:    */        
/* 118:118 */        paramInt.reset();
/* 119:    */        
/* 120:120 */        paramInt.setInput(paramBoolean, 0, localDataInputStream);
/* 121:    */        
/* 122:122 */        synchronized (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData)
/* 123:    */        {
/* 124:    */          try {
/* 125:125 */            if (k != 0) {
/* 126:126 */              this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.reset();
/* 127:    */            }
/* 128:128 */            paramBoolean = paramInt.inflate(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer());
/* 129:129 */            this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.setNeedsRevalidate(true);
/* 130:130 */            System.currentTimeMillis();
/* 131:131 */            if (!paramBoolean) {
/* 132:132 */              System.err.println("WARNING: INFLATED BYTES 0: " + paramInt.needsInput() + " " + paramInt.needsDictionary());
/* 133:    */            }
/* 134:    */            
/* 135:135 */            if (paramBoolean != this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer().length)
/* 136:136 */              System.err.println("[INFLATER] Exception: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " size received: " + localDataInputStream + ": " + paramBoolean + "/" + this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer().length + " for " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " pos " + this.jdField_a_of_type_Q);
/* 137:    */          } catch (DataFormatException localDataFormatException) {
/* 138:138 */            
/* 139:    */            
/* 140:140 */              localDataFormatException;
/* 141:    */          }
/* 142:    */        }
/* 143:    */      }
/* 144:    */    }
/* 145:    */    
/* 146:144 */    if ((!e) && (paramBoolean != true)) { throw new AssertionError(paramBoolean + "/2: " + k + ", " + localDataInputStream + ", " + ??? + "; byte size: " + paramInt);
/* 147:    */    }
/* 148:    */    
/* 150:148 */    this.jdField_a_of_type_Long = l1;
/* 151:149 */    this.d = false;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public final long a()
/* 155:    */  {
/* 156:154 */    return this.jdField_a_of_type_Long;
/* 157:    */  }
/* 158:    */  
/* 161:    */  public final long b()
/* 162:    */  {
/* 163:161 */    return this.jdField_b_of_type_Long;
/* 164:    */  }
/* 165:    */  
/* 175:    */  public final void a(le paramle)
/* 176:    */  {
/* 177:175 */    super.a(paramle);
/* 178:176 */    if (this.jdField_a_of_type_Boolean) {
/* 179:177 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 180:    */    }
/* 181:    */  }
/* 182:    */  
/* 196:    */  public final boolean e()
/* 197:    */  {
/* 198:196 */    return this.d;
/* 199:    */  }
/* 200:    */  
/* 210:    */  public final boolean f()
/* 211:    */  {
/* 212:210 */    return this.jdField_b_of_type_Boolean;
/* 213:    */  }
/* 214:    */  
/* 265:    */  public final int a(DataOutputStream paramDataOutputStream)
/* 266:    */  {
/* 267:265 */    paramDataOutputStream = new DataOutputStream(paramDataOutputStream);
/* 268:266 */    if ((!e) && (paramDataOutputStream.size() != 0)) throw new AssertionError();
/* 269:267 */    int i = 0;
/* 270:268 */    paramDataOutputStream.writeLong(this.jdField_a_of_type_Long);
/* 271:    */    
/* 272:270 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.a);
/* 273:271 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.b);
/* 274:272 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.c);
/* 275:    */    
/* 276:274 */    i += 20;
/* 277:275 */    if ((!e) && (paramDataOutputStream.size() != 20)) throw new AssertionError(paramDataOutputStream.size() + "/20");
/* 278:276 */    int k = 0;
/* 279:    */    int j;
/* 280:278 */    if ((this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null) && (!g()))
/* 281:    */    {
/* 283:281 */      paramDataOutputStream.writeByte(1);
/* 284:    */      
/* 285:    */      byte[] arrayOfByte;
/* 286:    */      Deflater localDeflater;
/* 287:285 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController == null) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())) {
/* 288:286 */        arrayOfByte = vg.jdField_a_of_type_ArrayOfByte;
/* 289:287 */        localDeflater = vg.jdField_a_of_type_JavaUtilZipDeflater;
/* 290:    */      } else {
/* 291:289 */        arrayOfByte = ct.jdField_a_of_type_ArrayOfByte;
/* 292:290 */        localDeflater = ct.jdField_a_of_type_JavaUtilZipDeflater;
/* 293:    */      }
/* 294:292 */      if ((!e) && (paramDataOutputStream.size() != 21)) { throw new AssertionError(paramDataOutputStream.size() + "/21");
/* 295:    */      }
/* 296:    */      
/* 297:    */      int m;
/* 298:296 */      synchronized (arrayOfByte) {
/* 299:297 */        synchronized (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData) {
/* 300:298 */          localDeflater.reset();
/* 301:299 */          localDeflater.setInput(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer());
/* 302:300 */          localDeflater.finish();
/* 303:301 */          m = localDeflater.deflate(arrayOfByte);
/* 304:    */        }
/* 305:    */        
/* 306:304 */        if ((!e) && (m <= 0)) { throw new AssertionError("[DEFLATER] DELFLATED SIZE: " + m + " for: " + this.jdField_a_of_type_Q + " " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": SData Size: " + this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSize());
/* 307:    */        }
/* 308:    */        
/* 310:308 */        paramDataOutputStream.writeInt(m);
/* 311:309 */        if ((!e) && (paramDataOutputStream.size() != 25)) { throw new AssertionError(paramDataOutputStream.size() + "/25");
/* 312:    */        }
/* 313:    */        
/* 314:312 */        paramDataOutputStream.write(arrayOfByte, 0, m);
/* 315:    */      }
/* 316:314 */      j = m + 25;
/* 317:315 */      if ((!e) && (paramDataOutputStream.size() != j)) throw new AssertionError(paramDataOutputStream.size() + "/" + j);
/* 318:    */    } else {
/* 319:317 */      if ((!e) && (!g())) { throw new AssertionError();
/* 320:    */      }
/* 321:319 */      j++;
/* 322:    */      
/* 323:321 */      paramDataOutputStream.writeByte(2);
/* 324:    */    }
/* 325:    */    
/* 328:326 */    if ((!e) && (paramDataOutputStream.size() != j)) { throw new AssertionError(paramDataOutputStream.size() + "/" + j);
/* 329:    */    }
/* 330:    */    
/* 339:337 */    return paramDataOutputStream.size();
/* 340:    */  }
/* 341:    */  
/* 356:    */  public final void a(long paramLong)
/* 357:    */  {
/* 358:356 */    this.jdField_a_of_type_Long = paramLong;
/* 359:    */  }
/* 360:    */  
/* 362:    */  public final void b(long paramLong)
/* 363:    */  {
/* 364:362 */    this.jdField_b_of_type_Long = paramLong;
/* 365:    */  }
/* 366:    */  
/* 368:    */  public final void f(boolean paramBoolean)
/* 369:    */  {
/* 370:368 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 371:    */  }
/* 372:    */  
/* 373:    */  public String toString() {
/* 374:372 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + "(" + this.jdField_a_of_type_Q + ")[ID" + getId() + "; " + (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null ? Integer.valueOf(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSize()) : "e") + "]";
/* 375:    */  }
/* 376:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */