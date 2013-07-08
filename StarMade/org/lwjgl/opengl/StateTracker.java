/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */
/*  43:    */final class StateTracker
/*  44:    */{
/*  45:    */  private ReferencesStack references_stack;
/*  46:    */  private final StateStack attrib_stack;
/*  47:    */  private boolean insideBeginEnd;
/*  48: 48 */  private final FastIntMap<VAOState> vaoMap = new FastIntMap();
/*  49:    */  
/*  50:    */  StateTracker() {
/*  51: 51 */    this.attrib_stack = new StateStack(0);
/*  52:    */  }
/*  53:    */  
/*  54:    */  void init()
/*  55:    */  {
/*  56: 56 */    this.references_stack = new ReferencesStack();
/*  57:    */  }
/*  58:    */  
/*  59:    */  static void setBeginEnd(ContextCapabilities caps, boolean inside) {
/*  60: 60 */    caps.tracker.insideBeginEnd = inside;
/*  61:    */  }
/*  62:    */  
/*  63:    */  boolean isBeginEnd() {
/*  64: 64 */    return this.insideBeginEnd;
/*  65:    */  }
/*  66:    */  
/*  67:    */  static void popAttrib(ContextCapabilities caps) {
/*  68: 68 */    caps.tracker.doPopAttrib();
/*  69:    */  }
/*  70:    */  
/*  71:    */  private void doPopAttrib() {
/*  72: 72 */    this.references_stack.popState(this.attrib_stack.popState());
/*  73:    */  }
/*  74:    */  
/*  75:    */  static void pushAttrib(ContextCapabilities caps, int mask) {
/*  76: 76 */    caps.tracker.doPushAttrib(mask);
/*  77:    */  }
/*  78:    */  
/*  79:    */  private void doPushAttrib(int mask) {
/*  80: 80 */    this.attrib_stack.pushState(mask);
/*  81: 81 */    this.references_stack.pushState();
/*  82:    */  }
/*  83:    */  
/*  84:    */  static References getReferences(ContextCapabilities caps) {
/*  85: 85 */    return caps.tracker.references_stack.getReferences();
/*  86:    */  }
/*  87:    */  
/*  88:    */  static void bindBuffer(ContextCapabilities caps, int target, int buffer) {
/*  89: 89 */    BaseReferences references = getReferences(caps);
/*  90: 90 */    switch (target) {
/*  91:    */    case 34962: 
/*  92: 92 */      references.arrayBuffer = buffer;
/*  93: 93 */      break;
/*  94:    */    
/*  96:    */    case 34963: 
/*  97: 97 */      if (references.vertexArrayObject != 0) {
/*  98: 98 */        ((VAOState)caps.tracker.vaoMap.get(references.vertexArrayObject)).elementArrayBuffer = buffer;
/*  99:    */      } else
/* 100:100 */        references.elementArrayBuffer = buffer;
/* 101:101 */      break;
/* 102:    */    case 35051: 
/* 103:103 */      references.pixelPackBuffer = buffer;
/* 104:104 */      break;
/* 105:    */    case 35052: 
/* 106:106 */      references.pixelUnpackBuffer = buffer;
/* 107:107 */      break;
/* 108:    */    case 36671: 
/* 109:109 */      references.indirectBuffer = buffer;
/* 110:    */    }
/* 111:    */  }
/* 112:    */  
/* 113:    */  static void bindVAO(ContextCapabilities caps, int array)
/* 114:    */  {
/* 115:115 */    FastIntMap<VAOState> vaoMap = caps.tracker.vaoMap;
/* 116:116 */    if (!vaoMap.containsKey(array)) {
/* 117:117 */      vaoMap.put(array, new VAOState(null));
/* 118:    */    }
/* 119:119 */    getReferences(caps).vertexArrayObject = array;
/* 120:    */  }
/* 121:    */  
/* 122:    */  static void deleteVAO(ContextCapabilities caps, IntBuffer arrays) {
/* 123:123 */    for (int i = arrays.position(); i < arrays.limit(); i++)
/* 124:124 */      deleteVAO(caps, arrays.get(i));
/* 125:    */  }
/* 126:    */  
/* 127:    */  static void deleteVAO(ContextCapabilities caps, int array) {
/* 128:128 */    caps.tracker.vaoMap.remove(array);
/* 129:    */    
/* 130:130 */    BaseReferences references = getReferences(caps);
/* 131:131 */    if (references.vertexArrayObject == array) {
/* 132:132 */      references.vertexArrayObject = 0;
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 141:    */  static int getElementArrayBufferBound(ContextCapabilities caps)
/* 142:    */  {
/* 143:143 */    BaseReferences references = getReferences(caps);
/* 144:    */    
/* 145:145 */    if (references.vertexArrayObject == 0) {
/* 146:146 */      return references.elementArrayBuffer;
/* 147:    */    }
/* 148:148 */    return ((VAOState)caps.tracker.vaoMap.get(references.vertexArrayObject)).elementArrayBuffer;
/* 149:    */  }
/* 150:    */  
/* 151:    */  private static class VAOState
/* 152:    */  {
/* 153:    */    int elementArrayBuffer;
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.StateTracker
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */