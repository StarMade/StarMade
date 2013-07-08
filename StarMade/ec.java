/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  17:    */public final class ec
/*  18:    */  extends dO
/*  19:    */  implements dP
/*  20:    */{
/*  21:    */  private ConvexHullShape a;
/*  22:    */  
/*  23:    */  public ec()
/*  24:    */  {
/*  25:    */    ObjectArrayList localObjectArrayList;
/*  26: 26 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  31: 31 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  32:    */    
/*  33: 33 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  37: 37 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  38: 38 */      short s2 = s1;
/*  39: 39 */      int i = paramInt;
/*  40: 40 */      boolean bool = false;
/*  41:    */      
/*  42: 42 */      switch (paramInt)
/*  43:    */      {
/*  46:    */      case 3: 
/*  47: 47 */        if (s2 == 0) {
/*  48: 48 */          bool = true;
/*  49:    */        }
/*  50: 50 */        else if (s2 == 1) {
/*  51: 51 */          bool = true;
/*  52:    */        }
/*  53: 53 */        else if (s2 == 2) {
/*  54: 54 */          i = 2;
/*  55: 55 */          s2 = 2;
/*  56:    */        }
/*  57: 57 */        else if (s2 == 3) {
/*  58: 58 */          i = 2;
/*  59: 59 */          s2 = 1; } break;
/*  60:    */      
/*  63:    */      case 1: 
/*  64: 64 */        s2 = 0;
/*  65: 65 */        break;
/*  66:    */      
/*  67:    */      case 0: 
/*  68: 68 */        break;
/*  69:    */      
/*  70:    */      case 5: 
/*  71: 71 */        if (s2 == 1)
/*  72: 72 */          s2 = 0; break;
/*  73:    */      
/*  76:    */      case 4: 
/*  77: 77 */        if (s2 == 2) {
/*  78: 78 */          s2 = 1;
/*  79:    */        }
/*  80:    */        break;
/*  81:    */      }
/*  82:    */      
/*  83: 83 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  84:    */    }
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  88: 88 */      short s2 = s1;
/*  89: 89 */      int i = paramInt1;
/*  90: 90 */      boolean bool = false;
/*  91:    */      
/*  92: 92 */      switch (paramInt1)
/*  93:    */      {
/*  96:    */      case 3: 
/*  97: 97 */        if (s2 == 0) {
/*  98: 98 */          bool = true;
/*  99:    */        }
/* 100:100 */        else if (s2 == 1) {
/* 101:101 */          bool = true;
/* 102:    */        }
/* 103:103 */        else if (s2 == 2) {
/* 104:104 */          i = 2;
/* 105:105 */          s2 = 2;
/* 106:    */        }
/* 107:107 */        else if (s2 == 3) {
/* 108:108 */          i = 2;
/* 109:109 */          s2 = 1; } break;
/* 110:    */      
/* 113:    */      case 1: 
/* 114:114 */        s2 = 0;
/* 115:115 */        break;
/* 116:    */      
/* 117:    */      case 0: 
/* 118:118 */        break;
/* 119:    */      
/* 120:    */      case 5: 
/* 121:121 */        if (s2 == 1)
/* 122:122 */          s2 = 0; break;
/* 123:    */      
/* 126:    */      case 4: 
/* 127:127 */        if (s2 == 2) {
/* 128:128 */          s2 = 1;
/* 129:    */        }
/* 130:    */        break;
/* 131:    */      }
/* 132:    */      
/* 133:133 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 134:    */    }
/* 135:    */  }
/* 136:    */  
/* 137:    */  protected final ConvexShape a()
/* 138:    */  {
/* 139:139 */    return this.a;
/* 140:    */  }
/* 141:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ec
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */