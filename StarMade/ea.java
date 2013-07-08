/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  17:    */public final class ea
/*  18:    */  extends dO
/*  19:    */  implements dP
/*  20:    */{
/*  21:    */  private ConvexHullShape a;
/*  22:    */  
/*  23:    */  public ea()
/*  24:    */  {
/*  25:    */    ObjectArrayList localObjectArrayList;
/*  26: 26 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  31: 31 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
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
/*  48: 48 */        if (s2 == 0)
/*  49:    */        {
/*  50: 50 */          bool = true;
/*  51: 51 */        } else if (s2 == 1) {
/*  52: 52 */          i = 2;
/*  53: 53 */          s2 = 3;
/*  54:    */        }
/*  55: 55 */        else if (s2 == 2) {
/*  56: 56 */          i = 2;
/*  57: 57 */          s2 = 2;
/*  58:    */        }
/*  59: 59 */        else if (s2 == 3) {
/*  60: 60 */          bool = true; } break;
/*  61:    */      
/*  64:    */      case 1: 
/*  65: 65 */        if (s2 == 3)
/*  66: 66 */          s2 = 2; break;
/*  67:    */      
/*  70:    */      case 0: 
/*  71: 71 */        if (s2 == 0)
/*  72: 72 */          s2 = 1; break;
/*  73:    */      
/*  76:    */      case 4: 
/*  77: 77 */        s2 = 0;
/*  78:    */      }
/*  79:    */      
/*  80:    */      
/*  81: 81 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  85:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  86: 86 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  87: 87 */      short s2 = s1;
/*  88: 88 */      int i = paramInt1;
/*  89: 89 */      boolean bool = false;
/*  90: 90 */      switch (paramInt1)
/*  91:    */      {
/*  95:    */      case 3: 
/*  96: 96 */        if (s2 == 0)
/*  97:    */        {
/*  98: 98 */          bool = true;
/*  99: 99 */        } else if (s2 == 1) {
/* 100:100 */          i = 2;
/* 101:101 */          s2 = 3;
/* 102:    */        }
/* 103:103 */        else if (s2 == 2) {
/* 104:104 */          i = 2;
/* 105:105 */          s2 = 2;
/* 106:    */        }
/* 107:107 */        else if (s2 == 3) {
/* 108:108 */          bool = true; } break;
/* 109:    */      
/* 112:    */      case 1: 
/* 113:113 */        if (s2 == 3)
/* 114:114 */          s2 = 2; break;
/* 115:    */      
/* 118:    */      case 0: 
/* 119:119 */        if (s2 == 0)
/* 120:120 */          s2 = 1; break;
/* 121:    */      
/* 124:    */      case 4: 
/* 125:125 */        s2 = 0;
/* 126:    */      }
/* 127:    */      
/* 128:    */      
/* 129:129 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 133:    */  protected final ConvexShape a()
/* 134:    */  {
/* 135:135 */    return this.a;
/* 136:    */  }
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ea
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */