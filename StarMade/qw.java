/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JSplitPane;
/*    */ import javax.swing.ListModel;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ final class qw extends MouseAdapter
/*    */ {
/*    */   qw(qv paramqv, JSplitPane paramJSplitPane)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void mouseClicked(MouseEvent paramMouseEvent)
/*    */   {
/* 61 */     if (qv.a(this.jdField_a_of_type_Qv).getSelectedIndex() >= 0) {
/* 62 */       paramMouseEvent = (Sendable)qv.a(this.jdField_a_of_type_Qv).getModel().getElementAt(qv.a(this.jdField_a_of_type_Qv).getSelectedIndex());
/*    */ 
/* 65 */       qy localqy = new qy(paramMouseEvent);
/*    */ 
/* 67 */       System.err.println("VALUE CHANGED: " + paramMouseEvent);
/*    */ 
/* 69 */       this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(localqy));
/* 70 */       org.schema.game.common.staremote.Staremote.a = localqy;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qw
 * JD-Core Version:    0.6.2
 */