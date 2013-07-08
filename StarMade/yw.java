/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   3:    */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*   4:    */import org.schema.schine.network.client.ClientState;
/*   5:    */
/*   8:    */public abstract class yw
/*   9:    */  extends yL
/*  10:    */  implements ys
/*  11:    */{
/*  12:    */  private yE jdField_a_of_type_YE;
/*  13:    */  private yE jdField_b_of_type_YE;
/*  14:    */  private boolean jdField_a_of_type_Boolean;
/*  15:    */  
/*  16:    */  public yw(ClientState paramClientState)
/*  17:    */  {
/*  18: 18 */    super(paramClientState);
/*  19: 19 */    this.g = true;
/*  20: 20 */    super.a(this);
/*  21: 21 */    this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  22: 22 */    this.jdField_b_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  23:    */  }
/*  24:    */  
/*  26:    */  public final void a(yz paramyz, xp paramxp)
/*  27:    */  {
/*  28: 28 */    if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0)) {
/*  29: 29 */      if (b())
/*  30:    */        try {
/*  31: 31 */          f(); return;
/*  32: 32 */        } catch (StateParameterNotFoundException localStateParameterNotFoundException1) { 
/*  33:    */          
/*  34: 34 */            localStateParameterNotFoundException1.printStackTrace();return;
/*  35:    */        }
/*  36:    */      try {
/*  37: 37 */        e(); return;
/*  38: 38 */      } catch (StateParameterNotFoundException localStateParameterNotFoundException2) { 
/*  39:    */        
/*  40: 40 */          localStateParameterNotFoundException2;
/*  41:    */      }
/*  42:    */    }
/*  43:    */  }
/*  44:    */  
/*  48:    */  public final void a(ys paramys)
/*  49:    */  {
/*  50: 48 */    if (!jdField_b_of_type_Boolean) { throw new AssertionError("CANNOT SET CALLBACK BESIDES OWN");
/*  51:    */    }
/*  52:    */  }
/*  53:    */  
/*  55:    */  protected abstract void e();
/*  56:    */  
/*  58:    */  protected abstract void f();
/*  59:    */  
/*  61:    */  protected abstract boolean b();
/*  62:    */  
/*  64:    */  public final void a() {}
/*  65:    */  
/*  67:    */  protected final void d() {}
/*  68:    */  
/*  69:    */  public final void b()
/*  70:    */  {
/*  71: 69 */    if (!this.jdField_a_of_type_Boolean) {
/*  72: 70 */      c();
/*  73:    */    }
/*  74: 72 */    GlUtil.d();
/*  75: 73 */    r();
/*  76: 74 */    l();
/*  77: 75 */    this.jdField_a_of_type_YE.b();
/*  78: 76 */    if (b()) {
/*  79: 77 */      this.jdField_b_of_type_YE.b();
/*  80:    */    }
/*  81: 79 */    GlUtil.c();
/*  82:    */  }
/*  83:    */  
/*  84:    */  public final float a()
/*  85:    */  {
/*  86: 84 */    return this.jdField_a_of_type_YE.a();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public final float b()
/*  90:    */  {
/*  91: 89 */    return this.jdField_a_of_type_YE.b();
/*  92:    */  }
/*  93:    */  
/*  95:    */  public final boolean a()
/*  96:    */  {
/*  97: 95 */    return false;
/*  98:    */  }
/*  99:    */  
/* 107:    */  public final void c()
/* 108:    */  {
/* 109:107 */    this.jdField_a_of_type_YE.a_(18);
/* 110:108 */    this.jdField_b_of_type_YE.a_(19);
/* 111:    */    
/* 112:110 */    this.jdField_a_of_type_YE.a().y += 2.0F;
/* 113:111 */    this.jdField_b_of_type_YE.a().set(this.jdField_a_of_type_YE.a());
/* 114:112 */    this.jdField_a_of_type_Boolean = true;
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */