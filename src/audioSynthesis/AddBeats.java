package audioSynthesis;


import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

public class AddBeats {
	
	public AddBeats(int progbar, int beatbars){
		 Player beat = new Player();
	       Rhythm bars = new Rhythm().addLayer("..X...X...X...XO");
	       bars.addLayer("..S...S...S...S.").addLayer("````````````````");
	       ChordProgression prog = new ChordProgression("I IV vi V").eachChordAs("$_i $_i Ri $_i");     
	       beat.play(prog.getPattern().repeat(progbar),bars.getPattern().repeat(beatbars));
	}
    
/**	public static void main(String[] args) {
       Player beat = new Player();
       Rhythm bars = new Rhythm().addLayer("..X...X...X...XO");
       bars.addLayer("..S...S...S...S.").addLayer("````````````````");
       ChordProgression prog = new ChordProgression("I IV vi V").eachChordAs("$_i $_i Ri $_i");     
       beat.play(prog.getPattern().repeat(6),bars.getPattern().repeat(6));
    }**/
	
}