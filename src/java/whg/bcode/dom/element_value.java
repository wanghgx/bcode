package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class element_value extends whg.bcode.Node {
	public int tag;
	public element_value(NodeFactory nf, int tag) throws IOException {
		super(nf);
		this.tag = tag;
	}
}
