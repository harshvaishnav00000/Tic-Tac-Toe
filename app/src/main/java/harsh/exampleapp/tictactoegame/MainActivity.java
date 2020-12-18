package harsh.exampleapp.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    0: X , 1: O  , 2: empty
    int r = 0;
    boolean active = true;
    int[] games = {2,2,2,2,2,2,2,2,2};
    int[][] wp = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void drop (View view) {
        ImageView counter = (ImageView) view;
        int tcount = Integer.parseInt(counter.getTag().toString());
        if (games[tcount] == 2 && active) {
            counter.setTranslationY(-1500);
            games[tcount] = r;
            if (r == 0) {
                counter.setImageResource(R.drawable.x);
                r = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                r = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] wp : wp) {
                if (games[wp[0]] == games[wp[1]] && games[wp[1]] == games[wp[2]] && games[wp[0]] != 2) {
                    String win = "";
                    active = false;
                    if (r == 1) {
                        win = "X wins";
                    } else {
                        win = "O wins";
                    }
                    Toast.makeText(this, win, Toast.LENGTH_SHORT).show();
                    Button playb = (Button) findViewById(R.id.playb);
                    playb.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playagain (View view){
        Button playb = (Button) findViewById(R.id.playb);
        playb.setVisibility(View.VISIBLE);
        r = 0;
        active = true;
        for (int i = 0;i <games.length ; i++){
            games[i] = 2;
        }
        playb.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gamegridlayout);
        for (int i=0; i < gridLayout.getChildCount(); i++){
            ImageView count = (ImageView) gridLayout.getChildAt(i);
            count.setImageDrawable(null);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}