package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Integer_info extends cp_info {
	public int value;
	public CONSTANT_Integer_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		this.value = nf.readInt();
	}
}
