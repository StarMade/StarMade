/*   1:    */package org.schema.game.common.controller.elements.factory;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import eH;
/*   5:    */import ij;
/*   6:    */import jD;
/*   7:    */import jL;
/*   8:    */import ja;
/*   9:    */import java.io.IOException;
/*  10:    */import java.io.PrintStream;
/*  11:    */import java.util.ArrayList;
/*  12:    */import java.util.Collection;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.HashSet;
/*  15:    */import java.util.Iterator;
/*  16:    */import java.util.List;
/*  17:    */import javax.vecmath.Vector3f;
/*  18:    */import ld;
/*  19:    */import le;
/*  20:    */import mh;
/*  21:    */import org.schema.game.common.controller.SegmentController;
/*  22:    */import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*  23:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*  24:    */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  25:    */import org.schema.game.common.data.element.ControlElementMap;
/*  26:    */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*  27:    */import q;
/*  28:    */
/*  29:    */public class FactoryCollectionManager extends ControlBlockElementCollectionManager
/*  30:    */{
/*  31:    */  public FactoryCollectionManager(short paramShort, le paramle, SegmentController paramSegmentController)
/*  32:    */  {
/*  33: 33 */    super(paramle, paramShort, paramSegmentController);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public int getMargin()
/*  37:    */  {
/*  38: 38 */    return 0;
/*  39:    */  }
/*  40:    */  
/*  41:    */  protected Class getType()
/*  42:    */  {
/*  43: 43 */    return FactoryUnit.class;
/*  44:    */  }
/*  45:    */  
/*  48: 48 */  protected void onChangedCollection() { refreshEnhancerCapabiities(); }
/*  49:    */  
/*  50:    */  public void refreshEnhancerCapabiities() {
/*  51: 51 */    this.capability = 1;
/*  52: 52 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/*  53: 53 */      ((FactoryUnit)localIterator.next()).refreshFactoryCapabilities(this);
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  59: 59 */  private q absPos = new q();
/*  60: 60 */  private int capability = 1;
/*  61:    */  
/*  65:    */  public void manufractureStep(FactoryElementManager paramFactoryElementManager, HashMap paramHashMap)
/*  66:    */  {
/*  67: 67 */    if (!consumePower(paramFactoryElementManager))
/*  68:    */    {
/*  69: 69 */      return;
/*  70:    */    }
/*  71:    */    
/*  72: 72 */    if (getSegmentController().isOnServer())
/*  73:    */    {
/*  74: 74 */      q localq = getControllerElement().a(this.absPos);
/*  75: 75 */      jD localjD = paramFactoryElementManager.getControlElementMap().getControlledElements((short)32767, localq);
/*  76:    */      
/*  80:    */      mf localmf;
/*  81:    */      
/*  84: 84 */      if ((localmf = ((ld)getSegmentController()).a().getInventory(localq)) == null) {
/*  85: 85 */        System.err.println("[SERVER][FACTORY] Exception: Factory has no own inventory: " + getSegmentController() + " -> " + localq);return;
/*  86:    */      }
/*  87: 87 */      if (!paramFactoryElementManager.isInputFactory()) {
/*  88: 88 */        int i = paramFactoryElementManager.getProductCount();
/*  89:    */        
/*  90: 90 */        for (int j = 0; j < i; j++) {
/*  91:    */          HashSet localHashSet;
/*  92: 92 */          if ((localHashSet = (HashSet)paramHashMap.get(localmf)) == null) {
/*  93: 93 */            localHashSet = new HashSet();
/*  94: 94 */            paramHashMap.put(localmf, localHashSet);
/*  95:    */          }
/*  96:    */          
/*  97: 97 */          handleProduct(localjD, paramFactoryElementManager, j, localmf, paramHashMap, localHashSet);
/*  98:    */        }
/*  99:    */      }
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 114:114 */  private final q posTmp = new q();
/* 115:    */  private long lastCheck;
/* 116:    */  
/* 117:    */  private void handleProduct(jD paramjD, FactoryElementManager paramFactoryElementManager, int paramInt, mf parammf, HashMap paramHashMap, HashSet paramHashSet) {
/* 118:118 */    for (ja localja : paramjD.a)
/* 119:    */    {
/* 120:120 */      this.posTmp.b(localja.a, localja.b, localja.c);
/* 121:    */      mf localmf;
/* 122:    */      int i;
/* 123:    */      le localle;
/* 124:124 */      if ((localmf = ((ld)getSegmentController()).a().getInventory(this.posTmp)) != null)
/* 125:    */      {
/* 127:127 */        if ((localObject = (HashSet)paramHashMap.get(localmf)) == null) {
/* 128:128 */          localObject = new HashSet();
/* 129:129 */          paramHashMap.put(localmf, localObject);
/* 130:    */        }
/* 131:    */        
/* 132:132 */        for (localja : paramFactoryElementManager.getInputType(paramInt))
/* 133:    */        {
/* 134:    */          int k;
/* 135:    */          
/* 136:136 */          if ((k = localmf.b(localja.type)) >= getCount(localja)) {
/* 137:137 */            try { localmf.a(localja.type, (Collection)localObject);
/* 138:    */              
/* 144:144 */              k = localmf.b(localja.type, k - getCount(localja));
/* 145:    */              
/* 148:148 */              ((HashSet)localObject).add(Integer.valueOf(k));
/* 149:    */              
/* 151:151 */              i = parammf.b(localja.type, getCount(localja));
/* 152:    */              
/* 154:154 */              paramHashSet.add(Integer.valueOf(i));
/* 155:    */            } catch (NoSlotFreeException localNoSlotFreeException1) {
/* 156:156 */              localle = null;
/* 157:    */              
/* 158:158 */              localNoSlotFreeException1.printStackTrace();
/* 159:    */            }
/* 160:    */            
/* 161:    */          }
/* 162:    */          
/* 163:    */        }
/* 164:    */      }
/* 165:163 */      else if (System.currentTimeMillis() - this.lastCheck > 1000L)
/* 166:    */      {
/* 167:    */        try {
/* 168:166 */          if (((localle = getSegmentController().getSegmentBuffer().a(this.posTmp, true)) == null) || (localle.a() != 212))
/* 169:    */          {
/* 171:169 */            System.err.println("[FACTORY] " + getSegmentController() + ": no inventory found at " + i + "; ControllerPos: " + getControllerPos() + "; NOW loaded supposed inventory position (Unsave Point): " + localle); }
/* 172:    */        } catch (IOException localIOException) {
/* 173:171 */          localle = null;
/* 174:    */          
/* 177:175 */          localIOException.printStackTrace();
/* 178:    */        } catch (InterruptedException localInterruptedException) {
/* 179:173 */          localle = null;
/* 180:    */          
/* 181:175 */          localInterruptedException.printStackTrace();
/* 182:    */        }
/* 183:    */        
/* 184:176 */        this.lastCheck = System.currentTimeMillis();
/* 185:    */      }
/* 186:    */    }
/* 187:    */    Object localObject;
/* 188:180 */    paramjD = 1;
/* 189:181 */    for (localObject : paramFactoryElementManager.getInputType(paramInt))
/* 190:    */    {
/* 193:185 */      if (parammf.b(((org.schema.game.common.data.element.FactoryResource)localObject).type) < getCount((org.schema.game.common.data.element.FactoryResource)localObject)) {
/* 194:186 */        paramjD = 0;
/* 195:    */      }
/* 196:    */    }
/* 197:189 */    if (paramjD != 0) { int n;
/* 198:190 */      for (localObject : paramFactoryElementManager.getInputType(paramInt)) {
/* 199:191 */        n = parammf.b(((org.schema.game.common.data.element.FactoryResource)localObject).type);
/* 200:    */        
/* 201:193 */        parammf.a(((org.schema.game.common.data.element.FactoryResource)localObject).type, paramHashSet);
/* 202:    */        try
/* 203:    */        {
/* 204:196 */          ??? = parammf.b(((org.schema.game.common.data.element.FactoryResource)localObject).type, n - getCount((org.schema.game.common.data.element.FactoryResource)localObject));
/* 205:    */          
/* 206:198 */          paramHashSet.add(Integer.valueOf(???));
/* 207:199 */        } catch (NoSlotFreeException localNoSlotFreeException2) { 
/* 208:    */          
/* 210:202 */            localNoSlotFreeException2;
/* 211:    */        }
/* 212:    */      }
/* 213:    */      
/* 215:204 */      for (localObject : paramFactoryElementManager.getOutputType(paramInt)) {
/* 216:    */        try {
/* 217:206 */          n = parammf.b(((org.schema.game.common.data.element.FactoryResource)localObject).type, getCount((org.schema.game.common.data.element.FactoryResource)localObject));
/* 218:    */          
/* 219:208 */          paramHashSet.add(Integer.valueOf(n));
/* 220:209 */        } catch (NoSlotFreeException localNoSlotFreeException3) { 
/* 221:    */          
/* 222:211 */            localNoSlotFreeException3;
/* 223:    */        }
/* 224:    */      }
/* 225:    */    }
/* 226:    */  }
/* 227:    */  
/* 228:    */  public boolean consumePower(FactoryElementManager paramFactoryElementManager)
/* 229:    */  {
/* 230:217 */    boolean bool = (paramFactoryElementManager = ((PowerManagerInterface)paramFactoryElementManager.getManagerContainer()).getPowerAddOn()).consumePowerInstantly(this.capability * 500);
/* 231:    */    
/* 232:219 */    if ((!getSegmentController().isOnServer()) && (!bool)) {
/* 233:    */      Transform localTransform;
/* 234:221 */      (localTransform = new Transform()).setIdentity();
/* 235:222 */      q localq = getControllerElement().a(this.absPos);
/* 236:223 */      localTransform.origin.set(localq.a - 8, localq.b - 8, localq.c - 8);
/* 237:224 */      getSegmentController().getWorldTransform().transform(localTransform.origin);
/* 238:225 */      ij.a.add(new eH(localTransform, "Insufficient Energy (" + (int)paramFactoryElementManager.getPower() + " / " + this.capability * 100 + ")", 1.0F, 0.1F, 0.1F));
/* 239:    */    }
/* 240:227 */    return bool;
/* 241:    */  }
/* 242:    */  
/* 243:    */  public int getCount(org.schema.game.common.data.element.FactoryResource paramFactoryResource) {
/* 244:231 */    return paramFactoryResource.count * this.capability;
/* 245:    */  }
/* 246:    */  
/* 247:234 */  public void addCapability(int paramInt) { this.capability += paramInt; }
/* 248:    */  
/* 249:    */  public boolean needsUpdate()
/* 250:    */  {
/* 251:238 */    return false;
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */