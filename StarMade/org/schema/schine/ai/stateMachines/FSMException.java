package org.schema.schine.ai.stateMachines;

import class_1003;
import class_993;
import class_999;

public class FSMException
  extends Exception
{
  private static final long serialVersionUID = -4217837268845972600L;
  
  public FSMException(class_999 paramclass_999, class_1003 paramclass_1003)
  {
    super("ERR: Transition from \"" + paramclass_999 + "\" --" + paramclass_1003 + "--> \"newState\" failed | not found in " + paramclass_999 + ". inputs: " + paramclass_999.a10().field_1277 + ", outputs " + paramclass_999.a10().field_1278);
  }
  
  public FSMException(String paramString)
  {
    super(paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.ai.stateMachines.FSMException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */