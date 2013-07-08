/*   1:    */import org.schema.common.FastMath;
/*   2:    */
/*   3:    */public class q implements Comparable { public int a;
/*   4:    */  public int b;
/*   5:    */  public int c;
/*   6:    */  
/*   7:  7 */  static { jdField_a_of_type_Boolean = !q.class.desiredAssertionStatus(); }
/*   8:    */  
/*  11:    */  public q(float paramFloat1, float paramFloat2, float paramFloat3)
/*  12:    */  {
/*  13: 13 */    this.jdField_a_of_type_Int = ((int)paramFloat1);this.b = ((int)paramFloat2);this.c = ((int)paramFloat3);
/*  14:    */  }
/*  15:    */  
/*  16: 16 */  public q(int paramInt1, int paramInt2, int paramInt3) { this.jdField_a_of_type_Int = paramInt1;this.b = paramInt2;this.c = paramInt3;
/*  17:    */  }
/*  18:    */  
/*  19: 19 */  public q(javax.vecmath.Vector3f paramVector3f) { this.jdField_a_of_type_Int = ((int)paramVector3f.x);this.b = ((int)paramVector3f.y);this.c = ((int)paramVector3f.z);
/*  20:    */  }
/*  21:    */  
/*  22: 22 */  public q(q paramq) { this.jdField_a_of_type_Int = paramq.jdField_a_of_type_Int;
/*  23: 23 */    this.b = paramq.b;
/*  24: 24 */    this.c = paramq.c;
/*  25:    */  }
/*  26:    */  
/*  27: 27 */  public final void a(int paramInt1, int paramInt2, int paramInt3) { this.jdField_a_of_type_Int += paramInt1;
/*  28: 28 */    this.b += paramInt2;
/*  29: 29 */    this.c += paramInt3;
/*  30:    */  }
/*  31:    */  
/*  32:    */  public final void a(q paramq) {
/*  33: 33 */    this.jdField_a_of_type_Int += paramq.jdField_a_of_type_Int;
/*  34: 34 */    this.b += paramq.b;
/*  35: 35 */    this.c += paramq.c;
/*  36:    */  }
/*  37:    */  
/*  42:    */  public final void a()
/*  43:    */  {
/*  44: 44 */    this.jdField_a_of_type_Int /= 2;
/*  45: 45 */    this.b /= 2;
/*  46: 46 */    this.c /= 2;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public final boolean a(int paramInt1, int paramInt2, int paramInt3) {
/*  50: 50 */    return (this.jdField_a_of_type_Int == paramInt1) && (this.b == paramInt2) && (this.c == paramInt3);
/*  51:    */  }
/*  52:    */  
/*  59:    */  public boolean equals(Object paramObject)
/*  60:    */  {
/*  61:    */    try
/*  62:    */    {
/*  63: 63 */      paramObject = (q)paramObject;
/*  64: 64 */      return (this.jdField_a_of_type_Int == paramObject.jdField_a_of_type_Int) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*  65:    */    } catch (NullPointerException localNullPointerException) {
/*  66: 66 */      return false; } catch (ClassCastException localClassCastException) {}
/*  67: 67 */    return false;
/*  68:    */  }
/*  69:    */  
/*  81:    */  public int hashCode()
/*  82:    */  {
/*  83: 83 */    return ((this.jdField_a_of_type_Int ^ this.jdField_a_of_type_Int >>> 16) * 15 + (this.b ^ this.b >>> 16)) * 15 + (this.c ^ this.c >>> 16);
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public final float a() { return FastMath.l(this.jdField_a_of_type_Int * this.jdField_a_of_type_Int + this.b * this.b + this.c * this.c); }
/*  87:    */  
/*  88:    */  public final void a(int paramInt) {
/*  89: 89 */    this.jdField_a_of_type_Int *= paramInt;
/*  90: 90 */    this.b *= paramInt;
/*  91: 91 */    this.c *= paramInt;
/*  92:    */  }
/*  93:    */  
/* 102:    */  public final void b(int paramInt1, int paramInt2, int paramInt3)
/* 103:    */  {
/* 104:104 */    this.jdField_a_of_type_Int = paramInt1;this.b = paramInt2;this.c = paramInt3;
/* 105:    */  }
/* 106:    */  
/* 107:107 */  public final void b(q paramq) { b(paramq.jdField_a_of_type_Int, paramq.b, paramq.c); }
/* 108:    */  
/* 109:    */  public final void c(int paramInt1, int paramInt2, int paramInt3) {
/* 110:110 */    this.jdField_a_of_type_Int -= paramInt1;
/* 111:111 */    this.b -= paramInt2;
/* 112:112 */    this.c -= paramInt3;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public final void c(q paramq) {
/* 116:116 */    this.jdField_a_of_type_Int -= paramq.jdField_a_of_type_Int;
/* 117:117 */    this.b -= paramq.b;
/* 118:118 */    this.c -= paramq.c;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public final void a(q paramq1, q paramq2) {
/* 122:122 */    paramq1.jdField_a_of_type_Int -= paramq2.jdField_a_of_type_Int;
/* 123:123 */    paramq1.b -= paramq2.b;
/* 124:124 */    paramq1.c -= paramq2.c;
/* 125:    */  }
/* 126:    */  
/* 127:127 */  public final void b(q paramq1, q paramq2) { paramq1.jdField_a_of_type_Int += paramq2.jdField_a_of_type_Int;
/* 128:128 */    paramq1.b += paramq2.b;
/* 129:129 */    paramq1.c += paramq2.c;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public String toString() {
/* 133:133 */    return "(" + this.jdField_a_of_type_Int + ", " + this.b + ", " + this.c + ")";
/* 134:    */  }
/* 135:    */  
/* 137:    */  public final int a(int paramInt)
/* 138:    */  {
/* 139:139 */    switch (paramInt) {
/* 140:140 */    case 0:  return this.jdField_a_of_type_Int;
/* 141:141 */    case 1:  return this.b;
/* 142:142 */    case 2:  return this.c; }
/* 143:143 */    if (!jdField_a_of_type_Boolean) { throw new AssertionError(paramInt);
/* 144:    */    }
/* 145:145 */    throw new NullPointerException(paramInt + " coord");
/* 146:    */  }
/* 147:    */  
/* 156:    */  public static q a(String paramString)
/* 157:    */  {
/* 158:158 */    if ((paramString = paramString.split(",")).length != 3) {
/* 159:159 */      throw new NumberFormatException("Wrong number of arguments");
/* 160:    */    }
/* 161:161 */    return new q(Integer.parseInt(paramString[0].trim()), Integer.parseInt(paramString[1].trim()), Integer.parseInt(paramString[2].trim()));
/* 162:    */  }
/* 163:    */  
/* 164:164 */  public final void b() { this.jdField_a_of_type_Int = (-this.jdField_a_of_type_Int);
/* 165:165 */    this.b = (-this.b);
/* 166:166 */    this.c = (-this.c);
/* 167:    */  }
/* 168:    */  
/* 169:169 */  public final void c() { this.jdField_a_of_type_Int = Math.abs(this.jdField_a_of_type_Int);
/* 170:170 */    this.b = Math.abs(this.b);
/* 171:171 */    this.c = Math.abs(this.c);
/* 172:    */  }
/* 173:    */  
/* 174:    */  public q() {}
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     q
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */