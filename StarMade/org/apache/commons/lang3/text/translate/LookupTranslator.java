/*    */ package org.apache.commons.lang3.text.translate;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class LookupTranslator extends CharSequenceTranslator
/*    */ {
/*    */   private final HashMap<CharSequence, CharSequence> lookupMap;
/*    */   private final int shortest;
/*    */   private final int longest;
/*    */ 
/*    */   public LookupTranslator(CharSequence[][] lookup)
/*    */   {
/* 41 */     this.lookupMap = new HashMap();
/* 42 */     int _shortest = 2147483647;
/* 43 */     int _longest = 0;
/* 44 */     if (lookup != null) {
/* 45 */       for (CharSequence[] seq : lookup) {
/* 46 */         this.lookupMap.put(seq[0], seq[1]);
/* 47 */         int sz = seq[0].length();
/* 48 */         if (sz < _shortest) {
/* 49 */           _shortest = sz;
/*    */         }
/* 51 */         if (sz > _longest) {
/* 52 */           _longest = sz;
/*    */         }
/*    */       }
/*    */     }
/* 56 */     this.shortest = _shortest;
/* 57 */     this.longest = _longest;
/*    */   }
/*    */ 
/*    */   public int translate(CharSequence input, int index, Writer out)
/*    */     throws IOException
/*    */   {
/* 65 */     int max = this.longest;
/* 66 */     if (index + this.longest > input.length()) {
/* 67 */       max = input.length() - index;
/*    */     }
/*    */ 
/* 70 */     for (int i = max; i >= this.shortest; i--) {
/* 71 */       CharSequence subSeq = input.subSequence(index, index + i);
/* 72 */       CharSequence result = (CharSequence)this.lookupMap.get(subSeq);
/* 73 */       if (result != null) {
/* 74 */         out.write(result.toString());
/* 75 */         return i;
/*    */       }
/*    */     }
/* 78 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.LookupTranslator
 * JD-Core Version:    0.6.2
 */