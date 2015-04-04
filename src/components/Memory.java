package components;

import components.Cache;
import components.MainConsole;

/**
 * @authors Ashwini Prabhu, Rajat Vij, Rahul Dhamnani, Xiaobei Yu, Anirud Pandey
 *          This class simulates the Memory The memory is created with help of a
 *          2 - D array
 */
/*
public class Memory {

	int[][] MEMORY;

	public Memory() {

		MEMORY = new int[8192][20];

	}
*/

public class Memory
{
  public int[][] MEMORYBank0;
  public int[][] MEMORYBank1;
  public int[][] MEMORYBank2;
  public int[][] MEMORYBank3;
  public int[][] MEMORYBank4;
  public int[][] MEMORYBank5;
  public int[][] MEMORYBank6;
  public int[][] MEMORYBank7;
  private Cache L1Cache;

  public Memory()
  {
    this.MEMORYBank0 = new int['ÿ'][20];
    this.MEMORYBank1 = new int['ÿ'][20];
    this.MEMORYBank2 = new int['ÿ'][20];
    this.MEMORYBank3 = new int['ÿ'][20];
    this.MEMORYBank4 = new int['ÿ'][20];
    this.MEMORYBank5 = new int['ÿ'][20];
    this.MEMORYBank6 = new int['ÿ'][20];
    this.MEMORYBank7 = new int['ÿ'][20];

    this.L1Cache = new Cache(16,100,4);
  }

	/**
	 * This method returns the content of Memory at the asked address
	 * 
	 * @param strAddr
	 *            is converted into integer and the data is fetched from the
	 *            memory at the location
	 */
	public String get(String strAddr) {

		String s = "";

		s = this.L1Cache.Read(Integer.parseInt(strAddr, 2));
		
		return s;
	}
/*	
	public String gets(String strAddr) {

		String s = null;

		s = this.L1Cache.Read(Integer.parseInt(strAddr, 2)).toString();
		
		return s;
	}
*/
	
	public String getDirect(String ADDR)
	  {
	    String DATA = ""; String BankNumber = "";
	    int OffSet = 0;

	    ADDR = ADDR.length() != 11 ? String.format(new StringBuilder().append("%0").append(11 - ADDR.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + ADDR : ADDR;

	    BankNumber = ADDR.substring(ADDR.length() - 3, ADDR.length());
	    OffSet = Integer.parseInt(ADDR.substring(0, ADDR.length() - 3), 2);

	    switch (BankNumber) {
	    case "000":
	      DATA = getDataFromBank(this.MEMORYBank0, OffSet);
	      break;
	    case "001":
	      DATA = getDataFromBank(this.MEMORYBank1, OffSet);
	      break;
	    case "010":
	      DATA = getDataFromBank(this.MEMORYBank2, OffSet);
	      break;
	    case "011":
	      DATA = getDataFromBank(this.MEMORYBank3, OffSet);
	      break;
	    case "100":
	      DATA = getDataFromBank(this.MEMORYBank4, OffSet);
	      break;
	    case "101":
	      DATA = getDataFromBank(this.MEMORYBank5, OffSet);
	      break;
	    case "110":
	      DATA = getDataFromBank(this.MEMORYBank6, OffSet);
	      break;
	    case "111":
	      DATA = getDataFromBank(this.MEMORYBank7, OffSet);
	    }

	    return DATA;
	  }

	  public String getDataFromBank(int[][] Bank, int OffSet)
	  {
	    String s = "";

	    for (int i = 0; i < 20; i++) {
	      s = s + Bank[OffSet][i];
	    }

	    return s;
	  }
	
	
	/**
	 * This method saves a content in the Memory address
	 * 
	 * @param strValue
	 *            The content which will be saved in the Memory.
	 */
	public void set(String strValue, String strAddr) {

		 strValue = strValue.length() != 20 ? String.format(new StringBuilder().append("%0").append(20 - strValue.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + strValue : strValue;

		    this.L1Cache.Write(strValue, Integer.parseInt(strAddr, 2));
		}
	public void setDirect(String DATA, String ADDR)
	  {
	    DATA = DATA.length() != 20 ? String.format(new StringBuilder().append("%0").append(20 - DATA.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + DATA : DATA;

	    String BankNumber = "";

	    BankNumber = ADDR.substring(ADDR.length() - 3, ADDR.length());
	    int OffSet = Integer.parseInt(ADDR.substring(0, ADDR.length() - 3), 2);

	    switch (BankNumber) {
	    case "000":
	      setDataToBank(this.MEMORYBank0, OffSet, DATA);
	      break;
	    case "001":
	      setDataToBank(this.MEMORYBank1, OffSet, DATA);
	      break;
	    case "010":
	      setDataToBank(this.MEMORYBank2, OffSet, DATA);
	      break;
	    case "011":
	      setDataToBank(this.MEMORYBank3, OffSet, DATA);
	      break;
	    case "100":
	      setDataToBank(this.MEMORYBank4, OffSet, DATA);
	      break;
	    case "101":
	      setDataToBank(this.MEMORYBank5, OffSet, DATA);
	      break;
	    case "110":
	      setDataToBank(this.MEMORYBank6, OffSet, DATA);
	      break;
	    case "111":
	      setDataToBank(this.MEMORYBank7, OffSet, DATA);
	    }

//	    MainConsole.textField.append("MEMORY(" + Integer.parseInt(ADDR, 2) + ") --> " + Integer.parseInt(DATA, 2) + "\n");
	  }

	  public void setDataToBank(int[][] Bank, int OffSet, String DATA)
	  {
	    for (int i = 0; i < 20; i++)
	      Bank[OffSet][i] = Integer.parseInt(Character.toString(DATA.charAt(i)));
	  }

	}