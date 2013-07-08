/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   5:    */import org.schema.schine.network.client.ClientState;
/*   6:    */
/*  53:    */public final class ib
/*  54:    */  extends yz
/*  55:    */{
/*  56:    */  private boolean jdField_a_of_type_Boolean;
/*  57:    */  private final mF jdField_a_of_type_MF;
/*  58:    */  private final String b;
/*  59:    */  
/*  60:    */  public ib(hV paramhV, ClientState paramClientState, mF parammF, boolean paramBoolean)
/*  61:    */  {
/*  62: 62 */    super(paramClientState);
/*  63: 63 */    this.jdField_a_of_type_MF = parammF;
/*  64: 64 */    this.jdField_a_of_type_Boolean = paramBoolean;
/*  65: 65 */    if ((parammF instanceof kd)) {
/*  66: 66 */      this.b = "Ship";return; }
/*  67: 67 */    if ((parammF instanceof kf)) {
/*  68: 68 */      this.b = "Shop";return; }
/*  69: 69 */    if ((parammF instanceof kl)) {
/*  70: 70 */      this.b = "Death Star";return; }
/*  71: 71 */    if ((parammF instanceof jy)) {
/*  72: 72 */      this.b = "Asteroid";return; }
/*  73: 73 */    if ((parammF instanceof lD)) {
/*  74: 74 */      this.b = "Player";return;
/*  75:    */    }
/*  76: 76 */    this.b = "Other";
/*  77:    */  }
/*  78:    */  
/*  82:    */  public final void a() {}
/*  83:    */  
/*  86:    */  public final void b()
/*  87:    */  {
/*  88: 88 */    this.jdField_a_of_type_Boolean = (hV.a(this.jdField_a_of_type_HV) == this.jdField_a_of_type_MF);
/*  89:    */    
/*  90: 90 */    ct localct = (ct)a();
/*  91: 91 */    hV.b().set(this.jdField_a_of_type_MF.getWorldTransformClient().origin);
/*  92: 92 */    if (localct.a() != null) {
/*  93: 93 */      hV.b().sub(localct.a().getWorldTransform().origin);
/*  94:    */    } else {
/*  95: 95 */      hV.b().sub(xe.a().a());
/*  96:    */    }
/*  97: 97 */    float f = hV.b().length();
/*  98:    */    
/* 100:100 */    String str2 = this.jdField_a_of_type_MF.toNiceString();
/* 101:101 */    String str1 = (int)f + "m";
/* 102:102 */    hV.a(this.jdField_a_of_type_HV).b.set(0, this.b);
/* 103:103 */    hV.a(this.jdField_a_of_type_HV).b.set(1, str2);
/* 104:104 */    hV.a(this.jdField_a_of_type_HV).b.set(2, str1);
/* 105:105 */    hV.a(this.jdField_a_of_type_HV).a().set(5.0F, 5.0F, 0.0F);
/* 106:    */    
/* 109:109 */    ij.a(this.jdField_a_of_type_MF, hV.a(), this.jdField_a_of_type_MF == hV.b(this.jdField_a_of_type_HV), (ct)a());
/* 110:110 */    hV.a().w = 0.8F;
/* 111:111 */    hV.a(this.jdField_a_of_type_HV).a().c(hV.a());
/* 112:    */    
/* 113:113 */    if (this.jdField_a_of_type_Boolean) {
/* 114:114 */      hV.b(this.jdField_a_of_type_HV).a_(5);
/* 115:    */    } else {
/* 116:116 */      hV.b(this.jdField_a_of_type_HV).a_(4);
/* 117:    */    }
/* 118:118 */    hV.b(this.jdField_a_of_type_HV).b();
/* 119:    */  }
/* 120:    */  
/* 123:    */  public final float a()
/* 124:    */  {
/* 125:125 */    return 64.0F;
/* 126:    */  }
/* 127:    */  
/* 130:    */  public final mF a()
/* 131:    */  {
/* 132:132 */    return this.jdField_a_of_type_MF;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public final float b()
/* 136:    */  {
/* 137:137 */    return 255.0F;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public final void c() {}
/* 141:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ib
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */