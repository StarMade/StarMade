/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import org.dom4j.IllegalAddException;
/*   8:    */import org.dom4j.Node;
/*   9:    */
/*  28:    */public class BackedList
/*  29:    */  extends ArrayList
/*  30:    */{
/*  31:    */  private List branchContent;
/*  32:    */  private AbstractBranch branch;
/*  33:    */  
/*  34:    */  public BackedList(AbstractBranch branch, List branchContent)
/*  35:    */  {
/*  36: 36 */    this(branch, branchContent, branchContent.size());
/*  37:    */  }
/*  38:    */  
/*  39:    */  public BackedList(AbstractBranch branch, List branchContent, int capacity) {
/*  40: 40 */    super(capacity);
/*  41: 41 */    this.branch = branch;
/*  42: 42 */    this.branchContent = branchContent;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public BackedList(AbstractBranch branch, List branchContent, List initialContent)
/*  46:    */  {
/*  47: 47 */    super(initialContent);
/*  48: 48 */    this.branch = branch;
/*  49: 49 */    this.branchContent = branchContent;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public boolean add(Object object) {
/*  53: 53 */    this.branch.addNode(asNode(object));
/*  54:    */    
/*  55: 55 */    return super.add(object);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public void add(int index, Object object) {
/*  59: 59 */    int size = size();
/*  60:    */    
/*  61: 61 */    if (index < 0) {
/*  62: 62 */      throw new IndexOutOfBoundsException("Index value: " + index + " is less than zero");
/*  63:    */    }
/*  64: 64 */    if (index > size) {
/*  65: 65 */      throw new IndexOutOfBoundsException("Index value: " + index + " cannot be greater than " + "the size: " + size);
/*  66:    */    }
/*  67:    */    
/*  68:    */    int realIndex;
/*  69:    */    
/*  70:    */    int realIndex;
/*  71: 71 */    if (size == 0) {
/*  72: 72 */      realIndex = this.branchContent.size(); } else { int realIndex;
/*  73: 73 */      if (index < size) {
/*  74: 74 */        realIndex = this.branchContent.indexOf(get(index));
/*  75:    */      } else {
/*  76: 76 */        realIndex = this.branchContent.indexOf(get(size - 1)) + 1;
/*  77:    */      }
/*  78:    */    }
/*  79: 79 */    this.branch.addNode(realIndex, asNode(object));
/*  80: 80 */    super.add(index, object);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public Object set(int index, Object object) {
/*  84: 84 */    int realIndex = this.branchContent.indexOf(get(index));
/*  85:    */    
/*  86: 86 */    if (realIndex < 0) {
/*  87: 87 */      realIndex = index == 0 ? 0 : 2147483647;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    if (realIndex < this.branchContent.size()) {
/*  91: 91 */      this.branch.removeNode(asNode(get(index)));
/*  92: 92 */      this.branch.addNode(realIndex, asNode(object));
/*  93:    */    } else {
/*  94: 94 */      this.branch.removeNode(asNode(get(index)));
/*  95: 95 */      this.branch.addNode(asNode(object));
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    this.branch.childAdded(asNode(object));
/*  99:    */    
/* 100:100 */    return super.set(index, object);
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean remove(Object object) {
/* 104:104 */    this.branch.removeNode(asNode(object));
/* 105:    */    
/* 106:106 */    return super.remove(object);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public Object remove(int index) {
/* 110:110 */    Object object = super.remove(index);
/* 111:    */    
/* 112:112 */    if (object != null) {
/* 113:113 */      this.branch.removeNode(asNode(object));
/* 114:    */    }
/* 115:    */    
/* 116:116 */    return object;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public boolean addAll(Collection collection) {
/* 120:120 */    ensureCapacity(size() + collection.size());
/* 121:    */    
/* 122:122 */    int count = size();
/* 123:    */    
/* 124:124 */    for (Iterator iter = collection.iterator(); iter.hasNext(); count--) {
/* 125:125 */      add(iter.next());
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return count != 0;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public boolean addAll(int index, Collection collection) {
/* 132:132 */    ensureCapacity(size() + collection.size());
/* 133:    */    
/* 134:134 */    int count = size();
/* 135:    */    
/* 136:136 */    for (Iterator iter = collection.iterator(); iter.hasNext(); count--) {
/* 137:137 */      add(index++, iter.next());
/* 138:    */    }
/* 139:    */    
/* 140:140 */    return count != 0;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void clear() {
/* 144:144 */    for (Iterator iter = iterator(); iter.hasNext();) {
/* 145:145 */      Object object = iter.next();
/* 146:146 */      this.branchContent.remove(object);
/* 147:147 */      this.branch.childRemoved(asNode(object));
/* 148:    */    }
/* 149:    */    
/* 150:150 */    super.clear();
/* 151:    */  }
/* 152:    */  
/* 159:    */  public void addLocal(Object object)
/* 160:    */  {
/* 161:161 */    super.add(object);
/* 162:    */  }
/* 163:    */  
/* 164:    */  protected Node asNode(Object object) {
/* 165:165 */    if ((object instanceof Node)) {
/* 166:166 */      return (Node)object;
/* 167:    */    }
/* 168:168 */    throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.BackedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */