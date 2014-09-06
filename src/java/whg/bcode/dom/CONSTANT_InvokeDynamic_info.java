package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_InvokeDynamic_info extends cp_info {
	public int bootstrap_method_attr_index;
	public int name_and_type_index;
	public CONSTANT_InvokeDynamic_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("bootstrap_method_attr_index");
		u2("name_and_type_index");
	}
}
