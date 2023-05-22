package com.fiscaliageneralags.fiscalia.ui.gallery;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fiscaliageneralags.fiscalia.IPresenters.IBuzonFiscalMainModulePresenter;
import com.fiscaliageneralags.fiscalia.IViews.IBuzonFiscalMainModuleView;
import com.fiscaliageneralags.fiscalia.MainActivity;
import com.fiscaliageneralags.fiscalia.Models.SugerenciaAlFiscal;
import com.fiscaliageneralags.fiscalia.Presenters.BuzonFiscalMainModulePresenter;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.INavegable;
import com.fiscaliageneralags.fiscalia.ui.home.HomeFragment;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.fiscaliageneralags.fiscalia.R.color.colorPrimary;

public class GalleryFragment extends Fragment  implements IBuzonFiscalMainModuleView {

    private GalleryViewModel galleryViewModel;
    @BindView(R.id.buzon_fiscal_edt_asunto)
    MaterialEditText asuntoEditText;
    @BindView(R.id.buzon_fiscal_edt_correo) MaterialEditText correoEditText;
    @BindView(R.id.buzon_fiscal_edt_mensaje) MaterialEditText mensajeEditText;
    @BindView(R.id.buzon_fiscal_edt_nombre) MaterialEditText nombreEditText;
   @Nullable
   @BindView(R.id.toolbar) Toolbar toolbar;
    private Unbinder unbinder;

    private IBuzonFiscalMainModulePresenter presenter;

    private MaterialDialog processDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        //Put aguments to pass to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        //Header:
        toolbar =  getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(colorPrimary));
     /*   toolbar.setBackground(colorPrimary);
        toolbar = rootView.findViewById(R.id.app_bar_main)
        toolbar.setBackgroundColor(getResources().getColor(colorPrimary));

        *////


        System.out.println("PERRA MADRE!");
        unbinder = ButterKnife.bind(this, rootView);
        addEditTextValidators();
        processDialog = new MaterialDialog.Builder(getContext())
                .content("Enviando Mensaje ...")
                .progress(true, 0)
                .build();
        processDialog.setCanceledOnTouchOutside(false);
        presenter = new BuzonFiscalMainModulePresenter(getContext(),this);

        return rootView;
    }
    public void addEditTextValidators(){
        nombreEditText.addValidator(new METValidator("Este campo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length()>0;
            }
        });
        correoEditText.addValidator(new METValidator("Ingrese un correo electrónico valido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return Patterns.EMAIL_ADDRESS.matcher(text).matches();
            }
        });
        asuntoEditText.addValidator(new METValidator("Este campo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length() > 0;
            }
        });
        mensajeEditText.addValidator(new METValidator("Este campo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length() > 0;
            }
        });
    }

    private SugerenciaAlFiscal getSugerenciaAlFiscal(){
        SugerenciaAlFiscal sugerencia = new SugerenciaAlFiscal();
        sugerencia.asunto = asuntoEditText.getText().toString();
        sugerencia.correo = correoEditText.getText().toString();
        sugerencia.nombre = nombreEditText.getText().toString();
        sugerencia.sugerencia = mensajeEditText.getText().toString();
        return sugerencia;
    }

    @OnClick(R.id.buzon_fiscal_enviar)
    public void onSugerenciaAlFiscal(){
        if( !(nombreEditText.validate() && correoEditText.validate() &&
                asuntoEditText.validate() && mensajeEditText.validate()) ){
            Toast.makeText(getContext(),"Por favor llene todos los campos.",Toast.LENGTH_LONG).show();
        }
        else{
            presenter.postSugerenciaAlFiscal(getSugerenciaAlFiscal());
            processDialog.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPostSugerenciaAlFiscalSuccess() {
        processDialog.dismiss();
        Toast.makeText(getContext(),"Tu mensaje al Fiscal fue enviado exitosamente.",Toast.LENGTH_LONG).show();
       /* if(getActivity() instanceof INavegable){
            ((INavegable)getActivity()).makeFragmentTransactionTo(R.id.nav_home);
        }*/
         Intent intent = new Intent(getActivity(), MainActivity.class);
         getActivity().startActivity(intent);
    }

    @Override
    public void onPostSugerenciaAlFiscalError() {
        processDialog.dismiss();
        Toast.makeText(getContext(),"Lo sentimos ha ocurrido un error, por favor inténtelo nuevamente.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConecctionError() {
        processDialog.dismiss();
        Toast.makeText(getContext(),"Lo sentimos ha ocurrido un error, por favor inténtelo nuevamente.",Toast.LENGTH_LONG).show();
    }
}
