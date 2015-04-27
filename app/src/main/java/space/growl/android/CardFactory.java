package space.growl.android;

import android.content.Context;
import android.view.View;

import com.dexafree.materialList.cards.BasicButtonsCard;
import com.dexafree.materialList.cards.OnButtonPressListener;
import com.dexafree.materialList.model.Card;

import space.growl.android.entity.Post;

/**
 * Created by Nicholas on 4/27/2015.
 */
public class CardFactory {
    public static BasicButtonsCard PostToCard(Post post, Context context) {
        BasicButtonsCard basicButtonsCard = new BasicButtonsCard(context);
        basicButtonsCard.setTitle(post.getUsername());
        basicButtonsCard.setDescription(post.getCaption());
        basicButtonsCard.setLeftButtonText("Like");
        basicButtonsCard.setRightButtonText("Play");

        return basicButtonsCard;
    }

}
