package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Parser implements Runnable {

	public Parser(int SHINGLESIZE, BlockingQueue<Shingle> q, File fileName, int docId) {
		
		FileReader in;
		BufferedReader br; 
		int reset = 0;
		
		try {
			
			in = new FileReader(fileName);
			
		    br = new BufferedReader(in);
			
			while (br.readLine() != null) {
				
				int s;
				String word = "";

				while((s = br.read()) != -1)
				{	
					
					if (s == 32) {
						
						reset++;
						
						if(reset == SHINGLESIZE) {
							
							q.add(new Shingle(word.hashCode(), docId));
							
							word = "";
							reset = 0;
							
						}
						
					}
					else if ( (s > 64 && s < 91) || (s > 96 && s < 123) ) {
						word += (char)s;
					
					}
					else {
						
						continue;
						
					}
					
				}
			    
			}
			
			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}

	@Override
	public void run() {

		
	}

}
