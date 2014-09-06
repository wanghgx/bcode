package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_RuntimeAnnotations extends attribute_info {
	public int num_annotations;
	public annotation[] annotations;
	public attribute_RuntimeAnnotations(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("num_annotations");
		array(annotation.class, "annotations", num_annotations, 0);
	}
}
