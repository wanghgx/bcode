package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Utf8_info extends cp_info {
	public String value;
	public CONSTANT_Utf8_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		this.value = nf.readUtf8();
	}
}
