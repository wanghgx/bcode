package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_NameAndType_info extends cp_info {
	public int name_index;
	public int descriptor_index;
	public CONSTANT_NameAndType_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("name_index");
		u2("descriptor_index");
	}
}
