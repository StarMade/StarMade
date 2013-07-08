/*   1:    */import java.util.Random;
/*   2:    */import org.schema.game.common.data.world.SegmentData;
/*   3:    */
/*  10:    */public final class vb
/*  11:    */  extends uX
/*  12:    */{
/*  13:    */  public vb()
/*  14:    */  {
/*  15: 15 */    this((byte)0);
/*  16:    */  }
/*  17:    */  
/*  21:    */  private vb(byte paramByte) {}
/*  22:    */  
/*  26:    */  public final boolean a(SegmentData paramSegmentData, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
/*  27:    */  {
/*  28: 28 */    int i = paramRandom.nextInt(3) + 4;
/*  29: 29 */    int j = 1;
/*  30:    */    
/*  31: 31 */    if ((paramInt2 <= 0) || (paramInt2 + i + 1 > 256))
/*  32:    */    {
/*  33: 33 */      return false; }
/*  34:    */    int m;
/*  35:    */    int n;
/*  36: 36 */    int i2; for (int k = paramInt2; k <= paramInt2 + 1 + i; k++)
/*  37:    */    {
/*  38: 38 */      m = 1;
/*  39:    */      
/*  40: 40 */      if (k == paramInt2)
/*  41:    */      {
/*  42: 42 */        m = 0;
/*  43:    */      }
/*  44:    */      
/*  45: 45 */      if (k >= paramInt2 + 1 + i - 2)
/*  46:    */      {
/*  47: 47 */        m = 2;
/*  48:    */      }
/*  49:    */      
/*  50: 50 */      for (n = paramInt1 - m; (n <= paramInt1 + m) && (j != 0); n++)
/*  51:    */      {
/*  52: 52 */        for (i1 = paramInt3 - m; (i1 <= paramInt3 + m) && (j != 0); i1++)
/*  53:    */        {
/*  54: 54 */          if ((k >= 0) && (k < 64))
/*  55:    */          {
/*  58: 58 */            if (((i2 = paramSegmentData.getType(a(n), a(k), a(i1))) != 0) && (i2 != 85) && (i2 != 82) && (i2 != 87) && (i2 != 84))
/*  59:    */            {
/*  60: 60 */              j = 0;
/*  61:    */            }
/*  62:    */            
/*  63:    */          }
/*  64:    */          else {
/*  65: 65 */            j = 0;
/*  66:    */          }
/*  67:    */        }
/*  68:    */      }
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    if (j == 0)
/*  72:    */    {
/*  73: 73 */      return false;
/*  74:    */    }
/*  75:    */    
/*  78: 78 */    if ((((k = paramSegmentData.getType(a(paramInt1), a(paramInt2 - 1), a(paramInt3))) != 82) && (k != 87)) || (paramInt2 >= 256 - i - 1))
/*  79:    */    {
/*  80: 80 */      return false;
/*  81:    */    }
/*  82:    */    
/*  83: 83 */    paramSegmentData.setInfoElementUnsynched(a(paramInt1), a(paramInt2 - 1), a(paramInt3), (short)87, false);
/*  84: 84 */    for (int i1 = paramInt2 - 3 + i; i1 <= paramInt2 + i; 
/*  85:    */        
/*  87: 87 */        i1++)
/*  88:    */    {
/*  89: 89 */      i2 = i1 - (paramInt2 + i);
/*  90: 90 */      j = 1 - i2 / 2;
/*  91:    */      
/*  92: 92 */      for (k = paramInt1 - j; k <= paramInt1 + j; k++)
/*  93:    */      {
/*  94: 94 */        m = k - paramInt1;
/*  95:    */        
/*  96: 96 */        for (n = paramInt3 - j; n <= paramInt3 + j; n++)
/*  97:    */        {
/*  98: 98 */          int i3 = n - paramInt3;
/*  99:    */          
/* 100:100 */          if ((Math.abs(m) != j) || (Math.abs(i3) != j) || ((paramRandom.nextInt(2) != 0) && (i2 != 0)))
/* 101:    */          {
/* 103:103 */            paramSegmentData.setInfoElementUnsynched(a(k), a(i1), a(n), (short)85, false);
/* 104:    */          }
/* 105:    */        }
/* 106:    */      }
/* 107:    */    }
/* 108:    */    
/* 109:109 */    for (i1 = 0; i1 < i;)
/* 110:    */    {
/* 114:114 */      if (((i2 = paramSegmentData.getType(a(paramInt1), a(paramInt2 + i1), a(paramInt3))) == 0) || (i2 == 85))
/* 115:    */      {
/* 116:116 */        paramSegmentData.setInfoElementUnsynched(a(paramInt1), a(paramInt2 + i1), a(paramInt3), (short)84, false);
/* 117:    */      }
/* 118:    */      
/* 123:123 */      i1++;
/* 124:    */    }
/* 125:    */    
/* 154:154 */    return true;
/* 155:    */  }
/* 156:    */  
/* 199:    */  public final int a(Random paramRandom)
/* 200:    */  {
/* 201:201 */    return 4 + paramRandom.nextInt(8);
/* 202:    */  }
/* 203:    */  
/* 204:    */  public final int b(Random paramRandom) {
/* 205:205 */    return 4 + paramRandom.nextInt(8);
/* 206:    */  }
/* 207:    */  
/* 208:    */  private static byte a(int paramInt)
/* 209:    */  {
/* 210:210 */    return (byte)(Math.abs(paramInt) % 16);
/* 211:    */  }
/* 212:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */