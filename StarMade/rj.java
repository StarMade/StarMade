/*  1:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  2:   */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.Collections;
/*  5:   */import java.util.Iterator;
/*  6:   */import javax.swing.AbstractListModel;
/*  7:   */import org.schema.schine.network.NetworkStateContainer;
/*  8:   */import org.schema.schine.network.objects.Sendable;
/*  9:   */
/* 15:   */public final class rj
/* 16:   */  extends AbstractListModel
/* 17:   */{
/* 18:   */  private static final long serialVersionUID = -2709488666387980490L;
/* 19:19 */  private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 20:   */  private ct jdField_a_of_type_Ct;
/* 21:   */  
/* 22:   */  public rj(ct paramct) {
/* 23:23 */    this.jdField_a_of_type_Ct = paramct;
/* 24:   */    
/* 25:25 */    a();
/* 26:   */  }
/* 27:   */  
/* 28:28 */  public final void a() { this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 29:29 */    for (Iterator localIterator = this.jdField_a_of_type_Ct.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/* 30:30 */      if (((localSendable = (Sendable)localIterator.next()) instanceof lE)) {
/* 31:31 */        this.jdField_a_of_type_JavaUtilArrayList.add((lE)localSendable);
/* 32:   */      }
/* 33:   */    }
/* 34:   */    
/* 35:35 */    Collections.sort(this.jdField_a_of_type_JavaUtilArrayList, new rk());
/* 36:   */    
/* 44:44 */    fireContentsChanged(this, 0, this.jdField_a_of_type_JavaUtilArrayList.size());
/* 45:   */  }
/* 46:   */  
/* 47:   */  public final Object getElementAt(int paramInt)
/* 48:   */  {
/* 49:   */    try
/* 50:   */    {
/* 51:51 */      return this.jdField_a_of_type_JavaUtilArrayList.get(paramInt);
/* 52:52 */    } catch (Exception localException) { localException;
/* 53:   */    }
/* 54:   */    
/* 55:55 */    return "Exception";
/* 56:   */  }
/* 57:   */  
/* 58:   */  public final int getSize()
/* 59:   */  {
/* 60:60 */    return this.jdField_a_of_type_JavaUtilArrayList.size();
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */