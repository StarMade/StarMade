/*    1:     */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*    2:     */import java.util.ArrayList;
/*    3:     */
/* 1259:     */final class z
/* 1260:     */  implements Runnable
/* 1261:     */{
/* 1262:     */  z(x paramx, mF[] paramArrayOfmF, IntOpenHashSet paramIntOpenHashSet) {}
/* 1263:     */  
/* 1264:     */  public final void run()
/* 1265:     */  {
/* 1266:1266 */    synchronized (x.a(this.jdField_a_of_type_X))
/* 1267:     */    {
/* 1268:1268 */      for (mF localmF : this.jdField_a_of_type_ArrayOfMF) {
/* 1269:1269 */        if ((!this.jdField_a_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.contains(localmF.getSectorId())) && ((localmF instanceof ka)))
/* 1270:     */        {
/* 1271:1271 */          ((ka)localmF).writeAllBufferedSegmentsToDatabase();
/* 1272:     */          
/* 1273:1273 */          synchronized (x.a(this.jdField_a_of_type_X)) {
/* 1274:1274 */            x.a(this.jdField_a_of_type_X).add((ka)localmF);
/* 1275:     */          }
/* 1276:     */        }
/* 1277:     */      }
/* 1278:     */      
/* 1280:1280 */      return;
/* 1281:     */    }
/* 1282:     */  }
/* 1283:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     z
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */