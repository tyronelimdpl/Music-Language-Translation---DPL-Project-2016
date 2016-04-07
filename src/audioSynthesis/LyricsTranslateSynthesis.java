/**
 * Name: Tyrone Lim
 * ID: 0702477
 * Tutor: Dr. Sean Thorpe
 *************************
 */
package audioSynthesis;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.*;

import llvm.DPLMusicCompileMagicTokenManager;
import llvm.ParseException;
import llvm.SimpleCharStream;
import llvm.Token;

public class LyricsTranslateSynthesis extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//final variables
	static private final String newline ="\n";
	
	//global variable
	Token temp_token = null;
	DPLMusicCompileMagicTokenManager TkMgr;
	private String strFPath;
	private String strAction;
	//private boolean stop = false;
	Thread AddBeatThread;
	Thread ReadThread;

	//local variables
	private JButton btnOpenFile;
	private JButton btnReadLyrics;
	private JButton btnTranslateENtoES;
	private JButton btnTranslateEStoEN;
	private JButton btnAddbeat;
	private JTextArea txtTextView;
	private JScrollPane jsTextScrollPane;
	private JFileChooser jfOpenFile;
	
	

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	        	 ShowDisplay();
	         }
	      });
		
	}
	
	public static void ShowDisplay(){	
		try{
			for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}catch(ClassNotFoundException ex){
			System.out.println("GUI ERROR: Class not found");
		}catch(InstantiationException ex){
			System.out.println("GUI ERROR: cannot initialize");
		}catch(IllegalAccessException ex){
			System.out.println("GUI ERROR: illegal access");
		}catch(UnsupportedLookAndFeelException ex){
			System.out.println("GUI ERROR: unsupported");
		}
				
		LyricsTranslateSynthesis runprogram = new LyricsTranslateSynthesis();
		runprogram.setVisible(true);
		runprogram.setTitle("DPL Text to Speech Synthesis App");
	}
	
	public LyricsTranslateSynthesis(){
		drawGUI();
		
	}
	
	@SuppressWarnings("deprecation")
	private void drawGUI(){
		
		btnOpenFile = new JButton();
		btnReadLyrics = new JButton();
		btnTranslateENtoES = new JButton();
		btnTranslateEStoEN = new JButton();
		btnAddbeat = new JButton();
		txtTextView = new JTextArea();
		jsTextScrollPane = new JScrollPane();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setCursor(new Cursor(DEFAULT_CURSOR));
		
		btnOpenFile.setText("Open File");
		btnOpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnOpenFileActionPerformed(e);
			}
		});
		
		btnReadLyrics.setText("Read Lyrics");
		btnReadLyrics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnReadLyricsActionPerformed(e);
				} catch (ParseException e1) {
					System.out.println("PARSE ERROR");
				}				
			}
		});
		
		btnTranslateENtoES.setText("Translate to Spanish");
		btnTranslateENtoES.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTranslateActionPerformed(e);				
			}
		});
		
		btnAddbeat.setText("Add Beat");
		btnAddbeat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddbeatActionPerformed(e);				
			}
		});
		btnTranslateEStoEN.setText("Translate to English");
		btnTranslateEStoEN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTranslateActionPerformed(e);				
			}
		});
		
		txtTextView.setColumns(20);
		txtTextView.setRows(5);
		jsTextScrollPane.setViewportView(txtTextView);
		
		//jsTextScrollPane = new JScrollPane(txtTextView,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(jsTextScrollPane,GroupLayout.DEFAULT_SIZE,424,Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
						.addComponent(btnOpenFile,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnReadLyrics, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnTranslateENtoES, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnTranslateEStoEN, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnAddbeat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
					.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING,layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jsTextScrollPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING,false)
							.addComponent(btnOpenFile,GroupLayout.PREFERRED_SIZE,45,GroupLayout.PREFERRED_SIZE)
							.addComponent(btnReadLyrics,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
							.addComponent(btnTranslateENtoES,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
							.addComponent(btnTranslateEStoEN,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
							.addComponent(btnAddbeat,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE))
						.addGap(12,12,12))
				);
		
		pack();
	}
	
	public String ConvertReadertoString(Reader initialReader) throws IOException {
			    char[] arr = new char[8 * 1024];
			    StringBuilder buffer = new StringBuilder();
			    int numCharsRead;
			    while ((numCharsRead = initialReader.read(arr, 0, arr.length)) != -1) {
			        buffer.append(arr, 0, numCharsRead);
			    }
			    initialReader.close();
			    String targetString = buffer.toString();
			    return targetString;
			}
	
	private void btnOpenFileActionPerformed(ActionEvent e) {
		jfOpenFile = new JFileChooser();
		strAction="OpenFile";
		System.out.println("Pressed "+strAction);
		txtTextView.setText("");
		int PathValue = jfOpenFile.showOpenDialog(LyricsTranslateSynthesis.this);
		if (PathValue == JFileChooser.APPROVE_OPTION) {
            File file = jfOpenFile.getSelectedFile();
            strFPath=jfOpenFile.getSelectedFile().getAbsolutePath();
            System.out.println("Opening: " + file.getName() + "." + newline);
            txtTextView.append(OpenFile.readFile(strFPath));
            System.out.println(strFPath);
        } else {
        	System.out.println("Open command cancelled by user." + newline);
        }
	}
	
	private void btnReadLyricsActionPerformed(ActionEvent e) throws ParseException {
		strAction="ReadFile";
		System.out.println("Pressed "+strAction);
		Reader reader = new StringReader(txtTextView.getText());
		ReadThread = new Thread() {
            @Override
        public void run() { 
		try {
			SimpleCharStream stream = new SimpleCharStream(reader);
			TkMgr = new DPLMusicCompileMagicTokenManager(stream);
			int count = 0;
    		do {
    	//		if (stop) break;
    			temp_token = TkMgr.getNextToken();
				switch(temp_token.kind) {
						case llvm.DPLMusicCompileMagicConstants.VERSE:		
							count++;
							System.out.println("\nVERSE "+count+" :    " + temp_token.image);
							ConstructVerse(temp_token.image);
           				break;
         				case llvm.DPLMusicCompileMagicConstants.CHORUS:    
         					//System.out.println("\nCHORUS :    " + temp_token.image);
         					ConstructChorus(temp_token.image);
           				break;
         				case llvm.DPLMusicCompileMagicConstants.BRIDGE:    
         					//System.out.println("\nBRIDGE :    " + temp_token.image);
         					ConstructBridge(temp_token.image);
           				break;
         				default:
           					if ( temp_token.kind != llvm.DPLMusicCompileMagicConstants.EOF )
            					 System.out.println("\nOTHER: " + temp_token.image);
           				break;
        		}
    		} while (temp_token.kind != llvm.DPLMusicCompileMagicConstants.EOF);
			} 
			finally {
			}
        }      };
        ReadThread.start(); 
       
	}
	
	private void btnTranslateActionPerformed(ActionEvent e) {
		strAction="TranslateFile";
		System.out.println("Pressed "+strAction);
		Token temp_token = null;
		String Versetext="",Chorustext="",Bridgetext="";
		int count = 0;
		String strTransBtn = e.getActionCommand();
		String strAvailable = txtTextView.getText().toString();
	
		if(strTransBtn.equals("Translate to Spanish")){
			System.out.println("Translate to Spanish");
			if(strAvailable.equals("")){
				System.out.println("Please input text or upload file.\n");
			}else{
				txtTextView.setText("");
				TranslateFile translator = new TranslateFile("AIzaSyB0ZmBxBObBKcgKLKHbQ2YB8evQ3f3Z-ls");
				Reader reader = new StringReader(strAvailable);	
				try {
					SimpleCharStream stream = new SimpleCharStream(reader);
					DPLMusicCompileMagicTokenManager TkMgr = new DPLMusicCompileMagicTokenManager(stream);
		    		do {
		    			
		        		temp_token = TkMgr.getNextToken();
						switch(temp_token.kind) {
								case llvm.DPLMusicCompileMagicConstants.VERSE:		
									count++;
									System.out.println("\nVERSE "+count+" :    " + temp_token.image);
									Versetext = translator.translte(temp_token.image, "en", "es","verse");
									txtTextView.append("\n"+Versetext);
									txtTextView.append("\n");
		           				break;
		         				case llvm.DPLMusicCompileMagicConstants.CHORUS:    
		         					System.out.println("\nCHORUS :    " + temp_token.image);
		         					Chorustext = translator.translte(temp_token.image, "en", "es","chorus");
		         					txtTextView.append("\n"+Chorustext);
		         					txtTextView.append("\n");
		           				break;
		         				case llvm.DPLMusicCompileMagicConstants.BRIDGE:    
		         					System.out.println("\nBRIDGE :    " + temp_token.image);
		         					Bridgetext = translator.translte(temp_token.image, "en", "es","bridge");
		         					Bridgetext=Bridgetext.replaceAll("&quot","\"");
		         					Bridgetext=Bridgetext.replaceAll(";","");
		         					txtTextView.append("\n"+Bridgetext);
		         					txtTextView.append("\n");
		           				break;
		         				default:
		           					if ( temp_token.kind != llvm.DPLMusicCompileMagicConstants.EOF )
		            					// System.out.println("\nOTHER: " + temp_token.image);
		           				break;
		        		}
		    		} while (temp_token.kind != llvm.DPLMusicCompileMagicConstants.EOF);
					
					} 
					finally {
					}
				
				jsTextScrollPane.revalidate();
				jsTextScrollPane.repaint();
				System.out.println("Job Done.");
				
			} 
		}else{
			System.out.println("Translate to English");
			if(strAvailable.equals("")){
				System.out.println("Please input text or upload file.\n");
			}else{
				TranslateFile translator = new TranslateFile("AIzaSyB0ZmBxBObBKcgKLKHbQ2YB8evQ3f3Z-ls");
				String text = translator.translte(strAvailable, "es", "en","construct");
				System.out.println(text);
				txtTextView.setText(text);
				jsTextScrollPane.revalidate();
				jsTextScrollPane.repaint();
			}
		}  
	}
	
	private void btnAddbeatActionPerformed(ActionEvent e) {
		strAction="AddBeatToFile";
		System.out.println("Pressed "+strAction);
		//stop = true;
		
		AddBeatThread = new Thread() {
            @Override
        public void run() {      	
		//new AddBeats(5,6);
		Reader reader = new StringReader(txtTextView.getText().toString());	
		try {
			SimpleCharStream stream = new SimpleCharStream(reader);
			//Token temp_token = null;
			TkMgr = new DPLMusicCompileMagicTokenManager(stream);
    		do {
    	//		if (stop) break;
    			temp_token = TkMgr.getNextToken();
				switch(temp_token.kind) {
						case llvm.DPLMusicCompileMagicConstants.VERSE:		
							new AddBeats(4,1);
           				break;
         				case llvm.DPLMusicCompileMagicConstants.CHORUS:    
         					new AddBeats(4,4);
           				break;
         				case llvm.DPLMusicCompileMagicConstants.BRIDGE:    
         					new AddBeats(1,8);
           				break;
         				default:
           					if ( temp_token.kind != llvm.DPLMusicCompileMagicConstants.EOF )
            					 System.out.println("\nOTHER: " + temp_token.image);
           				break;
        		}
    		} while (temp_token.kind != llvm.DPLMusicCompileMagicConstants.EOF);
			} 
			finally {
			}
		
            }
		};
		//ReadThread.stop();
	//	ReadThread.start();
		AddBeatThread.start();
	}
	
	private void ConstructVerse(String VerseAction){
		new Speech(VerseAction);
	}
	
	private void ConstructChorus(String ChorusAction){
		new Speech(ChorusAction);
	}

	private void ConstructBridge(String BridgeAction){
		new Speech(BridgeAction);
	}

}
