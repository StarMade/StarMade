/*  1:   */import org.schema.schine.network.objects.Sendable;
/*  2:   */
/*  9:   */public abstract class wn
/* 10:   */  extends wo
/* 11:   */{
/* 12:   */  private final Sendable jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable;
/* 13:   */  
/* 14:   */  public wn(String paramString, Sendable paramSendable)
/* 15:   */  {
/* 16:16 */    super(paramString, paramSendable.getState());
/* 17:17 */    this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable = paramSendable;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public Sendable a()
/* 21:   */  {
/* 22:22 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public final boolean a() {
/* 26:26 */    return (super.a()) || ((!this.b) && (((wp)this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable).a().b()));
/* 27:   */  }
/* 28:   */  
/* 32:   */  public final void a(xq paramxq)
/* 33:   */  {
/* 34:34 */    super.a(paramxq);
/* 35:35 */    if (a()) {
/* 36:36 */      if (this.b) {
/* 37:37 */        b(paramxq);return;
/* 38:   */      }
/* 39:39 */      a_(paramxq);
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 43:   */  public float b() {
/* 44:44 */    if (!jdField_a_of_type_Boolean) throw new AssertionError();
/* 45:45 */    return 0.0F;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public abstract void a_(xq paramxq);
/* 49:   */  
/* 50:   */  public abstract void b(xq paramxq);
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */