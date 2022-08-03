package com.tim.data;

public enum FieldType {

	DOUBLE("Double"), 
	INTEGER("Integer"), 
	STRING("String"), 
	BOOLEAN("Boolean"),
	LOCALDATE("LocalDate");
	
	final String typeValue;
	
	private FieldType(final String typeValue) {
		this.typeValue = typeValue;
	}
	
	public String getName() {
		return name();
	}
	
	public String getValue() {
		return typeValue;
	}
	
	@Override
	public String toString() {
		return name();
	}
	
}
