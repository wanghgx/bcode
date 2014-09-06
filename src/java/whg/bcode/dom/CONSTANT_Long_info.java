package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Long_info extends cp_info {
	public long value;
	public CONSTANT_Long_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		this.value = nf.readLong();
	}
}
