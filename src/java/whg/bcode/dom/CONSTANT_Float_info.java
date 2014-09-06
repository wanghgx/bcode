package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Float_info extends cp_info {
	public float value;
	public CONSTANT_Float_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		this.value = nf.readFloat();
	}
}
