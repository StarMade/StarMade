/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  17:    */public final class eg
/*  18:    */  extends dO
/*  19:    */  implements dP
/*  20:    */{
/*  21:    */  private ConvexHullShape a;
/*  22:    */  
/*  23:    */  public eg()
/*  24:    */  {
/*  25:    */    ObjectArrayList localObjectArrayList;
/*  26: 26 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  28:    */    
/*  29: 29 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  31:    */    
/*  32: 32 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  33: 33 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  34:    */    
/*  35: 35 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*  39:    */  {
/*  40: 40 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  41: 41 */      short s2 = s1;
/*  42: 42 */      int i = paramInt;
/*  43: 43 */      boolean bool = false;
/*  44: 44 */      switch (paramInt)
/*  45:    */      {
/*  49:    */      case 0: 
/*  50: 50 */        if (s2 == 0)
/*  51:    */        {
/*  52: 52 */          bool = true;
/*  53: 53 */        } else if (s2 == 1) {
/*  54: 54 */          i = 1;
/*  55: 55 */          s2 = 2;
/*  56:    */        }
/*  57: 57 */        else if (s2 == 2) {
/*  58: 58 */          i = 1;
/*  59: 59 */          s2 = 1;
/*  60:    */        }
/*  61: 61 */        else if (s2 == 3) {
/*  62: 62 */          bool = true; } break;
/*  63:    */      
/*  66:    */      case 3: 
/*  67: 67 */        if (s2 == 0)
/*  68: 68 */          s2 = 3; break;
/*  69:    */      
/*  72:    */      case 2: 
/*  73: 73 */        if (s2 == 0)
/*  74: 74 */          s2 = 3; break;
/*  75:    */      
/*  78:    */      case 5: 
/*  79: 79 */        s2 = 0;
/*  80:    */      }
/*  81:    */      
/*  82:    */      
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
/*  97:    */      case 0: 
/*  98: 98 */        if (s2 == 0)
/*  99:    */        {
/* 100:100 */          bool = true;
/* 101:101 */        } else if (s2 == 1) {
/* 102:102 */          i = 1;
/* 103:103 */          s2 = 2;
/* 104:    */        }
/* 105:105 */        else if (s2 == 2) {
/* 106:106 */          i = 1;
/* 107:107 */          s2 = 1;
/* 108:    */        }
/* 109:109 */        else if (s2 == 3) {
/* 110:110 */          bool = true; } break;
/* 111:    */      
/* 114:    */      case 3: 
/* 115:115 */        if (s2 == 0)
/* 116:116 */          s2 = 3; break;
/* 117:    */      
/* 120:    */      case 2: 
/* 121:121 */        if (s2 == 0)
/* 122:122 */          s2 = 3; break;
/* 123:    */      
/* 126:    */      case 5: 
/* 127:127 */        s2 = 0;
/* 128:    */      }
/* 129:    */      
/* 130:    */      
/* 131:131 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 132:    */    }
/* 133:    */  }
/* 134:    */  
/* 135:    */  protected final ConvexShape a()
/* 136:    */  {
/* 137:137 */    return this.a;
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */