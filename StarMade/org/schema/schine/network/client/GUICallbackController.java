package org.schema.schine.network.client;

import class_1363;
import class_1412;
import class_939;
import java.util.ArrayList;

public class GUICallbackController
{
  private final ArrayList guiCallbacks = new ArrayList();
  private final ArrayList callingGUIElements = new ArrayList();
  
  public void addCallback(class_1412 paramclass_1412, class_1363 paramclass_1363)
  {
    int i;
    if (((i = this.guiCallbacks.indexOf(paramclass_1412)) < 0) || (i != this.callingGUIElements.indexOf(paramclass_1363)))
    {
      this.guiCallbacks.add(paramclass_1412);
      this.callingGUIElements.add(paramclass_1363);
    }
  }
  
  public void execute(class_939 paramclass_939)
  {
    assert (this.guiCallbacks.size() == this.callingGUIElements.size());
    for (int i = 0; i < this.guiCallbacks.size(); i++)
    {
      class_1412 localclass_1412 = (class_1412)this.guiCallbacks.get(i);
      class_1363 localclass_1363 = (class_1363)this.callingGUIElements.get(i);
      localclass_1412.a(localclass_1363, paramclass_939);
    }
  }
  
  public void reset()
  {
    this.guiCallbacks.clear();
    this.callingGUIElements.clear();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.GUICallbackController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */