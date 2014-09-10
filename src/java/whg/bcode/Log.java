package whg.bcode;

import whg.bcode.dom.CONSTANT_Utf8_info;
import whg.bcode.dom.CPTAG;
import whg.bcode.dom.REFKIND;

public class Log {

	private int lvl = 1;
	private int width = 29;
	private IConstantPool cp;

	public Log(IConstantPool cp) {
		this.cp = cp;
	}

	public void logU4(String label, int u4) {
		long l = u4;
		if (l < 0) {
			l &= 0xffffffffl;
		}
		if ("magic".equals(label)) {
			logHex(label, l);
		} else {
			logDec(label, l);
		}
	}

	public void logU2(String label, int u2) {
		logDec(label, u2);
	}

	public void logU1(String label, int u1) {
		if ("reference_kind".equals(label)) {
			String value = String.format("%d - %s", u1, REFKIND.name(u1));
			logStr(label, value);
		} else {
			logDec(label, u1);
		}
	}

	public void logStr(String label, String str) {
		System.out.printf("%" + (lvl * width) + "s: %s\n", label, str);
	}

	public void logSub(String label, int idx) {
		label = String.format("%s[%d]", label, idx);
		logStr(label, "");
	}

	public void logSub(String label, int idx, int val) {
		label = String.format("%s[%d]", label, idx);
		logDec(label, val);
	}

	public void logCpTag(int tag) {
		String label = "tag";
		String value = String.format("%d - %s", tag, CPTAG.name(tag));
		logStr(label, value);
	}

	public void logDec(String label, long l) {
		String value = String.format("%d", l);
		logStr(label, value);
	}

	private void logHex(String label, long l) {
		String value = String.format("%x", l);
		logStr(label, value);
	}

	public void inc() {
		lvl++;
	}

	public void dec() {
		lvl--;
	}

	public void logUtf8Ptr(String label, int attribute_name_index) {
		CONSTANT_Utf8_info utf8_info = (CONSTANT_Utf8_info) cp
				.constant_pool(attribute_name_index);
		String utf8 = utf8_info.value;
		String value = String.format("#%d - %s", attribute_name_index, utf8);
		logStr(label, value);
	}

}
