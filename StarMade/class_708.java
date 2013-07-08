import com.bulletphysics.util.ObjectArrayList;

public class class_708
{
  private final ObjectArrayList observer = new ObjectArrayList();
  
  public void notifyObservers()
  {
    notifyObservers(null, null);
  }
  
  public void notifyObservers(Object paramObject)
  {
    notifyObservers(paramObject, null);
  }
  
  public void notifyObservers(Object paramObject1, Object paramObject2)
  {
    for (int i = 0; i < this.observer.size(); i++) {
      ((class_710)this.observer.get(i)).a4(this, paramObject1, paramObject2);
    }
  }
  
  public void addObserver(class_710 paramclass_710)
  {
    this.observer.add(paramclass_710);
  }
  
  public void deleteObserver(class_710 paramclass_710)
  {
    this.observer.remove(paramclass_710);
  }
  
  public void deleteObserver(int paramInt)
  {
    this.observer.remove(paramInt);
  }
  
  public void clearObservers()
  {
    this.observer.clear();
  }
  
  public int countObservers()
  {
    return this.observer.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_708
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */