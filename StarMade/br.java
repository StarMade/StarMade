/*    */ import org.schema.game.network.objects.NetworkPlayer;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*    */ 
/*    */ public final class br extends bu
/*    */ {
/*    */   public br(ct paramct)
/*    */   {
/* 19 */     super(paramct, "Drop Credits", "How many credits do you want to drop");
/*    */ 
/* 22 */     a(new bs(this));
/*    */ 
/* 45 */     this.a.a(new bt(this));
/*    */   }
/*    */ 
/*    */   public final boolean a(String paramString)
/*    */   {
/* 58 */     paramString = Integer.parseInt(paramString);
/* 59 */     this.a.a().a().creditsDropBuffer.add(new RemoteInteger(Integer.valueOf(paramString), false));
/* 60 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     br
 * JD-Core Version:    0.6.2
 */