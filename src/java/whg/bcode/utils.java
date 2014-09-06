package whg.bcode;

import java.io.File;
import java.io.PrintStream;

public class utils {
	private static final File DIR = new File("src/java/whg/bcode/dom");
	private static final String PACKAGE = "whg.bcode.dom";
	private static final String NODECLASS = "whg.bcode.Node";
	private static final String ENCODING = "UTF-8";
	private File file;
	private PrintStream ps;
	private String fn;

	private void printf(String format, Object... args) {
		ps.printf(format, args);
	}

	public void startfile(int idx, String classname) {
		startfile(idx, classname, null);
	}

	public void startfile(int idx, String classname, String supername) {
		fn = String.format("%s.java", classname);
		file = new File(DIR, fn);
		log(String.format("%10s: %s\n", "STARTFILE", fn));
		try {
			ps = new PrintStream(file, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		printf("package %s;\n", PACKAGE);
		startclass(classname, supername);
	}

	private void log(String msg) {
		System.err.printf(msg);
	}

	private void startclass(String classname, String supername) {
		if (supername == null) {
			supername = NODECLASS;
		}
		printf("public class %s extends %s {\n", classname, supername);
	}

	public void endfile() {
		log(String.format("%10s: %s\n", "ENDFILE", fn));
		ps.close();
	}
}
