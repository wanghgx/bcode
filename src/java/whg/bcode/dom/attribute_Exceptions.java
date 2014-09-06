package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_Exceptions extends attribute_info {
	public int number_of_exceptions;
	public int[] exception_index_table;
	public attribute_Exceptions(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("number_of_exceptions");
		array(short.class, "exception_index_table", number_of_exceptions, 0);
	}
}
