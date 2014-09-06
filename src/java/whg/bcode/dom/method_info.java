package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class method_info extends whg.bcode.Node {
	public int access_flags;
	public int name_index;
	public int descriptor_index;
	public int attributes_count;
	public attribute_info[] attributes;
	public method_info(NodeFactory nf) throws IOException {
		super(nf);
		u2("access_flags");
		u2("name_index");
		u2("descriptor_index");
		u2("attributes_count");
		array(attribute_info.class, "attributes", attributes_count, 0);
	}
}
