package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class annotation extends whg.bcode.Node {
	public int type_index;
	public int num_element_value_pairs;
	public static class element_value_pair extends whg.bcode.Node {
		public int element_name_index;
		public element_value value;
		public element_value_pair(NodeFactory nf) throws IOException {
			super(nf);
			u2("element_name_index");
			element_value("value");
		}
	}
	public element_value_pair[] element_value_pairs;
	public annotation(NodeFactory nf) throws IOException {
		super(nf);
		u2("type_index");
		u2("num_element_value_pairs");
		array(element_value_pair.class, "element_value_pairs", num_element_value_pairs, 0);
	}
}
