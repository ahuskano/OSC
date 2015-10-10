package co.ahuskano.something.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.ahuskano.something.R;


public abstract class BaseFragment extends Fragment {

    public static String KEY_FRAGMENT="key_fragment";
    public abstract void initViews(View view);
    public abstract int getLayout();
    public abstract String getName();
    private ProgressDialog dialog;

    public BaseFragment(int tagId){
        Bundle args = new Bundle();
        args.putInt(KEY_FRAGMENT, tagId);
        setArguments(args);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(getLayout(),container,false);

        try{
            initViews(root);
        }catch(Throwable e){
            getActivity().finish();
        }
        return root;
    }

    public void reportIt(String msg, String type){


    }


    public void showDialog(){
        if(dialog==null)
            setUpDialog();
        dialog.show();
    }

    private void setUpDialog(){
        dialog=new ProgressDialog(getActivity(), R.style.DialogTheme);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
    }

    public void dismissDialog(){
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }
}
