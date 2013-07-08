/*   1:    */import java.io.DataInputStream;
/*   2:    */import java.io.DataOutputStream;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.zip.DataFormatException;
/*   5:    */import java.util.zip.Deflater;
/*   6:    */import java.util.zip.Inflater;
/*   7:    */import org.schema.game.common.data.world.Universe;
/*   8:    */import org.schema.game.network.objects.NetworkPlayer;
/*   9:    */import org.schema.game.network.objects.remote.RemoteProximitySector;
/*  10:    */
/*  19:    */public class mp
/*  20:    */{
/*  21:    */  private int jdField_a_of_type_Int;
/*  22: 22 */  private q jdField_a_of_type_Q = new q();
/*  23: 23 */  private byte[] jdField_a_of_type_ArrayOfByte = new byte[5488];
/*  24:    */  private lE jdField_a_of_type_LE;
/*  25:    */  
/*  26:    */  public mp(lE paramlE) {
/*  27: 27 */    this.jdField_a_of_type_LE = paramlE;
/*  28:    */  }
/*  29:    */  
/*  30:    */  public final q a(int paramInt, q paramq)
/*  31:    */  {
/*  32: 32 */    paramq.b(this.jdField_a_of_type_Q.jdField_a_of_type_Int - 7, this.jdField_a_of_type_Q.b - 7, this.jdField_a_of_type_Q.c - 7);
/*  33:    */    
/*  37: 37 */    int i = paramInt / 196;
/*  38:    */    
/*  39: 39 */    int j = (paramInt - i * 196) / 14;
/*  40:    */    
/*  41: 41 */    paramInt -= i * 196 + j * 14;
/*  42:    */    
/*  43: 43 */    paramq.a(paramInt, j, i);
/*  44:    */    
/*  45: 45 */    return paramq;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public final byte a(int paramInt) {
/*  49: 49 */    return this.jdField_a_of_type_ArrayOfByte[(paramInt + 2744)];
/*  50:    */  }
/*  51:    */  
/*  52: 52 */  public final byte b(int paramInt) { return this.jdField_a_of_type_ArrayOfByte[paramInt]; }
/*  53:    */  
/*  54:    */  public final int a() {
/*  55: 55 */    return this.jdField_a_of_type_Int;
/*  56:    */  }
/*  57:    */  
/*  61:    */  public final void a(DataInputStream paramDataInputStream)
/*  62:    */  {
/*  63: 63 */    long l1 = System.currentTimeMillis();
/*  64:    */    
/*  65: 65 */    this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/*  66:    */    
/*  67: 67 */    int i = paramDataInputStream.readInt();
/*  68: 68 */    int j = paramDataInputStream.readInt();
/*  69: 69 */    int k = paramDataInputStream.readInt();
/*  70:    */    
/*  71: 71 */    this.jdField_a_of_type_Q.b(i, j, k);
/*  72:    */    
/*  73:    */    byte[] arrayOfByte;
/*  74:    */    
/*  75:    */    Inflater localInflater;
/*  76: 76 */    if (this.jdField_a_of_type_LE.isOnServer()) {
/*  77: 77 */      arrayOfByte = vg.jdField_a_of_type_ArrayOfByte;
/*  78: 78 */      localInflater = vg.jdField_a_of_type_JavaUtilZipInflater;
/*  79:    */    } else {
/*  80: 80 */      arrayOfByte = ct.jdField_a_of_type_ArrayOfByte;
/*  81: 81 */      localInflater = ct.jdField_a_of_type_JavaUtilZipInflater;
/*  82:    */    }
/*  83:    */    
/*  84: 84 */    k = paramDataInputStream.readInt();
/*  85:    */    
/*  86: 86 */    synchronized (arrayOfByte) {
/*  87: 87 */      int m = paramDataInputStream.read(arrayOfByte, 0, k);
/*  88:    */      
/*  89: 89 */      if ((!jdField_a_of_type_Boolean) && (m != k)) throw new AssertionError();
/*  90: 90 */      localInflater.reset();
/*  91: 91 */      localInflater.setInput(arrayOfByte, 0, k);
/*  92:    */      
/*  93:    */      try
/*  94:    */      {
/*  95: 95 */        if ((paramDataInputStream = localInflater.inflate(this.jdField_a_of_type_ArrayOfByte)) == 0) {
/*  96: 96 */          System.err.println("WARNING: INFLATED BYTES 0: " + localInflater.needsInput() + " " + localInflater.needsDictionary());
/*  97:    */        }
/*  98:    */        
/*  99: 99 */        if (paramDataInputStream != this.jdField_a_of_type_ArrayOfByte.length)
/* 100:100 */          System.err.println("[INFLATER] Exception: " + this.jdField_a_of_type_ArrayOfByte.length + " size received: " + k + ": " + paramDataInputStream + "/" + this.jdField_a_of_type_ArrayOfByte.length + " for " + this.jdField_a_of_type_LE + " pos " + this.jdField_a_of_type_Q);
/* 101:    */      } catch (DataFormatException localDataFormatException) {
/* 102:102 */        
/* 103:    */        
/* 104:104 */          localDataFormatException;
/* 105:    */      }
/* 106:    */    }
/* 107:    */    
/* 108:    */    long l2;
/* 109:107 */    if ((l2 = System.currentTimeMillis() - l1) > 5L) {
/* 110:108 */      System.err.println("[CLIENT] WARNING: deserialized PROXIMITY " + this.jdField_a_of_type_Q + " took " + l2 + "ms");
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 117:    */  public final void a(DataOutputStream paramDataOutputStream)
/* 118:    */  {
/* 119:117 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 120:118 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 121:119 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.b);
/* 122:120 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.c);
/* 123:    */    
/* 124:    */    byte[] arrayOfByte;
/* 125:    */    
/* 126:    */    Deflater localDeflater;
/* 127:125 */    if (this.jdField_a_of_type_LE.isOnServer()) {
/* 128:126 */      arrayOfByte = vg.jdField_a_of_type_ArrayOfByte;
/* 129:127 */      localDeflater = vg.jdField_a_of_type_JavaUtilZipDeflater;
/* 130:    */    } else {
/* 131:129 */      arrayOfByte = ct.jdField_a_of_type_ArrayOfByte;
/* 132:130 */      localDeflater = ct.jdField_a_of_type_JavaUtilZipDeflater;
/* 133:    */    }
/* 134:132 */    synchronized (arrayOfByte) {
/* 135:133 */      localDeflater.reset();
/* 136:134 */      localDeflater.setInput(this.jdField_a_of_type_ArrayOfByte);
/* 137:135 */      localDeflater.finish();
/* 138:136 */      int i = localDeflater.deflate(arrayOfByte);
/* 139:137 */      paramDataOutputStream.writeInt(i);
/* 140:138 */      paramDataOutputStream.write(arrayOfByte, 0, i);
/* 141:    */    }
/* 142:140 */    System.err.println("[SERVER] SERIALIZED PROXIMITY " + this.jdField_a_of_type_Q);
/* 143:    */  }
/* 144:    */  
/* 150:    */  public final void a()
/* 151:    */  {
/* 152:150 */    long l1 = System.currentTimeMillis();
/* 153:151 */    vg localvg = (vg)this.jdField_a_of_type_LE.getState();
/* 154:152 */    q localq1 = new q(this.jdField_a_of_type_LE.a());
/* 155:    */    
/* 156:154 */    this.jdField_a_of_type_Q.b(localq1);
/* 157:155 */    this.jdField_a_of_type_Int = this.jdField_a_of_type_LE.c();
/* 158:    */    
/* 159:157 */    localvg.a().updateProximitySectorInformation(localq1);
/* 160:158 */    int i = 0;
/* 161:159 */    q localq2 = new q();
/* 162:    */    
/* 163:161 */    for (int j = localq1.c - 7; j < localq1.c + 7; j++) {
/* 164:162 */      for (int k = localq1.b - 7; k < localq1.b + 7; k++) {
/* 165:163 */        for (int m = localq1.jdField_a_of_type_Int - 7; m < localq1.jdField_a_of_type_Int + 7; m++) {
/* 166:164 */          localq2.b(m, k, j);
/* 167:165 */          mI localmI = localvg.a().getStellarSystemFromSecPos(localq2);
/* 168:    */          
/* 169:167 */          this.jdField_a_of_type_ArrayOfByte[i] = ((byte)localmI.a(localq2).ordinal());
/* 170:168 */          this.jdField_a_of_type_ArrayOfByte[(i + 2744)] = ((byte)localmI.a(localq2).ordinal());
/* 171:169 */          i++;
/* 172:    */        }
/* 173:    */      }
/* 174:    */    }
/* 175:173 */    if ((!jdField_a_of_type_Boolean) && (i != 2744)) throw new AssertionError(i + "/2744");
/* 176:174 */    this.jdField_a_of_type_LE.a().proximitySector.setChanged(true);
/* 177:    */    
/* 178:    */    long l2;
/* 179:177 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 180:178 */      System.err.println("[SERVER] WARNING: UPDATING SERVER SECTORPROXMITY TOOK " + l2);
/* 181:    */    }
/* 182:    */  }
/* 183:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */