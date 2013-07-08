/*  1:   */import org.schema.schine.network.client.ClientState;
/*  2:   */
/* 11:   */public final class gx
/* 12:   */  extends eX
/* 13:   */{
/* 14:   */  mF a;
/* 15:   */  
/* 16:   */  public gx(ClientState paramClientState, aN paramaN, mF parammF)
/* 17:   */  {
/* 18:18 */    super(paramClientState, paramaN, "Faction Block Config", "");
/* 19:19 */    this.jdField_a_of_type_MF = parammF;
/* 20:   */  }
/* 21:   */  
/* 25:   */  public final void c()
/* 26:   */  {
/* 27:27 */    super.c();
/* 28:   */    
/* 29:   */    yN localyN1;
/* 30:30 */    (localyN1 = new yN(a(), 200, 20, "Reset Faction Signitaure", this.jdField_a_of_type_Ys)).jdField_a_of_type_JavaLangObject = "NEUTRAL";
/* 31:   */    yN localyN2;
/* 32:32 */    (localyN2 = new yN(a(), 200, 20, "Enter Faction Signiture", this.jdField_a_of_type_Ys)).jdField_a_of_type_JavaLangObject = "FACTION";
/* 33:33 */    localyN2.a().y = 30.0F;
/* 34:   */    
/* 43:   */    gy localgy;
/* 44:   */    
/* 53:53 */    (localgy = new gy(this, a(), "Make Faction Home", this.jdField_a_of_type_Ys)).jdField_a_of_type_JavaLangObject = "HOMEBASE";
/* 54:54 */    localgy.a().y = 60.0F;
/* 55:55 */    this.jdField_a_of_type_Yr.a(localgy);
/* 56:   */    
/* 65:65 */    this.jdField_a_of_type_Yr.a(localyN1);
/* 66:66 */    this.jdField_a_of_type_Yr.a(localyN2);
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */