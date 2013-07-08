/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.schema.schine.network.client.ClientState;
/*   5:    */
/*  71:    */final class fk
/*  72:    */  extends yz
/*  73:    */{
/*  74:    */  private yP jdField_a_of_type_YP;
/*  75:    */  private yP b;
/*  76:    */  private yP c;
/*  77:    */  private yP d;
/*  78:    */  private yP e;
/*  79: 79 */  private int jdField_a_of_type_Int = 100;
/*  80: 80 */  public fk(ClientState paramClientState) { super(paramClientState);
/*  81:    */    
/*  83: 83 */    this.jdField_a_of_type_YP = new yP(300, 30, d.b(), paramClientState);
/*  84: 84 */    this.jdField_a_of_type_YP.b = new ArrayList();
/*  85: 85 */    this.jdField_a_of_type_YP.b.add("");
/*  86:    */    
/*  87: 87 */    this.b = new yP(300, 30, d.b(), paramClientState);
/*  88: 88 */    this.b.b = new ArrayList();
/*  89: 89 */    this.b.b.add("");
/*  90: 90 */    this.b.a().x += this.jdField_a_of_type_Int;
/*  91:    */    
/*  92: 92 */    this.c = new yP(300, 30, d.b(), paramClientState);
/*  93: 93 */    this.c.b = new ArrayList();
/*  94: 94 */    this.c.b.add("");
/*  95: 95 */    this.c.a().x += 2 * this.jdField_a_of_type_Int;
/*  96:    */    
/*  97: 97 */    this.d = new yP(300, 30, d.b(), paramClientState);
/*  98: 98 */    this.d.b = new ArrayList();
/*  99: 99 */    this.d.b.add("");
/* 100:100 */    this.d.a().x += 3 * this.jdField_a_of_type_Int;
/* 101:    */    
/* 102:102 */    this.e = new yP(300, 30, d.b(), paramClientState);
/* 103:103 */    this.e.b = new ArrayList();
/* 104:104 */    this.e.b.add("");
/* 105:105 */    this.e.a().x += 4 * this.jdField_a_of_type_Int;
/* 106:    */  }
/* 107:    */  
/* 110:    */  public final void a() {}
/* 111:    */  
/* 114:    */  public final void b()
/* 115:    */  {
/* 116:116 */    r();
/* 117:117 */    this.jdField_a_of_type_YP.b();
/* 118:118 */    this.b.b();
/* 119:119 */    this.c.b();
/* 120:120 */    this.d.b();
/* 121:121 */    this.e.b();
/* 122:    */  }
/* 123:    */  
/* 124:    */  public final float a()
/* 125:    */  {
/* 126:126 */    return this.jdField_a_of_type_YP.a();
/* 127:    */  }
/* 128:    */  
/* 129:    */  public final float b()
/* 130:    */  {
/* 131:131 */    return this.jdField_a_of_type_YP.b() * 4.0F + 4 * this.jdField_a_of_type_Int;
/* 132:    */  }
/* 133:    */  
/* 139:    */  public final void c()
/* 140:    */  {
/* 141:141 */    this.jdField_a_of_type_YP.c();
/* 142:142 */    this.b.c();
/* 143:143 */    this.c.c();
/* 144:144 */    this.d.c();
/* 145:145 */    this.e.c();
/* 146:    */  }
/* 147:    */  
/* 148:    */  public final void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
/* 149:149 */    this.jdField_a_of_type_YP.b.set(0, paramString1);
/* 150:150 */    this.b.b.set(0, paramString2);
/* 151:151 */    this.c.b.set(0, paramString3);
/* 152:152 */    this.d.b.set(0, paramString4);
/* 153:153 */    this.e.b.set(0, paramString5);
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */