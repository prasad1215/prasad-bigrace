package prasad.chouti.thebigrace.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import prasad.chouti.thebigrace.R;
import prasad.chouti.thebigrace.models.Contestants;

public class MainActivity extends AppCompatActivity implements RaceView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        RacePresenter presenter = new RacePresenter(this);
        presenter.getRaceStats();
    }


    @Override
    public void showRaceStats(List<Contestants.Runners> contestants) {

        StatsAdapter statsAdapter = new StatsAdapter(contestants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(statsAdapter);
        statsAdapter.notifyDataSetChanged();


    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading");
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    @Override
    public void onError(String message) {


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
