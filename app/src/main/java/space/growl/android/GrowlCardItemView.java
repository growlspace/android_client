package space.growl.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dexafree.materialList.model.CardItemView;

import space.growl.android.cards.GrowlCard;

/**
 * Created by Nicholas on 4/27/2015.
 */
public class GrowlCardItemView extends CardItemView<GrowlCard> {

    TextView mTitle;
    TextView mDescription;

    public GrowlCardItemView(Context context) {
        super(context);
    }

    public GrowlCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GrowlCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void build(GrowlCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
    }

    public void setTitle(String title){
        mTitle = (TextView)findViewById(R.id.titleTextView);
        mTitle.setText(title);
    }

    public void setDescription(String description){
        mDescription = (TextView) findViewById(R.id.descriptionTextView);
        mDescription.setText(description);
    }
}
