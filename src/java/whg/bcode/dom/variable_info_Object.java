package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class variable_info_Object extends verification_type_info {
	public int cpool_index;
	public variable_info_Object(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("cpool_index");
	}
}
