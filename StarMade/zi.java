/*     */ import java.util.LinkedList;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public final class zi
/*     */ {
/*     */   public String a;
/*     */   public boolean a;
/*     */   public Vector a;
/*     */   public LinkedList a;
/*     */   public zi a;
/*     */   public String b;
/*     */ 
/*     */   public zi()
/*     */   {
/*  91 */     this.jdField_a_of_type_Boolean = false;
/*     */ 
/*  97 */     this.jdField_a_of_type_JavaUtilVector = new Vector();
/*     */ 
/* 100 */     this.jdField_a_of_type_JavaUtilLinkedList = new LinkedList();
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 121 */     String str = "";
/*     */ 
/* 126 */     str = str + "<" + this.jdField_a_of_type_JavaLangString + ">\n";
/* 127 */     for (zi localzi : this.jdField_a_of_type_JavaUtilLinkedList) {
/* 128 */       str = str + localzi.toString();
/*     */     }
/*     */ 
/* 138 */     return str + "</" + this.jdField_a_of_type_JavaLangString + ">\n";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zi
 * JD-Core Version:    0.6.2
 */