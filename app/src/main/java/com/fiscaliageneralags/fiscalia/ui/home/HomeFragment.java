package com.fiscaliageneralags.fiscalia.ui.home;

import android.Manifest;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.R;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.fiscaliageneralags.fiscalia.R.color.transparent;

public class HomeFragment extends Fragment {
    private List<Model> serviciosLista;
    private MenuAdapter menuAdapter;
    private RecyclerView recyclerView;
   // private Fragment actualFragment;
    private HomeViewModel homeViewModel;
    private final static int REQ_CODE_PHONE_STATE = 1025;

    public static HomeFragment newInstance() {
       HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        //Put aguments to pass to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        String[] perms = {Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.INTERNET,Manifest.permission.RECEIVE_BOOT_COMPLETED,Manifest.permission.MODIFY_PHONE_STATE,Manifest.permission.ACCESS_WIFI_STATE};//READ_CONTACTS};
         if (!EasyPermissions.hasPermissions(getContext(), perms)) {
            EasyPermissions.requestPermissions(this, "Se necesita el permiso", REQ_CODE_PHONE_STATE, perms);
        }else{
            System.out.println("Ya tengo permisos?");
        }
        Toolbar toolbar =  getActivity().findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(transparent));
        AppBarLayout barLayout = getActivity().findViewById(R.id.fondoToolbar);
        barLayout.setBackground(getResources().getDrawable(transparent));



        recyclerView = root.findViewById(R.id.recyclerview);
        serviciosLista = new ArrayList<>();
        menuAdapter = new MenuAdapter(getContext(), serviciosLista);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3,dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);
        InsertDataIntoCards();
        return root;
    }
    private void InsertDataIntoCards() {
        int [] servicecovers = new int[]{
                R.drawable.denunanon,
                R.drawable.buzonfiscal,
                R.drawable.comunica,
                R.drawable.checauto,
                R.drawable.numemer,
                R.drawable.orientacion,
                R.drawable.prede,
                R.drawable.quehacer,
                R.drawable.ubicacion
        };

        Model modelo = new Model("Predenuncia",servicecovers[6]);
        serviciosLista.add(modelo);

         modelo = new Model("Denuncia anonima",servicecovers[0]);
        serviciosLista.add(modelo);

        modelo = new Model("Ubicación de Agencias",servicecovers[8]);
        serviciosLista.add(modelo);

        modelo = new Model("Numeros de Emergencia",servicecovers[4]);
        serviciosLista.add(modelo);

        modelo = new Model("Buzón del Fiscal",servicecovers[1]);
        serviciosLista.add(modelo);

        modelo = new Model("Qué hacer en caso de... ?",servicecovers[7]);
        serviciosLista.add(modelo);

        modelo = new Model("Orientación en Secuestro Virtual y Extorsión",servicecovers[5]);
        serviciosLista.add(modelo);

        modelo = new Model("Comunicación social",servicecovers[2]);
        serviciosLista.add(modelo);

        modelo = new Model("Checauto",servicecovers[3]);
        serviciosLista.add(modelo);


        menuAdapter.notifyDataSetChanged();
    }

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view);
            int col = position % spanCount;
            if (includeEdge){
                outRect.left = spacing - col * spacing / spanCount;
                outRect.right = (col + 5 ) * spacing / spanCount;
                if(position < spanCount ){
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            }else{
                outRect.left = col * spacing * spacing / spanCount;
                outRect.right = spacing - (col + 5) * spacing;
                if(position >= spanCount){
                    outRect.top= spacing;
                }

            }
        }
    }

    private int dpToPx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, r.getDisplayMetrics()));
    }
}
