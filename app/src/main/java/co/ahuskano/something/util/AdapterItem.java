package co.ahuskano.something.util;


import co.ahuskano.something.models.BaseModel;
import co.ahuskano.something.util.recycleView.BasicModel;

public interface AdapterItem <T extends BaseModel> {

        void findViews();

        void fillDate(T model);
}

