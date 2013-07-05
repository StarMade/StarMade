/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ public abstract class sa extends JLabel
/*    */   implements rY
/*    */ {
/*    */   private static final long serialVersionUID = 5254155298324471539L;
/*    */   private final String jdField_a_of_type_JavaLangString;
/*    */   private Object jdField_a_of_type_JavaLangObject;
/*    */ 
/*    */   public sa(String paramString)
/*    */   {
/* 16 */     this.jdField_a_of_type_JavaLangString = paramString;
/*    */ 
/* 18 */     this.jdField_a_of_type_JavaLangObject = a();
/*    */   }
/*    */ 
/*    */   public final JComponent a()
/*    */   {
/* 28 */     setText(a().toString());
/* 29 */     setOpaque(true);
/*    */ 
/* 37 */     setBackground(UIManager.getColor("List.background"));
/*    */ 
/* 40 */     return this;
/*    */   }
/*    */ 
/*    */   public final boolean a() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public final boolean b()
/*    */   {
/*    */     Object localObject;
/* 49 */     if (!(
/* 49 */       localObject = a())
/* 49 */       .equals(this.jdField_a_of_type_JavaLangObject)) {
/* 50 */       setText(localObject.toString());
/* 51 */       this.jdField_a_of_type_JavaLangObject = localObject;
/*    */ 
/* 53 */       return true;
/*    */     }
/* 55 */     return false;
/*    */   }
/*    */   public final String a() {
/* 58 */     return this.jdField_a_of_type_JavaLangString;
/*    */   }
/*    */ 
/*    */   public abstract Object a();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sa
 * JD-Core Version:    0.6.2
 */