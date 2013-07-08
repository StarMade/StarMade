/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/*  39:    */public class AxisSweep3
/*  40:    */  extends AxisSweep3Internal
/*  41:    */{
/*  42:    */  public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax)
/*  43:    */  {
/*  44: 44 */    this(worldAabbMin, worldAabbMax, 16384, null);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles) {
/*  48: 48 */    this(worldAabbMin, worldAabbMax, maxHandles, null);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles, OverlappingPairCache pairCache) {
/*  52: 52 */    super(worldAabbMin, worldAabbMax, 65534, 65535, maxHandles, pairCache);
/*  53:    */    
/*  54: 54 */    assert ((maxHandles > 1) && (maxHandles < 32767));
/*  55:    */  }
/*  56:    */  
/*  57:    */  protected AxisSweep3Internal.EdgeArray createEdgeArray(int size)
/*  58:    */  {
/*  59: 59 */    return new EdgeArrayImpl(size);
/*  60:    */  }
/*  61:    */  
/*  62:    */  protected AxisSweep3Internal.Handle createHandle()
/*  63:    */  {
/*  64: 64 */    return new HandleImpl();
/*  65:    */  }
/*  66:    */  
/*  67:    */  protected int getMask() {
/*  68: 68 */    return 65535;
/*  69:    */  }
/*  70:    */  
/*  71:    */  protected static class EdgeArrayImpl extends AxisSweep3Internal.EdgeArray {
/*  72:    */    private short[] pos;
/*  73:    */    private short[] handle;
/*  74:    */    
/*  75:    */    public EdgeArrayImpl(int size) {
/*  76: 76 */      this.pos = new short[size];
/*  77: 77 */      this.handle = new short[size];
/*  78:    */    }
/*  79:    */    
/*  80:    */    public void swap(int idx1, int idx2)
/*  81:    */    {
/*  82: 82 */      short tmpPos = this.pos[idx1];
/*  83: 83 */      short tmpHandle = this.handle[idx1];
/*  84:    */      
/*  85: 85 */      this.pos[idx1] = this.pos[idx2];
/*  86: 86 */      this.handle[idx1] = this.handle[idx2];
/*  87:    */      
/*  88: 88 */      this.pos[idx2] = tmpPos;
/*  89: 89 */      this.handle[idx2] = tmpHandle;
/*  90:    */    }
/*  91:    */    
/*  92:    */    public void set(int dest, int src)
/*  93:    */    {
/*  94: 94 */      this.pos[dest] = this.pos[src];
/*  95: 95 */      this.handle[dest] = this.handle[src];
/*  96:    */    }
/*  97:    */    
/*  98:    */    public int getPos(int index)
/*  99:    */    {
/* 100:100 */      return this.pos[index] & 0xFFFF;
/* 101:    */    }
/* 102:    */    
/* 103:    */    public void setPos(int index, int value)
/* 104:    */    {
/* 105:105 */      this.pos[index] = ((short)value);
/* 106:    */    }
/* 107:    */    
/* 108:    */    public int getHandle(int index)
/* 109:    */    {
/* 110:110 */      return this.handle[index] & 0xFFFF;
/* 111:    */    }
/* 112:    */    
/* 113:    */    public void setHandle(int index, int value)
/* 114:    */    {
/* 115:115 */      this.handle[index] = ((short)value);
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 119:    */  protected static class HandleImpl extends AxisSweep3Internal.Handle
/* 120:    */  {
/* 121:    */    private short minEdges0;
/* 122:    */    private short minEdges1;
/* 123:    */    private short minEdges2;
/* 124:    */    private short maxEdges0;
/* 125:    */    private short maxEdges1;
/* 126:    */    private short maxEdges2;
/* 127:    */    
/* 128:    */    public int getMinEdges(int edgeIndex)
/* 129:    */    {
/* 130:130 */      switch (edgeIndex) {
/* 131:    */      case 0: default: 
/* 132:132 */        return this.minEdges0 & 0xFFFF;
/* 133:133 */      case 1:  return this.minEdges1 & 0xFFFF; }
/* 134:134 */      return this.minEdges2 & 0xFFFF;
/* 135:    */    }
/* 136:    */    
/* 138:    */    public void setMinEdges(int edgeIndex, int value)
/* 139:    */    {
/* 140:140 */      switch (edgeIndex) {
/* 141:141 */      case 0:  this.minEdges0 = ((short)value);break;
/* 142:142 */      case 1:  this.minEdges1 = ((short)value);break;
/* 143:143 */      case 2:  this.minEdges2 = ((short)value);
/* 144:    */      }
/* 145:    */    }
/* 146:    */    
/* 147:    */    public int getMaxEdges(int edgeIndex)
/* 148:    */    {
/* 149:149 */      switch (edgeIndex) {
/* 150:    */      case 0: default: 
/* 151:151 */        return this.maxEdges0 & 0xFFFF;
/* 152:152 */      case 1:  return this.maxEdges1 & 0xFFFF; }
/* 153:153 */      return this.maxEdges2 & 0xFFFF;
/* 154:    */    }
/* 155:    */    
/* 157:    */    public void setMaxEdges(int edgeIndex, int value)
/* 158:    */    {
/* 159:159 */      switch (edgeIndex) {
/* 160:160 */      case 0:  this.maxEdges0 = ((short)value);break;
/* 161:161 */      case 1:  this.maxEdges1 = ((short)value);break;
/* 162:162 */      case 2:  this.maxEdges2 = ((short)value);
/* 163:    */      }
/* 164:    */    }
/* 165:    */  }
/* 166:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */