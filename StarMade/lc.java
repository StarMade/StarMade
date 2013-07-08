/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import java.io.IOException;
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.HashSet;
/*  6:   */import java.util.Iterator;
/*  7:   */import javax.vecmath.Vector3f;
/*  8:   */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  9:   */import org.schema.game.common.data.world.Segment;
/* 10:   */import org.schema.game.network.objects.NetworkSegmentController;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 12:   */import org.schema.schine.network.objects.remote.RemoteVector3i;
/* 13:   */
/* 20:   */public final class lc
/* 21:   */  implements Runnable
/* 22:   */{
/* 23:   */  private ArrayList jdField_a_of_type_JavaUtilArrayList;
/* 24:   */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/* 25:   */  private float jdField_a_of_type_Float;
/* 26:   */  private float b;
/* 27:   */  private lb jdField_a_of_type_Lb;
/* 28:   */  private EditableSendableSegmentController jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController;
/* 29:   */  private boolean jdField_a_of_type_Boolean;
/* 30:   */  
/* 31:   */  public lc(EditableSendableSegmentController paramEditableSendableSegmentController, Transform paramTransform, float paramFloat1, float paramFloat2, lb paramlb)
/* 32:   */  {
/* 33:33 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
/* 34:34 */    this.jdField_a_of_type_Float = paramFloat1;
/* 35:35 */    this.b = paramFloat2;
/* 36:36 */    this.jdField_a_of_type_Lb = paramlb;
/* 37:37 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController = paramEditableSendableSegmentController;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public final void run()
/* 41:   */  {
/* 42:42 */    new HashSet();
/* 43:   */    
/* 44:44 */    long l1 = System.currentTimeMillis();
/* 45:   */    
/* 46:   */    try
/* 47:   */    {
/* 48:48 */      this.jdField_a_of_type_JavaUtilArrayList = this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getRadius(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_a_of_type_Float);
/* 49:   */      
/* 50:50 */      long l2 = System.currentTimeMillis() - l1;
/* 51:51 */      Vector3f localVector3f = new Vector3f(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 52:   */      
/* 55:55 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getWorldTransformInverse().transform(localVector3f);
/* 56:56 */      localVector3f.x += 8.0F;
/* 57:57 */      localVector3f.y += 8.0F;
/* 58:58 */      localVector3f.z += 8.0F;
/* 59:59 */      for (Iterator localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext();) {
/* 60:60 */        ((Segment)localIterator.next()).a(this.b, localVector3f, this.jdField_a_of_type_Float);
/* 61:   */      }
/* 62:   */      
/* 63:63 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getSegmentBuffer().a(kd.a, true).a() <= 0) {
/* 64:64 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.setFlagCoreDestroyedByExplosion(this.jdField_a_of_type_Lb != null ? this.jdField_a_of_type_Lb : new Object());
/* 65:   */      }
/* 66:66 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.setFlagCharacterExitCheckByExplosion(true);
/* 67:   */      
/* 69:69 */      long l3 = System.currentTimeMillis() - l1 - l2;
/* 70:   */      
/* 71:71 */      long l4 = System.currentTimeMillis() - l1 - l2 - l3;
/* 72:   */      
/* 73:73 */      System.err.println("[EXPLOSION] " + this + " SERVER EXPLOSION HANDLE: TOTAL: " + (System.currentTimeMillis() - l1) + " HITS:" + this.jdField_a_of_type_JavaUtilArrayList.size() + ": " + this.jdField_a_of_type_Lb + "->" + this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController + "; ExplosionRadius " + this.jdField_a_of_type_Float + " TIMES RADIUS: " + l2 + "; HANDLE: " + l3 + "; REMOTE: " + l4 + ": dam 0; set 0; rem 0");
/* 74:   */    }
/* 75:   */    catch (IOException localIOException) {
/* 76:76 */      
/* 77:   */      
/* 80:80 */        localIOException;
/* 81:   */    } catch (InterruptedException localInterruptedException) {
/* 82:78 */      
/* 83:   */      
/* 84:80 */        localInterruptedException;
/* 85:   */    }
/* 86:   */    
/* 87:81 */    this.jdField_a_of_type_Boolean = true;
/* 88:   */  }
/* 89:   */  
/* 90:   */  public final void a() {
/* 91:85 */    for (Segment localSegment : this.jdField_a_of_type_JavaUtilArrayList) {
/* 92:86 */      synchronized (this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject()) {
/* 93:87 */        ((mw)localSegment).a(System.currentTimeMillis());
/* 94:88 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().dirtySegmentBuffer.add(new RemoteVector3i(localSegment.a, this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject()));
/* 95:   */      }
/* 96:   */    }
/* 97:   */  }
/* 98:   */  
/* 101:   */  public final boolean a()
/* 102:   */  {
/* 103:97 */    return this.jdField_a_of_type_Boolean;
/* 104:   */  }
/* 105:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */