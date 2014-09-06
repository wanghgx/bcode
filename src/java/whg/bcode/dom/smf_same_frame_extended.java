package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class smf_same_frame_extended extends stack_map_frame {
	public int offset_delta;
	public smf_same_frame_extended(NodeFactory nf, int frame_type) throws IOException {
		super(nf, frame_type);
		u2("offset_delta");
	}
}
