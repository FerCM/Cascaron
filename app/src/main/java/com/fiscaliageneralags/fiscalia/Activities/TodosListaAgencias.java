package com.fiscaliageneralags.fiscalia.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.Adapters.ListaAgenciasViewAdapter;
import com.fiscaliageneralags.fiscalia.FiscApplication.FiscApp;
import com.fiscaliageneralags.fiscalia.Models.AuxiliarEdificios;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.IUpdatable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.objectbox.Box;

public class TodosListaAgencias extends Fragment implements IUpdatable{

    @BindView(R.id.lista_negra_recycler_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ListaAgenciasViewAdapter adapter;
     ////SERVICIOS
    public TodosListaAgencias(){}

    public static TodosListaAgencias newInstance(){
        TodosListaAgencias fragment = new TodosListaAgencias();
        Bundle args =  new Bundle();
       //Put aguments to pass to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_local_negra_lista_table, container, false);
        // Call ButterKnife After loading the other layouts
        unbinder = ButterKnife.bind(this, rootView);
        Box<AuxiliarEdificios> numeroListaAgenciasBox = ((FiscApp) getActivity().getApplication()).getBoxStore().boxFor(AuxiliarEdificios.class);
        List<AuxiliarEdificios> listaAgenciaLocals = numeroListaAgenciasBox.getAll();
        System.out.println("K vergas?" + listaAgenciaLocals);
        adapter = new ListaAgenciasViewAdapter(
                listaAgenciaLocals,
                getContext()
        );
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

  @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



    @Override
    public void UpdateContent() {

    }


}
