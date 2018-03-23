package murzin.andrey.testproject.presentation.category;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.data.model.Event;


public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    public interface CategoryListner {
        void onClickCategory(String article);
    }


    private List<Event> events;
    private CategoryListner listner;

    public AdapterCategory(List<Event> events, CategoryListner listner) {
        this.events = events;
        this.listner = listner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        if (event != null) {
            holder.tvCofficient.setText(event.getCoefficient());
            holder.tvPlace.setText(event.getPlace());
            holder.tvPreview.setText(event.getPreview());
            holder.tvTime.setText(event.getTime());
            holder.tvTitle.setText(event.getTitle());
            holder.cardView.setOnClickListener(__ -> listner.onClickCategory(event.getArticle()));
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_coefficient)
        TextView tvCofficient;
        @BindView(R.id.tv_place)
        TextView tvPlace;
        @BindView(R.id.tv_preview)
        TextView tvPreview;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
