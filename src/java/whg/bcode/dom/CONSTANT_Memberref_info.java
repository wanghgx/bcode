package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Memberref_info extends cp_info {
	public int class_index;
	public int name_and_type_index;
	public CONSTANT_Memberref_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("class_index");
		u2("name_and_type_index");
	}
}
