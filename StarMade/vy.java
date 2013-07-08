/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  3:   */import org.schema.game.common.data.element.ControlElementMap;
/*  4:   */import org.schema.game.server.controller.GameServerController;
/*  5:   */import org.schema.schine.network.StateInterface;
/*  6:   */import org.schema.schine.network.SynchronizationContainerController;
/*  7:   */
/* 19:   */public final class vy
/* 20:   */  extends vD
/* 21:   */{
/* 22:   */  private int b;
/* 23:   */  
/* 24:   */  public vy(StateInterface paramStateInterface, vc paramvc, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, q paramq1, q paramq2, String paramString3)
/* 25:   */  {
/* 26:26 */    super(paramStateInterface, paramvc, paramString1, paramString2, paramArrayOfFloat, paramq1, paramq2, paramString3);
/* 27:27 */    this.jdField_b_of_type_Int = paramInt;
/* 28:   */  }
/* 29:   */  
/* 34:   */  public final void a(int paramInt, boolean paramBoolean)
/* 35:   */  {
/* 36:36 */    ve.a(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_a_of_type_JavaLangString);
/* 37:37 */    String str2 = this.c;int i2 = this.jdField_b_of_type_Q.c;int i1 = this.jdField_b_of_type_Q.jdField_b_of_type_Int;int n = this.jdField_b_of_type_Q.a;int m = this.jdField_a_of_type_Q.c;int k = this.jdField_a_of_type_Q.jdField_b_of_type_Int;int j = this.jdField_a_of_type_Q.a;float[] arrayOfFloat = this.jdField_a_of_type_ArrayOfFloat;String str1 = this.jdField_b_of_type_JavaLangString;int i = paramInt;paramBoolean = this.jdField_a_of_type_JavaLangString;paramInt = this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface; jy localjy; (localjy = new jy(paramInt)).setUniqueIdentifier(paramBoolean);localjy.getMinPos().b(new q(j, k, m));localjy.getMaxPos().b(new q(n, i1, i2));localjy.setId(paramInt.getNextFreeObjectId());localjy.setSectorId(i);localjy.setRealName(str1);localjy.initialize();localjy.getInitialTransform().setFromOpenGLMatrix(arrayOfFloat);localjy.setSpawner(str2);(
/* 38:38 */      paramInt = localjy).setFactionId(this.jdField_b_of_type_Int);
/* 39:39 */    paramInt.getControlElementMap().set(this.jdField_a_of_type_Vc.a());
/* 40:40 */    ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a().addNewSynchronizedObjectQueued(paramInt);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public final void a(q paramq, vg paramvg, int paramInt, ObjectArrayList paramObjectArrayList) {}
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */