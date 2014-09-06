package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_StackMapTable extends attribute_info {
	public int number_of_entries;
	public stack_map_frame[] entries;
	public attribute_StackMapTable(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("number_of_entries");
		array(stack_map_frame.class, "entries", number_of_entries, 0);
	}
}
