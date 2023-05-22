package com.fiscaliageneralags.fiscalia.Utils;

import androidx.annotation.NonNull;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by ERodriguezF on 29/11/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public abstract class PostMETValidator extends METValidator {

    private MaterialEditText caller;
    private METValidator validator;

    public PostMETValidator(@NonNull MaterialEditText caller, @NonNull String errorMessage, @NonNull METValidator validator ){
        super(errorMessage);
        this.caller = caller;
        this.validator = validator;
    }


    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        if (!validator.isValid(text,isEmpty)){
            postValidation(caller,text);
            return false;
        }
        return true;
    }

    public abstract void postValidation(@NonNull MaterialEditText caller, @NonNull CharSequence text);

}
