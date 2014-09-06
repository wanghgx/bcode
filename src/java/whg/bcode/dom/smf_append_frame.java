package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class smf_append_frame extends stack_map_frame {
	public int offset_delta;
	public verification_type_info[] locals;
	public smf_append_frame(NodeFactory nf, int frame_type) throws IOException {
		super(nf, frame_type);
		u2("offset_delta");
		array(verification_type_info.class, "locals", frame_type-251, 0);
	}
}
