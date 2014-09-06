package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_MethodHandle_info extends cp_info {
	public int reference_kind;
	public int reference_index;
	public CONSTANT_MethodHandle_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u1("reference_kind");
		u2("reference_index");
	}
}
