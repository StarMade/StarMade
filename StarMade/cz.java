/*   1:    */import it.unimi.dsi.fastutil.longs.LongSet;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.schema.game.common.data.element.ControlElementMap;
/*   5:    */import org.schema.game.common.data.element.ControlElementMapper;
/*   6:    */import org.schema.game.common.data.element.ElementCollection;
/*   7:    */import org.schema.game.network.objects.NetworkPlayer;
/*   8:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*   9:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  10:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  11:    */
/*  12:    */public final class cz implements Ak
/*  13:    */{
/*  14:    */  private String jdField_a_of_type_JavaLangString;
/*  15:    */  private String b;
/*  16:    */  public java.util.concurrent.ConcurrentHashMap a;
/*  17:    */  public java.util.concurrent.ConcurrentHashMap b;
/*  18:    */  private lE jdField_a_of_type_LE;
/*  19:    */  
/*  20:    */  public cz(lE paramlE, String paramString)
/*  21:    */  {
/*  22: 22 */    this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap = new java.util.concurrent.ConcurrentHashMap();
/*  23: 23 */    this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap = new java.util.concurrent.ConcurrentHashMap();
/*  24:    */    
/*  28: 28 */    this.jdField_b_of_type_JavaLangString = paramlE.getUniqueIdentifier();
/*  29: 29 */    this.jdField_a_of_type_LE = paramlE;
/*  30: 30 */    this.jdField_a_of_type_JavaLangString = paramString;
/*  31:    */  }
/*  32:    */  
/*  33:    */  public final boolean equals(Object paramObject)
/*  34:    */  {
/*  35: 35 */    return (this.jdField_a_of_type_JavaLangString.equals(((cz)paramObject).jdField_a_of_type_JavaLangString)) && (this.jdField_b_of_type_JavaLangString.equals(((cz)paramObject).jdField_b_of_type_JavaLangString));
/*  36:    */  }
/*  37:    */  
/*  39:    */  public final void fromTagStructure(Ah paramAh) {}
/*  40:    */  
/*  42:    */  public final q a(int paramInt)
/*  43:    */  {
/*  44: 44 */    return (q)this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt));
/*  45:    */  }
/*  46:    */  
/*  53:    */  public final String getUniqueIdentifier()
/*  54:    */  {
/*  55: 55 */    return null;
/*  56:    */  }
/*  57:    */  
/*  58: 58 */  public final boolean a(int paramInt) { return this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(Integer.valueOf(paramInt)); }
/*  59:    */  
/*  61:    */  public final int hashCode()
/*  62:    */  {
/*  63: 63 */    return this.jdField_a_of_type_JavaLangString.hashCode() + this.jdField_b_of_type_JavaLangString.hashCode();
/*  64:    */  }
/*  65:    */  
/*  66: 66 */  public final boolean a(q paramq) { return this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.containsValue(paramq); }
/*  67:    */  
/*  70:    */  public final boolean isVolatile()
/*  71:    */  {
/*  72: 72 */    return false;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public final boolean a(int paramInt, q paramq, boolean paramBoolean) {
/*  76: 76 */    System.err.println("[SHIP][KEYCONFIG] ASSIGNED Key " + paramInt + " to " + paramq + " on " + this.jdField_a_of_type_JavaLangString + " on " + this.jdField_a_of_type_LE.getState());
/*  77:    */    
/*  78: 78 */    Object localObject = (q)this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt));
/*  79: 79 */    if (paramq.equals(localObject))
/*  80:    */    {
/*  81: 81 */      return false;
/*  82:    */    }
/*  83: 83 */    if (localObject != null) {
/*  84: 84 */      this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject);
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    if ((localObject = (Integer)this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.get(paramq)) != null) {
/*  88: 88 */      this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject);
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.put(Integer.valueOf(paramInt), paramq);
/*  92: 92 */    this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.put(paramq, Integer.valueOf(paramInt));
/*  93: 93 */    if (paramBoolean) {
/*  94: 94 */      a(paramInt, paramq, true);
/*  95:    */    }
/*  96: 96 */    return true;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public final void a(ct paramct, jD paramjD) {
/* 100:100 */    paramct = paramct.a();
/* 101:101 */    int i = 0;
/* 102:102 */    if (paramct != null)
/* 103:    */    {
/* 104:104 */      q localq = null;long l1 = ElementCollection.getIndex(new q(8, 8, 8));
/* 105:105 */      for (paramct = paramct.getControlElementMap().getControllingMap().keySet().iterator(); paramct.hasNext();) { long l2;
/* 106:106 */        if ((l2 = ((Long)paramct.next()).longValue()) != l1) {
/* 107:107 */          localq = ElementCollection.getPosFromIndex(l2, new q());
/* 108:108 */          if (paramjD.a.contains(localq))
/* 109:    */          {
/* 110:110 */            a(i, localq, true);
/* 111:    */          }
/* 112:112 */          if (i > 9) break;
/* 113:113 */          i++;
/* 114:    */        }
/* 115:    */      }
/* 116:    */      
/* 118:118 */      return; }
/* 119:119 */    System.err.println("[ShipKeyConfig] WARNING: no ship for state but tried to update assignments");
/* 120:    */  }
/* 121:    */  
/* 123:    */  public final q a(int paramInt, boolean paramBoolean)
/* 124:    */  {
/* 125:    */    q localq;
/* 126:126 */    if ((localq = (q)this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt))) != null) {
/* 127:127 */      this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localq);
/* 128:128 */      this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(Integer.valueOf(paramInt));
/* 129:129 */      if (paramBoolean) {
/* 130:130 */        a(paramInt, localq, false);
/* 131:    */      }
/* 132:132 */      return localq;
/* 133:    */    }
/* 134:134 */    return null;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public final int a(q paramq) {
/* 138:    */    Integer localInteger;
/* 139:139 */    if ((localInteger = (Integer)this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.get(paramq)) != null) {
/* 140:140 */      this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.remove(paramq);
/* 141:141 */      this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localInteger);
/* 142:142 */      a(localInteger.intValue(), paramq, false);
/* 143:    */      
/* 145:145 */      return localInteger.intValue();
/* 146:    */    }
/* 147:147 */    return -1;
/* 148:    */  }
/* 149:    */  
/* 150:150 */  private void a(int paramInt, q paramq, boolean paramBoolean) { synchronized (this.jdField_a_of_type_LE.a()) {
/* 151:    */      org.schema.schine.network.objects.remote.RemoteIntegerArray localRemoteIntegerArray;
/* 152:152 */      (localRemoteIntegerArray = new org.schema.schine.network.objects.remote.RemoteIntegerArray(4, this.jdField_a_of_type_LE.a())).set(0, Integer.valueOf(paramBoolean ? paramInt : -paramInt - 1));
/* 153:153 */      localRemoteIntegerArray.set(1, Integer.valueOf(paramq.a));
/* 154:154 */      localRemoteIntegerArray.set(2, Integer.valueOf(paramq.b));
/* 155:155 */      localRemoteIntegerArray.set(3, Integer.valueOf(paramq.c));
/* 156:156 */      this.jdField_a_of_type_LE.a().controllerKeyNameBuffer.add(new RemoteString(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_LE.a()));
/* 157:157 */      this.jdField_a_of_type_LE.a().controllerKeyBuffer.add(localRemoteIntegerArray);
/* 158:158 */      return;
/* 159:    */    } }
/* 160:    */  
/* 161:161 */  public final void a() { synchronized (this.jdField_a_of_type_LE.a()) {
/* 162:162 */      for (java.util.Map.Entry localEntry : this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.entrySet()) {
/* 163:163 */        a(((Integer)localEntry.getKey()).intValue(), (q)localEntry.getValue(), true);
/* 164:    */      }
/* 165:165 */      return;
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 171:    */  public final String toString()
/* 172:    */  {
/* 173:173 */    return "ShipKeyConfiguration [shipIdentifier=" + this.jdField_a_of_type_JavaLangString + ", playerIdentifier=" + this.jdField_b_of_type_JavaLangString + ", keyControllerMap=" + this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap + ", controllerKeyMap=" + this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap + "]";
/* 174:    */  }
/* 175:    */  
/* 180:    */  public final Ah toTagStructure()
/* 181:    */  {
/* 182:182 */    return null;
/* 183:    */  }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */