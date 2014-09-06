package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_LineNumberTable extends attribute_info {
	public int line_number_table_length;
	public static class line_number extends whg.bcode.Node {
		public int start_pc;
		public int line_number;
		public line_number(NodeFactory nf) throws IOException {
			super(nf);
			u2("start_pc");
			u2("line_number");
		}
	}
	public line_number[] line_number_table;
	public attribute_LineNumberTable(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("line_number_table_length");
		array(line_number.class, "line_number_table", line_number_table_length, 0);
	}
}
