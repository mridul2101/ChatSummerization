package rank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ChatStructure.EmailThread;
import ChatStructure.Message;
import ChatStructure.SingleMail;

public class RankingClass {
	static FileWriter fw;
	BufferedWriter bw;
	static int pnum=0;
	public ArrayList<ArrayList<String>> rankMethod(List<EmailThread> convers){
			
		StringBuilder outputstr = new StringBuilder();
		outputstr.append("<html>\n<head><title>summary" + ".html</title></head>\n<body bgcolor = \"white\">\n");
		File file = new File("E:\\Dropbox\\Dump\\bc3\\summary" + ".html");
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			System.out.println("----------o/p:"+outputstr.toString());
			fw.write(outputstr.toString());
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<ArrayList<String>> output_summary = new ArrayList<ArrayList<String>>();
		
		int numsum = 0;
		Map<Double, List<Message>> output = new TreeMap<Double, List<Message>>(Collections.reverseOrder());
		for(EmailThread et : convers){
			output.clear(); 
			int words = 0;
			numsum++;
			for(SingleMail mail : et.getMails()){
				for(Message msg : mail.getMessage()){
					words += msg.getWords();
					if(!Double.isNaN(msg.getScore()))
					{
						if(output.containsKey(msg.getScore())){
						
							List<Message> as = output.get(msg.getScore());
							if(as==null){
								as=new ArrayList<Message>();
								as.add(msg);
							}else
							{
								
								output.get(msg.getScore()).add(msg);
							}
						   }
						else{
							List<Message> as = new ArrayList<Message>();
							as.add(msg);
							output.put(msg.getScore(), as);
							
						}	
					}
				}
			}
			int perc = 20;
			int num = (output.size()*perc)/100;
			int j = 0;
			
			List<Message> outlist = new ArrayList<Message>();
			
			for(Map.Entry<Double, List<Message>> mlist : output.entrySet()){
				 
					 for(Message msg : mlist.getValue()){
						 outlist.add(msg);
						 j++;
						 if(j == num)
							 break;
					 }
					 if(j == num)
						 break;
				 
			 }
			
			try {
				sortListbyID(outlist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<String> summary=new ArrayList<String>();
			printMsgList(outlist, numsum,summary);	
			output_summary.add(summary);
		}
		outputstr.setLength(0);
		outputstr.append("</body>\n</html>");
		try {
			fw = new FileWriter(file.getAbsoluteFile(),true);
			System.out.println("----------o/p:"+outputstr.toString());
			fw.write(outputstr.toString());
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return output_summary;
	}
	private static void printMsgList(List<Message> message, int numsum,ArrayList<String> summary) {
		// TODO Auto-generated method stub
		
		StringBuilder outputstr = new StringBuilder();
		//outputstr.append("<html>\n<head><title>file_" + numsum + ".html</title></head>\n<body bgcolor = \"white\">\n");
		
		try{
				for(Message msg : message){
					pnum++;
					outputstr.append("<a name=\"" + pnum +"\">[" + pnum +"]</a>");
					String sent=msg.getRealSentence();
					sent = (sent.charAt(0)+"").toUpperCase()+sent.substring(1);
					
					outputstr.append(" <a href=\"#" + pnum + "\" id="+ pnum + ">" + sent + "</a>\n");
					System.out.println(msg.getSentence());
					 sent=msg.getSentence();
					sent = (sent.charAt(0)+"").toUpperCase()+sent.substring(1);
					summary.add(sent);
				}
				
				File file = new File("E:\\Dropbox\\Dump\\bc3\\summary" + ".html");//new File("E:\\Dropbox\\Dump\\bc3\\Doc\\file_" + numsum + ".html");
				try {
					fw = new FileWriter(file.getAbsoluteFile(),true);
					System.out.println("----------o/p:"+outputstr.toString());
					fw.write(outputstr.toString());
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	private void sortListbyID(List<Message> list) throws Exception {
		// TODO Auto-generated method stub		
		Collections.sort( list, new Comparator<Message>()
        {
            public int compare( Message m1, Message m2 )
            {
                return (m1.getID()).compareTo( m2.getID() );
            }
        } );
	}
}
