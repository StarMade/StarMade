/*   1:    */import java.util.LinkedList;
/*   2:    */import java.util.Vector;
/*   3:    */
/*  80:    */public final class zi
/*  81:    */{
/*  82:    */  public String a;
/*  83:    */  public boolean a;
/*  84:    */  public Vector a;
/*  85:    */  public LinkedList a;
/*  86:    */  public zi a;
/*  87:    */  public String b;
/*  88:    */  
/*  89:    */  public zi()
/*  90:    */  {
/*  91: 91 */    this.jdField_a_of_type_Boolean = false;
/*  92:    */    
/*  97: 97 */    this.jdField_a_of_type_JavaUtilVector = new Vector();
/*  98:    */    
/* 100:100 */    this.jdField_a_of_type_JavaUtilLinkedList = new LinkedList();
/* 101:    */  }
/* 102:    */  
/* 119:    */  public final String toString()
/* 120:    */  {
/* 121:121 */    String str = "";
/* 122:    */    
/* 126:126 */    str = str + "<" + this.jdField_a_of_type_JavaLangString + ">\n";
/* 127:127 */    for (zi localzi : this.jdField_a_of_type_JavaUtilLinkedList) {
/* 128:128 */      str = str + localzi.toString();
/* 129:    */    }
/* 130:    */    
/* 138:138 */    return str + "</" + this.jdField_a_of_type_JavaLangString + ">\n";
/* 139:    */  }
/* 140:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */