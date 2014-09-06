package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_String_info extends cp_info {
	public int string_index;
	public CONSTANT_String_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("string_index");
	}
}
