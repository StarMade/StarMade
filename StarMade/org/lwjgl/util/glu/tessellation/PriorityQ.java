/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  54:    */abstract class PriorityQ
/*  55:    */{
/*  56:    */  public static final int INIT_SIZE = 32;
/*  57:    */  
/* 109:    */  public static boolean LEQ(Leq leq, Object x, Object y)
/* 110:    */  {
/* 111:111 */    return Geom.VertLeq((GLUvertex)x, (GLUvertex)y);
/* 112:    */  }
/* 113:    */  
/* 114:    */  static PriorityQ pqNewPriorityQ(Leq leq) {
/* 115:115 */    return new PriorityQSort(leq);
/* 116:    */  }
/* 117:    */  
/* 118:    */  abstract void pqDeletePriorityQ();
/* 119:    */  
/* 120:    */  abstract boolean pqInit();
/* 121:    */  
/* 122:    */  abstract int pqInsert(Object paramObject);
/* 123:    */  
/* 124:    */  abstract Object pqExtractMin();
/* 125:    */  
/* 126:    */  abstract void pqDelete(int paramInt);
/* 127:    */  
/* 128:    */  abstract Object pqMinimum();
/* 129:    */  
/* 130:    */  abstract boolean pqIsEmpty();
/* 131:    */  
/* 132:    */  public static abstract interface Leq
/* 133:    */  {
/* 134:    */    public abstract boolean leq(Object paramObject1, Object paramObject2);
/* 135:    */  }
/* 136:    */  
/* 137:    */  public static class PQhandleElem
/* 138:    */  {
/* 139:    */    Object key;
/* 140:    */    int node;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public static class PQnode
/* 144:    */  {
/* 145:    */    int handle;
/* 146:    */  }
/* 147:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQ
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */