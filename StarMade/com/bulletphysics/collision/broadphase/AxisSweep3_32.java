/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/*  36:    */public class AxisSweep3_32
/*  37:    */  extends AxisSweep3Internal
/*  38:    */{
/*  39:    */  public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax)
/*  40:    */  {
/*  41: 41 */    this(worldAabbMin, worldAabbMax, 1500000, null);
/*  42:    */  }
/*  43:    */  
/*  44:    */  public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles) {
/*  45: 45 */    this(worldAabbMin, worldAabbMax, maxHandles, null);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles, OverlappingPairCache pairCache) {
/*  49: 49 */    super(worldAabbMin, worldAabbMax, -2, 2147483647, maxHandles, pairCache);
/*  50:    */    
/*  51: 51 */    assert ((maxHandles > 1) && (maxHandles < 2147483647));
/*  52:    */  }
/*  53:    */  
/*  54:    */  protected AxisSweep3Internal.EdgeArray createEdgeArray(int size)
/*  55:    */  {
/*  56: 56 */    return new EdgeArrayImpl(size);
/*  57:    */  }
/*  58:    */  
/*  59:    */  protected AxisSweep3Internal.Handle createHandle()
/*  60:    */  {
/*  61: 61 */    return new HandleImpl();
/*  62:    */  }
/*  63:    */  
/*  64:    */  protected int getMask() {
/*  65: 65 */    return -1;
/*  66:    */  }
/*  67:    */  
/*  68:    */  protected static class EdgeArrayImpl extends AxisSweep3Internal.EdgeArray {
/*  69:    */    private int[] pos;
/*  70:    */    private int[] handle;
/*  71:    */    
/*  72:    */    public EdgeArrayImpl(int size) {
/*  73: 73 */      this.pos = new int[size];
/*  74: 74 */      this.handle = new int[size];
/*  75:    */    }
/*  76:    */    
/*  77:    */    public void swap(int idx1, int idx2)
/*  78:    */    {
/*  79: 79 */      int tmpPos = this.pos[idx1];
/*  80: 80 */      int tmpHandle = this.handle[idx1];
/*  81:    */      
/*  82: 82 */      this.pos[idx1] = this.pos[idx2];
/*  83: 83 */      this.handle[idx1] = this.handle[idx2];
/*  84:    */      
/*  85: 85 */      this.pos[idx2] = tmpPos;
/*  86: 86 */      this.handle[idx2] = tmpHandle;
/*  87:    */    }
/*  88:    */    
/*  89:    */    public void set(int dest, int src)
/*  90:    */    {
/*  91: 91 */      this.pos[dest] = this.pos[src];
/*  92: 92 */      this.handle[dest] = this.handle[src];
/*  93:    */    }
/*  94:    */    
/*  95:    */    public int getPos(int index)
/*  96:    */    {
/*  97: 97 */      return this.pos[index];
/*  98:    */    }
/*  99:    */    
/* 100:    */    public void setPos(int index, int value)
/* 101:    */    {
/* 102:102 */      this.pos[index] = value;
/* 103:    */    }
/* 104:    */    
/* 105:    */    public int getHandle(int index)
/* 106:    */    {
/* 107:107 */      return this.handle[index];
/* 108:    */    }
/* 109:    */    
/* 110:    */    public void setHandle(int index, int value)
/* 111:    */    {
/* 112:112 */      this.handle[index] = value;
/* 113:    */    }
/* 114:    */  }
/* 115:    */  
/* 116:    */  protected static class HandleImpl extends AxisSweep3Internal.Handle
/* 117:    */  {
/* 118:    */    private int minEdges0;
/* 119:    */    private int minEdges1;
/* 120:    */    private int minEdges2;
/* 121:    */    private int maxEdges0;
/* 122:    */    private int maxEdges1;
/* 123:    */    private int maxEdges2;
/* 124:    */    
/* 125:    */    public int getMinEdges(int edgeIndex)
/* 126:    */    {
/* 127:127 */      switch (edgeIndex) {
/* 128:    */      case 0: default: 
/* 129:129 */        return this.minEdges0;
/* 130:130 */      case 1:  return this.minEdges1; }
/* 131:131 */      return this.minEdges2;
/* 132:    */    }
/* 133:    */    
/* 135:    */    public void setMinEdges(int edgeIndex, int value)
/* 136:    */    {
/* 137:137 */      switch (edgeIndex) {
/* 138:138 */      case 0:  this.minEdges0 = value;break;
/* 139:139 */      case 1:  this.minEdges1 = value;break;
/* 140:140 */      case 2:  this.minEdges2 = value;
/* 141:    */      }
/* 142:    */    }
/* 143:    */    
/* 144:    */    public int getMaxEdges(int edgeIndex)
/* 145:    */    {
/* 146:146 */      switch (edgeIndex) {
/* 147:    */      case 0: default: 
/* 148:148 */        return this.maxEdges0;
/* 149:149 */      case 1:  return this.maxEdges1; }
/* 150:150 */      return this.maxEdges2;
/* 151:    */    }
/* 152:    */    
/* 154:    */    public void setMaxEdges(int edgeIndex, int value)
/* 155:    */    {
/* 156:156 */      switch (edgeIndex) {
/* 157:157 */      case 0:  this.maxEdges0 = value;break;
/* 158:158 */      case 1:  this.maxEdges1 = value;break;
/* 159:159 */      case 2:  this.maxEdges2 = value;
/* 160:    */      }
/* 161:    */    }
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3_32
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */