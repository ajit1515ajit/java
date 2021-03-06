import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class TFIDFHINDI extends Porter{
    public static Set<String> set =null;  
    public static HashMap<String,HashMap<String,Double>>invert_index  = new HashMap<String,HashMap<String,Double>>(); //hashmap for inverted index
    public static Porter port=new Porter();
    public static Double id=1.0;
    public static int p =1,q=1;
    public static HashMap<String,Double> IDF = new HashMap<String,Double>();
    public static HashMap<HashMap<String,String>,Double> WTF = new HashMap<HashMap<String,String>,Double>();
    public static BufferedReader bufferedReader=null;
    public static FileInputStream inputfilename=null;
public static void main(String[] args) throws Exception {
      long startTime = System.currentTimeMillis();         
      File f = new File("MapHindi.ser");
      boolean bool = false;
      bool = f.exists();                   //if binary file exists or not
      try{
         if(bool==false){                                
                File folder = new File("/home/ajit/Desktop/6th_SEM/Information_Retrieval/Assignment_1/hindi");  //folder path
                File[] listOfFiles = folder.listFiles();               //stroing filename in array of filetype
       
       String[] stopwords = { "से", "हैं", "को", "पर", "इस", "होता", "कि", "जो", "कर", "मे", "गया", "करने", "किया", "लिये", "अपने", "ने", "बनी", "नहीं", "तो", "ही", "या", "एवं", "दिया", "हो", "इसका", "था", "द्वारा", "हुआ", "तक", "साथ", "करना", "वाले", "बाद", "लिए", "आप", "कुछ", "सकते", "किसी", "ये", "इसके", "सबसे", "इसमें", "थे", "दो", "होने", "वह", "वे", "करते", "बहुत", "कहा", "वर्ग", "कई", "करें", "होती", "अपनी", "उनके", "थी", "यदि", "हुई", "जा", "ना", "इसे", "कहते", "जब", "होते", "कोई", "हुए", "व", "न", "अभी", "जैसे", "सभी", "करता", "उनकी", "तरह", "उस", "आदि", "कुल", "एस", "रहा", "इसकी", "सकता", "रहे", "उनका", "इसी", "रखें", "अपना", "पे", "उसके"};
       /*String[] stopwords = {"a", "as", "able", "about",
"above", "according", "accordingly", "across", "actually",
"after", "afterwards", "again", "against", "aint", "all",
"allow", "allows", "almost", "alone", "along", "already",
"also", "although", "always", "am", "among", "amongst", "an",
"and", "another", "any", "anybody", "anyhow", "anyone", "anything",
"anyway", "anyways", "anywhere", "apart", "appear", "appreciate",
"appropriate", "are", "arent", "around", "as", "aside", "ask", "asking",
"associated", "at", "available", "away", "awfully", "be", "became", "because",
"become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being",
"believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both",
"brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes",
"certain", "certainly", "changes", "clearly", "co", "com", "come",
"comes", "concerning", "consequently", "consider", "considering", "contain",
"containing",    "contains","corresponding","could", "couldnt", "course", "currently",
"definitely", "described", "despite", "did", "didnt", "different", "do", "does",
"doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu",
"eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially",
"et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere",
"ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed",   
"following", "follows", "for", "former", "formerly", "forth", "four", "from", "further",
"furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone"
    , "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have",
    "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};*/
   
      set=new HashSet<String>(Arrays.asList(stopwords));    //conveting  stopword array to set   
      for(File file : listOfFiles){                           //reading file array
           invertedindex(file);                            //calling inverted index function where id docid and file name
           p++;                                              //increasing docid
           System.out.println(p);
              }
      BufferedWriter outfile= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert_index_Hindiwtf.txt"), "UTF-8")); 
     // BufferedWriter outfile= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert_index_Englishwtf.txt"), "UTF-8"));
      for(Map.Entry e : invert_index.entrySet())
      { // IDF.put(e.getKey(),(Doubl\e)(invert_index.get(e.getKey()).size()));
         System.out.println(q);
         q++;
  Double use =(Double)(Math.log(listOfFiles.length/invert_index.get(e.getKey()).size()));
  IDF.put((String) e.getKey(),(Double)((Math.log(listOfFiles.length/invert_index.get(e.getKey()).size()))));
 
  
  for(Map.Entry ee : invert_index.get(e.getKey()).entrySet()){
          HashMap<String,String>hash = new HashMap<String,String>();
          hash.put((String)e.getKey(), (String)ee.getKey());
         
          Double use1 = use*(Double)(ee.getValue());
           outfile.write(""+(String)e.getKey()+" = "+(String)ee.getKey()+" = "+use1+"\n");
         // if(((String)invert_index.get(e.getKey())).equals("delhi")){
          //System.out.println(invert_index.get(e.getKey()).get(ee)+" =  "+(String)e.getKey()+" Value = "+use1);
 //WTF.put(hash.put((String)e.getKey(),(String)ee.getKey()),(Double)((Double)ee.getValue()*((Math.log(listOfFiles.length/invert_index.get(e.getKey()).size())))));
   WTF.put(hash,use1);
          //outfile.write((String)e.getKey()+" = "+(String)ee.getKey()+" = "+ use1+"\n");
             }
      }
      for(Map.Entry e : IDF.entrySet())
      { // IDF.put(e.getKey(),(Doubl\e)(invert_index.get(e.getKey()).size()));
      //System.out.println(""+e.getKey()+" =  "+e.getValue() );
      }
              
System.out.println("Time after Write"+(System.currentTimeMillis()-startTime));
        FileOutputStream fos = new FileOutputStream("MapHindi.ser");           
        ObjectOutputStream oos = new ObjectOutputStream(fos);             
        oos.writeObject(invert_index);                          //writing hashmap in objectoutput stream
        oos.close();
        
        FileOutputStream fosidf = new FileOutputStream("MapHindiidf.ser");           
        ObjectOutputStream oosidf = new ObjectOutputStream(fosidf);             
        oosidf.writeObject(IDF);                          //writing hashmap in objectoutput stream
        oosidf.close();

        
        FileOutputStream foswtf = new FileOutputStream("Maphindiwtf.ser");           
        ObjectOutputStream ooswtf = new ObjectOutputStream(foswtf);             
        ooswtf.writeObject(IDF);                          //writing hashmap in objectoutput stream
        ooswtf.close();
    }
else{                  
System.out.println("file exists");  

//if binary file already exists
FileInputStream fis = new FileInputStream("MapHindi.ser");
ObjectInputStream ois = new ObjectInputStream(fis);
@SuppressWarnings("unchecked")
HashMap<HashMap<String,String>,Double> mapwtf = (HashMap<HashMap<String,String>,Double>) ois.readObject();
            ois.close();
/*System.out.println("Problem");
//System.out.println("Problem");
FileInputStream fiswtf = new FileInputStream("MapEnglishwtf.ser");
ObjectInputStream oiswtf = new ObjectInputStream(fiswtf);
HashMap<HashMap<String,String>,Double> map1 = (HashMap<HashMap<String,String>,Double>) ois.readObject();
oiswtf.close();
System.out.println("Problem");*/

/*FileInputStream fisidf = new FileInputStream("MapEnglishidf.ser");
ObjectInputStream oisidf = new ObjectInputStream(fiswtf);
HashMap<String,Double> map2 = (HashMap<String,Double>) ois.readObject();
oisidf.close();*/

/*BufferedWriter outfile= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert_index_Hindi.txt"), "UTF-8"));
 for (Map.Entry m:map.entrySet()) {
        outfile.write(m.getKey()+" : "+ m.getValue() + "\n");
       // System.out.println("file =  "+p);
        //p++;
         }*/
 /*BufferedWriter outfilewtf= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert_index_Hindiwtf.txt"), "UTF-8"));
 for (Map.Entry m:mapwtf.entrySet()) {
     for(Map.Entry m1 : )
    outfilewtf.write(m.getKey()+" : "+ m.getValue() + "\n");
   
    }*/
       
       // System.out.println("file =  "+p);
        //p++;
         
 /*BufferedWriter outfileidf= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert_index_Englishidf.txt"), "UTF-8"));
 for (Map.Entry m:map2.entrySet()) {
        outfileidf.write(m.getKey()+" : "+ m.getValue() + "\n");
       // System.out.println("file =  "+p);
        //p++;
         }*/

/*FileInputStream fiswtf = new FileInputStream("MapEnglishwtf.ser");
ObjectInputStream oiswtf = new ObjectInputStream(fiswtf);
HashMap<HashMap<String,String>,Double> map1 = (HashMap<HashMap<String,String>,Double>) ois.readObject();
oiswtf.close();*/
       //System.out.println(map);
           // System.out.println("delete() invoked");
 /*BufferedWriter outfile= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert_index_English.txt"), "UTF-8"));
 for (Map.Entry m:map.entrySet()) {
        outfile.write(m.getKey()+" : "+ m.getValue() + "\n");
       // System.out.println("file =  "+p);
        //p++;
         }*/
  
 
        // frequency(map,"cancel"); 
         //System.out.println("Frequency");
         //term(map,"cancel"); 
         
            // calling function to check stirng present in which docs
        // System.out.println("Helo");
}}
    catch(Exception e){
          System.out.println(e);
        }
      finally{
long endTime   = System.currentTimeMillis();
long totalTime = endTime - startTime;
System.out.println("finally : "+totalTime);
      }

   }
  
  public static void invertedindex(File file) throws Exception{  //function invert index with filename and docid 
   inputfilename = new FileInputStream(file); 
   bufferedReader= new BufferedReader(new InputStreamReader(inputfilename, "UTF-8")); 
           String s;
            while((s = bufferedReader.readLine()) != null){      // reading file          
              
                s = s.replaceAll("\\<.*?>"," ");                      // removing < > mark content like <stroy> ,<Title>
                if(s.contains("॥") || s.contains(":")|| s.contains("।")||       //Removing special char and punctuation mark
                s.contains(",")|| s.contains("!")|| s.contains("?")){
                s=s.replace("॥"," ");
                s=s.replace(":"," ");
                s=s.replace("।"," ");
                s=s.replace(","," ");
                s=s.replace("!"," ");
                s=s.replace("?"," ");
                }                                                   
            StringTokenizer st = new StringTokenizer(s," ");           // change string as string tokenizer 
                while (st.hasMoreTokens()){                            // loop till tokan exists
                String str=(st.nextToken());
                if (set.contains(str)) {                                // check string is stopword or not if yes then continue
                }
                else{                                                   
                         //String ss=port.stripAffixes(str);
                        int length=Steam.stem(str);                      //stem the string  
                        
                        char [] arr=new char[length];                    
                           for (int j=0;j<length ;j++ ) {
                               arr[j]=str.charAt(j);                          //get the string
                           }
                        String ss=new String(arr);                      //convert array in string   
                        if(!invert_index.containsKey(ss)){
                            //hashmap has this String or not
                       HashMap<String,Double> in = new HashMap<>();
                      
                       
                                             in.put(file.getName(),id);
                       invert_index.put(ss,in);              //if not put into hashmap 
                        } else
                        {
                            //if(invert_index.containsKey(ss)){
                                if(invert_index.get(ss).containsKey(file.getName())){
                                Double h = (Double)invert_index.get(ss).get(file.getName());
                                h = h+1;
                                
                               //System.out.println("term = "+ss+" file "+file.getName()+" Frequency "+h);
                               // h++;
                                invert_index.get(ss).replace(file.getName(),h);
                                
                                }
                                else{
                                HashMap<String,Double> inin=invert_index.get(ss);            //get hasset of string 
                                inin.put(file.getName(),id);    
                                }
                                
//                              }
                           
                        
                        }
                       
                }
                }
                }             
                
                              
                    
            }
  public static void frequency(HashMap<String,HashMap<String,Integer>> another,String str){
    if(another.containsKey(str))
       System. out.println(""+str+" = "+another.get(str));
   
    }
 public static void term(HashMap<String,HashMap<String,Integer>> another,String str){
     if(another.containsKey(str)){
     System.out.println("Yes");
     }
 }
}
class Steam {
      static int stem(String st) {           //stemming of a string
// 5
int len=st.length();      
if ((len > 6) && (st.endsWith("ाएंगी")     //if length and ends with this decrease the length
|| st.endsWith("ाएंगे")
|| st.endsWith("ाऊंगी")
|| st.endsWith("ाऊंगा")
|| st.endsWith("ाइयाँ")
|| st.endsWith("ाइयों")
|| st.endsWith("ाइयां")
))

return len - 5;
// 4
if ((len > 5) && (st.endsWith("ाएगी")
||st.endsWith("ाएगा")
|| st.endsWith("ाओगी")
|| st.endsWith("ाओगे")
||st.endsWith("एंगी")
|| st.endsWith("ेंगी")
||st.endsWith("एंगे")
||st.endsWith("ेंगे")
||st.endsWith("ूंगी")
||st.endsWith("ूंगा")
||st.endsWith("ातीं")
||st.endsWith("नाओं")
||st.endsWith("नाएं")
||st.endsWith("ताओं")
||st.endsWith("ताएं")
||st.endsWith("ियाँ")
||st.endsWith("ियों")
|| st.endsWith("ियां")
))
return len - 4;
// 3
if ((len > 4) && (st.endsWith("ाकर")
|| st.endsWith("ाइए")
|| st.endsWith("ाईं")
|| st.endsWith("ाया")
|| st.endsWith("ेगी")
||st.endsWith("ोगी")
|| st.endsWith("ोगे")
|| st.endsWith("ाने")
||st.endsWith("ाना")
||st.endsWith("ाते")
||st.endsWith("ाती")
||st.endsWith("ाता")
||st.endsWith("तीं")
||st.endsWith("ाओं")
||st.endsWith("ाएं")
||st.endsWith("ुओं")
||st.endsWith("ुएं")
||st.endsWith("ुआं")
))
return len - 3;
// 2
if ((len > 3) && (st.endsWith("कर")
|| st.endsWith("ाओ")
|| st.endsWith("िए")
|| st.endsWith("ाई")
|| st.endsWith("ाए")
|| st.endsWith("ने")
|| st.endsWith("नी")
|| st.endsWith("ना")
|| st.endsWith("ते")
|| st.endsWith("ीं")
|| st.endsWith("ती")
|| st.endsWith("ता")
|| st.endsWith("ाँ")
|| st.endsWith("ां")
|| st.endsWith("ों")
|| st.endsWith("ें")
))
return len - 2;
// 1
if ((len > 2) && (st.endsWith("ो")
|| st.endsWith("े")
|| st.endsWith("ू")
||st.endsWith("ु")
|| st.endsWith("ी")
||st.endsWith("ि")
||st.endsWith("ा")
))
return len - 1;
return len;
}
}

    
   
  





