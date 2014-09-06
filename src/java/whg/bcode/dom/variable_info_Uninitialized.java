package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class variable_info_Uninitialized extends verification_type_info {
	public int offset;
	public variable_info_Uninitialized(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("offset");
	}
}
