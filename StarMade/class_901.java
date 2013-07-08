import java.util.LinkedList;

final class class_901
{
  LinkedList field_1128 = new LinkedList();
  
  final boolean a(Object paramObject)
  {
    synchronized (this.field_1128)
    {
      return this.field_1128.contains(paramObject);
    }
  }
  
  final Object a1()
  {
    synchronized (this.field_1128)
    {
      while (this.field_1128.isEmpty()) {
        this.field_1128.wait();
      }
      return this.field_1128.removeFirst();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_901
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */