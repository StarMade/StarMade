/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2IntOpenHashMap;
/*  2:   */import it.unimi.dsi.fastutil.shorts.ShortSet;
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.HashSet;
/*  5:   */import java.util.Iterator;
/*  6:   */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*  7:   */import org.schema.game.network.objects.NetworkPlayer;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/* 10:   */import org.schema.schine.network.server.ServerStateInterface;
/* 11:   */
/* 13:   */public class mg
/* 14:   */{
/* 15:   */  private lE jdField_a_of_type_LE;
/* 16:16 */  private Short2IntOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap = new Short2IntOpenHashMap();
/* 17:   */  
/* 18:   */  public mg(lE paramlE) {
/* 19:19 */    this.jdField_a_of_type_LE = paramlE;
/* 20:20 */    if ((paramlE.getState() instanceof ServerStateInterface)) {
/* 21:21 */      paramlE = this; if ((!jdField_a_of_type_Boolean) && (!paramlE.jdField_a_of_type_LE.getInventory(null).c())) throw new AssertionError(); paramlE.jdField_a_of_type_LE.getInventory(null).b(0, (short)5, 25);paramlE.jdField_a_of_type_LE.getInventory(null).b(1, (short)2, 6);paramlE.jdField_a_of_type_LE.getInventory(null).b(2, (short)8, 6);paramlE.jdField_a_of_type_LE.getInventory(null).b(3, (short)6, 1);paramlE.jdField_a_of_type_LE.getInventory(null).b(4, (short)16, 20);paramlE.jdField_a_of_type_LE.getInventory(null).b(10, (short)1, 1);
/* 22:   */    }
/* 23:   */  }
/* 24:   */  
/* 25:   */  public final void a(short paramShort, int paramInt) {
/* 26:   */    RemoteIntegerArray localRemoteIntegerArray;
/* 27:27 */    (localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_a_of_type_LE.a())).set(0, Integer.valueOf(paramInt));
/* 28:28 */    localRemoteIntegerArray.set(1, Integer.valueOf(paramShort));
/* 29:29 */    this.jdField_a_of_type_LE.a().buyBuffer.add(localRemoteIntegerArray);
/* 30:   */  }
/* 31:   */  
/* 45:   */  public final void b(short paramShort, int paramInt)
/* 46:   */  {
/* 47:   */    RemoteIntegerArray localRemoteIntegerArray;
/* 48:   */    
/* 61:61 */    (localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_a_of_type_LE.a())).set(0, Integer.valueOf(paramInt));
/* 62:62 */    localRemoteIntegerArray.set(1, Integer.valueOf(paramShort));
/* 63:63 */    this.jdField_a_of_type_LE.a().sellBuffer.add(localRemoteIntegerArray);
/* 64:   */  }
/* 65:   */  
/* 66:66 */  public final void a(short paramShort) { int i = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(paramShort) ? this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(paramShort) : 0;
/* 67:67 */    this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put(paramShort, i + 1);
/* 68:   */  }
/* 69:   */  
/* 70:   */  public final void a() {
/* 71:71 */    if (!this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.isEmpty()) {
/* 72:72 */      HashSet localHashSet = new HashSet();
/* 73:73 */      for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.keySet().iterator(); localIterator.hasNext();) { short s = ((Short)localIterator.next()).shortValue();
/* 74:74 */        mf localmf = this.jdField_a_of_type_LE.getInventory(null);
/* 75:   */        try {
/* 76:76 */          int i = localmf.b(s, this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(s));
/* 77:77 */          localHashSet.add(Integer.valueOf(i));
/* 78:   */        }
/* 79:   */        catch (NoSlotFreeException localNoSlotFreeException) {
/* 80:80 */          System.err.println("NO FREE SLOT [TODO send server message]");
/* 81:   */        }
/* 82:   */      }
/* 83:83 */      this.jdField_a_of_type_LE.sendInventoryModification(localHashSet, null);
/* 84:84 */      this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.clear();
/* 85:   */    }
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */