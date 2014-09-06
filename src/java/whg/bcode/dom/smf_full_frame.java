package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class smf_full_frame extends stack_map_frame {
	public int offset_delta;
	public int number_of_locals;
	public verification_type_info[] locals;
	public int number_of_stack_items;
	public verification_type_info[] stack;
	public smf_full_frame(NodeFactory nf, int frame_type) throws IOException {
		super(nf, frame_type);
		u2("offset_delta");
		u2("number_of_locals");
		array(verification_type_info.class, "locals", number_of_locals, 0);
		u2("number_of_stack_items");
		array(verification_type_info.class, "stack", number_of_stack_items, 0);
	}
}
