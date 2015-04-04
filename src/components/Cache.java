package components;

import components.MainConsole;


public class Cache {
	private int cachesize;
	private int bitsize;
	private int blocksize;
	private int [][] cachedata;
	private Register writeBuffer;
	
	public Cache(int csize,int bsize, int blsize){
		cachesize = csize;  //16
		bitsize = bsize;    //100
		blocksize = blsize; //4
		cachedata = new int[csize][bitsize];
		writeBuffer = new Register();
	}
	/**
	 * 
	 * @param address a specific address in cache memory
	 * @return word the word read in cache
	 */
   public String Read(int address){
	   
	   String word = "";
	   int block = 0;
	   boolean onCache = false;
	   
	   //calculate offset
	   int offset = Integer.valueOf(address%blocksize);
	   int tag = address - offset;
	   System.out.println("Read Cache offset:"+offset+"tag:"+tag+"address:"+address);
//		mem.getDirect(Long.toBinaryString(address));
	   String OffSet = "";
	   OffSet = offset <= 1 ? "0" + String.format("%s", new Object[] { offset }) : Long.toBinaryString(offset);
	    
	   //compare the associated tag in block and block in memory
	   for( int i = 0;i<cachesize;i++){
		   if(GetBlockTag(i) == tag){
			   this.cachedata[i][5] = Integer.parseInt(OffSet.substring(0, 1));
		       this.cachedata[i][6] = Integer.parseInt(OffSet.substring(1, 2));
			   //read hit
			   MainConsole.textField.append("Read Hit!\n");
			   block = i;
			   onCache = true;
			   break;
		   }
	   }
	   
	   if(onCache==false){
		   //read miss
		   MainConsole.textField.append("Read Miss!\n");
		   
		   block = tag % 16;
		      if (this.cachedata[block][1] == 1) {
		        this.writeBuffer.set(TakeDataBitsFromtheBlock(block, 0, 99));
		        Writeback(block);
		      }
		   
		   //fetch the data from memory into cache
		   Fetch(tag);
		   this.cachedata[block][5] = Integer.parseInt(OffSet.substring(0, 1));
		   this.cachedata[block][6] = Integer.parseInt(OffSet.substring(1, 2));
	   }
	   
		//return the word
	    word = GetDataInBlock(block);
	    return word;
	}
   
   public String Fetch(int tag){
	    String data = ""; 
	    String msg = "";
	    String Tag = "";
	    int block = tag % 16;

	    SetDataBitsIntheBlock("0000000", block, 0, 6);
	    Tag = Long.toBinaryString(tag);
	    Tag = String.format(new StringBuilder().append("%0").append(13 - Tag.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + Tag;
	    SetDataBitsIntheBlock(Tag, block, 7, 19);

	    int indexStart = 20; int indexEnd = 39;

	    for (int i = 1; i <= 4; i++) {
	      data = MainConsole.MEMORY.getDirect(Long.toBinaryString(tag + i - 1));
	      SetDataBitsIntheBlock(data, block, i * indexStart, indexEnd + (i - 1) * indexStart);
	      msg = msg + String.format("%s", new Object[] { Integer.valueOf(tag + i - 1) }) + "-";
	    }
	    return msg;
   }
	
	public void Write(String data, int address){
		int tag=0;
		String add;
		boolean onCache = false;
		int offset = address%4; // 0,1,2,3
		tag = address - offset; // 0-16
//		System.out.println("Write Cache Offset:"+offset+"tag:"+tag+"address:"+address+"blocksize"+blocksize);
		String OffSet = "";
		OffSet = offset <= 1 ? "0" + String.format("%s", new Object[] { offset }) : Long.toBinaryString(offset);
	    
//		System.out.println("data in Write Funtion"+data);
		for (int i = 0; i <= 15; i++) {
		      if (GetBlockTag(i) == tag)
		      {
		        this.cachedata[i][1] = 1;
		        this.cachedata[i][5] = Integer.parseInt(OffSet.substring(0, 1));
		        this.cachedata[i][6] = Integer.parseInt(OffSet.substring(1, 2));
		        SetDataInBlock(data, i);
		        MainConsole.textField.append("Write Hit! \n");
		        onCache = true;
		        break;
		      }
		    }

		    if (!onCache)
		    {
		    	MainConsole.textField.append("Write Miss! \n");
		    	add = Long.toBinaryString(address);
		    	add = String.format(new StringBuilder().append("%0").append(20 - add.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + add;
		    	MainConsole.MEMORY.setDirect(data, add);

		    	Fetch(tag);
		    }
	}
	
	/**
	 * 
	 * @param tag
	 * @return null
	 *fetch the whole block start from tag
	 */
    void Writeback(int block){
    	int startIndex;
    	int endIndex;
    	//write tag back
    	int tag = Integer.parseInt(TakeDataBitsFromtheBlock(block, 7, 19), 2);
    	//write data back
    	startIndex = 20; endIndex = 39;
    	String data = "";
    	String address = "";
    	
    	for (int i = 1; i <= 4; i++) {
    		data = TakeDataBitsFromtheBlock(block, i * startIndex, endIndex + (i - 1) * startIndex);
    		address = Long.toBinaryString(tag + i -1);
    		address = String.format(new StringBuilder().append("%0").append(20 - address.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + address;
    		MainConsole.MEMORY.setDirect(data, address);
    	}	
    }
    
	private int GetBlockTag(int block){
		String BlockTag = "";
		int blockTag = 0;
		int startIndex = 7;
		int endIndex = 19;
		for( int i = startIndex ;i<endIndex; i++)
			BlockTag = BlockTag + cachedata[block][i];
		blockTag = Integer.parseInt(BlockTag,2);
		return blockTag;
	}
	
	private String GetDataInBlock(int block){
		String data = "";
		int startIndex = 0;
		int endIndex = 0;
		int offset = 0;
		//calculate the offset
		offset = 1 * this.cachedata[block][6] + 2 * this.cachedata[block][5];
		switch (offset){
		
		case 0:
			startIndex = 20;
			endIndex = 39;
			break;
			
		case 1:
			startIndex = 40;
			endIndex = 59;
			break;
			
		case 2:
			startIndex = 60;
			endIndex = 79;
			break;
			
		case 3:
			startIndex = 80;
			endIndex = 99;
			break;
		}
		
		for(int i = startIndex; i<=endIndex; i++)
			data = data + cachedata[block][i];
		
		return data;	
	}
		
	private void SetDataInBlock(String data,int block){
		int startIndex = 0;
		int endIndex = 0;
	    int offset = 0;
	    //calcuate offset
	    offset = 1 * this.cachedata[block][6] + 2 * this.cachedata[block][5];
	    switch(offset){
	    
		case 0:
			startIndex = 20;
			endIndex = 39;
			break;
			
		case 1:
			startIndex = 40;
			endIndex = 59;
			break;
			
		case 2:
			startIndex = 60;
			endIndex = 79;
			break;
			
		case 3:
			startIndex = 80;
			endIndex = 99;
			break;
		}
	    
	    int j = 0;
	    for(int i = startIndex; i<endIndex;i++){
	    	cachedata[block][i] = Integer.parseInt(data.substring(j, j+1));
	    	j++;
	    }
		
	}
	
	private void SetDataBitsIntheBlock(String mData, int block, int indexStart, int indexEnd)
	  {
	    int j = 0;
	    for (int i = indexStart; i <= indexEnd; i++) {
	      this.cachedata[block][i] = Integer.parseInt(mData.substring(j, j + 1));
	      j++;
	    }
	  }

	  private String TakeDataBitsFromtheBlock(int block, int indexStart, int indexEnd)
	  {
	    String sData = "";

	    for (int i = indexStart; i <= indexEnd; i++) {
	      sData = sData + this.cachedata[block][i];
	    }
	    return sData;
	  }

}
