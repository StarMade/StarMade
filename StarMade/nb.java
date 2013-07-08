/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.Iterator;
/*  3:   */import javax.swing.AbstractListModel;
/*  4:   */
/*  5:   */public final class nb extends AbstractListModel
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 1L;
/*  8:   */  private ArrayList a;
/*  9:   */  
/* 10:   */  public nb(ArrayList paramArrayList)
/* 11:   */  {
/* 12:12 */    this.a = paramArrayList;
/* 13:   */  }
/* 14:   */  
/* 17:   */  public final Object getElementAt(int paramInt)
/* 18:   */  {
/* 19:19 */    if ((paramInt < 0) || (paramInt >= getSize())) {
/* 20:20 */      localObject1 = "index, " + paramInt + ", is out of bounds for getSize() = " + getSize();
/* 21:   */      
/* 22:22 */      throw new IllegalArgumentException((String)localObject1);
/* 23:   */    }
/* 24:24 */    Object localObject1 = this.a.iterator();
/* 25:25 */    int i = 0;
/* 26:26 */    while (((Iterator)localObject1).hasNext()) {
/* 27:27 */      Object localObject2 = ((Iterator)localObject1).next();
/* 28:28 */      if (paramInt == i) {
/* 29:29 */        return localObject2;
/* 30:   */      }
/* 31:31 */      i++;
/* 32:   */    }
/* 33:   */    
/* 34:34 */    return null;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public final int getSize()
/* 38:   */  {
/* 39:39 */    return this.a.size();
/* 40:   */  }
/* 41:   */  
/* 42:   */  private int a(Object paramObject) {
/* 43:43 */    int i = 0;
/* 44:44 */    for (Iterator localIterator = this.a.iterator(); localIterator.hasNext();) {
/* 45:45 */      if (localIterator.next().equals(paramObject)) {
/* 46:46 */        return i;
/* 47:   */      }
/* 48:48 */      i++;
/* 49:   */    }
/* 50:50 */    return -1;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public final boolean a(Object paramObject) {
/* 54:   */    boolean bool;
/* 55:55 */    if ((bool = this.a.add(paramObject))) {
/* 56:56 */      paramObject = a(paramObject);
/* 57:57 */      fireIntervalAdded(this, paramObject, paramObject + 1);
/* 58:   */    }
/* 59:59 */    return bool;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public final boolean b(Object paramObject) {
/* 63:   */    int i;
/* 64:64 */    if ((i = a(paramObject)) < 0) {
/* 65:65 */      return false;
/* 66:   */    }
/* 67:67 */    paramObject = this.a.remove(paramObject);
/* 68:68 */    fireIntervalRemoved(this, i, i + 1);
/* 69:69 */    return paramObject;
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */