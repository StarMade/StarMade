/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*  15:    */public class CProfileIterator
/*  16:    */{
/*  17:    */  private CProfileNode currentParent;
/*  18:    */  
/*  30:    */  private CProfileNode currentChild;
/*  31:    */  
/*  43:    */  CProfileIterator(CProfileNode start)
/*  44:    */  {
/*  45: 45 */    this.currentParent = start;
/*  46: 46 */    this.currentChild = this.currentParent.getChild();
/*  47:    */  }
/*  48:    */  
/*  50:    */  public void first()
/*  51:    */  {
/*  52: 52 */    this.currentChild = this.currentParent.getChild();
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void next() {
/*  56: 56 */    this.currentChild = this.currentChild.getSibling();
/*  57:    */  }
/*  58:    */  
/*  59:    */  public boolean isDone() {
/*  60: 60 */    return this.currentChild == null;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public boolean isRoot() {
/*  64: 64 */    return this.currentParent.getParent() == null;
/*  65:    */  }
/*  66:    */  
/*  69:    */  public void enterChild(int index)
/*  70:    */  {
/*  71: 71 */    this.currentChild = this.currentParent.getChild();
/*  72: 72 */    while ((this.currentChild != null) && (index != 0)) {
/*  73: 73 */      index--;
/*  74: 74 */      this.currentChild = this.currentChild.getSibling();
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    if (this.currentChild != null) {
/*  78: 78 */      this.currentParent = this.currentChild;
/*  79: 79 */      this.currentChild = this.currentParent.getChild();
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  87:    */  public void enterParent()
/*  88:    */  {
/*  89: 89 */    if (this.currentParent.getParent() != null) {
/*  90: 90 */      this.currentParent = this.currentParent.getParent();
/*  91:    */    }
/*  92: 92 */    this.currentChild = this.currentParent.getChild();
/*  93:    */  }
/*  94:    */  
/*  96:    */  public String getCurrentName()
/*  97:    */  {
/*  98: 98 */    return this.currentChild.getName();
/*  99:    */  }
/* 100:    */  
/* 101:    */  public int getCurrentTotalCalls() {
/* 102:102 */    return this.currentChild.getTotalCalls();
/* 103:    */  }
/* 104:    */  
/* 105:    */  public float getCurrentTotalTime() {
/* 106:106 */    return this.currentChild.getTotalTime();
/* 107:    */  }
/* 108:    */  
/* 110:    */  public String getCurrentParentName()
/* 111:    */  {
/* 112:112 */    return this.currentParent.getName();
/* 113:    */  }
/* 114:    */  
/* 115:    */  public int getCurrentParentTotalCalls() {
/* 116:116 */    return this.currentParent.getTotalCalls();
/* 117:    */  }
/* 118:    */  
/* 119:    */  public float getCurrentParentTotalTime() {
/* 120:120 */    return this.currentParent.getTotalTime();
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.CProfileIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */