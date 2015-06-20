package com.nileshjarad.realmdemo.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.nileshjarad.realmdemo.R;
import com.nileshjarad.realmdemo.models.VisitingCardPOJO;
import com.nileshjarad.realmdemo.presenters.ShowVisitingCardsPresenterImpl;
import com.nileshjarad.realmdemo.views.activities.views.ShowVisitingCardView;
import com.nileshjarad.realmdemo.views.adapters.VisitingCardsAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class ShowVisitingCardsActivity extends AppCompatActivity implements ShowVisitingCardView {

    private static final String LOG_TAG = ShowVisitingCardsActivity.class.getSimpleName();

    @InjectView(R.id.tool_bar)
    Toolbar toolBar;
    @InjectView(R.id.recycler_view_cards)
    RecyclerView recyclerViewCards;
    private VisitingCardsAdapter mAdapter;
    private ShowVisitingCardsPresenterImpl showVisitingCardsPresenterImpl;


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            //Remove swiped item from list and notify the RecyclerView

            if (swipeDir == ItemTouchHelper.LEFT) {
                Snackbar.make(recyclerViewCards, getString(R.string.item_deleted),
                        Snackbar
                                .LENGTH_LONG)
                        .show();
                showVisitingCardsPresenterImpl.detletCardFromRealm(Realm.getInstance
                                (ShowVisitingCardsActivity.this), mAdapter.getID(viewHolder
                                .getAdapterPosition()), viewHolder
                                .getAdapterPosition()
                );

            } else {
                showVisitingCardsPresenterImpl.startVisitingCardUpdateActivity(mAdapter.getID(viewHolder
                        .getAdapterPosition()));
            }
        }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);
        ButterKnife.inject(this);
        showVisitingCardsPresenterImpl = new ShowVisitingCardsPresenterImpl(
                this);

        initViews();

    }

    private void initViews() {
        setSupportActionBar(toolBar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showVisitingCardsPresenterImpl.getVisitingCards(Realm.getInstance(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_cards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            showVisitingCardsPresenterImpl.startVisitingCardActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showAllVisitingCards(ArrayList<VisitingCardPOJO> allVisitingCards) {
        recyclerViewCards.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewCards.setLayoutManager(mLayoutManager);
        mAdapter = new VisitingCardsAdapter(allVisitingCards);
        recyclerViewCards.setAdapter(mAdapter);
        // attached recycler view for swipe to dismiss event
        itemTouchHelper.attachToRecyclerView(recyclerViewCards);
    }

    @Override
    public void showVisitingCardScreen() {
        startActivity(new Intent(this, VisitingCardDetailsActivity.class));
    }

    @Override
    public void showVisitingCardScreenForUpdate(int item_id) {

        Intent updateCardIntent = new Intent(this, VisitingCardDetailsActivity.class);
        updateCardIntent.putExtra(VisitingCardDetailsActivity.ITEM_ID_TO_UPDATE, item_id);
        startActivity(updateCardIntent);

    }

    @Override
    public void refereshRecyclerView(int postion) {
        mAdapter.removeItem(postion);
    }


}
