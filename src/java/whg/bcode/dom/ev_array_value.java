package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class ev_array_value extends element_value {
	public int num_values;
	public element_value[] values;
	public ev_array_value(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("num_values");
		array(element_value.class, "values", num_values, 0);
	}
}
