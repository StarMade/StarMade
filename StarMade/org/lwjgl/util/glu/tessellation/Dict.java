/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  25:    */class Dict
/*  26:    */{
/*  27:    */  DictNode head;
/*  28:    */  
/*  49:    */  Object frame;
/*  50:    */  
/*  71:    */  DictLeq leq;
/*  72:    */  
/*  94:    */  static Dict dictNewDict(Object frame, DictLeq leq)
/*  95:    */  {
/*  96: 96 */    Dict dict = new Dict();
/*  97: 97 */    dict.head = new DictNode();
/*  98:    */    
/*  99: 99 */    dict.head.key = null;
/* 100:100 */    dict.head.next = dict.head;
/* 101:101 */    dict.head.prev = dict.head;
/* 102:    */    
/* 103:103 */    dict.frame = frame;
/* 104:104 */    dict.leq = leq;
/* 105:    */    
/* 106:106 */    return dict;
/* 107:    */  }
/* 108:    */  
/* 109:    */  static void dictDeleteDict(Dict dict) {
/* 110:110 */    dict.head = null;
/* 111:111 */    dict.frame = null;
/* 112:112 */    dict.leq = null;
/* 113:    */  }
/* 114:    */  
/* 115:    */  static DictNode dictInsert(Dict dict, Object key) {
/* 116:116 */    return dictInsertBefore(dict, dict.head, key);
/* 117:    */  }
/* 118:    */  
/* 119:    */  static DictNode dictInsertBefore(Dict dict, DictNode node, Object key) {
/* 120:    */    do {
/* 121:121 */      node = node.prev;
/* 122:122 */    } while ((node.key != null) && (!dict.leq.leq(dict.frame, node.key, key)));
/* 123:    */    
/* 124:124 */    DictNode newNode = new DictNode();
/* 125:125 */    newNode.key = key;
/* 126:126 */    newNode.next = node.next;
/* 127:127 */    node.next.prev = newNode;
/* 128:128 */    newNode.prev = node;
/* 129:129 */    node.next = newNode;
/* 130:    */    
/* 131:131 */    return newNode;
/* 132:    */  }
/* 133:    */  
/* 134:    */  static Object dictKey(DictNode aNode) {
/* 135:135 */    return aNode.key;
/* 136:    */  }
/* 137:    */  
/* 138:    */  static DictNode dictSucc(DictNode aNode) {
/* 139:139 */    return aNode.next;
/* 140:    */  }
/* 141:    */  
/* 142:    */  static DictNode dictPred(DictNode aNode) {
/* 143:143 */    return aNode.prev;
/* 144:    */  }
/* 145:    */  
/* 146:    */  static DictNode dictMin(Dict aDict) {
/* 147:147 */    return aDict.head.next;
/* 148:    */  }
/* 149:    */  
/* 150:    */  static DictNode dictMax(Dict aDict) {
/* 151:151 */    return aDict.head.prev;
/* 152:    */  }
/* 153:    */  
/* 154:    */  static void dictDelete(Dict dict, DictNode node) {
/* 155:155 */    node.next.prev = node.prev;
/* 156:156 */    node.prev.next = node.next;
/* 157:    */  }
/* 158:    */  
/* 159:    */  static DictNode dictSearch(Dict dict, Object key) {
/* 160:160 */    DictNode node = dict.head;
/* 161:    */    do
/* 162:    */    {
/* 163:163 */      node = node.next;
/* 164:164 */    } while ((node.key != null) && (!dict.leq.leq(dict.frame, key, node.key)));
/* 165:    */    
/* 166:166 */    return node;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public static abstract interface DictLeq
/* 170:    */  {
/* 171:    */    public abstract boolean leq(Object paramObject1, Object paramObject2, Object paramObject3);
/* 172:    */  }
/* 173:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Dict
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */