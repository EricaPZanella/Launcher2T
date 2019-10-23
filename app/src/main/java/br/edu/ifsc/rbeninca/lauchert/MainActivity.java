package br.edu.ifsc.rbeninca.lauchert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LaucherControler laucherControler;
    EditText editTextPesquisa;
    ArrayList<AppInfo> aplicativosList;
    AppInfoArrayAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        //editTextPesquisa = findViewById(R.id.editTextKeyWord);



        //Inicialização do controlador do Laucher
        laucherControler=new  LaucherControler(getApplicationContext());
        this.refreshList("");

        //configurando recycler view.
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3); //new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //configurando click do recyclerview.
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        AppInfo appInfo=(AppInfo)  mAdapter.mDataset.get(position);
                        Intent intent = getPackageManager().getLaunchIntentForPackage ( appInfo.pname  );
                        Toast.makeText(getApplicationContext(),appInfo.appname,Toast.LENGTH_LONG).show();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
        //configurando o textwacher
            //editTextPesquisa.addTextChangedListener(this.textWatcherPesquisa);
        }

    public void refreshList(String key ){
        aplicativosList=laucherControler.loadAppInf(key);
        mAdapter  = new AppInfoArrayAdapter(getApplicationContext(),
                R.layout.item_list_app,
                aplicativosList );
                recyclerView.setAdapter(mAdapter);
    }







    //Implementação do analisador texto para o campo de pesquisa
    private TextWatcher textWatcherPesquisa =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            refreshList(editTextPesquisa.getText().toString());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };










}
