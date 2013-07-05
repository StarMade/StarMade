/*     */ import com.eaio.uuid.UUIDGen;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.Writer;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
/*     */ import org.jasypt.util.text.BasicTextEncryptor;
/*     */ import org.schema.game.common.api.ApiController;
/*     */ 
/*     */ final class pz
/*     */   implements ActionListener
/*     */ {
/*     */   pz(px parampx)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 190 */     paramActionEvent = px.a(this.a).getText();
/* 191 */     Object localObject1 = new String(px.a(this.a).getPassword());
/* 192 */     Object localObject2 = new jp();
/*     */     try {
/* 194 */       ApiController.a((jp)localObject2);
/* 195 */       ApiController.a((jp)localObject2, paramActionEvent, (String)localObject1);
/* 196 */       org.schema.game.common.Starter.a = (jp)localObject2;
/* 197 */       if (px.a(this.a).isSelected())
/*     */       {
/* 199 */         paramActionEvent = new sD(paramActionEvent, (String)localObject1);
/* 199 */         (localObject1 = new BasicTextEncryptor()).setPassword(UUIDGen.getMACAddress()); localObject1 = ((BasicTextEncryptor)localObject1).encrypt(paramActionEvent.a); localObject2 = new FileWriter(new File(sE.a("StarMade"), "cred")); (localObject2 = new BufferedWriter((Writer)localObject2)).append(paramActionEvent.b); ((BufferedWriter)localObject2).newLine(); ((BufferedWriter)localObject2).append((CharSequence)localObject1); ((BufferedWriter)localObject2).flush(); ((BufferedWriter)localObject2).close();
/* 202 */       }
/*     */ this.a.dispose();
/*     */       return; } catch (Exception localException) {
/* 204 */       (
/* 205 */         paramActionEvent = 
/* 207 */         localException).printStackTrace();
/* 206 */       d.a(paramActionEvent);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pz
 * JD-Core Version:    0.6.2
 */