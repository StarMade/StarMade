/*  1:   */import javax.swing.JComponent;
/*  2:   */import javax.swing.JLabel;
/*  3:   */import javax.swing.UIManager;
/*  4:   */
/*  6:   */public abstract class sa
/*  7:   */  extends JLabel
/*  8:   */  implements rY
/*  9:   */{
/* 10:   */  private static final long serialVersionUID = 5254155298324471539L;
/* 11:   */  private final String jdField_a_of_type_JavaLangString;
/* 12:   */  private Object jdField_a_of_type_JavaLangObject;
/* 13:   */  
/* 14:   */  public sa(String paramString)
/* 15:   */  {
/* 16:16 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 17:   */    
/* 18:18 */    this.jdField_a_of_type_JavaLangObject = a();
/* 19:   */  }
/* 20:   */  
/* 26:   */  public final JComponent a()
/* 27:   */  {
/* 28:28 */    setText(a().toString());
/* 29:29 */    setOpaque(true);
/* 30:   */    
/* 37:37 */    setBackground(UIManager.getColor("List.background"));
/* 38:   */    
/* 40:40 */    return this;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public final boolean a() {
/* 44:44 */    return false;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public final boolean b() {
/* 48:   */    Object localObject;
/* 49:49 */    if (!(localObject = a()).equals(this.jdField_a_of_type_JavaLangObject)) {
/* 50:50 */      setText(localObject.toString());
/* 51:51 */      this.jdField_a_of_type_JavaLangObject = localObject;
/* 52:   */      
/* 53:53 */      return true;
/* 54:   */    }
/* 55:55 */    return false;
/* 56:   */  }
/* 57:   */  
/* 58:58 */  public final String a() { return this.jdField_a_of_type_JavaLangString; }
/* 59:   */  
/* 60:   */  public abstract Object a();
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sa
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */