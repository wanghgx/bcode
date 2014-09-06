package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_ConstantValue extends attribute_info {
	public int constantvalue_index;
	public attribute_ConstantValue(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("constantvalue_index");
	}
}
