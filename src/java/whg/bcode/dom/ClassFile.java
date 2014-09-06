package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class ClassFile extends whg.bcode.Node {
	public int magic;
	public int minor_version;
	public int major_version;
	public int constant_pool_count;
	public cp_info[] constant_pool;
	public int access_flags;
	public int this_class;
	public int super_class;
	public int interfaces_count;
	public int[] interfaces;
	public int fields_count;
	public field_info[] fields;
	public int methods_count;
	public method_info[] methods;
	public int attributes_count;
	public attribute_info[] attributes;
	public ClassFile(NodeFactory nf) throws IOException {
		super(nf);
		u4("magic");
		u2("minor_version");
		u2("major_version");
		u2("constant_pool_count");
		array(cp_info.class, "constant_pool", constant_pool_count, 1);
		u2("access_flags");
		u2("this_class");
		u2("super_class");
		u2("interfaces_count");
		array(short.class, "interfaces", interfaces_count, 0);
		u2("fields_count");
		array(field_info.class, "fields", fields_count, 0);
		u2("methods_count");
		array(method_info.class, "methods", methods_count, 0);
		u2("attributes_count");
		array(attribute_info.class, "attributes", attributes_count, 0);
	}
}
