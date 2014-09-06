package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class ev_annotation_value extends element_value {
	public annotation value;
	public ev_annotation_value(NodeFactory nf, int tag) throws IOException {
		super(nf, tag);
		annotation("value");
	}
}
