package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class attribute_BootstrapMethods extends attribute_info {
	public int num_bootstrap_methods;
	public static class bootstrap_method extends whg.bcode.Node {
		public int bootstrap_method_ref;
		public int num_bootstrap_arguments;
		public int[] bootstrap_arguments;
		public bootstrap_method(NodeFactory nf) throws IOException {
			super(nf);
			u2("bootstrap_method_ref");
			u2("num_bootstrap_arguments");
			array(short.class, "bootstrap_arguments", num_bootstrap_arguments, 0);
		}
	}
	public bootstrap_method[] bootstrap_methods;
	public attribute_BootstrapMethods(NodeFactory nf, int attribute_name_index, int attribute_length) throws IOException {
		super(nf, attribute_name_index, attribute_length);
		u2("num_bootstrap_methods");
		array(bootstrap_method.class, "bootstrap_methods", num_bootstrap_methods, 0);
	}
}
