/*   1:    */import java.io.Serializable;
/*   2:    */
/*  27:    */public class s
/*  28:    */  implements Serializable
/*  29:    */{
/*  30:    */  static final long serialVersionUID = 8749319902347760659L;
/*  31:    */  public int a;
/*  32:    */  public int b;
/*  33:    */  public int c;
/*  34:    */  public int d;
/*  35:    */  
/*  36:    */  public s() {}
/*  37:    */  
/*  38:    */  public s(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*  39:    */  {
/*  40: 40 */    this.a = paramInt1;
/*  41: 41 */    this.b = paramInt2;
/*  42: 42 */    this.c = paramInt3;
/*  43: 43 */    this.d = paramInt4;
/*  44:    */  }
/*  45:    */  
/*  65:    */  public s(q paramq)
/*  66:    */  {
/*  67: 67 */    this(paramq.a, paramq.b, paramq.c, 0);
/*  68:    */  }
/*  69:    */  
/*  89:    */  public s(q paramq, int paramInt)
/*  90:    */  {
/*  91: 91 */    this(paramq.a, paramq.b, paramq.c, paramInt);
/*  92:    */  }
/*  93:    */  
/* 112:    */  public boolean equals(Object paramObject)
/* 113:    */  {
/* 114:    */    try
/* 115:    */    {
/* 116:116 */      paramObject = (s)paramObject;
/* 117:117 */      return (this.a == paramObject.a) && (this.b == paramObject.b) && (this.c == paramObject.c) && (this.d == paramObject.d);
/* 118:    */    }
/* 119:    */    catch (NullPointerException localNullPointerException) {
/* 120:120 */      return false; } catch (ClassCastException localClassCastException) {}
/* 121:121 */    return false;
/* 122:    */  }
/* 123:    */  
/* 133:    */  public int hashCode()
/* 134:    */  {
/* 135:135 */    long l = 31L + this.a;
/* 136:    */    
/* 137:137 */    l = 31L * l + this.b;
/* 138:138 */    l = 31L * l + this.c; long 
/* 139:139 */      tmp45_44 = (31L * l + this.d);
/* 140:140 */    return (int)(tmp45_44 ^ tmp45_44 >> 32);
/* 141:    */  }
/* 142:    */  
/* 162:    */  public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/* 163:    */  {
/* 164:164 */    this.a = paramInt1;
/* 165:165 */    this.b = paramInt2;
/* 166:166 */    this.c = paramInt3;
/* 167:167 */    this.d = paramInt4;
/* 168:    */  }
/* 169:    */  
/* 205:    */  public String toString()
/* 206:    */  {
/* 207:207 */    return "(" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ")";
/* 208:    */  }
/* 209:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     s
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */