package blake.androidminigames;

import android.widget.ImageButton;

public class Tile {
    ImageButton ib;
    Integer state;
    Tile(ImageButton ib) {
        this.ib = ib;
        this.state = -1;
    }
}