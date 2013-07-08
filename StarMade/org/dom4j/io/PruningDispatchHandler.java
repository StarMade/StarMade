package org.dom4j.io;

import org.dom4j.Element;
import org.dom4j.ElementPath;

class PruningDispatchHandler
  extends DispatchHandler
{
  public void onEnd(ElementPath elementPath)
  {
    super.onEnd(elementPath);
    if (getActiveHandlerCount() == 0) {
      elementPath.getCurrent().detach();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.PruningDispatchHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */