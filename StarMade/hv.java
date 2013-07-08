/*   1:    */import java.util.List;
/*   2:    */import javax.vecmath.Vector4f;
/*   3:    */import org.schema.schine.network.client.ClientState;
/*   4:    */
/*  24:    */public final class hv
/*  25:    */  extends yz
/*  26:    */{
/*  27:    */  private yN jdField_a_of_type_YN;
/*  28:    */  private yN b;
/*  29:    */  private yN c;
/*  30:    */  private yx jdField_a_of_type_Yx;
/*  31:    */  private yP jdField_a_of_type_YP;
/*  32:    */  private hK jdField_a_of_type_HK;
/*  33:    */  private gz jdField_a_of_type_Gz;
/*  34:    */  private yN d;
/*  35:    */  
/*  36:    */  public hv(ClientState paramClientState)
/*  37:    */  {
/*  38: 38 */    super(paramClientState);
/*  39:    */  }
/*  40:    */  
/*  43:    */  public final void a() {}
/*  44:    */  
/*  47:    */  public final void b()
/*  48:    */  {
/*  49: 49 */    this.jdField_a_of_type_YP.b.set(0, "Current Faction: " + ((ct)a()).a().a().a());
/*  50:    */    
/*  51: 51 */    k();
/*  52:    */  }
/*  53:    */  
/*  59:    */  public final void c()
/*  60:    */  {
/*  61: 61 */    aO localaO = ((ct)a()).a().a.a.a;
/*  62:    */    
/*  63: 63 */    this.jdField_a_of_type_Yx = new yx(a(), 540.0F, 30.0F, new Vector4f(0.0F, 0.3F, 0.0F, 0.5F));
/*  64: 64 */    this.jdField_a_of_type_Yx.c = 3.0F;
/*  65: 65 */    this.jdField_a_of_type_YP = new yP(200, 30, d.d(), a());
/*  66: 66 */    this.jdField_a_of_type_YP.a("Current Faction: ");
/*  67: 67 */    this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_YP);
/*  68:    */    
/*  69: 69 */    this.jdField_a_of_type_YN = new yN(a(), 130, 20, "Create new faction", localaO, localaO);
/*  70:    */    
/*  71: 71 */    this.jdField_a_of_type_YN.a = "CREATE_FACTION";
/*  72: 72 */    this.jdField_a_of_type_YN.a(0.0F, 35.0F, 0.0F);
/*  73:    */    
/*  75: 75 */    this.b = new yN(a(), 100, 20, "Leave Faction", localaO, localaO);
/*  76:    */    
/*  77: 77 */    this.b.a = "LEAVE_FACTION";
/*  78: 78 */    this.b.a(400.0F, 35.0F, 0.0F);
/*  79:    */    
/*  80: 80 */    hw localhw = new hw(this);
/*  81:    */    
/*  86: 86 */    this.c = new yN(a(), 100, 20, localhw, localaO, localaO);
/*  87: 87 */    this.c.a = "INCOMING_INVITES";
/*  88: 88 */    this.c.a(150.0F, 35.0F, 0.0F);
/*  89:    */    
/*  91: 91 */    this.d = new yN(a(), 120, 20, "Pending Invites", localaO, localaO);
/*  92: 92 */    this.d.a = "OUTGOING_INVITES";
/*  93: 93 */    this.d.a(260.0F, 35.0F, 0.0F);
/*  94:    */    
/*  95: 95 */    this.jdField_a_of_type_Gz = new hx(a());
/*  96:    */    
/* 104:104 */    this.jdField_a_of_type_Gz.c();
/* 105:105 */    this.jdField_a_of_type_Gz.a(0.0F, 60.0F, 0.0F);
/* 106:    */    
/* 108:108 */    this.jdField_a_of_type_HK = new hK(a());
/* 109:109 */    this.jdField_a_of_type_HK.a(0.0F, 140.0F, 0.0F);
/* 110:110 */    this.jdField_a_of_type_HK.c();
/* 111:111 */    this.g = true;
/* 112:    */    
/* 113:113 */    a(this.jdField_a_of_type_YN);
/* 114:114 */    a(this.jdField_a_of_type_Yx);
/* 115:115 */    a(this.b);
/* 116:116 */    a(this.c);
/* 117:117 */    a(this.d);
/* 118:118 */    a(this.jdField_a_of_type_Gz);
/* 119:119 */    a(this.jdField_a_of_type_HK);
/* 120:    */  }
/* 121:    */  
/* 123:    */  public final float a()
/* 124:    */  {
/* 125:125 */    return 0.0F;
/* 126:    */  }
/* 127:    */  
/* 129:    */  public final float b()
/* 130:    */  {
/* 131:131 */    return 0.0F;
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */