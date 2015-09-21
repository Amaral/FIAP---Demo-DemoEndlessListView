package br.com.felipeamaral.demoendlesslistview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.felipeamaral.demoendlesslistview.adapter.EndlessAdapter;
import br.com.felipeamaral.demoendlesslistview.customview.EndlessListView;
import br.com.felipeamaral.demoendlesslistview.listener.EndlessListener;

public class MainActivity extends AppCompatActivity implements EndlessListener {

    private final static int TOTAL_ITEM_REQUISICAO = 50;
    private EndlessListView evLista;
    private int page = 0;
    private EndlessAdapter endlessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        evLista = (EndlessListView) findViewById(R.id.evLista);
        endlessAdapter = new EndlessAdapter(this, getItens(page), R.layout.row_layout);
        evLista.setLoadingView(R.layout.loading_layout);
        evLista.setAdapter(endlessAdapter);
        evLista.setEndlessListener(this);
    }

    private class FakeLoad extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String ... params) {
            try {
                Thread.sleep(4000);
            }catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            return getItens(page);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);
            evLista.addNewData(result);
        }
    }

    private List<String> getItens(int page) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < TOTAL_ITEM_REQUISICAO; i++) {
            result.add("Item " + (i *( page + i)));
        }
        page++;
        return result;
    }

    @Override
    public void loadData() {
        FakeLoad fake = new FakeLoad();
        fake.execute(new String[]{});
    }





}
