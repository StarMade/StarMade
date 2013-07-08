/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import java.util.ArrayList;
/*   3:    */import org.schema.game.common.controller.SegmentController;
/*   4:    */import org.schema.schine.network.NetworkStateContainer;
/*   5:    */import org.schema.schine.network.StateInterface;
/*   6:    */
/* 156:    */final class jt
/* 157:    */  implements Runnable
/* 158:    */{
/* 159:    */  jt(js paramjs) {}
/* 160:    */  
/* 161:    */  public final void run()
/* 162:    */  {
/* 163:    */    try
/* 164:    */    {
/* 165:    */      for (;;)
/* 166:    */      {
/* 167:167 */        js.a(this.a);
/* 168:168 */        int i = 0;
/* 169:169 */        for (int j = 0; j < js.a(this.a).size(); j++) {
/* 170:    */          SegmentController localSegmentController;
/* 171:171 */          if (((localSegmentController = (SegmentController)js.a(this.a).get(j)).getCreatorThread() != null) && (((ct)js.a(this.a)).a().containsKey(localSegmentController.getId()))) { kG localkG;
/* 172:172 */            if (System.currentTimeMillis() - localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a > 10000L) { localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().b();localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a = System.currentTimeMillis(); }
/* 173:173 */            if ((((localkG = localSegmentController.getCreatorThread()).jdField_a_of_type_Boolean) && (!xm.a()) ? localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().b() : 0)) {
/* 174:174 */              i = 1;
/* 175:175 */              Thread.sleep(5L);
/* 176:    */            }
/* 177:    */          }
/* 178:    */          try {
/* 179:179 */            if (!js.a(this.a).getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localSegmentController.getId())) {
/* 180:180 */              js.a(this.a).remove(j);
/* 181:181 */              j--;
/* 182:    */            }
/* 183:183 */          } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) { 
/* 184:    */            
/* 186:186 */              localArrayIndexOutOfBoundsException;
/* 187:    */          }
/* 188:    */        }
/* 189:    */        
/* 191:188 */        if (i == 0)
/* 192:189 */          Thread.sleep(20L);
/* 193:    */      }
/* 194:    */    } catch (InterruptedException localInterruptedException) {
/* 195:192 */      localInterruptedException;
/* 196:    */    }
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */