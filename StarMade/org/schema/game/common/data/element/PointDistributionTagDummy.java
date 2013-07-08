/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Ak;
/*   5:    */import jE;
/*   6:    */import java.util.Arrays;
/*   7:    */import q;
/*   8:    */
/*  67:    */public class PointDistributionTagDummy
/*  68:    */  implements Ak
/*  69:    */{
/*  70:    */  private PointDistributionTagDummy.PointEffectDummy[] effects;
/*  71:    */  private q controllerPos;
/*  72:    */  private q idPos;
/*  73:    */  
/*  74:    */  public PointDistributionTagDummy() {}
/*  75:    */  
/*  76:    */  public PointDistributionTagDummy(jE paramjE)
/*  77:    */  {
/*  78: 78 */    this.controllerPos = paramjE.jdField_a_of_type_Q;
/*  79: 79 */    this.idPos = paramjE.jdField_b_of_type_Q;
/*  80: 80 */    this.effects = new PointDistributionTagDummy.PointEffectDummy[1];
/*  81: 81 */    this.effects[0] = new PointDistributionTagDummy.PointEffectDummy(this);
/*  82: 82 */    this.effects[0].setId(Integer.valueOf(paramjE.jdField_a_of_type_Int));
/*  83: 83 */    PointDistributionTagDummy.PointEffectDummy.access$002(this.effects[0], Integer.valueOf(paramjE.jdField_b_of_type_Int));
/*  84:    */  }
/*  85:    */  
/*  88:    */  public void fromTagStructure(Ah paramAh)
/*  89:    */  {
/*  90: 90 */    paramAh = (Ah[])paramAh.a();
/*  91: 91 */    setControllerPos((q)paramAh[0].a());
/*  92: 92 */    setIdPos((q)paramAh[1].a());
/*  93: 93 */    getFromEffectTagStruct(paramAh[2]);
/*  94:    */  }
/*  95:    */  
/*  97:    */  public q getControllerPos()
/*  98:    */  {
/*  99: 99 */    return this.controllerPos;
/* 100:    */  }
/* 101:    */  
/* 106:106 */  public PointDistributionTagDummy.PointEffectDummy[] getEffects() { return this.effects; }
/* 107:    */  
/* 108:    */  public void getFromEffectTagStruct(Ah paramAh) {
/* 109:109 */    paramAh = (Ah[])paramAh.a();
/* 110:110 */    setEffects(new PointDistributionTagDummy.PointEffectDummy[paramAh.length - 1]);
/* 111:    */    
/* 112:112 */    for (int i = 0; i < getEffects().length; i++) {
/* 113:113 */      getEffects()[i] = new PointDistributionTagDummy.PointEffectDummy(this);
/* 114:114 */      getEffects()[i].fromTagStructure(paramAh[i]);
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 119:    */  public q getIdPos()
/* 120:    */  {
/* 121:121 */    return this.idPos;
/* 122:    */  }
/* 123:    */  
/* 128:    */  public String getUniqueIdentifier()
/* 129:    */  {
/* 130:130 */    return null;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public boolean isVolatile()
/* 134:    */  {
/* 135:135 */    return false;
/* 136:    */  }
/* 137:    */  
/* 139:    */  public void setControllerPos(q paramq)
/* 140:    */  {
/* 141:141 */    this.controllerPos = paramq;
/* 142:    */  }
/* 143:    */  
/* 145:    */  public void setEffects(PointDistributionTagDummy.PointEffectDummy[] paramArrayOfPointEffectDummy)
/* 146:    */  {
/* 147:147 */    this.effects = paramArrayOfPointEffectDummy;
/* 148:    */  }
/* 149:    */  
/* 151:    */  public void setIdPos(q paramq)
/* 152:    */  {
/* 153:153 */    this.idPos = paramq;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public String toString() {
/* 157:157 */    return "[" + getControllerPos() + "; " + getIdPos() + "; " + Arrays.toString(getEffects()) + "]";
/* 158:    */  }
/* 159:    */  
/* 160:    */  public Ah toTagStructure() {
/* 161:161 */    return null;
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionTagDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */