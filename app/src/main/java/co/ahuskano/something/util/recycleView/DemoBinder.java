package co.ahuskano.something.util.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import co.ahuskano.something.R;
import co.ahuskano.something.util.AdapterItem;

public class DemoBinder extends DataBinder<DemoBinder.ViewHolder> {

    public DemoBinder(RecycleAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        return new ViewHolder(getView(parent));
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        /*holder.findViews();
        holder.fillDate((LokacijaListView) dataBindAdapter.getItem(position));
        */
    }

    @Override
    public int getItemCount() {
        return dataBindAdapter.getItemCount();
    }

    @Override
    public int provideLayout() {
        return R.layout.demo_binder;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements AdapterItem<DemoModel> {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void findViews() {

        }

        @Override
        public void fillDate(DemoModel model) {

        }
    }


}
