package parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rank.RankingClass;
import ChatStructure.EmailThread;

public class BC3ParserMain {

public ArrayList<ArrayList<String>> getResponse(String file,InputStream stopwordfis,InputStream sentiwordfis){
	
		BC3Parser bcp = new BC3Parser();
		
		List<EmailThread> convers = new ArrayList<EmailThread>();
		Map<String, Integer> mapwordcount = new TreeMap<String, Integer>(); 
		
		String uri = file;
		bcp.bc3Parser(uri, convers, mapwordcount,stopwordfis,sentiwordfis);		
		RankingClass rank = new RankingClass();
		return rank.rankMethod(convers);		
	}
}
