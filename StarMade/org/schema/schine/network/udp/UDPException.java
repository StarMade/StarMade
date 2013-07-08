package org.schema.schine.network.udp;

public class UDPException
  extends RuntimeException
{
  private static final long serialVersionUID = 2679247287365962074L;
  private Exception ioException;
  
  public UDPException(String paramString)
  {
    super(paramString);
  }
  
  public UDPException(String paramString, Exception paramException)
  {
    super(paramString);
    this.ioException = paramException;
  }
  
  public Exception getIoException()
  {
    return this.ioException;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */