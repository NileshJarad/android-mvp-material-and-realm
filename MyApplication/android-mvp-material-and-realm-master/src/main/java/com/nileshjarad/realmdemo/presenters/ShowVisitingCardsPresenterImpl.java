package com.nileshjarad.realmdemo.presenters;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;
import com.nileshjarad.realmdemo.views.activities.views.ShowVisitingCardView;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by admin on 18-06-2015.
 */
public class ShowVisitingCardsPresenterImpl implements ShowVisitingCardsPresenter {

    private final ShowVisitingCardsInteractor interactor;
    private final ShowVisitingCardView view;

    public ShowVisitingCardsPresenterImpl(ShowVisitingCardView
                                                  view) {
        this.interactor = new ShowVisitingCardsInteractor(this);
        this.view = view;
    }

    @Override
    public void getVisitingCards(Realm realm) {
        view.showAllVisitingCards(interactor.getAllVisitingCards(realm));
    }

    @Override
    public void startVisitingCardActivity() {
        view.showVisitingCardScreen();
    }

    @Override
    public void startVisitingCardUpdateActivity(int item_id) {
        view.showVisitingCardScreenForUpdate(item_id);
    }

    @Override
    public void referesVisitingCardList(int postion) {
        view.refereshRecyclerView(postion);
    }

    @Override
    public void detletCardFromRealm(Realm realm, int card_id, int position) {
        interactor.deleteCard(realm, card_id, position);
    }
}
