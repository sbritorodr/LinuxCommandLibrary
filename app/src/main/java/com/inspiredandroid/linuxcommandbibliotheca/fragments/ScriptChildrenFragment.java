package com.inspiredandroid.linuxcommandbibliotheca.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspiredandroid.linuxcommandbibliotheca.R;
import com.inspiredandroid.linuxcommandbibliotheca.ScriptChildrenActivity;
import com.inspiredandroid.linuxcommandbibliotheca.adapter.ScriptChildrenAdapter;
import com.inspiredandroid.linuxcommandbibliotheca.models.CommandGroupModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by Simon Schubert
 */
public class ScriptChildrenFragment extends SuperFragment {

    @BindView(R.id.fragment_scriptchildren_rv)
    RecyclerView mRecyclerView;
    private Realm mRealm;

    public ScriptChildrenFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scriptchildren, container, false);

        ButterKnife.bind(this, view);

        int categoryId = getActivity().getIntent().getIntExtra(ScriptChildrenActivity.EXTRA_CATEGORY_ID, 0);

        ScriptChildrenAdapter adapter = new ScriptChildrenAdapter(getContext(), mRealm.where(CommandGroupModel.class).equalTo(CommandGroupModel.COLUMN_CATEGORY, categoryId).findAllSorted("votes", Sort.DESCENDING), false);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mRealm.close();
    }
}
