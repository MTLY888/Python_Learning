import java.util.Random;                                                                              
import java.util.Scanner;                                                                             
                                                                                                      
/*                                                                                                    
Çë±àÐ´Ò»¸ö²ÂÈ­µÄÓÎÏ·                                                                                            
ÓÐ¸öÈË Tom£¬Éè¼ÆËûµÄ³ÉÔ±±äÁ¿. ³ÉÔ±·½·¨, ¿ÉÒÔµçÄÔ²ÂÈ­. µçÄÔÃ¿´Î¶¼»áËæ»úÉú³É 0, 1, 2                                                    
0 ±íÊ¾ Ê¯Í· 1 ±íÊ¾¼ôµ¶ 2 ±íÊ¾ ²¼                                                                                 
²¢Òª¿ÉÒÔÏÔÊ¾ TomµÄÊäÓ®´ÎÊý£¨Çåµ¥£©, ¼Ù¶¨ ÍæÈý´Î.                                                                          
 */ 
 // ²âÊÔÀà,Ö÷Àà
public class MoraGame {                                                                               
                                                                                                      
    // ²âÊÔ                                                                                             
    public static void main(String[] args) {                                                          
        // ´´½¨Ò»¸öÍæ¼Ò¶ÔÏó                                                                                   
        Tom t = new Tom();                                                                            
        // ÓÃÀ´¼ÇÂ¼×îºóÊäÓ®µÄ´ÎÊý                                                                                
        int isWinCount = 0;                                                                           
                                                                                                      
        // ´´½¨Ò»¸ö¶þÎ¬Êý×é£¬ÓÃÀ´½ÓÊÕ¾ÖÊý£¬Tom³öÈ­Çé¿öÒÔ¼°µçÄÔ³öÈ­Çé¿ö                                                            
        int[][] arr1 = new int[3][3];                                                                 
        int j = 0;                                                                                    
                                                                                                      
        // ´´½¨Ò»¸öÒ»Î¬Êý×é£¬ÓÃÀ´½ÓÊÕÊäÓ®Çé¿ö                                                                          
        String[] arr2 = new String[3];                                                                
                                                                                                      
        Scanner scanner = new Scanner(System.in);                                                     
        for (int i = 0; i < 3; i++) {   //±ÈÈü3´Î                                                              
            // »ñÈ¡Íæ¼Ò³öµÄÈ­                                                                                
            System.out.println("ÇëÊäÈëÄãÒª³öµÄÈ­£¨0-È­Í·£¬1-¼ôµ¶£¬2-²¼£©£º");                                           
            int num = scanner.nextInt();                                                              
            t.setTomGuessNum(num);                                                                    
            int tomGuess = t.getTomGuessNum();                                                        
            arr1[i][j + 1] = tomGuess;                                                                
                                                                                                      
            // »ñÈ¡µçÄÔ³öµÄÈ­                                                                                
            int comGuess = t.computerNum();                                                           
            arr1[i][j + 2] = comGuess;                                                                
                                                                                                      
            // ½«Íæ¼Ò²ÂµÄÈ­ÓëµçÄÔ×ö±È½Ï                                                                           
            String isWin = t.vsComputer();                                                            
            arr2[i] = isWin;                                                                          
            arr1[i][j] = t.count;                                                                     
                                                                                                      
            // ¶ÔÃ¿Ò»¾ÖµÄÇé¿ö½øÐÐÊä³ö                                                                            
           System.out.println("=========================================");                           
            System.out.println("¾ÖÊý\tÍæ¼ÒµÄ³öÈ­\tµçÄÔµÄ³öÈ­\tÊäÓ®Çé¿ö");                                             
            System.out.println(t.count + "\t" + tomGuess + "\t\t" + comGuess + "\t\t" + t.vsComputer());
            System.out.println("=========================================");                          
            System.out.println("\n\n");                                                               
            isWinCount = t.winCount(isWin);                                                           
        }                                                                                             
                                                                                                      
        // ¶ÔÓÎÏ·µÄ×îÖÕ½á¹û½øÐÐÊä³ö                                                                               
        System.out.println("¾ÖÊý\tÍæ¼ÒµÄ³öÈ­\tµçÄÔµÄ³öÈ­\t\tÊäÓ®Çé¿ö");                                               
        for (int a = 0; a < arr1.length; a++) {                                                       
            for (int b = 0; b < arr1[a].length; b++) {                                                
                System.out.print(arr1[a][b] + "\t\t\t");                                              
            }                                                                                         
                                                                                                      
            System.out.print(arr2[a]);                                                                
            System.out.println();                                                                     
        }                                                                                             
        System.out.println("ÄãÓ®ÁË" + isWinCount + "´Î");                                                 
    }                                                                                                 
                                                                                                      
}                                                                                                     

