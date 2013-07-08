/*   1:    */package org.schema.game.network.objects.remote;
/*   2:    */
/*   3:    */import jL;
/*   4:    */import java.io.BufferedInputStream;
/*   5:    */import java.io.ByteArrayInputStream;
/*   6:    */import java.io.DataInputStream;
/*   7:    */import java.io.DataOutputStream;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.InputStream;
/*  10:    */import java.io.OutputStream;
/*  11:    */import java.io.PipedInputStream;
/*  12:    */import java.io.PipedOutputStream;
/*  13:    */import java.io.PrintStream;
/*  14:    */import java.util.zip.GZIPInputStream;
/*  15:    */import java.util.zip.GZIPOutputStream;
/*  16:    */import mw;
/*  17:    */import org.schema.common.util.ByteUtil;
/*  18:    */import org.schema.game.common.controller.SegmentController;
/*  19:    */import org.schema.game.common.data.world.Segment;
/*  20:    */import org.schema.game.common.data.world.SegmentData;
/*  21:    */import org.schema.schine.network.objects.NetworkObject;
/*  22:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  23:    */
/*  24:    */public class RemoteSegmentGZipPackage extends RemoteField
/*  25:    */{
/*  26: 26 */  byte[] bufferBytes = new byte[5];
/*  27: 27 */  byte[] compressedBuffer = new byte[16384];
/*  28:    */  
/*  29: 29 */  public RemoteSegmentGZipPackage(mw parammw, NetworkObject paramNetworkObject) { super(parammw, paramNetworkObject); }
/*  30:    */  
/*  31:    */  public int byteLength()
/*  32:    */  {
/*  33: 33 */    throw new IllegalArgumentException("Size unknown for zipped Stream");
/*  34:    */  }
/*  35:    */  
/*  37:    */  public void fromByteStream(DataInputStream paramDataInputStream, int arg2)
/*  38:    */  {
/*  39: 39 */    ??? = ByteUtil.a(paramDataInputStream);
/*  40:    */    
/*  43: 43 */    assert (get() != null);
/*  44: 44 */    assert (((mw)get()).a() != null);
/*  45: 45 */    assert (((mw)get()).a() != null);
/*  46: 46 */    if (??? <= 0) {
/*  47: 47 */      System.err.println("Sent empty Segment " + ((mw)get()).a);
/*  48: 48 */      return;
/*  49:    */    }
/*  50: 50 */    assert (??? <= this.compressedBuffer.length) : ???;
/*  51: 51 */    assert (((mw)get()).a() != null);
/*  52: 52 */    for (int i = 0; i < ???; i++) {
/*  53: 53 */      this.compressedBuffer[i] = ((byte)paramDataInputStream.read());
/*  54:    */    }
/*  55:    */    
/*  56: 56 */    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.compressedBuffer, 0, ???);
/*  57:    */    
/*  58: 58 */    ((mw)get()).a().reset();
/*  59:    */    
/*  64:    */    try
/*  65:    */    {
/*  66: 66 */      long l = ByteUtil.a(paramDataInputStream = new GZIPInputStream(localByteArrayInputStream));
/*  67:    */      
/*  68: 68 */      synchronized (((mw)get()).a()) {
/*  69: 69 */        while (paramDataInputStream.read(this.bufferBytes) > 0) {
/*  70: 70 */          byte b1 = this.bufferBytes[0];
/*  71: 71 */          byte b2 = this.bufferBytes[1];
/*  72: 72 */          byte b3 = this.bufferBytes[2];
/*  73: 73 */          short s = ByteUtil.a(this.bufferBytes, 3);
/*  74:    */          
/*  75: 75 */          ((mw)get()).a().setInfoElementUnsynched(b1, b2, b3, s, false);
/*  76:    */        }
/*  77:    */      }
/*  78:    */      
/*  79: 79 */      ((mw)get()).a().getSegmentBuffer().b((Segment)get());
/*  80: 80 */      ((mw)get()).a(l);
/*  81:    */      
/*  82: 82 */      paramDataInputStream.close(); return;
/*  83: 83 */    } catch (IOException localIOException) { 
/*  84:    */      
/*  85: 85 */        localIOException;
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  95:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/*  96:    */  {
/*  97: 95 */    assert (get() != null);
/*  98:    */    
/*  99: 97 */    Object localObject1 = new PipedOutputStream();
/* 100: 98 */    Object localObject3 = new PipedInputStream((PipedOutputStream)localObject1);
/* 101: 99 */    localObject3 = new BufferedInputStream((InputStream)localObject3);
/* 102:    */    
/* 108:106 */    synchronized ((mw)get()) {
/* 109:107 */      try { localObject1 = new GZIPOutputStream((OutputStream)localObject1);
/* 110:    */        
/* 112:110 */        SegmentData localSegmentData = ((mw)get()).a();
/* 113:111 */        assert (localSegmentData != null);
/* 114:112 */        ((GZIPOutputStream)localObject1).write(ByteUtil.a(((mw)get()).a()));
/* 115:113 */        for (byte b1 = 0; b1 < 16; 
/* 116:114 */            b1 = (byte)(b1 + 1)) {
/* 117:115 */          for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 118:116 */            for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
/* 119:117 */              int j = SegmentData.getInfoIndex(b3, b2, b1);
/* 120:118 */              if (localSegmentData.contains(j)) {
/* 121:119 */                this.bufferBytes[0] = b3;
/* 122:120 */                this.bufferBytes[1] = b2;
/* 123:121 */                this.bufferBytes[2] = b1;
/* 124:122 */                ByteUtil.a(localSegmentData.getType(j), this.bufferBytes, 3);
/* 125:123 */                ((GZIPOutputStream)localObject1).write(this.bufferBytes);
/* 126:    */              }
/* 127:    */            }
/* 128:    */          }
/* 129:    */        }
/* 130:    */        
/* 132:130 */        ((GZIPOutputStream)localObject1).finish();
/* 133:131 */        ((GZIPOutputStream)localObject1).close();
/* 134:132 */      } catch (IOException localIOException) { 
/* 135:    */        
/* 136:134 */          localIOException;
/* 137:    */      }
/* 138:    */    }
/* 139:    */    
/* 140:136 */    ??? = 0;
/* 141:137 */    Object localObject2 = 0;
/* 142:    */    
/* 143:139 */    ByteUtil.a(((BufferedInputStream)localObject3).available(), paramDataOutputStream);
/* 144:    */    int i;
/* 145:141 */    while ((??? = ((BufferedInputStream)localObject3).read(this.bufferBytes)) > 0) {
/* 146:142 */      paramDataOutputStream.write(this.bufferBytes, 0, ???);
/* 147:143 */      localObject2 += ???;
/* 148:    */    }
/* 149:    */    
/* 150:146 */    return i + 4;
/* 151:    */  }
/* 152:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentGZipPackage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */