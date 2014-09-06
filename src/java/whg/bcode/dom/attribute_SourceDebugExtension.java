package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_SourceDebugExtension extends attribute_info {
	public byte[] debug_extension;
	public attribute_SourceDebugExtension(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		array(byte.class, "debug_extension", attribute_length, 0);
	}
}
