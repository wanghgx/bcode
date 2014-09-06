package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_RuntimeParameterAnnotations extends attribute_info {
	public int num_parameters;
	public static class parameter_annotation extends whg.bcode.Node {
		public int num_annotations;
		public annotation[] annotations;
		public parameter_annotation(NodeFactory nf) throws IOException {
			super(nf);
			u2("num_annotations");
			array(annotation.class, "annotations", num_annotations, 0);
		}
	}
	public parameter_annotation[] parameter_annotations;
	public attribute_RuntimeParameterAnnotations(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u1("num_parameters");
		array(parameter_annotation.class, "parameter_annotations", num_parameters, 0);
	}
}
