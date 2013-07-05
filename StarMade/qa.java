/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.AbstractListModel;
/*     */ import org.schema.game.common.staremote.Staremote;
/*     */ 
/*     */ public final class qa extends AbstractListModel
/*     */ {
/*     */   private static final long serialVersionUID = 6959802025354159616L;
/*  34 */   private ArrayList a = new ArrayList();
/*     */ 
/*     */   public qa()
/*     */   {
/*     */     try
/*     */     {
/*  26 */       b();
/*     */       return;
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException)
/*     */     {
/*  31 */       localNumberFormatException.printStackTrace();
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*  31 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final Object getElementAt(int paramInt)
/*     */   {
/*  38 */     return this.a.get(paramInt);
/*     */   }
/*     */ 
/*     */   public final int getSize()
/*     */   {
/*  43 */     return this.a.size();
/*     */   }
/*     */   public final void a(qe paramqe) {
/*  46 */     this.a.add(paramqe);
/*  47 */     a();
/*     */     try { b();
/*     */       return; } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/*     */       return;
/*     */     } catch (IOException localIOException) {
/*  54 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a()
/*     */   {
/*     */     try
/*     */     {
/*  61 */       if (!(
/*  61 */         localObject = new File(Staremote.a()))
/*  61 */         .exists()) {
/*  62 */         ((File)localObject).createNewFile();
/*     */       }
/*  64 */       Object localObject = new BufferedWriter(new FileWriter((File)localObject));
/*  65 */       for (qe localqe : this.a) {
/*  66 */         ((BufferedWriter)localObject).append(localqe.jdField_a_of_type_JavaLangString + "," + localqe.b + ":" + localqe.jdField_a_of_type_Int + "\n");
/*  68 */       }((BufferedWriter)localObject).flush();
/*  69 */       ((BufferedWriter)localObject).close();
/*     */       return; } catch (Exception localException) {
/*  72 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b()
/*     */   {
/*  76 */     this.a.clear();
/*     */ 
/*  78 */     if (!(
/*  78 */       localObject1 = new File(Staremote.a()))
/*  78 */       .exists()) {
/*  79 */       throw new FileNotFoundException();
/*     */     }
/*  81 */     Object localObject1 = new BufferedReader(new FileReader((File)localObject1));
/*     */     Object localObject2;
/*  83 */     while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null)
/*     */     {
/*  85 */       String str1 = (
/*  85 */         localObject2 = ((String)localObject2).split(",", 21))[
/*  85 */         0];
/*     */ 
/*  87 */       String str2 = (
/*  87 */         localObject2 = localObject2[1].split(":", 2))[
/*  87 */         0];
/*  88 */       int i = Integer.parseInt(localObject2[1]);
/*     */ 
/*  90 */       qe localqe = new qe(str2, i, str1);
/*     */ 
/*  92 */       this.a.add(localqe);
/*     */     }
/*     */ 
/*  95 */     ((BufferedReader)localObject1).close();
/*     */ 
/*  97 */     fireContentsChanged(this, 0, this.a.size() - 1);
/*     */   }
/*     */ 
/*     */   public final void b(qe paramqe) {
/* 101 */     this.a.remove(paramqe);
/* 102 */     a();
/*     */     try { b();
/*     */       return; } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/*     */       return;
/*     */     } catch (IOException localIOException) {
/* 109 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qa
 * JD-Core Version:    0.6.2
 */