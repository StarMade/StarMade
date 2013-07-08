package org.lwjgl.util.glu.tessellation;

abstract class PriorityQ
{
  public static final int INIT_SIZE = 32;
  
  public static boolean LEQ(Leq leq, Object local_x, Object local_y)
  {
    return Geom.VertLeq((GLUvertex)local_x, (GLUvertex)local_y);
  }
  
  static PriorityQ pqNewPriorityQ(Leq leq)
  {
    return new PriorityQSort(leq);
  }
  
  abstract void pqDeletePriorityQ();
  
  abstract boolean pqInit();
  
  abstract int pqInsert(Object paramObject);
  
  abstract Object pqExtractMin();
  
  abstract void pqDelete(int paramInt);
  
  abstract Object pqMinimum();
  
  abstract boolean pqIsEmpty();
  
  public static abstract interface Leq
  {
    public abstract boolean leq(Object paramObject1, Object paramObject2);
  }
  
  public static class PQhandleElem
  {
    Object key;
    int node;
  }
  
  public static class PQnode
  {
    int handle;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQ
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */