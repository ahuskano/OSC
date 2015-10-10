package co.ahuskano.something.util.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class DataBinder <T extends RecyclerView.ViewHolder>{

    public static final int DEMO_BINDER=1;
        public RecycleAdapter dataBindAdapter;

        public DataBinder(RecycleAdapter dataBindAdapter) {
            this.dataBindAdapter = dataBindAdapter;
        }

        /*
        This method is used to inflate specific layout that is used for binder
         */
        public View getView(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    provideLayout(), parent, false);
            return view;
        }

        abstract public T newViewHolder(ViewGroup parent);

        abstract public void bindViewHolder(T holder, int position);

        abstract public int getItemCount();

        abstract public int provideLayout();
    }
