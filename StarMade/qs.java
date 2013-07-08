/*  1:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  2:   */import java.util.ArrayList;
/*  3:   */import java.util.Collections;
/*  4:   */import javax.swing.AbstractListModel;
/*  5:   */import org.schema.schine.network.NetworkStateContainer;
/*  6:   */import org.schema.schine.network.objects.Sendable;
/*  7:   */
/*  9:   */public final class qs
/* 10:   */  extends AbstractListModel
/* 11:   */{
/* 12:12 */  private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 13:   */  private ct jdField_a_of_type_Ct;
/* 14:   */  
/* 15:   */  public qs(ct paramct) {
/* 16:16 */    this.jdField_a_of_type_Ct = paramct;
/* 17:   */    
/* 18:18 */    a();
/* 19:   */  }
/* 20:   */  
/* 21:21 */  public final void a() { this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 22:22 */    for (Sendable localSendable : this.jdField_a_of_type_Ct.getLocalAndRemoteObjectContainer().getLocalObjects().values()) {
/* 23:23 */      this.jdField_a_of_type_JavaUtilArrayList.add(localSendable);
/* 24:   */    }
/* 25:   */    
/* 26:26 */    Collections.sort(this.jdField_a_of_type_JavaUtilArrayList, new qt());
/* 27:   */    
/* 35:35 */    fireContentsChanged(this, 0, this.jdField_a_of_type_JavaUtilArrayList.size());
/* 36:   */  }
/* 37:   */  
/* 38:   */  public final Object getElementAt(int paramInt)
/* 39:   */  {
/* 40:   */    try
/* 41:   */    {
/* 42:42 */      return this.jdField_a_of_type_JavaUtilArrayList.get(paramInt);
/* 43:43 */    } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) { localIndexOutOfBoundsException;
/* 44:   */    }
/* 45:45 */    return null;
/* 46:   */  }
/* 47:   */  
/* 49:   */  public final int getSize()
/* 50:   */  {
/* 51:51 */    return this.jdField_a_of_type_JavaUtilArrayList.size();
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */