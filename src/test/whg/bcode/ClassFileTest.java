package whg.bcode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import whg.bcode.dom.ClassFile;

public class ClassFileTest {
	public static void main(String[] args) throws IOException {
		boolean verbose = false;
		if (args.length > 0 && "-v".equals(args[0])) {
			verbose = true;
		}
		File listfile = new File("classlist.txt");
		InputStream input = new FileInputStream(listfile);
		Reader reader = new InputStreamReader(input);
		BufferedReader breader = new BufferedReader(reader);
		int count = 0;
		for (;;) {
			String line = breader.readLine();
			if (line == null) {
				break;
			}
			parse(++count, line, verbose);
		}
		System.out.println("count: " + count);
		breader.close();
		reader.close();
		input.close();
	}

	private static void parse(int index, String fn, boolean verbose) {
		System.out.printf("%5d %s ...", index, fn);
		InputStream input = null;
		DataInputStream dataInput = null;
		try {
			File file = new File(fn);
			input = new FileInputStream(file);
			dataInput = new DataInputStream(input);
			NodeFactory nf = new NodeFactory(dataInput, verbose);
			new ClassFile(nf);
			System.out.println("DONE");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
}
