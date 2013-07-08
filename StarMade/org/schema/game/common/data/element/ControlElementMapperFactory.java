package org.schema.game.common.data.element;

import class_68;
import java.io.DataInputStream;

public class ControlElementMapperFactory
  implements class_68
{
  private static final long serialVersionUID = 8953098951065383692L;
  
  public Object create(DataInputStream paramDataInputStream)
  {
    ControlElementMapper localControlElementMapper = new ControlElementMapper();
    ControlElementMap.deserialize(paramDataInputStream, localControlElementMapper);
    return localControlElementMapper;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMapperFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */