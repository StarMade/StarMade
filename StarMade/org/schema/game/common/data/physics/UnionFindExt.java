/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.MiscUtil;
/*   4:    */import com.bulletphysics.util.ObjectArrayList;
/*   5:    */import java.util.Comparator;
/*   6:    */
/*  34:    */public class UnionFindExt
/*  35:    */{
/*  36:    */  private final ObjectArrayList elements;
/*  37:    */  
/*  38:    */  public UnionFindExt()
/*  39:    */  {
/*  40: 40 */    this.elements = new ObjectArrayList();
/*  41:    */  }
/*  42:    */  
/*  46:    */  public void sortIslands()
/*  47:    */  {
/*  48: 48 */    int i = this.elements.size();
/*  49:    */    
/*  50: 50 */    for (int j = 0; j < i; j++) {
/*  51:    */      UnionFindExt.Element localElement;
/*  52: 52 */      (localElement = (UnionFindExt.Element)this.elements.getQuick(j)).id = find(j);
/*  53: 53 */      localElement.sz = j;
/*  54:    */    }
/*  55:    */    
/*  62: 62 */    MiscUtil.quickSort(this.elements, elementComparator);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void reset(int paramInt) {
/*  66: 66 */    allocate(paramInt);
/*  67:    */    
/*  68: 68 */    for (int i = 0; i < paramInt; i++) {
/*  69:    */      UnionFindExt.Element localElement;
/*  70: 70 */      (localElement = (UnionFindExt.Element)this.elements.getQuick(i)).id = i;
/*  71: 71 */      localElement.sz = 1;
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  75:    */  public int getNumElements() {
/*  76: 76 */    return this.elements.size();
/*  77:    */  }
/*  78:    */  
/*  79:    */  public boolean isRoot(int paramInt) {
/*  80: 80 */    return paramInt == ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public UnionFindExt.Element getElement(int paramInt) {
/*  84: 84 */    return (UnionFindExt.Element)this.elements.getQuick(paramInt);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void allocate(int paramInt) {
/*  88: 88 */    MiscUtil.resize(this.elements, paramInt, UnionFindExt.Element.class);
/*  89:    */    
/*  90: 90 */    while (this.elements.size() < paramInt) {
/*  91: 91 */      this.elements.add(new UnionFindExt.Element());
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    while (this.elements.size() > paramInt) {
/*  95: 95 */      this.elements.removeQuick(this.elements.size() - 1);
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void free() {
/* 100:100 */    this.elements.clear();
/* 101:    */  }
/* 102:    */  
/* 103:    */  public int find(int paramInt1, int paramInt2) {
/* 104:104 */    if (find(paramInt1) == find(paramInt2)) return 1; return 0;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void unite(int paramInt1, int paramInt2) {
/* 108:108 */    paramInt1 = find(paramInt1);paramInt2 = find(paramInt2);
/* 109:109 */    if (paramInt1 == paramInt2) {
/* 110:110 */      return;
/* 111:    */    }
/* 112:    */    
/* 124:124 */    ((UnionFindExt.Element)this.elements.getQuick(paramInt1)).id = paramInt2;
/* 125:125 */    ((UnionFindExt.Element)this.elements.getQuick(paramInt2)).sz += ((UnionFindExt.Element)this.elements.getQuick(paramInt1)).sz;
/* 126:    */  }
/* 127:    */  
/* 131:    */  public int find(int paramInt)
/* 132:    */  {
/* 133:133 */    while (paramInt != ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id)
/* 134:    */    {
/* 137:137 */      ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id = ((UnionFindExt.Element)this.elements.getQuick(((UnionFindExt.Element)this.elements.getQuick(paramInt)).id)).id;
/* 138:    */      
/* 139:139 */      paramInt = ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id;
/* 140:    */    }
/* 141:    */    
/* 143:143 */    return paramInt;
/* 144:    */  }
/* 145:    */  
/* 153:153 */  private static final Comparator elementComparator = new UnionFindExt.1();
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.UnionFindExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */