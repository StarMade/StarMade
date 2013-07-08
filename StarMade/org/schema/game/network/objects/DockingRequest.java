package org.schema.game.network.objects;

import class_48;

public class DockingRequest
{
  public boolean dock;
  public String field_1858;
  public class_48 pos;
  
  public DockingRequest() {}
  
  public DockingRequest(boolean paramBoolean, String paramString, class_48 paramclass_48)
  {
    set(paramBoolean, paramString, paramclass_48);
  }
  
  public void set(boolean paramBoolean, String paramString, class_48 paramclass_48)
  {
    this.dock = paramBoolean;
    this.field_1858 = paramString;
    this.pos = paramclass_48;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.DockingRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */