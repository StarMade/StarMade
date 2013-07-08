package org.schema.game.server.data.admin;

public class UnknownCategoryException
  extends Exception
{
  private static final long serialVersionUID = -2713992827958593228L;
  private String field_1771;
  
  public UnknownCategoryException(String paramString)
  {
    this.field_1771 = paramString;
  }
  
  public final String a()
  {
    return this.field_1771;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.server.data.admin.UnknownCategoryException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */