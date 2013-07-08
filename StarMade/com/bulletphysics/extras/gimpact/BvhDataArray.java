/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/*  37:    */class BvhDataArray
/*  38:    */{
/*  39: 39 */  private int size = 0;
/*  40:    */  
/*  41: 41 */  float[] bound = new float[0];
/*  42: 42 */  int[] data = new int[0];
/*  43:    */  
/*  44:    */  public int size() {
/*  45: 45 */    return this.size;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public void resize(int newSize) {
/*  49: 49 */    float[] newBound = new float[newSize * 6];
/*  50: 50 */    int[] newData = new int[newSize];
/*  51:    */    
/*  52: 52 */    System.arraycopy(this.bound, 0, newBound, 0, this.size * 6);
/*  53: 53 */    System.arraycopy(this.data, 0, newData, 0, this.size);
/*  54:    */    
/*  55: 55 */    this.bound = newBound;
/*  56: 56 */    this.data = newData;
/*  57:    */    
/*  58: 58 */    this.size = newSize;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void swap(int idx1, int idx2) {
/*  62: 62 */    int pos1 = idx1 * 6;
/*  63: 63 */    int pos2 = idx2 * 6;
/*  64:    */    
/*  65: 65 */    float b0 = this.bound[(pos1 + 0)];
/*  66: 66 */    float b1 = this.bound[(pos1 + 1)];
/*  67: 67 */    float b2 = this.bound[(pos1 + 2)];
/*  68: 68 */    float b3 = this.bound[(pos1 + 3)];
/*  69: 69 */    float b4 = this.bound[(pos1 + 4)];
/*  70: 70 */    float b5 = this.bound[(pos1 + 5)];
/*  71: 71 */    int d = this.data[idx1];
/*  72:    */    
/*  73: 73 */    this.bound[(pos1 + 0)] = this.bound[(pos2 + 0)];
/*  74: 74 */    this.bound[(pos1 + 1)] = this.bound[(pos2 + 1)];
/*  75: 75 */    this.bound[(pos1 + 2)] = this.bound[(pos2 + 2)];
/*  76: 76 */    this.bound[(pos1 + 3)] = this.bound[(pos2 + 3)];
/*  77: 77 */    this.bound[(pos1 + 4)] = this.bound[(pos2 + 4)];
/*  78: 78 */    this.bound[(pos1 + 5)] = this.bound[(pos2 + 5)];
/*  79: 79 */    this.data[idx1] = this.data[idx2];
/*  80:    */    
/*  81: 81 */    this.bound[(pos2 + 0)] = b0;
/*  82: 82 */    this.bound[(pos2 + 1)] = b1;
/*  83: 83 */    this.bound[(pos2 + 2)] = b2;
/*  84: 84 */    this.bound[(pos2 + 3)] = b3;
/*  85: 85 */    this.bound[(pos2 + 4)] = b4;
/*  86: 86 */    this.bound[(pos2 + 5)] = b5;
/*  87: 87 */    this.data[idx2] = d;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public BoxCollision.AABB getBound(int idx, BoxCollision.AABB out) {
/*  91: 91 */    int pos = idx * 6;
/*  92: 92 */    out.min.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
/*  93: 93 */    out.max.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
/*  94: 94 */    return out;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public Vector3f getBoundMin(int idx, Vector3f out) {
/*  98: 98 */    int pos = idx * 6;
/*  99: 99 */    out.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
/* 100:100 */    return out;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public Vector3f getBoundMax(int idx, Vector3f out) {
/* 104:104 */    int pos = idx * 6;
/* 105:105 */    out.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
/* 106:106 */    return out;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public void setBound(int idx, BoxCollision.AABB aabb) {
/* 110:110 */    int pos = idx * 6;
/* 111:111 */    this.bound[(pos + 0)] = aabb.min.x;
/* 112:112 */    this.bound[(pos + 1)] = aabb.min.y;
/* 113:113 */    this.bound[(pos + 2)] = aabb.min.z;
/* 114:114 */    this.bound[(pos + 3)] = aabb.max.x;
/* 115:115 */    this.bound[(pos + 4)] = aabb.max.y;
/* 116:116 */    this.bound[(pos + 5)] = aabb.max.z;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public int getData(int idx) {
/* 120:120 */    return this.data[idx];
/* 121:    */  }
/* 122:    */  
/* 123:    */  public void setData(int idx, int value) {
/* 124:124 */    this.data[idx] = value;
/* 125:    */  }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhDataArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */