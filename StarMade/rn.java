/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JSplitPane;
/*    */ import javax.swing.ListModel;
/*    */ 
/*    */ final class rn extends MouseAdapter
/*    */ {
/*    */   rn(rm paramrm, JSplitPane paramJSplitPane)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void mouseClicked(MouseEvent paramMouseEvent)
/*    */   {
/* 72 */     if (rm.a(this.jdField_a_of_type_Rm).getSelectedIndex() >= 0) {
/* 73 */       paramMouseEvent = (lE)rm.a(this.jdField_a_of_type_Rm).getModel().getElementAt(rm.a(this.jdField_a_of_type_Rm).getSelectedIndex());
/*    */ 
/* 76 */       rr localrr = new rr(paramMouseEvent);
/*    */ 
/* 78 */       System.err.println("VALUE CHANGED: " + paramMouseEvent);
/*    */ 
/* 80 */       this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(localrr));
/* 81 */       org.schema.game.common.staremote.Staremote.a = localrr;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rn
 * JD-Core Version:    0.6.2
 */