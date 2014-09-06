package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Class_info extends cp_info {
	public int name_index;
	public CONSTANT_Class_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("name_index");
	}
}
