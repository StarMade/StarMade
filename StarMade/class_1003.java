import java.io.Serializable;

public abstract class class_1003
  implements Serializable
{
  private static final long serialVersionUID = -8643354607753925915L;
  
  public boolean equals(Object paramObject)
  {
    return (paramObject != null) && (getClass().equals(paramObject.getClass()));
  }
  
  public int hashCode()
  {
    return getClass().hashCode();
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1003
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */