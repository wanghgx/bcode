package whg.bcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;

public class ParserTest {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: prog input");
			return;
		}
		File file = new File(args[0]);
		InputStream input = null;
		Reader reader = null;
		try {
			input = new FileInputStream(file);
			reader = new InputStreamReader(input, "UTF-8");
			SymbolFactory symbolFactory = new ComplexSymbolFactory();
			lexer lexer = new lexer(reader, symbolFactory);
			parser parser = new parser(lexer, symbolFactory);
			parser.parse();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
