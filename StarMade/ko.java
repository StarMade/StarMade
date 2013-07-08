/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.Arrays;
/*   3:    */
/*  18:    */public final class ko
/*  19:    */  implements Ak
/*  20:    */{
/*  21:    */  private final String jdField_a_of_type_JavaLangString;
/*  22:    */  private xB jdField_a_of_type_XB;
/*  23:    */  private Object jdField_a_of_type_JavaLangObject;
/*  24:    */  private final kp jdField_a_of_type_Kp;
/*  25:    */  private final kq jdField_a_of_type_Kq;
/*  26:    */  
/*  27:    */  static
/*  28:    */  {
/*  29: 29 */    new ArrayList();
/*  30:    */  }
/*  31:    */  
/*  41:    */  public ko(kq paramkq, Object paramObject, xB paramxB, kp paramkp)
/*  42:    */  {
/*  43: 43 */    this.jdField_a_of_type_JavaLangString = paramkq.jdField_a_of_type_JavaLangString;
/*  44: 44 */    this.jdField_a_of_type_Kq = paramkq;
/*  45: 45 */    this.jdField_a_of_type_XB = paramxB;
/*  46: 46 */    this.jdField_a_of_type_Kp = paramkp;
/*  47: 47 */    a(paramObject, false);
/*  48:    */  }
/*  49:    */  
/*  85:    */  public final Object a()
/*  86:    */  {
/*  87: 87 */    return this.jdField_a_of_type_JavaLangObject;
/*  88:    */  }
/*  89:    */  
/*  91:    */  public final String a()
/*  92:    */  {
/*  93: 93 */    return this.jdField_a_of_type_JavaLangString;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public final String b() {
/*  97: 97 */    return this.jdField_a_of_type_Kq.name();
/*  98:    */  }
/*  99:    */  
/* 106:    */  public final kq a()
/* 107:    */  {
/* 108:108 */    return this.jdField_a_of_type_Kq;
/* 109:    */  }
/* 110:    */  
/* 112:    */  public final String getUniqueIdentifier()
/* 113:    */  {
/* 114:114 */    return null;
/* 115:    */  }
/* 116:    */  
/* 123:    */  public final boolean a()
/* 124:    */  {
/* 125:125 */    return ((this.jdField_a_of_type_JavaLangObject instanceof Boolean)) && (((Boolean)this.jdField_a_of_type_JavaLangObject).booleanValue());
/* 126:    */  }
/* 127:    */  
/* 131:    */  public final boolean isVolatile()
/* 132:    */  {
/* 133:133 */    return false;
/* 134:    */  }
/* 135:    */  
/* 140:    */  public final void a(Object paramObject, boolean paramBoolean)
/* 141:    */  {
/* 142:142 */    int i = !paramObject.equals(this.jdField_a_of_type_JavaLangObject) ? 1 : 0;
/* 143:143 */    this.jdField_a_of_type_JavaLangObject = paramObject;
/* 144:    */    
/* 145:145 */    if (i != 0) {
/* 146:146 */      this.jdField_a_of_type_Kp.a(this, paramBoolean);
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 151:    */  public final void a()
/* 152:    */  {
/* 153:153 */    a(this.jdField_a_of_type_XB.a(), true);
/* 154:    */  }
/* 155:    */  
/* 157:    */  public final void b()
/* 158:    */  {
/* 159:159 */    Object localObject = this.jdField_a_of_type_XB.a();
/* 160:    */    
/* 161:161 */    a(localObject, true);
/* 162:    */  }
/* 163:    */  
/* 170:    */  public final void a(String paramString, boolean paramBoolean)
/* 171:    */  {
/* 172:172 */    a(this.jdField_a_of_type_XB.a(paramString), paramBoolean);
/* 173:    */  }
/* 174:    */  
/* 176:    */  public final void c()
/* 177:    */  {
/* 178:178 */    a(this.jdField_a_of_type_XB.b(), true);
/* 179:    */  }
/* 180:    */  
/* 183:    */  public final String toString()
/* 184:    */  {
/* 185:185 */    return this.jdField_a_of_type_JavaLangString + " (" + this.jdField_a_of_type_XB.a() + ") " + this.jdField_a_of_type_JavaLangObject + " " + Arrays.toString(this.jdField_a_of_type_XB.a);
/* 186:    */  }
/* 187:    */  
/* 195:    */  public final Ah toTagStructure()
/* 196:    */  {
/* 197:197 */    Ah localAh1 = new Ah(Aj.i, "type", this.jdField_a_of_type_Kq.name());
/* 198:198 */    Ah localAh2 = new Ah(Aj.i, "state", this.jdField_a_of_type_JavaLangObject.toString());
/* 199:    */    
/* 200:200 */    return new Ah(Aj.n, "AIElement", new Ah[] { localAh1, localAh2, new Ah(Aj.a, "fin", null) });
/* 201:    */  }
/* 202:    */  
/* 203:    */  public final void fromTagStructure(Ah paramAh) {}
/* 204:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ko
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */