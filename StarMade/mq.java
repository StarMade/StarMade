/*   1:    */import java.io.DataInputStream;
/*   2:    */import java.io.DataOutputStream;
/*   3:    */import org.schema.game.common.data.world.Universe;
/*   4:    */import org.schema.game.network.objects.NetworkPlayer;
/*   5:    */import org.schema.game.network.objects.remote.RemoteProximitySystem;
/*   6:    */
/*  14:    */public class mq
/*  15:    */{
/*  16: 16 */  private q jdField_a_of_type_Q = new q();
/*  17:    */  
/*  19: 19 */  private long[] jdField_a_of_type_ArrayOfLong = new long[27];
/*  20: 20 */  private byte[] jdField_a_of_type_ArrayOfByte = new byte[27];
/*  21:    */  private lE jdField_a_of_type_LE;
/*  22:    */  
/*  23:    */  public mq(lE paramlE)
/*  24:    */  {
/*  25: 25 */    this.jdField_a_of_type_LE = paramlE;
/*  26:    */  }
/*  27:    */  
/*  28:    */  public final q a(int paramInt, q paramq)
/*  29:    */  {
/*  30: 30 */    paramq.b(this.jdField_a_of_type_Q.a - 1, this.jdField_a_of_type_Q.b - 1, this.jdField_a_of_type_Q.c - 1);
/*  31:    */    
/*  35: 35 */    int i = paramInt / 9;
/*  36:    */    
/*  37: 37 */    int j = (paramInt - i * 9) / 3;
/*  38:    */    
/*  39: 39 */    paramInt -= i * 9 + j * 3;
/*  40:    */    
/*  41: 41 */    paramq.a(paramInt, j, i);
/*  42:    */    
/*  43: 43 */    return paramq;
/*  44:    */  }
/*  45:    */  
/*  46:    */  public final q a()
/*  47:    */  {
/*  48: 48 */    return this.jdField_a_of_type_Q;
/*  49:    */  }
/*  50:    */  
/*  52:    */  public final void a(DataInputStream paramDataInputStream)
/*  53:    */  {
/*  54: 54 */    int i = paramDataInputStream.readInt();
/*  55: 55 */    int j = paramDataInputStream.readInt();
/*  56: 56 */    int k = paramDataInputStream.readInt();
/*  57:    */    
/*  58: 58 */    this.jdField_a_of_type_Q.b(i, j, k);
/*  59:    */    
/*  61: 61 */    for (i = 0; i < 27; i++) {
/*  62: 62 */      this.jdField_a_of_type_ArrayOfByte[i] = paramDataInputStream.readByte();
/*  63: 63 */      this.jdField_a_of_type_ArrayOfLong[i] = paramDataInputStream.readLong();
/*  64:    */    }
/*  65:    */  }
/*  66:    */  
/*  71:    */  public final void a(DataOutputStream paramDataOutputStream)
/*  72:    */  {
/*  73: 73 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.a);
/*  74: 74 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.b);
/*  75: 75 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.c);
/*  76: 76 */    for (int i = 0; i < 27; i++) {
/*  77: 77 */      paramDataOutputStream.writeByte(this.jdField_a_of_type_ArrayOfByte[i]);
/*  78: 78 */      paramDataOutputStream.writeLong(this.jdField_a_of_type_ArrayOfLong[i]);
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  85:    */  public final void a()
/*  86:    */  {
/*  87: 87 */    vg localvg = (vg)this.jdField_a_of_type_LE.getState();
/*  88:    */    
/*  89: 89 */    q localq = new q(this.jdField_a_of_type_LE.a());
/*  90:    */    
/*  91: 91 */    this.jdField_a_of_type_Q.b(localq);
/*  92:    */    
/*  93: 93 */    localvg.a().updateProximitySectorInformation(localq);
/*  94:    */    
/*  95: 95 */    int i = 0;
/*  96:    */    
/*  97: 97 */    localq = new q(localq);
/*  98:    */    
/*  99: 99 */    mI localmI1 = localvg.a().getStellarSystemFromSecPos(localq);
/* 100:    */    
/* 101:101 */    this.jdField_a_of_type_Q.b(localmI1.jdField_a_of_type_Q);
/* 102:    */    
/* 103:103 */    for (int j = -1; j <= 1; j++) {
/* 104:104 */      for (int k = -1; k <= 1; k++) {
/* 105:105 */        for (int m = -1; m <= 1; m++)
/* 106:    */        {
/* 107:107 */          localq.b(this.jdField_a_of_type_Q.a + m, this.jdField_a_of_type_Q.b + k, this.jdField_a_of_type_Q.c + j);
/* 108:    */          
/* 109:109 */          mI localmI2 = localvg.a().getStellarSystemFromStellarPos(localq);
/* 110:    */          
/* 111:111 */          this.jdField_a_of_type_ArrayOfByte[i] = ((byte)localmI2.jdField_a_of_type_MD.ordinal());
/* 112:    */          
/* 113:113 */          this.jdField_a_of_type_ArrayOfLong[i] = localmI2.jdField_a_of_type_Long;
/* 114:    */          
/* 118:118 */          i++;
/* 119:    */        }
/* 120:    */      }
/* 121:    */    }
/* 122:122 */    if ((!jdField_a_of_type_Boolean) && (i != 27)) throw new AssertionError(i + "/27");
/* 123:123 */    synchronized (this.jdField_a_of_type_LE.a()) {
/* 124:124 */      this.jdField_a_of_type_LE.a().proximitySystem.setChanged(true); return;
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 129:    */  public final int a(q paramq)
/* 130:    */  {
/* 131:131 */    q localq = new q(this.jdField_a_of_type_Q.a - paramq.a + 1, this.jdField_a_of_type_Q.b - paramq.b + 1, this.jdField_a_of_type_Q.c - paramq.c + 1);
/* 132:    */    
/* 146:146 */    if ((!jdField_a_of_type_Boolean) && ((localq.a < 0) || (localq.b < 0) || (localq.c < 0))) { throw new AssertionError(localq + ": " + paramq + " / " + this.jdField_a_of_type_Q);
/* 147:    */    }
/* 148:148 */    int i = localq.c * 9 + localq.b * 3 + localq.a;
/* 149:    */    
/* 150:150 */    if ((!jdField_a_of_type_Boolean) && (!a(i, new q()).equals(paramq))) { throw new AssertionError(a(i, new q()) + " / " + paramq);
/* 151:    */    }
/* 152:152 */    if ((i < 0) || (i >= 27)) {
/* 153:153 */      return -1;
/* 154:    */    }
/* 155:    */    
/* 158:158 */    return i;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public final byte a(int paramInt)
/* 162:    */  {
/* 163:163 */    return this.jdField_a_of_type_ArrayOfByte[paramInt];
/* 164:    */  }
/* 165:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */