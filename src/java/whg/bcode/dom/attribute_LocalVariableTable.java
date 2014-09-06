package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_LocalVariableTable extends attribute_info {
	public int local_variable_table_length;
	public static class local_variable extends whg.bcode.Node {
		public int start_pc;
		public int length;
		public int name_index;
		public int descriptor_index;
		public int index;
		public local_variable(NodeFactory nf) throws IOException {
			super(nf);
			u2("start_pc");
			u2("length");
			u2("name_index");
			u2("descriptor_index");
			u2("index");
		}
	}
	public local_variable[] local_variable_table;
	public attribute_LocalVariableTable(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("local_variable_table_length");
		array(local_variable.class, "local_variable_table", local_variable_table_length, 0);
	}
}
