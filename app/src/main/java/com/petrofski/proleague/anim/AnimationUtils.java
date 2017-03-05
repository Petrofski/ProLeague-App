package com.petrofski.proleague.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by georgespetrofski on 18/12/15.
 */
public class AnimationUtils {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown==true?100:-100, 0);
        animatorTranslateY.setDuration(500);
        animatorTranslateY.start();
    }

}
