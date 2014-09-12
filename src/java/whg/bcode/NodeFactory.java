package whg.bcode;

import java.io.DataInputStream;
import java.io.IOException;

import whg.bcode.dom.ATTRNAME;
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
import whg.bcode.dom.CPTAG;
import whg.bcode.dom.ClassFile;
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

public class NodeFactory implements IConstantPool {

	private DataInputStream di;
	private ClassFile classFile;
	private Log log;

	public Log getLog() {
		return log;
	}

	public NodeFactory(DataInputStream di) {
		this(di, false);
	}

	public NodeFactory(DataInputStream di, boolean dolog) {
		this.di = di;
		this.log = new Log(this, dolog);
	}

	public void setClassFile(ClassFile classFile) {
		this.classFile = classFile;
	}

	public int u4() throws IOException {
		return di.readInt();
	}

	public int u2() throws IOException {
		return di.readUnsignedShort();
	}

	public int u1() throws IOException {
		return di.readUnsignedByte();
	}

	public byte[] bytes(int length) throws IOException {
		byte[] bytes = new byte[length];
		for (int n = 0; n < length;)
			n += di.read(bytes, n, length - n);
		return bytes;
	}

	@Override
	public cp_info constant_pool(int idx) {
		return classFile.constant_pool[idx];
	}

	public double readDouble() throws IOException {
		return di.readDouble();
	}

	public float readFloat() throws IOException {
		return di.readFloat();
	}

	public int readInt() throws IOException {
		int intVal = di.readInt();
		log.logDec("value", intVal);
		return intVal;
	}

	public long readLong() throws IOException {
		return di.readLong();
	}

	public String readUtf8() throws IOException {
		int len = u2();
		byte[] bytes = bytes(len);
		String utf8 = new String(bytes, "UTF-8");
		log.logStr("value", utf8);
		return utf8;
	}

	public String resolveUtf8(int idx) {
		CONSTANT_Utf8_info utf8 = (CONSTANT_Utf8_info) constant_pool(idx);
		return utf8.value;
	}

	public attribute_info attribute_info() throws IOException {
		attribute_info attribute_info = null;
		int attribute_name_index = this.u2();
		log.logUtf8Ptr("attribute_name_index", attribute_name_index);
		int attribute_length = this.u4();
		log.logDec("attribute_length", attribute_length);
		String attribute_name = this.resolveUtf8(attribute_name_index);
		if (ATTRNAME.ConstantValue.equals(attribute_name)) {
			attribute_info = new attribute_ConstantValue(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.Code.equals(attribute_name)) {
			attribute_info = new attribute_Code(this, attribute_name_index,
					attribute_length);
		} else if (ATTRNAME.StackMapTable.equals(attribute_name)) {
			attribute_info = new attribute_StackMapTable(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.Exceptions.equals(attribute_name)) {
			attribute_info = new attribute_Exceptions(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.InnerClasses.equals(attribute_name)) {
			attribute_info = new attribute_InnerClasses(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.EnclosingMethod.equals(attribute_name)) {
			attribute_info = new attribute_EnclosingMethod(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.Synthetic.equals(attribute_name)) {
			attribute_info = new attribute_Synthetic(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.Signature.equals(attribute_name)) {
			attribute_info = new attribute_Signature(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.SourceFile.equals(attribute_name)) {
			attribute_info = new attribute_SourceFile(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.SourceDebugExtension.equals(attribute_name)) {
			attribute_info = new attribute_SourceDebugExtension(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.LineNumberTable.equals(attribute_name)) {
			attribute_info = new attribute_LineNumberTable(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.LocalVariableTable.equals(attribute_name)) {
			attribute_info = new attribute_LocalVariableTable(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.LocalVariableTypeTable.equals(attribute_name)) {
			attribute_info = new attribute_LocalVariableTypeTable(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.Deprecated.equals(attribute_name)) {
			attribute_info = new attribute_Deprecated(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.RuntimeVisibleAnnotations.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeVisibleAnnotations(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.RuntimeInvisibleAnnotations.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeInvisibleAnnotations(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.RuntimeVisibleParameterAnnotations
				.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeVisibleParameterAnnotations(
					this, attribute_name_index, attribute_length);
		} else if (ATTRNAME.RuntimeInvisibleParameterAnnotations
				.equals(attribute_name)) {
			attribute_info = new attribute_RuntimeInvisibleParameterAnnotations(
					this, attribute_name_index, attribute_length);
		} else if (ATTRNAME.AnnotationDefault.equals(attribute_name)) {
			attribute_info = new attribute_AnnotationDefault(this,
					attribute_name_index, attribute_length);
		} else if (ATTRNAME.BootstrapMethods.equals(attribute_name)) {
			attribute_info = new attribute_BootstrapMethods(this,
					attribute_name_index, attribute_length);
		} else {
			throw new RuntimeException("Unknown attribute: " + attribute_name);
		}
		return (attribute_info) attribute_info;
	}

	public element_value element_value() throws IOException {
		element_value element_value = null;
		int tag = this.u1();
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
			element_value = new ev_const_value_index(this, tag);
			break;
		case 'e':
			element_value = new ev_enum_const_value(this, tag);
			break;
		case 'c':
			element_value = new ev_class_info_index(this, tag);
			break;
		case '@':
			element_value = new ev_annotation_value(this, tag);
			break;
		case '[':
			element_value = new ev_array_value(this, tag);
			break;
		default:
			throw new RuntimeException("Unknown tag: " + (char) tag);
		}
		return (element_value) element_value;
	}

	public cp_info cp_info() throws IOException {
		cp_info cp_info = null;
		int tag = this.u1();
		log.logCpTag(tag);
		switch (tag) {
		case CPTAG.Class:
			cp_info = new CONSTANT_Class_info(this, tag);
			break;
		case CPTAG.Utf8:
			cp_info = new CONSTANT_Utf8_info(this, tag);
			break;
		case CPTAG.Integer:
			cp_info = new CONSTANT_Integer_info(this, tag);
			break;
		case CPTAG.Float:
			cp_info = new CONSTANT_Float_info(this, tag);
			break;
		case CPTAG.Long:
			cp_info = new CONSTANT_Long_info(this, tag);
			break;
		case CPTAG.Double:
			cp_info = new CONSTANT_Double_info(this, tag);
			break;
		case CPTAG.String:
			cp_info = new CONSTANT_String_info(this, tag);
			break;
		case CPTAG.Fieldref:
			cp_info = new CONSTANT_Fieldref_info(this, tag);
			break;
		case CPTAG.Methodref:
			cp_info = new CONSTANT_Methodref_info(this, tag);
			break;
		case CPTAG.InterfaceMethodref:
			cp_info = new CONSTANT_InterfaceMethodref_info(this, tag);
			break;
		case CPTAG.NameAndType:
			cp_info = new CONSTANT_NameAndType_info(this, tag);
			break;
		case CPTAG.MethodHandle:
			cp_info = new CONSTANT_MethodHandle_info(this, tag);
			break;
		case CPTAG.MethodType:
			cp_info = new CONSTANT_MethodType_info(this, tag);
			break;
		case CPTAG.InvokeDynamic:
			cp_info = new CONSTANT_InvokeDynamic_info(this, tag);
			break;
		default:
			throw new RuntimeException("Unknown tag: " + tag);
		}
		return (cp_info) cp_info;
	}

	public field_info field_info() throws IOException {
		return new field_info(this);
	}

	public method_info method_info() throws IOException {
		return new method_info(this);
	}

	public attribute_Code.exception exception() throws IOException {
		return new attribute_Code.exception(this);
	}

	public bootstrap_method bootstrap_method() throws IOException {
		return new bootstrap_method(this);
	}

	public klass klass() throws IOException {
		return new klass(this);
	}

	public line_number line_number() throws IOException {
		return new line_number(this);
	}

	public local_variable local_variable() throws IOException {
		return new local_variable(this);
	}

	public local_variable_type local_variable_type() throws IOException {
		return new local_variable_type(this);
	}

	public annotation annotation() throws IOException {
		return new annotation(this);
	}

	public parameter_annotation parameter_annotation() throws IOException {
		return new parameter_annotation(this);
	}

	public stack_map_frame stack_map_frame() throws IOException {
		stack_map_frame stack_map_frame = null;
		//
		int frame_type = this.u1();
		if (0 <= frame_type && frame_type <= 63) {
			stack_map_frame = new smf_same_frame(this, frame_type);
		} else if (64 <= frame_type && frame_type <= 127) {
			stack_map_frame = new smf_same_locals_1_stack_item_frame(this,
					frame_type);
		} else if (128 <= frame_type && frame_type <= 246) {
			throw new RuntimeException("Unknown frame_type: " + frame_type);
		} else if (frame_type == 247) {
			stack_map_frame = new smf_same_locals_1_stack_item_frame_extended(
					this, frame_type);
		} else if (248 <= frame_type && frame_type <= 250) {
			stack_map_frame = new smf_chop_frame(this, frame_type);
		} else if (frame_type == 251) {
			stack_map_frame = new smf_same_frame_extended(this, frame_type);
		} else if (252 <= frame_type && frame_type <= 254) {
			stack_map_frame = new smf_append_frame(this, frame_type);
		} else if (frame_type == 255) {
			stack_map_frame = new smf_full_frame(this, frame_type);
		} else {
			throw new RuntimeException("Unknown frame_type: " + frame_type);
		}
		return (stack_map_frame) stack_map_frame;
	}

	public verification_type_info verification_type_info() throws IOException {
		verification_type_info verification_type_info = null;
		//
		int tag = this.u1();
		switch (tag) {
		case 0:
			verification_type_info = new variable_info_Top(this, tag);
			break;
		case 1:
			verification_type_info = new variable_info_Integer(this, tag);
			break;
		case 2:
			verification_type_info = new variable_info_Float(this, tag);
			break;
		case 3:
			verification_type_info = new variable_info_Double(this, tag);
			break;
		case 4:
			verification_type_info = new variable_info_Long(this, tag);
			break;
		case 5:
			verification_type_info = new variable_info_Null(this, tag);
			break;
		case 6:
			verification_type_info = new variable_info_UninitializedThis(this,
					tag);
			break;
		case 7:
			verification_type_info = new variable_info_Object(this, tag);
			break;
		case 8:
			verification_type_info = new variable_info_Uninitialized(this, tag);
			break;
		default:
			throw new RuntimeException("Unknown tag: " + tag);
		}
		return (verification_type_info) verification_type_info;
	}

	public element_value_pair element_value_pair() throws IOException {
		return new element_value_pair(this);
	}

	//
	//
	//

}
