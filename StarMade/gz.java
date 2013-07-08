/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.List;
/*  3:   */import java.util.Observable;
/*  4:   */import java.util.Observer;
/*  5:   */import org.schema.schine.network.client.ClientState;
/*  6:   */
/* 12:   */public abstract class gz
/* 13:   */  extends yz
/* 14:   */  implements Observer
/* 15:   */{
/* 16:   */  private int jdField_a_of_type_Int;
/* 17:   */  private int jdField_b_of_type_Int;
/* 18:   */  private yr jdField_a_of_type_Yr;
/* 19:   */  private yP jdField_a_of_type_YP;
/* 20:   */  private String jdField_b_of_type_JavaLangString;
/* 21:   */  private boolean jdField_a_of_type_Boolean;
/* 22:   */  
/* 23:   */  public gz(ClientState paramClientState, int paramInt)
/* 24:   */  {
/* 25:25 */    super(paramClientState);
/* 26:26 */    this.jdField_a_of_type_Int = paramInt;
/* 27:27 */    this.jdField_b_of_type_Int = 80;
/* 28:   */    
/* 29:29 */    ((ct)a()).a().addObserver(this);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public final void a()
/* 33:   */  {
/* 34:34 */    ((ct)a()).a().deleteObserver(this);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public final void b()
/* 38:   */  {
/* 39:39 */    if (this.jdField_a_of_type_Boolean) {
/* 40:40 */      this.jdField_b_of_type_JavaLangString = a();
/* 41:41 */      this.jdField_a_of_type_YP.b = new ArrayList();
/* 42:42 */      String[] arrayOfString = this.jdField_b_of_type_JavaLangString.split("\\\\n");
/* 43:43 */      this.jdField_a_of_type_YP.b.clear();
/* 44:44 */      for (int i = 0; i < arrayOfString.length; i++) {
/* 45:45 */        this.jdField_a_of_type_YP.b.add(arrayOfString[i]);
/* 46:   */      }
/* 47:47 */      this.jdField_a_of_type_Boolean = false;
/* 48:   */    }
/* 49:   */    
/* 50:50 */    k();
/* 51:   */  }
/* 52:   */  
/* 53:   */  public abstract String a();
/* 54:   */  
/* 55:   */  public final void c()
/* 56:   */  {
/* 57:57 */    this.jdField_a_of_type_Yr = new yr(a(), this.jdField_a_of_type_Int, this.jdField_b_of_type_Int);
/* 58:58 */    this.jdField_a_of_type_YP = new yP(this.jdField_a_of_type_Int, this.jdField_b_of_type_Int, a());
/* 59:   */    
/* 61:61 */    this.jdField_b_of_type_JavaLangString = a();
/* 62:62 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 63:63 */    String[] arrayOfString = this.jdField_b_of_type_JavaLangString.split("\\\\n");
/* 64:64 */    for (int i = 0; i < arrayOfString.length; i++) {
/* 65:65 */      this.jdField_a_of_type_YP.b.add(arrayOfString[i]);
/* 66:   */    }
/* 67:67 */    this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YP);
/* 68:   */    
/* 69:69 */    a(this.jdField_a_of_type_Yr);
/* 70:   */  }
/* 71:   */  
/* 74:   */  public final float a()
/* 75:   */  {
/* 76:76 */    return this.jdField_b_of_type_Int;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public final float b()
/* 80:   */  {
/* 81:81 */    return this.jdField_a_of_type_Int;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public void update(Observable paramObservable, Object paramObject)
/* 85:   */  {
/* 86:86 */    this.jdField_a_of_type_Boolean = true;
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */