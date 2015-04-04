package components;


/**
 * @author Rahul Dhamnani, Ashwini Prabhu, Rajat Vij, Xiaobei Yu, Anirud Pandey
 *         This class simulates the ALU component It contains three registers
 *         Operand1, Operand2 and RESULT Operand1 and Operand2 represent the
 *         operand registers inside the ALU the result is saved in RES register
 */
public class ALU {

	public Register Operand1, Operand2, RESULT, RESULT1, ENDRESULT;

	public ALU() {

		Operand1 = new Register("Operand1", 20, false);
		Operand2 = new Register("Operand2", 20, false);
		RESULT = new Register("RESULT", 20, false);
		RESULT1 = new Register("RESULT1", 20, false);
		ENDRESULT = new Register("ENDRESULT", 40, false);

	}

	/**
	 * This function simulates ADDER logic inside the ALU Values are read from
	 * Operand1 and Operand2 registers and result is stored in RESULT
	 */
	public void add() {
	    int temp = fromStrtoVal(this.Operand1.get()) + fromStrtoVal(this.Operand2.get());
	    RESULT.set(Long.toBinaryString(temp));
	}

	/**
	 * This function simulates SUBTRACT logic inside the ALU Values are read from
	 * Operand1 and Operand2 registers and result is stored in RESULT
	 */
	public void sub() {
	    int temp = fromStrtoVal(this.Operand1.get()) - fromStrtoVal(this.Operand2.get());
	    if (temp < 0) {
	      this.RESULT.set(valToStr(temp));
	    } else {
	      this.RESULT.set(Long.toBinaryString(temp));
	    }
	}
	/**
	 * This function returns the Signed Value from the String
	 */
	
	 public int fromStrtoVal(String value)
	  {
	    int a;
	    if (value.substring(0, 1).equals("1")) {
	      a = -Integer.parseInt(value.substring(1, 20), 2);
	    } else {
	      a = Integer.parseInt(value.substring(1, 20), 2);
	    }
	    return a;
	  }
	 	/**
		 * This function returns the signed value in RESULT1 
		 * and sets the value to RESULT1 
		 */
	  public String valToStr(int n)
	  {
	    if (n >= 0)
	    {
	      String s = Long.toBinaryString(n);
	      RESULT1.set(s);
	    }
	    else
	    {
	      String s = Long.toBinaryString(-n);
	      RESULT1.set(s);
	      RESULT1.set("1" + RESULT1.get().substring(1, 20));
	    }
	    return RESULT1.get();
	  }
	   /**
		 * This function simply returns the addition of the values
		 * present in Operand1 and Operand2
		 */
	  public int addValues()
	  {
	    return fromStrtoVal(Operand1.get()) + fromStrtoVal(Operand2.get());
	  }
	   /**
		 * This function simply returns the multiplication of the values
		 * present in Operand1 and Operand2
		 */
	  public void mult()
	  {
	    String signBite = "0";
	    if (Operand1.get().substring(0, 1).equals(Operand2.get().substring(0, 1))) {
	      signBite = "0";
	    } else {
	      signBite = "1";
	    }
	    long OPOne = Integer.parseInt(Operand1.get().substring(1, 20), 2);
	    long OPTwo = Integer.parseInt(Operand2.get().substring(1, 20), 2);
	    long multiResult = OPOne * OPTwo;
	    ENDRESULT.set(Long.toBinaryString(multiResult));
	    ENDRESULT.set(signBite + ENDRESULT.get().substring(1, 40));
	  }
	  
	   /**
		 * This function simply returns the division of the values
		 * present in Operand1 and Operand2
		 */
	  public void div()
	  {
	    RESULT.set(Long.toBinaryString(Integer.parseInt(Operand2.get(), 2) / Integer.parseInt(Operand1.get(), 2)));
	    RESULT1.set(Long.toBinaryString(Integer.parseInt(Operand2.get(), 2) % Integer.parseInt(Operand1.get(), 2)));
	  }
	   /**
		 * This function simply returns the Logical AND Function 
		 * of the values present in Operand1 and Operand2
		 */
	  public void and()
	  {
	    String result = "";
	    for (int i = 0; i < 20; i++) {
	      if ((Operand1.get().substring(i, i + 1).equals("1")) && (Operand2.get().substring(i, i + 1).equals("1"))) {
	        result = result + "1";
	      } else {
	        result = result + "0";
	      }
	    }
	    RESULT.set(result);
	  }
	  
	   /**
		 * This function simply returns the Logical OR Function 
		 * of the values present in Operand1 and Operand2
		 */
	  public void or()
	  {
	    String res = "";
	    System.out.println("Result before OR" +res);
	    for (int i = 0; i < 20; i++) {
	      if ((Operand1.get().substring(i, i + 1).equals("0")) && (Operand2.get().substring(i, i + 1).equals("0"))) {
	    	  res = res + "0";
	    	  System.out.println("Result after 0OR0 OR" +res);
	      } else {
	    	  res = res + "1";
	    	  System.out.println("Result after 1OR1 OR" +res);
	      }
	    }
	    RESULT.set(res);
	    System.out.println("Result after OR" +res);
	  }
	   /**
		 * This function simply returns the Logical NOT Function 
		 * of the value present in Operand1
		 */
	  public void not()
	  {
	    String result = "";
	    for (int i = 0; i < 20; i++) {
	      if (Operand1.get().substring(i, i + 1).equals("0")) {
	        result = result + "1";
	      } else {
	        result = result + "0";
	      }
	    }
	    this.RESULT.set(result);
	  }
	  
	   /**
		 * This function performs a Arithmetic Right Shift on Operand1
		 */
	  public void ShiftRightArithmetic(int n)
	  {
	    int shift = n % 19;
	    String sign = Operand1.get().substring(0, 1);
	    int beforeShift = Integer.parseInt(Operand1.get().substring(1, 20), 2);
	    int afterShift = beforeShift >>> shift;
	    String shiftResult = Long.toBinaryString(afterShift);
	    this.RESULT.set(shiftResult);
	    this.RESULT.setSignBit(sign);
	  }
	  
	   /**
		 * This function performs a Arithmetic Left Shift on Operand1
		 */
	  public void ShiftLeftArithmetic(int n)
	  {
	    int shift = n % 19;
	    String sign = Operand1.get().substring(0, 1);
	    int beforeShift = Integer.parseInt(Operand1.get().substring(1, 20), 2);
	    int afterShift = beforeShift << shift;
	    String shiftResult = Long.toBinaryString(afterShift);
	    this.RESULT.set(shiftResult);
	    this.RESULT.setSignBit(sign);
	  }
	  
	   /**
		 * This function performs a Logical Right Shift on Operand1
		 */
	  public void logicalShiftRight(int n)
	  {
	    int shift = n % 20;
	    int beforeShift = Integer.parseInt(Operand1.get(), 2);
	    int afterShift = beforeShift >> shift;
	    String shiftResult = Long.toBinaryString(afterShift);
	    this.RESULT.set(shiftResult);
	  }
	  
	   /**
		 * This function performs a Logical Left Shift on Operand1
		 */
	  public void logicalShiftLeft(int n)
	  {
	    int shift = n % 20;
	    int beforeShift = Integer.parseInt(Operand1.get(), 2);
	    int afterShift = beforeShift << shift;
	    String shiftResult = Long.toBinaryString(afterShift);
	    this.RESULT.set(shiftResult);
	  }
	  
	   /**
		 * This function performs a Logical Left Rotate on Operand1
		 */
	  public void logicalRotateLeft(int n)
	  {
	    int shift = n % 20;
	    String buffer = Operand1.get();
	    for (int i = 0; i < shift; i++) {
	      buffer = buffer.substring(1, 20) + buffer.substring(0, 1);
	    }
	    this.RESULT.set(buffer);
	  }
	  
	   /**
		 * This function performs a Logical Right Rotate on Operand1
		 */
	  public void logicalRotateRight(int n)
	  {
	    int shift = n % 20;
	    String buffer = Operand1.get();
	    for (int i = 0; i < shift; i++) {
	      buffer = buffer.substring(19) + buffer.substring(0, 19);
	    }
	    this.RESULT.set(buffer);
	  }
	  
	   /**
		 * This function performs a Checks the Memory Overflow
		 */
	  public int checkOverflow(int input, int bits)
	  {
	    Double max = Double.valueOf(Math.pow(2.0D, bits - 1));
	    Double min = Double.valueOf(-Math.pow(2.0D, bits - 1));
	    if ((input >= min.doubleValue()) && (input <= max.doubleValue())) {
	      return 0;
	    }
	    return 1;
	  }

	public Register getOperand1() {
		return Operand1;
	}

	public void setOperand1(Register operand1) {
		Operand1 = operand1;
	}

	public Register getOperand2() {
		return Operand2;
	}

	public void setOperand2(Register operand2) {
		Operand2 = operand2;
	}

	public Register getRESULT() {
		return RESULT;
	}

	public void setRESULT(Register rESULT) {
		RESULT = rESULT;
	}

	public Register getRESULT1() {
		return RESULT1;
	}

	public void setRESULT1(Register rESULT1) {
		RESULT1 = rESULT1;
	}

	public Register getENDRESULT() {
		return ENDRESULT;
	}

	public void setENDRESULT(Register eNDRESULT) {
		ENDRESULT = eNDRESULT;
	}
	  
	  

}
