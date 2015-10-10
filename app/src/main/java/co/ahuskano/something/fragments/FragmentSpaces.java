package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.controllers.BaseController;
import retrofit.RetrofitError;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentSpaces extends BaseFragment implements BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    public FragmentSpaces() {
        super(FragmentFactory.FRAGMENT_SPACES);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_spaces;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {

    }

    @Override
    public void onDataReceive(BaseResponse response) {

    }
}
