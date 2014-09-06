package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_EnclosingMethod extends attribute_info {
	public int class_index;
	public int method_index;
	public attribute_EnclosingMethod(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("class_index");
		u2("method_index");
	}
}
