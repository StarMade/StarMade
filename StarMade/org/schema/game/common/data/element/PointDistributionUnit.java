/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Aj;
/*   5:    */import Ak;
/*   6:    */import jE;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.util.Arrays;
/*   9:    */import le;
/*  10:    */import org.schema.common.FastMath;
/*  11:    */import org.schema.game.common.controller.SegmentController;
/*  12:    */import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*  13:    */import org.schema.game.common.controller.elements.DistributionInterface;
/*  14:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  15:    */import org.schema.game.common.data.element.pointeffect.PointEffect;
/*  16:    */import org.schema.schine.network.StateInterface;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteIntArray;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  20:    */import q;
/*  21:    */
/*  24:    */public abstract class PointDistributionUnit
/*  25:    */  extends ElementCollection
/*  26:    */  implements Ak
/*  27:    */{
/*  28:    */  private PointEffect[] effects;
/*  29:    */  private int maxPoints;
/*  30:    */  private boolean flagRedistribute;
/*  31:    */  
/*  32:    */  public void applyDummy(PointDistributionTagDummy paramPointDistributionTagDummy)
/*  33:    */  {
/*  34: 34 */    for (int i = 0; i < paramPointDistributionTagDummy.getEffects().length; i++) {
/*  35: 35 */      PointDistributionTagDummy.PointEffectDummy localPointEffectDummy = paramPointDistributionTagDummy.getEffects()[i];
/*  36:    */      PointEffect localPointEffect;
/*  37: 37 */      (localPointEffect = getEffectById(localPointEffectDummy.getId().intValue())).setDistribution(localPointEffectDummy.getDist().intValue());
/*  38: 38 */      sendDistChange(localPointEffect);
/*  39:    */    }
/*  40:    */  }
/*  41:    */  
/*  45:    */  public void clear()
/*  46:    */  {
/*  47: 47 */    super.clear();
/*  48: 48 */    resetPointsSpent();
/*  49: 49 */    setMaxPoints(0);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public void distributePoints() {
/*  53: 53 */    resetPointsSpent();
/*  54:    */    
/*  55: 55 */    int i = 0;
/*  56: 56 */    for (int j = 0; j < this.effects.length; j++) {
/*  57: 57 */      int k = FastMath.a(this.effects[j].getDistribution() / 100.0F * getMaxPoints());
/*  58: 58 */      this.effects[j].setPointsSpend(k);
/*  59: 59 */      i += k;
/*  60:    */    }
/*  61: 61 */    j = 0;
/*  62: 62 */    while (i < getMaxPoints()) {
/*  63: 63 */      this.effects[j].setPointsSpend(this.effects[j].getPointsSpend() + 1);
/*  64: 64 */      j = (j + 1) % this.effects.length;
/*  65: 65 */      i++;
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  72:    */  public void fromTagStructure(Ah paramAh)
/*  73:    */  {
/*  74: 74 */    (paramAh = (Ah[])paramAh.a())[0].a();
/*  75: 75 */    paramAh[1].a();
/*  76: 76 */    getFromEffectTagStruct(paramAh[2]);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public int getAvailableDist() {
/*  80: 80 */    int i = 0;
/*  81: 81 */    for (PointEffect localPointEffect : this.effects) {
/*  82: 82 */      i += localPointEffect.getDistribution();
/*  83:    */    }
/*  84:    */    
/*  86: 86 */    return 100 - i; }
/*  87:    */  
/*  88:    */  public PointEffect getEffectById(int paramInt) { PointEffect[] arrayOfPointEffect;
/*  89: 89 */    int i = (arrayOfPointEffect = getEffects()).length; for (int j = 0; j < i; j++) { PointEffect localPointEffect;
/*  90: 90 */      if ((localPointEffect = arrayOfPointEffect[j]).getEffectId() == paramInt) {
/*  91: 91 */        return localPointEffect;
/*  92:    */      }
/*  93:    */    }
/*  94: 94 */    throw new IllegalArgumentException("Effect Id " + paramInt + " not found in " + Arrays.toString(getEffects()));
/*  95:    */  }
/*  96:    */  
/*  98:    */  public PointEffect[] getEffects()
/*  99:    */  {
/* 100:100 */    if (this.flagRedistribute) {
/* 101:101 */      distributePoints();
/* 102:102 */      this.flagRedistribute = false;
/* 103:    */    }
/* 104:104 */    return this.effects;
/* 105:    */  }
/* 106:    */  
/* 107:107 */  public Ah getEffectTagStruct() { Ah[] arrayOfAh = new Ah[this.effects.length + 1];
/* 108:108 */    for (int i = 0; i < this.effects.length; i++) {
/* 109:109 */      arrayOfAh[i] = getEffects()[i].toTagStructure();
/* 110:    */    }
/* 111:111 */    arrayOfAh[this.effects.length] = new Ah(Aj.a, null, null);
/* 112:    */    
/* 113:113 */    return new Ah(Aj.n, "EffectStruct", arrayOfAh);
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void getFromEffectTagStruct(Ah paramAh) {
/* 117:117 */    paramAh = (Ah[])paramAh.a();
/* 118:118 */    for (int i = 0; i < this.effects.length; i++) {
/* 119:119 */      this.effects[i].fromTagStructure(paramAh[i]);
/* 120:    */    }
/* 121:121 */    distributePoints();
/* 122:    */  }
/* 123:    */  
/* 124:124 */  public int getMaxPoints() { return this.maxPoints; }
/* 125:    */  
/* 126:    */  private int getPointValue(long paramLong)
/* 127:    */  {
/* 128:128 */    return 4;
/* 129:    */  }
/* 130:    */  
/* 133:    */  public String getUniqueIdentifier()
/* 134:    */  {
/* 135:135 */    return null;
/* 136:    */  }
/* 137:    */  
/* 141:    */  public void initialize(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/* 142:    */  {
/* 143:143 */    super.initialize(paramShort, paramElementCollectionManager, paramSegmentController);
/* 144:    */    
/* 145:145 */    this.effects = initializeEffects();
/* 146:146 */    initializeDistribution();
/* 147:    */  }
/* 148:    */  
/* 149:149 */  protected void initializeDistribution() { float f = 1.0F / this.effects.length;
/* 150:150 */    for (int i = 0; i < this.effects.length; i++) {
/* 151:151 */      this.effects[i].setDistribution((int)(f * 100.0F));
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 156:    */  protected abstract PointEffect[] initializeEffects();
/* 157:    */  
/* 158:    */  public boolean isVolatile()
/* 159:    */  {
/* 160:160 */    return false;
/* 161:    */  }
/* 162:    */  
/* 163:163 */  protected void onAdd(long paramLong) { setMaxPoints(getMaxPoints() + getPointValue(paramLong)); }
/* 164:    */  
/* 167:    */  public boolean addElement(long paramLong)
/* 168:    */  {
/* 169:    */    boolean bool;
/* 170:    */    
/* 171:171 */    if ((bool = super.addElement(paramLong))) {
/* 172:172 */      onAdd(paramLong);
/* 173:    */    }
/* 174:174 */    return bool;
/* 175:    */  }
/* 176:    */  
/* 179:    */  public void onChangeFinished()
/* 180:    */  {
/* 181:181 */    super.onChangeFinished();
/* 182:182 */    distributePoints();
/* 183:    */  }
/* 184:    */  
/* 187:    */  protected void onRemove(q paramq)
/* 188:    */  {
/* 189:189 */    super.onRemove(paramq);
/* 190:190 */    setMaxPoints(getMaxPoints() - getPointValue(getIndex(paramq)));
/* 191:191 */    System.err.println("REMOVING four points " + this + " " + getMaxPoints());
/* 192:    */  }
/* 193:    */  
/* 194:194 */  public void receiveDistChange(jE paramjE) { if (getController().getState().getId() != paramjE.c) {
/* 195:    */      PointEffect localPointEffect;
/* 196:196 */      (localPointEffect = getEffectById(paramjE.a)).setDistribution(paramjE.b);
/* 197:    */      
/* 198:198 */      distributePoints();
/* 199:    */      
/* 200:200 */      getAvailableDist();
/* 201:    */      
/* 202:202 */      if (getController().isOnServer()) {
/* 203:203 */        sendDistChange(localPointEffect);
/* 204:    */      }
/* 205:    */    }
/* 206:    */  }
/* 207:    */  
/* 215:215 */  public boolean remove(q paramq) { return super.remove(paramq); }
/* 216:    */  
/* 217:    */  public void resetPointsSpent() { PointEffect[] arrayOfPointEffect;
/* 218:218 */    int i = (arrayOfPointEffect = this.effects).length; for (int j = 0; j < i; j++) {
/* 219:219 */      arrayOfPointEffect[j].reset();
/* 220:    */    }
/* 221:    */  }
/* 222:    */  
/* 223:    */  public void sendAllDistChange() {
/* 224:224 */    for (PointEffect localPointEffect : this.effects)
/* 225:225 */      sendDistChange(localPointEffect);
/* 226:    */  }
/* 227:    */  
/* 228:    */  public void sendDistChange(PointEffect paramPointEffect) {
/* 229:229 */    if (getId() == null) {
/* 230:230 */      if (!$assertionsDisabled) throw new AssertionError();
/* 231:231 */      throw new NullPointerException("PointDistribution] Could Not load ID POS");
/* 232:    */    }
/* 233:    */    
/* 234:234 */    RemoteIntArrayBuffer localRemoteIntArrayBuffer = ((DistributionInterface)getController().getNetworkObject()).getDistributionModification();
/* 235:235 */    RemoteIntArray localRemoteIntArray = new RemoteIntArray(9, getController().getNetworkObject());
/* 236:236 */    q localq1 = getId().a(new q());
/* 237:237 */    q localq2 = ((ControlBlockElementCollectionManager)this.col).getControllerPos();
/* 238:    */    
/* 239:239 */    localRemoteIntArray.set(0, localq2.a);
/* 240:240 */    localRemoteIntArray.set(1, localq2.b);
/* 241:241 */    localRemoteIntArray.set(2, localq2.c);
/* 242:    */    
/* 243:243 */    localRemoteIntArray.set(3, localq1.a);
/* 244:244 */    localRemoteIntArray.set(4, localq1.b);
/* 245:245 */    localRemoteIntArray.set(5, localq1.c);
/* 246:    */    
/* 247:247 */    localRemoteIntArray.set(6, paramPointEffect.getEffectId());
/* 248:248 */    localRemoteIntArray.set(7, paramPointEffect.getDistribution());
/* 249:249 */    localRemoteIntArray.set(8, getController().getState().getId());
/* 250:250 */    localRemoteIntArrayBuffer.add(localRemoteIntArray);
/* 251:    */  }
/* 252:    */  
/* 254:    */  public void setMaxPoints(int paramInt)
/* 255:    */  {
/* 256:256 */    int i = this.maxPoints;
/* 257:257 */    this.maxPoints = paramInt;
/* 258:258 */    if (i != paramInt) {
/* 259:259 */      this.flagRedistribute = true;
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public Ah toTagStructure()
/* 271:    */  {
/* 272:272 */    Object localObject1 = getId().a(new q());
/* 273:    */    
/* 274:274 */    Object localObject2 = ((ControlBlockElementCollectionManager)this.col).getControllerPos();
/* 275:    */    
/* 276:276 */    localObject2 = new Ah(Aj.k, "controller", localObject2);
/* 277:277 */    localObject1 = new Ah(Aj.k, "idPos", localObject1);
/* 278:    */    
/* 279:279 */    return new Ah(Aj.n, "PointDist", new Ah[] { localObject2, localObject1, getEffectTagStruct(), new Ah(Aj.a, null, null) });
/* 280:    */  }
/* 281:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */