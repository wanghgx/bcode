package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class smf_same_locals_1_stack_item_frame extends stack_map_frame {
	public verification_type_info[] stack;
	public smf_same_locals_1_stack_item_frame(NodeFactory nf, int frame_type) throws IOException {
		super(nf, frame_type);
		array(verification_type_info.class, "stack", 1, 0);
	}
}
