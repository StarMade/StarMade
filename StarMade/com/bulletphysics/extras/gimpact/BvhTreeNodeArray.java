/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/*  36:    */class BvhTreeNodeArray
/*  37:    */{
/*  38: 38 */  private int size = 0;
/*  39:    */  
/*  40: 40 */  private float[] bound = new float[0];
/*  41: 41 */  private int[] escapeIndexOrDataIndex = new int[0];
/*  42:    */  
/*  43:    */  public void clear() {
/*  44: 44 */    this.size = 0;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public void resize(int newSize) {
/*  48: 48 */    float[] newBound = new float[newSize * 6];
/*  49: 49 */    int[] newEIODI = new int[newSize];
/*  50:    */    
/*  51: 51 */    System.arraycopy(this.bound, 0, newBound, 0, this.size * 6);
/*  52: 52 */    System.arraycopy(this.escapeIndexOrDataIndex, 0, newEIODI, 0, this.size);
/*  53:    */    
/*  54: 54 */    this.bound = newBound;
/*  55: 55 */    this.escapeIndexOrDataIndex = newEIODI;
/*  56:    */    
/*  57: 57 */    this.size = newSize;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void set(int destIdx, BvhTreeNodeArray array, int srcIdx) {
/*  61: 61 */    int dpos = destIdx * 6;
/*  62: 62 */    int spos = srcIdx * 6;
/*  63:    */    
/*  64: 64 */    this.bound[(dpos + 0)] = array.bound[(spos + 0)];
/*  65: 65 */    this.bound[(dpos + 1)] = array.bound[(spos + 1)];
/*  66: 66 */    this.bound[(dpos + 2)] = array.bound[(spos + 2)];
/*  67: 67 */    this.bound[(dpos + 3)] = array.bound[(spos + 3)];
/*  68: 68 */    this.bound[(dpos + 4)] = array.bound[(spos + 4)];
/*  69: 69 */    this.bound[(dpos + 5)] = array.bound[(spos + 5)];
/*  70: 70 */    this.escapeIndexOrDataIndex[destIdx] = array.escapeIndexOrDataIndex[srcIdx];
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void set(int destIdx, BvhDataArray array, int srcIdx) {
/*  74: 74 */    int dpos = destIdx * 6;
/*  75: 75 */    int spos = srcIdx * 6;
/*  76:    */    
/*  77: 77 */    this.bound[(dpos + 0)] = array.bound[(spos + 0)];
/*  78: 78 */    this.bound[(dpos + 1)] = array.bound[(spos + 1)];
/*  79: 79 */    this.bound[(dpos + 2)] = array.bound[(spos + 2)];
/*  80: 80 */    this.bound[(dpos + 3)] = array.bound[(spos + 3)];
/*  81: 81 */    this.bound[(dpos + 4)] = array.bound[(spos + 4)];
/*  82: 82 */    this.bound[(dpos + 5)] = array.bound[(spos + 5)];
/*  83: 83 */    this.escapeIndexOrDataIndex[destIdx] = array.data[srcIdx];
/*  84:    */  }
/*  85:    */  
/*  86:    */  public BoxCollision.AABB getBound(int nodeIndex, BoxCollision.AABB out) {
/*  87: 87 */    int pos = nodeIndex * 6;
/*  88: 88 */    out.min.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
/*  89: 89 */    out.max.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
/*  90: 90 */    return out;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void setBound(int nodeIndex, BoxCollision.AABB aabb) {
/*  94: 94 */    int pos = nodeIndex * 6;
/*  95: 95 */    this.bound[(pos + 0)] = aabb.min.x;
/*  96: 96 */    this.bound[(pos + 1)] = aabb.min.y;
/*  97: 97 */    this.bound[(pos + 2)] = aabb.min.z;
/*  98: 98 */    this.bound[(pos + 3)] = aabb.max.x;
/*  99: 99 */    this.bound[(pos + 4)] = aabb.max.y;
/* 100:100 */    this.bound[(pos + 5)] = aabb.max.z;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isLeafNode(int nodeIndex)
/* 104:    */  {
/* 105:105 */    return this.escapeIndexOrDataIndex[nodeIndex] >= 0;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public int getEscapeIndex(int nodeIndex)
/* 109:    */  {
/* 110:110 */    return -this.escapeIndexOrDataIndex[nodeIndex];
/* 111:    */  }
/* 112:    */  
/* 113:    */  public void setEscapeIndex(int nodeIndex, int index) {
/* 114:114 */    this.escapeIndexOrDataIndex[nodeIndex] = (-index);
/* 115:    */  }
/* 116:    */  
/* 117:    */  public int getDataIndex(int nodeIndex)
/* 118:    */  {
/* 119:119 */    return this.escapeIndexOrDataIndex[nodeIndex];
/* 120:    */  }
/* 121:    */  
/* 122:    */  public void setDataIndex(int nodeIndex, int index) {
/* 123:123 */    this.escapeIndexOrDataIndex[nodeIndex] = index;
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhTreeNodeArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */