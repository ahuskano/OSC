package co.ahuskano.something.util.recycleView;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import co.ahuskano.something.models.BaseModel;
import co.ahuskano.something.models.Review;
import co.ahuskano.something.models.Space;


public class RecycleAdapter<T extends BaseModel> extends DataBinderAdapter {

    private List<T> items;
    private Activity activity;

    public RecycleAdapter(Activity activity,List<T> items) {
        this.items = items;
        this.activity=activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    /*
    We override method from RecyclerView.Adapter to use only one adapter for every list.
    Now we can custom setup ids for ViewType in Repsly app.
    Considering this method result class will know which binder to use.
     */
    @Override
    public int getItemViewType(int position) {
        if (items.get(0) instanceof Space) {
            Log.d("test", "distance: " + ((Space) items.get(0)).getDistance());
            if (((Space) items.get(0)).getDistance() == 0)
                return DataBinder.SPACE_BINDER;
            else
                return DataBinder.SPACE_BINDER_NEAR;
        }else if(items.get(0) instanceof Review){
            return DataBinder.REVIEW_BINDER;
        }
            return 0;
    }

    /*
    We override method from RecyclerView.Adapter to use only one adapter for every list.
    Considering viewType that method getItemViewType returned to us class know which binder to return
     */
    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        switch (viewType) {
            case DataBinder.SPACE_BINDER:
                return (T) new SpaceBinder(this);
            case DataBinder.SPACE_BINDER_NEAR:
                return (T) new SpaceNearBinder(this);
            case DataBinder.REVIEW_BINDER:
                return (T) new ReviewBinder(this);
            default:
                return null;
        }
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        return binderPosition;
    }

    @Override
    public int getBinderPosition(int position) {
        return position;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

}
