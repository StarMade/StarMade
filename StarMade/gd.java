/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */import org.schema.game.network.objects.NetworkGameState;
/*   6:    */import org.schema.game.server.data.blueprintnw.BlueprintEntry;
/*   7:    */import org.schema.schine.network.client.ClientState;
/*   8:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*   9:    */
/*  30:    */public final class gd
/*  31:    */  extends yr
/*  32:    */  implements ys
/*  33:    */{
/*  34:    */  private yN jdField_a_of_type_YN;
/*  35:    */  private yN b;
/*  36:    */  private yN c;
/*  37:    */  private yP jdField_a_of_type_YP;
/*  38:    */  
/*  39:    */  public gd(ClientState paramClientState)
/*  40:    */  {
/*  41: 41 */    super(paramClientState, 510.0F, 60.0F);
/*  42:    */  }
/*  43:    */  
/*  44: 44 */  public final aC a() { return ((ct)a()).a().a.a.jdField_a_of_type_AC; }
/*  45:    */  
/*  50:    */  public final void c()
/*  51:    */  {
/*  52: 52 */    super.c();
/*  53: 53 */    ct localct = (ct)a();
/*  54: 54 */    this.jdField_a_of_type_YN = new yN(localct, 142, 25, new Vector4f(0.3F, 0.3F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.c(), "Create new entry", this, a());
/*  55:    */    
/*  60: 60 */    this.jdField_a_of_type_YN.a = "save";
/*  61: 61 */    this.jdField_a_of_type_YN.b(4, 1);
/*  62: 62 */    this.b = new yN(localct, 140, 20, new Vector4f(0.3F, 0.7F, 0.5F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Save in local catalog", this, a());
/*  63:    */    
/*  68: 68 */    this.b.a = "save_local";
/*  69: 69 */    this.c = new yN(localct, 160, 20, new Vector4f(0.5F, 0.7F, 0.3F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Upload entry from local", this, a());
/*  70:    */    
/*  75: 75 */    this.c.a = "upload";
/*  76: 76 */    this.b.a().x = 220.0F;
/*  77:    */    
/*  78: 78 */    this.c.a().x = 370.0F;
/*  79:    */    
/*  80: 80 */    yP localyP = new yP(1, 1, localct);
/*  81:    */    
/*  82:    */    int i;
/*  83: 83 */    if ((i = ((Integer)localct.a().a().saveSlotsAllowed.get()).intValue()) < 0) {
/*  84: 84 */      localyP.a("Used: " + localct.a().a().a().size());
/*  85:    */    } else {
/*  86: 86 */      localyP.a("Used: " + localct.a().a().a().size() + "/" + i);
/*  87:    */    }
/*  88: 88 */    localyP.a().x = 150.0F;
/*  89: 89 */    localyP.a().y = 2.0F;
/*  90:    */    
/*  91: 91 */    this.jdField_a_of_type_YP = new yP(1, 1, localct);
/*  92: 92 */    this.jdField_a_of_type_YP.b = new ArrayList();
/*  93: 93 */    this.jdField_a_of_type_YP.b.add("\"Create new Entry\" will save the ship you are currently in into this catalog. You can also save ");
/*  94: 94 */    this.jdField_a_of_type_YP.b.add("a ship in your singleplayer (local) catalog, or upload an entry from it.");
/*  95: 95 */    this.jdField_a_of_type_YP.a().y = (this.jdField_a_of_type_YN.a().y + this.jdField_a_of_type_YN.a() + 4.0F);
/*  96: 96 */    a(this.jdField_a_of_type_YP);
/*  97: 97 */    a(localyP);
/*  98: 98 */    a(this.jdField_a_of_type_YN);
/*  99: 99 */    a(this.b);
/* 100:100 */    a(this.c);
/* 101:    */  }
/* 102:    */  
/* 108:    */  public final void b()
/* 109:    */  {
/* 110:110 */    ((ct)a()).a().a.a.jdField_a_of_type_Ar.a();
/* 111:    */    
/* 114:114 */    super.b();
/* 115:    */  }
/* 116:    */  
/* 118:    */  public final void a(yz paramyz, xp paramxp)
/* 119:    */  {
/* 120:120 */    if (paramxp.a()) {
/* 121:121 */      paramxp = ((ct)a()).a().a.a.jdField_a_of_type_Ar.a();
/* 122:122 */      paramxp = (((ct)a()).a() != null) || ((paramxp != null) && ((paramxp instanceof kd))) ? 1 : 0;
/* 123:123 */      Object localObject; if ("save".equals(paramyz.a)) {
/* 124:124 */        if (paramxp != 0) {
/* 125:125 */          paramyz = this;a().e(true);paramxp = "Please enter in a name for your blue print!";(localObject = new gi(paramyz, (ct)paramyz.a(), "BluePrint", paramxp, "BLUEPRINT_" + System.currentTimeMillis())).a(new gj());((K)localObject).c();return;
/* 126:    */        }
/* 127:127 */        ((ct)a()).a().b("You must be in a\nship or have one\nselected to save it");return;
/* 128:    */      }
/* 129:129 */      if ("save_local".equals(paramyz.a)) {
/* 130:130 */        if (paramxp != 0) {
/* 131:131 */          paramyz = this;a().e(true);paramxp = "Please enter in a name for your blue print!";(localObject = new ge(paramyz, (ct)paramyz.a(), "BluePrint", paramxp, "BLUEPRINT_" + System.currentTimeMillis())).a(new gf());((K)localObject).c();return;
/* 132:    */        }
/* 133:133 */        ((ct)a()).a().b("You must be in a\nship or have one\nselected to save it");return;
/* 134:    */      }
/* 135:135 */      if ("upload".equals(paramyz.a)) {
/* 136:136 */        paramyz = this;a().e(true);paramxp = tH.a.a();localObject = "Please enter in a name for your blue print!\n\nAvailable:\n" + paramxp;(paramxp = new gg(paramyz, (ct)paramyz.a(), "BluePrint", localObject, paramxp.isEmpty() ? "" : ((BlueprintEntry)paramxp.get(0)).toString())).a(new gh());paramxp.c();
/* 137:    */      }
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 330:    */  public final boolean a()
/* 331:    */  {
/* 332:332 */    return false;
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gd
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */