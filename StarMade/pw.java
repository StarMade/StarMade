/*    */ import javax.swing.AbstractListModel;
/*    */ import javax.swing.ComboBoxModel;
/*    */ 
/*    */ public final class pw extends AbstractListModel
/*    */   implements ComboBoxModel
/*    */ {
/*    */   private static final long serialVersionUID = -1012982137072739776L;
/*    */   private xu a;
/*    */ 
/*    */   public pw(xu paramxu)
/*    */   {
/* 18 */     this.a = paramxu;
/*    */   }
/*    */ 
/*    */   public final Object getElementAt(int paramInt) {
/* 22 */     if (paramInt >= 0) {
/* 23 */       if ((this.a.a().a[paramInt] instanceof Boolean)) {
/* 24 */         if (((Boolean)this.a.a().a[paramInt]).booleanValue()) return "On"; return "Off";
/*    */       }
/* 26 */       return this.a.a().a[paramInt];
/*    */     }
/* 28 */     return this.a.a();
/*    */   }
/*    */ 
/*    */   public final Object getSelectedItem()
/*    */   {
/* 34 */     if ((this.a.a() instanceof Boolean)) {
/* 35 */       if (((Boolean)this.a.a()).booleanValue()) return "ON"; return "OFF";
/*    */     }
/* 37 */     return this.a.a();
/*    */   }
/*    */ 
/*    */   public final int getSize()
/*    */   {
/* 42 */     return this.a.a().a.length;
/*    */   }
/*    */ 
/*    */   public final void setSelectedItem(Object paramObject)
/*    */   {
/* 47 */     if ("On".equals(paramObject)) {
/* 48 */       this.a.a(Boolean.valueOf(true)); return;
/* 49 */     }if ("Off".equals(paramObject)) {
/* 50 */       this.a.a(Boolean.valueOf(false)); return;
/*    */     }
/* 52 */     this.a.a(paramObject);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pw
 * JD-Core Version:    0.6.2
 */