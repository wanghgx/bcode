package whg.bcode.dom;
import java.io.*;
import whg.bcode.*;
public  class stack_map_frame extends whg.bcode.Node {
	public int frame_type;
	public stack_map_frame(NodeFactory nf, int frame_type) throws IOException {
		super(nf);
		this.frame_type = frame_type;
	}
}
