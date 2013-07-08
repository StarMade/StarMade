/*  1:   */import java.util.HashMap;
/*  2:   */import org.schema.schine.network.StateInterface;
/*  3:   */
/* 18:   */public abstract class wl
/* 19:   */{
/* 20:   */  public final wo a;
/* 21:21 */  private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/* 22:   */  public ws a;
/* 23:   */  private boolean jdField_a_of_type_Boolean;
/* 24:   */  
/* 25:   */  public wl(wo paramwo, boolean paramBoolean)
/* 26:   */  {
/* 27:27 */    this.jdField_a_of_type_Wo = paramwo;
/* 28:   */    
/* 29:29 */    a(this.jdField_a_of_type_JavaUtilHashMap);
/* 30:30 */    this.jdField_a_of_type_Ws = ((ws)this.jdField_a_of_type_JavaUtilHashMap.get(a()));
/* 31:31 */    if ((!b) && (this.jdField_a_of_type_Ws == null)) throw new AssertionError();
/* 32:32 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 33:   */  }
/* 34:   */  
/* 36:   */  protected abstract void a(HashMap paramHashMap);
/* 37:   */  
/* 38:   */  protected abstract String a();
/* 39:   */  
/* 40:   */  public StateInterface a()
/* 41:   */  {
/* 42:42 */    return this.jdField_a_of_type_Wo.a();
/* 43:   */  }
/* 44:   */  
/* 45:45 */  public final ws a() { return this.jdField_a_of_type_Ws; }
/* 46:   */  
/* 47:   */  public final void a(xq paramxq)
/* 48:   */  {
/* 49:49 */    this.jdField_a_of_type_Wo.a(paramxq);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public final boolean b() {
/* 53:53 */    return this.jdField_a_of_type_Boolean;
/* 54:   */  }
/* 55:   */  
/* 56:   */  public final void b(boolean paramBoolean) {
/* 57:57 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public static void g() {}
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */