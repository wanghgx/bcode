package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_MethodType_info extends cp_info {
	public int descriptor_index;
	public CONSTANT_MethodType_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("descriptor_index");
	}
}
