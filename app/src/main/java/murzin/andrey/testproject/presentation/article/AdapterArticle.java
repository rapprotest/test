package murzin.andrey.testproject.presentation.article;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.data.model.ArticleItem;


public class AdapterArticle extends RecyclerView.Adapter<AdapterArticle.ViewHolder> {

    private List<ArticleItem> articleItems;

    public AdapterArticle(List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    @Override
    public AdapterArticle.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterArticle.ViewHolder holder, int position) {
        ArticleItem articleItem = articleItems.get(position);
        if (articleItem != null) {
            holder.tvHeader.setText(articleItem.getHeader());
            holder.tvText.setText(articleItem.getText());
        }
    }

    @Override
    public int getItemCount() {
        return articleItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_header)
        TextView tvHeader;
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
