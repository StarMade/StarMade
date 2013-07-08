/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  20:    */public final class ei
/*  21:    */  extends dO
/*  22:    */  implements dP
/*  23:    */{
/*  24:    */  private ConvexHullShape a;
/*  25:    */  
/*  26:    */  public ei()
/*  27:    */  {
/*  28:    */    ObjectArrayList localObjectArrayList;
/*  29: 29 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  31:    */    
/*  32: 32 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  33: 33 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  34:    */    
/*  35: 35 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  36: 36 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  37:    */    
/*  39: 39 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*  43:    */  {
/*  44: 44 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  45: 45 */      short s2 = s1;
/*  46: 46 */      int i = paramInt;
/*  47: 47 */      boolean bool = false;
/*  48: 48 */      switch (paramInt)
/*  49:    */      {
/*  53:    */      case 4: 
/*  54: 54 */        if (s2 == 1) {
/*  55: 55 */          bool = true;
/*  56:    */        }
/*  57: 57 */        else if (s2 == 2) {
/*  58: 58 */          bool = true;
/*  59:    */        }
/*  60: 60 */        else if (s2 == 0) {
/*  61: 61 */          i = 5;
/*  62: 62 */          s2 = 3;
/*  63:    */        }
/*  64: 64 */        else if (s2 == 3) {
/*  65: 65 */          i = 5;
/*  66: 66 */          s2 = 0; } break;
/*  67:    */      
/*  71:    */      case 1: 
/*  72: 72 */        break;
/*  73:    */      
/*  74:    */      case 0: 
/*  75: 75 */        s2 = 0;
/*  76: 76 */        break;
/*  77:    */      
/*  78:    */      case 3: 
/*  79: 79 */        if (s2 == 1)
/*  80: 80 */          s2 = 0; break;
/*  81:    */      
/*  84:    */      case 2: 
/*  85: 85 */        if (s2 == 3) {
/*  86: 86 */          s2 = 2;
/*  87:    */        }
/*  88:    */        break;
/*  89:    */      }
/*  90:    */      
/*  91: 91 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  95:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  96: 96 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  97: 97 */      short s2 = s1;
/*  98: 98 */      int i = paramInt1;
/*  99: 99 */      boolean bool = false;
/* 100:    */      
/* 101:101 */      switch (paramInt1)
/* 102:    */      {
/* 105:    */      case 4: 
/* 106:106 */        if (s2 == 1) {
/* 107:107 */          bool = true;
/* 108:    */        }
/* 109:109 */        else if (s2 == 2) {
/* 110:110 */          bool = true;
/* 111:    */        }
/* 112:112 */        else if (s2 == 0) {
/* 113:113 */          i = 5;
/* 114:114 */          s2 = 3;
/* 115:    */        }
/* 116:116 */        else if (s2 == 3) {
/* 117:117 */          i = 5;
/* 118:118 */          s2 = 0; } break;
/* 119:    */      
/* 123:    */      case 1: 
/* 124:124 */        break;
/* 125:    */      
/* 126:    */      case 0: 
/* 127:127 */        s2 = 0;
/* 128:128 */        break;
/* 129:    */      
/* 130:    */      case 3: 
/* 131:131 */        if (s2 == 1)
/* 132:132 */          s2 = 0; break;
/* 133:    */      
/* 136:    */      case 2: 
/* 137:137 */        if (s2 == 3) {
/* 138:138 */          s2 = 2;
/* 139:    */        }
/* 140:    */        break;
/* 141:    */      }
/* 142:    */      
/* 143:143 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 144:    */    }
/* 145:    */  }
/* 146:    */  
/* 147:    */  protected final ConvexShape a()
/* 148:    */  {
/* 149:149 */    return this.a;
/* 150:    */  }
/* 151:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ei
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */