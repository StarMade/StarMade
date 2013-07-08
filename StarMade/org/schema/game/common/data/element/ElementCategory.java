/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */
/*  5:   */public class ElementCategory {
/*  6:   */  private ElementCategory parent;
/*  7:   */  private final Class category;
/*  8: 8 */  private final java.util.ArrayList children = new java.util.ArrayList();
/*  9: 9 */  private final java.util.ArrayList infoElements = new java.util.ArrayList();
/* 10:   */  
/* 11:   */  public ElementCategory(Class paramClass, ElementCategory paramElementCategory) {
/* 12:12 */    this.category = paramClass;
/* 13:13 */    this.parent = paramElementCategory;
/* 14:   */  }
/* 15:   */  
/* 21:   */  public Class getCategory()
/* 22:   */  {
/* 23:23 */    return this.category;
/* 24:   */  }
/* 25:   */  
/* 27:   */  public java.util.ArrayList getChildren()
/* 28:   */  {
/* 29:29 */    return this.children;
/* 30:   */  }
/* 31:   */  
/* 34:   */  public java.util.ArrayList getInfoElements()
/* 35:   */  {
/* 36:36 */    return this.infoElements;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public boolean hasChildren() {
/* 40:40 */    return !getChildren().isEmpty();
/* 41:   */  }
/* 42:   */  
/* 43:   */  public boolean isRoot() {
/* 44:44 */    return this.parent == null;
/* 45:   */  }
/* 46:   */  
/* 48:48 */  public void print() { printRec(1); }
/* 49:   */  
/* 50:   */  private void printItems(int paramInt) {
/* 51:51 */    StringBuilder localStringBuilder = new StringBuilder();
/* 52:52 */    for (int i = 0; i < paramInt; i++) {
/* 53:53 */      localStringBuilder.append("-");
/* 54:   */    }
/* 55:55 */    for (i = 0; i < getInfoElements().size(); i++) {
/* 56:56 */      paramInt = (ElementInformation)getInfoElements().get(i);
/* 57:57 */      System.err.println(localStringBuilder.toString() + " " + paramInt.getName());
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 61:61 */  private void printRec(int paramInt) { StringBuilder localStringBuilder = new StringBuilder();
/* 62:62 */    for (int i = 0; i < paramInt; i++) {
/* 63:63 */      localStringBuilder.append("#");
/* 64:   */    }
/* 65:65 */    System.err.println(localStringBuilder.toString() + " " + this.category.getSimpleName());
/* 66:66 */    printItems(paramInt);
/* 67:67 */    for (i = 0; i < this.children.size(); i++) {
/* 68:68 */      ((ElementCategory)this.children.get(i)).printRec(paramInt + 1);
/* 69:   */    }
/* 70:   */  }
/* 71:   */  
/* 72:   */  public String toString() {
/* 73:73 */    return this.category.getSimpleName();
/* 74:   */  }
/* 75:   */  
/* 76:   */  public void clear() {
/* 77:77 */    this.children.clear();
/* 78:78 */    this.infoElements.clear();
/* 79:   */  }
/* 80:   */  
/* 81:   */  public void insertRecusrive(ElementInformation paramElementInformation)
/* 82:   */  {
/* 83:83 */    if (this.category == paramElementInformation.getType()) {
/* 84:84 */      this.infoElements.add(paramElementInformation);return;
/* 85:   */    }
/* 86:86 */    for (java.util.Iterator localIterator = getChildren().iterator(); localIterator.hasNext();) {
/* 87:87 */      ((ElementCategory)localIterator.next()).insertRecusrive(paramElementInformation);
/* 88:   */    }
/* 89:   */  }
/* 90:   */  
/* 91:   */  public void removeRecursive(ElementInformation paramElementInformation)
/* 92:   */  {
/* 93:93 */    if (this.infoElements.contains(paramElementInformation)) {
/* 94:94 */      this.infoElements.remove(paramElementInformation);
/* 95:95 */      System.err.println("REMOVED FROM CATEGORY: " + paramElementInformation.getName());return;
/* 96:   */    }
/* 97:97 */    for (java.util.Iterator localIterator = getChildren().iterator(); localIterator.hasNext();) {
/* 98:98 */      ((ElementCategory)localIterator.next()).removeRecursive(paramElementInformation);
/* 99:   */    }
/* 100:   */  }
/* 101:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementCategory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */