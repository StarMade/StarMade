/*  1:   */import java.io.File;
/*  2:   */import java.io.PrintStream;
/*  3:   */import org.schema.game.network.objects.NetworkClientChannel;
/*  4:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  5:   */
/* 11:   */public final class lx
/* 12:   */  extends lI
/* 13:   */{
/* 14:   */  private boolean jdField_a_of_type_Boolean;
/* 15:   */  private String jdField_a_of_type_JavaLangString;
/* 16:   */  
/* 17:   */  public lx(t paramt)
/* 18:   */  {
/* 19:19 */    super(paramt);
/* 20:   */  }
/* 21:   */  
/* 26:   */  protected final String a()
/* 27:   */  {
/* 28:28 */    return this.jdField_a_of_type_JavaLangString;
/* 29:   */  }
/* 30:   */  
/* 32:   */  protected final File a(String paramString)
/* 33:   */  {
/* 34:34 */    return new File(paramString);
/* 35:   */  }
/* 36:   */  
/* 39:   */  protected final void a(File paramFile)
/* 40:   */  {
/* 41:41 */    System.err.println("[CLIENT] successfully received file from server: " + paramFile.getAbsolutePath());
/* 42:42 */    ((ct)((t)this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable).getState()).a().a().a(paramFile);
/* 43:43 */    this.jdField_a_of_type_Boolean = false;
/* 44:   */  }
/* 45:   */  
/* 46:   */  protected final RemoteBuffer a()
/* 47:   */  {
/* 48:48 */    return ((t)this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable).a().downloadBuffer;
/* 49:   */  }
/* 50:   */  
/* 55:   */  public final void a(String paramString)
/* 56:   */  {
/* 57:57 */    super.a(paramString);
/* 58:   */    
/* 59:59 */    this.jdField_a_of_type_Boolean = true;
/* 60:   */  }
/* 61:   */  
/* 64:   */  public final boolean a()
/* 65:   */  {
/* 66:66 */    return this.jdField_a_of_type_Boolean;
/* 67:   */  }
/* 68:   */  
/* 71:   */  public final void b_()
/* 72:   */  {
/* 73:73 */    this.jdField_a_of_type_Boolean = false;
/* 74:   */  }
/* 75:   */  
/* 85:   */  public final void b(String paramString)
/* 86:   */  {
/* 87:87 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */