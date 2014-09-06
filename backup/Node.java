package whg.bcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import whg.bcode.dom.CONSTANT_Class_info;
import whg.bcode.dom.CONSTANT_Double_info;
import whg.bcode.dom.CONSTANT_Fieldref_info;
import whg.bcode.dom.CONSTANT_Float_info;
import whg.bcode.dom.CONSTANT_Integer_info;
import whg.bcode.dom.CONSTANT_InterfaceMethodref_info;
import whg.bcode.dom.CONSTANT_InvokeDynamic_info;
import whg.bcode.dom.CONSTANT_Long_info;
import whg.bcode.dom.CONSTANT_MethodHandle_info;
import whg.bcode.dom.CONSTANT_MethodType_info;
import whg.bcode.dom.CONSTANT_Methodref_info;
import whg.bcode.dom.CONSTANT_NameAndType_info;
import whg.bcode.dom.CONSTANT_String_info;
import whg.bcode.dom.CONSTANT_Utf8_info;
import whg.bcode.dom.annotation;
import whg.bcode.dom.annotation.element_value_pair;
import whg.bcode.dom.attribute_AnnotationDefault;
import whg.bcode.dom.attribute_BootstrapMethods;
import whg.bcode.dom.attribute_BootstrapMethods.bootstrap_method;
import whg.bcode.dom.attribute_Code;
import whg.bcode.dom.attribute_ConstantValue;
import whg.bcode.dom.attribute_Deprecated;
import whg.bcode.dom.attribute_EnclosingMethod;
import whg.bcode.dom.attribute_Exceptions;
import whg.bcode.dom.attribute_InnerClasses;
import whg.bcode.dom.attribute_InnerClasses.klass;
import whg.bcode.dom.attribute_LineNumberTable;
import whg.bcode.dom.attribute_LineNumberTable.line_number;
import whg.bcode.dom.attribute_LocalVariableTable;
import whg.bcode.dom.attribute_LocalVariableTable.local_variable;
import whg.bcode.dom.attribute_LocalVariableTypeTable;
import whg.bcode.dom.attribute_LocalVariableTypeTable.local_variable_type;
import whg.bcode.dom.attribute_RuntimeInvisibleAnnotations;
import whg.bcode.dom.attribute_RuntimeInvisibleParameterAnnotations;
import whg.bcode.dom.attribute_RuntimeParameterAnnotations.parameter_annotation;
import whg.bcode.dom.attribute_RuntimeVisibleAnnotations;
import whg.bcode.dom.attribute_RuntimeVisibleParameterAnnotations;
import whg.bcode.dom.attribute_Signature;
import whg.bcode.dom.attribute_SourceDebugExtension;
import whg.bcode.dom.attribute_SourceFile;
import whg.bcode.dom.attribute_StackMapTable;
import whg.bcode.dom.attribute_Synthetic;
import whg.bcode.dom.attribute_info;
import whg.bcode.dom.cp_info;
import whg.bcode.dom.element_value;
import whg.bcode.dom.ev_annotation_value;
import whg.bcode.dom.ev_array_value;
import whg.bcode.dom.ev_class_info_index;
import whg.bcode.dom.ev_const_value_index;
import whg.bcode.dom.ev_enum_const_value;
import whg.bcode.dom.field_info;
import whg.bcode.dom.method_info;
import whg.bcode.dom.smf_append_frame;
import whg.bcode.dom.smf_chop_frame;
import whg.bcode.dom.smf_full_frame;
import whg.bcode.dom.smf_same_frame;
import whg.bcode.dom.smf_same_frame_extended;
import whg.bcode.dom.smf_same_locals_1_stack_item_frame;
import whg.bcode.dom.smf_same_locals_1_stack_item_frame_extended;
import whg.bcode.dom.stack_map_frame;
import whg.bcode.dom.variable_info_Double;
import whg.bcode.dom.variable_info_Float;
import whg.bcode.dom.variable_info_Integer;
import whg.bcode.dom.variable_info_Long;
import whg.bcode.dom.variable_info_Null;
import whg.bcode.dom.variable_info_Object;
import whg.bcode.dom.variable_info_Top;
import whg.bcode.dom.variable_info_Uninitialized;
import whg.bcode.dom.variable_info_UninitializedThis;
import whg.bcode.dom.verification_type_info;

public abstract class Node {
	protected final DataInputStream di;
	public cp_info[] cp;

	public Node(DataInputStream dataInput, cp_info[] constant_pool) {
		this.di = dataInput;
		this.cp = constant_pool;
	}

	protected int u4() throws IOException {
		return di.readInt();
	}

	protected int u4(String name) throws IOException {
		try {
			int u4 = u4();
			setField(name, u4);
			return u4;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int u2() throws IOException {
		return di.readUnsignedShort();
	}

	protected int u2(String name) throws IOException {
		try {
			int u2 = u2();
			setField(name, u2);
			return u2;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int u1() throws IOException {
		return di.readUnsignedByte();
	}

	protected int u1(String name) throws IOException {
		try {
			int u1 = u1();
			setField(name, u1);
			return u1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected byte[] bytes(int length) throws IOException {
		byte[] bytes = new byte[length];
		for (int n = 0; n < length;)
			n += di.read(bytes, n, length - n);
		return bytes;
	}

	protected String resolveUtf8(int idx) {
		CONSTANT_Utf8_info utf8 = (CONSTANT_Utf8_info) cp[idx];
		return utf8.value;
	}

	protected attribute_info attribute_info() throws IOException {
		attribute_info attribute_info = null;
		int attribute_name_index = u2();
		int attribute_length = u4();
		String attribute_name = resolveUtf8(attribute_name_index);
		if (Constants.ATTR_ConstantValue.equals(attribute_name)) {
			attribute_info = new attribute_ConstantValue(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_Code.equals(attribute_name)) {
			attribute_info = new attribute_Code(di, cp, attribute_name_index,
					attribute_length);
		} else if (Constants.ATTR_StackMapTable.equals(attribute_name)) {
			attribute_info = new attribute_StackMapTable(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_Exceptions.equals(attribute_name)) {
			attribute_info = new attribute_Exceptions(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_InnerClasses.equals(attribute_name)) {
			attribute_info = new attribute_InnerClasses(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_EnclosingMethod.equals(attribute_name)) {
			attribute_info = new attribute_EnclosingMethod(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_Synthetic.equals(attribute_name)) {
			attribute_info = new attribute_Synthetic(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_Signature.equals(attribute_name)) {
			attribute_info = new attribute_Signature(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_SourceFile.equals(attribute_name)) {
			attribute_info = new attribute_SourceFile(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_SourceDebugExtension.equals(attribute_name)) {
			attribute_info = new attribute_SourceDebugExtension(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_LineNumberTable.equals(attribute_name)) {
			attribute_info = new attribute_LineNumberTable(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_LocalVariableTable.equals(attribute_name)) {
			attribute_info = new attribute_LocalVariableTable(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_LocalVariableTypeTable.equals(attribute_name)) {
			attribute_info = new attribute_LocalVariableTypeTable(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_Deprecated.equals(attribute_name)) {
			attribute_info = new attribute_Deprecated(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_RuntimeVisibleAnnotations
				.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeVisibleAnnotations(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_RuntimeInvisibleAnnotations
				.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeInvisibleAnnotations(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_RuntimeVisibleParameterAnnotations
				.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeVisibleParameterAnnotations(
					di, cp, attribute_name_index, attribute_length);
		} else if (Constants.ATTR_RuntimeInvisibleParameterAnnotations
				.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeInvisibleParameterAnnotations(
					di, cp, attribute_name_index, attribute_length);
		} else if (Constants.ATTR_AnnotationDefault.equals(attribute_name)) {
			attribute_info = new attribute_AnnotationDefault(di, cp,
					attribute_name_index, attribute_length);
		} else if (Constants.ATTR_BootstrapMethods.equals(attribute_name)) {
			attribute_info = new attribute_BootstrapMethods(di, cp,
					attribute_name_index, attribute_length);
		} else {
			throw new RuntimeException("Unknown attribute: " + attribute_name);
		}
		return (attribute_info) attribute_info;
	}

	public element_value element_value(String name) throws IOException {
		element_value value = element_value();
		setField(name, value);
		return value;
	}

	public element_value element_value() throws IOException {
		element_value element_value = null;
		int tag = u1();
		switch (tag) {
		case 'B':
		case 'C':
		case 'D':
		case 'F':
		case 'I':
		case 'J':
		case 'S':
		case 'Z':
		case 's':
			element_value = new ev_const_value_index(di, cp, tag);
			break;
		case 'e':
			element_value = new ev_enum_const_value(di, cp, tag);
			break;
		case 'c':
			element_value = new ev_class_info_index(di, cp, tag);
			break;
		case '@':
			element_value = new ev_annotation_value(di, cp, tag);
			break;
		case '[':
			element_value = new ev_array_value(di, cp, tag);
			break;
		default:
			throw new RuntimeException("Unknown tag: " + (char) tag);
		}
		return (element_value) element_value;
	}

	protected cp_info cp_info() throws IOException {
		cp_info cp_info = null;
		int tag = u1();
		switch (tag) {
		case Constants.CONSTANT_Class:
			cp_info = new CONSTANT_Class_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Utf8:
			cp_info = new CONSTANT_Utf8_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Integer:
			cp_info = new CONSTANT_Integer_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Float:
			cp_info = new CONSTANT_Float_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Long:
			cp_info = new CONSTANT_Long_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Double:
			cp_info = new CONSTANT_Double_info(di, cp, tag);
			break;
		case Constants.CONSTANT_String:
			cp_info = new CONSTANT_String_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Fieldref:
			cp_info = new CONSTANT_Fieldref_info(di, cp, tag);
			break;
		case Constants.CONSTANT_Methodref:
			cp_info = new CONSTANT_Methodref_info(di, cp, tag);
			break;
		case Constants.CONSTANT_InterfaceMethodref:
			cp_info = new CONSTANT_InterfaceMethodref_info(di, cp, tag);
			break;
		case Constants.CONSTANT_NameAndType:
			cp_info = new CONSTANT_NameAndType_info(di, cp, tag);
			break;
		case Constants.CONSTANT_MethodHandle:
			cp_info = new CONSTANT_MethodHandle_info(di, cp, tag);
			break;
		case Constants.CONSTANT_MethodType:
			cp_info = new CONSTANT_MethodType_info(di, cp, tag);
			break;
		case Constants.CONSTANT_InvokeDynamic:
			cp_info = new CONSTANT_InvokeDynamic_info(di, cp, tag);
			break;
		default:
			throw new RuntimeException("Unknown tag: " + tag);
		}
		return (cp_info) cp_info;
	}

	protected Object array(Class<?> elemtype, String arrayname, int arraylen,
			int start) {
		try {
			Object array = null;
			if (elemtype == byte.class) {
				array = bytes(arraylen);
			} else {
				Method method = null;
				if (elemtype == short.class) {
					array = Array.newInstance(int.class, arraylen);
				} else if (elemtype == int.class) {
					array = Array.newInstance(elemtype, arraylen);
				} else {
					method = Node.class.getDeclaredMethod(elemtype
							.getSimpleName());
					array = Array.newInstance(elemtype, arraylen);
				}
				for (int i = start; i < arraylen; i++) {
					Object elem = null;
					if (elemtype == int.class) {
						int u4 = u4();
						elem = u4;
					} else if (elemtype == short.class) {
						int u2 = u2();
						elem = u2;
					} else {
						elem = method.invoke(this);
					}
					Array.set(array, i, elem);
					if ("constant_pool".equals(arrayname)
							&& (elem instanceof CONSTANT_Double_info || elem instanceof CONSTANT_Long_info)) {
						i++;
					}
				}
			}
			setField(arrayname, array);
			if ("constant_pool".equals(arrayname)) {
				setField("cp", array);
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

	protected field_info field_info() throws IOException {
		return new field_info(di, cp);
	}

	protected method_info method_info() throws IOException {
		return new method_info(di, cp);
	}

	protected attribute_Code.exception exception() throws IOException {
		return new attribute_Code.exception(di, cp);
	}

	protected bootstrap_method bootstrap_method() throws IOException {
		return new bootstrap_method(di, cp);
	}

	protected klass klass() throws IOException {
		return new klass(di, cp);
	}

	protected line_number line_number() throws IOException {
		return new line_number(di, cp);
	}

	protected local_variable local_variable() throws IOException {
		return new local_variable(di, cp);
	}

	protected local_variable_type local_variable_type() throws IOException {
		return new local_variable_type(di, cp);
	}

	protected annotation annotation(String name) throws IOException {
		annotation value = annotation();
		setField(name, value);
		return value;
	}

	protected annotation annotation() throws IOException {
		return new annotation(di, cp);
	}

	protected parameter_annotation parameter_annotation() throws IOException {
		return new parameter_annotation(di, cp);
	}

	protected stack_map_frame stack_map_frame() throws IOException {
		stack_map_frame stack_map_frame = null;
		//
		int frame_type = u1();
		if (0 <= frame_type && frame_type <= 63) {
			stack_map_frame = new smf_same_frame(di, cp, frame_type);
		} else if (64 <= frame_type && frame_type <= 127) {
			stack_map_frame = new smf_same_locals_1_stack_item_frame(di, cp,
					frame_type);
		} else if (128 <= frame_type && frame_type <= 246) {
			throw new RuntimeException("Unknown frame_type: " + frame_type);
		} else if (frame_type == 247) {
			stack_map_frame = new smf_same_locals_1_stack_item_frame_extended(
					di, cp, frame_type);
		} else if (248 <= frame_type && frame_type <= 250) {
			stack_map_frame = new smf_chop_frame(di, cp, frame_type);
		} else if (frame_type == 251) {
			stack_map_frame = new smf_same_frame_extended(di, cp, frame_type);
		} else if (252 <= frame_type && frame_type <= 254) {
			stack_map_frame = new smf_append_frame(di, cp, frame_type);
		} else if (frame_type == 255) {
			stack_map_frame = new smf_full_frame(di, cp, frame_type);
		} else {
			throw new RuntimeException("Unknown frame_type: " + frame_type);
		}
		return (stack_map_frame) stack_map_frame;
	}

	protected verification_type_info verification_type_info()
			throws IOException {
		verification_type_info verification_type_info = null;
		//
		int tag = u1();
		switch (tag) {
		case 0:
			verification_type_info = new variable_info_Top(di, cp, tag);
			break;
		case 1:
			verification_type_info = new variable_info_Integer(di, cp, tag);
			break;
		case 2:
			verification_type_info = new variable_info_Float(di, cp, tag);
			break;
		case 3:
			verification_type_info = new variable_info_Double(di, cp, tag);
			break;
		case 4:
			verification_type_info = new variable_info_Long(di, cp, tag);
			break;
		case 5:
			verification_type_info = new variable_info_Null(di, cp, tag);
			break;
		case 6:
			verification_type_info = new variable_info_UninitializedThis(di,
					cp, tag);
			break;
		case 7:
			verification_type_info = new variable_info_Object(di, cp, tag);
			break;
		case 8:
			verification_type_info = new variable_info_Uninitialized(di, cp,
					tag);
			break;
		default:
			throw new RuntimeException("Unknown tag: " + tag);
		}
		return (verification_type_info) verification_type_info;
	}

	public element_value_pair element_value_pair() throws IOException {
		return new element_value_pair(di, cp);
	}
}
