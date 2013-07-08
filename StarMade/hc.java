/*   1:    */import org.schema.schine.network.client.ClientState;
/*   2:    */
/* 159:    */final class hc
/* 160:    */  extends yw
/* 161:    */{
/* 162:    */  hc(ha paramha, ClientState paramClientState)
/* 163:    */  {
/* 164:164 */    super(paramClientState);
/* 165:    */  }
/* 166:    */  
/* 167:    */  protected final boolean b() {
/* 168:168 */    return this.a.jdField_a_of_type_Boolean;
/* 169:    */  }
/* 170:    */  
/* 172:    */  protected final void f()
/* 173:    */  {
/* 174:174 */    if (this.a.jdField_a_of_type_Int == 4) {
/* 175:175 */      ((ct)a()).a().b("Cannot modify rights\nof admin role");return;
/* 176:    */    }
/* 177:    */    
/* 181:181 */    this.a.jdField_a_of_type_Boolean = false;
/* 182:    */  }
/* 183:    */  
/* 186:    */  protected final void e()
/* 187:    */  {
/* 188:188 */    this.a.jdField_a_of_type_Boolean = true;
/* 189:    */  }
/* 190:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */