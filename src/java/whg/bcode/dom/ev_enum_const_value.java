package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class ev_enum_const_value extends element_value {
	public int type_name_index;
	public int const_name_index;
	public ev_enum_const_value(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		u2("type_name_index");
		u2("const_name_index");
	}
}
