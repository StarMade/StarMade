/*    */ package com.eaio.uuid;
/*    */ 
/*    */ import org.omg.CORBA.Any;
/*    */ import org.omg.CORBA.ORB;
/*    */ import org.omg.CORBA.StructMember;
/*    */ import org.omg.CORBA.TCKind;
/*    */ import org.omg.CORBA.TypeCode;
/*    */ import org.omg.CORBA.portable.InputStream;
/*    */ import org.omg.CORBA.portable.OutputStream;
/*    */ 
/*    */ public abstract class UUIDHelper
/*    */ {
/* 17 */   private static String _id = "IDL:com/eaio/uuid/UUID:1.0";
/*    */ 
/* 32 */   private static TypeCode __typeCode = null;
/* 33 */   private static boolean __active = false;
/*    */ 
/*    */   public static void insert(Any a, UUID that)
/*    */   {
/* 21 */     OutputStream out = a.create_output_stream();
/* 22 */     a.type(type());
/* 23 */     write(out, that);
/* 24 */     a.read_value(out.create_input_stream(), type());
/*    */   }
/*    */ 
/*    */   public static UUID extract(Any a)
/*    */   {
/* 29 */     return read(a.create_input_stream());
/*    */   }
/*    */ 
/*    */   public static synchronized TypeCode type()
/*    */   {
/* 36 */     if (__typeCode == null)
/*    */     {
/* 38 */       synchronized (TypeCode.class)
/*    */       {
/* 40 */         if (__typeCode == null)
/*    */         {
/* 42 */           if (__active)
/*    */           {
/* 44 */             return ORB.init().create_recursive_tc(_id);
/*    */           }
/* 46 */           __active = true;
/* 47 */           StructMember[] _members0 = new StructMember[2];
/* 48 */           TypeCode _tcOf_members0 = null;
/* 49 */           _tcOf_members0 = ORB.init().get_primitive_tc(TCKind.tk_longlong);
/* 50 */           _members0[0] = new StructMember("time", _tcOf_members0, null);
/*    */ 
/* 54 */           _tcOf_members0 = ORB.init().get_primitive_tc(TCKind.tk_longlong);
/* 55 */           _members0[1] = new StructMember("clockSeqAndNode", _tcOf_members0, null);
/*    */ 
/* 59 */           __typeCode = ORB.init().create_struct_tc(id(), "UUID", _members0);
/* 60 */           __active = false;
/*    */         }
/*    */       }
/*    */     }
/* 64 */     return __typeCode;
/*    */   }
/*    */ 
/*    */   public static String id()
/*    */   {
/* 69 */     return _id;
/*    */   }
/*    */ 
/*    */   public static UUID read(InputStream istream)
/*    */   {
/* 74 */     UUID value = new UUID();
/* 75 */     value.time = istream.read_longlong();
/* 76 */     value.clockSeqAndNode = istream.read_longlong();
/* 77 */     return value;
/*    */   }
/*    */ 
/*    */   public static void write(OutputStream ostream, UUID value)
/*    */   {
/* 82 */     ostream.write_longlong(value.time);
/* 83 */     ostream.write_longlong(value.clockSeqAndNode);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUIDHelper
 * JD-Core Version:    0.6.2
 */