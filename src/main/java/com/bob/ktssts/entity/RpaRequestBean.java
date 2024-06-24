package com.bob.ktssts.entity;

public class RpaRequestBean {

	private String Name;
	private int Type;
	private Object Value;

	public RpaRequestBean() {
	}

	public RpaRequestBean(Object value, int type, String name) {
		Value = value;
		Type = type;
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public Object getValue() {
		return Value;
	}

	public void setValue(Object value) {
		Value = value;
	}

	public String toString() {
		return "Name: " + Name + ", Type: " + Type + ", Value: " + Value;
	}
}
