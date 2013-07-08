/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.schema.schine.network.client.ClientState;
/*   3:    */
/*  10:    */public final class ff
/*  11:    */  extends yz
/*  12:    */{
/*  13:    */  private yE jdField_a_of_type_YE;
/*  14:    */  private yE b;
/*  15:    */  private yE c;
/*  16:    */  private yE d;
/*  17:    */  private boolean jdField_a_of_type_Boolean;
/*  18:    */  private yE e;
/*  19:    */  
/*  20:    */  public ff(ClientState paramClientState)
/*  21:    */  {
/*  22: 22 */    super(paramClientState);
/*  23:    */  }
/*  24:    */  
/*  26:    */  public final void a() {}
/*  27:    */  
/*  29:    */  public final void b()
/*  30:    */  {
/*  31: 31 */    if (this.jdField_a_of_type_Boolean) {
/*  32: 32 */      int i = ((ct)a()).a().jdField_a_of_type_Y.a;
/*  33: 33 */      if (a().a().b) {
/*  34: 34 */        this.jdField_a_of_type_YE.b();
/*  35:    */      }
/*  36: 36 */      if (a().a().b) {
/*  37: 37 */        if (i == 0) {
/*  38: 38 */          this.b.b();
/*  39:    */        }
/*  40: 40 */        if (i == 1) {
/*  41: 41 */          this.c.b();
/*  42:    */        }
/*  43: 43 */        if (i == 2) {
/*  44: 44 */          this.d.b();
/*  45:    */        }
/*  46:    */      }
/*  47: 47 */      return; }
/*  48: 48 */    this.e.b();
/*  49:    */  }
/*  50:    */  
/*  53:    */  public final float a()
/*  54:    */  {
/*  55: 55 */    return 0.0F;
/*  56:    */  }
/*  57:    */  
/*  60:    */  private ar a()
/*  61:    */  {
/*  62: 62 */    return ((ct)a()).a().jdField_a_of_type_Am.a.a;
/*  63:    */  }
/*  64:    */  
/*  67:    */  public final float b()
/*  68:    */  {
/*  69: 69 */    return 0.0F;
/*  70:    */  }
/*  71:    */  
/*  84:    */  public final void c()
/*  85:    */  {
/*  86: 86 */    this.e = new yE(xe.a().a("help-gui-"), a());
/*  87: 87 */    this.e.h(9);
/*  88: 88 */    this.e.c();
/*  89: 89 */    this.e.a().y -= 64.0F;
/*  90:    */    
/*  92: 92 */    this.jdField_a_of_type_YE = new yE(xe.a().a("help-gamecharacter-gui-"), a());
/*  93: 93 */    this.jdField_a_of_type_YE.h(17);
/*  94: 94 */    this.b = new yE(xe.a().a("help-shipcommon-gui-"), a());
/*  95: 95 */    this.b.h(17);
/*  96: 96 */    this.c = new yE(xe.a().a("help-shipbuild-gui-"), a());
/*  97: 97 */    this.c.h(17);
/*  98: 98 */    this.d = new yE(xe.a().a("help-shipflight-gui-"), a());
/*  99: 99 */    this.d.h(17);
/* 100:    */  }
/* 101:    */  
/* 103:    */  public final void a(boolean paramBoolean)
/* 104:    */  {
/* 105:105 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 106:    */  }
/* 107:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ff
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */