/*   1:    */import java.io.Serializable;
/*   2:    */import org.schema.schine.ai.stateMachines.FSMException;
/*   3:    */
/*  71:    */public abstract class ws
/*  72:    */  implements Serializable
/*  73:    */{
/*  74:    */  private static final long serialVersionUID = -6064345513645124981L;
/*  75:    */  private wt jdField_a_of_type_Wt;
/*  76:    */  private wr jdField_a_of_type_Wr;
/*  77:    */  public wk a;
/*  78:    */  private wl jdField_a_of_type_Wl;
/*  79:    */  
/*  80:    */  public ws(wk paramwk, wl paramwl)
/*  81:    */  {
/*  82: 82 */    if ((!jdField_a_of_type_Boolean) && (paramwk == null)) { throw new AssertionError();
/*  83:    */    }
/*  84: 84 */    this.jdField_a_of_type_Wk = paramwk;
/*  85:    */    
/*  86: 86 */    this.jdField_a_of_type_Wl = paramwl;
/*  87:    */    
/*  88: 88 */    a();
/*  89:    */  }
/*  90:    */  
/* 102:    */  public abstract void a();
/* 103:    */  
/* 114:    */  public final wr a()
/* 115:    */  {
/* 116:116 */    return this.jdField_a_of_type_Wr;
/* 117:    */  }
/* 118:    */  
/* 123:    */  public wl a()
/* 124:    */  {
/* 125:125 */    return this.jdField_a_of_type_Wl;
/* 126:    */  }
/* 127:    */  
/* 158:    */  public final void a(wt paramwt)
/* 159:    */  {
/* 160:160 */    b(paramwt);
/* 161:161 */    this.jdField_a_of_type_Wr = new wr(paramwt, this);
/* 162:    */  }
/* 163:    */  
/* 168:    */  protected final void b(wt paramwt)
/* 169:    */  {
/* 170:170 */    if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Wk == null)) throw new AssertionError();
/* 171:171 */    this.jdField_a_of_type_Wt = paramwt;
/* 172:    */  }
/* 173:    */  
/* 178:    */  public String toString()
/* 179:    */  {
/* 180:180 */    return getClass().getSimpleName();
/* 181:    */  }
/* 182:    */  
/* 187:    */  public final void b()
/* 188:    */  {
/* 189:189 */    if (this.jdField_a_of_type_Wt == null) {
/* 190:190 */      throw new FSMException("[CRITICAL] no state set! please set the FiniteStateMachine.setStartState(State state) Method in createFSM()");
/* 191:    */    }
/* 192:    */    
/* 193:    */    wt localwt;
/* 194:    */    
/* 195:195 */    if ((localwt = this.jdField_a_of_type_Wt).e())
/* 196:    */    {
/* 197:197 */      this.jdField_a_of_type_Wt.c();
/* 198:198 */      localwt.a(false);return;
/* 199:    */    }
/* 200:    */    
/* 202:202 */    this.jdField_a_of_type_Wt.d();
/* 203:    */  }
/* 204:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ws
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */