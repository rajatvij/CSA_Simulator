package components;/** * @author Ashwini Prabhu, Rajat Vij, Rahul Dhamnani, Xiaobei Yu, Anirud Pandey *         This class simulates the Register File components It contains only *         two methods get() and set(). Fuction of get is to get the content of *         register Fuction of set is to change the register file with given *         value */public class InstructionComponents {	private String value;//	private String[] values;	public Integer Index;	public InstructionComponents() {		value = "";	}	public String get() {		return this.value;	}/*		public String[] gets() {		return this.values;	}*/		public void set(String strValue) {		this.value = strValue;		System.out.println("value is : "+value);		this.Index = Integer.parseInt(strValue, 2);	}/*	public void set(String[] stringValue) {				this.values = stringValue;		this.Index = Integer.parseInt(stringValue.toString(), 2);			}*/}