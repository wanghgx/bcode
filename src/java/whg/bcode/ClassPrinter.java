package whg.bcode;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import whg.bcode.dom.ClassFile;

public class ClassPrinter {
	public static void main(String[] args) {
		if (args.length < 1) {
			usage();
			return;
		}
		File file = new File(args[0]);
		InputStream input = null;
		DataInputStream dataInput = null;
		try {
			input = new FileInputStream(file);
			dataInput = new DataInputStream(input);
			NodeFactory nf = new NodeFactory(dataInput, true);
			new ClassFile(nf);
		} catch (FileNotFoundException e) {
			error(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (dataInput != null)
					dataInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void error(String msg) {
		System.err.println(msg);
	}

	private static void usage() {
		System.out.printf("Usage: whg.bcode.ClassPrinter <classfile>\n");
	}

}
