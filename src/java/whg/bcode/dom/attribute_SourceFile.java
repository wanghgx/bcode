package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_SourceFile extends attribute_info {
	public int sourcefile_index;
	public attribute_SourceFile(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("sourcefile_index");
	}
}
