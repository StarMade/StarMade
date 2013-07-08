/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.HashMap;
/*   9:    */import org.schema.game.common.data.world.SectorNotFoundException;
/*  10:    */import org.schema.game.common.data.world.Universe;
/*  11:    */import org.schema.game.network.objects.NetworkClientChannel;
/*  12:    */import org.schema.game.network.objects.remote.RemoteMissileUpdate;
/*  13:    */import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*  14:    */import org.schema.schine.network.NetworkStateContainer;
/*  15:    */import org.schema.schine.network.RegisteredClientOnServer;
/*  16:    */import org.schema.schine.network.objects.Sendable;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteShort;
/*  19:    */
/*  20:    */public class lo
/*  21:    */{
/*  22: 22 */  private final it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap = new it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap();
/*  23:    */  private final vg jdField_a_of_type_Vg;
/*  24:    */  
/*  25:    */  public lo(vg paramvg) {
/*  26: 26 */    this.jdField_a_of_type_Vg = paramvg;
/*  27:    */  }
/*  28:    */  
/*  29:    */  public final void a(ln paramln) {
/*  30: 30 */    paramln.initPhysics();
/*  31: 31 */    this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.put(paramln.a(), paramln);
/*  32: 32 */    paramln.b.add(a(paramln));
/*  33: 33 */    System.err.println("[SERVER] MISSILE ADDED " + paramln);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public final void a(xq paramxq) {
/*  37: 37 */    ObjectIterator localObjectIterator = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.values().iterator();
/*  38: 38 */    while (localObjectIterator.hasNext()) {
/*  39:    */      ln localln;
/*  40:    */      java.util.Iterator localIterator;
/*  41: 41 */      if ((localln = (ln)localObjectIterator.next()).a()) {
/*  42: 42 */        localln.a(paramxq);
/*  43:    */        
/*  44: 44 */        localln.a().clear();
/*  45: 45 */        for (localIterator = this.jdField_a_of_type_Vg.b().values().iterator(); localIterator.hasNext();) { lE locallE;
/*  46: 46 */          if ((locallE = (lE)localIterator.next()).c() == localln.b()) {
/*  47: 47 */            localln.a().add(locallE);
/*  48:    */          }
/*  49:    */          else {
/*  50:    */            mx localmx;
/*  51: 51 */            if ((localmx = this.jdField_a_of_type_Vg.a().getSector(localln.b())) != null)
/*  52:    */            {
/*  53: 53 */              if ((Math.abs(localmx.a.a - locallE.a().a) < 2) && (Math.abs(localmx.a.b - locallE.a().b) < 2) && (Math.abs(localmx.a.c - locallE.a().c) < 2))
/*  54:    */              {
/*  58: 58 */                localln.a().add(locallE);
/*  59:    */              }
/*  60:    */            }
/*  61:    */            else {
/*  62: 62 */              localln.d();
/*  63:    */            }
/*  64:    */          }
/*  65:    */        }
/*  66:    */      } else {
/*  67:    */        try {
/*  68: 68 */          localln.c();
/*  69:    */        } catch (SectorNotFoundException localSectorNotFoundException) {
/*  70: 70 */          System.err.println("[SERVER][MISSILEMAN] WARNING: Physics for missile " + localln + " has not been removed: Sector not loaded: " + localSectorNotFoundException.getMessage());
/*  71:    */        }
/*  72: 72 */        localObjectIterator.remove();
/*  73:    */      }
/*  74:    */    }
/*  75:    */    
/*  77: 77 */    a();
/*  78:    */  }
/*  79:    */  
/*  80:    */  private void a() {
/*  81: 81 */    for (java.util.Iterator localIterator1 = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.values().iterator(); localIterator1.hasNext();) { ln localln;
/*  82:    */      java.util.Iterator localIterator2;
/*  83: 83 */      if ((localln = (ln)localIterator1.next()).c()) {
/*  84: 84 */        System.err.println("[SERVER] BROADCAST MISSILE UPDATE " + localln + "; Missile Count: " + this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.size() + "; " + localln.b);
/*  85: 85 */        for (localIterator2 = this.jdField_a_of_type_Vg.b().values().iterator(); localIterator2.hasNext();) { localObject1 = (lE)localIterator2.next();
/*  86:    */          
/*  88: 88 */          if ((localObject2 = (RegisteredClientOnServer)this.jdField_a_of_type_Vg.getClients().get(Integer.valueOf(((lE)localObject1).a()))) != null) {
/*  89: 89 */            ((lE)localObject1).a();
/*  90:    */            
/*  93: 93 */            if (((localObject2 = (Sendable)((RegisteredClientOnServer)localObject2).getLocalAndRemoteObjectContainer().getLocalObjects().get(0)) != null) && ((localObject2 instanceof t))) {
/*  94: 94 */              localObject1 = (t)localObject2;
/*  95: 95 */              localln.b((t)localObject1);
/*  96:    */            } else {
/*  97: 97 */              System.err.println("[SERVER] BROADCAST MISSILE UPDATE FAILED FOR " + localObject1 + ": NO CLIENT CHANNEL");
/*  98:    */            }
/*  99:    */          }
/* 100:    */          else
/* 101:    */          {
/* 102:102 */            System.err.println("[SEVRER][MISSILEMAN] client for player not found: " + localObject1);
/* 103:    */          }
/* 104:    */        } }
/* 105:    */      Object localObject1;
/* 106:    */      Object localObject2;
/* 107:107 */      if ((localln.b()) && (localln.a())) {
/* 108:108 */        for (localIterator2 = localln.a().iterator(); localIterator2.hasNext();) { localObject1 = (lE)localIterator2.next();
/* 109:    */          
/* 111:111 */          if ((localObject2 = (RegisteredClientOnServer)this.jdField_a_of_type_Vg.getClients().get(Integer.valueOf(((lE)localObject1).a()))) != null) {
/* 112:112 */            ((lE)localObject1).a();
/* 113:    */            
/* 116:116 */            if (((localObject2 = (Sendable)((RegisteredClientOnServer)localObject2).getLocalAndRemoteObjectContainer().getLocalObjects().get(0)) != null) && ((localObject2 instanceof t))) {
/* 117:117 */              localObject1 = (t)localObject2;
/* 118:118 */              localln.a((t)localObject1);
/* 119:    */            }
/* 120:    */          } else {
/* 121:121 */            System.err.println("[SEVRER][MISSILEMAN] broadcast client for player not found: " + localObject1);
/* 122:    */          }
/* 123:    */        }
/* 124:    */      }
/* 125:    */      
/* 126:126 */      localln.e();
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 136:    */  public final void a(NetworkClientChannel paramNetworkClientChannel)
/* 137:    */  {
/* 138:138 */    for (int i = 0; i < paramNetworkClientChannel.missileMissingRequestBuffer.getReceiveBuffer().size(); 
/* 139:139 */        i++) {
/* 140:140 */      short s = ((Short)((RemoteShort)paramNetworkClientChannel.missileMissingRequestBuffer.getReceiveBuffer().get(i)).get()).shortValue();
/* 141:    */      
/* 142:    */      ln localln;
/* 143:143 */      if ((localln = (ln)this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.get(s)) != null) {
/* 144:144 */        System.err.println("[SERVER] Adding Requested Spawn " + localln);
/* 145:145 */        paramNetworkClientChannel.missileUpdateBuffer.add(new RemoteMissileUpdate(a(localln), paramNetworkClientChannel));
/* 146:    */      }
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 151:    */  private static lu a(ln paramln)
/* 152:    */  {
/* 153:153 */    int i = -1;
/* 154:154 */    if (((paramln instanceof lp)) && (((lp)paramln).a() != null))
/* 155:    */    {
/* 156:156 */      i = ((lp)paramln).a().getId();
/* 157:    */    }
/* 158:    */    
/* 159:159 */    lu locallu = new lu(paramln.a(), paramln.a().origin, paramln.a(), i, paramln.a(), paramln.b());
/* 160:    */    
/* 163:163 */    if ((!jdField_a_of_type_Boolean) && (locallu.a != paramln.a())) { throw new AssertionError();
/* 164:    */    }
/* 165:165 */    return locallu;
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */