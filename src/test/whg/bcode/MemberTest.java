package whg.bcode;

public class MemberTest {
	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.run();
	}
}

class Sup {
	public String name = "sup";

	public void test() {
		System.out.println(name);
	}
}

class Sub extends Sup {
	public String name = "sub";

	public void run() {
		test();
	}
}
