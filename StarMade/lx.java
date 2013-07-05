/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import org.schema.game.network.objects.NetworkClientChannel;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public final class lx extends lI
/*    */ {
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */   private String jdField_a_of_type_JavaLangString;
/*    */ 
/*    */   public lx(t paramt)
/*    */   {
/* 19 */     super(paramt);
/*    */   }
/*    */ 
/*    */   protected final String a()
/*    */   {
/* 28 */     return this.jdField_a_of_type_JavaLangString;
/*    */   }
/*    */ 
/*    */   protected final File a(String paramString)
/*    */   {
/* 34 */     return new File(paramString);
/*    */   }
/*    */ 
/*    */   protected final void a(File paramFile)
/*    */   {
/* 41 */     System.err.println("[CLIENT] successfully received file from server: " + paramFile.getAbsolutePath());
/* 42 */     ((ct)((t)this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable).getState()).a().a().a(paramFile);
/* 43 */     this.jdField_a_of_type_Boolean = false;
/*    */   }
/*    */ 
/*    */   protected final RemoteBuffer a()
/*    */   {
/* 48 */     return ((t)this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable).a().downloadBuffer;
/*    */   }
/*    */ 
/*    */   public final void a(String paramString)
/*    */   {
/* 57 */     super.a(paramString);
/*    */ 
/* 59 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 66 */     return this.jdField_a_of_type_Boolean;
/*    */   }
/*    */ 
/*    */   public final void b_()
/*    */   {
/* 73 */     this.jdField_a_of_type_Boolean = false;
/*    */   }
/*    */ 
/*    */   public final void b(String paramString)
/*    */   {
/* 87 */     this.jdField_a_of_type_JavaLangString = paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lx
 * JD-Core Version:    0.6.2
 */