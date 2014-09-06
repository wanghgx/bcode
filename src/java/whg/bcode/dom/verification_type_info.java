package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class verification_type_info extends whg.bcode.Node {
	public int tag;
	public verification_type_info(NodeFactory nf, int tag) throws IOException {
		super(nf);
		this.tag = tag;
	}
}
