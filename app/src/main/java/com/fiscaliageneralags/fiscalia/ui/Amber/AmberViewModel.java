package com.fiscaliageneralags.fiscalia.ui.Amber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AmberViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AmberViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}