/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics.BulletStats;
/*   4:    */
/*  41:    */class CProfileNode
/*  42:    */{
/*  43:    */  protected String name;
/*  44:    */  protected int totalCalls;
/*  45:    */  protected float totalTime;
/*  46:    */  protected long startTime;
/*  47:    */  protected int recursionCounter;
/*  48:    */  protected CProfileNode parent;
/*  49:    */  protected CProfileNode child;
/*  50:    */  protected CProfileNode sibling;
/*  51:    */  
/*  52:    */  public CProfileNode(String name, CProfileNode parent)
/*  53:    */  {
/*  54: 54 */    this.name = name;
/*  55: 55 */    this.totalCalls = 0;
/*  56: 56 */    this.totalTime = 0.0F;
/*  57: 57 */    this.startTime = 0L;
/*  58: 58 */    this.recursionCounter = 0;
/*  59: 59 */    this.parent = parent;
/*  60: 60 */    this.child = null;
/*  61: 61 */    this.sibling = null;
/*  62:    */    
/*  63: 63 */    reset();
/*  64:    */  }
/*  65:    */  
/*  66:    */  public CProfileNode getSubNode(String name)
/*  67:    */  {
/*  68: 68 */    CProfileNode child = this.child;
/*  69: 69 */    while (child != null) {
/*  70: 70 */      if (child.name == name) {
/*  71: 71 */        return child;
/*  72:    */      }
/*  73: 73 */      child = child.sibling;
/*  74:    */    }
/*  75:    */    
/*  78: 78 */    CProfileNode node = new CProfileNode(name, this);
/*  79: 79 */    node.sibling = this.child;
/*  80: 80 */    this.child = node;
/*  81: 81 */    return node;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public CProfileNode getParent() {
/*  85: 85 */    return this.parent;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public CProfileNode getSibling() {
/*  89: 89 */    return this.sibling;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public CProfileNode getChild() {
/*  93: 93 */    return this.child;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public void cleanupMemory() {
/*  97: 97 */    this.child = null;
/*  98: 98 */    this.sibling = null;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void reset() {
/* 102:102 */    this.totalCalls = 0;
/* 103:103 */    this.totalTime = 0.0F;
/* 104:104 */    BulletStats.gProfileClock.reset();
/* 105:    */    
/* 106:106 */    if (this.child != null) {
/* 107:107 */      this.child.reset();
/* 108:    */    }
/* 109:109 */    if (this.sibling != null) {
/* 110:110 */      this.sibling.reset();
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 114:    */  public void call() {
/* 115:115 */    this.totalCalls += 1;
/* 116:116 */    if (this.recursionCounter++ == 0) {
/* 117:117 */      this.startTime = BulletStats.profileGetTicks();
/* 118:    */    }
/* 119:    */  }
/* 120:    */  
/* 121:    */  public boolean Return() {
/* 122:122 */    if ((--this.recursionCounter == 0) && (this.totalCalls != 0)) {
/* 123:123 */      long time = BulletStats.profileGetTicks();
/* 124:124 */      time -= this.startTime;
/* 125:125 */      this.totalTime += (float)time / BulletStats.profileGetTickRate();
/* 126:    */    }
/* 127:127 */    return this.recursionCounter == 0;
/* 128:    */  }
/* 129:    */  
/* 130:    */  public String getName() {
/* 131:131 */    return this.name;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public int getTotalCalls() {
/* 135:135 */    return this.totalCalls;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public float getTotalTime() {
/* 139:139 */    return this.totalTime;
/* 140:    */  }
/* 141:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.CProfileNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */