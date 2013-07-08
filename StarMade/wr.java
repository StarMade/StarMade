/*   1:    */import java.io.PrintStream;
/*   2:    */import java.io.Serializable;
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Vector;
/*   5:    */import org.schema.schine.ai.stateMachines.FSMException;
/*   6:    */
/*  51:    */public final class wr
/*  52:    */  implements Serializable
/*  53:    */{
/*  54:    */  private static final long serialVersionUID = 1860839739177998472L;
/*  55:    */  wt jdField_a_of_type_Wt;
/*  56:    */  private ws jdField_a_of_type_Ws;
/*  57:    */  
/*  58:    */  public wr(wt paramwt, ws paramws)
/*  59:    */  {
/*  60: 60 */    new HashMap();
/*  61:    */    
/*  78: 78 */    this.jdField_a_of_type_Wt = paramwt;
/*  79: 79 */    this.jdField_a_of_type_Ws = paramws;
/*  80: 80 */    new wu();
/*  81:    */  }
/*  82:    */  
/*  87:    */  public final wt a()
/*  88:    */  {
/*  89: 89 */    return this.jdField_a_of_type_Wt;
/*  90:    */  }
/*  91:    */  
/* 138:    */  public final wt a(wv paramwv)
/* 139:    */  {
/* 140:140 */    if (this.jdField_a_of_type_Wt == null) {
/* 141:141 */      throw new FSMException("ERROR (FSMclass): CURRENT STATE NOT FOUND " + this.jdField_a_of_type_Wt);
/* 142:    */    }
/* 143:    */    
/* 145:145 */    if (this.jdField_a_of_type_Wt != this.jdField_a_of_type_Wt.a().a()) {
/* 146:146 */      throw new FSMException("ERROR The State <" + this.jdField_a_of_type_Wt + "> of gameObject [" + this.jdField_a_of_type_Wt.a() + "] is unequal with the firering state <" + this.jdField_a_of_type_Wt.a().a().toString() + ">");
/* 147:    */    }
/* 148:    */    
/* 153:    */    wq localwq;
/* 154:    */    
/* 158:158 */    wv localwv = paramwv; Object localObject; int i; if ((i = (localObject = localwq = this.jdField_a_of_type_Wt.a()).jdField_a_of_type_JavaUtilVector.indexOf(localwv)) < 0) { throw new FSMException(((wq)localObject).jdField_a_of_type_Wt, localwv);
/* 159:    */    }
/* 160:160 */    if ((localObject = (wt)((wq)localObject).b.get(i)) == null) {
/* 161:161 */      System.err.println("could not set state: discarding");
/* 162:162 */      throw new FSMException(this.jdField_a_of_type_Wt, paramwv);
/* 163:    */    }
/* 164:164 */    if (localObject == this.jdField_a_of_type_Wt)
/* 165:    */    {
/* 168:168 */      this.jdField_a_of_type_Wt.b();
/* 169:169 */      this.jdField_a_of_type_Wt.a(true);
/* 170:170 */      return this.jdField_a_of_type_Wt;
/* 171:    */    }
/* 172:    */    
/* 181:181 */    this.jdField_a_of_type_Wt.b();
/* 182:182 */    this.jdField_a_of_type_Wt = ((wt)localObject);
/* 183:183 */    this.jdField_a_of_type_Ws.b((wt)localObject);
/* 184:184 */    this.jdField_a_of_type_Wt.a(true);
/* 185:185 */    this.jdField_a_of_type_Ws.a();wl.g();
/* 186:    */    
/* 189:189 */    return this.jdField_a_of_type_Wt;
/* 190:    */  }
/* 191:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */