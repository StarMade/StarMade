/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import Af;
/*   4:    */import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
/*   5:    */import it.unimi.dsi.fastutil.longs.Long2ObjectMap.FastEntrySet;
/*   6:    */import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   8:    */import it.unimi.dsi.fastutil.shorts.Short2ObjectMap.Entry;
/*   9:    */import it.unimi.dsi.fastutil.shorts.Short2ObjectMap.FastEntrySet;
/*  10:    */import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*  11:    */import ja;
/*  12:    */import java.io.DataOutputStream;
/*  13:    */import java.util.Iterator;
/*  14:    */import java.util.Set;
/*  15:    */import q;
/*  16:    */
/*  20:    */public class ControlElementMapper
/*  21:    */  extends Long2ObjectOpenHashMap
/*  22:    */  implements Af
/*  23:    */{
/*  24:    */  private static final long serialVersionUID = 8953098951065383692L;
/*  25:    */  
/*  26: 26 */  public void writeToTag(DataOutputStream paramDataOutputStream) { ControlElementMap.serialize(paramDataOutputStream, this); }
/*  27:    */  
/*  28: 28 */  private final Long2ObjectOpenHashMap all = new Long2ObjectOpenHashMap();
/*  29: 29 */  private final Long2ObjectOpenHashMap controllers = new Long2ObjectOpenHashMap();
/*  30:    */  
/*  32:    */  public byte getFactoryId()
/*  33:    */  {
/*  34: 34 */    return 0;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public boolean put(q paramq1, q paramq2, short paramShort) {
/*  38: 38 */    long l = ElementCollection.getIndex(paramq1);
/*  39:    */    
/*  40: 40 */    return put(l, paramq2, paramShort);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public boolean put(long paramLong, q paramq, short paramShort) {
/*  44: 44 */    assert (paramShort != 0);
/*  45: 45 */    assert (paramShort != 32767);
/*  46:    */    
/*  47:    */    Object localObject;
/*  48:    */    
/*  49: 49 */    if (!containsKey(paramLong)) {
/*  50: 50 */      localObject = new Short2ObjectOpenHashMap();
/*  51: 51 */      put(paramLong, localObject);
/*  52:    */    } else {
/*  53: 53 */      localObject = (Short2ObjectOpenHashMap)get(paramLong);
/*  54:    */    }
/*  55:    */    
/*  56:    */    ObjectOpenHashSet localObjectOpenHashSet;
/*  57:    */    
/*  58: 58 */    if (!((Short2ObjectOpenHashMap)localObject).containsKey(paramShort)) {
/*  59: 59 */      localObjectOpenHashSet = new ObjectOpenHashSet();
/*  60: 60 */      ((Short2ObjectOpenHashMap)localObject).put(paramShort, localObjectOpenHashSet);
/*  61:    */    } else {
/*  62: 62 */      localObjectOpenHashSet = (ObjectOpenHashSet)((Short2ObjectOpenHashMap)localObject).get(paramShort);
/*  63:    */    }
/*  64:    */    
/*  66: 66 */    paramq = new ja(paramq, paramShort);
/*  67:    */    
/*  69: 69 */    if (!getAll().containsKey(paramLong)) {
/*  70: 70 */      localObject = new ObjectOpenHashSet();
/*  71: 71 */      getAll().put(paramLong, localObject);
/*  72:    */    } else {
/*  73: 73 */      localObject = (ObjectOpenHashSet)getAll().get(paramLong);
/*  74:    */    }
/*  75: 75 */    ((ObjectOpenHashSet)localObject).add(paramq);
/*  76:    */    
/*  77: 77 */    if (!ElementKeyMap.getInfo(paramShort).getControlling().isEmpty())
/*  78:    */    {
/*  79: 79 */      if (!getControllers().containsKey(paramLong)) {
/*  80: 80 */        paramShort = new ObjectOpenHashSet();
/*  81: 81 */        getControllers().put(paramLong, paramShort);
/*  82:    */      } else {
/*  83: 83 */        paramShort = (ObjectOpenHashSet)getControllers().get(paramLong);
/*  84:    */      }
/*  85: 85 */      paramShort.add(paramq);
/*  86:    */    }
/*  87:    */    
/*  90: 90 */    return localObjectOpenHashSet.add(paramq);
/*  91:    */  }
/*  92:    */  
/*  95:    */  public Short2ObjectOpenHashMap remove(q paramq)
/*  96:    */  {
/*  97: 97 */    long l = ElementCollection.getIndex(paramq);
/*  98: 98 */    getAll().remove(l);
/*  99: 99 */    getControllers().remove(l);
/* 100:100 */    return (Short2ObjectOpenHashMap)super.remove(l);
/* 101:    */  }
/* 102:    */  
/* 103:103 */  ja tmp = new ja();
/* 104:    */  
/* 105:    */  public boolean remove(q paramq1, q paramq2, short paramShort)
/* 106:    */  {
/* 107:107 */    long l = ElementCollection.getIndex(paramq1);
/* 108:    */    
/* 109:109 */    return remove(l, paramq2, paramShort);
/* 110:    */  }
/* 111:    */  
/* 115:    */  public boolean remove(long paramLong, q paramq, short paramShort)
/* 116:    */  {
/* 117:117 */    if ((containsKey(paramLong)) && (((Short2ObjectOpenHashMap)get(paramLong)).containsKey(paramShort)))
/* 118:    */    {
/* 119:119 */      this.tmp.a(paramq, paramShort);
/* 120:120 */      ((ObjectOpenHashSet)getAll().get(paramLong)).remove(this.tmp);
/* 121:121 */      if (getControllers().containsKey(paramLong)) {
/* 122:122 */        ((ObjectOpenHashSet)getControllers().get(paramLong)).remove(this.tmp);
/* 123:    */      }
/* 124:    */      
/* 125:125 */      return ((ObjectOpenHashSet)((Short2ObjectOpenHashMap)get(paramLong)).get(paramShort)).remove(paramq);
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return false;
/* 129:    */  }
/* 130:    */  
/* 132:    */  public Long2ObjectOpenHashMap getAll()
/* 133:    */  {
/* 134:134 */    return this.all;
/* 135:    */  }
/* 136:    */  
/* 139:    */  public Long2ObjectOpenHashMap getControllers()
/* 140:    */  {
/* 141:141 */    return this.controllers;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public void set(ControlElementMapper paramControlElementMapper)
/* 145:    */  {
/* 146:146 */    for (paramControlElementMapper = paramControlElementMapper.long2ObjectEntrySet().iterator(); paramControlElementMapper.hasNext();) { localObject1 = (Long2ObjectMap.Entry)paramControlElementMapper.next();
/* 147:147 */      localObject2 = new Short2ObjectOpenHashMap();
/* 148:148 */      put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject2);
/* 149:    */      
/* 150:150 */      localObject3 = new ObjectOpenHashSet();
/* 151:151 */      this.all.put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject3);
/* 152:    */      
/* 156:156 */      for (localObject1 = ((Short2ObjectOpenHashMap)((Long2ObjectMap.Entry)localObject1).getValue()).short2ObjectEntrySet().iterator(); ((Iterator)localObject1).hasNext();) { localObject4 = (Short2ObjectMap.Entry)((Iterator)localObject1).next();
/* 157:157 */        localObjectOpenHashSet = new ObjectOpenHashSet();
/* 158:158 */        ((Short2ObjectOpenHashMap)localObject2).put(((Short2ObjectMap.Entry)localObject4).getShortKey(), localObjectOpenHashSet);
/* 159:159 */        for (localObject4 = ((ObjectOpenHashSet)((Short2ObjectMap.Entry)localObject4).getValue()).iterator(); ((Iterator)localObject4).hasNext();) { ja localja = (ja)((Iterator)localObject4).next();
/* 160:160 */          localja = new ja(localja);
/* 161:161 */          localObjectOpenHashSet.add(localja);
/* 162:162 */          ((ObjectOpenHashSet)localObject3).add(localja); } } }
/* 163:    */    Object localObject1;
/* 164:    */    Object localObject2;
/* 165:    */    Object localObject3;
/* 166:    */    Object localObject4;
/* 167:167 */    ObjectOpenHashSet localObjectOpenHashSet; for (paramControlElementMapper = this.controllers.long2ObjectEntrySet().iterator(); paramControlElementMapper.hasNext();) { localObject1 = (Long2ObjectMap.Entry)paramControlElementMapper.next();
/* 168:168 */      localObject2 = new ObjectOpenHashSet();
/* 169:169 */      this.controllers.put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject2);
/* 170:170 */      for (localObject3 = ((ObjectOpenHashSet)((Long2ObjectMap.Entry)localObject1).getValue()).iterator(); ((Iterator)localObject3).hasNext();) { localObject1 = (ja)((Iterator)localObject3).next();
/* 171:171 */        ((ObjectOpenHashSet)localObject2).add(new ja((ja)localObject1));
/* 172:    */      }
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 178:    */  public void clear()
/* 179:    */  {
/* 180:180 */    this.all.clear();
/* 181:181 */    this.controllers.clear();
/* 182:182 */    super.clear();
/* 183:    */  }
/* 184:    */  
/* 185:185 */  public void clearAndTrim() { clear();
/* 186:186 */    this.all.trim();
/* 187:187 */    this.controllers.trim();
/* 188:188 */    trim();
/* 189:    */  }
/* 190:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */