package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_AnnotationDefault extends attribute_info {
	public element_value default_value;
	public attribute_AnnotationDefault(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		element_value("default_value");
	}
}
