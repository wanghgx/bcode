package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class smf_same_locals_1_stack_item_frame_extended extends stack_map_frame {
	public int offset_delta;
	public verification_type_info[] stack;
	public smf_same_locals_1_stack_item_frame_extended(NodeFactory nf, int frame_type) throws IOException {
		super(nf, frame_type);
		u2("offset_delta");
		array(verification_type_info.class, "stack", 1, 0);
	}
}
