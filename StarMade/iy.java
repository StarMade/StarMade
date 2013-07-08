/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */import org.newdawn.slick.UnicodeFont;
/*   6:    */import org.schema.schine.network.client.ClientState;
/*   7:    */
/*  21:    */public final class iy
/*  22:    */  extends yr
/*  23:    */  implements ys
/*  24:    */{
/*  25:    */  private yP jdField_a_of_type_YP;
/*  26:    */  private yP b;
/*  27:    */  private yP c;
/*  28:    */  private yP d;
/*  29:    */  private yP e;
/*  30:    */  private yP f;
/*  31:    */  private boolean jdField_a_of_type_Boolean;
/*  32:    */  
/*  33:    */  public iy(ClientState paramClientState)
/*  34:    */  {
/*  35: 35 */    super(paramClientState);
/*  36:    */  }
/*  37:    */  
/*  41:    */  public final void a(yz paramyz, xp paramxp)
/*  42:    */  {
/*  43: 43 */    if ((a() != null) && 
/*  44: 44 */      (paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*  45:    */    {
/*  46: 46 */      if (paramyz == this.b) {
/*  47: 47 */        a().c();return; }
/*  48: 48 */      if (paramyz == this.c) {
/*  49: 49 */        a().e();return; }
/*  50: 50 */      if (paramyz == this.d) {
/*  51: 51 */        a().f();return; }
/*  52: 52 */      if (paramyz == this.e) {
/*  53: 53 */        a().b();return; }
/*  54: 54 */      if (paramyz == this.f) {
/*  55: 55 */        a().d();
/*  56:    */      }
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  63:    */  public final void a() {}
/*  64:    */  
/*  67:    */  public final void b()
/*  68:    */  {
/*  69: 69 */    if (!this.jdField_a_of_type_Boolean) {
/*  70: 70 */      c();
/*  71:    */    }
/*  72: 72 */    super.b();
/*  73:    */  }
/*  74:    */  
/*  75:    */  public final float a()
/*  76:    */  {
/*  77: 77 */    return 180.0F;
/*  78:    */  }
/*  79:    */  
/*  81:    */  private bJ a()
/*  82:    */  {
/*  83: 83 */    return ((ct)a()).a().a();
/*  84:    */  }
/*  85:    */  
/*  86:    */  public final float b()
/*  87:    */  {
/*  88: 88 */    return 260.0F;
/*  89:    */  }
/*  90:    */  
/*  92:    */  public final boolean a()
/*  93:    */  {
/*  94: 94 */    return false;
/*  95:    */  }
/*  96:    */  
/* 105:    */  public final void c()
/* 106:    */  {
/* 107:107 */    Object localObject = d.n();
/* 108:    */    
/* 109:109 */    this.jdField_a_of_type_YP = new yP(120, 30, d.o(), a());
/* 110:    */    
/* 113:113 */    this.b = new yP(120, 30, (UnicodeFont)localObject, a());
/* 114:114 */    this.c = new yP(120, 30, (UnicodeFont)localObject, a());
/* 115:115 */    this.d = new yP(120, 30, (UnicodeFont)localObject, a());
/* 116:116 */    this.e = new yP(120, 30, (UnicodeFont)localObject, a());
/* 117:117 */    this.f = new yP(120, 30, (UnicodeFont)localObject, a());
/* 118:    */    
/* 121:121 */    this.b.a(this);
/* 122:122 */    this.c.a(this);
/* 123:123 */    this.d.a(this);
/* 124:124 */    this.e.a(this);
/* 125:125 */    this.f.a(this);
/* 126:    */    
/* 127:127 */    this.b.g = true;
/* 128:128 */    this.c.g = true;
/* 129:129 */    this.d.g = true;
/* 130:130 */    this.e.g = true;
/* 131:131 */    this.f.g = true;
/* 132:    */    
/* 134:134 */    this.jdField_a_of_type_YP.b = new ArrayList(1);
/* 135:135 */    this.b.b = new ArrayList(1);
/* 136:136 */    this.c.b = new ArrayList(1);
/* 137:137 */    this.d.b = new ArrayList(1);
/* 138:138 */    this.e.b = new ArrayList(1);
/* 139:139 */    this.f.b = new ArrayList(1);
/* 140:    */    
/* 141:141 */    this.jdField_a_of_type_YP.b.add("Tutorial Controls");
/* 142:142 */    this.b.b.add("Skip Part");
/* 143:143 */    this.c.b.add("Repeat Part");
/* 144:144 */    this.d.b.add("skip current step");
/* 145:145 */    this.e.b.add("end tutorial");
/* 146:146 */    this.f.b.add("reset tutorial");
/* 147:    */    
/* 149:149 */    localObject = new Vector4f(0.0F, 0.0F, 0.0F, 0.7F);
/* 150:150 */    yx localyx1 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 151:151 */    yx localyx2 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 152:152 */    yx localyx3 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 153:153 */    yx localyx4 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 154:154 */    yx localyx5 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 155:155 */    localObject = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 156:    */    
/* 158:158 */    localyx4.a().set(0.0F, 30.0F, 0.0F);
/* 159:    */    
/* 160:160 */    localyx3.a().set(0.0F, 60.0F, 0.0F);
/* 161:161 */    localyx2.a().set(120.0F, 60.0F, 0.0F);
/* 162:    */    
/* 164:164 */    ((yx)localObject).a().set(0.0F, 90.0F, 0.0F);
/* 165:165 */    localyx5.a().set(120.0F, 90.0F, 0.0F);
/* 166:    */    
/* 167:167 */    localyx1.a(this.jdField_a_of_type_YP);
/* 168:168 */    localyx2.a(this.b);
/* 169:169 */    localyx3.a(this.c);
/* 170:170 */    localyx4.a(this.d);
/* 171:171 */    localyx5.a(this.e);
/* 172:172 */    ((yx)localObject).a(this.f);
/* 173:    */    
/* 174:174 */    a(localyx1);
/* 175:175 */    a(localyx2);
/* 176:176 */    a(localyx3);
/* 177:177 */    a(localyx4);
/* 178:178 */    a(localyx5);
/* 179:179 */    a((yz)localObject);
/* 180:    */    
/* 182:182 */    this.jdField_a_of_type_Boolean = true;
/* 183:    */  }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */