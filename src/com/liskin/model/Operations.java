package com.liskin.model;

public enum Operations {

	MULTIPLICATION('*', (byte) 1) {
		@Override
		public Double calculate(double op1, double op2) {
			return op1 * op2;
		}
	},

	SUBSTRUCTION('-', (byte) 0) {
		@Override
		public Double calculate(double op1, double op2) {
			return op1 - op2;
		}
	},
	DIVISION('/', (byte) 1) {
		@Override
		public Double calculate(double op1, double op2) {
			return op1 / op2;
		}
	},

	ADDITION('+', (byte) 0) {
		@Override
		public Double calculate(double op1, double op2) {
			return op1 + op2;
		}
	};

	private Character code;
	private byte prioroty;

	private Operations(Character code, byte prioroty) {
		this.code = code;
		this.prioroty = prioroty;
	}

	public static boolean contains(Character source) {
		for (Operations operation : values()) {
			if (operation.code.equals(source)) {
				return true;
			}
		}
		return false;
	}

	public static Operations getOperation(Character source) {
		for (Operations operation : values()) {
			if (operation.code.equals(source)) {
				return operation;
			}
		}
		return null;
	}

	public byte getPrioroty() {
		return this.prioroty;
	}

	public Double calculate(double op1, double op2) {
		System.out.println("Forget");
		Operations.values();
		return null;
	}

	public char getCode() {
		return this.code;
	}

}
