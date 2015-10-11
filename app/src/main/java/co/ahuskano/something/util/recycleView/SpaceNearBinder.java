package co.ahuskano.something.util.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.ahuskano.something.R;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.AdapterItem;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class SpaceNearBinder extends DataBinder<SpaceNearBinder.ViewHolder> {

    public SpaceNearBinder(RecycleAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        return new ViewHolder(getView(parent));
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.findViews();
        holder.fillDate((Space) dataBindAdapter.getItem(position));

    }

    @Override
    public int getItemCount() {
        return dataBindAdapter.getItemCount();
    }

    @Override
    public int provideLayout() {
        return R.layout.space_near_binder;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements AdapterItem<Space> {

        private ImageView image;
        private View view;
        private TextView name, distance;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }


        @Override
        public void findViews() {
            image = (ImageView) view.findViewById(R.id.ivImage);
            name = (TextView) view.findViewById(R.id.tvName);
            distance = (TextView) view.findViewById(R.id.tvDistance);
        }

        @Override
        public void fillDate(Space model) {
            Picasso.with(view.getContext()).load(model.getImage().getUrl()).placeholder(R.drawable.default_image).into(image);
            name.setText(model.getName());
            distance.setText((float)model.getDistance()/1000+"km");
        }
    }


}

