/*  1:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  2:   */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*  3:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  4:   */import it.unimi.dsi.fastutil.shorts.ShortOpenHashSet;
/*  5:   */import java.util.ArrayList;
/*  6:   */import java.util.Iterator;
/*  7:   */import org.schema.game.network.objects.NetworkClientChannel;
/*  8:   */import org.schema.game.network.objects.remote.RemoteMissileUpdate;
/*  9:   */import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteShort;
/* 12:   */
/* 23:   */public final class lj
/* 24:   */{
/* 25:   */  private ct jdField_a_of_type_Ct;
/* 26:26 */  private Short2ObjectArrayMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap = new Short2ObjectArrayMap();
/* 27:27 */  private ShortOpenHashSet jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet = new ShortOpenHashSet();
/* 28:   */  
/* 29:29 */  private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 30:   */  
/* 31:   */  public lj(ct paramct) {
/* 32:32 */    this.jdField_a_of_type_Ct = paramct;
/* 33:   */  }
/* 34:   */  
/* 38:   */  public final void a(xq paramxq, t paramt)
/* 39:   */  {
/* 40:40 */    Object localObject = paramt;paramt = this; for (Iterator localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); ((lw)localIterator.next()).a(paramt.jdField_a_of_type_Ct, paramt.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap, (t)localObject)) {} paramt.jdField_a_of_type_JavaUtilArrayList.clear();
/* 41:   */    
/* 42:42 */    for (paramt = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap.values().iterator(); paramt.hasNext();) { localObject = (ln)paramt.next();
/* 43:43 */      if (this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.size() > 0) {
/* 44:44 */        this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.remove(((ln)localObject).a());
/* 45:   */      }
/* 46:   */      
/* 47:47 */      ((ln)localObject).b(paramxq);
/* 48:48 */      ((ln)localObject).a(this.jdField_a_of_type_Ct.a(), this.jdField_a_of_type_Ct.a().a());
/* 49:   */    }
/* 50:   */  }
/* 51:   */  
/* 52:   */  public final void a(NetworkClientChannel paramNetworkClientChannel) {
/* 53:53 */    for (int i = 0; i < paramNetworkClientChannel.missileUpdateBuffer.getReceiveBuffer().size(); i++) {
/* 54:54 */      lw locallw = (lw)((RemoteMissileUpdate)paramNetworkClientChannel.missileUpdateBuffer.getReceiveBuffer().get(i)).get();
/* 55:   */      
/* 56:56 */      this.jdField_a_of_type_JavaUtilArrayList.add(locallw);
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 65:   */  public final void a(short paramShort, t paramt)
/* 66:   */  {
/* 67:67 */    if (!this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.contains(paramShort)) {
/* 68:68 */      this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.add(paramShort);
/* 69:69 */      paramt.a().missileMissingRequestBuffer.add(new RemoteShort(paramShort, false));
/* 70:   */    }
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */