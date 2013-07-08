/*   1:    */package de.jarnbjo.util.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */
/*  33:    */public final class HuffmanNode
/*  34:    */{
/*  35:    */  private HuffmanNode parent;
/*  36: 36 */  private int depth = 0;
/*  37:    */  protected HuffmanNode o0;
/*  38:    */  protected HuffmanNode o1;
/*  39: 39 */  protected Integer value; private boolean full = false;
/*  40:    */  
/*  44:    */  public HuffmanNode()
/*  45:    */  {
/*  46: 46 */    this(null);
/*  47:    */  }
/*  48:    */  
/*  49:    */  protected HuffmanNode(HuffmanNode parent) {
/*  50: 50 */    this.parent = parent;
/*  51: 51 */    if (parent != null) {
/*  52: 52 */      this.depth = (parent.getDepth() + 1);
/*  53:    */    }
/*  54:    */  }
/*  55:    */  
/*  56:    */  protected HuffmanNode(HuffmanNode parent, int value) {
/*  57: 57 */    this(parent);
/*  58: 58 */    this.value = new Integer(value);
/*  59: 59 */    this.full = true;
/*  60:    */  }
/*  61:    */  
/*  62:    */  protected int read(BitInputStream bis) throws IOException {
/*  63: 63 */    HuffmanNode iter = this;
/*  64: 64 */    while (iter.value == null) {
/*  65: 65 */      iter = bis.getBit() ? iter.o1 : iter.o0;
/*  66:    */    }
/*  67: 67 */    return iter.value.intValue();
/*  68:    */  }
/*  69:    */  
/*  70:    */  protected HuffmanNode get0() {
/*  71: 71 */    return this.o0 == null ? set0(new HuffmanNode(this)) : this.o0;
/*  72:    */  }
/*  73:    */  
/*  74:    */  protected HuffmanNode get1() {
/*  75: 75 */    return this.o1 == null ? set1(new HuffmanNode(this)) : this.o1;
/*  76:    */  }
/*  77:    */  
/*  78:    */  protected Integer getValue() {
/*  79: 79 */    return this.value;
/*  80:    */  }
/*  81:    */  
/*  82:    */  private HuffmanNode getParent() {
/*  83: 83 */    return this.parent;
/*  84:    */  }
/*  85:    */  
/*  86:    */  protected int getDepth() {
/*  87: 87 */    return this.depth;
/*  88:    */  }
/*  89:    */  
/*  90:    */  private boolean isFull() {
/*  91: 91 */    return this.full = (this.o0 != null) && (this.o0.isFull()) && (this.o1 != null) && (this.o1.isFull()) ? 1 : 0;
/*  92:    */  }
/*  93:    */  
/*  94:    */  private HuffmanNode set0(HuffmanNode value) {
/*  95: 95 */    return this.o0 = value;
/*  96:    */  }
/*  97:    */  
/*  98:    */  private HuffmanNode set1(HuffmanNode value) {
/*  99: 99 */    return this.o1 = value;
/* 100:    */  }
/* 101:    */  
/* 102:    */  private void setValue(Integer value) {
/* 103:103 */    this.full = true;
/* 104:104 */    this.value = value;
/* 105:    */  }
/* 106:    */  
/* 114:    */  public boolean setNewValue(int depth, int value)
/* 115:    */  {
/* 116:116 */    if (isFull()) {
/* 117:117 */      return false;
/* 118:    */    }
/* 119:119 */    if (depth == 1) {
/* 120:120 */      if (this.o0 == null) {
/* 121:121 */        set0(new HuffmanNode(this, value));
/* 122:122 */        return true;
/* 123:    */      }
/* 124:124 */      if (this.o1 == null) {
/* 125:125 */        set1(new HuffmanNode(this, value));
/* 126:126 */        return true;
/* 127:    */      }
/* 128:    */      
/* 129:129 */      return false;
/* 130:    */    }
/* 131:    */    
/* 133:133 */    return get0().setNewValue(depth - 1, value) ? true : get1().setNewValue(depth - 1, value);
/* 134:    */  }
/* 135:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.util.io.HuffmanNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */