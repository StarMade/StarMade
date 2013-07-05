/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.Action;
/*    */ import javax.swing.ActionMap;
/*    */ import javax.swing.InputMap;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.KeyStroke;
/*    */ 
/*    */ public final class c
/*    */   implements MouseListener
/*    */ {
/* 18 */   private static final KeyStroke jdField_a_of_type_JavaxSwingKeyStroke = KeyStroke.getKeyStroke(10, 0);
/*    */   private JList jdField_a_of_type_JavaxSwingJList;
/*    */   private KeyStroke b;
/*    */ 
/*    */   public c(JList paramJList, Action paramAction)
/*    */   {
/* 28 */     this(paramJList, paramAction, jdField_a_of_type_JavaxSwingKeyStroke);
/*    */   }
/*    */ 
/*    */   private c(JList paramJList, Action paramAction, KeyStroke paramKeyStroke)
/*    */   {
/* 36 */     this.jdField_a_of_type_JavaxSwingJList = paramJList;
/* 37 */     this.b = paramKeyStroke;
/*    */ 
/* 41 */     paramJList.getInputMap()
/* 42 */       .put(paramKeyStroke, paramKeyStroke);
/*    */ 
/* 46 */     paramKeyStroke = paramAction; paramAction = this; this.jdField_a_of_type_JavaxSwingJList.getActionMap().put(paramAction.b, paramKeyStroke);
/*    */ 
/* 50 */     paramJList.addMouseListener(this);
/*    */   }
/*    */ 
/*    */   public final void mouseClicked(MouseEvent paramMouseEvent)
/*    */   {
/* 65 */     if (paramMouseEvent.getClickCount() == 2)
/*    */     {
/* 69 */       if ((
/* 69 */         paramMouseEvent = this.jdField_a_of_type_JavaxSwingJList.getActionMap().get(this.b)) != null)
/*    */       {
/* 71 */         ActionEvent localActionEvent = new ActionEvent(this.jdField_a_of_type_JavaxSwingJList, 1001, "");
/*    */ 
/* 75 */         paramMouseEvent.actionPerformed(localActionEvent);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void mouseEntered(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void mouseExited(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void mousePressed(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void mouseReleased(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     c
 * JD-Core Version:    0.6.2
 */