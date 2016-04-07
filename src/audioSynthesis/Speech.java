package audioSynthesis;

import java.io.File;
import java.io.FileNotFoundException;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speech {
    VoiceManager freettsVM;
    Voice freettsVoice;
    String sysPath=System.getProperty("user.home"),voiceName="";

    public Speech(String words) {
        // Most important part!
    	if (!new File("/home/parallels/mbrolaAPI").isDirectory())
    	{
    		
    	   try 
    	   {
    		   voiceName = "kevin";
    		   throw new FileNotFoundException("\nYikes! ");
    	   }
    	   catch (FileNotFoundException e) 
    	   {System.out.println("mbrola voices not found\nPlease place mbrolaAPI folder in user home foler.");}   
    	}else{
    		voiceName = "mbrola_us1";
    	}
        
    	//System.out.println(System.getProperty("user.home"));
    	System.setProperty("mbrola.base", sysPath+"/mbrolaAPI");
        freettsVM = VoiceManager.getInstance();
        
        // Simply change to MBROLA voice
        freettsVoice = freettsVM.getVoice(voiceName);
    
        // Allocate your chosen voice
        freettsVoice.allocate();
        sayWords(words);
    }
    
    public static void listAllVoices() {
    	  System.out.println();
    	  System.out.println("All voices available:");
    	  VoiceManager voiceManager = VoiceManager.getInstance();
    	  Voice[] voices = voiceManager.getVoices();
    	  for (int i = 0; i < voices.length; i++) {
    	   System.out.println("    " + voices[i].getName() + " ("
    	     + voices[i].getDomain() + " domain)");
    	  }
    	 }

    public void sayWords(String words) {
        // Make her speak!
        freettsVoice.speak(words);
    }
 
/**
  	public static void main(String [] args) {    	
  		//new Speech("Hello there! Now M BROLA and Free T T S work together!");
  		new Speech("Hello");
  		listAllVoices();
	}
**/
}