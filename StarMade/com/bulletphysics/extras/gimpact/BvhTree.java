/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.VectorUtil;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/*  39:    */class BvhTree
/*  40:    */{
/*  41: 41 */  protected int num_nodes = 0;
/*  42: 42 */  protected BvhTreeNodeArray node_array = new BvhTreeNodeArray();
/*  43:    */  
/*  44:    */  protected int _calc_splitting_axis(BvhDataArray arg1, int arg2, int arg3) {
/*  45: 45 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  46: 46 */      means.set(0.0F, 0.0F, 0.0F);
/*  47: 47 */      Vector3f variance = localStack.get$javax$vecmath$Vector3f();
/*  48: 48 */      variance.set(0.0F, 0.0F, 0.0F);
/*  49:    */      
/*  50: 50 */      int numIndices = endIndex - startIndex;
/*  51:    */      
/*  52: 52 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  53: 53 */      Vector3f diff2 = localStack.get$javax$vecmath$Vector3f();
/*  54:    */      
/*  55: 55 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  56: 56 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  57:    */      
/*  58: 58 */      for (int i = startIndex; i < endIndex; i++) {
/*  59: 59 */        primitive_boxes.getBoundMax(i, tmp1);
/*  60: 60 */        primitive_boxes.getBoundMin(i, tmp2);
/*  61: 61 */        center.add(tmp1, tmp2);
/*  62: 62 */        center.scale(0.5F);
/*  63: 63 */        means.add(center);
/*  64:    */      }
/*  65: 65 */      means.scale(1.0F / numIndices);
/*  66:    */      
/*  67: 67 */      for (int i = startIndex; i < endIndex; i++) {
/*  68: 68 */        primitive_boxes.getBoundMax(i, tmp1);
/*  69: 69 */        primitive_boxes.getBoundMin(i, tmp2);
/*  70: 70 */        center.add(tmp1, tmp2);
/*  71: 71 */        center.scale(0.5F);
/*  72: 72 */        diff2.sub(center, means);
/*  73: 73 */        VectorUtil.mul(diff2, diff2, diff2);
/*  74: 74 */        variance.add(diff2);
/*  75:    */      }
/*  76: 76 */      variance.scale(1.0F / (numIndices - 1));
/*  77:    */      
/*  78: 78 */      return VectorUtil.maxAxis(variance); } finally { localStack.pop$javax$vecmath$Vector3f();
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  82: 82 */  protected int _sort_and_calc_splitting_index(BvhDataArray arg1, int arg2, int arg3, int arg4) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();int splitIndex = startIndex;
/*  83: 83 */      int numIndices = endIndex - startIndex;
/*  84:    */      
/*  86: 86 */      float splitValue = 0.0F;
/*  87:    */      
/*  88: 88 */      Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  89: 89 */      means.set(0.0F, 0.0F, 0.0F);
/*  90:    */      
/*  91: 91 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  92:    */      
/*  93: 93 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  94: 94 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  95:    */      
/*  96: 96 */      for (int i = startIndex; i < endIndex; i++) {
/*  97: 97 */        primitive_boxes.getBoundMax(i, tmp1);
/*  98: 98 */        primitive_boxes.getBoundMin(i, tmp2);
/*  99: 99 */        center.add(tmp1, tmp2);
/* 100:100 */        center.scale(0.5F);
/* 101:101 */        means.add(center);
/* 102:    */      }
/* 103:103 */      means.scale(1.0F / numIndices);
/* 104:    */      
/* 105:105 */      splitValue = VectorUtil.getCoord(means, splitAxis);
/* 106:    */      
/* 108:108 */      for (int i = startIndex; i < endIndex; i++) {
/* 109:109 */        primitive_boxes.getBoundMax(i, tmp1);
/* 110:110 */        primitive_boxes.getBoundMin(i, tmp2);
/* 111:111 */        center.add(tmp1, tmp2);
/* 112:112 */        center.scale(0.5F);
/* 113:    */        
/* 114:114 */        if (VectorUtil.getCoord(center, splitAxis) > splitValue)
/* 115:    */        {
/* 116:116 */          primitive_boxes.swap(i, splitIndex);
/* 117:    */          
/* 118:118 */          splitIndex++;
/* 119:    */        }
/* 120:    */      }
/* 121:    */      
/* 131:131 */      int rangeBalancedIndices = numIndices / 3;
/* 132:132 */      boolean unbalanced = (splitIndex <= startIndex + rangeBalancedIndices) || (splitIndex >= endIndex - 1 - rangeBalancedIndices);
/* 133:    */      
/* 134:134 */      if (unbalanced) {
/* 135:135 */        splitIndex = startIndex + (numIndices >> 1);
/* 136:    */      }
/* 137:    */      
/* 138:138 */      boolean unbal = (splitIndex == startIndex) || (splitIndex == endIndex);
/* 139:139 */      assert (!unbal);
/* 140:    */      
/* 141:141 */      return splitIndex; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 145:145 */  protected void _build_sub_tree(BvhDataArray arg1, int arg2, int arg3) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();int curIndex = this.num_nodes;
/* 146:146 */      this.num_nodes += 1;
/* 147:    */      
/* 148:148 */      assert (endIndex - startIndex > 0);
/* 149:    */      
/* 150:150 */      if (endIndex - startIndex == 1)
/* 151:    */      {
/* 154:154 */        this.node_array.set(curIndex, primitive_boxes, startIndex);
/* 155:    */        
/* 156:156 */        return;
/* 157:    */      }
/* 158:    */      
/* 161:161 */      int splitIndex = _calc_splitting_axis(primitive_boxes, startIndex, endIndex);
/* 162:    */      
/* 163:163 */      splitIndex = _sort_and_calc_splitting_index(primitive_boxes, startIndex, endIndex, splitIndex);
/* 164:    */      
/* 167:167 */      BoxCollision.AABB node_bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 168:168 */      BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 169:    */      
/* 170:170 */      node_bound.invalidate();
/* 171:    */      
/* 172:172 */      for (int i = startIndex; i < endIndex; i++) {
/* 173:173 */        primitive_boxes.getBound(i, tmpAABB);
/* 174:174 */        node_bound.merge(tmpAABB);
/* 175:    */      }
/* 176:    */      
/* 177:177 */      setNodeBound(curIndex, node_bound);
/* 178:    */      
/* 180:180 */      _build_sub_tree(primitive_boxes, startIndex, splitIndex);
/* 181:    */      
/* 183:183 */      _build_sub_tree(primitive_boxes, splitIndex, endIndex);
/* 184:    */      
/* 185:185 */      this.node_array.setEscapeIndex(curIndex, this.num_nodes - curIndex);
/* 186:186 */    } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 187:    */    }
/* 188:    */  }
/* 189:    */  
/* 190:190 */  public void build_tree(BvhDataArray primitive_boxes) { this.num_nodes = 0;
/* 191:    */    
/* 192:192 */    this.node_array.resize(primitive_boxes.size() * 2);
/* 193:    */    
/* 194:194 */    _build_sub_tree(primitive_boxes, 0, primitive_boxes.size());
/* 195:    */  }
/* 196:    */  
/* 197:    */  public void clearNodes() {
/* 198:198 */    this.node_array.clear();
/* 199:199 */    this.num_nodes = 0;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public int getNodeCount() {
/* 203:203 */    return this.num_nodes;
/* 204:    */  }
/* 205:    */  
/* 208:    */  public boolean isLeafNode(int nodeindex)
/* 209:    */  {
/* 210:210 */    return this.node_array.isLeafNode(nodeindex);
/* 211:    */  }
/* 212:    */  
/* 213:    */  public int getNodeData(int nodeindex) {
/* 214:214 */    return this.node_array.getDataIndex(nodeindex);
/* 215:    */  }
/* 216:    */  
/* 217:    */  public void getNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 218:218 */    this.node_array.getBound(nodeindex, bound);
/* 219:    */  }
/* 220:    */  
/* 221:    */  public void setNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 222:222 */    this.node_array.setBound(nodeindex, bound);
/* 223:    */  }
/* 224:    */  
/* 225:    */  public int getLeftNode(int nodeindex) {
/* 226:226 */    return nodeindex + 1;
/* 227:    */  }
/* 228:    */  
/* 229:    */  public int getRightNode(int nodeindex) {
/* 230:230 */    if (this.node_array.isLeafNode(nodeindex + 1)) {
/* 231:231 */      return nodeindex + 2;
/* 232:    */    }
/* 233:233 */    return nodeindex + 1 + this.node_array.getEscapeIndex(nodeindex + 1);
/* 234:    */  }
/* 235:    */  
/* 236:    */  public int getEscapeNodeIndex(int nodeindex) {
/* 237:237 */    return this.node_array.getEscapeIndex(nodeindex);
/* 238:    */  }
/* 239:    */  
/* 240:    */  public BvhTreeNodeArray get_node_pointer() {
/* 241:241 */    return this.node_array;
/* 242:    */  }
/* 243:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */