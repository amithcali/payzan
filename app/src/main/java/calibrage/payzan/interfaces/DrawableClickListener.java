package calibrage.payzan.interfaces;

/**
 * Created by Calibrage11 on 10/17/2017.
 */

public interface DrawableClickListener {
    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target);
}
