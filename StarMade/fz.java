/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.List;
/*   4:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   5:    */import org.schema.schine.network.client.ClientState;
/*   6:    */
/*  17:    */public final class fz
/*  18:    */  extends yL
/*  19:    */  implements ys
/*  20:    */{
/*  21:    */  private yP jdField_a_of_type_YP;
/*  22:    */  private yE jdField_a_of_type_YE;
/*  23:    */  private yE b;
/*  24:    */  private aj jdField_a_of_type_Aj;
/*  25:    */  private boolean jdField_a_of_type_Boolean;
/*  26:    */  
/*  27:    */  public fz(ClientState paramClientState, aj paramaj)
/*  28:    */  {
/*  29: 29 */    super(paramClientState);
/*  30: 30 */    this.g = true;
/*  31: 31 */    a(this);
/*  32: 32 */    this.jdField_a_of_type_Aj = paramaj;
/*  33: 33 */    this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  34: 34 */    this.b = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  35: 35 */    this.jdField_a_of_type_YP = new yP(30, 30, d.e(), a());
/*  36:    */  }
/*  37:    */  
/*  39:    */  public final void a(yz paramyz, xp paramxp)
/*  40:    */  {
/*  41: 41 */    if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*  42:    */    {
/*  43: 43 */      System.err.println("XCALLBACk");
/*  44:    */    }
/*  45:    */  }
/*  46:    */  
/*  50:    */  public final void a() {}
/*  51:    */  
/*  55:    */  protected final void d() {}
/*  56:    */  
/*  59:    */  public final void b()
/*  60:    */  {
/*  61: 61 */    if (!this.jdField_a_of_type_Boolean) {
/*  62: 62 */      c();
/*  63:    */    }
/*  64: 64 */    GlUtil.d();
/*  65: 65 */    r();
/*  66: 66 */    this.jdField_a_of_type_YP.b();
/*  67: 67 */    this.jdField_a_of_type_YE.b();
/*  68: 68 */    this.b.b();
/*  69: 69 */    GlUtil.c();
/*  70:    */  }
/*  71:    */  
/*  72:    */  public final float a()
/*  73:    */  {
/*  74: 74 */    return this.jdField_a_of_type_YP.a();
/*  75:    */  }
/*  76:    */  
/*  77:    */  public final float b()
/*  78:    */  {
/*  79: 79 */    return this.jdField_a_of_type_YP.b() + this.jdField_a_of_type_YE.b() + this.b.b();
/*  80:    */  }
/*  81:    */  
/*  83:    */  public final boolean a()
/*  84:    */  {
/*  85: 85 */    return false;
/*  86:    */  }
/*  87:    */  
/*  96:    */  public final void c()
/*  97:    */  {
/*  98: 98 */    this.jdField_a_of_type_YP.b = new ArrayList();
/*  99: 99 */    this.jdField_a_of_type_YP.b.add(this.jdField_a_of_type_Aj.toString());
/* 100:100 */    this.jdField_a_of_type_YP.c();
/* 101:    */    
/* 102:102 */    this.jdField_a_of_type_YE.g = true;
/* 103:103 */    this.b.g = true;
/* 104:    */    
/* 106:106 */    this.jdField_a_of_type_YE.a(new fA(this));
/* 107:    */    
/* 129:129 */    this.b.a(new fB(this));
/* 130:    */    
/* 146:146 */    this.jdField_a_of_type_YE.a_(21);
/* 147:147 */    this.b.a_(20);
/* 148:148 */    this.jdField_a_of_type_YP.a().x = this.jdField_a_of_type_YE.b();
/* 149:149 */    this.b.a().x = (this.jdField_a_of_type_YE.b() + this.jdField_a_of_type_YP.b());
/* 150:    */    
/* 151:151 */    this.jdField_a_of_type_Boolean = true;
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */