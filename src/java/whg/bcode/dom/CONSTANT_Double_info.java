package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class CONSTANT_Double_info extends cp_info {
	public double value;
	public CONSTANT_Double_info(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		this.value = nf.readDouble();
	}
}
