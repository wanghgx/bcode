package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class ev_const_value_index extends element_value {
	public int value;
	public ev_const_value_index(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("value");
	}
}
