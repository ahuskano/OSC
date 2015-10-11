package co.ahuskano.something.util.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.ahuskano.something.R;
import co.ahuskano.something.models.Review;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.AdapterItem;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class ReviewBinder extends DataBinder<ReviewBinder.ViewHolder> {

    public ReviewBinder(RecycleAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        return new ViewHolder(getView(parent));
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.findViews();
        holder.fillDate((Review) dataBindAdapter.getItem(position));
    }

    @Override
    public int getItemCount() {
        return dataBindAdapter.getItemCount();
    }

    @Override
    public int provideLayout() {
        return R.layout.review_binder;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements AdapterItem<Review> {

       private TextView name,date;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }


        @Override
        public void findViews() {
            name=(TextView) view.findViewById(R.id.tvDescription);
        }

        @Override
        public void fillDate(Review model) {
            name.setText(model.getDescription());
        }
    }


}