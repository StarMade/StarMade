/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   4:    */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*   5:    */import org.schema.schine.network.client.ClientState;
/*   6:    */
/*  19:    */public final class ft
/*  20:    */  extends yL
/*  21:    */  implements ys
/*  22:    */{
/*  23:    */  private yP jdField_a_of_type_YP;
/*  24:    */  private yE jdField_a_of_type_YE;
/*  25:    */  private yE b;
/*  26:    */  private ko jdField_a_of_type_Ko;
/*  27:    */  private boolean jdField_a_of_type_Boolean;
/*  28:    */  
/*  29:    */  public ft(ClientState paramClientState, ko paramko)
/*  30:    */  {
/*  31: 31 */    super(paramClientState);
/*  32: 32 */    this.g = true;
/*  33: 33 */    a(this);
/*  34: 34 */    this.jdField_a_of_type_Ko = paramko;
/*  35: 35 */    this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  36: 36 */    this.b = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  37: 37 */    this.jdField_a_of_type_YP = new yP(140, 30, d.d(), a());
/*  38:    */  }
/*  39:    */  
/*  42:    */  public final void a(yz paramyz, xp paramxp)
/*  43:    */  {
/*  44: 44 */    paramyz = null; if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0)) {
/*  45:    */      try
/*  46:    */      {
/*  47: 47 */        this.jdField_a_of_type_Ko.a(); return;
/*  48:    */      } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/*  49: 49 */        (paramyz = 
/*  50:    */        
/*  51: 51 */          localStateParameterNotFoundException).printStackTrace();xm.a(paramyz);
/*  52:    */      }
/*  53:    */    }
/*  54:    */  }
/*  55:    */  
/*  59:    */  public final void a() {}
/*  60:    */  
/*  63:    */  protected final void d() {}
/*  64:    */  
/*  67:    */  public final void b()
/*  68:    */  {
/*  69: 68 */    if (!this.jdField_a_of_type_Boolean) {
/*  70: 69 */      c();
/*  71:    */    }
/*  72: 71 */    GlUtil.d();
/*  73: 72 */    r();
/*  74: 73 */    this.jdField_a_of_type_YP.b();
/*  75: 74 */    this.jdField_a_of_type_YE.b();
/*  76: 75 */    this.b.b();
/*  77: 76 */    GlUtil.c();
/*  78:    */  }
/*  79:    */  
/*  80:    */  public final float a()
/*  81:    */  {
/*  82: 81 */    return this.jdField_a_of_type_YP.a();
/*  83:    */  }
/*  84:    */  
/*  85:    */  public final float b()
/*  86:    */  {
/*  87: 86 */    return this.jdField_a_of_type_YP.b() + this.jdField_a_of_type_YE.b() + this.b.b();
/*  88:    */  }
/*  89:    */  
/*  91:    */  public final boolean a()
/*  92:    */  {
/*  93: 92 */    return false;
/*  94:    */  }
/*  95:    */  
/* 104:    */  public final void c()
/* 105:    */  {
/* 106:105 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 107:106 */    this.jdField_a_of_type_YP.b.add(this.jdField_a_of_type_Ko.a().toString());
/* 108:107 */    this.jdField_a_of_type_YP.a(0.0F, 5.0F, 0.0F);
/* 109:108 */    this.jdField_a_of_type_YP.c();
/* 110:    */    
/* 111:110 */    this.jdField_a_of_type_YE.g = true;
/* 112:111 */    this.b.g = true;
/* 113:    */    
/* 115:114 */    this.jdField_a_of_type_YE.a(new fu(this));
/* 116:    */    
/* 145:144 */    this.b.a(new fv(this));
/* 146:    */    
/* 168:167 */    this.jdField_a_of_type_YE.a_(21);
/* 169:168 */    this.b.a_(20);
/* 170:169 */    this.jdField_a_of_type_YP.a().x = this.jdField_a_of_type_YE.b();
/* 171:170 */    this.b.a().x = (this.jdField_a_of_type_YE.b() + this.jdField_a_of_type_YP.b());
/* 172:    */    
/* 173:172 */    this.jdField_a_of_type_Boolean = true;
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ft
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */