package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_LocalVariableTypeTable extends attribute_info {
	public int local_variable_type_table_length;
	public static class local_variable_type extends whg.bcode.Node {
		public int start_pc;
		public int length;
		public int name_index;
		public int signature_index;
		public int index;
		public local_variable_type(NodeFactory nf) throws IOException {
			super(nf);
			u2("start_pc");
			u2("length");
			u2("name_index");
			u2("signature_index");
			u2("index");
		}
	}
	public local_variable_type[] local_variable_type_table;
	public attribute_LocalVariableTypeTable(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("local_variable_type_table_length");
		array(local_variable_type.class, "local_variable_type_table", local_variable_type_table_length, 0);
	}
}
