/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  17:    */public final class eh
/*  18:    */  extends dO
/*  19:    */  implements dP
/*  20:    */{
/*  21:    */  private ConvexHullShape a;
/*  22:    */  
/*  23:    */  public eh()
/*  24:    */  {
/*  25:    */    ObjectArrayList localObjectArrayList;
/*  26: 26 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  28:    */    
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  31:    */    
/*  32: 32 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  33: 33 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  34:    */    
/*  35: 35 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  39: 39 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  40: 40 */      short s2 = s1;
/*  41: 41 */      int i = paramInt;
/*  42: 42 */      boolean bool = false;
/*  43:    */      
/*  44: 44 */      switch (paramInt)
/*  45:    */      {
/*  48:    */      case 4: 
/*  49: 49 */        if (s2 == 0) {
/*  50: 50 */          bool = true;
/*  51:    */        }
/*  52: 52 */        else if (s2 == 3) {
/*  53: 53 */          bool = true;
/*  54:    */        }
/*  55: 55 */        else if (s2 == 1) {
/*  56: 56 */          i = 5;
/*  57: 57 */          s2 = 2;
/*  58:    */        }
/*  59: 59 */        else if (s2 == 2) {
/*  60: 60 */          i = 5;
/*  61: 61 */          s2 = 1; } break;
/*  62:    */      
/*  65:    */      case 1: 
/*  66: 66 */        s2 = 0;
/*  67: 67 */        break;
/*  68:    */      
/*  69:    */      case 0: 
/*  70: 70 */        break;
/*  71:    */      
/*  72:    */      case 3: 
/*  73: 73 */        if (s2 == 2)
/*  74: 74 */          s2 = 1; break;
/*  75:    */      
/*  78:    */      case 2: 
/*  79: 79 */        if (s2 == 2) {
/*  80: 80 */          s2 = 1;
/*  81:    */        }
/*  82:    */        break;
/*  83:    */      }
/*  84:    */      
/*  85: 85 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  89: 89 */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  90: 90 */      short s2 = s1;
/*  91: 91 */      int i = paramInt1;
/*  92: 92 */      boolean bool = false;
/*  93:    */      
/*  94: 94 */      switch (paramInt1)
/*  95:    */      {
/*  98:    */      case 4: 
/*  99: 99 */        if (s2 == 0) {
/* 100:100 */          bool = true;
/* 101:    */        }
/* 102:102 */        else if (s2 == 3) {
/* 103:103 */          bool = true;
/* 104:    */        }
/* 105:105 */        else if (s2 == 1) {
/* 106:106 */          i = 5;
/* 107:107 */          s2 = 2;
/* 108:    */        }
/* 109:109 */        else if (s2 == 2) {
/* 110:110 */          i = 5;
/* 111:111 */          s2 = 1; } break;
/* 112:    */      
/* 115:    */      case 1: 
/* 116:116 */        s2 = 0;
/* 117:117 */        break;
/* 118:    */      
/* 119:    */      case 0: 
/* 120:120 */        break;
/* 121:    */      
/* 122:    */      case 3: 
/* 123:123 */        if (s2 == 2)
/* 124:124 */          s2 = 1; break;
/* 125:    */      
/* 128:    */      case 2: 
/* 129:129 */        if (s2 == 2) {
/* 130:130 */          s2 = 1;
/* 131:    */        }
/* 132:    */        break;
/* 133:    */      }
/* 134:    */      
/* 135:135 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 139:    */  protected final ConvexShape a()
/* 140:    */  {
/* 141:141 */    return this.a;
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */