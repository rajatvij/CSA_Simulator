package components;

public class AsciiConverter {
	
	 public static String formatCode(String code, int bit)
	  {
	    if (code.length() < bit) {
	      code = String.format(new StringBuilder().append("%0").append(bit - code.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + code;
	    }
	    return code;
	  }

	  public static String toBinary(String line)
	  {
	    String binaryCode = new String();
	    String operator = new String();
	    String rest = new String();

	    line = line.toLowerCase();
	    line = line.trim();
	    String[] temp = line.split(" ");
	    operator = temp[0];
	    rest = line.substring(operator.length(), line.length());
	    rest = rest.replace(" ", "");
	    String[] restTemp = rest.split(",");

	    if ((operator.equals("ldr")) || (operator.equals("str")) || (operator.equals("lda")) || (operator.equals("jz")) || (operator.equals("jne")) || (operator.equals("jcc")) || (operator.equals("sob")) || (operator.equals("jge")) || (operator.equals("amr")) || (operator.equals("smr")))
	    {
	      String R = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String IX = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 2);
	      String addr = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[2]).intValue()), 8);

	      if (operator.equals("ldr")) {
	        operator = Integer.toBinaryString(1);
	      }
	      else if (operator.equals("str")) {
	        operator = Integer.toBinaryString(2);
	      }
	      else if (operator.equals("lda")) {
	        operator = Integer.toBinaryString(3);
	      }
	      else if (operator.equals("jz")) {
	        operator = Integer.toBinaryString(10);
	      }
	      else if (operator.equals("jne")) {
	        operator = Integer.toBinaryString(11);
	      }
	      else if (operator.equals("jcc")) {
	        operator = Integer.toBinaryString(12);
	      }
	      else if (operator.equals("sob")) {
	        operator = Integer.toBinaryString(16);
	      }
	      else if (operator.equals("jge")) {
	        operator = Integer.toBinaryString(17);
	      }
	      else if (operator.equals("amr")) {
	        operator = Integer.toBinaryString(4);
	      }
	      else if (operator.equals("smr")) {
	        operator = Integer.toBinaryString(5);
	      }
	      operator = formatCode(operator, 6);

	      if (restTemp.length == 4) {
	        binaryCode = operator + IX + R + Integer.toBinaryString(Integer.valueOf(restTemp[3]).intValue()) + "0" + addr;
	      }
	      else if (restTemp.length == 3) {
	        binaryCode = operator + IX + R + "00" + addr;
	      }
	      else
	      {
	        return "false";
	      }

	    }

	    if ((operator.equals("ldx")) || (operator.equals("stx")) || (operator.equals("jmp")) || (operator.equals("jsr")))
	    {
	      String IX = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String addr = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 8);

	      if (operator.equals("ldx")) {
	        operator = Integer.toBinaryString(41);
	      }
	      else if (operator.equals("stx")) {
	        operator = Integer.toBinaryString(42);
	      }
	      else if (operator.equals("jmp")) {
	        operator = Integer.toBinaryString(13);
	      }
	      else if (operator.equals("jsr")) {
	        operator = Integer.toBinaryString(14);
	      }
	      operator = formatCode(operator, 6);

	      if (restTemp.length == 3) {
	        binaryCode = operator + IX + "00" + Integer.toBinaryString(Integer.valueOf(restTemp[2]).intValue()) + "0" + addr;
	      }
	      else if (restTemp.length == 2) {
	        binaryCode = operator + IX + "00" + "00" + addr;
	      }
	      else
	      {
	        return "false";
	      }

	    }
	    else if (operator.equals("rfs")) {
	      String immed = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 8);
	      binaryCode = formatCode(Integer.toBinaryString(15), 6) + "000000" + immed;
	    }
	    else if ((operator.equals("air")) || (operator.equals("sir"))) {
	      String R = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String immed = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 8);
	      if (operator.equals("air")) {
	        operator = Integer.toBinaryString(6);
	      }
	      else if (operator.equals("sir")) {
	        operator = Integer.toBinaryString(7);
	      }
	      operator = formatCode(operator, 6);
	      binaryCode = operator + "00" + R + "00" + immed;
	    }
	    else if ((operator.equals("aix")) || (operator.equals("six"))) {
	      String IX = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String immed = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 8);
	      if (operator.equals("aix")) {
	        operator = Integer.toBinaryString(8);
	      }
	      else if (operator.equals("six")) {
	        operator = Integer.toBinaryString(9);
	      }

	      operator = formatCode(operator, 6);
	      binaryCode = operator + IX + "0000" + immed;
	    }
	    else if ((operator.equals("mlt")) || (operator.equals("dvd")) || (operator.equals("trr")) || (operator.equals("and")) || (operator.equals("orr")))
	    {
	      String Rx = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String Ry = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 2);
	      if (operator.equals("mlt")) {
	        operator = Integer.toBinaryString(20);
	      }
	      else if (operator.equals("dvd")) {
	        operator = Integer.toBinaryString(21);
	      }
	      else if (operator.equals("trr")) {
	        operator = Integer.toBinaryString(22);
	      }
	      else if (operator.equals("and")) {
	        operator = Integer.toBinaryString(23);
	      }
	      else if (operator.equals("orr")) {
	        operator = Integer.toBinaryString(24);
	      }
	      operator = formatCode(operator, 6);

	      binaryCode = operator + Rx + Ry + "0000000000";
	    }
	    else if (operator.equals("not")) {
	      String Rx = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      binaryCode = formatCode(Integer.toBinaryString(25), 6) + Rx + "000000000000";
	    }
	    else if ((operator.equals("src")) || (operator.equals("rrc")))
	    {
	      String R = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String count = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 5);
	      if (operator.equals("src")) {
	        operator = Integer.toBinaryString(31);
	      }
	      else if (operator.equals("rrc")) {
	        operator = Integer.toBinaryString(32);
	      }
	      operator = formatCode(operator, 6);

	      binaryCode = operator + "00" + R + Integer.toBinaryString(Integer.valueOf(restTemp[3]).intValue()) + Integer.toBinaryString(Integer.valueOf(restTemp[2]).intValue()) + "000" + count;
	    }
	    else if ((operator.equals("in")) || (operator.equals("out")) || (operator.equals("chk")))
	    {
	      String R = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 2);
	      String devid = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[1]).intValue()), 4);
	      if (operator.equals("in")) {
	        operator = Integer.toBinaryString(61);
	      }
	      else if (operator.equals("out")) {
	        operator = Integer.toBinaryString(62);
	      }
	      else if (operator.equals("chk")) {
	        operator = Integer.toBinaryString(63);
	      }
	      operator = formatCode(operator, 6);

	      binaryCode = operator + "00" + R + "000000" + devid;
	    }
	    else if (operator.equals("hlt")) {
	      binaryCode = "00000000000000000000";
	    }
	    else if (operator.equals("trap")) {
	      String code = formatCode(Integer.toBinaryString(Integer.valueOf(restTemp[0]).intValue()), 8);
	      binaryCode = formatCode(Integer.toBinaryString(30), 6) + "000000" + code;
	    }

	    return binaryCode;
	  }

}
