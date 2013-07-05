/*    */ import java.io.IOException;
/*    */ import org.schema.game.server.data.admin.AdminCommands;
/*    */ 
/*    */ final class rx extends rW
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   rx(String paramString, lE paramlE)
/*    */   {
/* 68 */     super(paramString);
/*    */   }
/*    */ 
/*    */   public final void a() { try { ((ct)this.a.getState()).a().a(AdminCommands.R, new Object[] { this.a.getName() });
/*    */       return;
/*    */     } catch (IOException localIOException) { localIOException.printStackTrace();
/*    */       return;
/*    */     } catch (InterruptedException localInterruptedException) {
/* 78 */       localInterruptedException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public final Object a()
/*    */   {
/* 82 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rx
 * JD-Core Version:    0.6.2
 */