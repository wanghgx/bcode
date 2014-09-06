package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class smf_chop_frame extends stack_map_frame {
	public int offset_delta;
	public smf_chop_frame(NodeFactory nf, int frame_type) throws IOException {
		super(nf, frame_type);
		u2("offset_delta");
	}
}
