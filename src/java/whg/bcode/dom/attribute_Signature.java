package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_Signature extends attribute_info {
	public int signature_index;
	public attribute_Signature(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("signature_index");
	}
}
