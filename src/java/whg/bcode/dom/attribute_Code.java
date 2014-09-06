package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_Code extends attribute_info {
	public int max_stack;
	public int max_locals;
	public int code_length;
	public byte[] code;
	public int exception_table_length;
	public static class exception extends whg.bcode.Node {
		public int start_pc;
		public int end_pc;
		public int handler_pc;
		public int catch_type;
		public exception(NodeFactory nf) throws IOException {
			super(nf);
			u2("start_pc");
			u2("end_pc");
			u2("handler_pc");
			u2("catch_type");
		}
	}
	public exception[] exception_table;
	public int attributes_count;
	public attribute_info[] attributes;
	public attribute_Code(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("max_stack");
		u2("max_locals");
		u4("code_length");
		array(byte.class, "code", code_length, 0);
		u2("exception_table_length");
		array(exception.class, "exception_table", exception_table_length, 0);
		u2("attributes_count");
		array(attribute_info.class, "attributes", attributes_count, 0);
	}
}
