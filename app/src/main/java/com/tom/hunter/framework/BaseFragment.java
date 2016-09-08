package com.tom.hunter.framework;

import android.support.v4.app.Fragment;


/**
 * Base Fragment, all fragment must have own activity
 * and the activity must be implement OnFragmentInteractionListener for future used
 *
 * @author Tom Xu
 * @Date 3/07/2016
 */
public class BaseFragment extends Fragment {

    protected String mTag;

    public BaseFragment() {
        // Required empty public constructor
    }

    public String getFragmentTag() {
        return mTag;
    }
}
