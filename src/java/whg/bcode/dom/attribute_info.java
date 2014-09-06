package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_info extends whg.bcode.Node {
	public int attribute_name_index;
	public int attribute_length;
	public attribute_info(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf);
		this.attribute_name_index = attribute_name_index;
		this.attribute_length = attribute_length;
	}
}
