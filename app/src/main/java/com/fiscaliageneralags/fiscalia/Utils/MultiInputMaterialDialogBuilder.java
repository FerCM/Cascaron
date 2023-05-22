package com.fiscaliageneralags.fiscalia.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERodriguezF on 11/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class MultiInputMaterialDialogBuilder extends MaterialDialog.Builder {
    private Context mContext;
    private List<EditText> mEditTexts;
    private List<InputValidator> mValidators;
    private LinearLayout mRootView;

    /**
     * Custom material dialog with multiple inputs.
     * @param context context for the material dialog and inputs
     */
    public MultiInputMaterialDialogBuilder(@NonNull Context context) {
        super(context);
        mContext = context;
        mEditTexts = new ArrayList<>();
        mValidators = new ArrayList<>();

        mRootView = new LinearLayout(context);
        mRootView.setOrientation(LinearLayout.VERTICAL);

        this.autoDismiss(false);
    }

    /**
     * Adds an input to the material dialog
     * @param preFill Pre-filled text
     * @param hint Hint text
     * @param validator Costom validator for the text
     * @return
     */
    public MultiInputMaterialDialogBuilder addInput(CharSequence preFill, CharSequence hint, @NonNull InputValidator validator) {
        MaterialEditText newEditText = new MaterialEditText(mContext);
        newEditText.setText(preFill);
        newEditText.setHint(hint);
        newEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //newEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newEditText.setFloatingLabelText(hint);
        //newEditText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);
        newEditText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);

        mEditTexts.add(newEditText);
        mValidators.add(validator);
        mRootView.addView(newEditText);

        this.customView(mRootView, true);
        return this;
    }

    /**
     * Adds an input to the material dialog
     * @param preFill Pre-filled text
     * @param hint Hint text
     * @return
     */
    public MultiInputMaterialDialogBuilder addInput(CharSequence preFill, CharSequence hint) {
        return addInput(preFill, hint, new InputValidator() {
            @Override
            public CharSequence validate(CharSequence input) {
                return null;
            }
        });
    }

    /**
     * Adds an input to the material dialog
     * @param preFill
     * @param hint
     * @param validator
     * @return
     */
    public MultiInputMaterialDialogBuilder addInput(@StringRes int preFill, @StringRes int hint, @NonNull InputValidator validator) {
        return addInput(
                preFill == 0 ? null : mContext.getString(preFill),
                hint == 0 ? null : mContext.getString(hint),
                validator
        );
    }

    /**
     * Adds an input to the material dialog
     * @param preFill
     * @param hint
     * @return
     */
    public MultiInputMaterialDialogBuilder addInput(@StringRes int preFill, @StringRes int hint) {
        return addInput(preFill, hint, new InputValidator() {
            @Override
            public CharSequence validate(CharSequence input) {
                return null;
            }
        });
    }

    /**
     * Adds the given InputsCallback in every positive click
     * @param callback
     * @return
     */
    public MultiInputMaterialDialogBuilder inputs(@NonNull final InputsCallback callback) {
        this.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                //super.onPositive(dialog);

                List<CharSequence> inputs = new ArrayList<>();
                for (EditText editText : mEditTexts) {
                    inputs.add(editText.getText());
                }

                boolean allInputsValidated = true;
                for (int i = 0; i < inputs.size(); i++) {
                    CharSequence input = inputs.get(i);
                    CharSequence errorMessage = mValidators.get(i).validate(input);
                    boolean validated = errorMessage == null;
                    if (!validated) {
                        mEditTexts.get(i).setError(errorMessage);
                        allInputsValidated = false;
                    }
                }

                callback.onInputs(dialog, inputs, allInputsValidated);

                if (allInputsValidated) {
                    dialog.dismiss();
                }
            }
        });
        this.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * Callback Interface for inputs
     */
    public interface InputsCallback {
        void onInputs(MaterialDialog dialog, List<CharSequence> inputs, boolean allInputsValidated);
    }

    /**
     * Validator Interface
     */
    public interface InputValidator {
        /**
         * Validate text input
         *
         * @param input text to be validated
         * @return error message. Null if validated
         */
        CharSequence validate(CharSequence input);
    }

    /**
     * Default Input Validator for empty validation text
     */
    public InputValidator NonEmptyInputValidator = new InputValidator() {
        @Override
        public CharSequence validate(CharSequence input) {
            return TextUtils.isEmpty(input) ? mContext.getString(android.R.string.no) : null;
        }
    };
}