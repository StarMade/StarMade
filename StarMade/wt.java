/*   1:    */import java.io.Serializable;
/*   2:    */import java.util.Vector;
/*   3:    */
/*  61:    */public abstract class wt
/*  62:    */  implements Serializable
/*  63:    */{
/*  64:    */  private static final long serialVersionUID = -493388144215505990L;
/*  65:    */  private wk jdField_a_of_type_Wk;
/*  66: 66 */  private boolean jdField_a_of_type_Boolean = true;
/*  67:    */  
/*  70:    */  private wq jdField_a_of_type_Wq;
/*  71:    */  
/*  75:    */  public wt(wk paramwk)
/*  76:    */  {
/*  77: 77 */    this.jdField_a_of_type_Wk = paramwk;
/*  78: 78 */    this.jdField_a_of_type_Wq = new wq(this);
/*  79:    */  }
/*  80:    */  
/*  81: 81 */  public final wt a(wv paramwv, wt paramwt) { wt localwt = paramwt;paramwt = paramwv;(paramwv = this.jdField_a_of_type_Wq).b.add(localwt);paramwv.a.add(paramwt);
/*  82: 82 */    return this;
/*  83:    */  }
/*  84:    */  
/*  88:    */  public boolean equals(Object paramObject)
/*  89:    */  {
/*  90: 90 */    return (paramObject != null) && (getClass().equals(paramObject.getClass()));
/*  91:    */  }
/*  92:    */  
/*  99:    */  public wk a()
/* 100:    */  {
/* 101:101 */    return this.jdField_a_of_type_Wk;
/* 102:    */  }
/* 103:    */  
/* 109:    */  public final wq a()
/* 110:    */  {
/* 111:111 */    return this.jdField_a_of_type_Wq;
/* 112:    */  }
/* 113:    */  
/* 118:    */  public final boolean e()
/* 119:    */  {
/* 120:120 */    return this.jdField_a_of_type_Boolean;
/* 121:    */  }
/* 122:    */  
/* 130:    */  public abstract boolean c();
/* 131:    */  
/* 139:    */  public abstract boolean b();
/* 140:    */  
/* 148:    */  public abstract boolean d();
/* 149:    */  
/* 157:    */  public final void a(boolean paramBoolean)
/* 158:    */  {
/* 159:159 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 160:    */  }
/* 161:    */  
/* 173:    */  public final void a(wv paramwv)
/* 174:    */  {
/* 175:175 */    if ((!b) && (a() == null)) throw new AssertionError();
/* 176:176 */    if ((!b) && (a().a() == null)) throw new AssertionError();
/* 177:177 */    if ((!b) && (a().a().a() == null)) throw new AssertionError();
/* 178:178 */    a().a().a().a(paramwv);
/* 179:    */  }
/* 180:    */  
/* 183:    */  public String toString()
/* 184:    */  {
/* 185:185 */    return getClass().getSimpleName();
/* 186:    */  }
/* 187:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */