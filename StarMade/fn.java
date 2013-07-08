/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*   3:    */import org.schema.game.common.controller.elements.ShipManagerContainer;
/*   4:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*   5:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   6:    */
/*  18:    */public final class fn
/*  19:    */  extends yz
/*  20:    */{
/*  21:    */  private yE jdField_a_of_type_YE;
/*  22:    */  private yE b;
/*  23:    */  private yE c;
/*  24:    */  private yE d;
/*  25:    */  private zJ jdField_a_of_type_ZJ;
/*  26: 26 */  private float jdField_a_of_type_Float = 32.0F;
/*  27:    */  
/*  28:    */  private boolean jdField_a_of_type_Boolean;
/*  29:    */  private yE e;
/*  30:    */  private yE f;
/*  31:    */  
/*  32:    */  public fn(ct paramct)
/*  33:    */  {
/*  34: 34 */    super(paramct);
/*  35:    */    
/*  36: 36 */    this.jdField_a_of_type_ZJ = new zJ();
/*  37:    */    
/*  38: 38 */    paramct = "powerbar-1x4-gui-";
/*  39:    */    
/*  41: 41 */    this.b = new yE(xe.a().a(paramct), a());
/*  42: 42 */    this.b.a_(0);
/*  43: 43 */    this.b.a(66.0F, -11.0F, 0.0F);
/*  44:    */    
/*  45: 45 */    this.jdField_a_of_type_YE = new yE(xe.a().a(paramct), a());
/*  46: 46 */    this.jdField_a_of_type_YE.a_(2);
/*  47: 47 */    this.jdField_a_of_type_YE.a(66.0F, -11.0F, 0.0F);
/*  48:    */    
/*  49: 49 */    this.e = new yE(xe.a().a(paramct), a());
/*  50: 50 */    this.e.a_(2);
/*  51: 51 */    this.e.a(66.0F, -11.0F, 0.0F);
/*  52:    */    
/*  53: 53 */    this.f = new yE(xe.a().a(paramct), a());
/*  54: 54 */    this.f.a_(2);
/*  55: 55 */    this.f.a(66.0F, -11.0F, 0.0F);
/*  56:    */    
/*  57: 57 */    this.d = new yE(xe.a().a(paramct), a());
/*  58: 58 */    this.d.a_(1);
/*  59: 59 */    this.d.a(549.0F, -11.0F, 0.0F);
/*  60:    */    
/*  61: 61 */    this.c = new yE(xe.a().a(paramct), a());
/*  62: 62 */    this.c.a_(3);
/*  63: 63 */    this.c.a(549.0F, -11.0F, 0.0F);
/*  64:    */    
/*  65: 65 */    if (xe.a().a(paramct).a() == null) {
/*  66: 66 */      xe.a().a(paramct).c(new javax.vecmath.Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  71:    */  public final void a() {}
/*  72:    */  
/*  74:    */  public final void b()
/*  75:    */  {
/*  76: 76 */    GlUtil.d();
/*  77: 77 */    r();
/*  78:    */    
/*  79: 79 */    Object localObject = (ct)a();
/*  80: 80 */    this.jdField_a_of_type_YE.a().a().w = 0.0F;
/*  81: 81 */    this.c.a().a().w = 0.0F;
/*  82: 82 */    this.jdField_a_of_type_YE.b();
/*  83:    */    
/*  84: 84 */    this.b.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/*  85:    */    
/*  86: 86 */    float f1 = 1.0F - ((ct)localObject).a().a() / 300.0F;
/*  87:    */    
/*  91: 91 */    org.lwjgl.util.vector.Vector4f localVector4f = new org.lwjgl.util.vector.Vector4f(this.b.a().x + f1 * 402.0F, this.b.a().x + 402.0F, this.b.a().y, this.b.a().y + this.b.a());
/*  92:    */    
/*  99: 99 */    this.b.a(localVector4f);
/* 100:    */    
/* 106:106 */    if (((ct)localObject).a() != null) {
/* 107:107 */      kd localkd = ((ct)localObject).a();
/* 108:    */      
/* 109:109 */      this.e.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/* 110:110 */      float f3 = (float)(1.0D - localkd.a().getShieldManager().getShields() / localkd.a().getShieldManager().getShieldCapabilityHP());
/* 111:111 */      localObject = new org.lwjgl.util.vector.Vector4f(this.e.a().x + f3 * 402.0F, this.e.a().x + 402.0F, this.e.a().y, this.e.a().y + this.e.a());
/* 112:    */      
/* 117:117 */      this.e.a((org.lwjgl.util.vector.Vector4f)localObject);
/* 118:    */      
/* 120:120 */      this.c.a().a().w = 0.0F;
/* 121:121 */      if (this.jdField_a_of_type_Boolean != localkd.a().getPowerAddOn().isInRecovery()) {
/* 122:122 */        this.jdField_a_of_type_Boolean = localkd.a().getPowerAddOn().isInRecovery();
/* 123:    */      }
/* 124:    */      
/* 125:125 */      if ((this.jdField_a_of_type_Boolean) || (localkd.a().getPowerAddOn().getPower() <= 0.0D)) {
/* 126:126 */        this.c.a().a().x = 1.0F;
/* 127:127 */        this.c.a().a().y = 0.0F;
/* 128:128 */        this.c.a().a().z = 0.0F;
/* 129:129 */        this.c.a().a().w = (this.jdField_a_of_type_ZJ.a() * 0.3F);
/* 130:    */      }
/* 131:131 */      this.c.b();
/* 132:    */      
/* 133:133 */      this.d.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/* 134:    */      
/* 135:135 */      f3 = (float)localkd.a().getPowerAddOn().getMaxPower();float f2 = (float)localkd.a().getPowerAddOn().getPower() * 0.785F;localObject = this.d;f2 /= f3;((yz)localObject).a.set(((yz)localObject).a().x, ((yz)localObject).a().x + f2 * ((yz)localObject).b(), ((yz)localObject).a().y, ((yz)localObject).a().y + 1.0F * ((yz)localObject).a());((yz)localObject).a(((yz)localObject).a);
/* 136:    */    }
/* 137:    */    
/* 140:140 */    GlUtil.c();
/* 141:    */  }
/* 142:    */  
/* 143:    */  public final float a() {
/* 144:144 */    return this.jdField_a_of_type_YE.a();
/* 145:    */  }
/* 146:    */  
/* 151:    */  public final float b()
/* 152:    */  {
/* 153:153 */    return this.jdField_a_of_type_YE.b() * 2.0F + this.jdField_a_of_type_Float;
/* 154:    */  }
/* 155:    */  
/* 169:    */  public final void c()
/* 170:    */  {
/* 171:171 */    this.jdField_a_of_type_YE.c();
/* 172:172 */    this.b.c();
/* 173:173 */    this.c.c();
/* 174:174 */    this.d.c();
/* 175:    */  }
/* 176:    */  
/* 177:    */  public final void a(xq paramxq)
/* 178:    */  {
/* 179:179 */    this.jdField_a_of_type_ZJ.a(paramxq);
/* 180:    */  }
/* 181:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */