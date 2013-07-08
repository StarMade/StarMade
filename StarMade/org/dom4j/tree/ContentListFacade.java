/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.AbstractList;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import org.dom4j.IllegalAddException;
/*   8:    */import org.dom4j.Node;
/*   9:    */
/*  31:    */public class ContentListFacade
/*  32:    */  extends AbstractList
/*  33:    */{
/*  34:    */  private List branchContent;
/*  35:    */  private AbstractBranch branch;
/*  36:    */  
/*  37:    */  public ContentListFacade(AbstractBranch branch, List branchContent)
/*  38:    */  {
/*  39: 39 */    this.branch = branch;
/*  40: 40 */    this.branchContent = branchContent;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public boolean add(Object object) {
/*  44: 44 */    this.branch.childAdded(asNode(object));
/*  45:    */    
/*  46: 46 */    return this.branchContent.add(object);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void add(int index, Object object) {
/*  50: 50 */    this.branch.childAdded(asNode(object));
/*  51: 51 */    this.branchContent.add(index, object);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public Object set(int index, Object object) {
/*  55: 55 */    this.branch.childAdded(asNode(object));
/*  56:    */    
/*  57: 57 */    return this.branchContent.set(index, object);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public boolean remove(Object object) {
/*  61: 61 */    this.branch.childRemoved(asNode(object));
/*  62:    */    
/*  63: 63 */    return this.branchContent.remove(object);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Object remove(int index) {
/*  67: 67 */    Object object = this.branchContent.remove(index);
/*  68:    */    
/*  69: 69 */    if (object != null) {
/*  70: 70 */      this.branch.childRemoved(asNode(object));
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    return object;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public boolean addAll(Collection collection) {
/*  77: 77 */    int count = this.branchContent.size();
/*  78:    */    
/*  79: 79 */    for (Iterator iter = collection.iterator(); iter.hasNext(); count++) {
/*  80: 80 */      add(iter.next());
/*  81:    */    }
/*  82:    */    
/*  83: 83 */    return count == this.branchContent.size();
/*  84:    */  }
/*  85:    */  
/*  86:    */  public boolean addAll(int index, Collection collection) {
/*  87: 87 */    int count = this.branchContent.size();
/*  88:    */    
/*  89: 89 */    for (Iterator iter = collection.iterator(); iter.hasNext(); count--) {
/*  90: 90 */      add(index++, iter.next());
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    return count == this.branchContent.size();
/*  94:    */  }
/*  95:    */  
/*  96:    */  public void clear() {
/*  97: 97 */    for (Iterator iter = iterator(); iter.hasNext();) {
/*  98: 98 */      Object object = iter.next();
/*  99: 99 */      this.branch.childRemoved(asNode(object));
/* 100:    */    }
/* 101:    */    
/* 102:102 */    this.branchContent.clear();
/* 103:    */  }
/* 104:    */  
/* 105:    */  public boolean removeAll(Collection c) {
/* 106:106 */    for (Iterator iter = c.iterator(); iter.hasNext();) {
/* 107:107 */      Object object = iter.next();
/* 108:108 */      this.branch.childRemoved(asNode(object));
/* 109:    */    }
/* 110:    */    
/* 111:111 */    return this.branchContent.removeAll(c);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public int size() {
/* 115:115 */    return this.branchContent.size();
/* 116:    */  }
/* 117:    */  
/* 118:    */  public boolean isEmpty() {
/* 119:119 */    return this.branchContent.isEmpty();
/* 120:    */  }
/* 121:    */  
/* 122:    */  public boolean contains(Object o) {
/* 123:123 */    return this.branchContent.contains(o);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public Object[] toArray() {
/* 127:127 */    return this.branchContent.toArray();
/* 128:    */  }
/* 129:    */  
/* 130:    */  public Object[] toArray(Object[] a) {
/* 131:131 */    return this.branchContent.toArray(a);
/* 132:    */  }
/* 133:    */  
/* 134:    */  public boolean containsAll(Collection c) {
/* 135:135 */    return this.branchContent.containsAll(c);
/* 136:    */  }
/* 137:    */  
/* 138:    */  public Object get(int index) {
/* 139:139 */    return this.branchContent.get(index);
/* 140:    */  }
/* 141:    */  
/* 142:    */  public int indexOf(Object o) {
/* 143:143 */    return this.branchContent.indexOf(o);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public int lastIndexOf(Object o) {
/* 147:147 */    return this.branchContent.lastIndexOf(o);
/* 148:    */  }
/* 149:    */  
/* 150:    */  protected Node asNode(Object object) {
/* 151:151 */    if ((object instanceof Node)) {
/* 152:152 */      return (Node)object;
/* 153:    */    }
/* 154:154 */    throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
/* 155:    */  }
/* 156:    */  
/* 159:    */  protected List getBackingList()
/* 160:    */  {
/* 161:161 */    return this.branchContent;
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ContentListFacade
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */