package co.ahuskano.something.util.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

abstract public class DataBinderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int CLIENT_BINDER = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).newViewHolder(parent);
    }

    /*
    This method will take specific binder with getDataBinder() (considering viewType) that we need
    for item in recyclerview and then will call bindViewHolder method in specific binder that will
    call findViews and fillData.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int binderPosition = getBinderPosition(position);

        if (getDataBinder(viewHolder.getItemViewType()) != null)
            getDataBinder(viewHolder.getItemViewType()).bindViewHolder(viewHolder, binderPosition);
    }

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position);

    public abstract <T extends DataBinder> T getDataBinder(int viewType);

    public abstract int getPosition(DataBinder binder, int binderPosition);

    public abstract int getBinderPosition(int position);
}
