/*  1:   */import org.schema.game.network.objects.NetworkPlayer;
/*  2:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  3:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  4:   */
/* 14:   */public final class br
/* 15:   */  extends bu
/* 16:   */{
/* 17:   */  public br(ct paramct)
/* 18:   */  {
/* 19:19 */    super(paramct, "Drop Credits", "How many credits do you want to drop");
/* 20:   */    
/* 22:22 */    a(new bs(this));
/* 23:   */    
/* 45:45 */    this.a.a(new bt(this));
/* 46:   */  }
/* 47:   */  
/* 56:   */  public final boolean a(String paramString)
/* 57:   */  {
/* 58:58 */    paramString = Integer.parseInt(paramString);
/* 59:59 */    this.a.a().a().creditsDropBuffer.add(new RemoteInteger(Integer.valueOf(paramString), false));
/* 60:60 */    return true;
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     br
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */