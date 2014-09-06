package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class ev_class_info_index extends element_value {
	public int value;
	public ev_class_info_index(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("value");
	}
}
