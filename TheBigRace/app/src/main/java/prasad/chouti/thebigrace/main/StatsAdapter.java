package prasad.chouti.thebigrace.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import prasad.chouti.thebigrace.R;
import prasad.chouti.thebigrace.models.Contestants;

/**
 * Date   : 3/29/17
 * Time   : 11:18 AM
 */

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_CONTENT = 1;


    private List<Contestants.Runners> runnersList;

    public StatsAdapter(List<Contestants.Runners> runnersList) {
        this.runnersList = runnersList;
    }

    @Override
    public StatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {

            final View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
            return new StatViewHolder(listItemView);

        } else {

            final View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new StatViewHolder(listItemView);
        }
    }

    @Override
    public void onBindViewHolder(StatViewHolder holder, int position) {

        if (position == 0) {
            return;
        } else {
            holder.age.setText(String.valueOf(runnersList.get(position - 1).getAge()));
            holder.time.setText(String.valueOf(runnersList.get(position - 1).getTime()));
            holder.name.setText(String.valueOf(runnersList.get(position - 1).getName()));
            holder.rank.setText(String.valueOf(runnersList.get(position - 1).getRank()));
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return runnersList.size() + 1;
    }

    class StatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_name)
        TextView name;

        @BindView(R.id.list_item_time)
        TextView time;

        @BindView(R.id.list_item_age)
        TextView age;

        @BindView(R.id.list_item_ranking)
        TextView rank;


        public StatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
