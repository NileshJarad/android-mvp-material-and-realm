package com.nileshjarad.realmdemo.views.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.nileshjarad.realmdemo.R;
import com.nileshjarad.realmdemo.models.VisitingCardPOJO;
import com.nileshjarad.realmdemo.presenters.VisitingCardDetailsPresenterImpl;
import com.nileshjarad.realmdemo.views.activities.views.VisitingCardDetailsView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;

import android.hardware.camera2.CameraManager;

public class VisitingCardDetailsActivity extends AppCompatActivity implements VisitingCardDetailsView {

    private static final String LOG_TAG = VisitingCardDetailsActivity.class.getSimpleName();

    // for the intent key
    public static final String ITEM_ID_TO_UPDATE = "item_id";

    @InjectView(R.id.tool_bar)
    Toolbar toolBar;
    @InjectView(R.id.edt_id)
    EditText edtId;
    @InjectView(R.id.til_id)
    TextInputLayout tilId;
    @InjectView(R.id.edt_name)
    EditText edtName;
    @InjectView(R.id.til_name)
    TextInputLayout tilName;
    @InjectView(R.id.edt_address)
    EditText edtAddress;
    @InjectView(R.id.til_address)
    TextInputLayout tilAddress;

    private VisitingCardDetailsPresenterImpl visitingCardDetailsPresenterImpl;

    // item id for the update card
    private int item_id = 0;

    // decides whether card is to add or update
    private boolean isEditCard = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_card_details);
        ButterKnife.inject(this);
        visitingCardDetailsPresenterImpl = new VisitingCardDetailsPresenterImpl(this);
        initViews();
        getIntentData();

    }

    /**
     * Check whether previous activity sent data in intent or not
     * <p/>
     * If sent then extract data
     */
    private void getIntentData() {
        Bundle extrasFromActivity = getIntent().getExtras();
        if (extrasFromActivity != null) {
            item_id = extrasFromActivity.getInt(ITEM_ID_TO_UPDATE);
            visitingCardDetailsPresenterImpl.getCard(Realm.getInstance(this), item_id);

        } else {
            visitingCardDetailsPresenterImpl.getNextId(Realm.getInstance(this));
        }
    }


    /***
     * Initialize the views here
     */
    private void initViews() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visiting_card_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_add_new_card) {
            VisitingCardPOJO cardPOJO = createCard();
            if (visitingCardDetailsPresenterImpl.verifyContent(cardPOJO)) {

                if (isEditCard)
                    visitingCardDetailsPresenterImpl.updateCard(Realm.getInstance(this), cardPOJO);
                else
                    visitingCardDetailsPresenterImpl.saveNewCard(Realm.getInstance(this), cardPOJO);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /***
     * Create the Visiting card from the entered data
     *
     * @return
     */
    private VisitingCardPOJO createCard() {
        VisitingCardPOJO cardPOJO = new VisitingCardPOJO();

//        Toast.makeText(this, "Card id :" + Integer.parseInt(edtId.getText().toString().trim()), Toast
//                .LENGTH_LONG).show();
        cardPOJO.setNo(Integer.parseInt(edtId.getText().toString().trim()));
        cardPOJO.setName("" + edtName.getText().toString().trim());
        cardPOJO.setAddress("" + edtAddress.getText().toString().trim());
        return cardPOJO;
    }

    /**
     * Set next id to insert in database
     *
     * @param id to set
     */
    @Override
    public void setNextId(int id) {

        id = id + 1;
        edtId.setText("" + id);
        edtName.requestFocus();
    }


    @Override
    public void setErrorForName() {
        Snackbar.make(edtName, "Please enter the name", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setErrorForAddress() {
        Snackbar.make(edtAddress, "Please enter the Address", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showCardToEdit(VisitingCardPOJO cardDetailForID) {

        isEditCard = true;
        getSupportActionBar().setTitle(getString(R.string.title_update_visiting_card));
        edtId.setText("" + cardDetailForID.getNo());
        edtName.setText(cardDetailForID.getName());
        edtAddress.setText(cardDetailForID.getAddress());
    }
}