// TomÀà
class Tom {     // ºËÐÄ´úÂë  
	// Íæ¼Ò³öÈ­µÄÀàÐÍ 
    int tomGuessNum; //0,1,2
	// µçÄÔ³öÈ­µÄÀàÐÍ
    int comGuessNum; //0,1,2
	// Íæ¼ÒÓ®µÄ´ÎÊý
    int winCountNum;  
	// ±ÈÈüµÄ´ÎÊý
    int count = 1;   //Ò»¹²±ÈÈü3´Î                                                                                 
     
	
	public void showInfo() {
		//....
	}
	
    /**                                                                                               
     * µçÄÔËæ»úÉú³É²ÂÈ­µÄÊý×ÖµÄ·½·¨                                                                                 
     * @return                                                                                        
     */                                                                                               
    public int computerNum() {                                                                        
        Random r = new Random();                                                                      
        comGuessNum = r.nextInt(3);      // ·½·¨ ·µ»Ø 0-2µÄËæ»úÊý                                                             
        // System.out.println(comGuessNum);                                                           
        return comGuessNum;                                                                           
    }                                                                                                 
                                                                                                      
    /**                                                                                               
     * ÉèÖÃÍæ¼Ò²ÂÈ­µÄÊý×ÖµÄ·½·¨                                                                                   
     * @param tomGuessNum                                                                             
     */                                                                                               
    public void setTomGuessNum(int tomGuessNum) {                                                     
        if (tomGuessNum > 2 || tomGuessNum < 0) { 
			//Å×³öÒ»¸öÒì³£, ÀîÍ¬Ñ§»áÐ´£¬Ã»ÓÐ´¦Àí
            throw new IllegalArgumentException("Êý×ÖÊäÈë´íÎó");                                             
        }                                                                                             
        this.tomGuessNum = tomGuessNum;                                                               
    }                                                                                                 
                                                                                                      
    public int getTomGuessNum() {                                                                     
        return tomGuessNum;                                                                           
    }                                                                                                 
                                                                                                      
    /**                                                                                               
     * ±È½Ï²ÂÈ­µÄ½á¹û                                                                                        
     * @return Íæ¼ÒÓ®·µ»Øtrue£¬·ñÔò·µ»Øfalse                                                                    
     */                                                                                               
    public String vsComputer() { 
		 //±È½ÏÇÉ
        if (tomGuessNum == 0 && comGuessNum == 1) {                                                   
            return "ÄãÓ®ÁË";                                                                             
        } else if (tomGuessNum == 1 && comGuessNum == 2) {                                            
            return "ÄãÓ®ÁË";                                                                             
        } else if (tomGuessNum == 2 && comGuessNum == 0) {                                            
            return "ÄãÓ®ÁË";                                                                             
        } else if (tomGuessNum == comGuessNum){                                                       
            return "Æ½ÊÖ";                                                                              
        } else {                                                                                      
            return "ÄãÊäÁË";                                                                             
        }                                                                                             
    }                                                                                                 
                                                                                                      
    /**                                                                                               
     * ¼ÇÂ¼Íæ¼ÒÓ®µÄ´ÎÊý                                                                                       
     * @return                                                                                        
     */                                                                                               
    public int winCount(String s) {                                                                   
        count++;    //¿ØÖÆÍæµÄ´ÎÊý                                                                                   
        if (s.equals("ÄãÓ®ÁË")) {     //Í³¼ÆÓ®µÄ´ÎÊý                                                                   
            winCountNum++;                                                                            
        }                                                                                             
        return winCountNum;                                                                           
    }                                                                                                 
                                                                                                      
}                                                                                                     
                                                                                                      