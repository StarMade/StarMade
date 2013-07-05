/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ class ReferencesStack
/*    */ {
/*    */   private References[] references_stack;
/*    */   private int stack_pos;
/*    */ 
/*    */   public References getReferences()
/*    */   {
/* 41 */     return this.references_stack[this.stack_pos];
/*    */   }
/*    */ 
/*    */   public void pushState() {
/* 45 */     int pos = ++this.stack_pos;
/* 46 */     if (pos == this.references_stack.length) {
/* 47 */       growStack();
/*    */     }
/* 49 */     this.references_stack[pos].copy(this.references_stack[(pos - 1)], -1);
/*    */   }
/*    */ 
/*    */   public References popState(int mask) {
/* 53 */     References result = this.references_stack[(this.stack_pos--)];
/*    */ 
/* 55 */     this.references_stack[this.stack_pos].copy(result, mask ^ 0xFFFFFFFF);
/* 56 */     result.clear();
/*    */ 
/* 58 */     return result;
/*    */   }
/*    */ 
/*    */   private void growStack() {
/* 62 */     References[] new_references_stack = new References[this.references_stack.length + 1];
/* 63 */     System.arraycopy(this.references_stack, 0, new_references_stack, 0, this.references_stack.length);
/* 64 */     this.references_stack = new_references_stack;
/* 65 */     this.references_stack[(this.references_stack.length - 1)] = new References(GLContext.getCapabilities());
/*    */   }
/*    */ 
/*    */   ReferencesStack() {
/* 69 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 70 */     this.references_stack = new References[1];
/* 71 */     this.stack_pos = 0;
/* 72 */     for (int i = 0; i < this.references_stack.length; i++)
/* 73 */       this.references_stack[i] = new References(caps);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ReferencesStack
 * JD-Core Version:    0.6.2
 */