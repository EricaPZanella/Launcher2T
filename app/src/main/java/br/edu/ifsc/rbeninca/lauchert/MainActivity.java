package br.edu.ifsc.rbeninca.lauchert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LaucherControler laucherControler;
    EditText editTextPesquisa;
    ArrayList<AppInfo> aplicativosList;
    AppInfoArrayAdapter mAdapter;
    BottomNavigationView bottomNavigationView;
    private RecyclerView.LayoutManager layoutManager;
    String [] listaPacoteAppTela1 = {"com.google.android.dialer","com.google.android.apps.messaging","com.android.contacts", "com.google.android.deskclock", "com.android.calculator2", "com.google.android.music", "com.google.android.apps.maps"};
    String [] listaRenaimeTela1 = {"Chamadas","Mensagens","Contatos", "Despertador", "Câmera", "Reprodutor de música"};
    String [] listaPacoteAppTela2 = {"com.google.android.apps.maps", "com.google.android.youtube", "com.google.android.apps.photos", "com.google.android.videos", "com.android.settings"};
    String [] listaRenaimeTela2 ={"Mapas - GPS", "Youtube - Vídeos", "Galeria fotos", "Reprodutor de vídeos","Configurações do celular"};
    String [] listaPacoteAppTela3 ={"com.android.chrome"};
    String [] listaRenaimeTela3 ={"Internet"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        //editTextPesquisa = findViewById(R.id.editTextKeyWord);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemReselectedListener(
                new BottomNavigationView.OnNavigationItemReselectedListener() {
                    @Override
                    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.nav_setaesquerda:{



                            }
                            case R.id.nav_telainicial:{


                                
                            }
                            case R.id.nav_setadireita:{


                                
                            }
                        }

                    }

                    private void refreshList(String s) {
                    }
                });


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
