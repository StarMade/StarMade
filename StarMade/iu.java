/*  1:   */import java.util.ArrayList;
/*  2:   */import org.newdawn.slick.Color;
/*  3:   */import org.newdawn.slick.UnicodeFont;
/*  4:   */import org.schema.game.common.data.element.ElementInformation;
/*  5:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  6:   */import org.schema.schine.network.client.ClientState;
/*  7:   */
/* 14:   */public final class iu
/* 15:   */  extends yz
/* 16:   */{
/* 17:   */  ArrayList jdField_a_of_type_JavaUtilArrayList;
/* 18:   */  private static yP jdField_a_of_type_YP;
/* 19:   */  private yG jdField_a_of_type_YG;
/* 20:20 */  private boolean jdField_a_of_type_Boolean = true;
/* 21:   */  private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/* 22:   */  private static ElementInformation b;
/* 23:   */  
/* 24:   */  public iu(ElementInformation paramElementInformation, ClientState paramClientState, UnicodeFont paramUnicodeFont) {
/* 25:25 */    super(paramClientState);
/* 26:26 */    if (jdField_a_of_type_YP == null)
/* 27:   */    {
/* 28:28 */      (iu.jdField_a_of_type_YP = new yP(512, 512, paramUnicodeFont, paramClientState)).a(512);
/* 29:29 */      jdField_a_of_type_YP.a(Color.green);
/* 30:   */    }
/* 31:31 */    this.jdField_a_of_type_YG = new yG(336.0F, 184.0F, paramClientState);
/* 32:32 */    this.jdField_a_of_type_YG.c(jdField_a_of_type_YP);
/* 33:33 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 34:34 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/* 35:   */  }
/* 36:   */  
/* 40:   */  public final void a() {}
/* 41:   */  
/* 44:   */  public final void b()
/* 45:   */  {
/* 46:46 */    if (this.jdField_a_of_type_Boolean) {
/* 47:47 */      c();
/* 48:   */    }
/* 49:49 */    if (b != this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation) {
/* 50:50 */      jdField_a_of_type_YP.b = this.jdField_a_of_type_JavaUtilArrayList;
/* 51:51 */      jdField_a_of_type_YP.jdField_a_of_type_Boolean = false;
/* 52:52 */      jdField_a_of_type_YP.e();
/* 53:53 */      b = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/* 54:   */    }
/* 55:   */    
/* 56:56 */    GlUtil.d();
/* 57:57 */    r();
/* 58:58 */    this.jdField_a_of_type_YG.b();
/* 59:   */    
/* 60:60 */    GlUtil.c();
/* 61:   */  }
/* 62:   */  
/* 64:   */  public final float a()
/* 65:   */  {
/* 66:66 */    return this.jdField_a_of_type_YG.a();
/* 67:   */  }
/* 68:   */  
/* 76:   */  public final float b()
/* 77:   */  {
/* 78:78 */    return this.jdField_a_of_type_YG.b();
/* 79:   */  }
/* 80:   */  
/* 88:   */  public final void c()
/* 89:   */  {
/* 90:90 */    this.jdField_a_of_type_YG.c();
/* 91:91 */    this.jdField_a_of_type_Boolean = false;
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */