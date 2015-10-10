package co.ahuskano.something.util.recycleView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.ahuskano.something.R;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.AdapterItem;

public class SpaceBinder extends DataBinder<SpaceBinder.ViewHolder> {

    public SpaceBinder(RecycleAdapter dataBindAdapter) {
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
        return R.layout.space_binder;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements AdapterItem<Space> {

        private ImageView image;
        private View view;
        private TextView name,description;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }


        @Override
        public void findViews() {
            image=(ImageView) view.findViewById(R.id.ivImage);
            name=(TextView) view.findViewById(R.id.tvName);
            description=(TextView)view.findViewById(R.id.tvDescription);
        }

        @Override
        public void fillDate(Space model) {
            Picasso.with(view.getContext()).load(model.getImage().getUrl()).into(image);
            name.setText(model.getName());
            description.setText(model.getAddress());
        }
    }


}
