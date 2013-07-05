/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public abstract class bu extends K
/*    */ {
/*    */   protected short a;
/*    */   protected final Object a;
/*    */ 
/*    */   public bu(ct paramct, short paramShort, String paramString, Object paramObject, int paramInt)
/*    */   {
/* 13 */     super(paramct, 5, paramString, paramObject, String.valueOf(paramInt));
/* 14 */     this.jdField_a_of_type_JavaLangObject = paramObject;
/* 15 */     this.jdField_a_of_type_Short = paramShort;
/*    */   }
/*    */ 
/*    */   public bu(ct paramct, String paramString, Object paramObject)
/*    */   {
/* 22 */     super(paramct, 10, paramString, paramObject, "0");
/* 23 */     this.jdField_a_of_type_JavaLangObject = paramObject;
/* 24 */     this.jdField_a_of_type_Short = -2;
/*    */   }
/*    */ 
/*    */   public String[] getCommandPrefixes()
/*    */   {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   public String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*    */   {
/* 44 */     return paramString1;
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 49 */     return this.a.b().indexOf(this) != this.a.b().size() - 1;
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 53 */     this.a.a().a.a.a.e(false);
/*    */   }
/*    */ 
/*    */   public void onFailedTextCheck(String paramString)
/*    */   {
/* 60 */     a(paramString);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bu
 * JD-Core Version:    0.6.2
 */