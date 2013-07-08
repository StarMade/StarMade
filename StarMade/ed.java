/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  17:    */public final class ed
/*  18:    */  extends dO
/*  19:    */  implements dP
/*  20:    */{
/*  21:    */  private ConvexHullShape a;
/*  22:    */  
/*  23:    */  public ed()
/*  24:    */  {
/*  25:    */    ObjectArrayList localObjectArrayList;
/*  26: 26 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  31: 31 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  32:    */    
/*  33: 33 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*  37:    */  {
/*  38: 38 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  39: 39 */      short s2 = s1;
/*  40: 40 */      int i = paramInt;
/*  41: 41 */      boolean bool = false;
/*  42: 42 */      switch (paramInt)
/*  43:    */      {
/*  47:    */      case 3: 
/*  48: 48 */        if (s2 == 0) {
/*  49: 49 */          i = 2;
/*  50: 50 */          s2 = 0;
/*  51:    */        }
/*  52: 52 */        else if (s2 == 1) {
/*  53: 53 */          i = 2;
/*  54: 54 */          s2 = 3;
/*  55:    */        }
/*  56: 56 */        else if (s2 == 2) {
/*  57: 57 */          bool = true;
/*  58:    */        }
/*  59: 59 */        else if (s2 == 3) {
/*  60: 60 */          bool = true; } break;
/*  61:    */      
/*  64:    */      case 0: 
/*  65: 65 */        s2 = 0;
/*  66: 66 */        break;
/*  67:    */      
/*  69:    */      case 1: 
/*  70: 70 */        break;
/*  71:    */      
/*  72:    */      case 5: 
/*  73: 73 */        if (s2 == 0)
/*  74: 74 */          s2 = 3; break;
/*  75:    */      
/*  78:    */      case 4: 
/*  79: 79 */        if (s2 == 3) {
/*  80: 80 */          s2 = 0;
/*  81:    */        }
/*  82:    */        break;
/*  83:    */      }
/*  84:    */      
/*  85: 85 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  89:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  90: 90 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  91: 91 */      short s2 = s1;
/*  92: 92 */      int i = paramInt1;
/*  93: 93 */      boolean bool = false;
/*  94: 94 */      switch (paramInt1)
/*  95:    */      {
/*  99:    */      case 3: 
/* 100:100 */        if (s2 == 0) {
/* 101:101 */          i = 2;
/* 102:102 */          s2 = 0;
/* 103:    */        }
/* 104:104 */        else if (s2 == 1) {
/* 105:105 */          i = 2;
/* 106:106 */          s2 = 3;
/* 107:    */        }
/* 108:108 */        else if (s2 == 2) {
/* 109:109 */          bool = true;
/* 110:    */        }
/* 111:111 */        else if (s2 == 3) {
/* 112:112 */          bool = true; } break;
/* 113:    */      
/* 116:    */      case 0: 
/* 117:117 */        s2 = 0;
/* 118:118 */        break;
/* 119:    */      
/* 121:    */      case 1: 
/* 122:122 */        break;
/* 123:    */      
/* 124:    */      case 5: 
/* 125:125 */        if (s2 == 0)
/* 126:126 */          s2 = 3; break;
/* 127:    */      
/* 130:    */      case 4: 
/* 131:131 */        if (s2 == 3) {
/* 132:132 */          s2 = 0;
/* 133:    */        }
/* 134:    */        break;
/* 135:    */      }
/* 136:    */      
/* 137:137 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 141:    */  protected final ConvexShape a()
/* 142:    */  {
/* 143:143 */    return this.a;
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ed
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */