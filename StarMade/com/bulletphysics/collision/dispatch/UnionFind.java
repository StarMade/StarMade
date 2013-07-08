/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.MiscUtil;
/*   4:    */import com.bulletphysics.util.ObjectArrayList;
/*   5:    */import java.util.Comparator;
/*   6:    */
/*  34:    */public class UnionFind
/*  35:    */{
/*  36:    */  private final ObjectArrayList<Element> elements;
/*  37:    */  
/*  38:    */  public UnionFind()
/*  39:    */  {
/*  40: 40 */    this.elements = new ObjectArrayList();
/*  41:    */  }
/*  42:    */  
/*  46:    */  public void sortIslands()
/*  47:    */  {
/*  48: 48 */    int numElements = this.elements.size();
/*  49:    */    
/*  50: 50 */    for (int i = 0; i < numElements; i++) {
/*  51: 51 */      ((Element)this.elements.getQuick(i)).id = find(i);
/*  52: 52 */      ((Element)this.elements.getQuick(i)).sz = i;
/*  53:    */    }
/*  54:    */    
/*  61: 61 */    MiscUtil.quickSort(this.elements, elementComparator);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void reset(int N) {
/*  65: 65 */    allocate(N);
/*  66:    */    
/*  67: 67 */    for (int i = 0; i < N; i++) {
/*  68: 68 */      ((Element)this.elements.getQuick(i)).id = i;
/*  69: 69 */      ((Element)this.elements.getQuick(i)).sz = 1;
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  73:    */  public int getNumElements() {
/*  74: 74 */    return this.elements.size();
/*  75:    */  }
/*  76:    */  
/*  77:    */  public boolean isRoot(int x) {
/*  78: 78 */    return x == ((Element)this.elements.getQuick(x)).id;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Element getElement(int index) {
/*  82: 82 */    return (Element)this.elements.getQuick(index);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void allocate(int N) {
/*  86: 86 */    MiscUtil.resize(this.elements, N, Element.class);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void free() {
/*  90: 90 */    this.elements.clear();
/*  91:    */  }
/*  92:    */  
/*  93:    */  public int find(int p, int q) {
/*  94: 94 */    return find(p) == find(q) ? 1 : 0;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void unite(int p, int q) {
/*  98: 98 */    int i = find(p);int j = find(q);
/*  99: 99 */    if (i == j) {
/* 100:100 */      return;
/* 101:    */    }
/* 102:    */    
/* 114:114 */    ((Element)this.elements.getQuick(i)).id = j;
/* 115:115 */    ((Element)this.elements.getQuick(j)).sz += ((Element)this.elements.getQuick(i)).sz;
/* 116:    */  }
/* 117:    */  
/* 121:    */  public int find(int x)
/* 122:    */  {
/* 123:123 */    while (x != ((Element)this.elements.getQuick(x)).id)
/* 124:    */    {
/* 127:127 */      ((Element)this.elements.getQuick(x)).id = ((Element)this.elements.getQuick(((Element)this.elements.getQuick(x)).id)).id;
/* 128:    */      
/* 129:129 */      x = ((Element)this.elements.getQuick(x)).id;
/* 130:    */    }
/* 131:    */    
/* 133:133 */    return x;
/* 134:    */  }
/* 135:    */  
/* 143:143 */  private static final Comparator<Element> elementComparator = new Comparator() {
/* 144:    */    public int compare(UnionFind.Element o1, UnionFind.Element o2) {
/* 145:145 */      return o1.id < o2.id ? -1 : 1;
/* 146:    */    }
/* 147:    */  };
/* 148:    */  
/* 149:    */  public static class Element
/* 150:    */  {
/* 151:    */    public int id;
/* 152:    */    public int sz;
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.UnionFind
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */