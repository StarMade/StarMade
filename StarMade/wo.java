/*   1:    */import org.schema.schine.network.StateInterface;
/*   2:    */import org.schema.schine.network.server.ServerStateInterface;
/*   3:    */
/*  60:    */public class wo
/*  61:    */  implements wk
/*  62:    */{
/*  63:    */  private String jdField_a_of_type_JavaLangString;
/*  64:    */  public wl a;
/*  65:    */  private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  66:    */  public final boolean b;
/*  67:    */  
/*  68:    */  public wo(String paramString, StateInterface paramStateInterface)
/*  69:    */  {
/*  70: 70 */    this.jdField_a_of_type_JavaLangString = paramString;
/*  71: 71 */    this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  72: 72 */    this.b = (this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof ServerStateInterface);
/*  73:    */  }
/*  74:    */  
/*  76:    */  public final wl a()
/*  77:    */  {
/*  78: 78 */    return this.jdField_a_of_type_Wl;
/*  79:    */  }
/*  80:    */  
/*  87:    */  public final ws a()
/*  88:    */  {
/*  89: 89 */    return this.jdField_a_of_type_Wl.a();
/*  90:    */  }
/*  91:    */  
/*  97:    */  public final wt a()
/*  98:    */  {
/*  99: 99 */    return this.jdField_a_of_type_Wl.a().a().a();
/* 100:    */  }
/* 101:    */  
/* 119:    */  public final void d()
/* 120:    */  {
/* 121:121 */    this.jdField_a_of_type_Wl = null;
/* 122:    */  }
/* 123:    */  
/* 128:    */  public String toString()
/* 129:    */  {
/* 130:130 */    String str = this.jdField_a_of_type_JavaLangString;
/* 131:131 */    if (this.jdField_a_of_type_Wl == null) {
/* 132:132 */      return str + "[NULL_PROGRAM]";
/* 133:    */    }
/* 134:134 */    if (this.jdField_a_of_type_Wl.a().a().a == null) {
/* 135:135 */      return str + "\n->[" + this.jdField_a_of_type_Wl.getClass().getSimpleName() + "->NULL_STATE]";
/* 136:    */    }
/* 137:137 */    return str + "\n->[" + this.jdField_a_of_type_Wl.getClass().getSimpleName() + "->" + this.jdField_a_of_type_Wl.a().a().a.getClass().getSimpleName() + "]";
/* 138:    */  }
/* 139:    */  
/* 149:    */  public void a(xq paramxq)
/* 150:    */  {
/* 151:151 */    if ((this.jdField_a_of_type_Wl != null) && (!this.jdField_a_of_type_Wl.b()))
/* 152:152 */      this.jdField_a_of_type_Wl.a().b();
/* 153:    */  }
/* 154:    */  
/* 155:    */  public boolean a() {
/* 156:156 */    return (this.jdField_a_of_type_Wl != null) && (!this.jdField_a_of_type_Wl.b());
/* 157:    */  }
/* 158:    */  
/* 162:    */  public StateInterface a()
/* 163:    */  {
/* 164:164 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/* 165:    */  }
/* 166:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */