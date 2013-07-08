/*  1:   */package org.schema.schine.ai.stateMachines;
/*  2:   */
/*  3:   */import wq;
/*  4:   */import wt;
/*  5:   */import wv;
/*  6:   */
/* 56:   */public class FSMException
/* 57:   */  extends Exception
/* 58:   */{
/* 59:   */  private static final long serialVersionUID = -4217837268845972600L;
/* 60:   */  
/* 61:   */  public FSMException(wt paramwt, wv paramwv)
/* 62:   */  {
/* 63:63 */    super("ERR: Transition from \"" + paramwt + "\" --" + paramwv + "--> \"newState\" failed | not found in " + paramwt + ". inputs: " + paramwt.a().a + ", outputs " + paramwt.a().b);
/* 64:   */  }
/* 65:   */  
/* 71:   */  public FSMException(String paramString)
/* 72:   */  {
/* 73:73 */    super(paramString);
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.ai.stateMachines.FSMException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */