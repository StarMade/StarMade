/*  1:   */import java.io.Serializable;
/*  2:   */
/* 68:   */public abstract class wv
/* 69:   */  implements Serializable
/* 70:   */{
/* 71:   */  private static final long serialVersionUID = -8643354607753925915L;
/* 72:   */  
/* 73:   */  public boolean equals(Object paramObject)
/* 74:   */  {
/* 75:75 */    return (paramObject != null) && (getClass().equals(paramObject.getClass()));
/* 76:   */  }
/* 77:   */  
/* 78:   */  public int hashCode()
/* 79:   */  {
/* 80:80 */    return getClass().hashCode();
/* 81:   */  }
/* 82:   */  
/* 86:   */  public String toString()
/* 87:   */  {
/* 88:88 */    return getClass().getSimpleName();
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */