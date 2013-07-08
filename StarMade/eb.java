/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  17:    */public final class eb
/*  18:    */  extends dO
/*  19:    */  implements dP
/*  20:    */{
/*  21:    */  private ConvexHullShape a;
/*  22:    */  
/*  23:    */  public eb()
/*  24:    */  {
/*  25:    */    ObjectArrayList localObjectArrayList;
/*  26: 26 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  31: 31 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
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
/*  51:    */        }
/*  52: 52 */        else if (s2 == 1) {
/*  53: 53 */          bool = true;
/*  54:    */        }
/*  55: 55 */        else if (s2 == 2) {
/*  56: 56 */          bool = true;
/*  57:    */        }
/*  58: 58 */        else if (s2 == 3) {
/*  59: 59 */          i = 2;
/*  60: 60 */          s2 = 1; } break;
/*  61:    */      
/*  66:    */      case 1: 
/*  67: 67 */        if (s2 == 2)
/*  68: 68 */          s2 = 1; break;
/*  69:    */      
/*  72:    */      case 0: 
/*  73: 73 */        if (s2 != 1) {}
/*  74:    */        break; case 5:  s2 = 0;
/*  75:    */      }
/*  76:    */      
/*  77:    */      
/*  83: 83 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  84:    */    }
/*  85:    */  }
/*  86:    */  
/*  87:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  88: 88 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  89: 89 */      short s2 = s1;
/*  90: 90 */      int i = paramInt1;
/*  91: 91 */      boolean bool = false;
/*  92: 92 */      switch (paramInt1)
/*  93:    */      {
/*  97:    */      case 3: 
/*  98: 98 */        if (s2 == 0) {
/*  99: 99 */          i = 2;
/* 101:    */        }
/* 102:102 */        else if (s2 == 1) {
/* 103:103 */          bool = true;
/* 104:    */        }
/* 105:105 */        else if (s2 == 2) {
/* 106:106 */          bool = true;
/* 107:    */        }
/* 108:108 */        else if (s2 == 3) {
/* 109:109 */          i = 2;
/* 110:110 */          s2 = 1; } break;
/* 111:    */      
/* 116:    */      case 1: 
/* 117:117 */        if (s2 == 2)
/* 118:118 */          s2 = 1; break;
/* 119:    */      
/* 122:    */      case 0: 
/* 123:123 */        if (s2 != 1) {}
/* 124:    */        break; case 5:  s2 = 0;
/* 125:    */      }
/* 126:    */      
/* 127:    */      
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
 * Qualified Name:     eb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */