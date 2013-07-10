package jo.sm.ent.logic;

import jo.sm.ent.data.Tag;
import jo.sm.ent.data.TagType;

public class EntityUtils {
	
	public static void dump(Tag obj, String prefix) 
	{
		System.out.print(prefix);
		System.out.print("<"+obj.getType());
		if (obj.getName() != null)
			System.out.print(" name=\""+obj.getName()+"\"");
		System.out.print(">");
		switch (obj.getType())
		{
			case STRUCT:
			{
				System.out.println();
				Tag[] val = (Tag[])obj.getValue();
				for (int i = 0; i < val.length; i++)
					if (val[i].getType() == TagType.FINISH)
						break;
					else
						dump(val[i], prefix+"  ");
				System.out.print(prefix);
				break;
			}
			case LIST:
			{
				System.out.println();
				Tag[] val = (Tag[])obj.getValue();
				for (int i = 0; i < val.length; i++)
					if (val[i].getType() == TagType.FINISH)
						break;
					else
						dump(val[i], prefix+"  ");
				System.out.print(prefix);
				break;
			}
			default:
				if (obj.getValue() == null)
					System.out.print("<null>");
				else
					System.out.print(obj.getValue().toString());
		}
		System.out.println("</"+obj.getType()+">");
	}

	public static Tag lookup(Tag obj, String id) {
		if ((id == null) || (id.length() == 0))
			return obj;
		String[] ids = id.split("/");
		for (String i : ids)
			obj = find(obj, i);
		return obj;
	}

	private static Tag find(Tag obj, String id) {
		if (obj.getName().equals(id))
			return obj;
		if (obj.getType() == TagType.STRUCT)
		{
			for (Tag sub : (Tag[])obj.getValue())
				if (id.equals(sub.getName()))
					return sub;
		}
		if (obj.getType() == TagType.LIST)
		{
			int n = Integer.parseInt(id);
			Tag[] subs = (Tag[])obj.getValue();
			if (n < subs.length)
				return subs[n];
		}
		return null;
	}

	public static void decr(Tag obj, String val) {
		switch (obj.getType())
		{
			case INT:
			{
				Integer v = (Integer)obj.getValue();
				v -= Integer.parseInt(val);
				obj.setValue(v);
				break;
			}
			default:
				throw new IllegalArgumentException("set is not supported on "+obj.getType());
		}
	}

	public static void incr(Tag obj, String val) {
		switch (obj.getType())
		{
			case INT:
			{
				Integer v = (Integer)obj.getValue();
				v += Integer.parseInt(val);
				obj.setValue(v);
				break;
			}
			default:
				throw new IllegalArgumentException("set is not supported on "+obj.getType());
		}
	}

	public static void set(Tag obj, String val) 
	{
		switch (obj.getType())
		{
			case INT:
			{
				obj.setValue(new Integer(val));
				break;
			}
			default:
				throw new IllegalArgumentException("set is not supported on "+obj.getType());
		}
	}

}
