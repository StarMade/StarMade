/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */import java.util.Iterator;
/*   5:    */import org.jaxen.Navigator;
/*   6:    */import org.jaxen.UnsupportedAxisException;
/*   7:    */
/*  55:    */class NodeComparator
/*  56:    */  implements Comparator
/*  57:    */{
/*  58:    */  private Navigator navigator;
/*  59:    */  
/*  60:    */  NodeComparator(Navigator navigator)
/*  61:    */  {
/*  62: 62 */    this.navigator = navigator;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public int compare(Object o1, Object o2)
/*  66:    */  {
/*  67: 67 */    if (o1 == o2) { return 0;
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    if (this.navigator == null) { return 0;
/*  71:    */    }
/*  72: 72 */    if ((isNonChild(o1)) && (isNonChild(o2))) {
/*  73:    */      try
/*  74:    */      {
/*  75: 75 */        Object p1 = this.navigator.getParentNode(o1);
/*  76: 76 */        Object p2 = this.navigator.getParentNode(o2);
/*  77:    */        
/*  78: 78 */        if (p1 == p2) {
/*  79: 79 */          if ((this.navigator.isNamespace(o1)) && (this.navigator.isAttribute(o2))) {
/*  80: 80 */            return -1;
/*  81:    */          }
/*  82: 82 */          if ((this.navigator.isNamespace(o2)) && (this.navigator.isAttribute(o1))) {
/*  83: 83 */            return 1;
/*  84:    */          }
/*  85: 85 */          if (this.navigator.isNamespace(o1)) {
/*  86: 86 */            String prefix1 = this.navigator.getNamespacePrefix(o1);
/*  87: 87 */            String prefix2 = this.navigator.getNamespacePrefix(o2);
/*  88: 88 */            return prefix1.compareTo(prefix2);
/*  89:    */          }
/*  90: 90 */          if (this.navigator.isAttribute(o1)) {
/*  91: 91 */            String name1 = this.navigator.getAttributeQName(o1);
/*  92: 92 */            String name2 = this.navigator.getAttributeQName(o2);
/*  93: 93 */            return name1.compareTo(name2);
/*  94:    */          }
/*  95:    */        }
/*  96:    */        
/*  97: 97 */        return compare(p1, p2);
/*  98:    */      }
/*  99:    */      catch (UnsupportedAxisException ex) {
/* 100:100 */        return 0;
/* 101:    */      }
/* 102:    */    }
/* 103:    */    
/* 104:    */    try
/* 105:    */    {
/* 106:106 */      int depth1 = getDepth(o1);
/* 107:107 */      int depth2 = getDepth(o2);
/* 108:    */      
/* 109:109 */      Object a1 = o1;
/* 110:110 */      Object a2 = o2;
/* 111:    */      
/* 112:112 */      while (depth1 > depth2) {
/* 113:113 */        a1 = this.navigator.getParentNode(a1);
/* 114:114 */        depth1--;
/* 115:    */      }
/* 116:116 */      if (a1 == o2) { return 1;
/* 117:    */      }
/* 118:118 */      while (depth2 > depth1) {
/* 119:119 */        a2 = this.navigator.getParentNode(a2);
/* 120:120 */        depth2--;
/* 121:    */      }
/* 122:122 */      if (a2 == o1) { return -1;
/* 123:    */      }
/* 124:    */      for (;;)
/* 125:    */      {
/* 126:126 */        Object p1 = this.navigator.getParentNode(a1);
/* 127:127 */        Object p2 = this.navigator.getParentNode(a2);
/* 128:128 */        if (p1 == p2) {
/* 129:129 */          return compareSiblings(a1, a2);
/* 130:    */        }
/* 131:131 */        a1 = p1;
/* 132:132 */        a2 = p2;
/* 133:    */      }
/* 134:    */      
/* 137:137 */      return 0;
/* 138:    */    }
/* 139:    */    catch (UnsupportedAxisException ex) {}
/* 140:    */  }
/* 141:    */  
/* 142:    */  private boolean isNonChild(Object o) {
/* 143:143 */    return (this.navigator.isAttribute(o)) || (this.navigator.isNamespace(o));
/* 144:    */  }
/* 145:    */  
/* 147:    */  private int compareSiblings(Object sib1, Object sib2)
/* 148:    */    throws UnsupportedAxisException
/* 149:    */  {
/* 150:150 */    if (isNonChild(sib1))
/* 151:151 */      return 1;
/* 152:152 */    if (isNonChild(sib2)) {
/* 153:153 */      return -1;
/* 154:    */    }
/* 155:    */    
/* 156:156 */    Iterator following = this.navigator.getFollowingSiblingAxisIterator(sib1);
/* 157:157 */    while (following.hasNext()) {
/* 158:158 */      Object next = following.next();
/* 159:159 */      if (next.equals(sib2)) return -1;
/* 160:    */    }
/* 161:161 */    return 1;
/* 162:    */  }
/* 163:    */  
/* 164:    */  private int getDepth(Object o)
/* 165:    */    throws UnsupportedAxisException
/* 166:    */  {
/* 167:167 */    int depth = 0;
/* 168:168 */    Object parent = o;
/* 169:    */    
/* 170:170 */    while ((parent = this.navigator.getParentNode(parent)) != null) {
/* 171:171 */      depth++;
/* 172:    */    }
/* 173:173 */    return depth;
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.NodeComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */