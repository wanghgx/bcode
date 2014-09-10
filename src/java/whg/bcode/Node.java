package whg.bcode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import whg.bcode.dom.CONSTANT_Double_info;
import whg.bcode.dom.CONSTANT_Long_info;
import whg.bcode.dom.ClassFile;
import whg.bcode.dom.annotation;
import whg.bcode.dom.element_value;

public abstract class Node {

	private NodeFactory nf;
	private Log log;

	public Node(NodeFactory nf) {
		this.nf = nf;
		this.log = nf.getLog();
	}

	protected int u4(String name) throws IOException {
		try {
			int u4 = nf.u4();
			setField(name, u4);
			log.logU4(name, u4);
			return u4;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int u2(String name) throws IOException {
		try {
			int u2 = nf.u2();
			setField(name, u2);
			log.logU2(name, u2);
			return u2;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int u1(String name) throws IOException {
		try {
			int u1 = nf.u1();
			setField(name, u1);
			log.logU1(name, u1);
			return u1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public element_value element_value(String name) throws IOException {
		element_value value = nf.element_value();
		setField(name, value);
		return value;
	}

	protected Object array(Class<?> elemtype, String arrayname, int arraylen,
			int start) {
		try {
			Object array = null;
			if (elemtype == byte.class) {
				array = nf.bytes(arraylen);
				log.logStr(arrayname, "TODO {len:" + ((byte[]) array).length
						+ "}");
			} else {
				Method method = null;
				if (elemtype == short.class) {
					array = Array.newInstance(int.class, arraylen);
				} else if (elemtype == int.class) {
					array = Array.newInstance(elemtype, arraylen);
				} else {
					method = NodeFactory.class.getDeclaredMethod(elemtype
							.getSimpleName());
					array = Array.newInstance(elemtype, arraylen);
				}
				for (int i = start; i < arraylen; i++) {
					log.logSub(arrayname, i);
					Object elem = null;
					log.inc();
					if (elemtype == int.class) {
						int u4 = nf.u4();
						elem = u4;
					} else if (elemtype == short.class) {
						int u2 = nf.u2();
						elem = u2;
					} else {
						elem = method.invoke(nf);
					}
					log.dec();
					Array.set(array, i, elem);
					if ("constant_pool".equals(arrayname)
							&& (elem instanceof CONSTANT_Double_info || elem instanceof CONSTANT_Long_info)) {
						i++;
					}
				}
			}
			setField(arrayname, array);
			if ("constant_pool".equals(arrayname)) {
				nf.setClassFile((ClassFile) this);
			}
			return array;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void setField(String fieldName, Object value) {
		try {
			this.getClass().getField(fieldName).set(this, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected annotation annotation(String name) throws IOException {
		annotation value = nf.annotation();
		setField(name, value);
		return value;
	}

}
