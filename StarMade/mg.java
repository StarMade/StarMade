/*    */ import it.unimi.dsi.fastutil.shorts.Short2IntOpenHashMap;
/*    */ import it.unimi.dsi.fastutil.shorts.ShortSet;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*    */ import org.schema.game.network.objects.NetworkPlayer;
/*    */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class mg
/*    */ {
/*    */   private lE jdField_a_of_type_LE;
/* 16 */   private Short2IntOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap = new Short2IntOpenHashMap();
/*    */ 
/*    */   public mg(lE paramlE) {
/* 19 */     this.jdField_a_of_type_LE = paramlE;
/* 20 */     if ((paramlE.getState() instanceof ServerStateInterface)) {
/* 21 */       paramlE = this; if ((!jdField_a_of_type_Boolean) && (!paramlE.jdField_a_of_type_LE.getInventory(null).c())) throw new AssertionError(); paramlE.jdField_a_of_type_LE.getInventory(null).b(0, (short)5, 25); paramlE.jdField_a_of_type_LE.getInventory(null).b(1, (short)2, 6); paramlE.jdField_a_of_type_LE.getInventory(null).b(2, (short)8, 6); paramlE.jdField_a_of_type_LE.getInventory(null).b(3, (short)6, 1); paramlE.jdField_a_of_type_LE.getInventory(null).b(4, (short)16, 20); paramlE.jdField_a_of_type_LE.getInventory(null).b(10, (short)1, 1);
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void a(short paramShort, int paramInt)
/*    */   {
/*    */     RemoteIntegerArray localRemoteIntegerArray;
/* 25 */     (
/* 27 */       localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_a_of_type_LE.a()))
/* 27 */       .set(0, Integer.valueOf(paramInt));
/* 28 */     localRemoteIntegerArray.set(1, Integer.valueOf(paramShort));
/* 29 */     this.jdField_a_of_type_LE.a().buyBuffer.add(localRemoteIntegerArray);
/*    */   }
/*    */ 
/*    */   public final void b(short paramShort, int paramInt)
/*    */   {
/*    */     RemoteIntegerArray localRemoteIntegerArray;
/* 58 */     (
/* 61 */       localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_a_of_type_LE.a()))
/* 61 */       .set(0, Integer.valueOf(paramInt));
/* 62 */     localRemoteIntegerArray.set(1, Integer.valueOf(paramShort));
/* 63 */     this.jdField_a_of_type_LE.a().sellBuffer.add(localRemoteIntegerArray);
/*    */   }
/*    */   public final void a(short paramShort) {
/* 66 */     int i = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(paramShort) ? this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(paramShort) : 0;
/* 67 */     this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put(paramShort, i + 1);
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 71 */     if (!this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.isEmpty()) {
/* 72 */       HashSet localHashSet = new HashSet();
/* 73 */       for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.keySet().iterator(); localIterator.hasNext(); ) { short s = ((Short)localIterator.next()).shortValue();
/* 74 */         mf localmf = this.jdField_a_of_type_LE.getInventory(null);
/*    */         try {
/* 76 */           int i = localmf.b(s, this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(s));
/* 77 */           localHashSet.add(Integer.valueOf(i));
/*    */         }
/*    */         catch (NoSlotFreeException localNoSlotFreeException) {
/* 80 */           System.err.println("NO FREE SLOT [TODO send server message]");
/*    */         }
/*    */       }
/* 83 */       this.jdField_a_of_type_LE.sendInventoryModification(localHashSet, null);
/* 84 */       this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.clear();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mg
 * JD-Core Version:    0.6.2
 */