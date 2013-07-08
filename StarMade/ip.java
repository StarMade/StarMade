/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector4f;
/*   4:    */import org.schema.game.common.data.element.ElementInformation;
/*   5:    */import org.schema.schine.network.client.ClientState;
/*   6:    */
/*  65:    */public final class ip
/*  66:    */  extends yr
/*  67:    */{
/*  68:    */  private final short jdField_a_of_type_Short;
/*  69:    */  private final ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*  70:    */  private int jdField_a_of_type_Int;
/*  71:    */  
/*  72:    */  public ip(ClientState paramClientState, ElementInformation paramElementInformation, int paramInt)
/*  73:    */  {
/*  74: 74 */    super(paramClientState, 224.0F, 74.0F);
/*  75: 75 */    this.jdField_a_of_type_Short = ((short)paramElementInformation.getBuildIconNum());
/*  76: 76 */    this.a = Short.valueOf(paramElementInformation.getId());
/*  77: 77 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/*  78: 78 */    this.jdField_a_of_type_Int = paramInt;
/*  79:    */  }
/*  80:    */  
/*  90:    */  public final void c()
/*  91:    */  {
/*  92: 92 */    super.c();
/*  93:    */    
/*  94: 94 */    if (this.jdField_a_of_type_Int % 2 == 0) {
/*  95: 95 */      yx localyx = new yx(a(), 224.0F, 74.0F, new Vector4f(0.06F, 0.06F, 0.06F, 1.0F));
/*  96: 96 */      a(localyx);
/*  97:    */    }
/*  98: 98 */    int i = this.jdField_a_of_type_Short / 256;
/*  99:    */    
/* 100:100 */    Object localObject = xe.a().a("build-icons-" + i.b(i) + "-16x16-gui-");
/* 101:    */    
/* 103:103 */    (localObject = new yE((yg)localObject, a())).a().y = 14.0F;
/* 104:    */    
/* 105:    */    yP localyP1;
/* 106:106 */    (localyP1 = new yP(224, 20, d.h(), a())).b = new ArrayList();
/* 107:107 */    localyP1.b.add(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName());
/* 108:108 */    localyP1.a().y = 3.0F;
/* 109:    */    
/* 110:    */    yP localyP2;
/* 111:    */    
/* 112:112 */    (localyP2 = new yP(224, 64, d.n(), a())).a().x = 64.0F;
/* 113:113 */    localyP2.a().y = 24.0F;
/* 114:    */    
/* 115:115 */    int j = this.jdField_a_of_type_Short % 256;
/* 116:116 */    ((yE)localObject).a_(j);
/* 117:117 */    localyP2.b = new ArrayList();
/* 118:118 */    localyP2.b.add(new iq(this, this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation));
/* 119:119 */    localyP2.b.add(new ir(this, this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation));
/* 120:120 */    a(localyP1);
/* 121:121 */    a((yz)localObject);
/* 122:122 */    a(localyP2);
/* 123:    */    
/* 124:124 */    this.g = true;
/* 125:    */  }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ip
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */