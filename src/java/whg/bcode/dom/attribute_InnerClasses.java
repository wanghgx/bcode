package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_InnerClasses extends attribute_info {
	public int number_of_classes;
	public static class klass extends whg.bcode.Node {
		public int inner_class_info_index;
		public int outer_class_info_index;
		public int inner_name_index;
		public int inner_class_access_flags;
		public klass(NodeFactory nf) throws IOException {
			super(nf);
			u2("inner_class_info_index");
			u2("outer_class_info_index");
			u2("inner_name_index");
			u2("inner_class_access_flags");
		}
	}
	public klass[] klasses;
	public attribute_InnerClasses(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("number_of_classes");
		array(klass.class, "klasses", number_of_classes, 0);
	}
}
