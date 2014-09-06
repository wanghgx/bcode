package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class cp_info extends whg.bcode.Node {
	public int tag;
	public cp_info(NodeFactory nf, int tag) throws IOException {
		super(nf);
		this.tag = tag;
	}
}
