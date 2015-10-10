package co.ahuskano.something.util;


import co.ahuskano.something.util.recycleView.BasicModel;

public interface AdapterItem <T extends BasicModel> {

        void findViews();

        void fillDate(T model);
}

