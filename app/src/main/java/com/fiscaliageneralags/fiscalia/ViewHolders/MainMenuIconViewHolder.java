package com.fiscaliageneralags.fiscalia.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.R;

/**
 * Created by ERodriguezF on 04/12/2017.
 * @author ERodriguezF
 * @version 0.9
 */

public class MainMenuIconViewHolder extends RecyclerView.ViewHolder {

    /**
     * Icon Image View instance of {@link R.layout#card_view_menu_icon} layout.
     */
    private ImageView iconImageView;

    /**
     * Label Text View instance of {@link R.layout#card_view_menu_icon} layout.
     */
    private TextView nameTextView;

    /**
     * Background Image View instance of {@link R.layout#card_view_menu_icon} layout.
     */
    private ImageView backgroundImage;

    /**
     * Create a View Holder For the Instance of {@link R.layout#card_view_menu_icon}.
     * @param itemView The instance of {@link R.layout#card_view_menu_icon} layout of the view holder.
     */
    public MainMenuIconViewHolder(View itemView) {
        super(itemView);
        iconImageView = itemView.findViewById(R.id.main_menu_item_image_view_icon);
        nameTextView = itemView.findViewById(R.id.main_menu_item_text_view_name);
        backgroundImage = itemView.findViewById(R.id.main_menu_item_image_view_background);
    }

    /**
     *
     * @return The Icon {@link ImageView} instance of the instance of {@link R.layout#card_view_menu_icon} layout.
     */
    public ImageView getIconImageView() {
        return iconImageView;
    }

    /**
     *
     * @return The Name {@link TextView} instance of the instance of {@link R.layout#card_view_menu_icon} layout.
     */
    public TextView getNameTextView() {
        return nameTextView;
    }

    /**
     *
     * @return The Background {@link ImageView} instance of the instance of {@link R.layout#card_view_menu_icon} layout.
     */
    public ImageView getBackgroundImage() {
        return backgroundImage;
    }

}
