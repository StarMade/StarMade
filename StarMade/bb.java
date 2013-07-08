/*  1:   */import java.io.PrintStream;
/*  2:   */
/*  9:   */public final class bb
/* 10:   */  extends H
/* 11:   */{
/* 12:   */  private lP jdField_a_of_type_LP;
/* 13:   */  private lP b;
/* 14:   */  private gX jdField_a_of_type_GX;
/* 15:   */  
/* 16:   */  public bb(ct paramct, lP paramlP1, lP paramlP2)
/* 17:   */  {
/* 18:18 */    super(paramct);
/* 19:19 */    this.jdField_a_of_type_LP = paramlP1;
/* 20:20 */    this.b = paramlP2;
/* 21:   */    
/* 22:22 */    this.jdField_a_of_type_GX = new gX(paramct, paramlP1, paramlP2, this);
/* 23:23 */    this.jdField_a_of_type_GX.e();
/* 24:   */  }
/* 25:   */  
/* 27:   */  public final void a(yz paramyz, xp paramxp)
/* 28:   */  {
/* 29:29 */    if (paramxp.a()) {
/* 30:30 */      if (paramyz.b().equals("OK")) {
/* 31:31 */        d();return; }
/* 32:32 */      if ((paramyz.b().equals("CANCEL")) || (paramyz.b().equals("X"))) {
/* 33:33 */        System.err.println("CANCEL");
/* 34:34 */        d(); return; }
/* 35:35 */      lP locallP; if (paramyz.b().equals("WAR")) {
/* 36:36 */        locallP = this.b;paramxp = this.jdField_a_of_type_LP;(paramyz = this.a.a().a.a.a).e(true);new aT(paramyz, paramyz.a(), "Declare war", "Enter a message for your declaration", paramxp, locallP).c();
/* 37:   */        
/* 38:38 */        d();return; }
/* 39:39 */      if (paramyz.b().equals("PEACE")) {
/* 40:40 */        locallP = this.b;paramxp = this.jdField_a_of_type_LP;(paramyz = this.a.a().a.a.a).e(true);new aU(paramyz, paramyz.a(), "Peace Treaty Offer", "Enter a message for the offer", paramxp, locallP).c();
/* 41:   */        
/* 42:42 */        d();return; }
/* 43:43 */      if (paramyz.b().equals("ALLY")) {
/* 44:44 */        locallP = this.b;paramxp = this.jdField_a_of_type_LP;(paramyz = this.a.a().a.a.a).e(true);new aW(paramyz, paramyz.a(), "Alliance Offer", "Enter a message for the offer", paramxp, locallP).c();
/* 45:   */        
/* 46:46 */        d();
/* 47:   */      }
/* 48:   */    }
/* 49:   */  }
/* 50:   */  
/* 52:   */  public final boolean a()
/* 53:   */  {
/* 54:54 */    return false;
/* 55:   */  }
/* 56:   */  
/* 62:   */  public final void handleKeyEvent() {}
/* 63:   */  
/* 69:   */  public final yz a()
/* 70:   */  {
/* 71:71 */    return this.jdField_a_of_type_GX;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public final void a()
/* 75:   */  {
/* 76:76 */    this.a.a().a.a.a.a(500);
/* 77:   */    
/* 78:78 */    this.a.a().a.a.a.e(false);
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */