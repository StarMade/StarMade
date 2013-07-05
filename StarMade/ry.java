/*    */ import java.io.IOException;
/*    */ import org.schema.game.server.data.admin.AdminCommands;
/*    */ 
/*    */ final class ry extends rW
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   ry(String paramString, lE paramlE)
/*    */   {
/* 85 */     super(paramString);
/*    */   }
/*    */ 
/*    */   public final void a() { try { ((ct)this.a.getState()).a().a(AdminCommands.K, new Object[] { this.a.getName() });
/*    */       return;
/*    */     } catch (IOException localIOException) { localIOException.printStackTrace();
/*    */       return;
/*    */     } catch (InterruptedException localInterruptedException) {
/* 95 */       localInterruptedException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public final Object a()
/*    */   {
/* 99 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ry
 * JD-Core Version:    0.6.2
 */