package blake.androidminigames;

import android.app.Activity;
import android.os.Bundle;

public class GameOfLife extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gol);
    }

}
